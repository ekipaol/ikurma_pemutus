package com.application.bris.brisi_pemutus.api.model.request.sektor_ekonomi;

import com.google.gson.annotations.SerializedName;

public class ReqSektorEkonomi {

    @SerializedName("idAplikasi")
    private int idAplikasi;
    @SerializedName("idRole")
    private int idRole;

    public ReqSektorEkonomi(int idAplikasi, int idRole) {
        this.idAplikasi = idAplikasi;
        this.idRole = idRole;
    }
    public ReqSektorEkonomi() {

    }

    public int getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
