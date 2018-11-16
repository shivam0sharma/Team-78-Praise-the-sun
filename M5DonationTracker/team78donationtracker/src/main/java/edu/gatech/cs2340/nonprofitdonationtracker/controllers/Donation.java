package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.support.annotation.NonNull;

/**
 * Donation class. DO NOT DELETE UNUSED GETTERS. NEEDED FOR SERIALIZABLE.
 */
public class Donation implements java.io.Serializable {
    private String name;
    private String timeStamp;
    private String location;
    private String shortDescription;
    private String fullDescription;
    private double value;
    private Category category;

    /**
     * Donation constructor.
     * @param name name
     * @param timeStamp time stamp
     * @param location location
     * @param shortDescription short description
     * @param fullDescription fullDescription
     * @param value value
     */
    public Donation(String name, String timeStamp, String location, String shortDescription,
                    String fullDescription, double value) {
        this.name = name;
        this.timeStamp = timeStamp;
        this.location = location;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.value = value;
    }

    /**
     * Donation constructor
     */
    public Donation() {
        name = "asdf";
        timeStamp = "asdf";
        location = "asdf";
        shortDescription = "asdf";
        fullDescription = "asdf";
        value = 4.20;
        category = Category.CLOTHING;
    }

    /**
     * Returns name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns time stamp
     * @return time stamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Gets location
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns short description
     * @return short decription
     */
    public CharSequence getShortDescription() {
        return shortDescription;
    }

    /**
     * Returns full description
     * @return full description
     */
    public CharSequence getFullDescription() {
        return fullDescription;
    }

    /**
     * Returns value
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * Returns category
     * @return category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets time stamp
     * @param timeStamp time stamp
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Sets location
     * @param location location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets short description
     * @param shortDescription short description
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Sets full description
     * @param fullDescription full description
     */
    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    /**
     * Sets value
     * @param value value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Sets category
     * @param category category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
