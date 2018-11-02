package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class Location {
    private static JSONObject locationFile;

    public Location(URL url) {
        try {
            URLConnection conn = url.openConnection();
            System.out.println("The url is: " + url);
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }
            String response = sb.toString();
            locationFile = new JSONObject(response);
        } catch (Exception e) {
            System.out.println("BIG IO ERROR");
        }
    }

    public Location() {
        String response = "{\"result\":[{\"Name\":\"AFD Station 4\",\"Latitude\":\"33.75416\",\"Longitude\":\"-84.37742\",\"Type\":\"Drop Off\",\"Phone\":\"(404) 555 - 3456\",\"Address\":\"309 EDGEWOOD AVE SE\"},{\"Name\":\"BOYS & GILRS CLUB W.W. WOOLFOLK\",\"Latitude\":\"33.73182\",\"Longitude\":\"-84.43971\",\"Type\":\"Store\",\"Phone\":\"(404) 555 - 1234\",\"Address\":\"1642 RICHLAND RD\"},{\"Name\":\"PATHWAY UPPER ROOM CHRISTIAN MINISTRIES\",\"Latitude\":\"33.70866\",\"Longitude\":\"-84.41853\",\"Type\":\"Warehouse\",\"Phone\":\"(404) 555 - 5432\",\"Address\":\"1683 SYLVAN RD\"},{\"Name\":\"PAVILION OF HOPE INC\",\"Latitude\":\"33.80129\",\"Longitude\":\"-84.25537\",\"Type\":\"Warehouse\",\"Phone\":\"(404) 555 - 8765\",\"Address\":\"3558 EAST PONCE DE LEON AVE\"},{\"Name\":\"D&D CONVENIENCE STORE\",\"Latitude\":\"33.71747\",\"Longitude\":\"-84.2521\",\"Type\":\"Drop Off\",\"Phone\":\"(404) 555 - 9876\",\"Address\":\"2426 COLUMBIA DRIVE\"},{\"Name\":\"KEEP NORTH FULTON BEAUTIFUL\",\"Latitude\":\"33.96921\",\"Longitude\":\"-84.3688\",\"Type\":\"Store\",\"Phone\":\"(770) 555 - 7321\",\"Address\":\"470 MORGAN FALLS RD\"}]}";
        try {
            locationFile = new JSONObject(response);
        } catch (Exception e) {
            System.out.println("The json failed to be created");
        }
    }

    public static String[] getData() {
        String[] data = new String[6];
        try {
            JSONArray locationArray = locationFile.getJSONArray("result");
            JSONObject current = null;
            for (int i = 0; i < 4; i++) {
                if (locationArray.getJSONObject(i).getString("Name").equalsIgnoreCase(Database.current)) {
                    current = locationArray.getJSONObject(i);
                }
            }
            data[0] = current.getString("Name");
            data[1] = current.getString("Latitude");
            data[2] = current.getString("Longitude");
            data[3] = current.getString("Type");
            data[4] = current.getString("Address");
            data[5] = current.getString("Phone");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return data;
    }
}
