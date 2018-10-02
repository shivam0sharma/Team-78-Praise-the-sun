package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

/**
 * Created by Neha on 9/29/2018.
 */

public enum UserType {
    USER("User"),
    ADMIN("Admin"),
    LOCATION_EMPLOYEE("Location Employee");

    private String userTypeInString;

    private UserType(String userTypeInString) {
        this.userTypeInString = userTypeInString;
    }

    @Override
    public String toString() {
        return userTypeInString;
    }
}

