package com.teamcho.sheltersearch.model;

/**
 * A class to hold the information for each individual shelter
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

    /**
     *  Standard constructor for a new Shelter
     */
    public Shelter() {
        
    }

    /**
     * A method to get a shelter's adress
     * @return the shelter's adress
     */
    public String getAddress() {
        return address;
    }

    /**
     * A method to update a shelter's address
     * @param address A valid adress that will be updated in the model
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * A method to get a shelter's capacity
     * @return the shelter's capacity
     */
    public String getCapacity() {
        return capacity;
    }

    /**
     * A method to change a shelter's capacity
     * @param capacity The shelter's new capacity
     */
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    /**
     * A method to get a shelter's latitude
     * @return The shelter's latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * A method to change the shelter's latitude
     * @param latitude The shelter's new latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * A method to get the shelter's longitude
     * @return The shelter's longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * A method to change the shelter's longitude
     * @param longitude The shelter's new longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * A method to get the shelter's phone number
     * @return The shelter's phone number
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * A method to change the shelter's phone number
     * @param phoneNum The shelter's new phone number
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * A method to get the shelter's restrictions
     * @return The shelter's restrictions
     */
    public String getRestrictions() {
        return restrictions;
    }

    /**
     * A method to change the shelter's restrictions
     * @param restrictions The shelter's new restrictions
     */
    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    /**
     * A method to get the shelter's name
     * @return The shelter's name
     */
    public String getName() {
        return name;
    }

    /**
     * A method to change the shelter's name
     * @param name The shelter's new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A method to get the shelter's notes
     * @return The shelter's notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * A method to change the shelter's notes
     * @param notes The shelter's new notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * A method to get the shelter's key
     * @return The shelter's key
     */
    public int getKey() {
        return key;
    }

    /**
     * A method to change the shelter's key
     * @param key The shelter's new key
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * A method to get the shelter's vacancies
     * @return The shelter's vacancies
     */
    public int getVacancies() {
        return vacancies;
    }

    /**
     * A method to change the shelter's vacancies
     * @param vacancies The shelter's new vacancies
     */
    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }
}
