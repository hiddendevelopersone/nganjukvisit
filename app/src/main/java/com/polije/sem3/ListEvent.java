package com.polije.sem3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.polije.sem3.model.EventModel;
import com.polije.sem3.model.EventModelAdapter;
import com.polije.sem3.response.EventResponse;
import com.polije.sem3.retrofit.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEvent extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventModelAdapter adapter;
    private ArrayList<EventModel> EventArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewListEvent);

        Client.getInstance().event().enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                    adapter = new EventModelAdapter(response.body().getData());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(ListEvent.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Toast.makeText(ListEvent.this, "Request Timeout", Toast.LENGTH_SHORT).show();
            }
        });
    }
}