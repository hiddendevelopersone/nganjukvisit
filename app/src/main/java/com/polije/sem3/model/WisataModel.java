package com.polije.sem3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WisataModel {

    @Expose
    @SerializedName("id_wisata")
    private String idwisata;
    @SerializedName("nama_wisata")
    private String nama;
    private String deskripsi;

    public WisataModel (String nama, String deskripsi) {
        this.nama = nama;
        this.deskripsi = deskripsi;
    }

    public String getIdwisata() {
        return idwisata;
    }

    public void setIdwisata(String idwisata) {
        this.idwisata = idwisata;
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
}
