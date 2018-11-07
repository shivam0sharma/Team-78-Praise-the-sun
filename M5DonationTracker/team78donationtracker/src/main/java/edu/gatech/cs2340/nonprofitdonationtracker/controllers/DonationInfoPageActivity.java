package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.dummy.DummyContent;

public class DonationInfoPageActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_info_page);

        Donation donation = new Donation();
        List<Donation> list = DummyContent.DONATIONS_MAP.map.get(Database.current);
        for (Donation don : list) {
            if (don.getName().equals(Database.currentDonation)) {
                donation = don;
            }
        }
        TextView tv1 = (TextView)findViewById(R.id.nameText);
        tv1.setText(donation.getName());
        TextView tv2 = (TextView)findViewById(R.id.timeStampText);
        tv2.setText(donation.getTimeStamp());
        TextView tv3 = (TextView)findViewById(R.id.shortDescriptionText);
        tv3.setText(donation.getShortDescription());
        TextView tv4 = (TextView)findViewById(R.id.fullDescriptionText);
        tv4.setText(donation.getFullDescription());
        TextView tv5 = (TextView)findViewById(R.id.valueText);
        tv5.setText(Double.toString(donation.getValue()));
        TextView tv6 = (TextView)findViewById(R.id.categoryText);
        tv6.setText(Category.getCategory(donation.getCategory()));
        TextView tv7 = (TextView) findViewById(R.id.locationText);
        tv7.setText(Database.current);

    }

    public void onClickBack(View view) {
        finish();
    }

}
