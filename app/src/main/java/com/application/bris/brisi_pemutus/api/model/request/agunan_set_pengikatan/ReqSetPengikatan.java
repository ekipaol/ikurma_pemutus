package com.application.bris.brisi_pemutus.api.model.request.agunan_set_pengikatan;

import com.google.gson.annotations.SerializedName;

public class ReqSetPengikatan {

    @SerializedName("idAgunan")
    private int idAgunan;
    @SerializedName("idApl")
    private int idApl;
    @SerializedName("idCif")
    private int idCif;
    @SerializedName("fidjenisAgunan")
    private int fidjenisAgunan;


    public int getIdAgunan() {
        return idAgunan;
    }

    public void setIdAgunan(int idAgunan) {
        this.idAgunan = idAgunan;
    }

    public int getIdApl() {
        return idApl;
    }

    public void setIdApl(int idApl) {
        this.idApl = idApl;
    }

    public int getIdCif() {
        return idCif;
    }

    public void setIdCif(int idCif) {
        this.idCif = idCif;
    }

    public int getFidjenisAgunan() {
        return fidjenisAgunan;
    }

    public void setFidjenisAgunan(int fidjenisAgunan) {
        this.fidjenisAgunan = fidjenisAgunan;
    }
}
