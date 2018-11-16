package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.data.DonationMap;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.data.InfoDump;

/**
 * Home page after logging in.
 */
public class HomePageActivity extends AppCompatActivity {

    boolean loadedDonation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //get donations saved from cache
        try {
            File directory = new File(getCacheDir(), "Donation.ser");
            FileInputStream fileIn = new FileInputStream(directory);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            InfoDump.DONATIONS_MAP = new
                    DonationMap((HashMap<String, ArrayList<Donation>>)in.readObject());
            in.close();
            fileIn.close();
            loadedDonation = true;
        } catch (IOException i) {
            System.out.println("No saved donations.");
        } catch (ClassNotFoundException c) {
            System.out.println("Donation class not found");
            c.printStackTrace();
        }

        HomePageTask hPT = new HomePageActivity.HomePageTask();
        hPT.execute();

    }

    class HomePageTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            System.out.println("Doing in background...");
            try{

                URL p = new URL("http://75.15.180.181/getData.php");
                URLConnection pp = p.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(pp.getInputStream()));
                String response = "";
                String inputLine = in.readLine();
                while ((inputLine) != null) {
                    response += inputLine;
                    inputLine = in.readLine();
                }
                in.close();

                JSONObject locationFile = new JSONObject(response);
                JSONArray locationArray = locationFile.getJSONArray("result");
                JSONObject current;
                Collection<Charity> charities = new ArrayList<>();
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
                InfoDump.setup(charities);


                URL p2 = new URL("http://75.15.180.181/getDonations.php");
                URLConnection pp2 = p2.openConnection();
                BufferedReader in2 = new BufferedReader(new InputStreamReader(pp2.getInputStream()));
                String response2 = "";
                String inputLine2;
                while ((inputLine2 = in2.readLine()) != null) {
                    response2 += inputLine2;
                }
                in.close();

                JSONObject donationFile = new JSONObject(response2);
                System.out.println("response2 is real" + response2);
                JSONArray donationArray = donationFile.getJSONArray("result");
                System.out.println("donationArray is real" + donationArray);

                JSONObject current2 = null;

                System.out.println("The array length is " + donationArray.length());
                Donation don;
                for (int i = 0; i < donationArray.length(); i++) {
                    current2 = donationArray.getJSONObject(i);
                    don = new Donation(current2.getString("donationName"),current2.getString("timeStamp"),current2.getString("location"),current2.getString("shortDescription"),current2.getString("longDescription"),current2.getDouble("donationValue"));
                    don.setCategory( Category.valueOf(current2.getString("category")));
                    System.out.println("the donation is " + don);
                    InfoDump.DONATIONS_MAP.map.get(InfoDump.current).add(don);

                }
                if (loadedDonation) {
                    InfoDump.setup(charities);
                } else {
                    InfoDump.setUpEverything(charities);
                }


                return "yes" ;

            } catch(Exception e){
                System.out.println(e.getMessage());
                return "Exception: " + e.getMessage();
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

    /**
     * Goes to google map screen.
     * @param view current view
     */
    public void onClickMap(View view) {
        Intent intent = new Intent(this, GoogleMapActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to search donation screen.
     * @param view current view
     */
    public void onClickSearchDonations(View view) {
        Intent intent = new Intent(this, SelectScopeActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to locations screen.
     * @param view current view
     */
    public void onClickLocations(View view) {
        Intent intent = new Intent(this, SelectLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Logs out and goes back to opening screen.
     * @param view current view
     */
    public void onClickLogout(View view) {
        Intent intent = new Intent(this, OpeningScreen.class);
        startActivity(intent);
        finish();
    }
}
