package com.example.initapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {
    EditText username, useraccount , userpassword, useremail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initui();
    }
    private void initui(){
        username = findViewById(R.id.Username);
        useraccount = findViewById(R.id.Useraccount);
        userpassword = findViewById(R.id.Userpassword);
        useremail = findViewById(R.id.Useremail);
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

}
