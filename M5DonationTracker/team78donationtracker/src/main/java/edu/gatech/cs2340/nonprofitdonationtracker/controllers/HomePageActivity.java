package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.InputStream;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.dummy.DummyContent;

public class HomePageActivity extends AppCompatActivity {

    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        InputStream inputStream = getResources().openRawResource(R.raw.locationdata);
        loadCharities dataProvider = new loadCharities(inputStream);
        DummyContent.setup(dataProvider.getCharities());

    }

    public void onClickSearchDonations(View view) {
        Intent intent = new Intent(this, SearchDonationActivity.class);
        startActivity(intent);
    }
    public void onClickLocations(View view) {
        Intent intent = new Intent(this, SelectLocationActivity.class);
        startActivity(intent);
    }
    public void onClickLogout(View view) {
        Intent intent = new Intent(this, OpeningScreen.class);
        startActivity(intent);
        finish();
    }
}
