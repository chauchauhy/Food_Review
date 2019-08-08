package com.example.initapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {          //login page
    EditText username, useraccount , userpassword, useremail;
    Button send;
    LinearLayout linearLayout;
    static String st_str_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initui();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chknull();
            }
        });

    }
    private void initui(){
        username = findViewById(R.id.Username);
        useraccount = findViewById(R.id.Useraccount);
        userpassword = findViewById(R.id.Userpassword);
        useremail = findViewById(R.id.Useremail);
        send = findViewById(R.id.send);
        linearLayout = findViewById(R.id.login_Linear);
    }

    private void noitication(){
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                    if(! task.isSuccessful())
                        return;
                    if(task.getResult()==null)
                        return;
                    String token =task.getResult().getToken();
                Log.i("TokenMain:", token);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    String channelId = "default_notification_channel_id";
                    String channelName = "default_notification_channel_name";
                    NotificationManager notificationManager =
                            getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW));
                }
            }
        });
    }

    private void chknull(){
        initui();
        Boolean boolean_result = false;
        String str_username = "username";
        String str_useraccount = "account";
        String str_userpassword = "password";
        String str_useremail = "Email";
        String warring = " is invaild";
        if(username.getText().toString().trim().equals("")) {
            Snackbar.make(linearLayout, str_username + warring, Snackbar.LENGTH_SHORT).show();
        if(useraccount.getText().toString().trim().length()<5){
            Snackbar.make(linearLayout, str_useraccount + warring, Snackbar.LENGTH_SHORT).show();
        if (userpassword.getText().toString().trim().length()<5){
            Snackbar.make(linearLayout, str_userpassword+ warring, Snackbar.LENGTH_SHORT).show();
        if(!(useremail.getText().toString().trim().length()<7 && useremail.getText().toString().trim().contains("@")&&useremail.getText().toString().trim().contains(".com"))) {
            Snackbar.make(linearLayout, str_useremail + warring, Snackbar.LENGTH_SHORT).show();
        }}}}else {
            Snackbar.make(linearLayout,"right!", Snackbar.LENGTH_SHORT).show();
        }
//        return false;
    }


}
