package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

public enum CharityType {

    DROP_OFF, STORE, WAREHOUSE;

    public String getName() {
        switch (this) {
            case DROP_OFF:
                return "Drop Off";
            case STORE:
                return "Store";
            case WAREHOUSE:
                return "Warehouse";
            default:
                return "whatever";
        }
    }

    public static CharityType charityType(String string) {
        switch (string) {
            case "Drop Off":
                return DROP_OFF;
            case "Store":
                return STORE;
            case "Warehouse":
                return WAREHOUSE;
            default:
                return WAREHOUSE;
        }
    }

}