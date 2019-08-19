package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.initapp.Custom_List.Cus_Dish_list;

public class Dish extends AppCompatActivity {
    RecyclerView recyclerView;
    Cus_Dish_list cus_dish_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        recyclerView = findViewById(R.id.dish_list);


    }
}
