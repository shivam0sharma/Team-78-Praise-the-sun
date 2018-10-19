package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class LocationInformationActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_information);
        TextView tv1 = (TextView)findViewById(R.id.name);
        tv1.setText(Database.current);
    }

    public void onClickBack(View view) {
        finish();
    }
}
