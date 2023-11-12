package com.polije.sem3.model;

import android.annotation.SuppressLint;
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

    private OnClickListener tampil;

    @NonNull
    @Override
    public FavoritWisataModelAdapter.FavoritWisataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_wisata, parent, false);     // layoutfordisplay
        return new FavoritWisataViewHolder(view);
    }

    public FavoritWisataModelAdapter(ArrayList<FavoritWisataModel> dataList, OnClickListener listener) {
        this.dataList = dataList;
        this.tampil = listener;
    }

    @Override
    public void onBindViewHolder(FavoritWisataModelAdapter.FavoritWisataViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtNama.setText(dataList.get(position).getNamaWisata());
        holder.txtDesc.setText(fitmeTxt(dataList.get(position).getDeskripsi()));
        holder.imgButton.setImageResource(R.drawable.favorite_button_danger);
        holder.imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imgButton.setImageResource(R.drawable.favorite_button_white);
            }
        });
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

    private String fitmeTxt (String textDescOrigin) {

        int maxLength = 100; // Panjang maksimal yang diinginkan

        if (textDescOrigin.length() > maxLength) {
            String limitedText = textDescOrigin.substring(0, maxLength);
            String finalText = limitedText + " ...";
            return finalText;
        } else {
            // Teks tidak perlu dibatasi
            String finalText = textDescOrigin;
            return finalText;
        }
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

    public interface OnClickListener {
        void onItemClick(int position);
    }

}
