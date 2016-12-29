package com.example.claudius.saveme;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class create_4 extends android.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ActivityCommunicator activityCommunicator;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;

    Create_Activity cA = (Create_Activity) getActivity();

    Button favouriteColorRedButton;
    Button favouriteColorGreenButton;
    Button favouriteColorBlueButton;
    Button favouriteColorYellowButton;
    String favouriteBand;
    String favouriteflowers;
    String favouriteKindOfFood;

    EditText favBand;
    EditText favFood;
    EditText favFlowers;

    private OnFragmentInteractionListener mListener;

    public create_4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment create_4.
     */
    // TODO: Rename and change types and number of parameters
    public static create_4 newInstance(String param1, String param2) {
        create_4 fragment = new create_4();
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

        view = inflater.inflate(R.layout.create_4_fragment, container, false);

        favouriteColorRedButton = (Button) view.findViewById(R.id.favouriteColorRedButton);
        favouriteColorRedButton.setOnClickListener(new ButtonColorListener(this));


        favouriteColorGreenButton = (Button) view.findViewById(R.id.favouriteColorGreenButton);
        favouriteColorGreenButton.setOnClickListener(new ButtonColorListener(this));

        favouriteColorBlueButton = (Button) view.findViewById(R.id.favouriteColorBlueButton);
        favouriteColorBlueButton.setOnClickListener(new ButtonColorListener(this));

        favouriteColorYellowButton = (Button) view.findViewById(R.id.favouriteColorYellowButton);
        favouriteColorYellowButton.setOnClickListener(new ButtonColorListener(this));

        favBand = (EditText) view.findViewById(R.id.favouriteBandEditText);
        favouriteBand = favBand.getText().toString();

        favFood = (EditText) view.findViewById(R.id.favouriteKindOfFoodEditText);
        favouriteKindOfFood = favFood.getText().toString();

        favFlowers = (EditText) view.findViewById(R.id.favouriteFlowerEditText);
        favouriteflowers = favFlowers.getText().toString();

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


        context = getActivity();
        activityCommunicator =(ActivityCommunicator)context;
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onStop(){
        super.onStop();
       // activityCommunicator.passStrings(prename,TypePreName);

        String sendestring;

        favouriteBand = favBand.getText().toString();
        favouriteKindOfFood = favFood.getText().toString();
        favouriteflowers = favFlowers.getText().toString();



        sendestring = favouriteBand + "/" + favouriteflowers + "--" + favouriteKindOfFood;

        activityCommunicator.passStrings(sendestring,cA.getTypefavourites());
    }




}
