package com.application.bris.brisi_pemutus.model.ao_silang_list_kanwil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListKanwil {

    @SerializedName("TELP_KANTOR")
    @Expose
    private String TELP_KANTOR;
    @SerializedName("FAX_KANTOR")
    @Expose
    private String FAX_KANTOR;
    @SerializedName("KODE_SKPP")
    @Expose
    private String KODE_SKPP;
    @SerializedName("SBDESC")
    @Expose
    private String SBDESC;
    @SerializedName("ALAMAT_KANTOR")
    @Expose
    private String ALAMAT_KANTOR;
    @SerializedName("KODE_LENGKAP")
    @Expose
    private String KODE_LENGKAP;
    @SerializedName("SUBBR")
    @Expose
    private String SUBBR;
    @SerializedName("UNIT_KERJA")
    @Expose
    private String UNIT_KERJA;
    @SerializedName("KODE_KANWIL")
    @Expose
    private String KODE_KANWIL;
    @SerializedName("KOTA")
    @Expose
    private String KOTA;
    @SerializedName("KODE_OL")
    @Expose
    private String KODE_OL;
    @SerializedName("KODE_CABANG_INDUK")
    @Expose
    private String KODE_CABANG_INDUK;
    @SerializedName("KODE_POS")
    @Expose
    private String KODE_POS;

    public String getTELP_KANTOR() {
        return TELP_KANTOR;
    }

    public String getFAX_KANTOR() {
        return FAX_KANTOR;
    }

    public String getKODE_SKPP() {
        return KODE_SKPP;
    }

    public String getSBDESC() {
        return SBDESC;
    }

    public String getALAMAT_KANTOR() {
        return ALAMAT_KANTOR;
    }

    public String getKODE_LENGKAP() {
        return KODE_LENGKAP;
    }

    public String getSUBBR() {
        return SUBBR;
    }

    public String getUNIT_KERJA() {
        return UNIT_KERJA;
    }

    public String getKODE_KANWIL() {
        return KODE_KANWIL;
    }

    public String getKOTA() {
        return KOTA;
    }

    public String getKODE_OL() {
        return KODE_OL;
    }

    public String getKODE_CABANG_INDUK() {
        return KODE_CABANG_INDUK;
    }

    public String getKODE_POS() {
        return KODE_POS;
    }
}
