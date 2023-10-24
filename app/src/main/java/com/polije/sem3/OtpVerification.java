package com.polije.sem3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OtpVerification extends AppCompatActivity {
    private AppCompatImageButton btnBack;
    private Button btnsubmitOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        btnBack = findViewById(R.id.backButton);
        btnsubmitOTP = findViewById(R.id.btnVerify);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtpVerification.this, Login.class);
                startActivity(intent);
            }
        });

        btnsubmitOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtpVerification.this, PasswordBaru.class));
            }
        });
    }
}