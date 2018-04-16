package com.teamcho.sheltersearch.model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Singleton class to hold data downloaded from firebase.
 *
 * Created by Luis G. on 2/13/2018.
 */

public class Database {

    private static Database db_instance = null;

    private Database() {}

    /**
     * A method to get the database's singleton instance
     * @return The Database object
     */
    public static Database getInstance() {
        if (db_instance == null)
            db_instance = new Database();

        return db_instance;
    }

    private ArrayList<String> shelterNameList = new ArrayList<>();
    private ArrayList<Shelter> shelterList = new ArrayList<>();
    private final HashMap<String, User> userList = new HashMap<>();

    /**
     * A method to load the data on firebase into the local database
     */
    public void loadData() {
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
                    assert s != null;
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
                    assert r != null;
                    userList.put(r.getUid(), r);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * A method used to clear the local database's data
     */
    public void clearData() {
        shelterNameList.clear();
        shelterList.clear();
        userList.clear();
    }

    /**
     * A method to get the list with all the shelters' names
     * @return The array list with the shelter's names
     */
    public ArrayList<String> getShelterNameList() {
        return shelterNameList;
    }

    /**
     * A method to set the database's list of names
     * @param sNameList A list of shelter names
     */
    public void setShelterNameList(ArrayList<String> sNameList) {
        shelterNameList = sNameList;
    }

    /**
     * A method to get the list of shelter's in the database
     * @return The list of shelters
     */
    public ArrayList<Shelter> getShelterList() {
        return shelterList;
    }

    /**
     * A method to update the list of shelters in the database
     * @param sList The list of shelters
     */
    public void setShelterList(ArrayList<Shelter> sList) {
        shelterList = sList;
    }

    /**
     * A method to get a list of user's in the database
     * @return A list of User objects
     */
    public HashMap<String, User> getUserList() {
        return userList;
    }


}
