package com.example.initapp.Custom_List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.initapp.R;
import com.example.initapp.model.Mlkit_post;
import com.example.initapp.model.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.initapp.SetUp.urlpostMLKIT_post;
import static com.example.initapp.SetUp.urlpostMLKIT_post1;

public class Cus_MLKIT_POST extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    LayoutInflater layoutInflater;
    ArrayList<Mlkit_post> mlkit_posts = new ArrayList<Mlkit_post>();
    Context context;
    public Cus_MLKIT_POST(ArrayList<Mlkit_post> mlkit_posts, Context context){
        this.mlkit_posts = mlkit_posts;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cus_image_mlkit,parent,false);
        RecyclerView.ViewHolder holder = new vh(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            vh vhh = (vh) holder;
            Mlkit_post mlkit_post = new Mlkit_post();
            mlkit_post = mlkit_posts.get(position);
            String url = urlpostMLKIT_post1+mlkit_post.getImagePath();
            Picasso.with(layoutInflater.getContext())
                .load(url)
                .into(vhh.imageView);

    }

    @Override
    public int getItemCount() {
        if (mlkit_posts.size() >= 1) {
            return mlkit_posts.size();
        } else {
            return 0;
        }
    }
    public class vh extends RecyclerView.ViewHolder {
        ImageView imageView;

        public vh(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_mlkit);
        }
    }
}
