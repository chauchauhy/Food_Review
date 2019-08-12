package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import static com.example.initapp.Home.resID;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent i = getIntent();
        String id = i.getStringExtra(resID);
        Log.i("Stringid","XXXXXXXXXXXXXXX"+ id);
    }
}
