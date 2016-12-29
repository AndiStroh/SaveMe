package com.example.claudius.saveme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.FileNotFoundException;

public class create_1 extends android.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Create_Activity cA = (Create_Activity) getActivity();


    private ActivityCommunicator activityCommunicator;
    ImageButton imgbutton;
    Bitmap profilepic;
    Girlfriend thegirlfriend;
    EditText preNameTextBox;
    String prename;
    View view;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private OnFragmentInteractionListener mListener;

    public create_1() {
        // Required empty public constructor
        imgbutton = null;

    }

    // TODO: Rename and change types and number of parameters
    public static create_1 newInstance(String param1, String param2) {
        create_1 fragment = new create_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);


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
        view = inflater.inflate(R.layout.create_1_fragment, container, false);

        imgbutton = (ImageButton) view.findViewById(R.id.picbutton);
        final EditText edt =  (EditText) view.findViewById(R.id.NameGirlfriendEditText);
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                prename = edt.getText().toString();
            }
        });


        imgbutton.setOnClickListener(new ImageButton.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK){
            Uri targetUri = data.getData();

            try {
                profilepic = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(targetUri));
                imgbutton.setImageBitmap(profilepic);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);

            uri.toString();
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
    public void onResume(){
        super.onResume();
        final EditText edt =  (EditText) view.findViewById(R.id.NameGirlfriendEditText);
        prename = edt.getText().toString();
    }
    @Override
    public void onStop(){
        super.onStop();
        activityCommunicator.passStrings(prename,cA.getTypeUserName());
    }




}