package com.example.claudius.saveme.Create_stuff;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.claudius.saveme.Interfaces.ActivityCommunicator;
import com.example.claudius.saveme.Result_Stuff.New_Show_Activity;
import com.example.claudius.saveme.Storages.Girlfriend;
import com.example.claudius.saveme.Storages.MySQLiteHelper;
import com.example.claudius.saveme.Interfaces.OnFragmentInteractionListener;
import com.example.claudius.saveme.R;

import com.example.claudius.saveme.Storages.User;

/*
Klasse dient Hauptsächlich der Steuerung der Fragmente für den Freundin erstell Prozess. Hat das Interface ActivityCommunicator welches dafür sorgt das die Klassen miteinander
Kommunizieren können.
 */

public class Create_Activity extends AppCompatActivity implements OnFragmentInteractionListener, ActivityCommunicator {

    //Enums für die Ganzen Fragmente
    public static final int maxfragment = 5;
    public static final int minfragment = 0;

    public static final int fragment0 = 0;
    public static final int fragment1 = 1;
    public static final int fragment2 = 2;
    public static final int fragment3 = 3;
    public static final int fragment4 = 4;
    public static final int createfertig = 5;

    public static final int TypeUserName = 1;
    public static final int TypeBirthday = 2;
    public static final int TypeAnniversary = 3;
    public static final int Typefavourites = 4;
    public static final int TypePassword = 0;

    //Counter welches Fragment gerade aktiv ist.
    private int counter = 0;

    //Zwischenspeicher Freundin
    private Girlfriend girlfriend = new Girlfriend();
    //Zwiwchenspeicher Userinfos
    User user = new User();
    //Wird als einziges Fragment schonmal vorher geladen weil es Probleme mit dem Zwiwchenspeicher der Attribute gegeben hat.
    create_4 c4 = new create_4();

    public Girlfriend getGirlfriend() {
        return girlfriend;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity);

        final Button incbutton;
        incbutton = (Button) findViewById(R.id.forwardbutton);
        loadnewFragment();
        incbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                if(counter <= maxfragment){
                    loadnewFragment();
                }else{
                    counter--;

                }

            }
        });

        //Zurück button beim Erstellungsprozess. Listener öffnet dann jeweils die loadnewfragment() Methode und verhindert gleichzeitig das der Counter
        //zu nicht definierten werten kommt


        final Button decbutton;
        decbutton = (Button) findViewById(R.id.backbutton);
        decbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                if(counter >= minfragment){
                    loadnewFragment();
                }else{
                    counter++;
                }

            }
        });
    }

    //lädt je nach counter das dem entsprechende Fragment in das Layout mit der id fragment_holder
    public void loadnewFragment(){
        Button backbutton = (Button) findViewById(R.id.backbutton);
        if(counter == fragment0) {

            backbutton.setVisibility(View.INVISIBLE);
            FragmentManager manager = getFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            String tag = create_0.class.getSimpleName();
            fragmentTransaction.replace(R.id.fragment_holder,new create_0(),tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        }

        if(counter == fragment1) {
            backbutton.setVisibility(View.VISIBLE);
            FragmentManager manager = getFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            String tag = create_1.class.getSimpleName();
            fragmentTransaction.replace(R.id.fragment_holder,new create_1(),tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        }


        if(counter == fragment2) {

            FragmentManager manager = getFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            String tag = create_2.class.getSimpleName();
            fragmentTransaction.replace(R.id.fragment_holder, new create_2(), tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();

        }

        if(counter == fragment3) {

            FragmentManager manager = getFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            String tag = create_3.class.getSimpleName();
            fragmentTransaction.replace(R.id.fragment_holder, new create_3(), tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();

        }

        if(counter == fragment4) {

            FragmentManager manager = getFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            String tag = create_4.class.getSimpleName();
            fragmentTransaction.replace(R.id.fragment_holder, c4, tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();

        }

        if(counter == createfertig){

            c4.submit();
            MySQLiteHelper sqhelper = new MySQLiteHelper(this.getBaseContext());
            sqhelper.addGirl(girlfriend, user);

            girlfriend = sqhelper.getGirlfriend();

            Intent switchToInfo = new Intent(this, New_Show_Activity.class);
            startActivity(switchToInfo);
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //Fügt je nach Typ der empfangennen Nachricht die erhaltenen Werte in die Girlfriend Klasse ein

    @Override
    public void passStrings(String someValue, int type){
        switch(type){

            case TypeUserName:

                int nameindex = someValue.indexOf("/");
                String name = someValue.substring(0,nameindex);
                String residence = someValue.substring(nameindex+1,someValue.length());
                girlfriend.setName(name);
                girlfriend.setResidence(residence);

                break;

            case Typefavourites:

                int ersterteiler = someValue.indexOf("/");
                int zweiterteiler =  someValue.indexOf("--");

                String band = someValue.substring(0,ersterteiler);
                girlfriend.setBand(band);
                String flowers = someValue.substring(ersterteiler+1,zweiterteiler);
                girlfriend.setFlowers(flowers);
                String food = someValue.substring(zweiterteiler+2,someValue.length());
                girlfriend.setFood(food);

                break;

            case TypePassword:

                int index = someValue.indexOf("/");
                String username = someValue.substring(0,index);
                String passwort = someValue.substring(index+1,someValue.length());

                user.setUsername(username);
                user.setPassword(passwort);

                break;
            default:
                break;
        }
    }

    @Override
    public void sendDates(int day, int month, int year, int Type) {

        switch(Type){

            case TypeBirthday:

                girlfriend.setbDayDay(day);
                girlfriend.setbDayMonth(month);
                girlfriend.setbDayYear(year);

                break;

            case TypeAnniversary:

                girlfriend.setAnniversaryDay(day);
                girlfriend.setAnniversaryMonth(month);
                girlfriend.setAnniversaryYear(year);
                break;
        }
    }

    @Override
    public void sendBitmap(Bitmap bitmap){
        girlfriend.setProfilePic(bitmap);
    }

    public static int getTypeUserName() {
        return TypeUserName;
    }

    public static int getTypeBirthday() {
        return TypeBirthday;
    }

    public static int getTypeAnniversary() {
        return TypeAnniversary;
    }

    public static int getTypefavourites() {
        return Typefavourites;
    }

    public void leerlauf(View view){

    }
}
