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
import com.polije.sem3.R;
import com.polije.sem3.retrofit.Client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EventModelAdapter extends RecyclerView.Adapter<EventModelAdapter.EventModelViewHolder>{
    private ArrayList<EventModel> dataList;

    private OnClickListener tampil;

    @NonNull
    @Override
    public EventModelAdapter.EventModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_event, parent, false);     // layoutfordisplay
        return new EventModelViewHolder(view);
    }

    public EventModelAdapter(ArrayList<EventModel> dataList, OnClickListener listener) {
        this.dataList = dataList;
        this.tampil = listener;
    }


    @Override
    public void onBindViewHolder(EventModelViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtTitle.setText(dataList.get(position).getNama());
        holder.txtLokasi.setText(dataList.get(position).getLokasi());
        holder.txtJadwal.setText(dataList.get(position).getHari() + ", " + convertToDate(dataList.get(position).getTanggaldanwaktu()));

        Glide.with(holder.itemView.getContext()).load(Client.IMG_DATA + dataList.get(position).getGambar()).into(holder.imgView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                    tampil.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public static String convertToDate(@NonNull String date){
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        try {
            Date inputDate = inputDateFormat.parse(date);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm", new Locale("id"));
            assert inputDate != null;
            return outputDateFormat.format(inputDate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return inputDateFormat.toString();
    }

    public static String convertToDate1(String date) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        try {
            Date inputDate = inputDateFormat.parse(date);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMM yyyy | HH:mm", new Locale("id"));
            assert inputDate != null;
            return outputDateFormat.format(inputDate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return inputDateFormat.toString();
    }

    public class EventModelViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle, txtLokasi, txtJadwal;
        private ImageView imgView;

        public EventModelViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.judulEvent);
            txtLokasi = (TextView) itemView.findViewById(R.id.lokasiEvent);
            txtJadwal = (TextView) itemView.findViewById(R.id.jadwalEvent);
            imgView = (ImageView) itemView.findViewById(R.id.imageViewevent);
        }
    }

    public interface OnClickListener {
        void onItemClick(int position);
    }

}
