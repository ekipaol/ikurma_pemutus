package com.application.bris.brisi_pemutus.model.aom_disposisi;

import com.google.gson.annotations.SerializedName;

public class AomDisposisi {


    @SerializedName("UID")
    private String UID;

    @SerializedName("NAMA_UMS")
    private String NAMA_UMS;

    @SerializedName("NAMA_PEGAWAI")
    private String NAMA_PEGAWAI;

    public String getUID() {
        return UID;
    }

    public void setUID(String FID_CIF_UIDLAS) {
        this.UID = FID_CIF_UIDLAS;
    }

    public String getNAMA_UMS() {
        return NAMA_UMS;
    }

    public void setNAMA_UMS(String NAMA_UMS) {
        this.NAMA_UMS = NAMA_UMS;
    }

    public String getNAMA_PEGAWAI() {
        return NAMA_PEGAWAI;
    }

    public void setNAMA_PEGAWAI(String NAMA_PEGAWAI) {
        this.NAMA_PEGAWAI = NAMA_PEGAWAI;
    }
}
