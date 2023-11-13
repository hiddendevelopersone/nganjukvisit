package com.polije.sem3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.polije.sem3.model.EventModel;
import com.polije.sem3.model.EventModelAdapter;
import com.polije.sem3.response.DetailEventResponse;
import com.polije.sem3.retrofit.Client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEvent extends AppCompatActivity {
    public static String ID_EVENT = "id";
    private String idSelected;

    private EventModel eventArrayList;
    private TextView namaEvent, desc, cp, jadwal, lokasi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        idSelected = getIntent().getStringExtra(ID_EVENT);

        namaEvent = findViewById(R.id.namaEvent);
        desc = findViewById(R.id.deskripsiEvent);
        cp = findViewById(R.id.contactPerson);
        jadwal = findViewById(R.id.jadwalEvent);
        lokasi = findViewById(R.id.lokasiEvent);

        Client.getInstance().detailevent(idSelected).enqueue(new Callback<DetailEventResponse>() {
            @Override
            public void onResponse(Call<DetailEventResponse> call, Response<DetailEventResponse> response) {
                eventArrayList = response.body().getData();

                namaEvent.setText(eventArrayList.getNama());
                desc.setText(eventArrayList.getDeskripsi());
                cp.setText((!eventArrayList.getContactPerson().isEmpty()) ? eventArrayList.getContactPerson() : "Tidak Diketahui");
                jadwal.setText(
                        eventArrayList.getHari() + ", " + convertToDate(eventArrayList.getTanggaldanwaktu())
                );

                lokasi.setText(eventArrayList.getLokasi());
            }

            @Override
            public void onFailure(Call<DetailEventResponse> call, Throwable t) {

            }
        });
    }

    public static String convertToDate(@NonNull String date){
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        try {
            Date inputDate = inputDateFormat.parse(date);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm", new Locale("id"));
            assert inputDate != null;
            return outputDateFormat.format(inputDate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return inputDateFormat.toString();
    }

}