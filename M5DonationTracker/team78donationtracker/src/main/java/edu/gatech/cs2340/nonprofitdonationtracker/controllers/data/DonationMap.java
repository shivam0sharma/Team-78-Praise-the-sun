package edu.gatech.cs2340.nonprofitdonationtracker.controllers.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.gatech.cs2340.nonprofitdonationtracker.controllers.Donation;

/**
 * Donation map class
 */
public class DonationMap implements Serializable {
    public final Map<String, ArrayList<Donation>> map;

    /**
     * DonationMap default constructor
     */
    public DonationMap() {
        map = new HashMap<>();
    }

    /**
     * DonationMap constructor
     * @param map donation map
     */
    public DonationMap(Map<String, ArrayList<Donation>> map) {
        this.map = map;
    }
}
