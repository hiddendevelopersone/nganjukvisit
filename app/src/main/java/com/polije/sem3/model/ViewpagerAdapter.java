package com.polije.sem3.model;

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

public class ViewpagerAdapter extends RecyclerView.Adapter<ViewpagerAdapter.ViewHolder> {
    ArrayList<EventModel> datalist;

    public ViewpagerAdapter(ArrayList<EventModel> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public ViewpagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewpager_event, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewpagerAdapter.ViewHolder holder, int position) {
        holder.txtTitle.setText(datalist.get(position).getNama());
        holder.txtLokasi.setText(datalist.get(position).getLokasi());
        holder.txtJadwal.setText(datalist.get(position).getHari() + " " + datalist.get(position).getTanggaldanwaktu());
        Glide.with(holder.itemView.getContext()).load(Client.IMG_DATA + datalist.get(position).getGambar()).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return (datalist != null) ? datalist.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle, txtLokasi, txtJadwal;
        private ImageView imgView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.judulEvent);
            txtLokasi = (TextView) itemView.findViewById(R.id.lokasiEvent);
            txtJadwal = (TextView) itemView.findViewById(R.id.jadwalEvent);
            imgView = (ImageView) itemView.findViewById(R.id.imageViewevent);
        }
    }
}
