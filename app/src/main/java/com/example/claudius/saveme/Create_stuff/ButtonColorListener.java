package com.example.claudius.saveme.Create_stuff;

import android.graphics.Color;
import android.view.View;

import com.example.claudius.saveme.R;

//Listener der der Create Activity mitteilt welcher Farb auswahl Button gedrückt wurde.
public class ButtonColorListener implements View.OnClickListener {


    create_4 create4;

    public ButtonColorListener(create_4 c4){
        create4 = c4;
    }

    @Override
    public void onClick(View v) {
        create4.favouriteColorRedButton.setBackgroundColor(Color.RED);
        create4.favouriteColorGreenButton.setBackgroundColor(Color.GREEN);
        create4.favouriteColorBlueButton.setBackgroundColor(Color.BLUE);
        create4.favouriteColorYellowButton.setBackgroundColor(Color.YELLOW);
        Create_Activity create_activity = (Create_Activity) create4.getActivity();
        switch(v.getId()){


            case R.id.favouriteColorRedButton:

                create4.favouriteColorRedButton.getBackground().setAlpha(150);
                create_activity.getGirlfriend().setColor("RED");

                break;

            case R.id.favouriteColorGreenButton:

                create4.favouriteColorGreenButton.getBackground().setAlpha(150);
                create_activity.getGirlfriend().setColor("GREEN");
                break;


            case R.id.favouriteColorBlueButton:

                create4.favouriteColorBlueButton.getBackground().setAlpha(150);
                create_activity.getGirlfriend().setColor("BLUE");

                break;

            case R.id.favouriteColorYellowButton:

                create4.favouriteColorYellowButton.getBackground().setAlpha(150);
                create_activity.getGirlfriend().setColor("YELLOW");
                break;
        }
    }
}
