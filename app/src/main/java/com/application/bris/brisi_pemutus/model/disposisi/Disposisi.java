package com.application.bris.brisi_pemutus.model.disposisi;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Nullable;

public class Disposisi implements Serializable {

    @SerializedName("FID_PHOTO_KTP")
    private String FID_PHOTO_KTP;
    @SerializedName("RT_KTP")
    private String RT_KTP;
    @SerializedName("RW_KTP")
    private String RW_KTP;
    @SerializedName("RTRW_KTP")
    private String RTRW_KTP;
    @SerializedName("TGL_CREATED")
    private String TGL_CREATED;
    @SerializedName("KODE_PRODUK")
    private String KODE_PRODUK;
    @SerializedName("KOTA_KTP")
    private String KOTA_KTP;
    @SerializedName("KODE_POS_KTP")
    private String KODE_POS_KTP;
    @SerializedName("PROV_KTP")
    private String PROV_KTP;
    @SerializedName("NO_KTP")
    private String NO_KTP;
    @SerializedName("KODE_UNIK")
    private String KODE_UNIK;
    @SerializedName("ALAMAT_KTP")
    private String ALAMAT_KTP;
    @SerializedName("EMAIL")
    private String EMAIL;
    @SerializedName("TIPE")
    private String TIPE;
    @SerializedName("NAMA_LENGKAP")
    private String NAMA_LENGKAP;
    @SerializedName("JANGKA_WAKTU")
    private String JANGKA_WAKTU;
    @SerializedName("PLAFOND")
    private String PLAFOND;
    @SerializedName("NO_HP")
    private String NO_HP;
    @SerializedName("NAMA_PRODUK")
    private String NAMA_PRODUK;
    @SerializedName("KEL_KTP")
    private String KEL_KTP;
    @SerializedName("FID_APLIKASI")
    private String FID_APLIKASI;
    @SerializedName("ID")
    private String ID;
    @SerializedName("KEC_KTP")
    private String KEC_KTP;
    @Nullable
    @SerializedName("NAMA_ASSIGNED")
    private String NAMA_ASSIGNED;
    @Nullable
    @SerializedName("UID_ASSIGNED")
    private String UID_ASSIGNED;
    @Nullable
    @SerializedName("TGL_ASSIGNED")
    private String TANGGAL_ASSIGNED;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Nullable
    public String getTANGGAL_ASSIGNED() {
        return TANGGAL_ASSIGNED;
    }

    public void setTANGGAL_ASSIGNED(@Nullable String TANGGAL_ASSIGNED) {
        this.TANGGAL_ASSIGNED = TANGGAL_ASSIGNED;
    }

    public String getNAMA_ASSIGNED() {
        return NAMA_ASSIGNED;
    }

    public void setNAMA_ASSIGNED(String NAMA_ASSIGNED) {
        this.NAMA_ASSIGNED = NAMA_ASSIGNED;
    }

    public String getUID_ASSIGNED() {
        return UID_ASSIGNED;
    }

    public void setUID_ASSIGNED(String UID_ASSIGNED) {
        this.UID_ASSIGNED = UID_ASSIGNED;
    }

    public String getRT_KTP() {
        return RT_KTP;
    }

    public String getRTRW_KTP() {
        return RTRW_KTP;
    }

    public void setRTRW_KTP(String RTRW_KTP) {
        this.RTRW_KTP = RTRW_KTP;
    }

    public void setRT_KTP(String RT_KTP) {
        this.RT_KTP = RT_KTP;
    }

    public String getRW_KTP() {
        return RW_KTP;
    }

    public void setRW_KTP(String RW_KTP) {
        this.RW_KTP = RW_KTP;
    }

    public String getFID_PHOTO_KTP() {
        return FID_PHOTO_KTP;
    }

    public void setFID_PHOTO_KTP(String FID_PHOTO_KTP) {
        this.FID_PHOTO_KTP = FID_PHOTO_KTP;
    }



    public String getTGL_CREATED() {
        return TGL_CREATED;
    }

    public void setTGL_CREATED(String TGL_CREATED) {
        this.TGL_CREATED = TGL_CREATED;
    }

    public String getKODE_PRODUK() {
        return KODE_PRODUK;
    }

    public void setKODE_PRODUK(String KODE_PRODUK) {
        this.KODE_PRODUK = KODE_PRODUK;
    }

    public String getKOTA_KTP() {
        return KOTA_KTP;
    }

    public void setKOTA_KTP(String KOTA_KTP) {
        this.KOTA_KTP = KOTA_KTP;
    }

    public String getKODE_POS_KTP() {
        return KODE_POS_KTP;
    }

    public void setKODE_POS_KTP(String KODE_POS_KTP) {
        this.KODE_POS_KTP = KODE_POS_KTP;
    }

    public String getPROV_KTP() {
        return PROV_KTP;
    }

    public void setPROV_KTP(String PROV_KTP) {
        this.PROV_KTP = PROV_KTP;
    }

    public String getNO_KTP() {
        return NO_KTP;
    }

    public void setNO_KTP(String NO_KTP) {
        this.NO_KTP = NO_KTP;
    }

    public String getKODE_UNIK() {
        return KODE_UNIK;
    }

    public void setKODE_UNIK(String KODE_UNIK) {
        this.KODE_UNIK = KODE_UNIK;
    }


    public String getALAMAT_KTP() {
        return ALAMAT_KTP;
    }

    public void setALAMAT_KTP(String ALAMAT_KTP) {
        this.ALAMAT_KTP = ALAMAT_KTP;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getTIPE() {
        return TIPE;
    }

    public void setTIPE(String TIPE) {
        this.TIPE = TIPE;
    }

    public String getNAMA_LENGKAP() {
        return NAMA_LENGKAP;
    }

    public void setNAMA_LENGKAP(String NAMA_LENGKAP) {
        this.NAMA_LENGKAP = NAMA_LENGKAP;
    }

    public String getJANGKA_WAKTU() {
        return JANGKA_WAKTU;
    }

    public void setJANGKA_WAKTU(String JANGKA_WAKTU) {
        this.JANGKA_WAKTU = JANGKA_WAKTU;
    }

    public String getPLAFOND() {
        return PLAFOND;
    }

    public void setPLAFOND(String PLAFOND) {
        this.PLAFOND = PLAFOND;
    }

    public String getNO_HP() {
        return NO_HP;
    }

    public void setNO_HP(String NO_HP) {
        this.NO_HP = NO_HP;
    }

    public String getNAMA_PRODUK() {
        return NAMA_PRODUK;
    }

    public void setNAMA_PRODUK(String NAMA_PRODUK) {
        this.NAMA_PRODUK = NAMA_PRODUK;
    }

    public String getKEL_KTP() {
        return KEL_KTP;
    }

    public void setKEL_KTP(String KEL_KTP) {
        this.KEL_KTP = KEL_KTP;
    }

    public String getFID_APLIKASI() {
        return FID_APLIKASI;
    }

    public void setFID_APLIKASI(String FID_APLIKASI) {
        this.FID_APLIKASI = FID_APLIKASI;
    }

    public String getKEC_KTP() {
        return KEC_KTP;
    }

    public void setKEC_KTP(String KEC_KTP) {
        this.KEC_KTP = KEC_KTP;
    }
}
