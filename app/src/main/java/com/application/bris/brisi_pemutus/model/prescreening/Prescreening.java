package com.application.bris.brisi_pemutus.model.prescreening;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

public class Prescreening {
    @Nullable
    @SerializedName("ID_PRESCREENING")
    private String id_prescreening;
    @Nullable
    @SerializedName("DUKCAPIL")
    private String dukcapil;
    @SerializedName("DHN")
    private String dhn;
    @Nullable
    @SerializedName("SLIK")
    private String slik;
    @Nullable
    @SerializedName("SIKP")
    private String sikp;
    @Nullable
    @SerializedName("FID_APLIKASI")
    private String fid_aplikasi;
    @Nullable
    @SerializedName("noPermin")
    private String noPermin;
    @Nullable
    @SerializedName("noPermin2")
    private String noPermin2;
    @SerializedName("RESULT")
    private String result;

    @Nullable
    public String getNoPermin() {
        return noPermin;
    }

    public void setNoPermin(@Nullable String noPermin) {
        this.noPermin = noPermin;
    }

    @Nullable
    public String getNoPermin2() {
        return noPermin2;
    }

    public void setNoPermin2(@Nullable String noPermin2) {
        this.noPermin2 = noPermin2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId_prescreening() {
        return id_prescreening;
    }

    public void setId_prescreening(String id_prescreening) {
        this.id_prescreening = id_prescreening;
    }

    public String getDukcapil() {
        return dukcapil;
    }

    public void setDukcapil(String dukcapil) {
        this.dukcapil = dukcapil;
    }

    public String getDhn() {
        return dhn;
    }

    public void setDhn(String dhn) {
        this.dhn = dhn;
    }

    public String getSlik() {
        return slik;
    }

    public void setSlik(String slik) {
        this.slik = slik;
    }

    public String getSikp() {
        return sikp;
    }

    public void setSikp(String sikp) {
        this.sikp = sikp;
    }

    public String getFid_aplikasi() {
        return fid_aplikasi;
    }

    public void setFid_aplikasi(String fid_aplikasi) {
        this.fid_aplikasi = fid_aplikasi;
    }


}
