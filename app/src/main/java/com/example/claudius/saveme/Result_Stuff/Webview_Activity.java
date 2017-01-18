package com.example.claudius.saveme.Result_Stuff;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.claudius.saveme.R;
import com.example.claudius.saveme.Storages.Girlfriend;
import com.example.claudius.saveme.Storages.MySQLiteHelper;

public class Webview_Activity extends AppCompatActivity {


    MySQLiteHelper sqlhelper;
    Girlfriend girlfriend = new Girlfriend();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);

        sqlhelper = new MySQLiteHelper(getBaseContext());

        girlfriend = sqlhelper.getGirlfriend();

        String url = "leer";

        //Argumente werden wie bei JSON aus dem bundle geholt und dann wird mit der jeweiligen switch methode heruasgefunden was dargestellt werden soll

        Bundle extra = getIntent().getExtras();

        switch (extra.getString("company")) {


            case "amazon":


                switch (extra.getInt("cat")) {

                    case 0:
                        url = "https://www.amazon.de/s/ref=nb_sb_noss_1?__mk_de_DE=ÅMÅŽÕÑ&url=search-alias%3Daps&field-keywords=" + girlfriend.getBand();
                        break;

                    case 1:
                        url = "https://www.amazon.de/s/ref=nb_sb_noss_1?__mk_de_DE=ÅMÅŽÕÑ&url=search-alias%3Daps&field-keywords=" + girlfriend.getFood();
                        break;

                    case 2:
                        url = "https://www.amazon.de/s/ref=nb_sb_noss_1?__mk_de_DE=ÅMÅŽÕÑ&url=search-alias%3Daps&field-keywords=" + girlfriend.getColor()+"+Damen";
                        break;

                    case 3:
                        url = "https://www.amazon.de/s/ref=nb_sb_noss_1?__mk_de_DE=ÅMÅŽÕÑ&url=search-alias%3Daps&field-keywords=" + girlfriend.getFlowers();
                        break;


                }

                break;

            case "eventim":
                url = "http://www.eventim.de/Tickets.html?affiliate=EVE&fun=search&fuzzy=yes&doc=search&action=grouped&inline=false&suchbegriff=" + girlfriend.getBand();
                break;

            case "zalando":
                url = "https://m.zalando.de/damen/?q=" + girlfriend.getColor();
                break;

            case "yelp":

                url = "https://www.yelp.de/search?find_desc=" + girlfriend.getFood() + "&find_loc=" + girlfriend.getResidence();
                break;

            case "hundm":
                url = "http://www.hm.com/de/products/search?categories=ladies&q=" + girlfriend.getColor();
                break;

            case "blumenfee":
                url = "https://www.blumenfee.de/catalogsearch/result/?q=" + girlfriend.getFlowers();
                break;

        }

        WebView webview = (WebView) findViewById(R.id.webview);
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
    }

}
