package com.polije.sem3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotifyModelNew {
    @Expose
    @SerializedName("id_pemesanan")
    private String idnotif;
    @SerializedName("metode_pembayaran")
    private String bodynotif;
    @SerializedName("id_user")
    private String idpengguna;
    @SerializedName("jadwal_pesan")
    private String tanggalnotif;

    public NotifyModelNew(String idnotif, String bodynotif, String idpengguna, String tanggalnotif) {
        this.idnotif = idnotif;
        this.bodynotif = bodynotif;
        this.idpengguna = idpengguna;
        this.tanggalnotif = tanggalnotif;
    }

    public String getIdnotif() {
        return idnotif;
    }

    public void setIdnotif(String idnotif) {
        this.idnotif = idnotif;
    }

    public String getBodynotif() {
        return bodynotif;
    }

    public void setBodynotif(String bodynotif) {
        this.bodynotif = bodynotif;
    }

    public String getIdpengguna() {
        return idpengguna;
    }

    public void setIdpengguna(String idpengguna) {
        this.idpengguna = idpengguna;
    }

    public String getTanggalnotif() {
        return tanggalnotif;
    }

    public void setTanggalnotif(String tanggalnotif) {
        this.tanggalnotif = tanggalnotif;
    }
}
