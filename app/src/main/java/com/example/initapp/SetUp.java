package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.initapp.model.Dishes;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.AddTrace;
import com.google.firebase.perf.metrics.HttpMetric;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

public class SetUp extends AppCompatActivity {

    public final static String url_RES = "restaurant.php";
    public final static String url_all_get = "http://10.0.2.2:8080/intern_app/get/";
    public final static String url_all_post = "http://10.0.2.2:8080/intern_app/post/";
    public final static String url_all_imageforupload = "http://10.0.2.2:8080/intern_app/Images/";
    public final static String url_all_image = "http://10.0.2.2:8080/intern_app/post/upload/";
    public final static String url_postimage = "postimage.php";
    public final static String url_User = "user.php";
    public final static String url_post = "post.php";
    public final static String url_comment = "comment.php";
    public final static String url_comment_res = "comment_res.php";
    public final static String url_dishes = "dishes.php";
    public final static String url_like = "like.php";
    public final static String url_order = "order.php";
    public final static String url_payment = "payment.php";
    public final static String url_res_info = "res_info.php";
    public final static String url_user_login_status = "user_login_status.php";
    public final static String url_like_post = "like_post.php";
    public final static String chk = "XXXXXXXX";
    public static String st_str_account = "";
    public static String st_str_accountID = "";
    public static String st_str_level = "";
    public final static String st_str_type = "type";
    public final static String st_str_type_thai = "Thai";
    public final static String st_str_type_ff = "FastFood";
    public final static String st_str_type_Dessert = "Dessert";
    public final static String st_str_type_Japen = "Japan";
    public final static String st_str_type_Korea = "Korea";
    public final static String st_str_type_ltalian = "Italian";
    public final static String st_str_type_Western = "Western";
    public final static String st_str_type_DimSum = "DimSum";
    public final static String urlpostMLKIT_post = url_all_post + "test.php";
    public final static String urlpostMLKIT_get = url_all_post + "test.php";
    public final static String urlpostMLKIT_post1 = url_all_post + "/upload/";



    public static ArrayList<Dishes> dishesArrayList = new ArrayList<Dishes>();


    TextView textView;

    @Override
//    @AddTrace(name = "onCreateTrace", enabled = true /* optional */)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);


        textView = findViewById(R.id.setup);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SetUp.this, MainActivity.class));
            }
        });

    }

}
