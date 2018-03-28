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
 * Here to hold the map for usernames and passwords.
 * Also handles setting new usernames and passwords.
 *
 * Created by lgpso on 2/13/2018.
 */

public class Database {

    private static Map<String, String> userMap = new HashMap<String, String>(){{ put("admin", "pass123"); }};
    private static ArrayList<String> shelterNameList = new ArrayList<>();
    private static ArrayList<Shelter> shelterList = new ArrayList<>();
    private static ArrayList<User> userList = new ArrayList<>();


    public static  void loadData() {
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

    public static ArrayList<String> getShelterNameList() {
        return shelterNameList;
    }

    public static void setShelterNameList(ArrayList<String> shelterNameList) {
        Database.shelterNameList = shelterNameList;
    }

    public static ArrayList<Shelter> getShelterList() {
        return shelterList;
    }

    public static void setShelterList(ArrayList<Shelter> shelterList) {
        Database.shelterList = shelterList;
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static boolean contains(String name){
        String stored = userMap.get(name);

        if(stored != null){
            return true;
        } else {
            return false;
        }
    }

    public static boolean newEntry(String username, String pass) {
        userMap.put(username, pass);
        return true;
    }

    public static String getPass(String username) {
        return userMap.get(username);
    }

}
