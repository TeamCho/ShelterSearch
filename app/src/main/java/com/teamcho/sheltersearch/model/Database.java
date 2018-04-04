package com.teamcho.sheltersearch.model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A Singleton class to hold data downloaded from firebase.
 *
 * Created by Luis G. on 2/13/2018.
 */

public class Database {

    private static Database db_instance = null;

    private Database() {}

    public static Database getInstance() {
        if (db_instance == null)
            db_instance = new Database();

        return db_instance;
    }

    private Map<String, String> userMap = new HashMap<String, String>(){{ put("admin", "pass123"); }};
    private ArrayList<String> shelterNameList = new ArrayList<>();
    private ArrayList<Shelter> shelterList = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();


    public void loadData() {
        //shelterNameList = new ArrayList<>();
        //shelterList = new ArrayList<>();
        //userList = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("/Shelter");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //DataSnapshot dbSnapshot = dataSnapshot;
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot item : children) {
                    Shelter s = item.getValue(Shelter.class);
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final DatabaseReference userRef = database.getReference("/User");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot item : children) {
                    User r = item.getValue(User.class);
                    userList.add(r);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void clearData() {
        shelterNameList.clear();
        shelterList.clear();
        userList.clear();
    }

    public ArrayList<String> getShelterNameList() {
        return shelterNameList;
    }

    public void setShelterNameList(ArrayList<String> sNameList) {
        shelterNameList = sNameList;
    }

    public ArrayList<Shelter> getShelterList() {
        return shelterList;
    }

    public void setShelterList(ArrayList<Shelter> sList) {
        shelterList = sList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

//    public boolean contains(String name){
//        String stored = userMap.get(name);
//
//        if(stored != null){
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public boolean newEntry(String username, String pass) {
//        userMap.put(username, pass);
//        return true;
//    }
//
//    public String getPass(String username) {
//        return userMap.get(username);
//    }

}
