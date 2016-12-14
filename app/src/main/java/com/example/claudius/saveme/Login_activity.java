package com.example.claudius.saveme;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_activity extends AppCompatActivity {

    int password = 0;
    String username = null;
    int musterpasswort = 1234;
    String musterUser = "John Wall";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void passwortfertig(View view){


        EditText numpadpasswort  = (EditText) findViewById(R.id.passworteingabefeld);
        EditText nutzernameeingabefeld = (EditText) findViewById(R.id.nameeingabefeld);

        username = nutzernameeingabefeld.getText().toString();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(Login_activity.this, "Nutzername fehlt!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(Login_activity.this, username, Toast.LENGTH_SHORT).show();
        }

        int passwort = Integer.parseInt(numpadpasswort.getText().toString());

        if (passwort == 0){
            Toast.makeText(Login_activity.this, "Bitte gib dein Passwort ein!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(Login_activity.this, "Passwort erfolgreich verarbeitet", Toast.LENGTH_SHORT).show();
        }

        if(username.equals(musterUser) && passwort == musterpasswort){
            Toast.makeText(Login_activity.this, "Anmeldung erfolgreich", Toast.LENGTH_SHORT).show();
        }

    }

}
