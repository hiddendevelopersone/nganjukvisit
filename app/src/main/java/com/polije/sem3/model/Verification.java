package com.polije.sem3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Verification {
    @Expose
    @SerializedName("id_verification")
    String idVerification;
    String email;
    String otp;
    String end_millis;

    public Verification(String idVerification, String email, String otp, String end_millis) {
        this.idVerification = idVerification;
        this.email = email;
        this.otp = otp;
        this.end_millis = end_millis;
    }

    public String getIdVerification() {
        return idVerification;
    }

    public void setIdVerification(String idVerification) {
        this.idVerification = idVerification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getEnd_millis() {
        return end_millis;
    }

    public void setEnd_millis(String end_millis) {
        this.end_millis = end_millis;
    }
}
