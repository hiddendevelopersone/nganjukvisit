package com.polije.sem3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.polije.sem3.model.PenginapanModel;
import com.polije.sem3.response.DetailPenginapanResponse;
import com.polije.sem3.retrofit.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPenginapan extends AppCompatActivity {
    public static  String ID_PENGINAPAN;
    private String idSelected;
    private TextView nama, lokasi, deskripsi, linkmaps, gambar;
    private PenginapanModel penginapanData;
    private ImageView gambarCover;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penginapan);

        idSelected = getIntent().getStringExtra(idSelected);

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
                    String linkmaps = penginapanData.getLinkmaps();

                    nama.setText(namaPenginapan);
                    lokasi.setText(lokasiPenginapan);
                    deskripsi.setText(deskripsiPenginapan);

                    Glide.with(DetailPenginapan.this).load(Client.IMG_DATA + penginapanData.getGambar()).into(gambarCover);

                }
            }

            @Override
            public void onFailure(Call<DetailPenginapanResponse> call, Throwable t) {

            }
        });

    }
}