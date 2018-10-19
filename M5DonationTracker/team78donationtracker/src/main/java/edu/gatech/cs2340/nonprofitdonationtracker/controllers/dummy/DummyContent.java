package edu.gatech.cs2340.nonprofitdonationtracker.controllers.dummy;

import edu.gatech.cs2340.nonprofitdonationtracker.controllers.Charity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static void setup(ArrayList<Charity> charities) {
        removeAllItems();
        for (Charity charity: charities) {
            DummyItem d = new DummyItem(charity);
            addItem(d);
        }
    }

    private static void removeAllItems() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
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