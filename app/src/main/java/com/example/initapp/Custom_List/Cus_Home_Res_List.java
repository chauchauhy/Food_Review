package com.example.initapp.Custom_List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.initapp.R;
import com.example.initapp.model.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.initapp.SetUp.url_RES;
import static com.example.initapp.SetUp.url_all_image;

public class Cus_Home_Res_List extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    LayoutInflater layoutInflater;
    ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();

    public Cus_Home_Res_List(Context context, ArrayList<Restaurant> restaurants) {
        this.layoutInflater = LayoutInflater.from(context);
        this.restaurants = restaurants;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cus_row_home_res,viewGroup,false);
        RecyclerView.ViewHolder holder = new Res_Home_List(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        Res_Home_List home_list = (Res_Home_List) holder;
        Restaurant restaurant = restaurants.get(i);
        String urll = url_all_image+restaurant.getResImage();
        Picasso.with(layoutInflater.getContext())
                .load(urll)
                .placeholder(R.drawable.btn_back)
                .error(R.drawable.btn_add)
                .into( home_list.res_image);
        Log.i("httpul",url_all_image+restaurant.getResImage());
        home_list.res_name.setText(restaurant.getResName());
        home_list.res_detail.setText(restaurant.getResDetail());

    }

    @Override
    public int getItemCount() {
         if(restaurants==null){
             Log.i("comments", "value size =:" +  String.valueOf(restaurants.size()));
             return 0;
        }else {
             Log.i("comments", "value size =:" +  String.valueOf(restaurants.size()));
             return restaurants.size();
         }
    }

    public class Res_Home_List extends  RecyclerView.ViewHolder {
        ImageView res_image;
        TextView res_name , res_detail;

        public Res_Home_List(@NonNull View itemView) {
            super(itemView);
            res_image = itemView.findViewById(R.id.firs_res_imaget);
            res_name = itemView.findViewById(R.id.res_Name_home);
            res_detail = itemView.findViewById(R.id.res_detail);


        }
    }


}