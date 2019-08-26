package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import static com.example.initapp.Home.resID;

public class Comment_res extends AppCompatActivity {
    String RES_ID;
    String CMID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_res);
        Intent i = getIntent();
        RES_ID = i.getStringExtra(resID);
        CMID = i.getStringExtra("CMID");

        // ( "XXXXXXXXX", RES_ID + "xxxxxxx"+ CMID);
    }


}
