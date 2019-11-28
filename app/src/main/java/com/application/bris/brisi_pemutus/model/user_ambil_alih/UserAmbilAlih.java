package com.application.bris.brisi_pemutus.model.user_ambil_alih;

import com.google.gson.annotations.SerializedName;

public class UserAmbilAlih {

    @SerializedName("ROLE")
    private String ROLE;
    @SerializedName("UID")
    private String UID;
    @SerializedName("NAMA_CABANG")
    private String NAMA_CABANG;
    @SerializedName("NAMA_PEGAWAI")
    private String NAMA_PEGAWAI;
    @SerializedName("USER_NAME")
    private String USER_NAME;

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getNAMA_CABANG() {
        return NAMA_CABANG;
    }

    public void setNAMA_CABANG(String NAMA_CABANG) {
        this.NAMA_CABANG = NAMA_CABANG;
    }

    public String getNAMA_PEGAWAI() {
        return NAMA_PEGAWAI;
    }

    public void setNAMA_PEGAWAI(String NAMA_PEGAWAI) {
        this.NAMA_PEGAWAI = NAMA_PEGAWAI;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }
}
