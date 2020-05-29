package com.application.bris.brisi_pemutus.api.model.request.scoring;

import com.google.gson.annotations.SerializedName;

public class ReqScoring {
    @SerializedName("cif")
    private int cif;
    @SerializedName("idAplikasi")
    private int idAplikasi;

    public ReqScoring(int cif, int idAplikasi) {
        this.cif = cif;
        this.idAplikasi = idAplikasi;
    }
    public ReqScoring() {

    }

    public void setCif(int cif) {
        this.cif = cif;
    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }
}
