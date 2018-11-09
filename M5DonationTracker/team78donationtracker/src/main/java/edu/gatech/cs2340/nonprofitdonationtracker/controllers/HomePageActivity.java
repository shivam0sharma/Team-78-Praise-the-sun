package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.URL;
import java.net.URLConnection;
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

        try {
            System.out.println("reached");
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

        new HomePageActivity.HomePageTask().execute();
        //get donations saved from cache

    }

    public class HomePageTask extends AsyncTask<String, Void, String> {

        HomePageTask () {
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO: attempt authentication against a network service.
            System.out.println("Doing in background...");
            try{

                URL p = new URL("http://75.15.180.181/getData.php");
                URLConnection pp = p.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(pp.getInputStream()));
                String response = "";
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response += inputLine;
                }
                in.close();

                JSONObject locationFile = new JSONObject(response);
                JSONArray locationArray = locationFile.getJSONArray("result");
                JSONObject current = null;
                ArrayList<Charity> charities = new ArrayList<Charity>();
                for (int i = 0; i < locationArray.length(); i++) {
                    Charity newCharity = new Charity();
                    current = locationArray.getJSONObject(i);
                    String name = current.getString("Name");
                    newCharity.setName(name);
                    String latitude = current.getString("Latitude");
                    newCharity.setLatitude(Double.parseDouble(latitude));
                    String longitude = current.getString("Longitude");
                    newCharity.setLongitude(Double.parseDouble(longitude));
                    String type = current.getString("Type");
                    newCharity.setType(CharityType.charityType(type));
                    String address = current.getString("Address");
                    newCharity.setStreetAddress(address);
                    String phone = current.getString("Phone");
                    newCharity.setPhoneNumber(phone);
                    charities.add(newCharity);
                }
                DummyContent.setup(charities);
                return "yes" ;

            } catch(Exception e){
                System.out.println(e.getMessage());
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String success) {
            System.out.println(success);
        }

        @Override
        protected void onCancelled() {
        }
    }

    public void onClickMap(View view) {
        Intent intent = new Intent(this, GoogleMapActivity.class);
        System.out.println("asdf");
        startActivity(intent);
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
