package com.application.bris.brisi_pemutus.api.model.request.req_kode_skk;

import com.google.gson.annotations.SerializedName;

public class ReqKodeSkk {

    @SerializedName("kodeSkk")
    private String kodeSkk;

    public String getKodeSkk() {
        return kodeSkk;
    }

    public void setKodeSkk(String kodeSkk) {
        this.kodeSkk = kodeSkk;
    }
}
