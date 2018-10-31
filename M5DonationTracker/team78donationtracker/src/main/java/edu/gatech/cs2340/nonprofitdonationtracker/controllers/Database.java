package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Database {
    public static String current;
    public static String currentDonation;
    public static List<Charity> charities = new ArrayList<>();
    public static Map<String, ArrayList<Donation>> donations_map = new HashMap<>();
    public static List<Donation> donations;


}
