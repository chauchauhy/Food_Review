package com.example.initapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.initapp.model.RequestHandler;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import static com.example.initapp.Home.resID;
import static com.example.initapp.SetUp.st_str_account;
import static com.example.initapp.SetUp.st_str_accountID;
import static com.example.initapp.SetUp.url_all_image;
import static com.example.initapp.SetUp.url_all_post;
import static com.example.initapp.SetUp.url_comment_res;
import static com.example.initapp.SetUp.url_postimage;

public class AddReview extends AppCompatActivity {
    TextView title;
    EditText content,ed_title;
    ImageButton post;
    BottomNavigationView bottomNavigationView;
    Boolean like;
    String restitle;
    ProgressBar progressBar;
String nameofres;
    ////////////////////////////////////////////////////////////
    ImageView test;
    Button button;
    public static final String UPLOAD_KEY = "image";

    ////////////////////////////////////////////////////////////
    private Bitmap bitmap;
    private Uri filePath;
    private int PICK_IMAGE_REQUEST = 1;

    String ressID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        getSupportActionBar().setTitle("Add Review");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        Intent i = getIntent();
        restitle =  i.getStringExtra("NAME");
        ressID = i.getStringExtra(resID);
        initui();

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);
                if (title.getText().toString().trim().length() > 1 && content.getText().toString().trim().length() > 1) {
                    if (filePath != null) {
                        uploadImage();
                        startActivity(new Intent(AddReview.this,Restaurant_info.class).putExtra(resID,ressID));
                    } else {
                        read();
                        startActivity(new Intent(AddReview.this,Restaurant_info.class).putExtra(resID,ressID));

                    }
                }else{
                        Toast.makeText(AddReview.this,"Please fill the from",Toast.LENGTH_SHORT).show();
                }
            }

        }
        );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);

        }
            });
    }
    private void read(){
        String parmas = "CM_RES_ID=" + ressID + "&" + "CM_RES_NAME=" +  restitle +  "&" +"CM_USER_ID=" +
                st_str_accountID +  "&" + "CM_USER_NAME="+st_str_account  +  "&" +"CM_TITLE=" +
                ed_title.getText().toString().trim() +  "&" + "CM_CONTENT=" + content.getText().toString().trim()
                ;
        // ("showwssad",parmas);
        post post1 = new post();
        post1.execute(url_all_post+url_comment_res,parmas);


    }

    private class post extends AsyncTask<String , Void ,String>{
        @Override
        protected String doInBackground(String... strings) {
            String reponse = postHttpURLConnection(strings[0],strings[1]);
            return reponse;

        }
    }














///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////Testin////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                test.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String> {
            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddReview.this, "Uploading Image", "Please wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.length()>1) {
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "The Image has been uploaded", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);
                data.put("name",getFileName(filePath));         //connect to php file and input the data
                data.put("CM_RES_ID",ressID);
                data.put("CM_RES_NAME",restitle);
                data.put("CM_USER_ID",st_str_accountID);
                data.put("CM_USER_NAME",st_str_account);
                data.put("CM_TITLE",ed_title.getText().toString().trim());
                data.put("CM_CONTENT",content.getText().toString().trim());
                // ("pathname",String.valueOf(filePath)+url_all_post+url_postimage);

                String result = rh.postRequest(url_all_post+url_postimage,data);
                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

    String getFileName(Uri uri){
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }









    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////Testin////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void post(){
        String postcontent = content.getText().toString().trim();
        String parmas = "";
    }
    private void initui(){
        title = findViewById(R.id.textView15);
        content = findViewById(R.id.editText19);
        post = findViewById(R.id.imageButton28);
        button = findViewById(R.id.button);
        ed_title = findViewById(R.id.title_addreview);

        test = findViewById(R.id.imageView2);

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
                    if (st_str_account.length() > 4) {
                        startActivity(new Intent(AddReview.this,Profolio.class));
                        break;
                    }else {
                        Toast.makeText(AddReview.this,"You may be need login",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.nav_social:
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
