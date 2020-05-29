package com.application.bris.brisi_pemutus.model.ao_silang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AoSilang {

    @SerializedName("namaAoSilang")
    @Expose
    private String namaAoSilang;

    @SerializedName("namaKantorAoSilang")
    @Expose
    private String namaKantorAoSilang;

    @SerializedName("namaPartnerAoSilang")
    @Expose
    private String namaPartnerAoSilang;

    @SerializedName("namaKantorPartnerAoSilang")
    @Expose
    private String namaKantorPartnerAoSilang;

    @SerializedName("nikAoSilang")
    @Expose
    private String nikAoSilang;

    @SerializedName("nikPartnerAoSilang")
    @Expose
    private String nikPartnerAoSilang;

    public String getNikAoSilang() {
        return nikAoSilang;
    }

    public void setNikAoSilang(String nikAoSilang) {
        this.nikAoSilang = nikAoSilang;
    }

    public String getNikPartnerAoSilang() {
        return nikPartnerAoSilang;
    }

    public void setNikPartnerAoSilang(String nikPartnerAoSilang) {
        this.nikPartnerAoSilang = nikPartnerAoSilang;
    }

    public String getNamaAoSilang() {
        return namaAoSilang;
    }

    public void setNamaAoSilang(String namaAoSilang) {
        this.namaAoSilang = namaAoSilang;
    }

    public String getNamaKantorAoSilang() {
        return namaKantorAoSilang;
    }

    public void setNamaKantorAoSilang(String namaKantorAoSilang) {
        this.namaKantorAoSilang = namaKantorAoSilang;
    }

    public String getNamaPartnerAoSilang() {
        return namaPartnerAoSilang;
    }

    public void setNamaPartnerAoSilang(String namaPartnerAoSilang) {
        this.namaPartnerAoSilang = namaPartnerAoSilang;
    }

    public String getNamaKantorPartnerAoSilang() {
        return namaKantorPartnerAoSilang;
    }

    public void setNamaKantorPartnerAoSilang(String namaKantorPartnerAoSilang) {
        this.namaKantorPartnerAoSilang = namaKantorPartnerAoSilang;
    }
}
