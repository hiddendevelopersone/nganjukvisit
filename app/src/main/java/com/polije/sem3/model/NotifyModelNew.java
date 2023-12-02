package com.polije.sem3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotifyModelNew {
    @Expose
    @SerializedName("id_notif")
    private String idnotif;
    @SerializedName("konten")
    private String bodynotif;
    @SerializedName("id_user")
    private String idpengguna;
    @SerializedName("waktu")
    private String tanggalnotif;
    @SerializedName("judul")
    private String judul;

    public NotifyModelNew(String idnotif, String bodynotif, String idpengguna, String tanggalnotif, String judul) {
        this.idnotif = idnotif;
        this.bodynotif = bodynotif;
        this.idpengguna = idpengguna;
        this.tanggalnotif = tanggalnotif;
        this.judul = judul;
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

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
}
