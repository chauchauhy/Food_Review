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
import com.example.initapp.model.Comment_Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.initapp.SetUp.url_all_image;
import static com.example.initapp.SetUp.url_all_post;

public class Cus_Res_comment_list extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Comment_Restaurant> comment_restaurants;
    Comment_Restaurant comment_restaurant;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


    public Cus_Res_comment_list(Context context, ArrayList<Comment_Restaurant> comment_restaurants) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.comment_restaurants = comment_restaurants;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cus_row_resinfo_post,parent,false);
        RecyclerView.ViewHolder holder = new Res_CM_VH(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Res_CM_VH res_cm_vh = (Res_CM_VH) holder;
        comment_restaurant = comment_restaurants.get(position);
        String urll = url_all_post + "/upload/" + comment_restaurant.getCM_RES_image();
        if( (urll.length()< 50)) {
            res_cm_vh.cus_post_image.setVisibility(View.GONE);
        }else {
            res_cm_vh.cus_post_image.setVisibility(View.VISIBLE);
            Picasso.with(layoutInflater.getContext())
                    .load(urll)
                    .placeholder(R.drawable.icon)
                    .fit()
                    .error(R.drawable.icon)
                    .into(res_cm_vh.cus_post_image);
            // ("httpuril", urll);
        }
        res_cm_vh.cus_post_author.setText(comment_restaurant.getCm_user_name());
        res_cm_vh.cus_post_content.setText(comment_restaurant.getCm_content());
        res_cm_vh.cus_post_Title.setText(comment_restaurant.getCm_title());




    }

    @Override
    public int getItemCount() {
        if(comment_restaurants==null){
            // ("comment_restaurants", "value size =:" +  String.valueOf(comment_restaurants.size()));
            return 0;
        }else {
            // ("comment_restaurants", "value size =:" +  String.valueOf(comment_restaurants.size()));
            return comment_restaurants.size();
        }
    }

    public class Res_CM_VH extends  RecyclerView.ViewHolder{
        ImageView cus_post_image;
        TextView cus_post_author,cus_post_content,cus_post_Title;

        public Res_CM_VH(@NonNull View itemView) {
            super(itemView);
            cus_post_author = itemView.findViewById(R.id.cus_post_author);
            cus_post_image = itemView.findViewById(R.id.cus_post_image);
            cus_post_content = itemView.findViewById(R.id.cus_post_content);
            cus_post_Title = itemView.findViewById(R.id.cus_post_Title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });



        }
    }
}
