package com.polije.sem3.model;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polije.sem3.R;

import java.util.ArrayList;

public class KulinerModelAdapter extends RecyclerView.Adapter<KulinerModelAdapter.KulinerModelViewHolder> {
    private ArrayList<KulinerModel> dataList;
    private OnClickListener tampil;

    @NonNull
    @Override
    public KulinerModelAdapter.KulinerModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_kuliner, parent, false);     // layoutfordisplay
        return new KulinerModelViewHolder(view);
    }

    public KulinerModelAdapter(ArrayList<KulinerModel> dataList, OnClickListener listener) {
        this.dataList = dataList;
        this.tampil = listener;
    }

    @Override
    public void onBindViewHolder(KulinerModelAdapter.KulinerModelViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtTitle.setText(dataList.get(position).getNama());
        holder.txtLokasi.setText(dataList.get(position).getLokasi());
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

    public class KulinerModelViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle, txtLokasi;
        public KulinerModelViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.nameKuliner);
            txtLokasi = (TextView) itemView.findViewById(R.id.alamatKuliner);
        }
    }

    public interface OnClickListener {
        void onItemClick(int position);
    }
}
