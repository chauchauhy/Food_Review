package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.initapp.model.Mlkit_post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static com.example.initapp.SetUp.url_all_get;

////////////////////////////////////////////////////////////////////////////////////
//////////////////TEST                                 ////////////////////////
////////////////////////////////////////////////////////////////////////////////////
public class Post_MLKIT extends AppCompatActivity {

    //Volley
    private StringRequest stringRequest;
    private Context context;
    private RequestQueue requestQueue;

    //ui
    TextView mainType1,mainType2;          //main type ui (TextView)
    TextView mainType1_SubType1,mainType1_SubType2,mainType1_SubType3,mainType1_SubType4;
    TextView mainType2_SubType1,mainType2_SubType2,mainType2_SubType3,mainType2_SubType4;
    LinearLayout m1,m2;
    RecyclerView recyclerView;

    //other
    ArrayList<Mlkit_post> mlkit_posts = new ArrayList<Mlkit_post>();
    ArrayList<Mlkit_post> mlkit_posts_content = new ArrayList<Mlkit_post>();
    ArrayList<Mlkit_post> mlkit_posts_type = new ArrayList<Mlkit_post>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post__mlkit);
        context = this;
        initui();
        read();


    }
    private void initui(){
        mainType1 = findViewById(R.id.maintype1);
        mainType2 = findViewById(R.id.maintype2);

        mainType1_SubType1 = findViewById(R.id.maintype1_sub1);
        mainType1_SubType2 = findViewById(R.id.maintype1_sub2);
        mainType1_SubType3 = findViewById(R.id.maintype1_sub3);
        mainType1_SubType4 = findViewById(R.id.maintype1_sub4);

        mainType2_SubType1 = findViewById(R.id.maintype2_sub1);
        mainType2_SubType2 = findViewById(R.id.maintype2_sub2);
        mainType2_SubType3 = findViewById(R.id.maintype2_sub3);
        mainType2_SubType4 = findViewById(R.id.maintype2_sub4);

        click();
    }

    private void click(){
        mainType1.setClickable(true);
        mainType1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void read(){
        get g = new get();
        g.execute(url_all_get+"test.php");
    }

    private class get extends AsyncTask<String,Void,String>{
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
            mlkit_postss.add( mlkit_posts.get(i).getImageType_MAIN());


        }
        HashSet set = new HashSet(mlkit_postss);
        mlkit_postss.clear();
        mlkit_postss.addAll(set);
        Log.i("showsize",String.valueOf(mlkit_postss.size()));
        if(mlkit_postss.size()>=2){
            mainType1.setText(mlkit_postss.get(0));
            mainType2.setText(mlkit_postss.get(1));
        }



    }






}
