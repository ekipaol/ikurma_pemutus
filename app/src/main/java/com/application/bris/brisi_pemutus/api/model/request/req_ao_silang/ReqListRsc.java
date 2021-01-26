package com.application.bris.brisi_pemutus.api.model.request.req_ao_silang;

import com.google.gson.annotations.SerializedName;

public class ReqListRsc {
    @SerializedName("kodeSKK")
    private String kodeSKK;

    public ReqListRsc(String kodeSKK) {
        this.kodeSKK = kodeSKK;
    }
}
