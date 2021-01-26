package com.application.bris.brisi_pemutus.model.monitoring;

import com.google.gson.annotations.SerializedName;

public class KcpMonitoring {
    @SerializedName("NAMA_CABANG")
    private String NAMA_CABANG;

    @SerializedName("TOTAL_OS")
    private String TOTAL_OS;
    @SerializedName("FID_UID")
    private String FID_UID;

    @SerializedName("TOTAL_PENCAIRAN")
    private String TOTAL_PENCAIRAN;

    @SerializedName("TOTAL_DPK")
    private String TOTAL_DPK;

    @SerializedName("TOTAL_KOL2")
    private String TOTAL_KOL2;

    @SerializedName("TOTAL_NPF")
    private String TOTAL_NPF;

    @SerializedName("FID_KODE_CABANG")
    private String KODE_CABANG;

    public String getKODE_CABANG() {
        return KODE_CABANG;
    }

    public void setKODE_CABANG(String KODE_CABANG) {
        this.KODE_CABANG = KODE_CABANG;
    }

    public String getNAMA_CABANG() {
        return NAMA_CABANG;
    }

    public void setNAMA_CABANG(String NAMA_CABANG) {
        this.NAMA_CABANG = NAMA_CABANG;
    }

    public String getTOTAL_OS() {
        return TOTAL_OS;
    }

    public void setTOTAL_OS(String TOTAL_OS) {
        this.TOTAL_OS = TOTAL_OS;
    }

    public String getFID_UID() {
        return FID_UID;
    }

    public void setFID_UID(String FID_UID) {
        this.FID_UID = FID_UID;
    }

    public String getTOTAL_PENCAIRAN() {
        return TOTAL_PENCAIRAN;
    }

    public void setTOTAL_PENCAIRAN(String TOTAL_PENCAIRAN) {
        this.TOTAL_PENCAIRAN = TOTAL_PENCAIRAN;
    }

    public String getTOTAL_DPK() {
        return TOTAL_DPK;
    }

    public void setTOTAL_DPK(String TOTAL_DPK) {
        this.TOTAL_DPK = TOTAL_DPK;
    }

    public String getTOTAL_KOL2() {
        return TOTAL_KOL2;
    }

    public void setTOTAL_KOL2(String TOTAL_KOL2) {
        this.TOTAL_KOL2 = TOTAL_KOL2;
    }

    public String getTOTAL_NPF() {
        return TOTAL_NPF;
    }

    public void setTOTAL_NPF(String TOTAL_NPF) {
        this.TOTAL_NPF = TOTAL_NPF;
    }
}
