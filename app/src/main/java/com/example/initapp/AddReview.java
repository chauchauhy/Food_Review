package com.example.initapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.initapp.Home.resID;

public class AddReview extends AppCompatActivity {
    TextView title;
    EditText content;
    ImageButton post;
    BottomNavigationView bottomNavigationView;
    String restitle;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        getSupportActionBar().setTitle("Add Review");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        Intent i = getIntent();
        restitle =  i.getStringExtra("NAME");
        Log.i("XXXX",  i.getStringExtra(resID) +  i.getStringExtra("NAME") + i.getStringExtra("RATE"));
        initui();

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);


            }
        });
    }
    private void post(){
        String postcontent = content.getText().toString().trim();
        String parmas = "";
    }
    private void initui(){
        title = findViewById(R.id.textView15);
        content = findViewById(R.id.editText19);
        post = findViewById(R.id.imageButton28);
        bottomNavigationView = findViewById(R.id.nav_addreview);
        bottomNavigationView.getMenu().findItem(R.id.nav_add).setEnabled(false);
        bottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        title.setText(restitle);
        progressBar = findViewById(R.id.progressBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(AddReview.this,Home.class));
                        break;
                    case R.id.nav_profile:
                        break;
                }


                return true;
            }
        });

    }

    private String postHttpURLConnection(String urlStr, String postParams) {

        String response = "";

        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            OutputStream outputStream = urlConnection.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeBytes(postParams);

            dataOutputStream.flush();
            dataOutputStream.close();

            int responseCode = urlConnection.getResponseCode();

            if ((responseCode == HttpURLConnection.HTTP_OK)) {
                String line;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;

                }

            } else {
                response = "";


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }


}
