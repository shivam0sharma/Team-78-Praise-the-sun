package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import java.util.ArrayList;

/**
 * Donation category enum
 */
public enum Category {
    CLOTHING, HAT, KITCHEN, ELECTRONICS, HOUSEHOLD, OTHER;

    /**
     * Returns category as string
     * @param input category
     * @return category as string
     */
    public static CharSequence getCategory(Category input) {
        switch(input) {
            case CLOTHING:
                return "CLOTHING";
            case HAT:
                return "HAT";
            case KITCHEN:
                return "KITCHEN";
            case ELECTRONICS:
                return "ELECTRONICS";
            case HOUSEHOLD:
<<<<<<< HEAD
                return "HOUSEHOLD";
            default:
                return "OTHER";
=======
                return "Household";
            case OTHER:
                return "Other";
            default:
                return "Undefined";
>>>>>>> 4128f121db44791d23f1956350dcf9fb58eb7893
        }
    }

    /**
     * Returns category string as category type
     * @param string category string
     * @return string as category type
     */
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

    /**
     * Returns list of categories
     * @return list of categories
     */
    public static ArrayList<String> list() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Clothing");
        arr.add("Hat");
        arr.add("Kitchen");
        arr.add("Electronics");
        arr.add("Household");
        arr.add("Other");
        return arr;
    }
}
