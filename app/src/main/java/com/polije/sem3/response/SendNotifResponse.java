package com.polije.sem3.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.polije.sem3.model.NotifyModel;

public class SendNotifResponse {
    @Expose
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private NotifyModel data;
    private String success;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotifyModel getData() {
        return data;
    }

    public void setData(NotifyModel data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
