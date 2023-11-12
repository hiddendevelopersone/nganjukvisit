package com.polije.sem3.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polije.sem3.R;

import java.util.ArrayList;

public class UlasanModelAdapter extends RecyclerView.Adapter<UlasanModelAdapter.UlasanModelViewHolder> {
    private ArrayList<UlasanModel> dataList;


    @NonNull
    @Override
    public UlasanModelAdapter.UlasanModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_ulasan, parent, false);     // layoutfordisplay
        return new UlasanModelViewHolder(view);
    }

    public UlasanModelAdapter(ArrayList<UlasanModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(UlasanModelAdapter.UlasanModelViewHolder holder, int position) {
        holder.namaPengguna.setText(dataList.get(position).getNamaPengguna());
        holder.tanggal.setText(dataList.get(position).getDateTime());
        holder.pesan.setText(dataList.get(position).getUlasan());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class UlasanModelViewHolder extends RecyclerView.ViewHolder {
        private TextView namaPengguna, tanggal, pesan;
        public UlasanModelViewHolder(View itemView) {
            super(itemView);
            namaPengguna = (TextView) itemView.findViewById(R.id.namaPengguna);
            tanggal = (TextView) itemView.findViewById(R.id.tanggalKomen);
            pesan = (TextView) itemView.findViewById(R.id.contentUlasan);
        }
    }
}
