package com.polije.sem3.model;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.polije.sem3.R;
import com.polije.sem3.response.FavoritPenginapanResponse;
import com.polije.sem3.retrofit.Client;
import com.polije.sem3.util.UsersUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RekomendasiPenginapanAdapter extends RecyclerView.Adapter<RekomendasiPenginapanAdapter.RekomendasiPenginapanViewHolder> {
    private ArrayList<PenginapanModel> dataList;
    private OnClickListener tampil;

    @NonNull
    @Override
    public RekomendasiPenginapanAdapter.RekomendasiPenginapanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_penginapan, parent, false);     // layoutfordisplay
        return new RekomendasiPenginapanViewHolder(view);
    }

    public RekomendasiPenginapanAdapter(ArrayList<PenginapanModel> dataList, OnClickListener listener) {
        this.dataList = dataList;
        this.tampil = listener;
    }


    @Override
    public void onBindViewHolder(RekomendasiPenginapanAdapter.RekomendasiPenginapanViewHolder holder, @SuppressLint("RecyclerView") int position) {
        UsersUtil usersUtil = new UsersUtil(holder.itemView.getContext());
        String idPengguna = usersUtil.getId();

        holder.txtNama.setText(dataList.get(position).getJudulPenginapan());
        holder.txtDesc.setText(dataList.get(position).getDeskripsi());
        Glide.with(holder.itemView.getContext()).load(Client.IMG_DATA + dataList.get(position).getGambar()).into(holder.imgView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampil.onItemClick(position);
            }
        });

        Client.getInstance().cekfavpenginapan(idPengguna, dataList.get(position).getIdPenginapan()).enqueue(new Callback<FavoritPenginapanResponse>() {
            @Override
            public void onResponse(Call<FavoritPenginapanResponse> call, Response<FavoritPenginapanResponse> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("alreadyex")) {
                    holder.imgFavs.setImageResource(R.drawable.favorite_button_danger);
                }
            }
            @Override
            public void onFailure(Call<FavoritPenginapanResponse> call, Throwable t) {
                Toast.makeText(holder.itemView.getContext(), "timeout", Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgFavs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imgFavs.setImageResource(R.drawable.favorite_button_danger);
                Client.getInstance().tambahfavpenginapan(idPengguna, dataList.get(position).getIdPenginapan()).enqueue(new Callback<FavoritPenginapanResponse>() {
                    @Override
                    public void onResponse(Call<FavoritPenginapanResponse> call, Response<FavoritPenginapanResponse> response) {
                        if (response.body() != null && response.body().getMessage() == "success") {
                            Toast.makeText(holder.itemView.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(holder.itemView.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FavoritPenginapanResponse> call, Throwable t) {
                        Toast.makeText(holder.itemView.getContext(), "timeout", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class RekomendasiPenginapanViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtDesc;
        private ImageView imgFavs, imgView;
        public RekomendasiPenginapanViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.penginapanTitle);
            txtDesc = (TextView) itemView.findViewById(R.id.textvwDesc);
            imgFavs = (ImageView) itemView.findViewById(R.id.buttonFavs);
            imgView = (ImageView) itemView.findViewById(R.id.gambarPenginapanList);
        }
    }

    public interface OnClickListener {
        void onItemClick(int position);
    }

}
