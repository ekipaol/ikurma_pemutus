package com.application.bris.brisi_pemutus.model.monitoring;

import com.google.gson.annotations.SerializedName;

public class MonitoringSalamDigital {
    @SerializedName("NAMA_NASABAH")
    private String namaNasabah;

    @SerializedName("TANGGAL")
    private String tanggalPengajuan;

    @SerializedName("EMAIL")
    private String email;

    @SerializedName("NAMA_PRODUK")
    private String namaProduk;

    @SerializedName("PLAFOND")
    private String plafond;

    @SerializedName("BRANCH_CODE")
    private String branchCode;

    @SerializedName("UNIT_KERJA")
    private String unitKerja;

    @SerializedName("PRESCREENING")
    private String prescreening;

    @SerializedName("DISPOSISI")
    private String disposisi;

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public String getTanggalPengajuan() {
        return tanggalPengajuan;
    }

    public void setTanggalPengajuan(String tanggalPengajuan) {
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getPlafond() {
        return plafond;
    }

    public void setPlafond(String plafond) {
        this.plafond = plafond;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getUnitKerja() {
        return unitKerja;
    }

    public void setUnitKerja(String unitKerja) {
        this.unitKerja = unitKerja;
    }

    public String getPrescreening() {
        return prescreening;
    }

    public void setPrescreening(String prescreening) {
        this.prescreening = prescreening;
    }

    public String getDisposisi() {
        return disposisi;
    }

    public void setDisposisi(String disposisi) {
        this.disposisi = disposisi;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }
}
