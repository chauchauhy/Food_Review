package com.example.initapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.initapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.initapp.SetUp.url_User;
import static com.example.initapp.SetUp.url_all_get;

public class MainActivity extends AppCompatActivity {          //sign page
    EditText username, useraccount, userpassword, useremail;
    TextView war_name, war_ac, war_pw, war_email;
    Button send;
    LinearLayout linearLayout;
    static String st_str_account;
    static String st_str_accountID;
    static String st_str_1;
    public Context context;
    public StringRequest stringRequest;
    public RequestQueue requestQueue;
    ArrayList<User> users = new ArrayList<User>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initui(); //
        read();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chknull()) {
//                    if (doubChk()) {
                    startActivity(new Intent(MainActivity.this, ShowRes.class));
//                    }
                }


            }
        });
        send.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startActivity(new Intent(MainActivity.this, ShowRes.class));
                return true;
            }
        });
    }

    private void initui() {
        username = findViewById(R.id.Username);
        useraccount = findViewById(R.id.Useraccount);
        userpassword = findViewById(R.id.Userpassword);
        useremail = findViewById(R.id.Useremail);
        send = findViewById(R.id.send);
        linearLayout = findViewById(R.id.login_Linear);
        war_ac = findViewById(R.id.war_ac);
        war_email = findViewById(R.id.war_email);
        war_name = findViewById(R.id.war_name);
        war_pw = findViewById(R.id.war_pa);
    }

    private void read() {
        g get = new g();
        get.execute(url_all_get + url_User);
    }


    private void noitication() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful())
                    return;
                if (task.getResult() == null)
                    return;
                String token = task.getResult().getToken();
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

    private Boolean chknull() {

        Boolean boolean_result = false;
        String str_username = "username";
        String str_useraccount = "account";
        String str_userpassword = "password";
        String str_useremail = "Email";
        String warring = " is invaild";
        String wa = "Your informations are invaild";
        if (username.getText().toString().trim().length() < 3) {
            war_name.setVisibility(View.VISIBLE);
            boolean_result = false;
        }else {
            war_name.setVisibility(View.GONE);
        }
        if (useraccount.getText().toString().trim().length() < 5) {
            war_ac.setVisibility(View.VISIBLE);
            boolean_result = false;
        }else {
            war_ac.setVisibility(View.GONE);
        }
        if (userpassword.getText().toString().trim().length() < 5) {
            war_pw.setVisibility(View.VISIBLE);
            boolean_result = false;
        }else{
            war_pw.setVisibility(View.GONE);
        }
        if (!(useremail.getText().toString().trim().length() > 7 && useremail.getText().toString().trim().contains("@") && useremail.getText().toString().trim().contains(".com"))) {            //check email formal
            war_email.setVisibility(View.VISIBLE);
            boolean_result = false;
        }else {
            war_email.setVisibility(View.GONE);
        }
        if(username.getText().toString().trim().length() > 3 &&  useraccount.getText().toString().trim().length() > 5 &&  userpassword.getText().toString().trim().length() > 5 && useremail.getText().toString().trim().length() > 7
                && useremail.getText().toString().trim().contains("@") && useremail.getText().toString().trim().contains(".com") ){
            boolean_result = true;
        }

        return boolean_result;
    }

    private class g extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            volley_get(strings[0]);
            return strings[0];
        }
    }

    private void volley_get(String urll) {
        context = this;
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        Log.i("uuuu", urll);
        stringRequest = new StringRequest(urll, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JsontoArray(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("RESPOR", error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

    private void JsontoArray(String json) {
        try {
            JSONObject root = new JSONObject(json);
            JSONArray jsonArray = root.getJSONArray("user");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);

                String id = j.getString("Userid");
                String account = j.getString("User_Account");
                String User_Name = j.getString("User_Name");
                String password = j.getString("User_Password");
                String loginMethod = j.getString("User_Login_Method");
                String User_Email = j.getString("User_Email");
                String User_Sign_up_time = j.getString("User_Sign_up_time");
                String User_icon = j.getString("User_icon");
                String User_level = j.getString("User_level");

                User user = new User(id, User_Name, account, password, loginMethod, User_Email, User_Sign_up_time, User_icon, User_level);
                users.add(user);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Boolean doubChk() {
        boolean ck_res = false;
        for (int i = 0; i < users.size(); i++) {
            if ((username.getText().toString().trim() == users.get(i).getUserName())) {

            }
        }
        return ck_res;
    }

}
