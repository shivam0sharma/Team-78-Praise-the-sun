package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.data.InfoDump;

/**
 * Donation information page
 */
public class DonationInfoPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_info_page);

        Donation donation = new Donation();
        List<Donation> list = InfoDump.DONATIONS_MAP.map.get(InfoDump.current);
        for (Donation don : list) {
            String n = don.getName();
            if (n.equals(InfoDump.currentDonation)) {
                donation = don;
            }
        }
        TextView tv1 = findViewById(R.id.nameText);
        tv1.setText(donation.getName());
        TextView tv2 = findViewById(R.id.timeStampText);
        tv2.setText(donation.getTimeStamp());
        TextView tv3 = findViewById(R.id.shortDescriptionText);
        tv3.setText(donation.getShortDescription());
        TextView tv4 = findViewById(R.id.fullDescriptionText);
        tv4.setText(donation.getFullDescription());
        TextView tv5 = findViewById(R.id.valueText);
        String v = donation.getValue() + "";
        tv5.setText(v);
        TextView tv6 = findViewById(R.id.categoryText);
        tv6.setText(Category.getCategory(donation.getCategory()));
        TextView tv7 = findViewById(R.id.locationText);
        tv7.setText(InfoDump.current);

    }

    /**
     * Goes back to previous screen.
     * @param view current view
     */
    public void onClickBack(View view) {
        finish();
    }

}
