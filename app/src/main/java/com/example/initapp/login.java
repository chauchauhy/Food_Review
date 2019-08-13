package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.initapp.Custom_List.Cus_Res_comment_list;
import com.example.initapp.model.User;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.util.Log.i;
import static com.example.initapp.Home.resID;
import static com.example.initapp.SetUp.url_User;
import static com.example.initapp.SetUp.url_all_get;
import static com.example.initapp.SetUp.st_str_account;
import static com.example.initapp.SetUp.st_str_accountID;
import static com.example.initapp.SetUp.st_str_level;


public class login extends AppCompatActivity {


    EditText Ed_username, userpassword;
    ImageButton login, signup, facebook, google;
    ConstraintLayout layout;
    static String userid;
    static String username;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private Context context;
    ArrayList<User> users = new ArrayList<User>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initui();
        read();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chk(Ed_username.getText().toString().trim(), userpassword.getText().toString().trim())) {
                    startActivity(new Intent(login.this, Home.class));
                }else {
                    Snackbar.make(layout, "the account or email is not existed", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,MainActivity.class));
            }
        });


    }

    private void initui() {
        Ed_username = findViewById(R.id.username_login);
        userpassword = findViewById(R.id.password_login);
        login = findViewById(R.id.login_login);
        signup = findViewById(R.id.signup_login);
        facebook = findViewById(R.id.facebook);
        google = findViewById(R.id.google);
        layout = findViewById(R.id.layout);
    }

    private void read() {
        get gett = new get();
        gett.execute(url_all_get + url_User);

    }


    private class get extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            volley_get_user(strings[0]);
            return strings[0];
        }
    }

    private void volley_get_user(String urlll) {
        context = this;
        requestQueue = Volley.newRequestQueue(context);
        stringRequest = new StringRequest(urlll, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                i("re_user", response);
                jsontoarr(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                i("responseforres", error.toString());
            }
        });
        requestQueue.add(stringRequest);

    }

    private void jsontoarr(String json) {
        i("jsona", json);
        try {
            JSONObject root = new JSONObject(json);
            JSONArray jsonArray = root.getJSONArray("user");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);
                String id = j.getString("Userid");
                String User_Name = j.getString("User_Name");
                String User_Account = j.getString("User_Account");
                String User_Password = j.getString("User_Password");
                String User_Email = j.getString("User_Email");
                String user_Email = User_Email.trim();
                String User_level = j.getString("User_level");
                String User_icon = j.getString("User_icon");
                String User_Login_Method = j.getString("User_Login_Method");
                String User_Sign_up_time = j.getString("User_Sign_up_time");

                User user = new User(id, User_Name, User_Account, User_Password, User_Login_Method, user_Email, User_Sign_up_time, User_icon, User_level);
                users.add(user);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Boolean chk(String username, String password) {
        boolean chk = false;
        for (int i = 0; i < users.size(); i++) {
            Log.i("sadsa", users.get(i).getUserEmail());
            if ((username.equals(users.get(i).getUserAccount()) || username.equals(users.get(i).getUserEmail()) || username.equals(users.get(i).getUserName())) && password.equals(users.get(i).getUserAccount())) {
                Log.i("sadsa", users.get(i).getUserEmail());
                st_str_account = users.get(i).getUserAccount();
                chk = true;
            }
        }
        if (chk == true) {
            Snackbar.make(layout, "the account or email is not existed", Snackbar.LENGTH_SHORT).show();
        }
        Log.i("ccccccc", String.valueOf(chk));
        return chk;
    }

}
