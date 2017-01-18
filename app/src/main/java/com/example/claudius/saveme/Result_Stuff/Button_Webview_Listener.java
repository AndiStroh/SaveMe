package com.example.claudius.saveme.Result_Stuff;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.View;

import com.example.claudius.saveme.Create_stuff.create_2;
import com.example.claudius.saveme.Login.Login_activity;
import com.example.claudius.saveme.R;






//Listener der je nach gedrücktem Firmenbutton im giftfinder_tab die Firma der gewünschten Webseite an die Show_Activity sendet.
public class Button_Webview_Listener implements View.OnClickListener {

    private Giftfinder_Tab giftfinder_fragment;
    private int sendetyp = 6;

    public Button_Webview_Listener(Giftfinder_Tab resfrag){

        giftfinder_fragment = resfrag;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            //Anhand des Strings kann dann die Show_Activity herausfinden welche seite geünscht ist und dementsprechend reagieren.

            //Hier wird jeweils auf die Buttonid verglichen (ids sind immer integers deshalb kann man das so machen)
            case R.id.companyAmazon:
                //Bei Amazon ist wieder die besonderheit das man die Kategorie zusätzlich wissen muss

                Intent i = new Intent(giftfinder_fragment.getActivity(), Webview_Activity.class);
                i.putExtra("company","amazon");
                i.putExtra("cat",giftfinder_fragment.radioCategory);
                giftfinder_fragment.getActivity().startActivity(i);

                break;

            case R.id.companyZalando:

                Intent j = new Intent(giftfinder_fragment.getActivity(), Webview_Activity.class);
                j.putExtra("company","zalando");
                j.putExtra("cat",giftfinder_fragment.radioCategory);
                giftfinder_fragment.getActivity().startActivity(j);

                break;

            case R.id.companyYelp:

                Intent k = new Intent(giftfinder_fragment.getActivity(), Webview_Activity.class);
                k.putExtra("company","yelp");
                k.putExtra("cat",giftfinder_fragment.radioCategory);
                giftfinder_fragment.getActivity().startActivity(k);


                break;

            case R.id.companyEventim:

                Intent l = new Intent(giftfinder_fragment.getActivity(), Webview_Activity.class);
                l.putExtra("company","eventim");
                l.putExtra("cat",giftfinder_fragment.radioCategory);
                giftfinder_fragment.getActivity().startActivity(l);
                break;

            case R.id.companyHundM:

                Intent m = new Intent(giftfinder_fragment.getActivity(), Webview_Activity.class);
                m.putExtra("company","hundm");
                m.putExtra("cat",giftfinder_fragment.radioCategory);
                giftfinder_fragment.getActivity().startActivity(m);
                break;

            case R.id.companyBlumenfeeDe:
                Intent n = new Intent(giftfinder_fragment.getActivity(), Webview_Activity.class);
                n.putExtra("company","blumenfee");
                n.putExtra("cat",giftfinder_fragment.radioCategory);
                giftfinder_fragment.getActivity().startActivity(n);

                break;

        }

    }
}
