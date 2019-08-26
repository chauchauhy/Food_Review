package com.example.initapp.Custom_List;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.initapp.Dish;
import com.example.initapp.R;
import com.example.initapp.model.Dishes;
import com.example.initapp.model.Order_List;
import com.example.initapp.model.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.initapp.SetUp.url_all_image;
import static com.example.initapp.SetUp.url_all_imageforupload;

public class Cus_Dish_list extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    LayoutInflater layoutInflater;
    ArrayList<Dishes> Dishess = new ArrayList<Dishes>();
    Context context;
    Dishes dishes;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public Cus_Dish_list(ArrayList<Dishes> dishess, Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.Dishess = dishess;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cus_dish_layout, parent, false);
        RecyclerView.ViewHolder holder = new dish_VH(item);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final dish_VH dishVh = (dish_VH) holder;
        dishes = Dishess.get(position);
        String urll = url_all_imageforupload + dishes.getDishes_Image();
        // ("sadfgh", urll);
        Picasso.with(layoutInflater.getContext())
                .load(urll)
                .error(R.drawable.icon)
                .into(dishVh.dish_image);
        dishVh.dish_title.setText(dishes.getDishes_name());
    }

    @Override
    public int getItemCount() {
        if (Dishess == null) {
            // ("Dishess", "value size =:" + String.valueOf(Dishess.size()));
            return 0;
        } else {
            // ("Dishess", "value size =:" + String.valueOf(Dishess.size()));
            return Dishess.size();
        }
    }

    public class dish_VH extends RecyclerView.ViewHolder {
        ImageView dish_image;
        TextView dish_title, ordernumber;
        Button add, decrease;
        LinearLayout cus_linear;

        public dish_VH(@NonNull View itemView) {
            super(itemView);
            this.dish_image = itemView.findViewById(R.id.dishimage);
            this.dish_title = itemView.findViewById(R.id.dishname);
            this.ordernumber = itemView.findViewById(R.id.dishqua);
            this.cus_linear = itemView.findViewById(R.id.linearDish);
            this.decrease = itemView.findViewById(R.id.dishqualreduce);
            add = itemView.findViewById(R.id.dishqualreadd);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = Integer.valueOf(ordernumber.getText().toString().trim());
                    i++;
//                    i++;
//                    if (i > 99) {
//                        i = 99;
//                        ordernumber.setText(i);
//                        notifyItemInserted(getAdapterPosition());
//                    }
//                    ordernumber.setText(i);
//                    notifyItemInserted(getAdapterPosition());
//                    Toast.makeText(context,i,Toast.LENGTH_SHORT).show();
//
                }
            });
            decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int i = Integer.valueOf(ordernumber.getText().toString().trim());
                    i--;
//                    i--;
//                    if (i < 0) {
//                        i = 0;
//                        ordernumber.setText(i);
//                        notifyItemInserted(getAdapterPosition());
//                    }
//                    ordernumber.setText(i);
//                    notifyItemInserted(getAdapterPosition());
                    Toast.makeText(context,i, Toast.LENGTH_SHORT).show();

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }
}


