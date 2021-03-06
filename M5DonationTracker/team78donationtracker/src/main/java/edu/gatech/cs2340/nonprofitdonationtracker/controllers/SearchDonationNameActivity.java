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
 * Search donation name screen.
 */
public class SearchDonationNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_donation_name_page);
        View recyclerView = findViewById(R.id.donation_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleCourseRecyclerViewAdapter(InfoDump.donations));
    }

    public class SimpleCourseRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleCourseRecyclerViewAdapter.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private final List<Donation> mDonations;

        /**
         * set the items to be used by the adapter
         * @param items the list of items to be displayed in the recycler view
         */
        SimpleCourseRecyclerViewAdapter(List<Donation> items) {
            mDonations = items;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            /*

              This sets up the view for each individual item in the recycler display
              To edit the actual layout, we would look at: res/layout/course_list_content.xml
              If you look at the example file, you will see it currently just 2 TextView elements
             */
            LayoutInflater lI = LayoutInflater.from(parent.getContext());
            View view = lI.inflate(R.layout.donation_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

            /*
            This is where we have to bind each data element in the list
            (given by position parameter) to an element in the view (which
            is one of our two TextView widgets
             */
            //start by getting the element at the correct position
            holder.mDonation = mDonations.get(position);
            /*
              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
              textview and the string rep of a course in the other.
             */
            Donation d = mDonations.get(position);
            holder.mDonationView.setText(d.getName());

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //on a phone, we need to change windows to the detail view
                    Context context = v.getContext();
                    //create our new intent with the new screen (activity)
                    Intent intent = new Intent(context, DonationInfoPageActivity.class);
                    /*
                        pass along the id of the course so we can retrieve the correct data in
                        the next window
                     */
                    InfoDump.currentDonation = holder.mDonation.getName();
                    //now just display the new window
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDonations.size();
        }

        /**
         * This inner class represents a ViewHolder which provides us a way to cache information
         * about the binding between the model element (in this case a Course) and the widgets in
         * the list view (in this case the two TextView)
         */

        public class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mDonationView;
            Donation mDonation;

            /**
             * ViewHolder constructor.
             * @param view current view
             */
            ViewHolder(View view) {
                super(view);
                mView = view;
                mDonationView = view.findViewById(R.id.donation);
            }

            @NonNull
            @Override
            public String toString() {
                return super.toString() + " '" + mDonationView.getText() + "'";
            }
        }
    }

    /**
     * Goes back to previous screen.
     * @param view current view
     */
    public void onClickCancel(View view) {
        finish();
    }
}
