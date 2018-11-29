package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

/**
 * Search donation page
 */
public class SearchDonationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_donation);
    }

    /**
     * Goes to enter name page
     * @param view current view
     */
    public void onClickName(View view) {
        Intent intent = new Intent(this, EnterNameActivity.class);
        startActivity(intent);
    }

    /**
     * Goes back to previous page
     * @param view current view
     */
    public void onClickCancel(View view) {
        finish();
    }

    /**
     * Goes to select category page
     * @param view view
     */
    public void onClickCategory(View view) {
        Intent intent = new Intent(this, SelectCategoryActivity.class);
        startActivity(intent);
    }
}
