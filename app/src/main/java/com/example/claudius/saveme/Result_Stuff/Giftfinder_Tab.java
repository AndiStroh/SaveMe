package com.example.claudius.saveme.Result_Stuff;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.claudius.saveme.Create_stuff.create_2;
import com.example.claudius.saveme.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Giftfinder_Tab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Giftfinder_Tab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Giftfinder_Tab extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public Giftfinder_Tab() {
        // Required empty public constructor
    }

    public static final int catMusic = 0;
    public static final int catFood = 1;
    public static final int catClothing = 2;
    public static final int catFlowers = 3;
    protected int radioCategory = catMusic;



    Button amazon;
    Button zalando;
    Button yelp;
    Button eventim;
    Button hundm;
    Button blumenfee;

    private String selectedcompany = "amazon";

    Button_Webview_Listener button_webview_listener;



    // TODO: Rename and change types and number of parameters
    public static Giftfinder_Tab newInstance(String param1, String param2) {
        Giftfinder_Tab fragment = new Giftfinder_Tab();
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


        View view = inflater.inflate(R.layout.giftfinder_tab_fragment, container, false);


        //RadioGroups sind dafür da das nur 1 RadioButton einer Gruppe ausgewählt werden kann. Ausßerdem machen sie die Sache mit dem Listener erheblich leichter
        RadioGroup catGroup = (RadioGroup) view.findViewById(R.id.radiocatGroup);
        catGroup.check(R.id.radioMusic);

        catGroup.setOnCheckedChangeListener(new RadioListener(this));


        button_webview_listener = new Button_Webview_Listener(this);

        amazon = (Button) view.findViewById(R.id.companyAmazon);
        amazon.setOnClickListener(button_webview_listener);


        zalando = (Button) view.findViewById(R.id.companyZalando);
        setupButton(zalando);

        yelp = (Button) view.findViewById(R.id.companyYelp);
        setupButton(yelp);

        eventim = (Button) view.findViewById(R.id.companyEventim);
        eventim.setOnClickListener(button_webview_listener);

        hundm = (Button) view.findViewById(R.id.companyHundM);
        setupButton(hundm);

        blumenfee = (Button) view.findViewById(R.id.companyBlumenfeeDe);
        setupButton(blumenfee);



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

    public void setupButton(Button button){
        float notselectable = (float)0.2;
        button.setClickable(false);
        button.setAlpha(notselectable);
        button.setOnClickListener(button_webview_listener);
    }

    public String getSelectedcompany() {
        return selectedcompany;
    }

    public void setSelectedcompany(String selectedcompany) {
        this.selectedcompany = selectedcompany;
    }


}
