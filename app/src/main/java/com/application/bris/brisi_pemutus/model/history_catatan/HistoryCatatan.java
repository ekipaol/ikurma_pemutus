package com.application.bris.brisi_pemutus.model.history_catatan;

import com.google.gson.annotations.SerializedName;

public class HistoryCatatan {

    @SerializedName("JABATAN")
    private String jabatan;
    @SerializedName("NAMA_PEMUTUS")
    private String nama_pemutus;
    @SerializedName("CATATAN_PEMUTUS")
    private String catatan_pemutus;
    @SerializedName("PUTUSAN_PEMUTUS")
    private String putusan_pemutus;
    @SerializedName("JENIS_PUTUSAN")
    private String jenis_putusan;

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNama_pemutus() {
        return nama_pemutus;
    }

    public void setNama_pemutus(String nama_pemutus) {
        this.nama_pemutus = nama_pemutus;
    }

    public String getCatatan_pemutus() {
        return catatan_pemutus;
    }

    public void setCatatan_pemutus(String catatan_pemutus) {
        this.catatan_pemutus = catatan_pemutus;
    }

    public String getPutusan_pemutus() {
        return putusan_pemutus;
    }

    public void setPutusan_pemutus(String putusan_pemutus) {
        this.putusan_pemutus = putusan_pemutus;
    }

    public String getJenis_putusan() {
        return jenis_putusan;
    }

    public void setJenis_putusan(String jenis_putusan) {
        this.jenis_putusan = jenis_putusan;
    }
}
