package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

/**
 * Charity type enum.
 */
public enum CharityType {

    DROP_OFF, STORE, WAREHOUSE;

    /**
     * Returns name
     * @param charityType charity type
     * @return name
     */
    public static CharSequence getName(CharityType charityType) {
        switch (charityType) {
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

    /**
     * Returns charity type of string
     * @param string string of charity type
     * @return the charity type
     */
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