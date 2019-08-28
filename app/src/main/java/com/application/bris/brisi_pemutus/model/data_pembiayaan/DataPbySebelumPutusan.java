package com.application.bris.brisi_pemutus.model.data_pembiayaan;

import com.google.gson.annotations.SerializedName;

public class DataPbySebelumPutusan {
    @SerializedName("jenisPenggunaanLBUText")
    private String namaAlias;

    @SerializedName("jenisPenggunaanText")
    private String jenisPenggunaanText;

    @SerializedName("hubDebiturDgnBankText")
    private String hubDebiturDgnBankText;

    @SerializedName("sifatKreditText")
    private String sifatKreditText;

    @SerializedName("kategoriKreditLBUText")
    private String kategoriKreditLBUText;

    @SerializedName("bidangUsahaText")
    private String bidangUsahaText;

    @SerializedName("jenisKreditLBUText")
    private String jenisKreditLBUText;

    @SerializedName("sektorEkonomiText")
    private String sektorEkonomiText;

    @SerializedName("sifatKreditLBUText")
    private String sifatKreditLBUText;

    public String getNamaAlias() {
        return namaAlias;
    }

    public void setNamaAlias(String namaAlias) {
        this.namaAlias = namaAlias;
    }

    public String getJenisPenggunaanText() {
        return jenisPenggunaanText;
    }

    public void setJenisPenggunaanText(String jenisPenggunaanText) {
        this.jenisPenggunaanText = jenisPenggunaanText;
    }

    public String getHubDebiturDgnBankText() {
        return hubDebiturDgnBankText;
    }

    public void setHubDebiturDgnBankText(String hubDebiturDgnBankText) {
        this.hubDebiturDgnBankText = hubDebiturDgnBankText;
    }

    public String getSifatKreditText() {
        return sifatKreditText;
    }

    public void setSifatKreditText(String sifatKreditText) {
        this.sifatKreditText = sifatKreditText;
    }

    public String getKategoriKreditLBUText() {
        return kategoriKreditLBUText;
    }

    public void setKategoriKreditLBUText(String kategoriKreditLBUText) {
        this.kategoriKreditLBUText = kategoriKreditLBUText;
    }

    public String getBidangUsahaText() {
        return bidangUsahaText;
    }

    public void setBidangUsahaText(String bidangUsahaText) {
        this.bidangUsahaText = bidangUsahaText;
    }

    public String getJenisKreditLBUText() {
        return jenisKreditLBUText;
    }

    public void setJenisKreditLBUText(String jenisKreditLBUText) {
        this.jenisKreditLBUText = jenisKreditLBUText;
    }

    public String getSektorEkonomiText() {
        return sektorEkonomiText;
    }

    public void setSektorEkonomiText(String sektorEkonomiText) {
        this.sektorEkonomiText = sektorEkonomiText;
    }

    public String getSifatKreditLBUText() {
        return sifatKreditLBUText;
    }

    public void setSifatKreditLBUText(String sifatKreditLBUText) {
        this.sifatKreditLBUText = sifatKreditLBUText;
    }
}
