package com.application.bris.brisi_pemutus.api.model.request.dashboard_pemrakarsa;

import com.google.gson.annotations.SerializedName;

public class RequestDashboardPemrakarsa {
    @SerializedName("uid")
    private int uid;
    @SerializedName("kode_skk")
    private String kodeSkk;

    public String getKodeSkk() {
        return kodeSkk;
    }

    public void setKodeSkk(String kodeSkk) {
        this.kodeSkk = kodeSkk;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
