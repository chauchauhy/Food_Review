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
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.initapp.Custom_List.Cus_Home_Res_List;
import com.example.initapp.model.Restaurant;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.util.Log.i;
import static com.example.initapp.SetUp.url_RES;
import static com.example.initapp.SetUp.url_all_get;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Cus_Home_Res_List.OnItemClickListener {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;
    RecyclerView recyclerView;
    public Context context;
    public RequestQueue requestQueue;
    public StringRequest stringRequest;
    static String resID = "RESID";
    private Cus_Home_Res_List cus_home_res_list;
    public ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
    public ArrayList<Restaurant> clone_res = new ArrayList<Restaurant>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        read();
        initui();



    }

    private void read() {
        String ur = url_all_get + url_RES;
        g get = new g();
        get.execute(ur);
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(Home.this, Restaurant_info.class);
        Log.i("sssssss",String.valueOf(restaurants.size()));
        Log.i("sssssss",String.valueOf(clone_res.size()));
        i.putExtra(resID,clone_res.get(position).getResID());
        startActivity(i);
    }

    private class g extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            volley_get(strings[0]);
            return strings[0];
        }

    }

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

                Restaurant restaurant = new Restaurant(id, Res_Name, Res_Detail, Res_Image, Res_Image2, Res_Image3, Res_Like, Res_Mark, Res_Type);
                Log.i("restau", restaurant.toString());
                this.restaurants.add(restaurant);
            }
            clone_res = restaurants;
            cus_home_res_list = new Cus_Home_Res_List(Home.this,restaurants);
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setAdapter(cus_home_res_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            cus_home_res_list.setOnItemClickListener(Home.this);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void initui(){
        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        navigationView = findViewById(R.id.Nav_home);
        mDrawerlayout.addDrawerListener(mToggle);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



//        cus_home_res_list = new Cus_Home_Res_List(Home.this,restaurants);
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setAdapter(cus_home_res_list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.signup:
                startActivity(new Intent(Home.this, MainActivity.class));
                break;

        }

        return true;
    }

}
