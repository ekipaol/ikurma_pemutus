package com.application.bris.brisi_pemutus.model.ranking_cabang;

import com.google.gson.annotations.SerializedName;

public class RankingCabang {
    @SerializedName("KODE_KANWIL")
    private String KODE_KANWIL;
    @SerializedName("CABANG")
    private String CABANG;
    @SerializedName("PIPELINE")
    private String PIPELINE;
    @SerializedName("HOT_PROSPEK")
    private String HOT_PROSPEK;
    @SerializedName("MENUNGGU_PUTUSAN")
    private String MENUNGGU_PUTUSAN;
    @SerializedName("DIPUTUS")
    private String DIPUTUS;
    @SerializedName("NOA")
    private String NOA;
    @SerializedName("AMOUNT")
    private String AMOUNT;

    public String getKODE_KANWIL() {
        return KODE_KANWIL;
    }

    public void setKODE_KANWIL(String KODE_KANWIL) {
        this.KODE_KANWIL = KODE_KANWIL;
    }

    public String getCABANG() {
        return CABANG;
    }

    public void setCABANG(String CABANG) {
        this.CABANG = CABANG;
    }

    public String getPIPELINE() {
        return PIPELINE;
    }

    public void setPIPELINE(String PIPELINE) {
        this.PIPELINE = PIPELINE;
    }

    public String getHOT_PROSPEK() {
        return HOT_PROSPEK;
    }

    public void setHOT_PROSPEK(String HOT_PROSPEK) {
        this.HOT_PROSPEK = HOT_PROSPEK;
    }

    public String getMENUNGGU_PUTUSAN() {
        return MENUNGGU_PUTUSAN;
    }

    public void setMENUNGGU_PUTUSAN(String MENUNGGU_PUTUSAN) {
        this.MENUNGGU_PUTUSAN = MENUNGGU_PUTUSAN;
    }

    public String getDIPUTUS() {
        return DIPUTUS;
    }

    public void setDIPUTUS(String DIPUTUS) {
        this.DIPUTUS = DIPUTUS;
    }

    public String getNOA() {
        return NOA;
    }

    public void setNOA(String NOA) {
        this.NOA = NOA;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }
}
