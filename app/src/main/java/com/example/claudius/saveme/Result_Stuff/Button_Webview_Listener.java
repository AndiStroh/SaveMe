package com.example.claudius.saveme.Result_Stuff;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.View;

import com.example.claudius.saveme.Create_stuff.create_2;
import com.example.claudius.saveme.Login.Login_activity;
import com.example.claudius.saveme.R;





//Anhand des Strings kann dann die Show_Activity herausfinden welche seite geünscht ist und dementsprechend reagieren.

//Hier wird jeweils auf die Buttonid verglichen (ids sind immer integers deshalb kann man das so machen)
//Bei Amazon ist wieder die besonderheit das man die Kategorie zusätzlich wissen muss


//Listener der je nach gedrücktem Firmenbutton im giftfinder_tab die Firma der gewünschten Webseite an die Show_Activity sendet.
public class Button_Webview_Listener implements View.OnClickListener {

    private Giftfinder_Tab giftfinder_fragment;
    private int sendetyp = 6;

    public Button_Webview_Listener(Giftfinder_Tab resfrag){

        giftfinder_fragment = resfrag;

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(giftfinder_fragment.getActivity(), Webview_Activity.class);
        switch (v.getId()){

            case R.id.companyAmazon:

                i.putExtra("company","amazon");
                i.putExtra("cat",giftfinder_fragment.radioCategory);
                giftfinder_fragment.getActivity().startActivity(i);

                break;

            case R.id.companyZalando:

                i.putExtra("company","zalando");
                i.putExtra("cat",giftfinder_fragment.radioCategory);
                giftfinder_fragment.getActivity().startActivity(i);

                break;

            case R.id.companyYelp:

                i.putExtra("company","yelp");
                i.putExtra("cat",giftfinder_fragment.radioCategory);
                giftfinder_fragment.getActivity().startActivity(i);


                break;

            case R.id.companyEventim:

                i.putExtra("company","eventim");
                i.putExtra("cat",giftfinder_fragment.radioCategory);
                giftfinder_fragment.getActivity().startActivity(i);
                break;

            case R.id.companyHundM:

                i.putExtra("company","hundm");
                i.putExtra("cat",giftfinder_fragment.radioCategory);
                giftfinder_fragment.getActivity().startActivity(i);
                break;

            case R.id.companyBlumenfeeDe:

                i.putExtra("company","blumenfee");
                i.putExtra("cat",giftfinder_fragment.radioCategory);
                giftfinder_fragment.getActivity().startActivity(i);

                break;

        }

    }
}
