package com.application.bris.brisi_pemutus.api.model.request.req_ao_silang;

import com.google.gson.annotations.SerializedName;

public class ReqListSkk {
    @SerializedName("kodeKanwil")
    private String kodeKanwil;

    public ReqListSkk(String kodeKanwil) {
        this.kodeKanwil = kodeKanwil;
    }
}
