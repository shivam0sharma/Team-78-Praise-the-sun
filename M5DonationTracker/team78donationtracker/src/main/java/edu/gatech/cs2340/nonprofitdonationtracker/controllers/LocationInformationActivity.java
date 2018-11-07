package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class LocationInformationActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_information);
        new LocationInformationActivity.LocationInformationTask().execute();

      /*  String database = Database.current;
        Charity charity = new Charity();
        for (DummyContent.DummyItem dum : DummyContent.ITEMS) {
            Charity ch = dum.charity;
            if (ch.getName().equals(database)) {
                charity = ch;
            }
        }

        TextView tv1 = (TextView)findViewById(R.id.name);
        tv1.setText(charity.getName());
        TextView tv2 = (TextView)findViewById(R.id.type);
        tv2.setText(charity.getType().getName());
        TextView tv3 = (TextView)findViewById(R.id.latitude);
        tv3.setText(Double.toString(charity.getLatitude()));
        TextView tv4 = (TextView)findViewById(R.id.longitude);
        tv4.setText(Double.toString(charity.getLongitude()));
        TextView tv5 = (TextView)findViewById(R.id.address);
        tv5.setText(charity.getStreetAddress());
        TextView tv6 = (TextView)findViewById(R.id.phonenumber);
        tv6.setText(charity.getPhoneNumber());
*/
    }
    public class LocationInformationTask extends AsyncTask<String, Void, String> {

        LocationInformationTask () {
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO: attempt authentication against a network service.
            System.out.println("Doing in background...");
            try{
                String link="http://75.15.180.181/getData.php";

                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                System.out.println("The url is: " + url);
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
               String response = sb.toString();
               System.out.println("response is " + response);
               response  = "{\"result\":[{\"Name\":\"AFD Station 4\",\"Latitude\":\"33.75416\",\"Longitude\":\"-84.37742\",\"Type\":\"Drop Off\",\"Phone\":\"(404) 555 - 3456\",\"Address\":\"309 EDGEWOOD AVE SE\"},{\"Name\":\"BOYS & GILRS CLUB W.W. WOOLFOLK\",\"Latitude\":\"33.73182\",\"Longitude\":\"-84.43971\",\"Type\":\"Store\",\"Phone\":\"(404) 555 - 1234\",\"Address\":\"1642 RICHLAND RD\"},{\"Name\":\"PATHWAY UPPER ROOM CHRISTIAN MINISTRIES\",\"Latitude\":\"33.70866\",\"Longitude\":\"-84.41853\",\"Type\":\"Warehouse\",\"Phone\":\"(404) 555 - 5432\",\"Address\":\"1683 SYLVAN RD\"},{\"Name\":\"PAVILION OF HOPE INC\",\"Latitude\":\"33.80129\",\"Longitude\":\"-84.25537\",\"Type\":\"Warehouse\",\"Phone\":\"(404) 555 - 8765\",\"Address\":\"3558 EAST PONCE DE LEON AVE\"},{\"Name\":\"D&D CONVENIENCE STORE\",\"Latitude\":\"33.71747\",\"Longitude\":\"-84.2521\",\"Type\":\"Drop Off\",\"Phone\":\"(404) 555 - 9876\",\"Address\":\"2426 COLUMBIA DRIVE\"},{\"Name\":\"KEEP NORTH FULTON BEAUTIFUL\",\"Latitude\":\"33.96921\",\"Longitude\":\"-84.3688\",\"Type\":\"Store\",\"Phone\":\"(770) 555 - 7321\",\"Address\":\"470 MORGAN FALLS RD\"}]}";
               JSONObject locationFile = new JSONObject(response);
               JSONArray locationArray = locationFile.getJSONArray("result");
               JSONObject current = null;
               for (int i = 0; i < 4; i++) {
                   if (locationArray.getJSONObject(i).getString("Name").equalsIgnoreCase(Database.current)) {
                       current = locationArray.getJSONObject(i);
                   }
               }
               System.out.println("JSON RECEIVED");
               String name = current.getString("Name");
               String latitude = current.getString("Latitude");
               String longitude = current.getString("Longitude");
               String type = current.getString("Type");
               String address = current.getString("Address");
               String phone = current.getString("Phone");

               TextView tv1 = (TextView)findViewById(R.id.name);
               tv1.setText(name);
               TextView tv2 = (TextView)findViewById(R.id.type);
               tv2.setText(type);
               TextView tv3 = (TextView)findViewById(R.id.latitude);
               tv3.setText(latitude);
               TextView tv4 = (TextView)findViewById(R.id.longitude);
               tv4.setText(longitude);
               TextView tv5 = (TextView)findViewById(R.id.address);
               tv5.setText(address);
               TextView tv6 = (TextView)findViewById(R.id.phonenumber);
               tv6.setText(phone);

               return "yes" ;

            } catch(Exception e){
                System.out.println(e.getMessage());
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String success) {
          //do something
            System.out.println(success);
        }

        @Override
        protected void onCancelled() {
           //do something
        }
    }
    public void onClickBack(View view) {
        finish();
    }

    public void onClickDonation(View view) {
        Intent intent = new Intent(this, DonationPageActivity.class);
        startActivity(intent);
    }
}
