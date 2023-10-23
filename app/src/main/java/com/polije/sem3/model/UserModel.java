package com.polije.sem3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {

    @Expose
    @SerializedName("id_user")
    private String idUser;
    @SerializedName("username")
    private String username;
    @SerializedName("kata_sandi")
    private String passwordUser;

}
