package com.polije.sem3.model;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polije.sem3.R;
import com.polije.sem3.TestRecycler;

import java.util.ArrayList;

public class WisataModelAdapter extends RecyclerView.Adapter<WisataModelAdapter.WisataModelViewHolder> {
    @NonNull
    @Override
    public WisataModelAdapter.WisataModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_wisata, parent, false);     // layoutfordisplay
        return new WisataModelViewHolder(view);
    }

    private ArrayList<WisataModel> dataList;

    public WisataModelAdapter(ArrayList<WisataModel> dataList) { this.dataList = dataList; }

    @Override
    public void onBindViewHolder(WisataModelAdapter.WisataModelViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtDesc.setText(dataList.get(position).getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }
    public class WisataModelViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNama, txtDesc;

        public WisataModelViewHolder(View itemView) {

            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.wisataTitle);
            txtDesc = (TextView) itemView.findViewById(R.id.textvwDesc);
        }
    }
}
