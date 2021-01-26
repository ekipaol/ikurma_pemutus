package com.application.bris.brisi_pemutus.model.hotprospek;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by PID on 4/26/2019.
 */

public class HotProspek implements Serializable {
    @SerializedName("FID_CIF_LAS")
    private String fid_cif_las;
    @SerializedName("ID_ST_APLIKASI")
    private String id_st_aplikasi;
    @SerializedName("KODE_PRODUK")
    private String kode_produk;
    @SerializedName("NAMA_TUJUAN")
    private String nama_tujuan;
    @SerializedName("PLAFOND_INDUK")
    private String plafond_induk;
    @SerializedName("KLASIFIKASI_KREDIT")
    private String klasifikasi_kredit;
    @SerializedName("STATUS_APLIKASI")
    private String status_aplikasi;
    @SerializedName("NAMA_PRODUK")
    private String nama_produk;
    @SerializedName("ID_APLIKASI")
    private String id_aplikasi;
    @SerializedName("TANGGAL_ENTRY")
    private String tanggal_entry;
    @SerializedName("FID_PHOTO")
    private String fid_photo;
    @SerializedName("JW")
    private String jw;
    @SerializedName("NAMA_DEBITUR_1")
    private String nama_debitur;
    @SerializedName("ID_PROGRAM")
    private String idProgram;

    public String getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(String idProgram) {
        this.idProgram = idProgram;
    }

    public String getNama_tujuan() {
        return nama_tujuan;
    }

    public void setNama_tujuan(String nama_tujuan) {
        this.nama_tujuan = nama_tujuan;
    }

    public String getFid_cif_las() {
        return fid_cif_las;
    }

    public void setFid_cif_las(String fid_cif_las) {
        this.fid_cif_las = fid_cif_las;
    }

    public String getId_st_aplikasi() {
        return id_st_aplikasi;
    }

    public void setId_st_aplikasi(String id_st_aplikasi) {
        this.id_st_aplikasi = id_st_aplikasi;
    }

    public String getKode_produk() {
        return kode_produk;
    }

    public void setKode_produk(String kode_produk) {
        this.kode_produk = kode_produk;
    }

    public String getPlafond_induk() {
        return plafond_induk;
    }

    public void setPlafond_induk(String plafond_induk) {
        this.plafond_induk = plafond_induk;
    }

    public String getKlasifikasi_kredit() {
        return klasifikasi_kredit;
    }

    public void setKlasifikasi_kredit(String klasifikasi_kredit) {
        this.klasifikasi_kredit = klasifikasi_kredit;
    }

    public String getStatus_aplikasi() {
        return status_aplikasi;
    }

    public void setStatus_aplikasi(String status_aplikasi) {
        this.status_aplikasi = status_aplikasi;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getId_aplikasi() {
        return id_aplikasi;
    }

    public void setId_aplikasi(String id_aplikasi) {
        this.id_aplikasi = id_aplikasi;
    }

    public String getTanggal_entry() {
        return tanggal_entry;
    }

    public void setTanggal_entry(String tanggal_entry) {
        this.tanggal_entry = tanggal_entry;
    }

    public String getFid_photo() {
        return fid_photo;
    }

    public void setFid_photo(String fid_photo) {
        this.fid_photo = fid_photo;
    }

    public String getJw() {
        return jw;
    }

    public void setJw(String jw) {
        this.jw = jw;
    }

    public String getNama_debitur() {
        return nama_debitur;
    }

    public void setNama_debitur(String nama_debitur) {
        this.nama_debitur = nama_debitur;
    }
}
