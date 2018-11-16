package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

/**
 * Charity class
 */
public class Charity {

    private String name;
    private double latitude;
    private double longitude;
    private String streetAddress;
    private CharityType type;
    private String phoneNumber;

    /**
     * Returns name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns latitude
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude
     * @param latitude latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns longitude
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude
     * @param longitude logitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets street address
     * @return street address
     */
    public CharSequence getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets street address
     * @param streetAddress street address
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Gets charity type
     * @return charity type
     */
    public CharityType getType() {
        return type;
    }

    /**
     * Sets charity type
     * @param type charity type
     */
    public void setType(CharityType type) {
        this.type = type;
    }

    /**
     * Gets phone number
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number
     * @param phoneNumber phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}