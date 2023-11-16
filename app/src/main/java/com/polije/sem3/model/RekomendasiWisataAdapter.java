package com.polije.sem3.model;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.polije.sem3.R;
import com.polije.sem3.retrofit.Client;

import java.util.ArrayList;

public class RekomendasiWisataAdapter extends RecyclerView.Adapter<RekomendasiWisataAdapter.RekomendasiWisataViewHolder> {
    private ArrayList<WisataModel> dataList;

    private OnClickListener tampil;

    @NonNull
    @Override
    public RekomendasiWisataAdapter.RekomendasiWisataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_wisata_slide, parent, false);     // layoutfordisplay
        return new RekomendasiWisataViewHolder(view);
    }

    public RekomendasiWisataAdapter(ArrayList<WisataModel> dataList, OnClickListener listener) {
        this.dataList = dataList;
        this.tampil = listener;
    }

    @Override
    public void onBindViewHolder(RekomendasiWisataAdapter.RekomendasiWisataViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtTitle.setText(dataList.get(position).getNama());
        holder.txtLokasi.setText(dataList.get(position).getAlamat());

        Glide.with(holder.itemView.getContext()).load(Client.IMG_DATA + dataList.get(position).getGambar()).into(holder.imgWisata);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                    tampil.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class RekomendasiWisataViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtLokasi;
        private ImageView imgWisata;
        public RekomendasiWisataViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.titleWisata);
            txtLokasi = (TextView) itemView.findViewById(R.id.alamatWisata);
            imgWisata = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public interface OnClickListener {
        void onItemClick(int position);
    }
}
