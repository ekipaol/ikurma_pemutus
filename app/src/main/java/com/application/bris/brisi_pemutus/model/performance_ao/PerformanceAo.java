package com.application.bris.brisi_pemutus.model.performance_ao;

import com.google.gson.annotations.SerializedName;

public class PerformanceAo {
    @SerializedName("NAMA_PEGAWAI")
    private String NAMA_PEGAWAI;
    @SerializedName("PIPELINE")
    private String PIPELINE;
    @SerializedName("HOT_PROSPEK")
    private String HOT_PROSPEK;
    @SerializedName("JLH_DITOLAK")
    private String JLH_DITOLAK;
    @SerializedName("DIPUTUS")
    private String DIPUTUS;
    @SerializedName("NOA")
    private String NOA;
    @SerializedName("AMOUNT")
    private String AMOUNT;
    @SerializedName("SBDESC")
    private String SBDESC;


    public String getNAMA_PEGAWAI() {
        return NAMA_PEGAWAI;
    }

    public void setNAMA_PEGAWAI(String NAMA_PEGAWAI) {
        this.NAMA_PEGAWAI = NAMA_PEGAWAI;
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

    public String getJLH_DITOLAK() {
        return JLH_DITOLAK;
    }

    public void setJLH_DITOLAK(String JLH_DITOLAK) {
        this.JLH_DITOLAK = JLH_DITOLAK;
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

    public String getSBDESC() {
        return SBDESC;
    }

    public void setSBDESC(String SBDESC) {
        this.SBDESC = SBDESC;
    }
}
