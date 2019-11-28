package com.application.bris.brisi_pemutus.api.model.request.id_aplikasi;

import com.google.gson.annotations.SerializedName;

public class ReqIdAplikasi {

    @SerializedName("idAplikasi")
    private int idAplikasi;

    public int getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }
}
