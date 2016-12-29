package com.example.claudius.saveme;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/*
Klasse dient Hauptsächlich der Steuerung der Fragmente. Hat das Interface ActivityCommunicator welches dafür sorgt das die Klassen miteinander
Kommunizieren können.
 */

public class Create_Activity extends AppCompatActivity implements OnFragmentInteractionListener, ActivityCommunicator{

    public static final int maxfragment = 4;
    public static final int minfragment = 1;

    public static final int fragment1 = 1;
    public static final int fragment2 = 2;
    public static final int fragment3 = 3;
    public static final int fragment4 = 4;

    public static final int TypeUserName = 1;
    public static final int TypeBirthday = 2;
    public static final int TypeAnniversary = 3;
    public static final int Typefavourites = 4;

    private int counter = 1;

    private Girlfriend girlfriend = new Girlfriend();
    ImageButton buttonLoadImage;

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

    public void loadnewFragment(){

        if(counter == fragment1) {

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
            fragmentTransaction.replace(R.id.fragment_holder, new create_4(), tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();

        }

        if(counter > 4){

            Intent switchToInfo = new Intent(this, Show_Activity.class);
            startActivity(switchToInfo);
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void passStrings(String someValue, int type){
        Toast.makeText(Create_Activity.this,someValue +" In Activity: "+ String.valueOf(type),Toast.LENGTH_SHORT).show();

        switch(type){

            case TypeUserName:

                girlfriend.setName(someValue);

                break;

            case Typefavourites:

                int ersterteiler = someValue.indexOf("/");
                int zweiterteiler =  someValue.indexOf("--");

                String band = someValue.substring(0,ersterteiler);

                girlfriend.setBand(band);
                Toast.makeText(Create_Activity.this,"Band in GF: "+girlfriend.getBand(), Toast.LENGTH_SHORT).show();

                String flowers = someValue.substring(ersterteiler+1,zweiterteiler);

                girlfriend.setFlowers(flowers);

                Toast.makeText(Create_Activity.this,"Flowers in GF: "+girlfriend.getFlowers(), Toast.LENGTH_SHORT).show();

                String food = someValue.substring(zweiterteiler+2,someValue.length());

                girlfriend.setFood(food);

                Toast.makeText(Create_Activity.this,"Food in GF: "+ girlfriend.getFood(), Toast.LENGTH_SHORT).show();

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
                Toast.makeText(Create_Activity.this, "Birthday: "+ girlfriend.getbDayDay() +" "+ girlfriend.getbDayMonth() +" "+ girlfriend.getbDayYear(), Toast.LENGTH_SHORT).show();
                break;

            case TypeAnniversary:

                girlfriend.setAnniversaryDay(day);
                girlfriend.setAnniversaryMonth(month);
                girlfriend.setAnniversaryYear(year);
                Toast.makeText(Create_Activity.this, "Anniversary: "+ girlfriend.getAnniversaryDay() +" "+ girlfriend.getAnniversaryMonth() +" "+ girlfriend.getAnniversaryYear(), Toast.LENGTH_SHORT).show();
                break;
        }
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
