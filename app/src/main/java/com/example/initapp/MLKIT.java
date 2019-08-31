package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.HashSet;

import static com.example.initapp.SetUp.url_all_get;

public class MLKIT extends AppCompatActivity {
    private StringRequest stringRequest;
    private Context context;
    private RequestQueue requestQueue;

    ArrayList<Mlkit_post> mlkit_posts = new ArrayList<Mlkit_post>();
    ArrayList<Mlkit_post> mlkit_posts_content = new ArrayList<Mlkit_post>();
    ArrayList<Mlkit_post> mlkit_posts_type = new ArrayList<Mlkit_post>();

    Button clothes, vehicle, pet, room, paper, tableware, food, juice;
    TextView t1, t2, t3, t4, t5, t6, t7, t8;

    static String mlkit_maintype = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mlkit);
        initui();
        read();

    }

    private void initui() {
        clothes = findViewById(R.id.buttonClothes);
        vehicle = findViewById(R.id.buttonVehicle);
        pet = findViewById(R.id.buttonPet);
        room = findViewById(R.id.buttonRoom);
        paper = findViewById(R.id.buttonPaper);
        tableware = findViewById(R.id.buttonTableware);
        food = findViewById(R.id.buttonFood);
        juice = findViewById(R.id.buttonJuice);
        context = this;
        t1 = findViewById(R.id.type1);
        t2 = findViewById(R.id.type2);
        t3 = findViewById(R.id.type3);
        t4 = findViewById(R.id.type4);
        t5 = findViewById(R.id.type5);
        t6 = findViewById(R.id.type6);
        t7 = findViewById(R.id.type7);
        t8 = findViewById(R.id.type8);

        click();
    }

    private void click() {
        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t1.getText().toString().trim() != null) {
                    String type = t1.getText().toString().trim();
                    mlkit_maintype = type;

                    startActivity(new Intent(MLKIT.this, mlkit_post.class));
                }            }
        });
        vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t4.getText().toString().trim() != null) {
                    String type = t4.getText().toString().trim();
                    mlkit_maintype = type;

                    startActivity(new Intent(MLKIT.this, mlkit_post.class));
                }            }
        });
        pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t3.getText().toString().trim() != null) {
                    String type = t3.getText().toString().trim();
                    mlkit_maintype = type;

                    startActivity(new Intent(MLKIT.this, mlkit_post.class));
                }
            }
        });
        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t2.getText().toString().trim() != null) {
                    String type = t2.getText().toString().trim();
                    mlkit_maintype = type;

                    startActivity(new Intent(MLKIT.this, mlkit_post.class));
                }
            }
        });
        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t5.getText().toString().trim() != null) {
                    String type = t5.getText().toString().trim();
                    mlkit_maintype = type;

                    startActivity(new Intent(MLKIT.this, mlkit_post.class));
                }
            }
        });
        tableware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t6.getText().toString().trim() != null) {
                    String type = t6.getText().toString().trim();
                    mlkit_maintype = type;

                    startActivity(new Intent(MLKIT.this, mlkit_post.class));
                }
                        }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t7.getText().toString().trim() != null) {
                    String type = t7.getText().toString().trim();
                    mlkit_maintype = type;

                    startActivity(new Intent(MLKIT.this, mlkit_post.class));
                }
                        }
        });
        juice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t8.getText().toString().trim() != null) {
                    String type = t8.getText().toString().trim();
                    mlkit_maintype = type;

                    startActivity(new Intent(MLKIT.this, mlkit_post.class));
                }
            }
        });

    }

    private void read() {
        get g = new get();
        g.execute(url_all_get + "test.php");
    }

    private class get extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            volley_get(strings[0]);
            return strings[0];
        }
    }

    private void volley_get(String urll) {
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

    private void jsontoarray(String json) {
        Log.i("jsona", json);
        try {
            JSONObject root = new JSONObject(json);
            JSONArray jsonArray = root.getJSONArray("test");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);
                String ID = j.getString("ID");
                String ImageName = j.getString("ImageName");
                String ImageType1 = j.getString("ImageType1");
                String ImageType2 = j.getString("ImageType2");
                String ImageType3 = j.getString("ImageType3");
                String ImageType4 = j.getString("ImageType4");
                String ImageType5 = j.getString("ImageType5");

                Mlkit_post mlkit_post = new Mlkit_post(ID, ImageName, ImageType1, ImageType2, ImageType3, ImageType4, ImageType5);
                mlkit_posts.add(mlkit_post);

            }
            clone_Arraylist();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void clone_Arraylist() {
        Log.i("showsizef", String.valueOf(mlkit_posts.size()));
        ArrayList<String> mlkit_postss = new ArrayList<String>();
        for (int i = 0; i < mlkit_posts.size(); i++) {
         if(!mlkit_posts.get(i).getImageType_MAIN().contains("unknown")) {
             mlkit_postss.add(mlkit_posts.get(i).getImageType_MAIN());
         }

        }
        Log.i("showsize+", String.valueOf(mlkit_postss.size()));

        HashSet set = new HashSet(mlkit_postss);
        mlkit_postss.clear();
        mlkit_postss.addAll(set);
        Log.i("showsize-", String.valueOf(mlkit_postss.size()));


        Log.i("showsize", String.valueOf(mlkit_postss.size()));
        if (mlkit_postss.size() >= 1) {
            t1.setText(mlkit_postss.get(0));
        }
        if (mlkit_postss.size() >= 2) {
            t1.setText(mlkit_postss.get(0));
            t2.setText(mlkit_postss.get(1));
        }
        if (mlkit_postss.size() >= 3) {
            t1.setText(mlkit_postss.get(0));
            t2.setText(mlkit_postss.get(1));
            t3.setText(mlkit_postss.get(2));
        }
        if (mlkit_postss.size() >= 4) {
            t1.setText(mlkit_postss.get(0));
            t2.setText(mlkit_postss.get(1));
            t3.setText(mlkit_postss.get(2));
            t4.setText(mlkit_postss.get(3));



        }
        if (mlkit_postss.size() >= 5) {
            t1.setText(mlkit_postss.get(0));
            t2.setText(mlkit_postss.get(1));
            t3.setText(mlkit_postss.get(2));
            t4.setText(mlkit_postss.get(3));
            t5.setText(mlkit_postss.get(4));

        }
        if (mlkit_postss.size() >= 6) {
            t1.setText(mlkit_postss.get(0));
            t2.setText(mlkit_postss.get(1));
            t3.setText(mlkit_postss.get(2));
            t4.setText(mlkit_postss.get(3));
            t5.setText(mlkit_postss.get(4));
            t6.setText(mlkit_postss.get(5));

        }
        if (mlkit_postss.size() >= 7) {
            t1.setText(mlkit_postss.get(0));
            t2.setText(mlkit_postss.get(1));
            t3.setText(mlkit_postss.get(2));
            t4.setText(mlkit_postss.get(3));
            t5.setText(mlkit_postss.get(4));
            t6.setText(mlkit_postss.get(5));
            t7.setText(mlkit_postss.get(6));

        }
        if (mlkit_postss.size() >= 8) {
            t1.setText(mlkit_postss.get(0));
            t2.setText(mlkit_postss.get(1));
            t3.setText(mlkit_postss.get(2));
            t4.setText(mlkit_postss.get(3));
            t5.setText(mlkit_postss.get(4));
            t6.setText(mlkit_postss.get(5));
            t7.setText(mlkit_postss.get(6));
            t8.setText(mlkit_postss.get(7));
        }


    }


}
