package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.initapp.model.Restaurant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.initapp.SetUp.url_RES;
import static com.example.initapp.SetUp.url_all_get;

public class ShowRes extends AppCompatActivity {
    RecyclerView recyclerView;
    public Context context;
    public RequestQueue requestQueue;
    public StringRequest stringRequest;
    ArrayList<Restaurant>restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_res);
        Log.i("showaaa", "show");
        Log.i("showaaa", "show22");
        initui();
        read();


    }
    private void initui(){
        recyclerView = findViewById(R.id.recy_RES_LIST);
    }

    private void read(){
        String ur  = url_all_get+url_RES;
        g get = new g();
        get.execute(ur);
    }
    private class g extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            volley_get(strings[0]);
            return strings[0];
        }
    }

//    private void volley_get(String urll){
//        context = this;
//        requestQueue = Volley.newRequestQueue(context);
//        stringRequest = new StringRequest(urll, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.i("responseforres", response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("responseforres", error.toString());
//            }
//        });
//        requestQueue.add(stringRequest);
//    }
public void volley_get(String urlll){
    context = this;
    requestQueue = Volley.newRequestQueue(context);
    stringRequest = new StringRequest(urlll, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.i("responseforres", response);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
               Log.i("responseforres", error.toString());
        }
    });
    requestQueue.add(stringRequest);
}

    private void jsontoarr(String json){
        try {
            JSONObject root = new JSONObject(json);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
