package com.example.initapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.initapp.Custom_List.Cus_Dish_list;
import com.example.initapp.model.Dishes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.initapp.Home.resID;
import static com.example.initapp.SetUp.st_str_account;
import static com.example.initapp.SetUp.st_str_accountID;
import static com.example.initapp.SetUp.st_str_level;
import static com.example.initapp.SetUp.url_all_get;
import static com.example.initapp.SetUp.url_dishes;

public class Dish extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener{
    RecyclerView recyclerView;
    Cus_Dish_list cus_dish_list;
    ArrayList<Dishes> dishes = new ArrayList<Dishes>();
    Context context;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    String resid;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    FirebaseAnalytics analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);
        context = this;
        Intent i = getIntent();
        resid = i.getStringExtra(resID);
        Log.i("sadfgh", resid);

        initui();
        read();


    }

    private void initui() {
        analytics = FirebaseAnalytics.getInstance(context);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Dishes");
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        recyclerView = findViewById(R.id.dish_list);

        drawerLayout = findViewById(R.id.drawer_dish);
        bottomNavigationView = findViewById(R.id.btm_nav_bar_dish);
        navigationView = findViewById(R.id.Nav_home_dish);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.getMenu().findItem(R.id.nav_add).setEnabled(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(context,Home.class));
                        break;
                    case R.id.nav_profile:
                        if (st_str_account.length() > 4) {
                            startActivity(new Intent(context, Profolio.class));
                            break;
                        } else {
                            Toast.makeText(context, "You may be need login", Toast.LENGTH_SHORT).show();
                        }

                }
                return true;

            }

        });


        navigationView.getMenu().findItem(R.id.login).setVisible(true);

        if (st_str_account.length() < 1) {
            navigationView.getMenu().findItem(R.id.order).setVisible(false);

        } else {
            navigationView.getMenu().findItem(R.id.login).setVisible(false);
            navigationView.getMenu().findItem(R.id.logout).setVisible(true);

        }


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_social:
                startActivity(new Intent(context,Social.class));

                break;
            case R.id.homepage:
                startActivity(new Intent(context,Home.class));
                break;
            case R.id.signup:
                startActivity(new Intent(context, MainActivity.class));
                break;
            case R.id.login:
                startActivity(new Intent(context, login.class));
                break;

            case R.id.order:
                if(st_str_accountID.length()<1){
                    Toast.makeText(context,"You need login to use this function",Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(context, Order.class));
                }
                break;
            case R.id.logout:
                st_str_account = "";
                st_str_level = "GUEST";
                st_str_accountID = "";
                Toast.makeText(context,"Logout",Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                reload();

                break;

        }

        return true;
    }

    @Override
    public void finish() {
        super.finish();
    }

    public void reload() {
        Intent i = getIntent();
        finish();
        startActivity(i);
    }


    private void read() {
        get gett = new get();
        gett.execute(url_all_get + url_dishes);
        Log.i("sadfgh", url_all_get + url_dishes);
    }

    private class get extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            volley_getdishes(strings[0]);
            return strings[0];
        }
    }

    private void volley_getdishes(String urll) {
        context = this;
        requestQueue = Volley.newRequestQueue(context);
        stringRequest = new StringRequest(urll, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("checkdishes", response);
                jsontoarray_dishes(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("errormessagefo volley", error.toString());

            }
        });
        requestQueue.add(stringRequest);
    }
    private void jsontoarray_dishes(String json){
        try {
            JSONObject root = new JSONObject(json);
            JSONArray jsonArray = root.getJSONArray("dishes");
            for (int i = 0 ; i<jsonArray.length();i++){
                JSONObject j = jsonArray.getJSONObject(i);

                String id = j.getString("Dishes_ID");
                String Dishes_Name = j.getString("Dishes_Name");
                String Image = j.getString("Image");
                String Dishes_RES_ID = j.getString("Dishes_RES_ID");
                String price = j.getString("price");

                if(Dishes_RES_ID.equals(resid)){
                    Dishes dishes = new Dishes(id,Dishes_Name,Image,Dishes_RES_ID,price);
                    this.dishes.add(dishes);
                }
                cus_dish_list = new Cus_Dish_list(dishes, Dish.this);
                recyclerView.setAdapter(cus_dish_list);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    }



