package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Set;

public class SetUp extends AppCompatActivity {

    public static String url_RES = "restaurant.php";
    public static String url_all_get = "http://10.0.2.2:8080/intern_app/get/";
    public static String url_all_post = "http://10.0.2.2:8080/intern_app/post/";
    public static String url_all_image = "http://10.0.2.2:8080/intern_app/images/";
    public static String url_User = "user.php";
    public static String url_post = "post.php";
    public static String url_comment = "comment.php";
    public static String url_comment_res = "comment_res.php";
    public static String url_dishes = "dishes.php";
    public static String url_like = "like.php";
    public static String url_order = "order.php";
    public static String url_payment = "payment.php";
    public static String url_user_login_status = "user_login_status.php";

    TextView textView;

    @Override
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
