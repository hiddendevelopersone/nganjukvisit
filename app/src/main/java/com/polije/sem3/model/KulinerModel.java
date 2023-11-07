package com.polije.sem3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KulinerModel {
    @Expose
    @SerializedName("id_kuliner")
    private String idKuliner;
    @SerializedName("nama_kuliner")
    private String nama;
    private String deskripsi;
    private String lokasi;

    public KulinerModel(String idKuliner, String nama, String deskripsi, String lokasi) {
        this.idKuliner = idKuliner;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.lokasi = lokasi;
    }

    public String getIdKuliner() {
        return idKuliner;
    }

    public void setIdKuliner(String idKuliner) {
        this.idKuliner = idKuliner;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
}
