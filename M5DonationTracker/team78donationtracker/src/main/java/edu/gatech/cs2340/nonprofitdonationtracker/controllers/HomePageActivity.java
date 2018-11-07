package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.dummy.DonationMap;
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
        try {
            File directory = new File(getCacheDir(), "Donation.ser");
            FileInputStream fileIn = new FileInputStream(directory);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            DummyContent.DONATIONS_MAP = new DonationMap((HashMap<String, ArrayList<Donation>>)in.readObject());
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Donation class not found");
            c.printStackTrace();
            return;
        }
    }

    public void onClickSearchDonations(View view) {
        Intent intent = new Intent(this, SelectScopeActivity.class);
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
