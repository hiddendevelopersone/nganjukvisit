package com.polije.sem3.model;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polije.sem3.R;
import com.polije.sem3.response.WisataResponse;
import com.polije.sem3.retrofit.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritWisataModelAdapter extends RecyclerView.Adapter<FavoritWisataModelAdapter.FavoritWisataViewHolder> {
    private ArrayList<FavoritWisataModel> dataList;

    @NonNull
    @Override
    public FavoritWisataModelAdapter.FavoritWisataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_wisata, parent, false);     // layoutfordisplay
        return new FavoritWisataViewHolder(view);
    }

    public FavoritWisataModelAdapter(ArrayList<FavoritWisataModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(FavoritWisataModelAdapter.FavoritWisataViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNamaWisata());
        holder.txtDesc.setText(dataList.get(position).getDeskripsi());
        holder.imgButton.setImageResource(R.drawable.favorite_button_danger);
        holder.imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imgButton.setImageResource(R.drawable.favorite_button_white);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class FavoritWisataViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtDesc;
        private ImageView imgButton;

        public FavoritWisataViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.wisataTitle);
            txtDesc = (TextView) itemView.findViewById(R.id.textvwDescw);
            imgButton = itemView.findViewById(R.id.favsbutton);
        }
    }

}
