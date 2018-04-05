package com.teamcho.sheltersearch.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.teamcho.sheltersearch.model.Database;

/**
 *
 * Created by Luis
 */

public class Shelter {

    private String address;
    private String capacity;
    private double latitude;
    private double longitude;
    private String phoneNum;
    private String restrictions;
    private String name;
    private String notes;
    private int key;
    private int vacancies;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    public Shelter() {
        
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }

    public void onBook(int bedsTaken, DatabaseReference mDatabase, FirebaseUser currentUser) {
        if (vacancies - bedsTaken < 0) {
            throw new IllegalArgumentException("There are not enough beds to make this booking.");
        }

        //Updates the current amount of vacancies
        this.setVacancies(vacancies - bedsTaken);

        //Updates the user's bedsTaken
        mDatabase.child(currentUser.getUid()).child("bedsTaken").setValue(bedsTaken);
        //Updates the user's booking
        mDatabase.child(currentUser.getUid()).child("booking").setValue(this.getKey());
        DatabaseReference dbRef = database.getReference("/Shelter");
        dbRef.child(this.getKey() + "").child("vacancies").setValue(vacancies - bedsTaken);
        Database.clearData();
        Database.loadData();
    }
}
