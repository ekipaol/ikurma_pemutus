package com.application.bris.brisi_pemutus.model.super_data_front;

import java.io.Serializable;

public class AllDataFront implements Serializable {
    private String idAplikasi;
    private String cif;
    private String tujuanPembiayaan;
    private String jw;
    private String plafond;
    private String idAgunan;
    private String idTujuan;
    private String fidStatus;
    private String asalHalaman;
    private String kodeProduk;
    private String namaNasabah;
    private String namaProduk;
    private int idFotoFormulirAplikasi=0;

    public int getIdFotoFormulirAplikasi() {
        return idFotoFormulirAplikasi;
    }

    public void setIdFotoFormulirAplikasi(int idFotoFormulirAplikasi) {
        this.idFotoFormulirAplikasi = idFotoFormulirAplikasi;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public String getAsalHalaman() {
        return asalHalaman;
    }

    public void setAsalHalaman(String asalHalaman) {
        this.asalHalaman = asalHalaman;
    }

    public String getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(String idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getTujuanPembiayaan() {
        return tujuanPembiayaan;
    }

    public void setTujuanPembiayaan(String tujuanPembiayaan) {
        this.tujuanPembiayaan = tujuanPembiayaan;
    }

    public String getJw() {
        return jw;
    }

    public void setJw(String jw) {
        this.jw = jw;
    }

    public String getPlafond() {
        return plafond;
    }

    public void setPlafond(String plafond) {
        this.plafond = plafond;
    }

    public String getIdAgunan() {
        return idAgunan;
    }

    public void setIdAgunan(String idAgunan) {
        this.idAgunan = idAgunan;
    }

    public String getIdTujuan() {
        return idTujuan;
    }

    public void setIdTujuan(String idTujuan) {
        this.idTujuan = idTujuan;
    }

    public String getFidStatus() {
        return fidStatus;
    }

    public void setFidStatus(String fidStatus) {
        this.fidStatus = fidStatus;
    }
}
