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
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
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

        Donation don = new Donation(name_value, time_value, location_value, short_value, full_value,
                value_value);
        don.setCategory(cat);
        List<Donation> d = InfoDump.DONATIONS_MAP.map.get(InfoDump.current);
        d.add(don);

        try {
            File directory = new File(getCacheDir(), "Donation.ser");
            FileOutputStream fileOut = new FileOutputStream(directory);
            ObjectOutput out = new ObjectOutputStream(fileOut);
            out.writeObject(InfoDump.DONATIONS_MAP.map);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in donations.ser");
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
}
