package com.polije.sem3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.polije.sem3.model.Verification;
import com.polije.sem3.response.VerificationResponse;
import com.polije.sem3.retrofit.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    private AppCompatImageButton btnBack;
    private Button btnSubmit;
    private EditText txtEmail;
    private String emaiUser;

    // model data
    private Verification verification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        btnBack = findViewById(R.id.backButton);
        btnSubmit = findViewById(R.id.btnSubmitOTP);
        txtEmail = findViewById(R.id.txtemails);
        emaiUser = "";

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, Login.class);
                startActivity(intent);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ForgotPassword.this, OtpVerification.class));
                emaiUser = txtEmail.getText().toString();
                String tipe = "ForgotPass";

                Client.getInstance().sendmailotp(emaiUser, tipe, "new").enqueue(new Callback<VerificationResponse>() {
                    @Override
                    public void onResponse(Call<VerificationResponse> call, Response<VerificationResponse> response) {
                        if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                            String otp = response.body().getData().getOtp();

                            Toast.makeText(ForgotPassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPassword.this, OtpVerification.class).putExtra(
                                    OtpVerification.EMAIL_USER, emaiUser
                            ).putExtra(
                                    OtpVerification.OTP_USER, otp
                            ));
                        } else {
                            Toast.makeText(ForgotPassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<VerificationResponse> call, Throwable t) {
                        Toast.makeText(ForgotPassword.this, "Request Timeout", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}