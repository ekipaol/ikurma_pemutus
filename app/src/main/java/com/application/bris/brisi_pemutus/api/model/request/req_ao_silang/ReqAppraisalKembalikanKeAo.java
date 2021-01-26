package com.application.bris.brisi_pemutus.api.model.request.req_ao_silang;

import com.google.gson.annotations.SerializedName;

public class ReqAppraisalKembalikanKeAo {

    @SerializedName("uid")
    private int uid;

    @SerializedName("idApl")
    private int idApl;

    @SerializedName("keterangan")
    private String keterangan;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getIdApl() {
        return idApl;
    }

    public void setIdApl(int idApl) {
        this.idApl = idApl;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
