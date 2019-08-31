package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.initapp.Custom_List.Cus_MLKIT_POST;
import com.example.initapp.model.Mlkit_post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

import static com.example.initapp.MLKIT.mlkit_maintype;
import static com.example.initapp.SetUp.url_all_get;

public class mlkit_post extends AppCompatActivity {
    RecyclerView recyclerView;
    private StringRequest stringRequest;
    private Context context;
    private RequestQueue requestQueue;

    ArrayList<Mlkit_post> mlkit_posts = new ArrayList<Mlkit_post>();
    ArrayList<Mlkit_post> mlkit_posts_content = new ArrayList<Mlkit_post>();
    ArrayList<Mlkit_post> mlkit_posts_type = new ArrayList<Mlkit_post>();

    Cus_MLKIT_POST cus_mlkit_post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mlkit_post);
        context = this;
        recyclerView = findViewById(R.id.post_mlkit);
        Log.i("showsize2",mlkit_maintype);
        read();


    }


    private void read(){
        get g = new get();
        g.execute(url_all_get+"test.php");
    }

    private class get extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            volley_get(strings[0]);
            return strings[0];
        }
    }

    private void volley_get(String urll){
        requestQueue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.GET, urll, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsontoarray(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void jsontoarray(String json){
        Log.i("jsona", json);
        try {
            JSONObject root = new JSONObject(json);
            JSONArray jsonArray = root.getJSONArray("test");
            for(int i =0; i <jsonArray.length();i++){
                JSONObject j = jsonArray.getJSONObject(i);
                String ID = j.getString("ID");
                String ImageName = j.getString("ImageName");
                String ImageType1 = j.getString("ImageType1");
                String ImageType2 = j.getString("ImageType2");
                String ImageType3 = j.getString("ImageType3");
                String ImageType4 = j.getString("ImageType4");
                String ImageType5 = j.getString("ImageType5");

                Mlkit_post mlkit_post = new Mlkit_post(ID,ImageName,ImageType1,ImageType2,ImageType3,ImageType4,ImageType5);
                mlkit_posts.add(mlkit_post);

            }
            clone_Arraylist();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void clone_Arraylist(){
        Log.i("showsize",String.valueOf(mlkit_posts.size()));
        ArrayList<String> mlkit_postss = new ArrayList<String>();
        for(int i = 0; i<mlkit_posts.size();i++) {
            if ((mlkit_maintype.equals(mlkit_posts.get(i).getImageType_MAIN())&& !mlkit_posts.get(i).getImageType_MAIN().equals("unknown"))||
                    (mlkit_maintype.equals(mlkit_posts.get(i).getImageType_SUB1())&& !mlkit_posts.get(i).getImageType_SUB1().equals("unknown"))||
                    (mlkit_maintype.equals(mlkit_posts.get(i).getImageType_SUB2())&& !mlkit_posts.get(i).getImageType_SUB2().equals("unknown"))||
                            (mlkit_maintype.equals(mlkit_posts.get(i).getImageType_SUB3())&& !mlkit_posts.get(i).getImageType_SUB3().equals("unknown"))||
                                    (mlkit_maintype.equals(mlkit_posts.get(i).getImageType_SUB4())&& !mlkit_posts.get(i).getImageType_SUB4().equals("unknown"))) {

                Mlkit_post mlkit_post = new Mlkit_post(mlkit_posts.get(i).getPost_ID(), mlkit_posts.get(i).getImagePath(), mlkit_posts.get(i).getImageType_MAIN(), mlkit_posts.get(i).getImageType_SUB1(), mlkit_posts.get(i).getImageType_SUB2(), mlkit_posts.get(i).getImageType_SUB3(), mlkit_posts.get(i).getImageType_SUB4());
                mlkit_posts_content.add(mlkit_post);
            }
        }
        Log.i("showsize",String.valueOf(mlkit_posts_content.size()));
        recyclerView = findViewById(R.id.post_mlkit);
        cus_mlkit_post = new Cus_MLKIT_POST(mlkit_posts_content,context);
        recyclerView.setAdapter(cus_mlkit_post);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));






    }






}

