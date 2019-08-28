package com.application.bris.brisi_pemutus.api.model.request.lkn;

import com.google.gson.annotations.SerializedName;

public class ReqLkn {
    public ReqLkn(String fid_aplikasi, String cif) {
        this.fid_aplikasi = fid_aplikasi;
        this.cif = cif;
    }

    public ReqLkn(){

    }

    @SerializedName("fid_aplikasi")
    private String fid_aplikasi;

    @SerializedName("cif")
    private String cif;

    public String getFid_aplikasi() {
        return fid_aplikasi;
    }

    public void setFid_aplikasi(String fid_aplikasi) {
        this.fid_aplikasi = fid_aplikasi;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }
}
