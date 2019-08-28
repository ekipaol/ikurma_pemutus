package com.application.bris.brisi_pemutus.api.model.request.history_putusan;

import com.google.gson.annotations.SerializedName;

public class ReqHistoryPutusan {
    @SerializedName("cif")
    private int cif;
    @SerializedName("idAplikasi")
    private int id_aplikasi;

    public ReqHistoryPutusan(int cif, int id_aplikasi) {
        this.cif = cif;
        this.id_aplikasi = id_aplikasi;
    }

    public ReqHistoryPutusan() {

    }

    public int getCif() {
        return cif;
    }

    public void setCif(int cif) {
        this.cif = cif;
    }

    public int getId_aplikasi() {
        return id_aplikasi;
    }

    public void setId_aplikasi(int id_aplikasi) {
        this.id_aplikasi = id_aplikasi;
    }



}


