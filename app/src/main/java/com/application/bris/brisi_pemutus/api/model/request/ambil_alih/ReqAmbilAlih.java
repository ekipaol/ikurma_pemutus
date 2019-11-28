package com.application.bris.brisi_pemutus.api.model.request.ambil_alih;

import com.google.gson.annotations.SerializedName;

public class ReqAmbilAlih {
    @SerializedName("fidRole")
    private String fidRole;
    @SerializedName("kodeSkk")
    private String kodeSkk;
    @SerializedName("kodeKanwil")
    private String kodeKanwil;

    public String getFidRole() {
        return fidRole;
    }

    public void setFidRole(String fidRole) {
        this.fidRole = fidRole;
    }

    public String getKodeSkk() {
        return kodeSkk;
    }

    public void setKodeSkk(String kodeSkk) {
        this.kodeSkk = kodeSkk;
    }

    public String getKodeKanwil() {
        return kodeKanwil;
    }

    public void setKodeKanwil(String kodeKanwil) {
        this.kodeKanwil = kodeKanwil;
    }
}
