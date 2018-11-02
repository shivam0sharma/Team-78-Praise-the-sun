package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class SelectScopeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_scope);
    }

    public void onClickSingle(View view) {
        Database.scope = "Single";
        Intent intent = new Intent(this, SelectSingleLocationActivity.class);
        startActivity(intent);
    }

    public void onClickAll(View view) {
        Database.scope = "All";
        Intent intent = new Intent(this, SearchDonationActivity.class);
        startActivity(intent);
    }

    public void onClickCancel(View view) {
        finish();
    }

}
