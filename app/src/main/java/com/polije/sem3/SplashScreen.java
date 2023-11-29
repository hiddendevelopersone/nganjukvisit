package com.polije.sem3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.polije.sem3.response.NganjukVisitResponse;
import com.polije.sem3.retrofit.Client;
import com.polije.sem3.util.UsersUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    private UsersUtil usersUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);


        // inisialisasi view
        View decorView = getWindow().getDecorView();

        // hide the status bar
        int uiOption = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOption);

        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        usersUtil = new UsersUtil(this);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                createUniverse();
//                startActivity(new Intent(SplashScreen.this, WelcomeScreen.class));
            }
        }, 3000);

    }

    private void createUniverse(){
        if (usersUtil.isSignIn()) {
            // Jika pengguna sudah masuk, buka MainActivity
//            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            startActivity(new Intent(SplashScreen.this, Dashboard.class));

        } else {
            // Jika pengguna belum masuk, buka WelcomeActivity
            startActivity(new Intent(SplashScreen.this, WelcomeScreen.class));
        }
        finish();
    }
}