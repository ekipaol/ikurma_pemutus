package com.application.bris.brisi_pemutus.api.model.request.putusan_pemutus;


import com.google.gson.annotations.SerializedName;

public class ReqSetujuPutusan {
    @SerializedName("role_id")
    private String role_id;
    @SerializedName("st_aplikasi")
    private String st_aplikasid;

    @SerializedName("id_pemutus")
    private String id_pemutus;
    @SerializedName("fid_aplikasi")
    private String fid_aplikasi;
    @SerializedName("catatan_pemutus")
    private String catatan_pemutus;
    @SerializedName("kode_dsn")
    private String kode_dsn;


    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getSt_aplikasid() {
        return st_aplikasid;
    }

    public void setSt_aplikasid(String uist_aplikasid) {
        this.st_aplikasid = uist_aplikasid;
    }



    public String getId_pemutus() {
        return id_pemutus;
    }

    public void setId_pemutus(String id_pemutus) {
        this.id_pemutus = id_pemutus;
    }

    public String getFid_aplikasi() {
        return fid_aplikasi;
    }

    public void setFid_aplikasi(String fid_aplikasi) {
        this.fid_aplikasi = fid_aplikasi;
    }

    public String getCatatan_pemutus() {
        return catatan_pemutus;
    }

    public void setCatatan_pemutus(String catatan_pemutus) {
        this.catatan_pemutus = catatan_pemutus;
    }

    public String getKode_dsn() {
        return kode_dsn;
    }

    public void setKode_dsn(String kode_dsn) {
        this.kode_dsn = kode_dsn;
    }
}

