package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.dummy.DummyContent;

public class LocationsActivity extends AppCompatActivity {

    private Spinner locationSpinner;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        locationSpinner = (Spinner) findViewById(R.id.LocationSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, DummyContent.ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);
    }

    public void onClickConfirm(View view) {
        String location =  locationSpinner.getSelectedItem().toString();
        System.out.println(location);
        Database.current = location;

        Intent intent = new Intent(this, LocationInformationActivity.class);
        startActivity(intent);
    }

    public void onClickCancel(View view) {
        finish();
    }
}
