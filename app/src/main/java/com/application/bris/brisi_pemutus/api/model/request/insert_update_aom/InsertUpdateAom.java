package com.application.bris.brisi_pemutus.api.model.request.insert_update_aom;

import com.google.gson.annotations.SerializedName;

public class InsertUpdateAom {

    @SerializedName("uid")
    private int uid;
    @SerializedName("noPegawai")
    private String noPegawai;
    @SerializedName("nama")
    private String nama;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("kodeCabang")
    private String kodeCabang;
    @SerializedName("fidRole")
    private int fid_role;
    @SerializedName("noSk")
    private String noSk;
    @SerializedName("fidCreator") //ini namanya FID, tapi ini sebenernya UID dari pemutus yang melakukan operasi
    private int uidCreator;

    public String getNoSk() {
        return noSk;
    }

    public void setNoSk(String noSk) {
        this.noSk = noSk;
    }

    public int getFid_role() {
        return fid_role;
    }

    public void setFid_role(int fid_role) {
        this.fid_role = fid_role;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNoPegawai() {
        return noPegawai;
    }

    public void setNoPegawai(String noPegawai) {
        this.noPegawai = noPegawai;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(String kodeCabang) {
        this.kodeCabang = kodeCabang;
    }

    public int getUidCreator() {
        return uidCreator;
    }

    public void setUidCreator(int uidCreator) {
        this.uidCreator = uidCreator;
    }
}
