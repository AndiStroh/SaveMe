package com.example.claudius.saveme.Result_Stuff;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.example.claudius.saveme.Interfaces.ActivityCommunicator;
import com.example.claudius.saveme.Interfaces.OnFragmentInteractionListener;
import com.example.claudius.saveme.R;
import com.example.claudius.saveme.Storages.Girlfriend;
import com.example.claudius.saveme.Storages.MySQLiteHelper;


//Klasse wird aufgerufen um eine Übersicht über die Freundin darzustellen und eine Fläche für den Webview bereitzustellen
public class Show_Activity extends AppCompatActivity implements OnFragmentInteractionListener, ActivityCommunicator {


    public static final int WEBVIEWTYPE = 6;
    MySQLiteHelper sqlhelper;
    Girlfriend girlfriend = new Girlfriend();
    Button backtoOverview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqlhelper = new MySQLiteHelper(getBaseContext());

        girlfriend = sqlhelper.getGirlfriend();

        setContentView(R.layout.activity_show_information);
        backToOverview();


        //Das ist der Button der auf der Activity erscheint um den Nutzer ein zurückspringen zu ermöglichen -> Tests von mir haben gezeigt das der Android zurück
        //Knopf nicht ausreicht (bei meiner Mum und meiner Schwester)
        backtoOverview = (Button) findViewById(R.id.backtoOverviewButton);
        backtoOverview.setVisibility(View.INVISIBLE);
        backtoOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                WebView wb = (WebView) findViewById(R.id.webview);

                if (wb.canGoBack()) {
                    wb.goBack();
                } else {
                    backToOverview();

                    //Button wird nur wenn der Webview offen ist gebraucht -> deshalb hier invisible ->da aber nicht sichtbar -> bei GONE wäre er weg
                    backtoOverview.setVisibility(View.INVISIBLE);
                }
            }
        });


    }
//Wird hier dazu verwendet um heruaszufinden welcher Button bei der Firmenauswhl gedrückt wurde -> Type 6 ist nur da wegen dem Never trust unchecked Data
//Dings
    @Override
    public void passStrings(String someValue, int type) {

        if (type != 6){

        }else{
            webView webView = new webView();
            Bundle bundle = new Bundle();

            //Amazon kann als einziger bei mehreren Kategorien ausgewählt werden, deshalb müssen wir hier feststellen um welche Kategorie es sich handelt
            //der amazon string ist immer amazon+Zahl (z.B. amazon0, amazon1) -> so kann man feststellen welcher Typ es ist.
            if(someValue.contains("amazon")){
                int li = someValue.length();
                int category = Integer.parseInt(someValue.substring(li-1));

                switch (category){

                    case 0:
                        bundle.putString("attribut",girlfriend.getBand());
                        bundle.putString("firma","amazon");
                        break;

                    case 1:
                        bundle.putString("firma","amazon");
                        bundle.putString("attribut",girlfriend.getFood());
                        break;

                    case 2:
                        bundle.putString("firma","amazon");
                        bundle.putString("attribut","Damen "+"+"+girlfriend.getColor());
                        break;
                }
                webView.setArguments(bundle);



            }else{

               //Hier noch für die anderen Firmen
                switch(someValue){


                    case "zalando":
                        bundle.putString("firma", "zalando");
                        bundle.putString("attribut", girlfriend.getColor());
                        webView.setArguments(bundle);
                        break;

                    case "yelp":
                        bundle.putString("firma", "yelp");
                        bundle.putString("stadt", girlfriend.getResidence());
                        bundle.putString("attribut", girlfriend.getFood());
                        webView.setArguments(bundle);

                        break;

                    case "eventim":
                        bundle.putString("firma", "eventim");
                        bundle.putString("attribut", girlfriend.getBand());
                        webView.setArguments(bundle);
                        break;

                    case "hundm":
                        bundle.putString("firma", "hundm");
                        bundle.putString("attribut", girlfriend.getColor());
                        webView.setArguments(bundle);
                        break;

                    case "blumenfee":
                        bundle.putString("firma", "blumenfee");
                        bundle.putString("attribut", girlfriend.getFlowers());
                        webView.setArguments(bundle);
                        break;

                }
            }
            //Am ende der ifs wird immer der Webview aufgerufen -> der Button wird dann auf Visible gesetzt weil wir jetzt ja zurück gehen können müssen
            FragmentManager manager = getFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            String tag = webView.class.getSimpleName();
            fragmentTransaction.replace(R.id.fragment_holder_result,webView,tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
            backtoOverview.setVisibility(View.VISIBLE);


        }


    }

    //Funktion ruft wieder die Übersicht auf und übergibt um die Verbindung zur Datenbank so wenig wie möglich zu brauchen alle Attribute außer das Bild
    //Das Bild musste ich über die DB holen da es sonst zu abstürzen wegen zu großer Datenmengen gekommen ist.
    //Bundle funktioniert im wesentlichen genau wie JSON mit put (putString,putInt usw.) setzt mann die Daten in das Bundle
    //mit Object.setArguments(bundle) fügt man sie hinzu. in der Klasse muss man dann this.getArguments aufrufen -> im result_fragment besser erklärt
    public void backToOverview(){



        result_fragment result_fragment = new result_fragment();

        Bundle bundle = new Bundle();
        bundle.putString("gfName",girlfriend.getName());
        bundle.putString("farbe",girlfriend.getColor());
        bundle.putString("favBand",girlfriend.getBand());
        bundle.putString("favFlowers",girlfriend.getFlowers());
        bundle.putString("favKindofFood",girlfriend.getFood());

        bundle.putInt("bDayDay",girlfriend.getbDayDay());
        bundle.putInt("bDayMonth",girlfriend.getbDayMonth());
        bundle.putInt("bDayYear",girlfriend.getbDayYear());

        bundle.putInt("anniversaryDay",girlfriend.getAnniversaryDay());
        bundle.putInt("anniversaryMonth",girlfriend.getAnniversaryMonth());
        bundle.putInt("anniversaryYear",girlfriend.getAnniversaryYear());



        result_fragment.setArguments(bundle);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        String tag = result_fragment.class.getSimpleName();
        fragmentTransaction.replace(R.id.fragment_holder_result,result_fragment,tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    @Override
    public void sendDates(int Day, int month, int year, int Type) {
    }

    @Override
    public void sendBitmap(Bitmap bitmap) {
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
