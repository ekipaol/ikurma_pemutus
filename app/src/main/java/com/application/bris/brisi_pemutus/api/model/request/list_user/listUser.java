package com.application.bris.brisi_pemutus.api.model.request.list_user;

import com.google.gson.annotations.SerializedName;

public class listUser {

    @SerializedName("uid")
    private String uid;
    @SerializedName("kode_skk")
    private String kodeSkk;
    @SerializedName("namaNasabah")
    private String namaNasabah;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setKodeSkk(String kodeSkk) {
        this.kodeSkk = kodeSkk;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }
}
