package com.application.bris.brisi_pemutus.api.model.request.list_hotprospek;

import com.google.gson.annotations.SerializedName;

public class ReqHotProspek {
    @SerializedName("uid")
    private String uid;
    @SerializedName("kode_skk")
    private String kode_skk;
    @SerializedName("namaNasabah")
    private String namaNasabah;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKode_skk() {
        return kode_skk;
    }

    public void setKode_skk(String kode_skk) {
        this.kode_skk = kode_skk;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }
}
