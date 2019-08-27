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
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.initapp.Custom_List.Cus_Home_Res_List;
import com.example.initapp.model.Restaurant;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.util.Log.i;
import static com.example.initapp.Home.resID;
import static com.example.initapp.SetUp.st_str_account;
import static com.example.initapp.SetUp.st_str_accountID;
import static com.example.initapp.SetUp.st_str_level;
import static com.example.initapp.SetUp.url_RES;
import static com.example.initapp.SetUp.url_all_get;

public class ResType extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,Cus_Home_Res_List.OnItemClickListener {
    String type;
    Context context;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    FirebaseAnalytics analytics;
    RecyclerView recyclerView;
    ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
    StringRequest stringRequest;
    RequestQueue requestQueue;
    Cus_Home_Res_List cus_home_res_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_type);
        context = this;
        Intent i = getIntent();
        type = i.getStringExtra("TYPE");
        initui();
        read();


    }

    private void initui() {
        analytics = FirebaseAnalytics.getInstance(context);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(type);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        recyclerView = findViewById(R.id.recyclerView_restype);

        drawerLayout = findViewById(R.id.drawer_restype);
        bottomNavigationView = findViewById(R.id.btm_nav_bar_resType);
        navigationView = findViewById(R.id.Nav_home_res_type);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        bottomNavigationView.getMenu().findItem(R.id.nav_add).setEnabled(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(context, Home.class));
                        break;
                    case R.id.nav_profile:
                        if (st_str_account.length() > 4) {
                            startActivity(new Intent(context, Profolio.class));
                            break;
                        } else {
                            Toast.makeText(context, "You may be need login", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.nav_add:
                        if (st_str_level.equals("admin")||st_str_level.equals("super member")) {
                            startActivity(new Intent(ResType.this, AddRestaurant.class));
                        } else {
                            Toast.makeText(ResType.this, "You can not use this function", Toast.LENGTH_SHORT).show();
                        }
                        break;

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

    private void read (){
        get t = new get();
        t.execute(url_all_get+url_RES);
    }
    private class get extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            volley_get(strings[0]);
            return strings[0];
        }
    }
// bottomNavigationView = findViewById(R.id.btm_nav_bar_resType);
//        navigationView = findViewById(R.id.Nav_home_res_type);
//        recyclerView = findViewById(R.id.recyclerView_restype);
//        drawerLayout = findViewById(R.id.drawer_restype);
//        analytics = FirebaseAnalytics.getInstance(context);
//
//        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
//        toggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        navigationView.setNavigationItemSelectedListener(this);
//        bottomNavigationView.getMenu().findItem(R.id.nav_add).setEnabled(false);
//        bottomNavigationView.getMenu().findItem(R.id.nav_profile).setEnabled(false);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.nav_home:
//                        startActivity(new Intent(context, Home.class));
//                        break;
//                    case R.id.nav_profile:
//                        if (st_str_account.length() > 4) {
//                            startActivity(new Intent(context, Profolio.class));
//                            break;
//                        } else {
//                            Toast.makeText(context, "You may be need login", Toast.LENGTH_SHORT).show();
//                        }
//
//                }
//                return true;
//
//            }
//
//        });
//        navigationView.getMenu().findItem(R.id.login).setVisible(true);
//
//        if (st_str_account.length() < 1) {
//            navigationView.getMenu().findItem(R.id.order).setVisible(false);
//
//        } else {
//            navigationView.getMenu().findItem(R.id.login).setVisible(false);
//            navigationView.getMenu().findItem(R.id.logout).setVisible(true);
//
//        }

    public void volley_get(String urlll) {
        context = this;
        requestQueue = Volley.newRequestQueue(context);
        stringRequest = new StringRequest(urlll, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                i("responseforres", response);
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
            JSONArray jsonArray = root.getJSONArray("restaurant");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);

                String id = j.getString("Res_ID");
                String Res_Name = j.getString("Res_Name");
                String Res_Detail = j.getString("Res_Detail");
                String Res_Image = j.getString("Res_Image");
                String Res_Image2 = j.getString("Res_Image2");
                String Res_Image3 = j.getString("Res_Image3");
                String Res_Like = j.getString("Res_Like");
                String Res_Mark = j.getString("Res_Mark");
                String Res_Type = j.getString("Res_Type");

                if(Res_Type.equals(type)) {
                    Restaurant restaurant = new Restaurant(id, Res_Name, Res_Detail, Res_Image, Res_Image2, Res_Image3, Res_Like, Res_Mark, Res_Type);

                // ("restau", restaurant.toString());
                this.restaurants.add(restaurant);
            }
            }
            cus_home_res_list = new Cus_Home_Res_List(ResType.this, restaurants);
            recyclerView = findViewById(R.id.recyclerView_restype);
            recyclerView.setAdapter(cus_home_res_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            cus_home_res_list.setOnItemClickListener(ResType.this);

        } catch (JSONException e) {
            e.printStackTrace();
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
                startActivity(new Intent(context, Social.class));

                break;
            case R.id.homepage:
                startActivity(new Intent(context, Home.class));
                break;
            case R.id.signup:
                startActivity(new Intent(context, MainActivity.class));
                break;
            case R.id.login:
                startActivity(new Intent(context, login.class));
                break;

            case R.id.order:
                if (st_str_accountID.length() < 1) {
                    Toast.makeText(context, "You need login to use this function", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(context, Order.class));
                }
                break;
            case R.id.logout:
                st_str_account = "";
                st_str_level = "GUEST";
                st_str_accountID = "";
                Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(ResType.this, Restaurant_info.class);
        // ("sssssss", String.valueOf(restaurants.size()));
        // ("sssssss", String.valueOf(clone_res.size()));
        i.putExtra(resID, restaurants.get(position).getResID());
        startActivity(i);
    }
}

