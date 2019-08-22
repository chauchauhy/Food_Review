package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.zip.InflaterInputStream;

import static com.example.initapp.Home.firebaseAnalytics;

public class Social extends AppCompatActivity implements View.OnClickListener{
    ImageButton pre_1month, pre_3month, pre_1year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        initui();
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);


    }

    private void initui() {
        pre_1month = findViewById(R.id.premiun1);
        pre_3month = findViewById(R.id.premiun2);
        pre_1year = findViewById(R.id.premiun3);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.premiun1:
                Bundle bundle1 = new Bundle();
                bundle1.putLong("purchase1month", System.currentTimeMillis());
                bundle1.putString("key","Value");
                firebaseAnalytics.logEvent("purchase_premium_1month",bundle1);
                break;
            case R.id.premiun2:
                Bundle bundle2 = new Bundle();
                bundle2.putLong("purchase3months", System.currentTimeMillis());
                bundle2.putString("key","Value");
                firebaseAnalytics.logEvent("purchase_premium_3months",bundle2);
                break;
            case R.id.premiun3:
                Bundle bundle3 = new Bundle();
                bundle3.putLong("purchase1year", System.currentTimeMillis());
                bundle3.putString("key","Value");
                firebaseAnalytics.logEvent("purchase_premium_1year",bundle3);
                break;

        }
    }
}
