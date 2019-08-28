package com.application.bris.brisi_pemutus.model.agunan_terikat;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AgunanTerikat implements Serializable {

    @SerializedName("FID_CIF_LAS")
    private String FID_CIF_LAS;
    @SerializedName("PLAFOND_INDUK")
    private String PLAFOND_INDUK;
    @SerializedName("FID_JENIS_AGUNAN")
    private String FID_JENIS_AGUNAN;
    @SerializedName("FID_AGUNAN")
    private String FID_AGUNAN;
    @SerializedName("ID_APLIKASI")
    private String ID_APLIKASI;
    @SerializedName("DESC_AGUNAN")
    private String DESC_AGUNAN;
    @SerializedName("NAMA_DEBITUR_1")
    private String NAMA_DEBITUR_1;
    @SerializedName("KATEGORI")
    private String KATEGORI;
    @SerializedName("KATEGORI_PHOTO")
    private String KATEGORI_PHOTO;
    @SerializedName("FID_STATUS")
    private String FID_STATUS;
    @SerializedName("ID")
    private String id_foto;

    public String getId_foto() {
        return id_foto;
    }

    public void setId_foto(String id_foto) {
        this.id_foto = id_foto;
    }

    public String getKATEGORI() {
        return KATEGORI;
    }

    public void setKATEGORI(String KATEGORI) {
        this.KATEGORI = KATEGORI;
    }

    public String getKATEGORI_PHOTO() {
        return KATEGORI_PHOTO;
    }

    public void setKATEGORI_PHOTO(String KATEGORI_PHOTO) {
        this.KATEGORI_PHOTO = KATEGORI_PHOTO;
    }

    public String getFID_STATUS() {
        return FID_STATUS;
    }

    public void setFID_STATUS(String FID_STATUS) {
        this.FID_STATUS = FID_STATUS;
    }

    public String getFID_CIF_LAS() {
        return FID_CIF_LAS;
    }

    public void setFID_CIF_LAS(String FID_CIF_LAS) {
        this.FID_CIF_LAS = FID_CIF_LAS;
    }

    public String getPLAFOND_INDUK() {
        return PLAFOND_INDUK;
    }

    public void setPLAFOND_INDUK(String PLAFOND_INDUK) {
        this.PLAFOND_INDUK = PLAFOND_INDUK;
    }

    public String getFID_JENIS_AGUNAN() {
        return FID_JENIS_AGUNAN;
    }

    public void setFID_JENIS_AGUNAN(String FID_JENIS_AGUNAN) {
        this.FID_JENIS_AGUNAN = FID_JENIS_AGUNAN;
    }

    public String getFID_AGUNAN() {
        return FID_AGUNAN;
    }

    public void setFID_AGUNAN(String FID_AGUNAN) {
        this.FID_AGUNAN = FID_AGUNAN;
    }

    public String getID_APLIKASI() {
        return ID_APLIKASI;
    }

    public void setID_APLIKASI(String ID_APLIKASI) {
        this.ID_APLIKASI = ID_APLIKASI;
    }

    public String getDESC_AGUNAN() {
        return DESC_AGUNAN;
    }

    public void setDESC_AGUNAN(String DESC_AGUNAN) {
        this.DESC_AGUNAN = DESC_AGUNAN;
    }

    public String getNAMA_DEBITUR_1() {
        return NAMA_DEBITUR_1;
    }

    public void setNAMA_DEBITUR_1(String NAMA_DEBITUR_1) {
        this.NAMA_DEBITUR_1 = NAMA_DEBITUR_1;
    }

}
