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
 * Donation page
 */
public class DonationPageActivity extends AppCompatActivity {

    private Spinner donationSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_page);

        donationSpinner = findViewById(R.id.DonationSpinner);
        List<Donation> d = InfoDump.DONATIONS_MAP.map.get(InfoDump.current);
        if (d.isEmpty()) {
            ArrayList<String> arr = new ArrayList<>();
            arr.add("No donations available.");
            ArrayAdapter<String> adapter = new
                    ArrayAdapter(this,android.R.layout.simple_spinner_item, arr);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            donationSpinner.setAdapter(adapter);
        } else {
            ArrayAdapter<String> adapter = new
                    ArrayAdapter(this,android.R.layout.simple_spinner_item,
                    InfoDump.DONATIONS_MAP.map.get(InfoDump.current));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            donationSpinner.setAdapter(adapter);
        }
    }

    /**
     * Goes to DonationInfoPage if there are donations
     * @param view current view
     */
    public void OnClickConfirm(View view) {
        Object o = donationSpinner.getSelectedItem();
        String donation =  o.toString();

        if (!("No donations available.".equals(donation))) {
            InfoDump.currentDonation = donation;
            Intent intent = new Intent(this, DonationInfoPageActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Goes to add donation page.
     * @param view current view
     */
    public void OnClickAdd(View view) {
        Intent intent = new Intent(this, AddDonationPageActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Goes back to previous page
     * @param view current view
     */
    public void onClickCancel(View view) {
        finish();
    }

}
