package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import edu.gatech.cs2340.nonprofitdonationtracker.controllers.Database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.dummy.DummyContent;

public class DonationPageActivity extends AppCompatActivity {

    private Spinner donationSpinner;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_page);

        donationSpinner = (Spinner) findViewById(R.id.DonationSpinner);
        if (DummyContent.DONATIONS_MAP.get(Database.current).isEmpty()) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add("No donations available.");
            ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, arr);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            donationSpinner.setAdapter(adapter);
        } else {
            ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, DummyContent.DONATIONS_MAP.get(Database.current));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            donationSpinner.setAdapter(adapter);
        }
    }

    public void OnClickConfirm(View view) {
        String donation =  donationSpinner.getSelectedItem().toString();

        if (!(donation.equals("No donations available."))) {
            Database.currentDonation = donation;
            Intent intent = new Intent(this, DonationInfoPageActivity.class);
            startActivity(intent);
        }
    }

    public void OnClickAdd(View view) {
        Intent intent = new Intent(this, AddDonationPageActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickCancel(View view) {
        finish();
    }

}
