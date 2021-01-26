package com.application.bris.brisi_pemutus.model.kelengkapan_dokumen;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KelengkapanDokumen implements Serializable {

    @SerializedName("SURAT_PERNYATAAN_NASABAH")
    private boolean suratPernyataanNasabah;
    @SerializedName("FC_NPWP_PRIBADI")
    private boolean fcNpwpPribadi;
    @SerializedName("FC_SURAT_NIKAH")
    private boolean fcSuratNikah;
    @SerializedName("FC_KTP")
    private boolean fcKtp;
    @SerializedName("LAPORAN_KEUANGAN")
    private boolean laporanKeuangan;
    @SerializedName("PAS_PHOTO")
    private boolean pasPhoto;
    @SerializedName("NO_KK")
    private String noKk;
    @SerializedName("APLIKASI_PERMOHONAN")
    private boolean aplikasiPermohonan;
    @SerializedName("NO_KTP")
    private String noKtp;
    @SerializedName("FC_KK")
    private boolean fcKk;
    @SerializedName("FC_SIUP")
    private boolean fcSiup;
    @SerializedName("SURAT_KETERANGAN_LUNAS_KUR")
    private boolean suratKeteranganLunasKur;
    @SerializedName("SURAT_PERNYATAAN_KUR")
    private boolean suratPernyataanKur;

    @SerializedName("NO_SKU")
    public String noSku;

    @SerializedName("ID_DOKUMEN_APLIKASI")
    private int id_foto_dokumen_aplikasi;
    @SerializedName("ID_DOKUMEN_SURAT_NIKAH")
    private int id_foto_dokumen_surat_nikah;
    @SerializedName("ID_DOKUMEN_KK")
    private int id_foto_dokumen_kk;
    @SerializedName("ID_DOKUMEN_KTP")
    private int id_foto_dokumen_ktp;
    @SerializedName("ID_DOKUMEN_SIUP")
    private int id_foto_dokumen_siup;
    @SerializedName("ID_DOKUMEN_NPWP_PRIBADI")
    private int id_foto_dokumen_npwp;
    @SerializedName("ID_DOKUMEN_LAPORAN_KEUANGAN")
    private int id_foto_dokumen_laporan_keuangan;
    @SerializedName("ID_DOKUMEN_SURAT_PERNYATAAN_KUR")
    private int id_foto_dokumen_pernyataan_kur;
    @SerializedName("ID_DOKUMEN_SURAT_PERNYATAAN_NASABAH")
    private int id_foto_dokumen_pernyataan_nasabah;
    @SerializedName("ID_DOKUMEN_SURAT_KETERANGAN_LUNAS_KUR")
    private int id_foto_dokumen_lunas_kur;
    @SerializedName("ID_DOKUMEN_PAS_PHOTO")
    private int id_foto_dokumen_pas_photo;

    public String getNoSku() {
        return noSku;
    }

    public void setNoSku(String noSku) {
        this.noSku = noSku;
    }

    public boolean getSuratPernyataanNasabah() {
        return suratPernyataanNasabah;
    }

    public void setSuratPernyataanNasabah(boolean suratPernyataanNasabah) {
        this.suratPernyataanNasabah = suratPernyataanNasabah;
    }

    public boolean getFcNpwpPribadi() {
        return fcNpwpPribadi;
    }

    public void setFcNpwpPribadi(boolean fcNpwpPribadi) {
        this.fcNpwpPribadi = fcNpwpPribadi;
    }

    public boolean getFcSuratNikah() {
        return fcSuratNikah;
    }

    public void setFcSuratNikah(boolean fcSuratNikah) {
        this.fcSuratNikah = fcSuratNikah;
    }

    public boolean getFcKtp() {
        return fcKtp;
    }

    public void setFcKtp(boolean fcKtp) {
        this.fcKtp = fcKtp;
    }

    public boolean getLaporanKeuangan() {
        return laporanKeuangan;
    }

    public void setLaporanKeuangan(boolean laporanKeuangan) {
        this.laporanKeuangan = laporanKeuangan;
    }

    public boolean getPasPhoto() {
        return pasPhoto;
    }

    public void setPasPhoto(boolean pasPhoto) {
        this.pasPhoto = pasPhoto;
    }

    public String getNoKk() {
        return noKk;
    }

    public void setNoKk(String noKk) {
        this.noKk = noKk;
    }

    public boolean getAplikasiPermohonan() {
        return aplikasiPermohonan;
    }

    public void setAplikasiPermohonan(boolean aplikasiPermohonan) {
        this.aplikasiPermohonan = aplikasiPermohonan;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public boolean getFcKk() {
        return fcKk;
    }

    public void setFcKk(boolean fcKk) {
        this.fcKk = fcKk;
    }

    public boolean getFcSiup() {
        return fcSiup;
    }

    public void setFcSiup(boolean fcSiup) {
        this.fcSiup = fcSiup;
    }

    public boolean getSuratKeteranganLunasKur() {
        return suratKeteranganLunasKur;
    }

    public void setSuratKeteranganLunasKur(boolean suratKeteranganLunasKur) {
        this.suratKeteranganLunasKur = suratKeteranganLunasKur;
    }

    public boolean getSuratPernyataanKur() {
        return suratPernyataanKur;
    }

    public void setSuratPernyataanKur(boolean suratPernyataanKur) {
        this.suratPernyataanKur = suratPernyataanKur;
    }

    public int getId_foto_dokumen_aplikasi() {
        return id_foto_dokumen_aplikasi;
    }

    public void setId_foto_dokumen_aplikasi(int id_foto_dokumen_aplikasi) {
        this.id_foto_dokumen_aplikasi = id_foto_dokumen_aplikasi;
    }

    public int getId_foto_dokumen_surat_nikah() {
        return id_foto_dokumen_surat_nikah;
    }

    public void setId_foto_dokumen_surat_nikah(int id_foto_dokumen_surat_nikah) {
        this.id_foto_dokumen_surat_nikah = id_foto_dokumen_surat_nikah;
    }

    public int getId_foto_dokumen_kk() {
        return id_foto_dokumen_kk;
    }

    public void setId_foto_dokumen_kk(int id_foto_dokumen_kk) {
        this.id_foto_dokumen_kk = id_foto_dokumen_kk;
    }

    public int getId_foto_dokumen_ktp() {
        return id_foto_dokumen_ktp;
    }

    public void setId_foto_dokumen_ktp(int id_foto_dokumen_ktp) {
        this.id_foto_dokumen_ktp = id_foto_dokumen_ktp;
    }

    public int getId_foto_dokumen_siup() {
        return id_foto_dokumen_siup;
    }

    public void setId_foto_dokumen_siup(int id_foto_dokumen_siup) {
        this.id_foto_dokumen_siup = id_foto_dokumen_siup;
    }

    public int getId_foto_dokumen_npwp() {
        return id_foto_dokumen_npwp;
    }

    public void setId_foto_dokumen_npwp(int id_foto_dokumen_npwp) {
        this.id_foto_dokumen_npwp = id_foto_dokumen_npwp;
    }

    public int getId_foto_dokumen_laporan_keuangan() {
        return id_foto_dokumen_laporan_keuangan;
    }

    public void setId_foto_dokumen_laporan_keuangan(int id_foto_dokumen_laporan_keuangan) {
        this.id_foto_dokumen_laporan_keuangan = id_foto_dokumen_laporan_keuangan;
    }

    public int getId_foto_dokumen_pernyataan_kur() {
        return id_foto_dokumen_pernyataan_kur;
    }

    public void setId_foto_dokumen_pernyataan_kur(int id_foto_dokumen_pernyataan_kur) {
        this.id_foto_dokumen_pernyataan_kur = id_foto_dokumen_pernyataan_kur;
    }

    public int getId_foto_dokumen_pernyataan_nasabah() {
        return id_foto_dokumen_pernyataan_nasabah;
    }

    public void setId_foto_dokumen_pernyataan_nasabah(int id_foto_dokumen_pernyataan_nasabah) {
        this.id_foto_dokumen_pernyataan_nasabah = id_foto_dokumen_pernyataan_nasabah;
    }

    public int getId_foto_dokumen_lunas_kur() {
        return id_foto_dokumen_lunas_kur;
    }

    public void setId_foto_dokumen_lunas_kur(int id_foto_dokumen_lunas_kur) {
        this.id_foto_dokumen_lunas_kur = id_foto_dokumen_lunas_kur;
    }

    public int getId_foto_dokumen_pas_photo() {
        return id_foto_dokumen_pas_photo;
    }

    public void setId_foto_dokumen_pas_photo(int id_foto_dokumen_pas_photo) {
        this.id_foto_dokumen_pas_photo = id_foto_dokumen_pas_photo;
    }
}
