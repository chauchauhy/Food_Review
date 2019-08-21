package com.example.initapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.media.FaceDetector;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.initapp.Custom_List.Cus_Res_comment_list;
import com.example.initapp.model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

import static android.util.Log.i;
import static com.example.initapp.Home.resID;
import static com.example.initapp.SetUp.url_User;
import static com.example.initapp.SetUp.url_all_get;
import static com.example.initapp.SetUp.st_str_account;
import static com.example.initapp.SetUp.st_str_accountID;
import static com.example.initapp.SetUp.st_str_level;


public class login extends AppCompatActivity {


    EditText Ed_username, userpassword;
    ImageButton login, signup;
    LoginButton facebook;
    SignInButton google;
    ConstraintLayout layout;
    static String userid;
    static String username;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private Context context;
    ArrayList<User> users = new ArrayList<User>();
    private FirebaseAuth firebaseAuth;
    String useremail_login, username_login, useraccountname_login;
    //    CallbackManager callbackManager;
    CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth_fb;
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth firebaseAuth_google;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
//        logger.logPurchase(BigDecimal.valueOf(4.32), Currency.getInstance("USD"));
        callbackManager = CallbackManager.Factory.create();
        firebaseAuth_fb = FirebaseAuth.getInstance();
        firebaseAuth_google = FirebaseAuth.getInstance();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(context, "Google", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .build();

        initui();
        read();
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(i, RC_SIGN_IN);
            }
        });


        facebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
                firebaseAuth_fb.signInWithCredential(credential)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = firebaseAuth_fb.getCurrentUser();
                                    Toast.makeText(login.this, " Current user is " + String.valueOf(user.getDisplayName()), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(login.this, "login Fail " + task.getException(), Toast.LENGTH_SHORT).show();
                                    Log.i("asdfg", String.valueOf(task.getException()));
                                }
                            }
                        });
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        firebaseAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (chknull()) {
                    if (chk(Ed_username.getText().toString().trim(), userpassword.getText().toString().trim())) {
                        startActivity(new Intent(login.this, Home.class));
                    } else {
                        Snackbar.make(layout, "the account or email is not existed**", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (chknull()) {
                    firebaseAuth.signInWithEmailAndPassword(Ed_username.getText().toString().trim(), userpassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
//                                firebaseauth();
                                Toast.makeText(login.this, "Success login", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(login.this, "PLZ Check you username(email)and Pssword", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                return true;
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, MainActivity.class));
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth_google.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(login.this, "Google sign in success", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(login.this, "Sign in faild",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    public void firebaseSingOut(View view){
        firebaseAuth_google.signOut();

        // Google 登出
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignIn.getClient(this, gso).signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(login.this, "SingOut", Toast.LENGTH_LONG).show();
            }
        });
    }

    private Boolean chknull() {
        boolean chk = false;
        if (Ed_username.getText().toString().trim().length() < 4 && userpassword.getText().toString().trim().length() < 7) {
            Toast.makeText(login.this, "Please enter corrent username/email/accountname and password", Toast.LENGTH_SHORT).show();
        } else {
            chk = true;
        }
        return chk;

    }

    private void firebaseauth() {
        Log.i("AAAAAAAAAAA", String.valueOf(users.size()));
        for (int i = 0; 0 < users.size(); i++) {
            if (users.get(i).getUserEmail().equals(Ed_username) && users.get(i).getUserPassword().equals(userpassword)) {
                st_str_account = users.get(i).getUserName();
                st_str_accountID = users.get(i).getUserID();
                st_str_level = users.get(i).getUserID();
                break;
            }
        }

    }


    private void initui() {
        Ed_username = findViewById(R.id.username_login);
        userpassword = findViewById(R.id.password_login);
        login = findViewById(R.id.login_login);
        signup = findViewById(R.id.signup_login);
        facebook = findViewById(R.id.facebook);
        google = findViewById(R.id.google);
        layout = findViewById(R.id.layout);
        facebook.setReadPermissions("email", "public_profile");

    }

    private void read() {
        get gett = new get();
        gett.execute(url_all_get + url_User);

    }


    private class get extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            volley_get_user(strings[0]);
            return strings[0];
        }
    }

    private void volley_get_user(String urlll) {
        context = this;
        requestQueue = Volley.newRequestQueue(context);
        stringRequest = new StringRequest(urlll, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                i("re_user", response);
                jsontoarr(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                i("responseforres", error.toString());
            }
        });
        requestQueue.add(stringRequest);

    }

    private void jsontoarr(String json) {
        i("jsona", json);
        try {
            JSONObject root = new JSONObject(json);
            JSONArray jsonArray = root.getJSONArray("user");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);
                String id = j.getString("Userid");
                String User_Name = j.getString("User_Name");
                String User_Account = j.getString("User_Account");
                String User_Password = j.getString("User_Password");
                String User_Email = j.getString("User_Email");
                String user_Email = User_Email.trim();
                String User_level = j.getString("User_level");
                String User_icon = j.getString("User_icon");
                String User_Login_Method = j.getString("User_Login_Method");
                String User_Sign_up_time = j.getString("User_Sign_up_time");

                User user = new User(id, User_Name, User_Account, User_Password, User_Login_Method, user_Email, User_Sign_up_time, User_icon, User_level);
                users.add(user);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Boolean chk(String username, String password) {
        boolean chk = false;
        for (int i = 0; i < users.size(); i++) {
            Log.i("SSSSSS", users.get(i).getUserEmail());
            if ((username.equals(users.get(i).getUserAccount()) || username.equals(users.get(i).getUserEmail()) || username.equals(users.get(i).getUserName())) && password.equals(users.get(i).getUserAccount())) {
                Log.i("sadsa", users.get(i).getUserEmail());
                st_str_account = users.get(i).getUserAccount();
                //new
                st_str_level = users.get(i).getUserLevel();
                st_str_accountID = users.get(i).getUserID();
                useraccountname_login = users.get(i).getUserAccount();
                username_login = users.get(i).getUserName();
                useremail_login = users.get(i).getUserEmail();
                Log.i("teuuels", useremail_login + username_login + useraccountname_login);

                //end of new
                chk = true;
                break;
            }
        }
        if (useremail_login.length() < 1 && (!(useremail_login.contains(".com")) || !(useremail_login.contains("@")))) {
            useremail_login = "null@gamil.com";
        }
        if (chk == true) {
//            Snackbar.make(layout, "the account or email is not existed", Snackbar.LENGTH_SHORT).show();
        }
        Log.i("ccccccc", String.valueOf(chk));
        return chk;
    }

}
