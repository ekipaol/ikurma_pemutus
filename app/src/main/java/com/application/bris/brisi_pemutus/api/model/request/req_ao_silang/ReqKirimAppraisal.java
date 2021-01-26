package com.application.bris.brisi_pemutus.api.model.request.req_ao_silang;

import com.google.gson.annotations.SerializedName;

public class ReqKirimAppraisal {
    @SerializedName("idApl")
    private int idApl;
    @SerializedName("uidApraisal")
    private int uidApraisal;

    public ReqKirimAppraisal(int idApl, int uidApraisal) {
        this.idApl = idApl;
        this.uidApraisal = uidApraisal;
    }
}
