package com.application.bris.brisi_pemutus.model.ums;

import com.google.gson.annotations.SerializedName;

public class Ums {
    @SerializedName("KODE_SKK")
    private String KODE_SKK;

    @SerializedName("KETERANGAN_SKK")
    private String KETERANGAN_SKK;

    @SerializedName("JUMLAH_UH")
    private String JUMLAH_UH;

    public String getJUMLAH_UH() {
        return JUMLAH_UH;
    }

    public void setJUMLAH_UH(String JUMLAH_UH) {
        this.JUMLAH_UH = JUMLAH_UH;
    }

    public String getKODE_SKK() {
        return KODE_SKK;
    }

    public void setKODE_SKK(String KODE_SKK) {
        this.KODE_SKK = KODE_SKK;
    }

    public String getKETERANGAN_SKK() {
        return KETERANGAN_SKK;
    }

    public void setKETERANGAN_SKK(String KETERANGAN_SKK) {
        this.KETERANGAN_SKK = KETERANGAN_SKK;
    }
}
