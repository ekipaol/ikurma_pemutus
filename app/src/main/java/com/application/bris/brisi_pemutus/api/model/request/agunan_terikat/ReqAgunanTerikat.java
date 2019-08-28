package com.application.bris.brisi_pemutus.api.model.request.agunan_terikat;

import com.google.gson.annotations.SerializedName;

public class ReqAgunanTerikat {

    @SerializedName("idCif")
    private String idCif;
    @SerializedName("idApl")
    private String idApl;

    public String getIdCif() {
        return idCif;
    }

    public void setIdCif(String idCif) {
        this.idCif = idCif;
    }

    public String getIdApl() {
        return idApl;
    }

    public void setIdApl(String idApl) {
        this.idApl = idApl;
    }
}

