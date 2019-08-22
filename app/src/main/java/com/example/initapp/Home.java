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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.Crashlytics;
import com.example.initapp.Custom_List.Cus_Home_Res_List;
import com.example.initapp.model.Restaurant;
import com.example.initapp.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.util.Log.i;
import static com.example.initapp.SetUp.chk;
import static com.example.initapp.SetUp.st_str_account;
import static com.example.initapp.SetUp.st_str_accountID;
import static com.example.initapp.SetUp.st_str_level;
import static com.example.initapp.SetUp.url_RES;
import static com.example.initapp.SetUp.url_User;
import static com.example.initapp.SetUp.url_all_get;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Cus_Home_Res_List.OnItemClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
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
    public ArrayList<User> users = new ArrayList<User>();
    BottomNavigationView bottomNavigationView;
    TextView username;
    private static final String TAG = "Home";
    FirebaseAuth firebaseAuth;
  public static FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseApp.initializeApp(this);
        Crashlytics.log(Log.DEBUG, TAG, "Crash");
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        read();
        initui();
        firebaseAuth = FirebaseAuth.getInstance();
        Log.i(chk, st_str_account);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_add:
                        if (st_str_level.equals("admin")||st_str_level.equals("super member")) {
                            startActivity(new Intent(Home.this, AddRestaurant.class));
                        } else {
                            Toast.makeText(Home.this, "You can not use this function", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.nav_profile:
                        if (st_str_account.length() > 4) {
                            startActivity(new Intent(Home.this, Profolio.class));
                            break;
                        } else {
                            Toast.makeText(Home.this, "You may be need login", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }


                return true;
            }
        });
    }

    private void read() {
        String ur = url_all_get + url_RES;
        g get = new g();
        get.execute(ur, url_all_get + url_User);
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(Home.this, Restaurant_info.class);
        Log.i("sssssss", String.valueOf(restaurants.size()));
        Log.i("sssssss", String.valueOf(clone_res.size()));
        i.putExtra(resID, clone_res.get(position).getResID());
        startActivity(i);
    }

    private class g extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            volley_get(strings[0]);
            volley_get_user(strings[1]);
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

    private void JsontoArray(String json) {
        Log.i("jsona", json);
        try {
            JSONObject root = new JSONObject(json);
            JSONArray jsonArray = root.getJSONArray("user");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);

                String id = j.getString("Userid");
                String account = j.getString("User_Account");
                String User_Name = j.getString("User_Name");
                String password = j.getString("User_Password");
                String loginMethod = j.getString("User_Login_Method");
                String User_Email = j.getString("User_Email");
                String User_Sign_up_time = j.getString("User_Sign_up_time");
                String User_icon = j.getString("User_icon");
                String User_level = j.getString("User_level");

                if ((User_Name.equals(st_str_account))) {
                    st_str_accountID = id;
                    st_str_level = User_level;
                    Log.i("sssssssss", id + st_str_account);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
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
            cus_home_res_list = new Cus_Home_Res_List(Home.this, restaurants);
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setAdapter(cus_home_res_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            cus_home_res_list.setOnItemClickListener(Home.this);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initui() {
        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        navigationView = findViewById(R.id.Nav_home);
        mDrawerlayout.addDrawerListener(mToggle);
        navigationView.setNavigationItemSelectedListener(this);
        username = navigationView.getHeaderView(0).findViewById(R.id.username_nav);

        if (st_str_account.length() > 3) {
            username.setText(st_str_account);
        }else{
            username.setText("GUEST");
        }
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.getMenu().findItem(R.id.homepage).setVisible(false);
        bottomNavigationView = findViewById(R.id.btm_nav_bar);
        bottomNavigationView.getMenu().findItem(R.id.nav_home).setEnabled(false).setChecked(true).setIcon(R.drawable.ic_home_black_24dp);


        navigationView.getMenu().findItem(R.id.login).setVisible(true);

        if (st_str_account.length() < 1) {
            navigationView.getMenu().findItem(R.id.order).setVisible(false);

        } else {
            navigationView.getMenu().findItem(R.id.login).setVisible(false);
            navigationView.getMenu().findItem(R.id.logout).setVisible(true);

        }


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
            case R.id.nav_social:
                Bundle bundle = new Bundle();
                bundle.putLong("time",System.currentTimeMillis());
                bundle.putString("key","time");
                firebaseAnalytics.logEvent("click_soical",bundle);
startActivity(new Intent(Home.this,Social.class));

                break;
            case R.id.signup:
                startActivity(new Intent(Home.this, MainActivity.class));
                break;
            case R.id.login:
                startActivity(new Intent(Home.this, login.class));
                break;

            case R.id.order:
                if(st_str_accountID.length()<1){
                    Toast.makeText(Home.this,"You need login to use this function",Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(Home.this, Order.class));
                }
                break;
            case R.id.logout:
                st_str_account = "";
                st_str_level = "GUEST";
                st_str_accountID = "";
                Toast.makeText(Home.this,"Logout",Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                reload();

                break;

        }

        return true;
    }

    @Override
    public void finish() {
        super.finish();
    }

    public void reload() {
        Intent i = getIntent();
        finish();
        startActivity(i);
    }
}
