package com.example.claudius.saveme;

import android.graphics.Color;
import android.view.View;

/**
 * Created by Claudius on 20.12.16.
 */

public class ButtonColorListener implements View.OnClickListener {


    create_4 create3;

    public ButtonColorListener(create_4 c3){
        create3 = c3;
    }

    @Override
    public void onClick(View v) {
        create3.favouriteColorRedButton.setBackgroundColor(Color.RED);
        create3.favouriteColorGreenButton.setBackgroundColor(Color.GREEN);
        create3.favouriteColorBlueButton.setBackgroundColor(Color.BLUE);
        create3.favouriteColorYellowButton.setBackgroundColor(Color.YELLOW);

        switch(v.getId()){


            case R.id.favouriteColorRedButton:

                create3.favouriteColorRedButton.getBackground().setAlpha(150);

                break;

            case R.id.favouriteColorGreenButton:

                create3.favouriteColorGreenButton.getBackground().setAlpha(150);

                break;


            case R.id.favouriteColorBlueButton:

                create3.favouriteColorBlueButton.getBackground().setAlpha(150);


                break;

            case R.id.favouriteColorYellowButton:

                create3.favouriteColorYellowButton.getBackground().setAlpha(150);

                break;
        }
    }
}
