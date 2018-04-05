package com.teamcho.sheltersearch.model;

/**
 * A user model class
 * Created by rahulzhade on 2/27/18.
 */

public class User {
    private String uid;
    private String name;
    private String email;
    private String userType;
    private int booking;
    private int bedsTaken;

    /**
     * A standard constructor for a User object
     */
    public User() {

    }

    /**
     * A constructor for User object with all the entries
     * @param uid The User's ID
     * @param email The User's email
     * @param name The User's name
     * @param userType The User's type
     * @param booking The User's booking number
     * @param bedsTaken The User's number of beds taken
     */
    public User(String uid, String email, String name, String userType, int booking, int bedsTaken) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.booking = booking;
        this.bedsTaken = bedsTaken;
    }

    /**
     * A method to get a user's ID
     * @return The user's ID
     */
    public String getUid() {
        return this.uid;
    }

    /**
     * A method to set the user's email
     * @param email The user's new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * A method to get a user's email
     * @return The user's email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * A method to change a user's name
     * @param name The user's new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A method to get a user's name
     * @return The name of the user
     */
    public String getName() {
        return this.name;
    }

    /**
     * A method to set a user's type
     * @param userType The user's new type (individual, employee, admin)
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * A method to get a user's type
     * @return The user's type
     */
    public String getUserType() {
        return this.userType;
    }

    /**
     * A method to get a users booking shelter
     * @return The user's booing shelter
     */
    public int getBooking() {
        return this.booking;
    }

    /**
     * A method to set a user's booking shelter
     * @param shelter The user's new booking shelter
     */
    public void setBooking(int shelter) {
        this.booking = shelter;
    }

    /**
     * A method to get the number of beds a user takes up
     * @return The number of beds
     */
    public int getBedsTaken() {return this.bedsTaken;}

    /**
     * A method to update the number of beds a user takes up
     * @param num The number of beds a user has reserved
     */
    public void setBedsTaken(int num) {this.bedsTaken = num;}
}
