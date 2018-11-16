package edu.gatech.cs2340.nonprofitdonationtracker.controllers;



public enum UserType {
    USER("User"),
    ADMIN("Admin"),
    LOCATION_EMPLOYEE("Location Employee");

    public String userTypeInString;

    UserType(String userTypeInString) {
        this.userTypeInString = userTypeInString;
    }

    @Override
    public String toString() {
        return userTypeInString;
    }
}

