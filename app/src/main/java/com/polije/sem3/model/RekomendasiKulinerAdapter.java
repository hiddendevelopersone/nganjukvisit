package com.polije.sem3.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polije.sem3.Home;
import com.polije.sem3.R;

import java.util.ArrayList;

public class RekomendasiKulinerAdapter extends RecyclerView.Adapter<RekomendasiKulinerAdapter.RekomendasiKulinerViewHolder> {
    private ArrayList<KulinerModel> dataList;

    @NonNull
    @Override
    public RekomendasiKulinerAdapter.RekomendasiKulinerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_kuliner_slide, parent, false);     // layoutfordisplay
        return new RekomendasiKulinerViewHolder(view);
    }

    public RekomendasiKulinerAdapter(ArrayList<KulinerModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(RekomendasiKulinerAdapter.RekomendasiKulinerViewHolder holder, int position) {
        holder.titleTxt.setText(dataList.get(position).getNama());
        holder.lokasiTxt.setText(dataList.get(position).getLokasi());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class RekomendasiKulinerViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTxt, lokasiTxt;

        public RekomendasiKulinerViewHolder(View itemView) {
            super(itemView);
            titleTxt = (TextView) itemView.findViewById(R.id.namaKuliner);
            lokasiTxt = (TextView) itemView.findViewById(R.id.lokasiKuliner);
        }
    }
}
