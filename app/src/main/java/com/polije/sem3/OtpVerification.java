package com.polije.sem3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.otpview.OTPTextView;
import com.polije.sem3.retrofit.Client;

public class OtpVerification extends AppCompatActivity {
    // intent email
    public static String EMAIL_USER = "email";
    private String emailGet;
    // intent otp
    public static String OTP_USER = "1234";
    private String otpGet;

    // button
    private AppCompatImageButton btnBack;
    private Button btnsubmitOTP;

    // otpget
    private OTPTextView otpinterface;
    private String stringOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        btnBack = findViewById(R.id.backButton);
        btnsubmitOTP = findViewById(R.id.btnVerify);
        otpinterface = findViewById(R.id.votp_inp_otp);
        emailGet = getIntent().getStringExtra(EMAIL_USER);
        otpGet = getIntent().getStringExtra(OTP_USER);


        Toast.makeText(this, emailGet, Toast.LENGTH_SHORT).show();

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
//                startActivity(new Intent(OtpVerification.this, PasswordBaru.class));
                stringOTP = otpinterface.getOtp();
                String emailPenggunaString = getIntent().getStringExtra(EMAIL_USER);
//                Toast.makeText(OtpVerification.this, "OTP -> " + stringOTP , Toast.LENGTH_SHORT).show();

                if (stringOTP.equalsIgnoreCase(otpGet)) {
                    startActivity(new Intent(OtpVerification.this, PasswordBaru.class).putExtra(
                            PasswordBaru.OTP_USER, stringOTP
                    ).putExtra(
                            PasswordBaru.EMAIL_USER, emailPenggunaString
                    ));
                } else {
                    Toast.makeText(OtpVerification.this, "OTP tidak cocok", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}