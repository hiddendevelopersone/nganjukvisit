package com.polije.sem3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavoritKulinerModel {
    @Expose
    @SerializedName("id_favorit")
    private String id;
    @SerializedName("id_kuliner")
    private String idKuliner;
    @SerializedName("nama_kuliner")
    private String namaKuliner;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("id_user")
    private String idUser;
    @SerializedName("lokasi")
    private String lokasi;

    public FavoritKulinerModel(String id, String idKuliner, String deskripsi, String idUser, String lokasi, String namaKuliner) {
        this.id = id;
        this.idKuliner = idKuliner;
        this.deskripsi = deskripsi;
        this.idUser = idUser;
        this.lokasi = lokasi;
        this.namaKuliner = namaKuliner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdKuliner() {
        return idKuliner;
    }

    public void setIdKuliner(String idKuliner) {
        this.idKuliner = idKuliner;
    }

    public String getNamaKuliner() {
        return namaKuliner;
    }

    public void setNamaKuliner(String namaKuliner) {
        this.namaKuliner = namaKuliner;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
}
