package com.example.claudius.saveme.Create_stuff;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.claudius.saveme.Interfaces.ActivityCommunicator;
import com.example.claudius.saveme.Interfaces.OnFragmentInteractionListener;
import com.example.claudius.saveme.R;


//Fragment das die Felder für Benutzernamen und Passwort des Benutzers enthält
public class create_0 extends android.app.Fragment implements OnFragmentInteractionListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String username;
    private String password;
    private View view;

    private ActivityCommunicator activityCommunicator;
    private OnFragmentInteractionListener mListener;

    public create_0() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment create_0.
     */
    // TODO: Rename and change types and number of parameters
    public static create_0 newInstance(String param1, String param2) {
        create_0 fragment = new create_0();
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


    //Hier werden den Feldern Listenern verpasst und gesagt was diese machen sollen.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_0_fragment, container, false);


        final EditText usernameEditText = (EditText) view.findViewById(R.id.createUsernameField);
        final EditText passwordEditText = (EditText) view.findViewById(R.id.createPasswordfield);

        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                username = usernameEditText.getText().toString();
            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                password = passwordEditText.getText().toString();
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

    //Sobald sich das Fragment schliesst, werden die Daten an die Create Activity gesendet.

    public void onStop(){
        super.onStop();
        activityCommunicator.passStrings(username+"/"+password,Create_Activity.TypePassword);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
