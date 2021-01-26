package com.application.bris.brisi_pemutus.api.model.request.req_ao_silang;

import com.google.gson.annotations.SerializedName;

public class ReqListUser {
    @SerializedName("kodeCabang")
    private String kodeCabang;
    @SerializedName("fidRole")
    private String fidRole;

    public ReqListUser(String kodeCabang, String fidRole) {
        this.kodeCabang = kodeCabang;
        this.fidRole = fidRole;
    }
}
