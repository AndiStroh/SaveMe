package com.example.claudius.saveme.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.claudius.saveme.Create_stuff.Create_Activity;
import com.example.claudius.saveme.R;
import com.example.claudius.saveme.Result_Stuff.New_Show_Activity;

import com.example.claudius.saveme.Storages.Girlfriend;
import com.example.claudius.saveme.Storages.MySQLiteHelper;

public class Login_activity extends AppCompatActivity {

    String username = null;
    MySQLiteHelper sqlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sqlhelper = new MySQLiteHelper(getBaseContext());

        //Wenn die getGirlfriend Methode null zurück gibt dann gibt es keine gespeicherte Freundin -> Es wird direkt die Create_Activity aufgerufen.
        //Login würde keinen Sinn machen da es nur ein Konto geben kann wenn es eine Freundin gibt.
        if (sqlhelper.getGirlfriend() == null) {
            Intent z = new Intent(this, Create_Activity.class);
            startActivity(z);
        }
    }

    /*Methode wird bei onClick der Passwort Tastatur aufgerufen und checkt ob der Benutzername und das Paswort korrekt sind (macht sie indem sie
        in der SQL Klasse eine Methode aufruft.)
        Wenn das der Fall ist, wird die Show Activity durch einen Intent dem keine weiteren
      Informationen mitgegeben werden aufgerufen.
     */

    public void passwortfertig(View view){
        EditText numpadpasswort  = (EditText) findViewById(R.id.passworteingabefeld);
        EditText nutzernameeingabefeld = (EditText) findViewById(R.id.nameeingabefeld);

        username = nutzernameeingabefeld.getText().toString();

        String password = numpadpasswort.getText().toString();

        sqlhelper = new MySQLiteHelper(getBaseContext());

        //wenn die getGirlfriend Methode null zurück gibt dann gibt es keine gespeicherte Freundin -> Es wird direkt die Create_Activity aufgerufen.
        if (sqlhelper.getGirlfriend() != null) {

            if(sqlhelper.usernamecorrect(username,password)){
                Intent x = new Intent(this, New_Show_Activity.class);
                startActivity(x);
            }else{
                Toast.makeText(Login_activity.this,"Eingabe fehlerhaft",Toast.LENGTH_SHORT).show();
            }
        } else {
            Intent z = new Intent(this, Create_Activity.class);
            startActivity(z);
        }
    }

    //Wird durch onClick auf dem Löschen Button aufgerufen, löscht einmal alles in der Tabele sodass Nutzer wieder Platz für eine neue Freundin hat.
    //um Ärger zu vermeiden hielt ich es für besser gleich die Möglichkeit zu geben den Benutzernamen und das Passwort ändern zu können.
    public void deleteGirlfriend(View view){
        sqlhelper = new MySQLiteHelper(getBaseContext());
        Girlfriend girlfriend = sqlhelper.getGirlfriend();
        sqlhelper.deleteGirl(girlfriend);
        Intent z = new Intent(this, Create_Activity.class);
        startActivity(z);
    }
}


