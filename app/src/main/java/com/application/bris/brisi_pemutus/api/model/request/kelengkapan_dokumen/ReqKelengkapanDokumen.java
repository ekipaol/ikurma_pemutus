package com.application.bris.brisi_pemutus.api.model.request.kelengkapan_dokumen;

import com.google.gson.annotations.SerializedName;

public class ReqKelengkapanDokumen {

    @SerializedName("idAplikasi")
    private int id_aplikasi;

    public int getId_aplikasi() {
        return id_aplikasi;
    }

    public void setId_aplikasi(int id_aplikasi) {
        this.id_aplikasi = id_aplikasi;
    }
}
