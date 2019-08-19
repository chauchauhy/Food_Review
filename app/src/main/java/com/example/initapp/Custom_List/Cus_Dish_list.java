package com.example.initapp.Custom_List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.initapp.model.Order_List;
import com.example.initapp.model.Restaurant;

import java.util.ArrayList;

public class Cus_Dish_list extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    LayoutInflater layoutInflater;
    ArrayList<Order_List> order_lists=new ArrayList<Order_List>();
    Context context;
    Order_List order_list;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


    public Cus_Dish_list(ArrayList<Order_List> order_lists, Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.order_lists = order_lists;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder{
        ImageView dish_image;
        TextView dish_title;
        Button ordernumber;
        LinearLayout cus_linear;

    }
}

