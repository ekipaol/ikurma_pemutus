package com.application.bris.brisi_pemutus.model.putusan_akad;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PutusanAkad implements Serializable {

    @SerializedName("Nama Nasabah")
    private String nama_nasabah;
    @SerializedName("Pemrakarsa")
    private String pemrakarsa;
    @SerializedName("Tipe Produk")
    private String tipe_produk;
    @SerializedName("Plafond Induk")
    private String plafond_induk;
    @SerializedName("Jenis Putusan")
    private String jenis_putusan;
    @SerializedName("Tanggal Entry")
    private String tanggal_entry;
    @SerializedName("ID Aplikasi")
    private String id_aplikasi;
    @SerializedName("CIF APPEL")
    private String cif_appel;
    @SerializedName("FID_PHOTO")
    private String fid_photo;
    @SerializedName("JW")
    private String jangka_waktu;
    @SerializedName("FID Status")
    private String fid_status;
    @SerializedName("FID Agunan")
    private String fid_agunan;
    @SerializedName("STATUS_APLIKASI")
    private String status_aplikasi;
    @SerializedName("ID TUJUAN")
    private String id_tujuan;
    @SerializedName("Tujuan Penggunaan")
    private String tujuan_penggunaan;
    @SerializedName("NO_AKAD")
    private String NO_AKAD;
    @SerializedName("LOAN_TYPE")
    private String LOAN_TYPE;
    @SerializedName("KODE_PRODUK")
    private String KODE_PRODUK;
    @SerializedName("NAMA_PRODUK")
    private String NAMA_PRODUK;

    public String getNAMA_PRODUK() {
        return NAMA_PRODUK;
    }

    public void setNAMA_PRODUK(String NAMA_PRODUK) {
        this.NAMA_PRODUK = NAMA_PRODUK;
    }

    public String getKODE_PRODUK() {
        return KODE_PRODUK;
    }

    public void setKODE_PRODUK(String KODE_PRODUK) {
        this.KODE_PRODUK = KODE_PRODUK;
    }

    public String getLOAN_TYPE() {
        return LOAN_TYPE;
    }

    public void setLOAN_TYPE(String LOAN_TYPE) {
        this.LOAN_TYPE = LOAN_TYPE;
    }

    public String getNama_nasabah() {
        return nama_nasabah;
    }

    public void setNama_nasabah(String nama_nasabah) {
        this.nama_nasabah = nama_nasabah;
    }

    public String getPemrakarsa() {
        return pemrakarsa;
    }

    public void setPemrakarsa(String pemrakarsa) {
        this.pemrakarsa = pemrakarsa;
    }

    public String getTipe_produk() {
        return tipe_produk;
    }

    public void setTipe_produk(String tipe_produk) {
        this.tipe_produk = tipe_produk;
    }

    public String getPlafond_induk() {
        return plafond_induk;
    }

    public void setPlafond_induk(String plafond_induk) {
        this.plafond_induk = plafond_induk;
    }

    public String getJenis_putusan() {
        return jenis_putusan;
    }

    public void setJenis_putusan(String jenis_putusan) {
        this.jenis_putusan = jenis_putusan;
    }

    public String getTanggal_entry() {
        return tanggal_entry;
    }

    public void setTanggal_entry(String tanggal_entry) {
        this.tanggal_entry = tanggal_entry;
    }

    public String getId_aplikasi() {
        return id_aplikasi;
    }

    public void setId_aplikasi(String id_aplikasi) {
        this.id_aplikasi = id_aplikasi;
    }

    public String getCif_appel() {
        return cif_appel;
    }

    public void setCif_appel(String cif_appel) {
        this.cif_appel = cif_appel;
    }

    public String getFid_photo() {
        return fid_photo;
    }

    public void setFid_photo(String fid_photo) {
        this.fid_photo = fid_photo;
    }

    public String getJangka_waktu() {
        return jangka_waktu;
    }

    public void setJangka_waktu(String jangka_waktu) {
        this.jangka_waktu = jangka_waktu;
    }

    public String getFid_status() {
        return fid_status;
    }

    public void setFid_status(String fid_status) {
        this.fid_status = fid_status;
    }

    public String getFid_agunan() {
        return fid_agunan;
    }

    public void setFid_agunan(String fid_agunan) {
        this.fid_agunan = fid_agunan;
    }

    public String getStatus_aplikasi() {
        return status_aplikasi;
    }

    public void setStatus_aplikasi(String status_aplikasi) {
        this.status_aplikasi = status_aplikasi;
    }



    public String getId_tujuan() {
        return id_tujuan;
    }

    public void setId_tujuan(String id_tujuan) {
        this.id_tujuan = id_tujuan;
    }

    public String getTujuan_penggunaan() {
        return tujuan_penggunaan;
    }

    public void setTujuan_penggunaan(String tujuan_penggunaan) {
        this.tujuan_penggunaan = tujuan_penggunaan;
    }

    public String getNO_AKAD() {
        return NO_AKAD;
    }

    public void setNO_AKAD(String NO_AKAD) {
        this.NO_AKAD = NO_AKAD;
    }
}
