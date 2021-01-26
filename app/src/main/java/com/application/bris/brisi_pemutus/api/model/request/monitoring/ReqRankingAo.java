package com.application.bris.brisi_pemutus.api.model.request.monitoring;

import com.google.gson.annotations.SerializedName;

public class ReqRankingAo {

    @SerializedName("fidRole")
    private String fidRole;

    public String getFidRole() {
        return fidRole;
    }

    public void setFidRole(String fidRole) {
        this.fidRole = fidRole;
    }

}