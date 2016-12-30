package com.example.claudius.saveme.Result_Stuff;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.claudius.saveme.Interfaces.OnFragmentInteractionListener;
import com.example.claudius.saveme.R;

//Klasse macht nichts als die jeweilige Adresse mit dem übergebenen Attribut in einem WebView darzustellen

public class webView extends Fragment implements OnFragmentInteractionListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View view;
    private String attribut;
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public webView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment webView.
     */
    // TODO: Rename and change types and number of parameters
    public static webView newInstance(String param1, String param2) {
        webView fragment = new webView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =  inflater.inflate(R.layout.web_view_fragment, container, false);



        String url = "leer";

        //Argumente werden wie bei JSON aus dem bundle geholt und dann wird mit der jeweiligen switch methode heruasgefunden was dargestellt werden soll

        if (getArguments() != null) {
            Bundle bundle = this.getArguments();

            attribut = bundle.getString("attribut");

            switch (bundle.getString("firma")){

                case "amazon":
                    url = "https://www.amazon.de/s/ref=nb_sb_noss_1?__mk_de_DE=ÅMÅŽÕÑ&url=search-alias%3Daps&field-keywords="+attribut;
                    break;

                case "eventim":
                    url ="http://www.eventim.de/Tickets.html?affiliate=EVE&fun=search&fuzzy=yes&doc=search&action=grouped&inline=false&suchbegriff="+attribut;
                    break;

                case "zalando":
                    url = "https://m.zalando.de/damen/?q="+attribut;
                    break;

                case "yelp":

                    url ="https://www.yelp.de/search?find_desc="+attribut+"&find_loc="+bundle.getString("stadt");
                    break;

                case "hundm":
                    url = "http://www.hm.com/de/products/search?categories=ladies&q="+attribut;
                    break;

                case "blumenfee":
                    url = "https://www.blumenfee.de/catalogsearch/result/?q="+attribut;
                    break;

            }
        }

        WebView webview = (WebView) view.findViewById(R.id.webview);
        //Wenn Javascript nicht enabled ist, wird bei manchen Seiten die Desktop Homepgae angezeigt. Der Webclient ist notwendig um zu verhindern das er
        //default Browser öffnet -> dann wäre der Nutzer weg.
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(url);

        //Hier wird eingestellt das der Nutzer wenn er auf zurück klickt nur eine Seite zurück geht und wenn er auf der Startseite ist zurück zum Overview kommt
        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    WebView webView = (WebView) v;

                    switch(keyCode)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if(webView.canGoBack())
                            {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });
        return view;
    }


    //Methode die wir nicht brauchen
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
