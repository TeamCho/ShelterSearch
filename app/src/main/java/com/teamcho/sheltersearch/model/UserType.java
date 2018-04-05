package com.teamcho.sheltersearch.model;

/**
 * An enum class to hold a user's possible types
 * Created by rahulzhade on 2/27/18.
 */

public enum UserType {
    ADMIN ("admin"),
    USER ("user");

    private final String type;

    /**
     * Setting the user type
     * @param type The type of user
     */
    UserType(String type) {
        this.type = type;
    }

    /**
     * A method to get the type of user
     * @return The user type
     */
    public String getUserType() {
        return this.type;
    }
}
