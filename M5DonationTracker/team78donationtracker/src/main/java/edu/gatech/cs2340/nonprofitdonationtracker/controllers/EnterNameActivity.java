package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.dummy.DummyContent;

public class EnterNameActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
    }

    public void OnClickConfirm(View view) {
        EditText name = (EditText)findViewById(R.id.nameText);
        String name_value = name.getText().toString();
        List<Donation> listDonations = new ArrayList<Donation>();
        if (Database.scope.equals("All")) {
            for (Charity charity : DummyContent.ITEMS) {
                for (Donation donation : DummyContent.DONATIONS_MAP.get(charity.getName())) {
                    if (donation.getName().contains(name_value)) {
                        listDonations.add(donation);
                    }
                }
            }
        } else {
            for (Donation donation : Database.donations) {
                if (donation.getName().contains(name_value)) {
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
