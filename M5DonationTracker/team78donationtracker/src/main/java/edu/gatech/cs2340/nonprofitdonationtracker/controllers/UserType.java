package edu.gatech.cs2340.nonprofitdonationtracker.controllers;


import android.support.annotation.NonNull;

/**
 * User types enum.
 */
public enum UserType {
    USER("User"),
    ADMIN("Admin"),
    LOCATION_EMPLOYEE("Location Employee");

    private final String userTypeInString;

    UserType(String userTypeInString) {
        this.userTypeInString = userTypeInString;
    }

    @NonNull
    @Override
    public String toString() {
        return userTypeInString;
    }
}

