package com.application.bris.brisi_pemutus.api.model.request.list_disposisi;

import com.google.gson.annotations.SerializedName;

public class ReqListDisposisi {

    @SerializedName("kodeSkk")
    private String kode_skk;
    @SerializedName("sudahDisposisi")
    private Boolean sudahDisposisi;

    public String getKode_skk() {
        return kode_skk;
    }

    public void setKode_skk(String kode_skk) {
        this.kode_skk = kode_skk;
    }

    public Boolean getSudahDisposisi() {
        return sudahDisposisi;
    }

    public void setSudahDisposisi(Boolean sudahDisposisi) {
        this.sudahDisposisi = sudahDisposisi;
    }
}
