package com.application.bris.brisi_pemutus.model.history_fasilitas;

import com.google.gson.annotations.SerializedName;

public class HistoryFasilitas {
    @SerializedName("EKSPOSURE")
    private String eksposure;
    @SerializedName("STATUS_APLIKASI")
    private String status_aplikasi;
    @SerializedName("PLAFOND_INDUK")
    private String plafond_induk;
    @SerializedName("ID_APLIKASI")
    private String id_aplikasi;
    @SerializedName("TIPE_PRODUK")
    private String tipe_produk;
    @SerializedName("TANGGAL_ENTRY")
    private String tanggal_entry;
    @SerializedName("UKER_PEMRAKARSA")
    private String uker_pemrakarsa;
    @SerializedName("NAMA_PEMRAKARSA")
    private String nama_pemrakarsa;

    public String getEksposure() {
        return eksposure;
    }

    public void setEksposure(String eksposure) {
        this.eksposure = eksposure;
    }

    public String getStatus_aplikasi() {
        return status_aplikasi;
    }

    public void setStatus_aplikasi(String status_aplikasi) {
        this.status_aplikasi = status_aplikasi;
    }

    public String getPlafond_induk() {
        return plafond_induk;
    }

    public void setPlafond_induk(String plafond_induk) {
        this.plafond_induk = plafond_induk;
    }

    public String getId_aplikasi() {
        return id_aplikasi;
    }

    public void setId_aplikasi(String id_aplikasi) {
        this.id_aplikasi = id_aplikasi;
    }

    public String getTipe_produk() {
        return tipe_produk;
    }

    public void setTipe_produk(String tipe_produk) {
        this.tipe_produk = tipe_produk;
    }

    public String getTanggal_entry() {
        return tanggal_entry;
    }

    public void setTanggal_entry(String tanggal_entry) {
        this.tanggal_entry = tanggal_entry;
    }

    public String getUker_pemrakarsa() {
        return uker_pemrakarsa;
    }

    public void setUker_pemrakarsa(String uker_pemrakarsa) {
        this.uker_pemrakarsa = uker_pemrakarsa;
    }

    public String getNama_pemrakarsa() {
        return nama_pemrakarsa;
    }

    public void setNama_pemrakarsa(String nama_pemrakarsa) {
        this.nama_pemrakarsa = nama_pemrakarsa;
    }
}
