package com.polije.sem3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.polije.sem3.apigoogle.GoogleUsers;
import com.polije.sem3.response.UserResponse;
import com.polije.sem3.retrofit.Client;
import com.polije.sem3.util.UsersUtil;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText username, password;
    Button btnLogin, btnSignup, btnGoogle;
    TextView lupaPass;
    boolean passwordVisible;

    private GoogleUsers googleUsers;

    private UsersUtil usersUtil;

    private String token;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        googleUsers = new GoogleUsers(this);

        username = (EditText) findViewById(R.id.txtusername);
        password = (EditText) findViewById(R.id.txtpassword);
        lupaPass = (TextView) findViewById(R.id.forgotPass);
        btnLogin = (Button) findViewById(R.id.loginButton);
        btnSignup = (Button) findViewById(R.id.signupButton);
        btnGoogle = findViewById(R.id.loginButtonWithGoogle);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                token = task.getResult();

                Log.e("TOKEN", token);
            }
        });

        btnLogin.setOnClickListener(v -> {
                    String usernameKey = username.getText().toString();
                    String passwordKey = password.getText().toString();

            Client.getInstance().login(usernameKey, passwordKey).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")){
                        Intent intent = new Intent(Login.this, Dashboard.class);
                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        UsersUtil util = new UsersUtil(Login.this, response.body().getData());
                        String email = util.getEmail();
                        addSession(email);
                        startActivity(intent);
                    }else {
                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(Login.this, "Timeout", Toast.LENGTH_SHORT).show();
                }
            });


                }
        );

        lupaPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= password.getRight() - password.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = password.getSelectionEnd();
                        if (passwordVisible) {
                            // set drawable image
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eyeicon, 0);
                            // hide password
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        } else {
                            // set drawable image
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eyeicon_close, 0);
                            // show password
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        btnGoogle.setOnClickListener(v -> {

            googleUsers.resetLastSignIn();
            startActivityForResult(googleUsers.getIntent(), GoogleUsers.REQUEST_CODE);

        });
    }

    private void addSession(String UserEmail){
        Client.getInstance().addintosession(UserEmail, token).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
//                    Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("berhasil input session", response.body().getMessage());
                } else {
//                    Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("gagal input", response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
//                Toast.makeText(Login.this, "Timeout Cannot add session", Toast.LENGTH_SHORT).show();
                Log.e("error session", "Timeout Cannot add session");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        googleUsers.onActivityResult(requestCode, resultCode, data);

        if (googleUsers.isAccountSelected()){

            Client.getInstance().logingoogle(
                    googleUsers.getUserData().getEmail(), token
            ).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                        Intent i = new Intent(Login.this, Dashboard.class);

                        usersUtil = new UsersUtil(Login.this, response.body().getData());

                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(i));
                    } else {
                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(Login.this, "timeout", Toast.LENGTH_SHORT).show();
                }
            });

        }else {
            Toast.makeText(Login.this, "NO DATA", Toast.LENGTH_SHORT).show();
        }
    }

}