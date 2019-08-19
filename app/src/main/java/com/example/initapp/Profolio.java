package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Profolio extends AppCompatActivity {
    RecyclerView comment,liked,orders;
    ImageButton comment_ib , liked_ib,orders_ib,edit_profolio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profolio);
        getSupportActionBar().setTitle("Profolio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        initui();

    }
    private void initui(){
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
    }
}
