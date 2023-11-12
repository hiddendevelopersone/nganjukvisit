package com.polije.sem3.model;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polije.sem3.R;

import java.util.ArrayList;

public class WisataModelAdapter extends RecyclerView.Adapter<WisataModelAdapter.WisataModelViewHolder> {
    @NonNull
    @Override
    public WisataModelAdapter.WisataModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_wisata, parent, false);     // layoutfordisplay
        return new WisataModelViewHolder(view);
    }

    private ArrayList<WisataModel> dataList;

    private OnClickListener tampil;

    public WisataModelAdapter(ArrayList<WisataModel> dataList, OnClickListener listener) {
        this.dataList = dataList;
        this.tampil = listener;
    }

    @Override
    public void onBindViewHolder(WisataModelAdapter.WisataModelViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtDesc.setText(fitmeTxt(dataList.get(position).getDeskripsi()));

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

    private String fitmeTxt (String textDescOrigin) {

        int maxLength = 100; // Panjang maksimal yang diinginkan

        if (textDescOrigin.length() > maxLength) {
            String limitedText = textDescOrigin.substring(0, maxLength);
            String finalText = limitedText + " ...";
            return finalText;
        } else {
            // Teks tidak perlu dibatasi
            String finalText = textDescOrigin;
            return finalText;
        }
    }

    public class WisataModelViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNama, txtDesc;

        public WisataModelViewHolder(View itemView) {

            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.wisataTitle);
            txtDesc = (TextView) itemView.findViewById(R.id.textvwDescw);
        }
    }

    public interface OnClickListener {
        void onItemClick(int position);
    }
}
