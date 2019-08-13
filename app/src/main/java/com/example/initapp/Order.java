package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.util.Log.i;
import static com.example.initapp.Home.resID;
import static com.example.initapp.SetUp.st_str_account;
import static com.example.initapp.SetUp.st_str_accountID;
import static com.example.initapp.SetUp.st_str_level;
import static com.example.initapp.SetUp.url_all_get;
import static com.example.initapp.SetUp.url_order;

import com.example.initapp.Custom_List.Cus_Order_list;
import  com.example.initapp.model.Order_List;
import com.example.initapp.model.User;


public class Order extends AppCompatActivity {
    String resid;
    RecyclerView recyclerView;
    public Context context;
    public RequestQueue requestQueue;
    public StringRequest stringRequest;
    ArrayList<Order_List>orders = new ArrayList<Order_List>();
    Cus_Order_list cus_order_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        read();
        recyclerView = findViewById(R.id.orderlist);


    }
    public void read(){
        get gett = new get();
        gett.execute(url_all_get+url_order);

    }


    public class get extends AsyncTask<String , Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            volley_get_user(strings[0]);
            return strings[0];
        }
    }

    public void volley_get_user(String urlll) {
        context = this;
        requestQueue = Volley.newRequestQueue(context);
        stringRequest = new StringRequest(urlll, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                i("responseforres", response);
                JsontoArray(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                i("responseforres", error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }
    private void JsontoArray(String json){
        try {
            JSONObject root = new JSONObject(json);
            JSONArray jsonArray = root.getJSONArray("Order_list");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);

                String id = j.getString("OrderID");
                String Order_Content = j.getString("Order_Content");
                String Quanity = j.getString("Quanity");
                String Price = j.getString("Price");
                String Order_RES_ID = j.getString("Order_RES_ID");
                String Order_RES_Name = j.getString("Order_RES_Name");
                String Order_userID = j.getString("Order_userID");
                String PaymentID = j.getString("PaymentID");
                String Finish = j.getString("Finish");
                String Order_Time = j.getString("Order_Time");

                if((Order_userID.equals(st_str_accountID))) {
                    Order_List order = new Order_List(id,Order_Content,Price,Order_RES_ID,Order_RES_Name,PaymentID,Finish,Order_Time,Quanity);
                    orders.add(order);
                }
            }
            cus_order_list = new Cus_Order_list(orders,Order.this);
            Log.i("sadf",String.valueOf(orders.size()));
            recyclerView.setAdapter(cus_order_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
