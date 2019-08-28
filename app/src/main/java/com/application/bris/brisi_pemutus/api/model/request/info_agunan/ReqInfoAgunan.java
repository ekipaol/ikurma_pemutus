package com.application.bris.brisi_pemutus.api.model.request.info_agunan;

import com.google.gson.annotations.SerializedName;

public class ReqInfoAgunan {

    @SerializedName("idCif")
    private int idCif;
    @SerializedName("idApl")
    private String idApl;

    public int getIdCif() {
        return idCif;
    }

    public void setIdCif(int idCif) {
        this.idCif = idCif;
    }

    public String getIdApl() {
        return idApl;
    }

    public void setIdApl(String idApl) {
        this.idApl = idApl;
    }
}
