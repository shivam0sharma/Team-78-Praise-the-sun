package edu.gatech.cs2340.nonprofitdonationtracker.controllers.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.gatech.cs2340.nonprofitdonationtracker.controllers.Charity;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.Donation;

/**
 * Class that stores information
 */
public class InfoDump implements java.io.Serializable {

    public static String current;
    public static String currentDonation;
    public static List<Donation> donations;
    public static String scope;
    public static final List<Charity> ITEMS = new ArrayList<>();
    public static DonationMap DONATIONS_MAP = new DonationMap();

    /**
     * Sets up the lists
     * @param charities list of charities
     */
    public static void setup(Iterable<Charity> charities) {
        System.out.println("Set Up");
        cleanUp();
        for (Charity charity: charities) {
            addItem(charity);
            ArrayList<Donation> arr = new ArrayList<>();
            DONATIONS_MAP.map.put(charity.getName(), arr);
        }
    }

    /**
     * Sets up the lists and donations as well.
     * @param charities list of charities
     */
    public static void setUpEverything(Iterable<Charity> charities) {
        System.out.println("Set Up Everything");
        cleanUp();
        HashMap<String, ArrayList<Donation>> map = new HashMap<>();
        for (Charity charity: charities) {
            addItem(charity);
            ArrayList<Donation> arr = new ArrayList<>();
            map.put(charity.getName(), arr);
        }
        DONATIONS_MAP = new DonationMap(map);
    }
    private static void cleanUp() {
        ITEMS.clear();
    }

    private static void addItem(Charity item) {
        ITEMS.add(item);
    }

}