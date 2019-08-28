package com.application.bris.brisi_pemutus.model;

import com.google.gson.annotations.SerializedName;

public class PasswordNewAo {
    @SerializedName("password")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
