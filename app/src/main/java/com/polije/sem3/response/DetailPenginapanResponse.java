package com.polije.sem3.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.polije.sem3.model.PenginapanModel;

public class DetailPenginapanResponse {
    @Expose
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private PenginapanModel data;

    public void setMessage(String status) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
    public String getStatus() {
        return this.status;
    }
    public PenginapanModel getData() {
        return data;
    }

    public void setData(PenginapanModel data) {
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
