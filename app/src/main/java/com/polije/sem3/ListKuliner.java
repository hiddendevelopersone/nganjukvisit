package com.polije.sem3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.polije.sem3.model.KulinerModel;
import com.polije.sem3.model.KulinerModelAdapter;
import com.polije.sem3.model.WisataModel;
import com.polije.sem3.model.WisataModelAdapter;
import com.polije.sem3.response.KulinerResponse;
import com.polije.sem3.retrofit.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListKuliner extends AppCompatActivity {
    private RecyclerView recyclerView;
    private KulinerModelAdapter adapter;
    private ArrayList<KulinerModel> KulinerArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kuliner);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewListKuliner);

        Client.getInstance().kuliner().enqueue(new Callback<KulinerResponse>() {
            @Override
            public void onResponse(Call<KulinerResponse> call, Response<KulinerResponse> response) {
                if(response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                    KulinerArrayList = response.body().getData();
                    adapter = new KulinerModelAdapter(KulinerArrayList, new KulinerModelAdapter.OnClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            startActivity(
                                    new Intent(ListKuliner.this, DetailKuliner.class)
                                            .putExtra(DetailKuliner.ID_KULINER, KulinerArrayList.get(position).getIdKuliner())
                            );
                        }
                    });
                    recyclerView.setAdapter(adapter);
                }else {
                    Toast.makeText(ListKuliner.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KulinerResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ListKuliner.this, "ERROR -> " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}