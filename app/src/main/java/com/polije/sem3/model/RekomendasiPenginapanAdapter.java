package com.polije.sem3.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polije.sem3.R;

import java.util.ArrayList;

public class RekomendasiPenginapanAdapter extends RecyclerView.Adapter<RekomendasiPenginapanAdapter.RekomendasiPenginapanViewHolder> {
    private ArrayList<PenginapanModel> dataList;

    @NonNull
    @Override
    public RekomendasiPenginapanAdapter.RekomendasiPenginapanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_penginapan, parent, false);     // layoutfordisplay
        return new RekomendasiPenginapanViewHolder(view);
    }

    public RekomendasiPenginapanAdapter(ArrayList<PenginapanModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(RekomendasiPenginapanAdapter.RekomendasiPenginapanViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getJudulPenginapan());
        holder.txtDesc.setText(dataList.get(position).getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class RekomendasiPenginapanViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtDesc;
        public RekomendasiPenginapanViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.penginapanTitle);
            txtDesc = (TextView) itemView.findViewById(R.id.textvwDesc);
        }
    }
}
