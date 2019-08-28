package com.application.bris.brisi_pemutus.api.model.request.simpan_disposisi;

import com.google.gson.annotations.SerializedName;

public class ReqSimpanDisposisi {

    @SerializedName("idAplikasi")
    private String idAplikasi;

    @SerializedName("uidAssigned")
    private String uidAssigned;

    @SerializedName("uidAssigner")
    private String uidAssigner;

    public String getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(String idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public String getUidAssigned() {
        return uidAssigned;
    }

    public void setUidAssigned(String uidAssigned) {
        this.uidAssigned = uidAssigned;
    }

    public String getUidAssigner() {
        return uidAssigner;
    }

    public void setUidAssigner(String uidAssigner) {
        this.uidAssigner = uidAssigner;
    }
}
