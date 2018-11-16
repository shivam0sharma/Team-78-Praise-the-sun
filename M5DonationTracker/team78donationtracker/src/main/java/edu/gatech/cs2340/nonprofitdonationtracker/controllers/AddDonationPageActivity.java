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
import java.util.ArrayList;
import android.content.Intent;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.dummy.DummyContent;

public class AddDonationPageActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation_page);

        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Category.list());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        TextView tv = (TextView)findViewById(R.id.textView20);
        tv.setText(Database.current);
    }

    public void onClickAddNew(View view) {
        EditText name = (EditText)findViewById(R.id.itemNameText);
        String name_value = name.getText().toString();
        EditText timeStamp = (EditText)findViewById(R.id.timeStampText);
        String time_value = timeStamp.getText().toString();
        String location_value = Database.current;
        EditText shortDescription = (EditText)findViewById(R.id.shortDescriptionText);
        String short_value = shortDescription.getText().toString();
        EditText fullDescription = (EditText)findViewById(R.id.fullDescriptionText);
        String full_value = fullDescription.getText().toString();
        EditText value = (EditText)findViewById(R.id.valueText);
        Double value_value = Double.parseDouble(value.getText().toString());
        String category =  categorySpinner.getSelectedItem().toString();
        Category cat = Category.category(category);

        System.out.println("why am I here");
        Donation don = new Donation(name_value, time_value, location_value, short_value, full_value, value_value);
        don.setCategory(cat);
        DummyContent.DONATIONS_MAP.map.get(Database.current).add(don);
        System.out.println(DummyContent.DONATIONS_MAP.map.get(Database.current));

        new AddDonationPageActivity.DonationTask(name_value.toString(), time_value.toString(), location_value.toString(), short_value.toString(), full_value.toString(), value_value.toString(), category).execute(name_value.toString(), time_value.toString(), location_value.toString(), short_value.toString(), full_value.toString(), value_value.toString(), category);

        try {
            File directory = new File(getCacheDir(), "Donation.ser");
            FileOutputStream fileOut = new FileOutputStream(directory);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(DummyContent.DONATIONS_MAP.map);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in donations.ser" + DummyContent.DONATIONS_MAP.map.toString());
        } catch (IOException i) {
            i.printStackTrace();
        }
        Intent intent = new Intent(this, DonationPageActivity.class);
        startActivity(intent);
        finish();
    }

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
