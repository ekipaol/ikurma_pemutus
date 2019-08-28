package com.application.bris.brisi_pemutus.model.data_ao;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

public class Ao {
    @SerializedName("UID")
    private String uid;
    @SerializedName("JABATAN")
    private String jabatan;
    @SerializedName("STATUS")
    private String status;

    @SerializedName("SBDESC")
    private String sbdesc;
    @SerializedName("FID_JHOOFF")
    private String fid_jhoof;
    @SerializedName("NAMA_PEGAWAI")
    private String nama_pegawai;
    @SerializedName("LOCK")
    private String lock;
    @SerializedName("LVLID")
    private String lvlid;
    @SerializedName("LIMIT")
    private String limit;
    @SerializedName("USER_NAME")
    private String username;
    @SerializedName("NO_PEGAWAI")
    private String nik;
    @Nullable
    @SerializedName("SK")
    private String sk;

    @Nullable
    public String getSk() {
        return sk;
    }

    public void setSk(@Nullable String sk) {
        this.sk = sk;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getSbdesc() {
        return sbdesc;
    }

    public void setSbdesc(String sbdesc) {
        this.sbdesc = sbdesc;
    }

    public String getFid_jhoof() {
        return fid_jhoof;
    }

    public void setFid_jhoof(String fid_jhoof) {
        this.fid_jhoof = fid_jhoof;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }

    public String getLvlid() {
        return lvlid;
    }

    public void setLvlid(String lvlid) {
        this.lvlid = lvlid;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
