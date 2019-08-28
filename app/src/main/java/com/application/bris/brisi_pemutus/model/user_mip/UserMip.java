package com.application.bris.brisi_pemutus.model.user_mip;

import com.google.gson.annotations.SerializedName;

public class UserMip {

    @SerializedName("FullName")
    private String namaUser;

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }
}
