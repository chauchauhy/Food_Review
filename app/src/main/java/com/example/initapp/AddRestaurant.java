package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.initapp.Custom_List.Cus_Home_Res_List;
import com.example.initapp.model.Restaurant;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.util.Log.i;
import static com.example.initapp.SetUp.url_RES;
import static com.example.initapp.SetUp.url_all_get;

public class AddRestaurant extends AppCompatActivity {
    EditText ed_phone, ed_address, ed_time, ed_price, ed_otherinfo, ed_name;
    ImageButton addimage, post;
    String phone, address, time, price, resid;
    Spinner spinner;
    ConstraintLayout add_layout;
    public Context context;
    public StringRequest stringRequest;
    public RequestQueue requestQueue;
    public ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        getSupportActionBar().setTitle("Add Restaurant");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initui();
        read();

        post.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chkexist()) {
                    Snackbar.make(add_layout, "the resaurant is existed", Snackbar.LENGTH_SHORT).show();
                } else if (chk() == false && chkexist() == false) {
                    phone = ed_phone.getText().toString().trim();
                    address = ed_address.getText().toString().trim();
                    time = ed_time.getText().toString().trim();
                    price = ed_price.getText().toString().trim();
                    String detail = ed_otherinfo.getText().toString().trim();
                    String name = ed_name.getText().toString().trim();
                    String parmase = "Res_Name=" + name + "&" + "Res_Detail=" + detail + "&" + "Res_Type=" +"";
                    ///////////////////////
                    //////finish\\\/////
                    ///////////////////////

                } else {
                    Snackbar.make(add_layout, "please fill the form", Snackbar.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void initui() {
        ed_address = findViewById(R.id.editText14);
        ed_time = findViewById(R.id.editText16);
        ed_phone = findViewById(R.id.editText4);
        ed_price = findViewById(R.id.imageButton21);
        ed_otherinfo = findViewById(R.id.editText17);
        addimage = findViewById(R.id.imageButton22);
        post = findViewById(R.id.imageButton23);
        add_layout = findViewById(R.id.add_layout);
        ed_name = findViewById(R.id.name_res);
        spinner = findViewById(R.id.type);

        final String[] type = {"Thai", "TW", "Other"};
        ArrayAdapter<String> typelist = new ArrayAdapter<>(AddRestaurant.this,R.layout.support_simple_spinner_dropdown_item,type);
        spinner.setAdapter(typelist);


    }

    private Boolean chk() {
//        boolean chk = false;
        if (ed_name.getText().toString().trim().length() < 1 || ed_address.getText().toString().trim().length() < 1 || ed_price.getText().toString().trim().length() < 1 || ed_phone.getText().toString().length() < 1 || ed_time.getText().toString().length() < 1) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean chkexist() {
        boolean chk = false;
        for (int i = 0; i < restaurants.size(); i++) {
            if (ed_name.getText().toString().trim().equals(restaurants.get(i).getResName())) {
                chk = true;
                resid = restaurants.get(i).getResID();
                break;
            } else {
                chk = false;
            }
        }
        return chk;

    }

    private void read() {
        get gett = new get();
        gett.execute(url_all_get + url_RES);

    }

    private class get extends AsyncTask<String, Void, String> {
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

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String postHttpURLConnection(String urlStr, String postParams) {

        String response = "";

        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            OutputStream outputStream = urlConnection.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeBytes(postParams);

            dataOutputStream.flush();
            dataOutputStream.close();

            int responseCode = urlConnection.getResponseCode();

            if ((responseCode == HttpURLConnection.HTTP_OK)) {
                String line;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;

                }

            } else {
                response = "";


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }

}
