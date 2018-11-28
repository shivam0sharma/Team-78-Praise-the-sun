package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharityTypeTest {

    @Test
    public void getName() {
        CharSequence type = CharityType.getName(CharityType.DROP_OFF);
        assertTrue("Returned string: " + type, type.equals("Drop Off"));

        type = CharityType.getName(CharityType.STORE);
        assertTrue("Returned string: " + type, type.equals("Store"));

        type = CharityType.getName(CharityType.WAREHOUSE);
        assertTrue("Returned string: " + type, type.equals("Warehouse"));
    }
}