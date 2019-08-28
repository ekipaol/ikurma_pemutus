package com.application.bris.brisi_pemutus.model.data_cabang;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.Realm;
import io.realm.RealmObject;

public class Cabang  extends RealmObject implements Serializable {
    // private String nama,kantor,jabatan,limit,status,lock,uid,pin;

    @SerializedName("KODE_KANWIL")
    private String kode_kanwil;
    @SerializedName("KOTA")
    private String kota;
    @SerializedName("KODE_SKPP")
    private String kode_skpp;
    @SerializedName("SBDESC")
    private String sb_desc;
    @SerializedName("ALAMAT_KANTOR")
    private String alamat_kantor;
    @SerializedName("KODE_OL")
    private String kode_ol;
    @SerializedName("KODE_CABANG_INDUK")
    private String kode_induk;
    @SerializedName("KODE_LENGKAP")
    private String kode_lengkap;
    @SerializedName("SUBBR")
    private String subbr;
    @SerializedName("UNIT_KERJA")
    private String unit_kerja;



    public String getKode_kanwil() {
        return kode_kanwil;
    }

    public void setKode_kanwil(String kode_kanwil) {
        this.kode_kanwil = kode_kanwil;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKode_skpp() {
        return kode_skpp;
    }

    public void setKode_skpp(String kode_skpp) {
        this.kode_skpp = kode_skpp;
    }

    public String getSb_desc() {
        return sb_desc;
    }

    public void setSb_desc(String sb_desc) {
        this.sb_desc = sb_desc;
    }

    public String getAlamat_kantor() {
        return alamat_kantor;
    }

    public void setAlamat_kantor(String alamat_kantor) {
        this.alamat_kantor = alamat_kantor;
    }

    public String getKode_ol() {
        return kode_ol;
    }

    public void setKode_ol(String kode_ol) {
        this.kode_ol = kode_ol;
    }

    public String getKode_induk() {
        return kode_induk;
    }

    public void setKode_induk(String kode_induk) {
        this.kode_induk = kode_induk;
    }

    public String getKode_lengkap() {
        return kode_lengkap;
    }

    public void setKode_lengkap(String kode_lengkap) {
        this.kode_lengkap = kode_lengkap;
    }

    public String getSubbr() {
        return subbr;
    }

    public void setSubbr(String subbr) {
        this.subbr = subbr;
    }

    public String getUnit_kerja() {
        return unit_kerja;
    }

    public void setUnit_kerja(String unit_kerja) {
        this.unit_kerja = unit_kerja;
    }



}
