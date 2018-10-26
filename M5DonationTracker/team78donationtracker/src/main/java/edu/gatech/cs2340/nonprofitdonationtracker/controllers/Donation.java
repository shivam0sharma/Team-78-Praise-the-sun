package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

public class Donation {
    private String name;
    private String timeStamp;
    private String location;
    private String shortDescription;
    private String fullDescription;
    private double value;
    private Category category;

    public Donation(String name, String timeStamp, String location, String shortDescription, String fullDescription, double value) {
        this.name = name;
        this.timeStamp = timeStamp;
        this.location = location;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.value = value;
    }

    public Donation() {
        name = "asdf";
        timeStamp = "asdf";
        location = "asdf";
        shortDescription = "asdf";
        fullDescription = "asdf";
        value = 12.23;
        category = Category.CLOTHING;
    }

    public String getName() {
        return name;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getLocation() {
        return location;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public double getValue() {
        return value;
    }

    public Category getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return name;
    }
}
