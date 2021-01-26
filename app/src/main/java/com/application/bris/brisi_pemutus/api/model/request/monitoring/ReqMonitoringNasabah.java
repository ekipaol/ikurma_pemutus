package com.application.bris.brisi_pemutus.api.model.request.monitoring;


import com.google.gson.annotations.SerializedName;

public class ReqMonitoringNasabah {
    @SerializedName("uid")
    private int uid;
    @SerializedName("noPegawai")
    private int noPegawai;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getNoPegawai() {
        return noPegawai;
    }

    public void setNoPegawai(int noPegawai) {
        this.noPegawai = noPegawai;
    }
}