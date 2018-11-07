package edu.gatech.cs2340.nonprofitdonationtracker.controllers.dummy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import edu.gatech.cs2340.nonprofitdonationtracker.controllers.Donation;

public class DonationMap implements Serializable {
    public HashMap<String, ArrayList<Donation>> map;

    public DonationMap() {
        map = new HashMap<>();
    }

    public DonationMap(HashMap<String, ArrayList<Donation>> map) {
        this.map = map;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> cdbe05badc0bc447837db74c79e6311166e23015
