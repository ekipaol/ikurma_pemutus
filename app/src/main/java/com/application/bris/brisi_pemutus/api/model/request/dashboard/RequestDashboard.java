package com.application.bris.brisi_pemutus.api.model.request.dashboard;

import com.google.gson.annotations.SerializedName;

public class RequestDashboard {
    @SerializedName("uid")
    private int uid;
    @SerializedName("kodeSkk")
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
