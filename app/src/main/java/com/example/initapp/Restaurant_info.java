package com.example.initapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.initapp.Custom_List.Cus_Res_comment_list;
import com.example.initapp.model.Comment;
import com.example.initapp.model.Comment_Restaurant;
import com.example.initapp.model.Like;
import com.example.initapp.model.Res_Info;
import com.example.initapp.model.Restaurant;
import com.example.initapp.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.util.Log.i;
import static com.example.initapp.Home.resID;
import static com.example.initapp.SetUp.st_str_account;
import static com.example.initapp.SetUp.st_str_accountID;
import static com.example.initapp.SetUp.url_RES;
import static com.example.initapp.SetUp.url_all_get;
import static com.example.initapp.SetUp.url_all_image;
import static com.example.initapp.SetUp.url_comment_res;
import static com.example.initapp.SetUp.url_like;
import static com.example.initapp.SetUp.url_res_info;

public class Restaurant_info extends AppCompatActivity implements Cus_Res_comment_list.OnItemClickListener {
    ImageView star1, star2, star3, star4, star5, Res_ImageView, like_image;
    TextView Res_des, Res_Con, Res_Name, Res_info, Res_comment, Res_deatil, Dishes;
    ImageButton Image_Like;
    RecyclerView recyclerView;
    private StringRequest stringRequest;
    private Context context;
    private RequestQueue requestQueue;
    ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
    String RES_ID;
    private ArrayList<Res_Info> res_infos = new ArrayList<Res_Info>();
    private ArrayList<Comment_Restaurant> comment_restaurants = new ArrayList<Comment_Restaurant>();
    private Cus_Res_comment_list cus_res_comment_list;
    View subview;
    BottomNavigationView bottomNavigationView;
    String name, rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_info);
        initui();
        read();


        Intent i = getIntent();
        RES_ID = i.getStringExtra(resID);

        Dishes.setClickable(true);
        Dishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Restaurant_info.this, Order.class).putExtra(resID, RES_ID));
            }
        });
        Res_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.GONE);
                Res_deatil.setVisibility(View.VISIBLE);
            }
        });
        Res_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.VISIBLE);
                Res_deatil.setVisibility(View.GONE);

            }
        });
    }

    private void read() {
        get get1 = new get();
        get1.execute(url_all_get + url_RES, url_all_get + url_res_info, url_all_get + url_like, url_all_get + url_comment_res);
        Log.i("Loing",  comment_restaurants.size()+ RES_ID);

    }

    @Override
    public void onItemClick(int position) {
    }

    private class get extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            volley_get(strings[0]);
            volley_get_like(strings[2]);
            volley_get_comment(strings[3]);
            Log.i("Loing",  comment_restaurants.size()+ RES_ID);
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

    public void volley_get_comment(String urll) {
        context = this;
        requestQueue = Volley.newRequestQueue(context);
        stringRequest = new StringRequest(urll, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                i("responseforres", response);
                jsoncomment(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                i("responseforres", error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

    private void volley_get_like(String urll) {
        context = this;
        requestQueue = Volley.newRequestQueue(context);
        stringRequest = new StringRequest(urll, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonlike(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String err = error.toString();
            }
        });
        requestQueue.add(stringRequest);
    }

    private void jsoncomment(String json) {
        i("jsonaaaasdfs", json);
        try {
            JSONObject root = new JSONObject(json);
            JSONArray jsonArray = root.getJSONArray("comment_restaurant");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);
                String id = j.getString("CM_ID");
                String CM_RES_ID = j.getString("CM_RES_ID");
                String CM_USER_ID = j.getString("CM_USER_ID");
                String CM_TITLE = j.getString("CM_TITLE");
                String CM_CONTENT = j.getString("CM_CONTENT");
                String CM_TIME = j.getString("CM_TIME");
                String CM_USER_NAME = j.getString("CM_USER_NAME");
                String CM_RES_IMAGE = j.getString("CM_RES_IMAGE");

                if (CM_RES_ID.equals(RES_ID)) {
                    Comment_Restaurant comment_restaurant = new Comment_Restaurant(id,CM_RES_ID,CM_USER_ID,CM_USER_NAME,CM_TITLE,CM_CONTENT,CM_TIME,CM_RES_IMAGE);
                    comment_restaurants.add(comment_restaurant);
                    Log.i("debuging",comment_restaurant.toString());
                }

            }
            cus_res_comment_list = new Cus_Res_comment_list(Restaurant_info.this, comment_restaurants);
            recyclerView.setAdapter(cus_res_comment_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            cus_res_comment_list.setOnItemClickListener(Restaurant_info.this);
            Log.i("Loing",  comment_restaurants.size()+ RES_ID);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void jsonlike(String json) {
        try {
            JSONObject root = new JSONObject(json);
            JSONArray jsonArray = root.getJSONArray("liked");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);
                String id = j.getString("Like_ID");
                String userid = j.getString("UserID");
                String resid = j.getString("Res_ID");

                if (resid.equals(RES_ID) && userid.equals(st_str_accountID)) {                  //change userid.eq
                    Like like = new Like(id, resid, userid);
                    Log.i("saaaaaaaaa", like.getRes_ID() + "xxxxxxxxxxxxx" + like.getUserId());
                    setlike(like.getRes_ID(), like.getUserId());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setlike(String resID, String userid) {
        if (resID.equals(RES_ID) && userid.equals(st_str_accountID)) {
            Image_Like.setVisibility(View.GONE);
            like_image.setVisibility(View.VISIBLE);
        } else {
            like_image.setVisibility(View.GONE);
            Image_Like.setVisibility(View.VISIBLE);
        }
    }


//    private void jsontoarrde(String json){
//        i("jsona", json);
//        try {
//            JSONObject root = new JSONObject(json);
//            JSONArray jsonArray = root.getJSONArray("res_info");
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject j = jsonArray.getJSONObject(i);
//
//                String id = j.getString("Res_info_id");
//                String Res_info_phone = j.getString("Res_info_phone");
//                String Res_Address = j.getString("Res_Address");
//                String Res_Time = j.getString("Res_Time");
//                String Res_Price = j.getString("Res_Price");
//                String Res_ID = j.getString("Res_ID");
//
//                if (Res_ID.equals(RES_ID)) {
//
//                    Res_Info res_info = new Res_Info(id,Res_info_phone,Res_Address,Res_Time,Res_Price,Res_ID);
//                    Log.i("restauaaa", res_info.toString());
//
//
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    private void setuidetail(String phone, String address, String time, String price) {
        String n1 = "PHONE : ";
        String n2 = "Address : ";
        String n3 = "Price : ";
        String n4 = "Time : ";

        Res_deatil.setText(n1 + phone + "\n" + "\n" + n2 + address + "\n" + "\n" + n3 + price + "\n" + "\n" + n4 + time);
        Res_deatil.setVisibility(View.VISIBLE);
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
                String phone = j.getString("phone");
                String Address = j.getString("Address");
                String Time = j.getString("Time");
                String Price = j.getString("Price");

                if (id.equals(RES_ID)) {

                    Restaurant restaurant = new Restaurant(id, Res_Name, Res_Detail, Res_Image, Res_Image2, Res_Image3, Res_Like, Res_Mark, Res_Type, phone, Address, Time, Price);
                    Log.i("restauaaa", restaurant.toString());
                    restaurants.add(restaurant);
                    setui(Res_Mark, Res_Image, Res_Image2, Res_Image3, Res_Name, Res_Detail);
                    this.name = Res_Name;
                    this.rate = Res_Mark;

                    setuidetail(restaurant.getPhone(), restaurant.getAddress(), restaurant.getTime(), restaurant.getPrice());

                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void initui() {
        star1 = findViewById(R.id.imageView7);
        star2 = findViewById(R.id.imageView9);
        star3 = findViewById(R.id.imageView10);
        star4 = findViewById(R.id.imageView11);
        star5 = findViewById(R.id.imageView6);
        bottomNavigationView = findViewById(R.id.nav_bar_resinfo);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(Restaurant_info.this, Home.class));
                        break;

                    case R.id.nav_add:
                        if(st_str_accountID.length()<1){
                            Toast.makeText(Restaurant_info.this,"You need login to use this function",Toast.LENGTH_SHORT).show();
                        }else {
                            startActivity(new Intent(Restaurant_info.this, AddReview.class).putExtra(resID, RES_ID).putExtra("NAME", name).putExtra("RATE", rate));
                        }
                        break;
                    case R.id.nav_profile:
                        if (st_str_account.length() > 4) {
                            startActivity(new Intent(Restaurant_info.this,Profolio.class));
                            break;
                        }else {
                            Toast.makeText(Restaurant_info.this,"You may be need login",Toast.LENGTH_SHORT).show();
                        }

                }
                return true;
            }
        });


        Dishes = findViewById(R.id.imageView4);
        Res_ImageView = findViewById(R.id.imageView3);

        Res_des = findViewById(R.id.textView5);
        Res_Name = findViewById(R.id.textView7);
        Res_Con = findViewById(R.id.textView6);
        Res_info = findViewById(R.id.textView8);
        Res_info.setClickable(true);
        Res_comment = findViewById(R.id.textView9);
        Res_comment.setClickable(true);

        Image_Like = findViewById(R.id.imageButton14);
        recyclerView = findViewById(R.id.Res_info_recy);
        Res_deatil = findViewById(R.id.textView);
        recyclerView.setVisibility(View.GONE);
        like_image = findViewById(R.id.imageButton15);
    }

    private void setui(String mark_st, String imagepath1, String imagepath2, String imagepath3, String Res_Name, String Res_Detail) {
        if (mark_st != null) {
            int mark = Integer.valueOf(mark_st);
            if (mark >= 5) {
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star4.setVisibility(View.VISIBLE);
                star5.setVisibility(View.VISIBLE);
            }
            if (mark == 4) {
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star4.setVisibility(View.VISIBLE);
                star5.setVisibility(View.INVISIBLE);
            }
            if (mark == 3) {
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star4.setVisibility(View.INVISIBLE);
                star5.setVisibility(View.INVISIBLE);
            }
            if (mark == 2) {
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.INVISIBLE);
                star4.setVisibility(View.INVISIBLE);
                star5.setVisibility(View.INVISIBLE);
            }
            if (mark == 1) {
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.INVISIBLE);
                star3.setVisibility(View.INVISIBLE);
                star4.setVisibility(View.INVISIBLE);
                star5.setVisibility(View.INVISIBLE);

            }
            if (mark < 1) {
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.INVISIBLE);
                star3.setVisibility(View.INVISIBLE);
                star4.setVisibility(View.INVISIBLE);
                star5.setVisibility(View.INVISIBLE);
            }
        }
        if (imagepath1 != null) {
            Picasso.with(Restaurant_info.this).load(url_all_image + imagepath1).fit().placeholder(R.drawable.btn_back).into(Res_ImageView);
        }
        if (Res_Name != null && Res_Detail != null) {
            Res_des.setText(Res_Detail);
            this.Res_Name.setText(Res_Name);
        }
    }
}
