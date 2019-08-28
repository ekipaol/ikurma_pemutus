package com.application.bris.brisi_pemutus.model.cs_model;

import com.google.gson.annotations.SerializedName;

public class CsModel {

    @SerializedName("NAMA_PEGAWAI")
    private String nama_pegawai;

    @SerializedName("NO_PEGAWAI")
    private String no_pegawai;

    @SerializedName("UID")
    private String uid;


    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public String getNo_pegawai() {
        return no_pegawai;
    }

    public void setNo_pegawai(String no_pegawai) {
        this.no_pegawai = no_pegawai;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
