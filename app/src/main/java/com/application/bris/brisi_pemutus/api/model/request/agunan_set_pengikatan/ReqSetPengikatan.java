package com.application.bris.brisi_pemutus.api.model.request.agunan_set_pengikatan;

import com.google.gson.annotations.SerializedName;

public class ReqSetPengikatan {

    @SerializedName("idAgunan")
    private String idAgunan;
    @SerializedName("idApl")
    private String idApl;
    @SerializedName("idCif")
    private String idCif;
    @SerializedName("fidjenisAgunan")
    private int fidjenisAgunan;


    public String getIdAgunan() {
        return idAgunan;
    }

    public void setIdAgunan(String idAgunan) {
        this.idAgunan = idAgunan;
    }

    public String getIdApl() {
        return idApl;
    }

    public void setIdApl(String idApl) {
        this.idApl = idApl;
    }

    public String getIdCif() {
        return idCif;
    }

    public void setIdCif(String idCif) {
        this.idCif = idCif;
    }

    public int getFidjenisAgunan() {
        return fidjenisAgunan;
    }

    public void setFidjenisAgunan(int fidjenisAgunan) {
        this.fidjenisAgunan = fidjenisAgunan;
    }
}
