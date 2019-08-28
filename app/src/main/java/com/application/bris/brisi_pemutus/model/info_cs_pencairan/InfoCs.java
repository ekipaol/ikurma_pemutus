package com.application.bris.brisi_pemutus.model.info_cs_pencairan;

import com.google.gson.annotations.SerializedName;

public class InfoCs {
    @SerializedName("NAMA_PETUGAS")
    private String nama_petugas;

    public String getNama_petugas() {
        return nama_petugas;
    }

    public void setNama_petugas(String nama_petugas) {
        this.nama_petugas = nama_petugas;
    }
}
