package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


import java.io.ObjectOutput;

import java.util.List;


import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.data.InfoDump;

/**
 * Add donation page.
 */
public class AddDonationPageActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation_page);

        categorySpinner = findViewById(R.id.categorySpinner);

        ArrayAdapter<String> adapter = new
                ArrayAdapter(this,android.R.layout.simple_spinner_item, Category.list());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        TextView tv = findViewById(R.id.textView20);
        tv.setText(InfoDump.current);
    }

    /**
     * Adds new donation.
     * @param view current view
     */
    public void onClickAddNew(View view) {
        EditText name = findViewById(R.id.itemNameText);
        CharSequence n = name.getText();
        String name_value = n.toString();
        EditText timeStamp = findViewById(R.id.timeStampText);
        CharSequence tV = timeStamp.getText();
        String time_value = tV.toString();
        String location_value = InfoDump.current;
        EditText shortDescription = findViewById(R.id.shortDescriptionText);
        CharSequence sD = shortDescription.getText();
        String short_value = sD.toString();
        EditText fullDescription = findViewById(R.id.fullDescriptionText);
        CharSequence fD = fullDescription.getText();
        String full_value = fD.toString();
        EditText value = findViewById(R.id.valueText);
        CharSequence v = value.getText();
        Double value_value = Double.parseDouble(v.toString());
        Object c = categorySpinner.getSelectedItem();
        String category =  c.toString();
        Category cat = Category.category(category);

        Donation don = new Donation(name_value, time_value, location_value, short_value, full_value, value_value);
        don.setCategory(cat);
        List<Donation> d = InfoDump.DONATIONS_MAP.map.get(InfoDump.current);
        d.add(don);

        new AddDonationPageActivity.DonationTask(name_value.toString(), time_value.toString(), location_value.toString(), short_value.toString(), full_value.toString(), value_value.toString(), category).execute(name_value.toString(), time_value.toString(), location_value.toString(), short_value.toString(), full_value.toString(), value_value.toString(), category);

        try {
            File directory = new File(getCacheDir(), "Donation.ser");
            FileOutputStream fileOut = new FileOutputStream(directory);
            ObjectOutput out = new ObjectOutputStream(fileOut);
            out.writeObject(InfoDump.DONATIONS_MAP.map);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in donations.ser" + InfoDump.DONATIONS_MAP.map.toString());
        } catch (IOException i) {
            i.printStackTrace();
        }
        Intent intent = new Intent(this, DonationPageActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Goes back to donation page.
     * @param view current view
     */
    public void onClickCancel(View view) {
        Intent intent = new Intent(this, DonationPageActivity.class);
        startActivity(intent);
        finish();
    }


    public class DonationTask extends AsyncTask<String, Void, String> {

        private final String donationName;
        private final String timeStamp;
        private final String location;
        private final String shortDescription;
        private final String longDescription;
        private final String donationValue;
        private final String category;

        DonationTask(String donationName, String timeStamp, String location, String shortDescription, String longDescription, String donationValue, String category) {
            this.donationName = donationName;
            this.timeStamp = timeStamp;
            this.donationValue = donationValue;
            this.location = location;
            this.shortDescription = shortDescription;
            this.longDescription = longDescription;
            this.category = category;
        }
        @Override

        protected String doInBackground(String... params) {
            System.out.println("Getting in here");

            try {
                String link = "http://75.15.180.181/createDonation.php?";
                String data = "donationName=" + donationName;
                data += "&timeStamp=" + timeStamp;
                data += "&donationValue=" + donationValue;
                data += "&location=" + location;
                data += "&shortDescription=" + shortDescription;
                data += "&longDescription=" + longDescription;
                data += "&category=" + category;
                link = link + data;

                System.out.println("Link is now " + link);

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                return "1";
            } catch (Exception e) {
                System.out.println("Exception: " + e);
                return "0";
            }
        }
    }

}
