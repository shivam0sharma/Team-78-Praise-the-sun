package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.data.InfoDump;

/**
 * Location Information Screen
 */
public class LocationInformationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_information);

        String database = InfoDump.current;
        Charity charity = new Charity();
        for (Charity ch : InfoDump.ITEMS) {
            String n = ch.getName();
            if (n.equals(database)) {
                charity = ch;
            }
        }

        TextView tv1 = findViewById(R.id.name);
        tv1.setText(charity.getName());
        TextView tv2 = findViewById(R.id.type);
        CharityType cT = charity.getType();
        tv2.setText(CharityType.getName(cT));
        TextView tv3 = findViewById(R.id.latitude);
        String lat = charity.getLatitude() + "";
        tv3.setText(lat);
        TextView tv4 = findViewById(R.id.longitude);
        String lng = charity.getLongitude() + "";
        tv4.setText(lng);
        TextView tv5 = findViewById(R.id.address);
        tv5.setText(charity.getStreetAddress());
        TextView tv6 = findViewById(R.id.phonenumber);
        tv6.setText(charity.getPhoneNumber());
    }

    /**
     * Goes back to previous screen.
     * @param view current view
     */
    public void onClickBack(View view) {
        finish();
    }

    /**
     * Goes to donation screen.
     * @param view current view
     */
    public void onClickDonation(View view) {
        Intent intent = new Intent(this, DonationPageActivity.class);
        startActivity(intent);
    }
}
