package com.application.bris.brisi_pemutus.api.model.request.data_cabang;

import com.google.gson.annotations.SerializedName;

public class RequestDataCabang {
    @SerializedName("kodeCabang")
    private String kodeCabang;
    @SerializedName("kodeSkk")
    private String kodeSkk;

    public String getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(String kodeCabang) {
        this.kodeCabang = kodeCabang;
    }

    public String getKodeSkk() {
        return kodeSkk;
    }

    public void setKodeSkk(String kodeSkk) {
        this.kodeSkk = kodeSkk;
    }
}
