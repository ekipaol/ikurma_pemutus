package com.application.bris.brisi_pemutus.api.model.request.validasi_data_finansial;


import com.google.gson.annotations.SerializedName;

public class ValidasiDataFinansialKmg {

    @SerializedName("idAplikasi")
    private String idAplikasi;
    @SerializedName("PlafondUsul")
    private String PlafondUsul;
    @SerializedName("RPCfinal")
    private String RPCfinal;
    @SerializedName("MAKSIMUM_PLAFOND")
    private String MAKSIMUM_PLAFOND;
    @SerializedName("INPUT_PERMOHONAN")
    private String INPUT_PERMOHONAN;
    @SerializedName("cookie_jw_max")
    private String cookie_jw_max;
    @SerializedName("JANGKA_WAKTU")
    private String JANGKA_WAKTU;
    @SerializedName("MAKS_TENOR_MPP")
    private String MAKS_TENOR_MPP;
    @SerializedName("OMZET_PERBULAN")
    private String OMZET_PERBULAN;


    public String getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(String idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public String getPlafondUsul() {
        return PlafondUsul;
    }

    public void setPlafondUsul(String plafondUsul) {
        PlafondUsul = plafondUsul;
    }

    public String getRPCfinal() {
        return RPCfinal;
    }

    public void setRPCfinal(String RPCfinal) {
        this.RPCfinal = RPCfinal;
    }

    public String getMAKSIMUM_PLAFOND() {
        return MAKSIMUM_PLAFOND;
    }

    public void setMAKSIMUM_PLAFOND(String MAKSIMUM_PLAFOND) {
        this.MAKSIMUM_PLAFOND = MAKSIMUM_PLAFOND;
    }

    public String getINPUT_PERMOHONAN() {
        return INPUT_PERMOHONAN;
    }

    public void setINPUT_PERMOHONAN(String INPUT_PERMOHONAN) {
        this.INPUT_PERMOHONAN = INPUT_PERMOHONAN;
    }

    public String getCookie_jw_max() {
        return cookie_jw_max;
    }

    public void setCookie_jw_max(String cookie_jw_max) {
        this.cookie_jw_max = cookie_jw_max;
    }

    public String getJANGKA_WAKTU() {
        return JANGKA_WAKTU;
    }

    public void setJANGKA_WAKTU(String JANGKA_WAKTU) {
        this.JANGKA_WAKTU = JANGKA_WAKTU;
    }

    public String getMAKS_TENOR_MPP() {
        return MAKS_TENOR_MPP;
    }

    public void setMAKS_TENOR_MPP(String MAKS_TENOR_MPP) {
        this.MAKS_TENOR_MPP = MAKS_TENOR_MPP;
    }

    public String getOMZET_PERBULAN() {
        return OMZET_PERBULAN;
    }

    public void setOMZET_PERBULAN(String OMZET_PERBULAN) {
        this.OMZET_PERBULAN = OMZET_PERBULAN;
    }
}

