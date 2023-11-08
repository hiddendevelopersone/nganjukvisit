package com.polije.sem3.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polije.sem3.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EventModelAdapter extends RecyclerView.Adapter<EventModelAdapter.EventModelViewHolder>{
    private ArrayList<EventModel> dataList;

    @NonNull
    @Override
    public EventModelAdapter.EventModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_event, parent, false);     // layoutfordisplay
        return new EventModelViewHolder(view);
    }

    public EventModelAdapter(ArrayList<EventModel> dataList) {
        this.dataList = dataList;
    }


    @Override
    public void onBindViewHolder(EventModelViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getNama());
        holder.txtLokasi.setText(dataList.get(position).getLokasi());
        holder.txtJadwal.setText(dataList.get(position).getHari() + ", " + convertToDate(dataList.get(position).getTanggaldanwaktu()));
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

    public class EventModelViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle, txtLokasi, txtJadwal;

        public EventModelViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.judulEvent);
            txtLokasi = (TextView) itemView.findViewById(R.id.lokasiEvent);
            txtJadwal = (TextView) itemView.findViewById(R.id.jadwalEvent);
        }
    }
}
