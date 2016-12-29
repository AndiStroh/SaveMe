package com.example.claudius.saveme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Show_Activity extends AppCompatActivity {

    public static final int WEBVIEWTYPE = 6;

    Girlfriend girlfriend = new Girlfriend();
    Button backtoOverview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information);
    }
}
