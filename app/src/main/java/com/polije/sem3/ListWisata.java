package com.polije.sem3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ListWisata extends AppCompatActivity {

    Resources resources;
    String textDescOrigin;
    TextView textViewDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wisata);



//        textViewDesc = (TextView) findViewById(R.id.textvwDesc);
        //        resources = getResources();
//        textDescOrigin = resources.getString(R.string.loremipsumgenerator);
//        int maxLength = 60; // Panjang maksimal yang diinginkan
//
//        if (textDescOrigin.length() > maxLength) {
//            String limitedText = textDescOrigin.substring(0, maxLength);
//            String finalText = limitedText + " ...";
//            textViewDesc.setText(finalText);
//        } else {
//            // Teks tidak perlu dibatasi
//            String finalText = textDescOrigin;
//            textViewDesc.setText(finalText);
//        }

    }
}