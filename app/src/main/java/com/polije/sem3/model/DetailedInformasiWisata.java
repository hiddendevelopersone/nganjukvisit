package com.polije.sem3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailedInformasiWisata {
    @Expose
    @SerializedName("idWisata")
    private String idWisata;
    private String gambarWisata;
    private String deskripsiWisata;
    private String jamOperasional;
    private String alamatWisata;

}
