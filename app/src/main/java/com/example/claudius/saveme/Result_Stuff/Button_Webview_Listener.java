package com.example.claudius.saveme.Result_Stuff;

import android.view.View;

import com.example.claudius.saveme.R;

/**
 * Created by Claudius on 25.12.16.
 */


//Listener der je nach gedrücktem Firmenbutton im result_fragment die Firma der gewünschten Webseite an die Show_Activity sendet.
public class Button_Webview_Listener implements View.OnClickListener {

    private result_fragment result_fragment;
    private int sendetyp = 6;

    public Button_Webview_Listener(result_fragment resfrag){

        result_fragment = resfrag;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            //Anhand des Strings kann dann die Show_Activity herausfinden welche seite geünscht ist und dementsprechend reagieren.

            //Hier wird jeweils auf die Buttonid verglichen (ids sind immer integers deshalb kann man das so machen)
            case R.id.companyAmazon:
                //Bei Amazon ist wieder die besonderheit das man die Kategorie zusätzlich wissen muss
                result_fragment.sendButtonName("amazon"+String.valueOf(result_fragment.radioCategory));

                break;

            case R.id.companyZalando:

                result_fragment.sendButtonName("zalando");

                break;

            case R.id.companyYelp:

                result_fragment.sendButtonName("yelp");

                break;

            case R.id.companyeventim:

                result_fragment.sendButtonName("eventim");

                break;

            case R.id.companyHundM:

                result_fragment.sendButtonName("hundm");

                break;

            case R.id.companyBlumenfee:

                result_fragment.sendButtonName("blumenfee");

                break;

        }

    }
}
