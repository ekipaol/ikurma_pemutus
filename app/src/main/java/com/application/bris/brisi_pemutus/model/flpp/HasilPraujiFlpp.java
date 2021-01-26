package com.application.bris.brisi_pemutus.model.flpp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HasilPraujiFlpp implements Serializable {
    @SerializedName("Nama Nasabah")
    @Expose
    private String namaNasabah;
    @SerializedName("Pekerjaan")
    @Expose
    private String pekerjaan;
    @SerializedName("Nama Badan Hukum")
    @Expose
    private String namaBadanHukum;
    @SerializedName("KTP")
    @Expose
    private String kTP;
    @SerializedName("Kota/Kab Agunan")
    @Expose
    private String kotaKabAgunan;
    @SerializedName("Gaji Pokok")
    @Expose
    private String gajiPokok;
    @SerializedName("Suku Bunga KPR")
    @Expose
    private String sukuBungaKPR;
    @SerializedName("No SP3K")
    @Expose
    private String noSP3K;
    @SerializedName("Harga Rumah")
    @Expose
    private String hargaRumah;
    @SerializedName("KTP Pendamping")
    @Expose
    private String kTPPendamping;
    @SerializedName("Nilai FLPP")
    @Expose
    private String nilaiFLPP;
    @SerializedName("agn_kodepos")
    @Expose
    private String agnKodepos;
    @SerializedName("Kode Jenis KPR")
    @Expose
    private String kodeJenisKPR;
    @SerializedName("Id Aplikasi")
    @Expose
    private String idAplikasi;
    @SerializedName("Badan Hukum")
    @Expose
    private String badanHukum;
    @SerializedName("Luas Bangunan")
    @Expose
    private String luasBangunan;
    @SerializedName("No DKS")
    @Expose
    private String noDKS;
    @SerializedName("luas_tanah")
    @Expose
    private String luasTanah;
    @SerializedName("Jenis Kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("Angsuran")
    @Expose
    private String angsuran;
    @SerializedName("Tenor")
    @Expose
    private String tenor;
    @SerializedName("Nama Pendamping")
    @Expose
    private String namaPendamping;
    @SerializedName("Keterangan")
    @Expose
    private String keterangan;
    @SerializedName("Nilai KPR")
    @Expose
    private String nilaiKPR;
    @SerializedName("Nama Perumahan")
    @Expose
    private String namaPerumahan;
    @SerializedName("Tanggal SP3K")
    @Expose
    private String tanggalSP3K;
    @SerializedName("NPWP")
    @Expose
    private String nPWP;
    @SerializedName("DESC1")
    @Expose
    private String DESC1;
    @SerializedName("FID_STATUS")
    @Expose
    private String STATUS;
    @SerializedName(" Alamat Agunan ")
    @Expose
    private String alamatAgunan;

    public String getDESC1() {
        return DESC1;
    }

    public void setDESC1(String DESC1) {
        this.DESC1 = DESC1;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getNamaBadanHukum() {
        return namaBadanHukum;
    }

    public void setNamaBadanHukum(String namaBadanHukum) {
        this.namaBadanHukum = namaBadanHukum;
    }

    public String getKTP() {
        return kTP;
    }

    public void setKTP(String kTP) {
        this.kTP = kTP;
    }

    public String getKotaKabAgunan() {
        return kotaKabAgunan;
    }

    public void setKotaKabAgunan(String kotaKabAgunan) {
        this.kotaKabAgunan = kotaKabAgunan;
    }

    public String getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(String gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public String getSukuBungaKPR() {
        return sukuBungaKPR;
    }

    public void setSukuBungaKPR(String sukuBungaKPR) {
        this.sukuBungaKPR = sukuBungaKPR;
    }

    public String getNoSP3K() {
        return noSP3K;
    }

    public void setNoSP3K(String noSP3K) {
        this.noSP3K = noSP3K;
    }

    public String getHargaRumah() {
        return hargaRumah;
    }

    public void setHargaRumah(String hargaRumah) {
        this.hargaRumah = hargaRumah;
    }

    public String getKTPPendamping() {
        return kTPPendamping;
    }

    public void setKTPPendamping(String kTPPendamping) {
        this.kTPPendamping = kTPPendamping;
    }

    public String getNilaiFLPP() {
        return nilaiFLPP;
    }

    public void setNilaiFLPP(String nilaiFLPP) {
        this.nilaiFLPP = nilaiFLPP;
    }

    public String getAgnKodepos() {
        return agnKodepos;
    }

    public void setAgnKodepos(String agnKodepos) {
        this.agnKodepos = agnKodepos;
    }

    public String getKodeJenisKPR() {
        return kodeJenisKPR;
    }

    public void setKodeJenisKPR(String kodeJenisKPR) {
        this.kodeJenisKPR = kodeJenisKPR;
    }

    public String getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(String idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public String getBadanHukum() {
        return badanHukum;
    }

    public void setBadanHukum(String badanHukum) {
        this.badanHukum = badanHukum;
    }

    public String getLuasBangunan() {
        return luasBangunan;
    }

    public void setLuasBangunan(String luasBangunan) {
        this.luasBangunan = luasBangunan;
    }

    public String getNoDKS() {
        return noDKS;
    }

    public void setNoDKS(String noDKS) {
        this.noDKS = noDKS;
    }

    public String getLuasTanah() {
        return luasTanah;
    }

    public void setLuasTanah(String luasTanah) {
        this.luasTanah = luasTanah;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAngsuran() {
        return angsuran;
    }

    public void setAngsuran(String angsuran) {
        this.angsuran = angsuran;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String getNamaPendamping() {
        return namaPendamping;
    }

    public void setNamaPendamping(String namaPendamping) {
        this.namaPendamping = namaPendamping;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNilaiKPR() {
        return nilaiKPR;
    }

    public void setNilaiKPR(String nilaiKPR) {
        this.nilaiKPR = nilaiKPR;
    }

    public String getNamaPerumahan() {
        return namaPerumahan;
    }

    public void setNamaPerumahan(String namaPerumahan) {
        this.namaPerumahan = namaPerumahan;
    }

    public String getTanggalSP3K() {
        return tanggalSP3K;
    }

    public void setTanggalSP3K(String tanggalSP3K) {
        this.tanggalSP3K = tanggalSP3K;
    }

    public String getNPWP() {
        return nPWP;
    }

    public void setNPWP(String nPWP) {
        this.nPWP = nPWP;
    }

    public String getAlamatAgunan() {
        return alamatAgunan;
    }

    public void setAlamatAgunan(String alamatAgunan) {
        this.alamatAgunan = alamatAgunan;
    }
}
