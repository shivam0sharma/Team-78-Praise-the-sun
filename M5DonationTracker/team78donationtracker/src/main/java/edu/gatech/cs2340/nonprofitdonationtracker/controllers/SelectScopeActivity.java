package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.data.InfoDump;

/**
 * Select scope page
 */
public class SelectScopeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_scope);
    }

    /**
     * Goes to select single location page
     * @param view current view
     */
    public void onClickSingle(View view) {
        InfoDump.scope = "Single";
        Intent intent = new Intent(this, SelectSingleLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to search donation page
     * @param view current view
     */
    public void onClickAll(View view) {
        InfoDump.scope = "All";
        Intent intent = new Intent(this, SearchDonationActivity.class);
        startActivity(intent);
    }

    /**
     * Goes back to previous page
     * @param view current view
     */
    public void onClickCancel(View view) {
        finish();
    }

}
