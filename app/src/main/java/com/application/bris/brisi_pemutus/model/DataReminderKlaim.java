package com.application.bris.brisi_pemutus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataReminderKlaim implements Serializable {
    @SerializedName("NAMA NASABAH")
    @Expose
    private String namaNasabah;
    @SerializedName("NO")
    @Expose
    private String no;
    @SerializedName("AREA")
    @Expose
    private String area;
    @SerializedName("UKER")
    @Expose
    private String uker;
    @SerializedName("KODE PRODUK")
    @Expose
    private String kodeProduk;
    @SerializedName("NAMA PRODUK")
    @Expose
    private String namaProduk;
    @SerializedName("OS")
    @Expose
    private String os;
    @SerializedName("PENJAMINAN")
    @Expose
    private String penjaminan;
    @SerializedName("NO LD")
    @Expose
    private String noLd;
    @SerializedName("KOL")
    @Expose
    private String kol;
    @SerializedName("DPD")
    @Expose
    private String dpd;
    @SerializedName("TANGGAL")
    @Expose
    private String tanggal;
    @SerializedName("MARGIN")
    @Expose
    private String margin;
    @SerializedName("TGL JATUH TEMPO")
    @Expose
    private String tanggalJatuhTempo;

    public String getTanggalJatuhTempo() {
        return tanggalJatuhTempo;
    }

    public void setTanggalJatuhTempo(String tanggalJatuhTempo) {
        this.tanggalJatuhTempo = tanggalJatuhTempo;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUker() {
        return uker;
    }

    public void setUker(String uker) {
        this.uker = uker;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getPenjaminan() {
        return penjaminan;
    }

    public void setPenjaminan(String penjaminan) {
        this.penjaminan = penjaminan;
    }

    public String getNoLd() {
        return noLd;
    }

    public void setNoLd(String noLd) {
        this.noLd = noLd;
    }

    public String getKol() {
        return kol;
    }

    public void setKol(String kol) {
        this.kol = kol;
    }

    public String getDpd() {
        return dpd;
    }

    public void setDpd(String dpd) {
        this.dpd = dpd;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
