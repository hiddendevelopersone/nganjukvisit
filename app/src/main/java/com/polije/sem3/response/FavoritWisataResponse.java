package com.polije.sem3.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.polije.sem3.model.FavoritWisataModel;

import java.util.ArrayList;

public class FavoritWisataResponse {
    @Expose
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<FavoritWisataModel> data;

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

    public ArrayList<FavoritWisataModel> getData() {
        return data;
    }

    public void setData(ArrayList<FavoritWisataModel> data) {
        this.data = data;
    }
}
