package com.application.bris.brisi_pemutus.model.agunan_set_pengikatan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AgunanPengikatan {
    @SerializedName("PlafondCover")
    private String PlafondCover;
    @SerializedName("namaDebitur")
    private String namaDebitur;
    @SerializedName("tipeProduk")
    private String tipeProduk;
    @SerializedName("KlasifikasiAgunan")
    private String KlasifikasiAgunan;
    @SerializedName("JenisPengikatan")
    private String JenisPengikatan;
    @SerializedName("CollIdSyiar")
    private String CollIdSyiar;
    @SerializedName("NilaiMarket")
    private String NilaiMarket;
    @SerializedName("PengikatanAplikasi")
    private String PengikatanAplikasi;
    @SerializedName("DescPengikatan")
    private String DescPengikatan;


    public String getPlafondCover() {
        return PlafondCover;
    }

    public void setPlafondCover(String plafondCover) {
        PlafondCover = plafondCover;
    }

    public String getNamaDebitur() {
        return namaDebitur;
    }

    public void setNamaDebitur(String namaDebitur) {
        this.namaDebitur = namaDebitur;
    }

    public String getTipeProduk() {
        return tipeProduk;
    }

    public void setTipeProduk(String tipeProduk) {
        this.tipeProduk = tipeProduk;
    }

    public String getKlasifikasiAgunan() {
        return KlasifikasiAgunan;
    }

    public void setKlasifikasiAgunan(String klasifikasiAgunan) {
        KlasifikasiAgunan = klasifikasiAgunan;
    }

    public String getJenisPengikatan() {
        return JenisPengikatan;
    }

    public void setJenisPengikatan(String jenisPengikatan) {
        JenisPengikatan = jenisPengikatan;
    }

    public String getCollIdSyiar() {
        return CollIdSyiar;
    }

    public void setCollIdSyiar(String collIdSyiar) {
        CollIdSyiar = collIdSyiar;
    }

    public String getNilaiMarket() {
        return NilaiMarket;
    }

    public void setNilaiMarket(String nilaiMarket) {
        NilaiMarket = nilaiMarket;
    }

    public String getPengikatanAplikasi() {
        return PengikatanAplikasi;
    }

    public void setPengikatanAplikasi(String pengikatanAplikasi) {
        PengikatanAplikasi = pengikatanAplikasi;
    }

    public String getDescPengikatan() {
        return DescPengikatan;
    }

    public void setDescPengikatan(String descPengikatan) {
        DescPengikatan = descPengikatan;
    }
}
