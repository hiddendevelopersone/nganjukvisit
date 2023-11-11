package com.polije.sem3.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polije.sem3.R;

import java.util.ArrayList;

public class RekomendasiWisataAdapter extends RecyclerView.Adapter<RekomendasiWisataAdapter.RekomendasiWisataViewHolder> {
    private ArrayList<WisataModel> dataList;

    @NonNull
    @Override
    public RekomendasiWisataAdapter.RekomendasiWisataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_wisata_slide, parent, false);     // layoutfordisplay
        return new RekomendasiWisataViewHolder(view);
    }

    public RekomendasiWisataAdapter(ArrayList<WisataModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(RekomendasiWisataAdapter.RekomendasiWisataViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getNama());
        holder.txtLokasi.setText(dataList.get(position).getAlamat());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class RekomendasiWisataViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtLokasi;
        public RekomendasiWisataViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.titleWisata);
            txtLokasi = (TextView) itemView.findViewById(R.id.alamatWisata);
        }
    }
}
