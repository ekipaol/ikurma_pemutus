package com.application.bris.brisi_pemutus.api.model.request.performance;

import com.google.gson.annotations.SerializedName;

public class ReqPerformanceAo {
    @SerializedName("kodeSkk")
    private String kodeSkk;

    @SerializedName("role")
    private String role;

    public String getKodeSkk() {
        return kodeSkk;
    }

    public void setKodeSkk(String kodeSkk) {
        this.kodeSkk = kodeSkk;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
