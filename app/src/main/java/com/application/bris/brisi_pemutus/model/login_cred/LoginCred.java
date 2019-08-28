package com.application.bris.brisi_pemutus.model.login_cred;

import com.google.gson.annotations.SerializedName;

public class LoginCred {
    @SerializedName("role_type")
    private String status;
    @SerializedName("token")
    private String token;
    @SerializedName("nama_kanwil")
    private String nama_kanwil;
    @SerializedName("fid_kantor")
    private String fid_kantor;
    @SerializedName("jabatan")
    private String jabatan;
    @SerializedName("nama_kantor")
    private String nama_kantor;
    @SerializedName("kode_skk")
    private String kode_skk;
    @SerializedName("dsn_code")
    private String dsn_code;
    @SerializedName("kode_kanwil")
    private String kode_kanwil;
    @SerializedName("fid_role")
    private String fid_role;
    @SerializedName("uid")
    private String uid;
    @SerializedName("nik")
    private String nik;
    @SerializedName("nama")
    private String nama;
    @SerializedName("kode_cabang")
    private String kode_cabang;
    @SerializedName("uker")
    private String uker;
    @SerializedName("nama_skk")
    private String nama_skk;
    @SerializedName("kode_ao")
    private String kode_ao;
    @SerializedName("kantor")
    private String kantor;

    public String getNama_kanwil() {
        return nama_kanwil;
    }

    public void setNama_kanwil(String nama_kanwil) {
        this.nama_kanwil = nama_kanwil;
    }

    public String getFid_kantor() {
        return fid_kantor;
    }

    public void setFid_kantor(String fid_kantor) {
        this.fid_kantor = fid_kantor;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNama_kantor() {
        return nama_kantor;
    }

    public void setNama_kantor(String nama_kantor) {
        this.nama_kantor = nama_kantor;
    }

    public String getKode_skk() {
        return kode_skk;
    }

    public void setKode_skk(String kode_skk) {
        this.kode_skk = kode_skk;
    }

    public String getDsn_code() {
        return dsn_code;
    }

    public void setDsn_code(String dsn_code) {
        this.dsn_code = dsn_code;
    }

    public String getKode_kanwil() {
        return kode_kanwil;
    }

    public void setKode_kanwil(String kode_kanwil) {
        this.kode_kanwil = kode_kanwil;
    }

    public String getFid_role() {
        return fid_role;
    }

    public void setFid_role(String fid_role) {
        this.fid_role = fid_role;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode_cabang() {
        return kode_cabang;
    }

    public void setKode_cabang(String kode_cabang) {
        this.kode_cabang = kode_cabang;
    }

    public String getUker() {
        return uker;
    }

    public void setUker(String uker) {
        this.uker = uker;
    }

    public String getNama_skk() {
        return nama_skk;
    }

    public void setNama_skk(String nama_skk) {
        this.nama_skk = nama_skk;
    }

    public String getKode_ao() {
        return kode_ao;
    }

    public void setKode_ao(String kode_ao) {
        this.kode_ao = kode_ao;
    }

    public String getKantor() {
        return kantor;
    }

    public void setKantor(String kantor) {
        this.kantor = kantor;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
