package com.polije.sem3.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polije.sem3.R;
import com.polije.sem3.TestRecycler;

import java.util.ArrayList;

public class FavoritPenginapanModelAdapter extends RecyclerView.Adapter<FavoritPenginapanModelAdapter.FavoritPenginapanViewHolder> {
    private ArrayList<FavoritPenginapanModel> dataList;

    @NonNull
    @Override
    public FavoritPenginapanModelAdapter.FavoritPenginapanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_penginapan, parent, false);     // layoutfordisplay
        return new FavoritPenginapanViewHolder(view);
    }

    public FavoritPenginapanModelAdapter(ArrayList<FavoritPenginapanModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(FavoritPenginapanModelAdapter.FavoritPenginapanViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtDesc.setText(dataList.get(position).getDeskripsiPenginapan());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class FavoritPenginapanViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtDesc;
        public FavoritPenginapanViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.penginapanTitle);
            txtDesc = (TextView) itemView.findViewById(R.id.textvwDesc);
        }
    }
}
