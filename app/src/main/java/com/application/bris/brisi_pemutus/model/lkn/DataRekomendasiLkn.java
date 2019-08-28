package com.application.bris.brisi_pemutus.model.lkn;

import com.google.gson.annotations.SerializedName;

public class DataRekomendasiLkn {
    @SerializedName("IDIR")
    public String IDIR;
    @SerializedName("REKOMENDASI_ANGSURAN")
    public String REKOMENDASI_ANGSURAN;
    @SerializedName("DISPOSABLE_INCOME")
    public String DISPOSABLE_INCOME;

    public String getIDIR() {
        return IDIR;
    }

    public void setIDIR(String IDIR) {
        this.IDIR = IDIR;
    }

    public String getREKOMENDASI_ANGSURAN() {
        return REKOMENDASI_ANGSURAN;
    }

    public void setREKOMENDASI_ANGSURAN(String REKOMENDASI_ANGSURAN) {
        this.REKOMENDASI_ANGSURAN = REKOMENDASI_ANGSURAN;
    }

    public String getDISPOSABLE_INCOME() {
        return DISPOSABLE_INCOME;
    }

    public void setDISPOSABLE_INCOME(String DISPOSABLE_INCOME) {
        this.DISPOSABLE_INCOME = DISPOSABLE_INCOME;
    }
}
