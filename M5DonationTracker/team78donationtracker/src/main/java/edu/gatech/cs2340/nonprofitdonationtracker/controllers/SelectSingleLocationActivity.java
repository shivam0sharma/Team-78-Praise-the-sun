package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.data.InfoDump;

/**
 * Select single location page
 */
public class SelectSingleLocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_single_location);
        View recyclerView = findViewById(R.id.location_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SelectSingleLocationActivity.
                SimpleCourseRecyclerViewAdapter());
    }

    public class SimpleCourseRecyclerViewAdapter
            extends RecyclerView.Adapter<SelectSingleLocationActivity.
            SimpleCourseRecyclerViewAdapter.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private final List<Charity> mLocations;

        /**
         * set the items to be used by the adapter
         */
        SimpleCourseRecyclerViewAdapter() {
            mLocations = InfoDump.ITEMS;
        }

        @NonNull
        @Override
        public SelectSingleLocationActivity.SimpleCourseRecyclerViewAdapter.
                ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            /*

              This sets up the view for each individual item in the recycler display
              To edit the actual layout, we would look at: res/layout/course_list_content.xml
              If you look at the example file, you will see it currently just 2 TextView elements
             */
            LayoutInflater lI = LayoutInflater.from(parent.getContext());
            View view = lI.inflate(R.layout.location_list_content, parent, false);
            return new SelectSingleLocationActivity.SimpleCourseRecyclerViewAdapter.
                    ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final SelectSingleLocationActivity.
                SimpleCourseRecyclerViewAdapter.ViewHolder holder, int position) {

            /*
            This is where we have to bind each data element in the list
            (given by position parameter) to an element in the view (which is one
             of our two TextView widgets
             */
            //start by getting the element at the correct position
            holder.mLocation = mLocations.get(position);
            /*
              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
              textview and the string rep of a course in the other.
             */
            Charity c = mLocations.get(position);
            holder.mLocationView.setText(c.getName());

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //on a phone, we need to change windows to the detail view
                    Context context = v.getContext();
                    //create our new intent with the new screen (activity)
                    Intent intent = new Intent(context, SearchDonationActivity.class);
                    /*
                        pass along the id of the course so we can retrieve the correct data in
                        the next window
                     */
                    InfoDump.donations = InfoDump.DONATIONS_MAP.map.
                            get(holder.mLocation.getName());
                    //now just display the new window
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mLocations.size();
        }

        /**
         * This inner class represents a ViewHolder which provides us a way to cache information
         * about the binding between the model element (in this case a Course) and the widgets in
         * the list view (in this case the two TextView)
         */

        public class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mLocationView;
            Charity mLocation;

            /**
             * ViewHolder constructor
             * @param view current view
             */
            ViewHolder(View view) {
                super(view);
                mView = view;
                mLocationView = view.findViewById(R.id.location);
            }

            @NonNull
            @Override
            public String toString() {
                return super.toString() + " '" + mLocationView.getText() + "'";
            }
        }
    }

    /**
     * Goes back to previous page
     * @param view current view
     */
    public void onClickCancel(View view) {
        finish();
    }
}
