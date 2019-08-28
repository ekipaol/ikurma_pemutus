package com.application.bris.brisi_pemutus.api.model.request.agunan;

import com.google.gson.annotations.SerializedName;

public class ReqAgunan {
    @SerializedName("idAgunan")
    private String idAgunan;


    @SerializedName("idApl")
    private String idApl;

    @SerializedName("idCif")
    private String idCif;

    public String getIdCif() {
        return idCif;
    }

    public void setIdCif(String idCif) {
        this.idCif = idCif;
    }

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
}
