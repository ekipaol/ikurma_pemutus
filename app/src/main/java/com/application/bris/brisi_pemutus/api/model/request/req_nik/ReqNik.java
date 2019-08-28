package com.application.bris.brisi_pemutus.api.model.request.req_nik;

import com.google.gson.annotations.SerializedName;

public class ReqNik {
    @SerializedName("nik")
    private String nik;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }
}
