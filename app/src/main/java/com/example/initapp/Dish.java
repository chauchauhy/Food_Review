package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.initapp.Custom_List.Cus_Dish_list;
import com.example.initapp.model.Dishes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.initapp.Home.resID;
import static com.example.initapp.SetUp.url_all_get;
import static com.example.initapp.SetUp.url_dishes;

public class Dish extends AppCompatActivity {
    RecyclerView recyclerView;
    Cus_Dish_list cus_dish_list;
    ArrayList<Dishes> dishes = new ArrayList<Dishes>();
    Context context;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    String resid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);
        Intent i = getIntent();
        resid = i.getStringExtra(resID);
        Log.i("sadfgh", resid);

        initui();
        read();


    }

    private void initui() {
        getSupportActionBar().setTitle("Dish And Order XD");
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        recyclerView = findViewById(R.id.dish_list);
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
