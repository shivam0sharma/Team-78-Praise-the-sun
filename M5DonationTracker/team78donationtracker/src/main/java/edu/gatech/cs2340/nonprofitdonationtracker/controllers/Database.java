package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import java.util.Arrays;
import java.util.List;

public class Database {
    public static String current;
    public static List<String> Locations = Arrays.asList("AFD Station 4", "BOYS & GILRS CLUB W.W. WOOLFOLK", "PATHWAY UPPER ROOM CHRISTIAN MINISTRIES", "PAVILION OF HOPE INC", "D&D CONVENIENCE STORE", "KEEP NORTH FULTON BEAUTIFUL");

    public class Location {
        public String name;
        public String type;
        public String latitude;
        public String longitude;
        public String street_address;
        public String phone;
        public Location() {
            name = name;
            type = "Drop Off";
            latitude = "33.75416";
            longitude = "-84.37742";
            street_address = "309 EDGEWOOD AVE SE";
            phone = "(404) 555 - 3456";
        }
    }
}
