package com.polije.sem3.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polije.sem3.R;
import com.polije.sem3.response.NotifyResponse;

import java.util.ArrayList;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.NotifyViewHolder> {
    private ArrayList<NotifyModelNew> dataList;

    public NotifyAdapter(ArrayList<NotifyModelNew> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public NotifyAdapter.NotifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_notif, parent, false);     // layoutfordisplay
        return new NotifyAdapter.NotifyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifyAdapter.NotifyViewHolder holder, int position) {
        holder.title.setText(dataList.get(position).getJudul());
        holder.bodynotif.setText(dataList.get(position).getBodynotif());
        holder.waktu.setText(dataList.get(position).getTanggalnotif());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;

    }

    public class NotifyViewHolder extends RecyclerView.ViewHolder{
        private TextView title, bodynotif, waktu;
        public NotifyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.adminTitle);
            bodynotif = (TextView) itemView.findViewById(R.id.bodyNotif);
            waktu = (TextView) itemView.findViewById(R.id.timedate);
        }
    }
}
