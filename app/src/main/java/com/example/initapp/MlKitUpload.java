package com.example.initapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.initapp.model.RequestHandler;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.example.initapp.AddReview.UPLOAD_KEY;
import static com.example.initapp.SetUp.st_str_account;
import static com.example.initapp.SetUp.st_str_accountID;
import static com.example.initapp.SetUp.url_all_get;
import static com.example.initapp.SetUp.url_all_post;
import static com.example.initapp.SetUp.url_postimage;
import static com.example.initapp.SetUp.urlpostMLKIT_post;

public class MlKitUpload extends AppCompatActivity {
    Button upload, select,post;
    ImageView showimage;
    TextView Showresult;
    Context context;
    private Bitmap bitmap;
    private Uri filePath;
    private int PICK_IMAGE_REQUEST = 1;
    String ImageType1 = "", ImageType2 = "", ImageType3 = "", ImageType4 = "", ImageType5 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml_kit_upload);
        initui();


    }

    private void initui() {
        upload = findViewById(R.id.upload);
        select = findViewById(R.id.selectBtn);
        Showresult = findViewById(R.id.labelresult);
        showimage = findViewById(R.id.uploadImage);
        context = this;
        post = findViewById(R.id.post);
        Click();


    }

    private void Click() {
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);

            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filePath != null) {
                    mlkitlabe();
                } else {
                    Toast.makeText(context, "You need select a picture", Toast.LENGTH_SHORT).show();
                }
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,MLKIT.class));
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {           //get intent result(image path)
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                showimage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ////////////////////////////////////////////////////////////////////
    /////////////////MLKIT                  /////////////////////
    ////////////////////////////////////////////////////////////////////

    private void mlkitlabe() {
        if (bitmap != null) {
            FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap);
            ImageLabel_upload(firebaseVisionImage);
        }
    }

    private void ImageLabel_upload(FirebaseVisionImage firebaseVisionImage) {
        FirebaseVisionImageLabeler firebaseVisionImageLabeler = FirebaseVision.getInstance().getOnDeviceImageLabeler();
        firebaseVisionImageLabeler.processImage(firebaseVisionImage).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionImageLabel>>() {
            @Override
            public void onSuccess(List<FirebaseVisionImageLabel> firebaseVisionImageLabels) {
//                if(firebaseVisionImageLabels.size()==5) {
                if (firebaseVisionImageLabels.size() >= 1 && firebaseVisionImageLabels.get(0).getConfidence() > 0.80) {
                    ImageType1 = firebaseVisionImageLabels.get(0).getText();
                    Log.i("label!", firebaseVisionImageLabels.get(0).getText());
                } else {
                    ImageType1 = "unknown";
                }
                if (firebaseVisionImageLabels.size() >= 2 && firebaseVisionImageLabels.get(1).getConfidence() > 0.70) {
                    Log.i("label!", firebaseVisionImageLabels.get(1).getText());

                    ImageType2 = firebaseVisionImageLabels.get(1).getText();
                } else {
                    ImageType2 = "unknown";
                }
                if (firebaseVisionImageLabels.size() >= 3 && firebaseVisionImageLabels.get(2).getConfidence() > 0.50) {
                    Log.i("label!", firebaseVisionImageLabels.get(2).getText());

                    ImageType3 = firebaseVisionImageLabels.get(2).getText();
                } else {
                    ImageType3 = "unknown";
                }
                if (firebaseVisionImageLabels.size() >= 4 && firebaseVisionImageLabels.get(3).getConfidence() > 0.30) {
                    Log.i("label!", firebaseVisionImageLabels.get(3).getText());

                    ImageType4 = firebaseVisionImageLabels.get(3).getText();
                } else {
                    ImageType4 = "unknown";
                }
                if (firebaseVisionImageLabels.size() >= 5 && firebaseVisionImageLabels.get(4).getConfidence() > 0.10) {
                    Log.i("label!", firebaseVisionImageLabels.get(4).getText());

                    ImageType5 = firebaseVisionImageLabels.get(4).getText();
                } else {
                    ImageType5 = "unknown";
                }
                Showresult.setVisibility(View.VISIBLE);
                Showresult.setText(ImageType1 + "\n" +  ImageType2  +"\n" +   ImageType3  +"\n" +   ImageType4 +"\n" +  ImageType5);
                //   }
//                Log.i("label!", ImageType1 +"\n" +  ImageType2  +"\n" +   ImageType3  +"\n" +   ImageType4 +"\n" +  ImageType5);
                uploadImage();
//                createdialog();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                ImageType1 = "unknown";
                ImageType2 = "unknown";
                ImageType3 = "unknown";
                ImageType4 = "unknown";
                ImageType5 = "unknown";
            }
        });
    }

    private void createdialog(){
        new AlertDialog.Builder(context).setTitle("The Label from Image ").setMessage("MESSAGE =   " + ImageType1 + "      " +  ImageType2  +"     " +   ImageType3  +"     " +   ImageType4 +"     " +  ImageType5).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();

    }


    ////////////////////////////////////////////////////////////////////
    /////////////////END OF  MLKIT    /////////////////////
    ////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////
    /////////////////POST IMAGE        /////////////////////
    ////////////////////////////////////////////////////////////////////

    public String getStringImage(Bitmap bmp) {                               //image  (bitmap ) to encoded
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage() {
        class UploadImage extends AsyncTask<Bitmap, Void, String> {
            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MlKitUpload.this, "Uploading Image", "Please wait...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Log.i("ssssssssssss", s);
                if (s.length() > 1) {
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "The Image has been uploaded", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String, String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);
//                data.put("name",getFileName(filePath));         //connect to php file and input the data
                data.put("ImageName", getFileName(filePath));
                data.put("ImageType1", ImageType1);
                data.put("ImageType2", ImageType2);
                data.put("ImageType3", ImageType3);
                data.put("ImageType4", ImageType4);
                data.put("ImageType5", ImageType5);
                // ("pathname",String.valueOf(filePath)+url_all_post+url_postimage);

                String result = rh.postRequest(urlpostMLKIT_post, data);
                Log.i("ssssssssssss", result);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

    String getFileName(Uri uri) {
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
    //////////////////////////////////////////////////////
    ////////////end of post               ////////////
    //////////////////////////////////////////////////////

}
