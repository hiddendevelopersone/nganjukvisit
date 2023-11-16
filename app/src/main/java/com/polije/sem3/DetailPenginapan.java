package com.polije.sem3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.polije.sem3.model.PenginapanModel;
import com.polije.sem3.response.DetailPenginapanResponse;
import com.polije.sem3.retrofit.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPenginapan extends AppCompatActivity {
    public static  String ID_PENGINAPAN;
    private String idSelected;
    private TextView nama, lokasi, deskripsi, linkmaps, gambar;
    private PenginapanModel penginapanData;
    private ImageView gambarCover;
    private boolean availablelinkmaps;
    private String destination;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penginapan);

        idSelected = getIntent().getStringExtra(idSelected);
        availablelinkmaps = true;
        destination = "";

        nama = findViewById(R.id.namaPenginapan);
        lokasi = findViewById(R.id.alamatPenginapan);
        deskripsi = findViewById(R.id.deskripsiPenginapan);
        linkmaps = findViewById(R.id.mapsPenginapan);
        gambarCover = findViewById(R.id.penginapanImage);

//        nama.setText(idSelected);

        Client.getInstance().detailpenginapan(idSelected).enqueue(new Callback<DetailPenginapanResponse>() {
            @Override
            public void onResponse(Call<DetailPenginapanResponse> call, Response<DetailPenginapanResponse> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                    penginapanData = response.body().getData();

                    String namaPenginapan = penginapanData.getJudulPenginapan();
                    String lokasiPenginapan = penginapanData.getLokasi();
                    String deskripsiPenginapan = penginapanData.getDeskripsi();
                    String linkmapsPenginapan = penginapanData.getLinkmaps();

                    nama.setText(namaPenginapan);
                    lokasi.setText(lokasiPenginapan);
                    deskripsi.setText(deskripsiPenginapan);

                    Glide.with(DetailPenginapan.this).load(Client.IMG_DATA + penginapanData.getGambar()).into(gambarCover);

                    if (linkmapsPenginapan.isEmpty()) {
                        availablelinkmaps = false;
                        destination = null;
                    } else {
                        availablelinkmaps = true;
                        destination = linkmapsPenginapan;
                    }
                } else {
                    Toast.makeText(DetailPenginapan.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetailPenginapanResponse> call, Throwable t) {
                Toast.makeText(DetailPenginapan.this, "Request Timeout", Toast.LENGTH_SHORT).show();
            }
        });

        linkmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (availablelinkmaps){

                    String mapUri = "https://www.google.com/maps/search/?api=1&query=" + destination;

                    Uri gmmIntentUri = Uri.parse(mapUri);

                    // Buat intent untuk membuka Google Maps
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps"); // Hanya buka dengan aplikasi Google Maps

                    // Periksa apakah aplikasi Google Maps terpasang
                    PackageManager packageManager = getPackageManager();
                    List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
                    boolean isIntentSafe = activities.size() > 0;

                    if (isIntentSafe) {
                        // Buka aplikasi Google Maps
                        startActivity(mapIntent);
                    } else {
                        // Jika Google Maps tidak terpasang, Anda dapat menampilkan pesan kesalahan
                        Toast.makeText(getApplicationContext(), "Aplikasi Google Maps tidak tersedia.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(DetailPenginapan.this, "Lokasi maps tidak tersedia", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}