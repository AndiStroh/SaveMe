package com.example.claudius.saveme.Result_Stuff;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.claudius.saveme.Interfaces.OnFragmentInteractionListener;
import com.example.claudius.saveme.R;
import com.example.claudius.saveme.Storages.Girlfriend;
import com.example.claudius.saveme.Storages.MySQLiteHelper;

import java.text.ParseException;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Girlfriend_Tab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Girlfriend_Tab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Girlfriend_Tab extends android.support.v4.app.Fragment implements OnFragmentInteractionListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Hier speichern wir uns das heutige Datum.
    Calendar cal = Calendar.getInstance();
    int this_year = cal.get(Calendar.YEAR);
    int this_month = cal.get(Calendar.MONTH)+1;
    int this_day = cal.get(Calendar.DAY_OF_MONTH);

    Girlfriend girlfriend = new Girlfriend();

    Bitmap profilePic;
    MySQLiteHelper sqlhelper;

    private OnFragmentInteractionListener mListener;

    public Girlfriend_Tab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Girlfriend_Tab.
     */
    // TODO: Rename and change types and number of parameters
    public static Girlfriend_Tab newInstance(String param1, String param2) {
        Girlfriend_Tab fragment = new Girlfriend_Tab();
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
        sqlhelper = new MySQLiteHelper(getActivity().getBaseContext());
        girlfriend = sqlhelper.getGirlfriend();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.girlfriend_tab_fragment, container, false);

        //--------------------------------------------- Freundin namen setzen + Bild einfügen in Image view --------------------------------------------------------------------
        TextView gfName = (TextView) view.findViewById(R.id.NameLabel);
        gfName.setText(girlfriend.getName());
        profilePic = sqlhelper.getGFProfilePic();
        girlfriend.setProfilePic(profilePic);

        ImageView profpic = (ImageView) view.findViewById(R.id.ProfilePic);
        profpic.setImageBitmap(girlfriend.getProfilePic());

        //--------------------------------------------- Geburtsdatum + Anzahl Tage bis zum nächsten Geburtstag anzeigen --------------------------------------------------------

        //gebdatView zeigt ist ein Textview der das Geburtsdatum der Freundin anzeigt
        TextView gebdatview = (TextView) view.findViewById(R.id.BDayDate);
        gebdatview.setText(String.valueOf(girlfriend.getbDayDay())+"."+girlfriend.getbDayMonth()+"."+girlfriend.getbDayYear());

        //Dieser String beinhaltet Das geburtsdatum aufgeteilt in Tag/Monat/Jahr
        String bDayDate=  String.valueOf(girlfriend.getbDayDay()) + "/"+ String.valueOf(girlfriend.getbDayMonth()) + "/" +String.valueOf(this_year);

        //Hier lassen wir die Funktion daysleft die Anzahl der Tage bis zum nächsten Geburtstag ausrechnen.
        String daysleftbDay = daysleft(bDayDate);

        //Jetzt holen wir uns den Textview der am Ende die Anzahl der Tage bis zum Geburtstag noch anzeigen soll.
        TextView daysleftBdayfield = (TextView) view.findViewById(R.id.DaysToBDay);
        int daystobDay = Integer.parseInt(daysleftbDay);

        //Hier lassen wir checken ob das Ereignis noch weit weg ist oder ob wir den Nutzer warnen müssen.
        setColorandtext(daysleftBdayfield,daystobDay);

        //--------------------------------------------- Jahrestagdatum + Anzahl Tage bis zum nächsten Jahrestag anzeigen --------------------------------------------------------

        //Hier schreiben wir das Jahrestagdatum aus der Girlfriendklasse direkt in den Textview
        TextView anniversaryview = (TextView) view.findViewById(R.id.AnniversaryDate);
        anniversaryview.setText(girlfriend.getAnniversaryDay() + "."+girlfriend.getAnniversaryMonth()+"."+girlfriend.getAnniversaryYear());

        //Hier erstellen wir einen String der das JahrestagsDatum beinhaltet.
        String anniversaryDate =  String.valueOf(girlfriend.getAnniversaryDay()) + "/"+ String.valueOf(girlfriend.getAnniversaryMonth()) + "/" +String.valueOf(this_year);


        //Hier lassen wir die Funktion daysleft die Anzahl der Tage bis zum nächsten Jahrestag ausrechnen.
        String daysleftAnniversary = daysleft(anniversaryDate);
        TextView daysleftAnniversaryfield = (TextView) view.findViewById(R.id.DaysToAnniversary);

        //Jetzt holen wir uns den Textview der am Ende die Anzahl der Tage zum Jahrestag noch anzeigen soll.

        int daystoAnniversary = Integer.parseInt(daysleftAnniversary);

        setColorandtext(daysleftAnniversaryfield,daystoAnniversary);


        //Gleicher ablauf für den Valentinstag nur das das Zieldatum hier statisch ist. (14.02.x)
        int year = this_year;
        TextView valTagview = (TextView) view.findViewById(R.id.ValentinesDate);
        String Valtagdatum = "14/02/"+ String.valueOf(year);

        //TextViewJahr gibt es nur weil das Datum das angezeigt wird und das Datum mit dem gerechnet wird, verschieden ist.
        int textViewJahr = year;
        //Falls das Datum schon gewesen ist, wird das Jahr einfahch um 1 erhöht um die Anzahl der Tage bis zum nächsten Jahr auszurechnen
        if(this_day>14 &&this_month>=2){
            textViewJahr++;
        }

        //Hier wird der Valentienstag angezeigz
        valTagview.setText(" 14.02."+String.valueOf(textViewJahr));
        String daysleftValtagstring = daysleft(Valtagdatum);
        TextView daysleftValtagtextview = (TextView) view.findViewById(R.id.DaysToValentines);
        daysleftValtagtextview.setText(String.valueOf(year));
        setColorandtext(daysleftValtagtextview,Integer.parseInt(daysleftValtagstring));

        TextView showfavband = (TextView) view.findViewById(R.id.favBandInputLabel);
        showfavband.setText(girlfriend.getBand());

        TextView showfavflowers = (TextView) view.findViewById(R.id.favFlowersInputLabel);
        showfavflowers.setText(girlfriend.getFlowers());

        TextView showfavFood = (TextView) view.findViewById(R.id.favFoodInputLabel);
        showfavFood.setText(girlfriend.getFood());


        return view;
    }

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //Diese Methode rechnet die Anzahl der Tage von heute (dem Aktuellen Datum -> CurrentDate) zum FinalDate aus -> Final Date ist der Geburtstag oder Jahrestag der übergeben wird.
    public String daysleft(String FinalDate){

        String CurrentDate = String.valueOf(this_day) + "/"+ String.valueOf(this_month) + "/" +String.valueOf(this_year);
        Date date1 = null;
        Date date2 = null;

        //Hier wird ein Datumsformat erstellt um das Datum in einer verständilchen Art und Weise berechnen zu können.
        SimpleDateFormat dates = new SimpleDateFormat("dd/MM/yyyy");

        //Jetzt wird der String auf das oben erstellte Datenformat geparst
        try {
            date1 = dates.parse(CurrentDate);


            date2 = dates.parse(FinalDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Mit get Time holt man sich die Millisekunden. Diese werden dann einfach subtrahiert
        long difference = Math.abs(date1.getTime() - date2.getTime());

        /*Jetzt muss man das ganze noch auf Tage umrechnen also von
          Millisec -> Sek -> Minuten -> Stunden -> Tage
         */

        long differenceDates = difference / (24 * 60 * 60 * 1000);
        if(date2.before(date1)){

            differenceDates = 365 - differenceDates;

        }

        //Am Ende übergeben wir es direkt als String sodass wir es nurnoch in das für die Ausgabe gewählte Art einsetzen müssen. (Toast, Textview etc.)
        return Long.toString(differenceDates);
    }


    //Diese Funktion sorgt dafür das die TextViews die die Anzahl der Tage zum B-Day oder Jahrestag anzeigen die richtige Farbe hat um den Nutzer zu warnen.
    public void setColorandtext(TextView field, int difference){

        if (difference <= 10){
            field.setText("In "+ difference + " Tagen");
            field.setBackgroundColor(Color.RED);
            field.setTextColor(Color.WHITE);

        }else if(difference == 1){
            field.setText("MORGEN!!!");
            field.setBackgroundColor(Color.RED);
            field.setTextColor(Color.WHITE);
        }else{
            field.setText("In "+ difference + " Tagen");
            field.setBackgroundColor(Color.GREEN);
            field.setTextColor(Color.BLACK);
        }
    }

}
