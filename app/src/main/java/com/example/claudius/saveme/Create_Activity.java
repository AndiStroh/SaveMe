package com.example.claudius.saveme;

import android.app.FragmentManager;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Create_Activity extends AppCompatActivity implements create_1.OnFragmentInteractionListener, create_2.OnFragmentInteractionListener, ActivityCommunicator{

    public static final int maxfragment = 2;
    public static final int minfragment = 1;

    public static final int fragment1 = 1;
    public static final int fragment2 = 2;

    public static final int TypeUserName = 1;
    public static final int TypeBirthday = 2;
    public static final int TypeAnniversary = 3;

    private create_1 c1;
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
