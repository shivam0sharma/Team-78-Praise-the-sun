package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


import edu.gatech.cs2340.nonprofitdonationtracker.R;

/**
 * Registration screen.
 */
public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //populating spinner with values from enum
        Spinner userType = findViewById(R.id.user_type_spinner_id);
        userType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                UserType.values()));
    }


    /**
     * Goes back to opening screen.
     * @param view current view
     */
    public void onClickCancel(View view) {
        Intent intent = new Intent(this, OpeningScreen.class);
        startActivity(intent);
        finish();
    }

    /**
     * Registers user.
     * @param view current view
     */
    public void onClickRegister(View view) {
        //getting user entered values from the edit text entry boxes
        EditText name = findViewById(R.id.name_edit_text_id);
        Editable name_value = name.getText();
        EditText email = findViewById(R.id.email_edit_text_id);
        Editable email_value = email.getText();
        EditText password = findViewById(R.id.password_edit_text_id);
        Editable password_value = password.getText();
        //get user type
        Spinner userType = findViewById(R.id.user_type_spinner_id);
        Object uT = userType.getSelectedItem();
        String selected_user_type = uT.toString();
        UserRegistrationTask uRT= new UserRegistrationTask(name_value.toString(),
                email_value.toString(), password_value.toString(), selected_user_type);
        uRT.execute(name_value.toString(), email_value.toString(), password_value.toString(),
                selected_user_type);
    }

    class UserRegistrationTask extends AsyncTask<String, Void, String> {

        private final String name;
        private final String username;
        private final String password;
        private final String userType;

        UserRegistrationTask(String name, String username, String password, String userType) {
            this.name = name;
            this.username = username;
            this.password = password;
            this.userType = userType;
        }

        @Override
        protected String doInBackground(String... params) {

            try{
                String link="http://75.15.180.181/createUser.php?";
                String data  = "name=" + name;
                data+= "&password=" + password;
                data+= "&userType=" + userType;
                data+= "&username=" + username;
                link = link + data;

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                sb.append(line);
                return "1";
            } catch(Exception e){
                System.out.println("Exception: " + e);
                return "0";
            }
            /*for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }
*/

        }

        @Override
        protected void onPostExecute(String success) {

            if ("1".equals(success)) {
                finish();
            }
        }

        @Override
        protected void onCancelled() {}
    }
}
