package com.example.initapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.initapp.SetUp.st_str_account;
import static com.example.initapp.SetUp.st_str_accountID;
import static com.example.initapp.SetUp.st_str_level;

public class Profolio extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    RecyclerView comment, liked, orders;
    ImageButton comment_ib, liked_ib, orders_ib, edit_profolio;
    Context context;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profolio);
        getSupportActionBar().setTitle("Profolio");
        initui();

    }

    private void initui() {
        context = this;
        liked = findViewById(R.id.liked);
        comment = findViewById(R.id.comment);
        orders = findViewById(R.id.orders);
        comment_ib = findViewById(R.id.imageButton10);
        liked_ib = findViewById(R.id.imageButton11);
        orders_ib = findViewById(R.id.imageButton12);
        edit_profolio = findViewById(R.id.imageButton9);
        edit_profolio.setVisibility(View.GONE);


        comment_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liked.setVisibility(View.GONE);
                orders.setVisibility(View.GONE);
                comment.setVisibility(View.VISIBLE);
            }
        });
        liked_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liked.setVisibility(View.VISIBLE);
                orders.setVisibility(View.GONE);
                comment.setVisibility(View.GONE);
            }
        });
        orders_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liked.setVisibility(View.GONE);
                orders.setVisibility(View.VISIBLE);
                comment.setVisibility(View.GONE);

            }
        });

        drawerLayout = findViewById(R.id.drawer_pro);
        bottomNavigationView = findViewById(R.id.btm_nav_bar_pro);
        navigationView = findViewById(R.id.nav_pro);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.getMenu().findItem(R.id.nav_add).setEnabled(false);
        bottomNavigationView.getMenu().findItem(R.id.nav_profile).setEnabled(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(context,Home.class));
                        break;
                    case R.id.nav_profile:
                        if (st_str_account.length() > 4) {
                            startActivity(new Intent(context, Profolio.class));
                            break;
                        } else {
                            Toast.makeText(context, "You may be need login", Toast.LENGTH_SHORT).show();
                        }

                }
                return true;

            }

        });
        navigationView.getMenu().findItem(R.id.login).setVisible(true);

        if (st_str_account.length() < 1) {
            navigationView.getMenu().findItem(R.id.order).setVisible(false);

        } else {
            navigationView.getMenu().findItem(R.id.login).setVisible(false);
            navigationView.getMenu().findItem(R.id.logout).setVisible(true);

        }





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_social:
                startActivity(new Intent(context,Social.class));

                break;
            case R.id.homepage:
                startActivity(new Intent(context,Home.class));
                break;
            case R.id.signup:
                startActivity(new Intent(context, MainActivity.class));
                break;
            case R.id.login:
                startActivity(new Intent(context, login.class));
                break;

            case R.id.order:
                if(st_str_accountID.length()<1){
                    Toast.makeText(context,"You need login to use this function",Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(context, Order.class));
                }
                break;
            case R.id.logout:
                st_str_account = "";
                st_str_level = "GUEST";
                st_str_accountID = "";
                Toast.makeText(context,"Logout",Toast.LENGTH_SHORT).show();
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
