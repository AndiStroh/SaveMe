package com.example.claudius.saveme.Create_stuff;

import android.content.Context;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.claudius.saveme.Interfaces.ActivityCommunicator;
import com.example.claudius.saveme.Interfaces.OnFragmentInteractionListener;
import com.example.claudius.saveme.R;

//Fragment das einen Datepicker enthält welcher das einstellen des Datums des Jahrestags ermöglicht
public class create_3 extends android.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ActivityCommunicator activityCommunicator;

    Create_Activity cA = (Create_Activity) getActivity();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int anniversaryMonth;
    private int anniversaryDay;
    private int anniversaryYear;
    DatePicker anniversaryPicker;
    View view;

    private OnFragmentInteractionListener mListener;

    public create_3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment create_3.
     */
    // TODO: Rename and change types and number of parameters
    public static create_3 newInstance(String param1, String param2) {
        create_3 fragment = new create_3();
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
        view =inflater.inflate(R.layout.create_3_fragment, container, false);

        anniversaryPicker = (DatePicker) view.findViewById(R.id.anniversarypicker);


        //DatePicker wird initialisert mit dem heutigen Datum und die variablen + Listener werden zugeordnet.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        anniversaryYear = anniversaryPicker.getYear();
        anniversaryMonth = anniversaryPicker.getMonth();
        anniversaryDay = anniversaryPicker.getDayOfMonth();
        anniversaryPicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                anniversaryYear = anniversaryPicker.getYear();
                anniversaryMonth = anniversaryPicker.getMonth();
                anniversaryDay = anniversaryPicker.getDayOfMonth();
            }
        });
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

    public void onStop(){
        super.onStop();

        activityCommunicator.sendDates(anniversaryDay,anniversaryMonth,anniversaryYear,cA.getTypeAnniversary());
    }




}
