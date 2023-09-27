package com.polije.sem3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatImageButton;

public class Login extends AppCompatActivity {

    EditText username, password;
    Button btnLogin, btnSignup;
    private AppCompatImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.txtusername);
        password = (EditText) findViewById(R.id.txtpassword);
        btnLogin = (Button) findViewById(R.id.loginButton);
        btnSignup = (Button) findViewById(R.id.signupButton);
        btnBack = findViewById(R.id.backButton);

        btnLogin.setOnClickListener(v -> {
            String usernameKey = username.getText().toString();
            String passwordKey = password.getText().toString();

            if (usernameKey.equals("admin") && passwordKey.equals("1234")) {
                Toast.makeText(getApplicationContext(), "Login Sukses",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, Dashboard.class);
                startActivity(intent);
            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setMessage("Username atau Password Anda salah!").setNegativeButton("Retry", null).create().show();
            }
        }
        );

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, WelcomeScreen.class);
                startActivity(intent);
            }
        });

    }
}