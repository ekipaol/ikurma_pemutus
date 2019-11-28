package com.application.bris.brisi_pemutus.api.model.request.performance_cabang;

import com.google.gson.annotations.SerializedName;

public class ReqPerformanceCabang {

    @SerializedName("kodeKanwil")
    private String kodeKanwil;

    public String getKodeKanwil() {
        return kodeKanwil;
    }

    public void setKodeKanwil(String kodeKanwil) {
        this.kodeKanwil = kodeKanwil;
    }
}
