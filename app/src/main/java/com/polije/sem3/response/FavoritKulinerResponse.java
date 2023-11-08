package com.polije.sem3.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.polije.sem3.model.FavoritKulinerModel;

import java.util.ArrayList;

public class FavoritKulinerResponse {
    @Expose
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<FavoritKulinerModel> data;

    public void setMessage(String status) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<FavoritKulinerModel> getData() {
        return data;
    }

    public void setData(ArrayList<FavoritKulinerModel> data) {
        this.data = data;
    }
}
