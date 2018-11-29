package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.data.InfoDump;

/**
 * Enter name page
 */
public class EnterNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
    }

    /**
     * Goes to search donation name page
     * @param view current view
     */
    public void OnClickConfirm(View view) {
        EditText name = findViewById(R.id.nameText);
        CharSequence n = name.getText();
        String name_value = n.toString();
        List<Donation> listDonations = new ArrayList<>();
        if ("All".equals(InfoDump.scope)) {
            for (Charity charity : InfoDump.ITEMS) {
                for (Donation donation : InfoDump.DONATIONS_MAP.map.get(charity.getName())) {
                    String na = donation.getName();
                    if (na.contains(name_value)) {
                        listDonations.add(donation);
                    }
                }
            }
        } else {
            for (Donation donation : InfoDump.donations) {
                String na = donation.getName();
                if (na.contains(name_value)) {
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
