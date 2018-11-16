package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.data.InfoDump;

/**
 * Select category page
 */
public class SelectCategoryActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        categorySpinner = findViewById(R.id.categorySpinner);

        ArrayAdapter<String> adapter = new
                ArrayAdapter(this,android.R.layout.simple_spinner_item, Category.list());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
    }

    /**
     * Goes to search donation name page
     * @param view current view
     */
    public void onClickConfirm(View view) {
        Object o = categorySpinner.getSelectedItem();
        Category category =  Category.category(o.toString());
        List<Donation> listDonations = new ArrayList<>();
        if ("All".equals(InfoDump.scope)) {
            for (Charity charity : InfoDump.ITEMS) {
                for (Donation donation : InfoDump.DONATIONS_MAP.map.get(charity.getName())) {
                    Category c = donation.getCategory();
                    if (c.equals(category)) {
                        listDonations.add(donation);
                    }
                }
            }
        } else {
            for (Donation donation : InfoDump.donations) {
                Category c = donation.getCategory();
                if (c.equals(category)) {
                    listDonations.add(donation);
                }
            }
        }
        InfoDump.donations = listDonations;
        if (InfoDump.donations.isEmpty()) {
            Intent intent = new Intent(this, EmptyListActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SearchDonationNameActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Goes back to previous page
     * @param view current view
     */
    public void onClickCancel(View view) {
        finish();
    }

}
