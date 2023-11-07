package com.polije.sem3.model;

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

    @NonNull
    @Override
    public KulinerModelAdapter.KulinerModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_kuliner, parent, false);     // layoutfordisplay
        return new KulinerModelViewHolder(view);
    }

    public KulinerModelAdapter(ArrayList<KulinerModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(KulinerModelAdapter.KulinerModelViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getNama());
        holder.txtLokasi.setText(dataList.get(position).getLokasi());
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
}
