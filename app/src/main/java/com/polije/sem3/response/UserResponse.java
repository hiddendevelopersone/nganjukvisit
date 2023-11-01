package com.polije.sem3.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.polije.sem3.model.UserModel;

public class UserResponse {
    @Expose
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private UserModel data;

    public void setMessage(String status) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
    public String getStatus() {
        return this.status;
    }
    public UserModel getData() {
        return data;
    }

    public void setData(UserModel data) {
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
