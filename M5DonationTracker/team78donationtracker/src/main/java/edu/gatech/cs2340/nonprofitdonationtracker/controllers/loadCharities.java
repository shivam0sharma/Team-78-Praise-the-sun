package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class loadCharities {

    private ArrayList<Charity> charities = new ArrayList<Charity>();

    public loadCharities(InputStream inputStream) {

        CSVFile csvFile = new CSVFile(inputStream);
        List charityDataObjects = csvFile.read();
        charityDataObjects.remove(0);

        for (Object charityDataObject : charityDataObjects) {
            String[] charityData = (String[]) charityDataObject;

            CharityType type = CharityType.charityType(charityData[8]);

            Charity charity = new Charity(charityData[0],
                    charityData[1],
                    Double.parseDouble(charityData[2]),
                    Double.parseDouble(charityData[3]),
                    charityData[4],
                    charityData[5],
                    charityData[6],
                    Integer.parseInt(charityData[7]),
                    type,
                    charityData[9],
                    charityData[10]);
            charities.add(charity);
        }

    }

    public ArrayList<Charity> getCharities() {
        return charities;
    }

}