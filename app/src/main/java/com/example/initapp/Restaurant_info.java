package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.initapp.model.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.util.Log.i;
import static com.example.initapp.SetUp.url_RES;
import static com.example.initapp.SetUp.url_all_get;

public class Restaurant_info extends AppCompatActivity {
    ImageView star1,star2,star3,star4,star5,orderImage,Res_ImageView;
    TextView Res_des,Res_Con,Res_Name,Res_info,Res_comment;
    ImageButton Image_Like;
    RecyclerView recyclerView;
    private StringRequest stringRequest;
    private Context context;
    private RequestQueue requestQueue;
    ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_info);
        initui();
        read();


    }
    private void initui(){
        star1 = findViewById(R.id.imageView7);
        star2 = findViewById(R.id.imageView9);
        star3 = findViewById(R.id.imageView10);
        star4 = findViewById(R.id.imageView11);
        star5 = findViewById(R.id.imageView6);

        orderImage = findViewById(R.id.imageView4);
        Res_ImageView = findViewById(R.id.imageView3);

        Res_des = findViewById(R.id.textView5);
        Res_Name = findViewById(R.id.textView7);
        Res_Con = findViewById(R.id.textView6);
        Res_info = findViewById(R.id.textView8);
        Res_comment = findViewById(R.id.textView9);

        Image_Like = findViewById(R.id.imageButton14);
        recyclerView = findViewById(R.id.Res_info_recy);

    }

    private void read(){
        get get1 = new get();
        get1.execute(url_all_get+url_RES);
    }

    private class get extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            volley_get(strings[0]);
            return strings[0];
        }
    }

    public void volley_get(String urlll){
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

    private void jsontoarr(String json){
        i("jsona",json);
        try {
            JSONObject root = new JSONObject(json);
            JSONArray jsonArray =root.getJSONArray("restaurant");

            for(int i = 0 ; i< jsonArray.length();i++){
                JSONObject j = jsonArray.getJSONObject(i);

                String  id = j.getString("Res_ID");
                String  Res_Name = j.getString("Res_Name");
                String  Res_Detail = j.getString("Res_Detail");
                String  Res_Image = j.getString("Res_Image");
                String  Res_Image2 = j.getString("Res_Image2");
                String  Res_Image3 = j.getString("Res_Image3");
                String  Res_Like = j.getString("Res_Like");
                String  Res_Mark = j.getString("Res_Mark");
                String  Res_Type = j.getString("Res_Type");

                Restaurant restaurant = new Restaurant(id,Res_Name,Res_Detail,Res_Image,Res_Image2,Res_Image3,Res_Like,Res_Mark,Res_Type);
                Log.i("restau",restaurant.toString());
                restaurants.add(restaurant);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
