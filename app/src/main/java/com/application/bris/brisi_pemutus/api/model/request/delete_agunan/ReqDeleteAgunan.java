package com.application.bris.brisi_pemutus.api.model.request.delete_agunan;

import com.google.gson.annotations.SerializedName;

public class ReqDeleteAgunan {
    @SerializedName("idApl")
    private int idApl;

    @SerializedName("idAgunan")
    private int idAgunan;

    @SerializedName("idApl")
    private int idCif;

    public int getIdApl() {
        return idApl;
    }

    public void setIdApl(int idApl) {
        this.idApl = idApl;
    }

    public int getIdAgunan() {
        return idAgunan;
    }

    public void setIdAgunan(int idAgunan) {
        this.idAgunan = idAgunan;
    }

    public int getIdCif() {
        return idCif;
    }

    public void setIdCif(int idCif) {
        this.idCif = idCif;
    }
}
