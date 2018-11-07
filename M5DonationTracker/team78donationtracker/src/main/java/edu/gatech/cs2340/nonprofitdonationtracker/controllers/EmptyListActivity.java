package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class EmptyListActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_list);
    }

    public void onClickBack(View view) {
        finish();
    }
}
