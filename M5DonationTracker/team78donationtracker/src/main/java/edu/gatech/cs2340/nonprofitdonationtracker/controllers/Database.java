package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import edu.gatech.cs2340.nonprofitdonationtracker.controllers.dummy.DonationMap;

public class Database {
    public static String current;
    public static String currentDonation;
    public static List<Donation> donations;
    public static String scope;
    public static final List<Charity> ITEMS = new ArrayList<Charity>();
    public static DonationMap DONATIONS_MAP = new DonationMap();
    public static final Map<String, Charity> ITEM_MAP = new HashMap<String, Charity>();
    public static void setup(ArrayList<Charity> charities) {
        removeAllItems();

        for (Charity charity: charities) {
            addItem(charity);
            ArrayList<Donation> arr = new ArrayList<Donation>();
            DONATIONS_MAP.map.put(charity.getName(), arr);
        }
    }
    private static void removeAllItems() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    private static void addItem(Charity item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getKey(), item);
    }


}
