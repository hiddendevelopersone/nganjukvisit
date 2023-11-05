package com.polije.sem3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventModel {
//    private String id_event;
    @Expose
    @SerializedName("nama_event")
    private String nama;
    private String lokasi;
    @SerializedName("jadwal")
    private String tanggaldanwaktu;
    private String hari;

    public EventModel(String nama, String lokasi, String tanggaldanwaktu, String hari) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.tanggaldanwaktu = tanggaldanwaktu;
        this.hari = hari;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setHari(String hari) {
        this.hari = hari;
    }
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
    public void setTanggaldanwaktu(String tanggaldanwaktu) {
        this.tanggaldanwaktu = tanggaldanwaktu;
    }
    public String getNama() {
        return nama;
    }
    public String getHari() {
        return hari;
    }
    public String getLokasi() {
        return lokasi;
    }
    public String getTanggaldanwaktu() {
        return tanggaldanwaktu;
    }
}
