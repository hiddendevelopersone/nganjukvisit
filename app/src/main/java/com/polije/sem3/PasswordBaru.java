package com.polije.sem3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PasswordBaru extends AppCompatActivity {
    EditText password, password2;
    boolean passwordVisible;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_baru);

        password = (EditText) findViewById(R.id.txtpassword);
        password2 = (EditText) findViewById(R.id.txtpasswordconfirm);
        btnSubmit = findViewById(R.id.btnSubmitNewpass);

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=password.getRight()-password.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = password.getSelectionEnd();
                        if(passwordVisible){
                            // set drawable image
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eyeicon, 0);
                            // hide password
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        }else{
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

        password2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=password2.getRight()-password2.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = password2.getSelectionEnd();
                        if(passwordVisible){
                            // set drawable image
                            password2.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eyeicon, 0);
                            // hide password
                            password2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        }else{
                            // set drawable image
                            password2.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eyeicon_close, 0);
                            // show password
                            password2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        password2.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PasswordBaru.this, Login.class));
            }
        });
    }
}