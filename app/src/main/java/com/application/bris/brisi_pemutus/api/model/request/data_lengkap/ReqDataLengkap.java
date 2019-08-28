package com.application.bris.brisi_pemutus.api.model.request.data_lengkap;

import com.google.gson.annotations.SerializedName;

public class ReqDataLengkap {
    @SerializedName("cif")
    private String cif;
    @SerializedName("idAplikasi")
    private String idAplikasi;

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(String idAplikasi) {
        this.idAplikasi = idAplikasi;
    }
}
