package com.polije.sem3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
    // intent endmillis
    public static String END_MILLIS = "1";
    private String endmillisget;
    private long currentmillis;

    // button
    private Button btnsubmitOTP;

    // otpget
    private OTPTextView otpinterface;
    private String stringOTP;

    //countdown
    private TextView countdownText;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 60000;
    private boolean timerRunning = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        btnsubmitOTP = findViewById(R.id.btnVerify);
        otpinterface = findViewById(R.id.votp_inp_otp);
        emailGet = getIntent().getStringExtra(EMAIL_USER);
        otpGet = getIntent().getStringExtra(OTP_USER);



        countdownText = findViewById(R.id.timercount);

        startCountdownTimer();

        btnsubmitOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(OtpVerification.this, PasswordBaru.class));
                stringOTP = otpinterface.getOtp();
                String emailPenggunaString = getIntent().getStringExtra(EMAIL_USER);
                endmillisget = getIntent().getStringExtra(END_MILLIS);
                long endmillisgetconvert = Long.parseLong(endmillisget);
                currentmillis = System.currentTimeMillis();
//                Toast.makeText(OtpVerification.this, "OTP -> " + stringOTP , Toast.LENGTH_SHORT).show();

//                Toast.makeText(OtpVerification.this, "end millis ->" + endmillisget, Toast.LENGTH_SHORT).show();
//                Toast.makeText(OtpVerification.this, "current millis ->" + currentmillis, Toast.LENGTH_SHORT).show();

                if (stringOTP.equalsIgnoreCase(otpGet)) {
                    if (endmillisgetconvert > currentmillis) {

                        startActivity(new Intent(OtpVerification.this, PasswordBaru.class).putExtra(
                                PasswordBaru.OTP_USER, stringOTP
                        ).putExtra(
                                PasswordBaru.EMAIL_USER, emailPenggunaString
                        ));

                    } else {
                        Toast.makeText(OtpVerification.this, "sesi OTP berakhir.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OtpVerification.this, "OTP tidak cocok", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

        private void startCountdownTimer() {
            countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeftInMillis = millisUntilFinished;
                    updateCountdownText();
                }

                @Override
                public void onFinish() {
                    countdownText.setText("OTP expired");
                    btnsubmitOTP.setEnabled(false);
                    // Handle what should happen when the countdown finishes
                }
            }.start();

            timerRunning = true;
        }

        private void updateCountdownText() {
            int minutes = (int) (timeLeftInMillis / 1000) / 60;
            int seconds = (int) (timeLeftInMillis / 1000) % 60;

            String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
            countdownText.setText("Expired: " + timeLeftFormatted);
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
        }
}