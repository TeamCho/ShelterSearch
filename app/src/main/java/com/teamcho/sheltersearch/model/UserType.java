package com.teamcho.sheltersearch.model;

/**
 * Created by rahulzhade on 2/27/18.
 */

public enum UserType {
    ADMIN ("admin"),
    USER ("user");

    private final String type;

    UserType(String type) {
        this.type = type;
    }

    public String getUserType() {
        return this.type;
    }
}
