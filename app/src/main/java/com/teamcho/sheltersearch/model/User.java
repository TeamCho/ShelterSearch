package com.teamcho.sheltersearch.model;

/**
 * Created by rahulzhade on 2/27/18.
 */

public class User {
    private String uid;
    private String name;
    private String email;
    private UserType userType;

    public User() {

    }

    public User(String uid, String email, String name, UserType userType) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.userType = userType;
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

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return this.userType.getUserType();
    }
}
