package com.example.claudius.saveme;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

public class Create_Activity extends AppCompatActivity implements create_1.OnFragmentInteractionListener, create_2.OnFragmentInteractionListener, create_3.OnFragmentInteractionListener, create_4.OnFragmentInteractionListener, ActivityCommunicator{

    public static final int maxfragment = 4;
    public static final int minfragment = 1;

    public static final int fragment1 = 1;
    public static final int fragment2 = 2;
    public static final int fragment3 = 3;
    public static final int fragment4 = 4;

    public static final int TypeUserName = 1;
    public static final int TypeBirthday = 2;
    public static final int TypeAnniversary = 3;

    private int counter = 1;

    Girlfriend girlfriend = new Girlfriend();
    ImageButton buttonLoadImage;

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

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void passusername(String someValue, int type){
        Toast.makeText(Create_Activity.this,someValue +" In Activity: "+ String.valueOf(type),Toast.LENGTH_SHORT).show();

        switch(type){

            case TypeUserName:

                girlfriend.setUsername(someValue);

                break;

            default:

                break;



        }
    }

    @Override
    public void sendDates(int Day, int month, int Type) {

    }


}
