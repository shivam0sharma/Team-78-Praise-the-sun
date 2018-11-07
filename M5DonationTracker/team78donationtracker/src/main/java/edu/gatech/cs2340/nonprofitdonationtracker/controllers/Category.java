package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import java.util.ArrayList;

public enum Category {
    CLOTHING, HAT, KITCHEN, ELECTRONICS, HOUSEHOLD, OTHER;


    public static String getCategory(Category input) {
        switch(input) {
            case CLOTHING:
                return "Clothing";
            case HAT:
                return "Hat";
            case KITCHEN:
                return "Kitchen";
            case ELECTRONICS:
                return "Electronics";
            case HOUSEHOLD:
                return "Household";
            default:
                return "Other";
        }
    }
    public static Category category(String string) {
        switch (string) {
            case "Clothing":
                return CLOTHING;
            case "Hat":
                return HAT;
            case "Kitchen":
                return KITCHEN;
            case "Electronics":
                return ELECTRONICS;
            case "Household":
                return HOUSEHOLD;
            default:
                return OTHER;
        }
    }

    public static ArrayList<String> list() {
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("Clothing");
        arr.add("Hat");
        arr.add("Kitchen");
        arr.add("Electronics");
        arr.add("Household");
        arr.add("Other");
        return arr;
    }
}
