package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import java.io.InputStream;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.dummy.DummyContent;

public class LocationInformationActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_information);

        String database = Database.current;
        Charity charity = new Charity();
        for (DummyContent.DummyItem dum : DummyContent.ITEMS) {
            Charity ch = dum.charity;
            if (ch.getName().equals(database)) {
                charity = ch;
            }
        }

        TextView tv1 = (TextView)findViewById(R.id.name);
        tv1.setText(charity.getName());
        TextView tv2 = (TextView)findViewById(R.id.type);
        tv2.setText(charity.getType().getName());
        TextView tv3 = (TextView)findViewById(R.id.latitude);
        tv3.setText(Double.toString(charity.getLatitude()));
        TextView tv4 = (TextView)findViewById(R.id.longitude);
        tv4.setText(Double.toString(charity.getLongitude()));
        TextView tv5 = (TextView)findViewById(R.id.address);
        tv5.setText(charity.getStreetAddress());
        TextView tv6 = (TextView)findViewById(R.id.phonenumber);
        tv6.setText(charity.getPhoneNumber());

    }

    public void onClickBack(View view) {
        finish();
    }

    public void onClickDonation(View view) {
        Intent intent = new Intent(this, DonationPageActivity.class);
        startActivity(intent);
    }
}
