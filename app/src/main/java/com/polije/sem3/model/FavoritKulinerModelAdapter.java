package com.polije.sem3.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polije.sem3.R;

import java.util.ArrayList;

public class FavoritKulinerModelAdapter extends RecyclerView.Adapter<FavoritKulinerModelAdapter.FavoritKulinerViewHolder> {
    ArrayList<FavoritKulinerModel> dataList;

    @NonNull
    @Override
    public FavoritKulinerModelAdapter.FavoritKulinerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_kuliner, parent, false);     // layoutfordisplay
        return new FavoritKulinerViewHolder(view);
    }

    public FavoritKulinerModelAdapter(ArrayList<FavoritKulinerModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(FavoritKulinerModelAdapter.FavoritKulinerViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getNamaKuliner());
        holder.txtLokasi.setText(dataList.get(position).getLokasi());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class FavoritKulinerViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle, txtLokasi;

        public FavoritKulinerViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.nameKuliner);
            txtLokasi = (TextView) itemView.findViewById(R.id.alamatKuliner);
        }
    }
}
