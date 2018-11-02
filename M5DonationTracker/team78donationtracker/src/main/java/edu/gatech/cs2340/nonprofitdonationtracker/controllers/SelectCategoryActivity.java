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
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.dummy.DummyContent;

public class SelectCategoryActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Category.list());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
    }

    public void onClickConfirm(View view) {
        Category category =  Category.category(categorySpinner.getSelectedItem().toString());
        List<Donation> listDonations = new ArrayList<Donation>();
        if (Database.scope.equals("All")) {
            for (Charity charity : DummyContent.ITEMS) {
                for (Donation donation : DummyContent.DONATIONS_MAP.get(charity.getName())) {
                    if (donation.getCategory().equals(category)) {
                        listDonations.add(donation);
                    }
                }
            }
        } else {
            for (Donation donation : Database.donations) {
                if (donation.getCategory().equals(category)) {
                    listDonations.add(donation);
                }
            }
        }
        Database.donations = listDonations;
        if (Database.donations.isEmpty()) {
            Intent intent = new Intent(this, EmptyListActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SearchDonationNameActivity.class);
            startActivity(intent);
        }
    }

    public void onClickCancel(View view) {
        finish();
    }

}
