package com.polije.sem3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavoritPenginapanModel {
    @Expose
    @SerializedName("id_favorit")
    private String id;
    @SerializedName("id_penginapan")
    private String idPenginapan;
    @SerializedName("nama_penginapan")
    private String nama;
    @SerializedName("deskripsi")
    private String deskripsiPenginapan;
    @SerializedName("id_user")
    private String idPengguna;

    public FavoritPenginapanModel(String id, String idPenginapan, String nama, String deskripsiPenginapan, String idPengguna) {
        this.id = id;
        this.idPenginapan = idPenginapan;
        this.nama = nama;
        this.deskripsiPenginapan = deskripsiPenginapan;
        this.idPengguna = idPengguna;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPenginapan() {
        return idPenginapan;
    }

    public void setIdPenginapan(String idPenginapan) {
        this.idPenginapan = idPenginapan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsiPenginapan() {
        return deskripsiPenginapan;
    }

    public void setDeskripsiPenginapan(String deskripsiPenginapan) {
        this.deskripsiPenginapan = deskripsiPenginapan;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }
}
