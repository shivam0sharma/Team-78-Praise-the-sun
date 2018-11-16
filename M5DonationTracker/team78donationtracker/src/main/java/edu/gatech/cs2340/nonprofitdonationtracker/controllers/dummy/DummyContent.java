package edu.gatech.cs2340.nonprofitdonationtracker.controllers.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.cs2340.nonprofitdonationtracker.controllers.Charity;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.Donation;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent implements java.io.Serializable {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Charity> ITEMS = new ArrayList<Charity>();
    public static final List<String> ITEMNAMES = new ArrayList<>();
    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Charity> ITEM_MAP = new HashMap<String, Charity>();

    public static DonationMap DONATIONS_MAP = new DonationMap();

    public static void setup(ArrayList<Charity> charities) {
        removeAllItems();

        for (Charity charity: charities) {
            addItem(charity);
            ArrayList<Donation> arr = new ArrayList<Donation>();
        }
    }

    private static void removeAllItems() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    private static void addItem(Charity item) {
        ITEMS.add(item);
        ITEMNAMES.add(item.getName());
        ITEM_MAP.put(item.getKey(), item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final Charity charity;

        public DummyItem(Charity charity) {
            this.id = charity.getKey();
            this.charity = charity;
        }

        @Override
        public String toString() {
            return charity.getName();
        }
    }
}