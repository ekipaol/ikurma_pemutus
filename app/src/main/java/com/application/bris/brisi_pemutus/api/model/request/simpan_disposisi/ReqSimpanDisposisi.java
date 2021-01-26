package com.application.bris.brisi_pemutus.api.model.request.simpan_disposisi;

import com.google.gson.annotations.SerializedName;

public class ReqSimpanDisposisi {

    @SerializedName("idReferal")
    private String idReferal;

    @SerializedName("uidAssigned")
    private String uidAssigned;

    @SerializedName("uidAssigner")
    private String uidAssigner;

    public String getIdReferal() {
        return idReferal;
    }

    public void setIdReferal(String idReferal) {
        this.idReferal = idReferal;
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
