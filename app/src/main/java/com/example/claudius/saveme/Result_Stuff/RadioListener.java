package com.example.claudius.saveme.Result_Stuff;

import android.view.View;
import android.widget.RadioGroup;

import com.example.claudius.saveme.R;

/**
 * Created by Claudius on 25.12.16.
 */

//Dieser Listener sorgt dafür das je nach ausgewählter Radio Button Kategorie nur die relevanten Buttons auswählbar sind. Die anderen werden transparent und nicht anklickbar

public class RadioListener implements RadioGroup.OnCheckedChangeListener {

    Giftfinder_Tab giftfinder_tab;

    public RadioListener(Giftfinder_Tab rF){
        giftfinder_tab = rF;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        View result_view = giftfinder_tab.getView();
        float notselectable = (float)0.2;
        float selectable = 1;


//Amazon hat alles was wir bisher als Kategoreie anbieten deshalb taucht es hier nicht auf

        //Zu Beginn werden alle Buttons nicht klickbar und zudem auf dem Alphawert 0,5 gesetzt
        // -> mit Alpha kann man Buttons transparent schalten 1 -> sichtbar, 0 -> komplett transparent
        result_view.findViewById(R.id.companyZalando).setClickable(false);
        result_view.findViewById(R.id.companyZalando).setAlpha(notselectable);

        result_view.findViewById(R.id.companyYelp).setClickable(false);
        result_view.findViewById(R.id.companyYelp).setAlpha(notselectable);

        result_view.findViewById(R.id.companyEventim).setClickable(false);
        result_view.findViewById(R.id.companyEventim).setAlpha(notselectable);

        result_view.findViewById(R.id.companyHundM).setClickable(false);
        result_view.findViewById(R.id.companyHundM).setAlpha(notselectable);

        result_view.findViewById(R.id.companyBlumenfeeDe).setClickable(false);
        result_view.findViewById(R.id.companyBlumenfeeDe).setAlpha(notselectable);



        //Je nachdem welcher Button ausgewählt wurde wird hier über die button id bestimmt um welchen Button es sich handelt und die entrsprechenden Buttons auswählbar und
        //voll sichtbar gemacht.
        switch (checkedId){

            case R.id.radioMusic:

                result_view.findViewById(R.id.companyEventim).setClickable(true);
                result_view.findViewById(R.id.companyEventim).setAlpha(selectable);
                giftfinder_tab.radioCategory = giftfinder_tab.catMusic;


                break;

            case R.id.radioFood:

                result_view.findViewById(R.id.companyYelp).setClickable(true);
                result_view.findViewById(R.id.companyYelp).setAlpha(selectable);
                giftfinder_tab.radioCategory = giftfinder_tab.catFood;
                break;

            case R.id.radioClothes:

                result_view.findViewById(R.id.companyZalando).setClickable(true);
                result_view.findViewById(R.id.companyZalando).setAlpha(selectable);

                result_view.findViewById(R.id.companyHundM).setClickable(true);
                result_view.findViewById(R.id.companyHundM).setAlpha(selectable);
                giftfinder_tab.radioCategory = giftfinder_tab.catClothing;

                break;

            case R.id.radioFlowers:

                result_view.findViewById(R.id.companyBlumenfeeDe).setClickable(true);
                result_view.findViewById(R.id.companyBlumenfeeDe).setAlpha(selectable);
                giftfinder_tab.radioCategory = giftfinder_tab.catFlowers;

                break;




        }
    }
}
