package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class SearchDonationActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_donation);
    }

    public void onClickName(View view) {
        Intent intent = new Intent(this, EnterNameActivity.class);
        startActivity(intent);
    }

    public void onClickCancel(View view) {
        finish();
    }

    public void onClickCategory(View view) {
        Intent intent = new Intent(this, SelectCategoryActivity.class);
        startActivity(intent);
    }
}
