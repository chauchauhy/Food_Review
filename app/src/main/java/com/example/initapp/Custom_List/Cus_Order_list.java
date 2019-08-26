package com.example.initapp.Custom_List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.initapp.R;
import com.example.initapp.model.Order_List;

import java.util.ArrayList;

public class Cus_Order_list extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    LayoutInflater layoutInflater;
    ArrayList<Order_List> order_lists;
    Context context;
    Order_List order_list;

    public Cus_Order_list(ArrayList<Order_List> order_lists, Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.order_lists = order_lists;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cus_row_order,parent,false);
        RecyclerView.ViewHolder holder = new viewHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        viewHolder viewHolder1 = (viewHolder) holder;
        order_list = order_lists.get(position);
        viewHolder1.time_order.setText("Time : " + order_list.getOrder_time() );
        viewHolder1.id_order.setText("OrderID : " + order_list.getOrderId() );
        viewHolder1.price.setText("Price : " + order_list.getPrice() );
        viewHolder1.quat_order.setText("Quantity :  $" + order_list.getQuanity()+" HKD" );
        viewHolder1.content.setText("Content : " + order_list.getOrder_content() );

    }

    @Override
    public int getItemCount() {
        if(order_lists==null){
            // ("order_lists", "value size =:" +  String.valueOf(order_lists.size()));
            return 0;
        }else {
            // ("order_lists", "value size =:" +  String.valueOf(order_lists.size()));
            return order_lists.size();
        }
    }
    public class viewHolder extends RecyclerView.ViewHolder{
        TextView content, time_order,price,quat_order,id_order;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content_order);
            time_order = itemView.findViewById(R.id.time_order);
            price = itemView.findViewById(R.id.price_order);
            quat_order = itemView.findViewById(R.id.quat_order);
            id_order = itemView.findViewById(R.id.id_order);


        }
    }
}
