package com.polije.sem3.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polije.sem3.R;

import java.util.ArrayList;

public class PenginapanModelAdapter extends RecyclerView.Adapter<PenginapanModelAdapter.PenginapanModelViewHolder>{
    private ArrayList<PenginapanModel> dataList;
    private OnClickListener tampil;

    @NonNull
    @Override
    public PenginapanModelAdapter.PenginapanModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_penginapan, parent, false);     // layoutfordisplay
        return new PenginapanModelViewHolder(view);
    }

    public PenginapanModelAdapter(ArrayList<PenginapanModel> datalist, OnClickListener listener) {
        this.dataList = datalist;
        this.tampil = listener;
    }

    @Override
    public void onBindViewHolder(PenginapanModelViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getJudulPenginapan());
        holder.txtDesc.setText(dataList.get(position).getDeskripsi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampil.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class PenginapanModelViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtDesc;
        public PenginapanModelViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.penginapanTitle);
            txtDesc = (TextView) itemView.findViewById(R.id.textvwDesc);
        }
    }

    public interface OnClickListener {
        void onItemClick(int position);
    }
}
