package com.teamcho.sheltersearch.model;

/**
 * Created by rahulzhade on 2/27/18.
 */

public class User {
    private String uid;
    private String name;
    private String email;
    private String userType;
    private int booking;
    private int bedsTaken;

    public User() {

    }

    public User(String uid, String email, String name, String userType, int booking, int bedsTaken) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.booking = booking;
        this.bedsTaken = bedsTaken;
    }

    public String getUid() {
        return this.uid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return this.userType;
    }

    public int getBooking() {
        return this.booking;
    }

    public void setBooking(int shelter) {
        this.booking = shelter;
    }

    public int getBedsTaken() {return this.bedsTaken;}

    public void setBedsTaken(int num) {this.bedsTaken = num;}
}
