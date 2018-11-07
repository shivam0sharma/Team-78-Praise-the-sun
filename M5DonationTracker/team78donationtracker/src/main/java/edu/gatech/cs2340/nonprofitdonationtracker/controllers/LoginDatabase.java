
package edu.gatech.cs2340.nonprofitdonationtracker.controllers;
import java.util.HashMap;

public class LoginDatabase {

    public static HashMap<String, String> login;

    public void add(String username, String password) {
        login.put(username, password);
    }
}