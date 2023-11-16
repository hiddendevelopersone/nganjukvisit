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
import com.polije.sem3.Home;
import com.polije.sem3.R;
import com.polije.sem3.retrofit.Client;

import java.util.ArrayList;

public class RekomendasiKulinerAdapter extends RecyclerView.Adapter<RekomendasiKulinerAdapter.RekomendasiKulinerViewHolder> {
    private ArrayList<KulinerModel> dataList;
    private OnClickListener tampil;

    @NonNull
    @Override
    public RekomendasiKulinerAdapter.RekomendasiKulinerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_kuliner_slide, parent, false);     // layoutfordisplay
        return new RekomendasiKulinerViewHolder(view);
    }

    public RekomendasiKulinerAdapter(ArrayList<KulinerModel> dataList, OnClickListener listener) {
        this.dataList = dataList;
        this.tampil = listener;
    }

    @Override
    public void onBindViewHolder(RekomendasiKulinerAdapter.RekomendasiKulinerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.titleTxt.setText(dataList.get(position).getNama());
        holder.lokasiTxt.setText(dataList.get(position).getLokasi());

        Glide.with(holder.itemView.getContext()).load(Client.IMG_DATA + dataList.get(position).getGambar()).into(holder.imgKuliner);

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

    public class RekomendasiKulinerViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTxt, lokasiTxt;
        private ImageView imgKuliner;

        public RekomendasiKulinerViewHolder(View itemView) {
            super(itemView);
            titleTxt = (TextView) itemView.findViewById(R.id.namaKuliner);
            lokasiTxt = (TextView) itemView.findViewById(R.id.lokasiKuliner);
            imgKuliner = (ImageView) itemView.findViewById(R.id.imageViewKuliner);
        }
    }

    public interface OnClickListener {
        void onItemClick(int position);
    }
}
