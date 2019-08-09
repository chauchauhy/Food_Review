package com.example.initapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Set;

public class SetUp extends AppCompatActivity {

    static String url_RES = "restaurant.php";
    static String url_all_get = "http://10.0.2.2:8080/intern_app/get/";
    static String url_all_post = "http://10.0.2.2:8080/intern_app/post/";
    static String url_all_image = "http://10.0.2.2:/intern_app/images/";
    static String url_User = "user.php";
    static String url_post = "post.php";
    static String url_comment = "comment.php";
    static String url_comment_res = "comment_res.php";
    static String url_dishes = "dishes.php";
    static String url_like = "like.php";
    static String url_order= "order.php";
    static String url_payment = "payment.php";
    static String url_user_login_status = "user_login_status.php";

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
                startActivity(new Intent(SetUp.this,MainActivity.class));
            }
        });

    }
}
