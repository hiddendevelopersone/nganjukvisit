package com.polije.sem3.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polije.sem3.R;

import java.util.ArrayList;

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
        holder.txtJadwal.setText(dataList.get(position).getHari() + ", " + dataList.get(position).getTanggaldanwaktu());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
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
