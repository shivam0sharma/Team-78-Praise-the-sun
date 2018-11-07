package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

        Donation don = new Donation(name_value, time_value, location_value, short_value, full_value, value_value);
        don.setCategory(cat);
        DummyContent.DONATIONS_MAP.map.get(Database.current).add(don);

        try {
            File directory = new File(getCacheDir(), "Donation.ser");
            FileOutputStream fileOut = new FileOutputStream(directory);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(DummyContent.DONATIONS_MAP.map);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in donations.ser");
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
}
