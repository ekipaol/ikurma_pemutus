package com.application.bris.brisi_pemutus.model.kantor_cabang;

import com.google.gson.annotations.SerializedName;

public class KantorCabang {
    @SerializedName("SBDESC")
    private String sb_desc;
    @SerializedName("KODE_CABANG")
    private String kode_cabang;
    @SerializedName("JUMLAH_PINCAPEM_AKTIF")
    private String jumlah_pincapem_aktif;

    public String getJumlah_pincapem_aktif() {
        return jumlah_pincapem_aktif;
    }

    public void setJumlah_pincapem_aktif(String jumlah_pincapem_aktif) {
        this.jumlah_pincapem_aktif = jumlah_pincapem_aktif;
    }

    public String getKode_cabang() {
        return kode_cabang;
    }

    public void setKode_cabang(String kode_cabang) {
        this.kode_cabang = kode_cabang;
    }

    public String getSb_desc() {
        return sb_desc;
    }

    public void setSb_desc(String sb_desc) {
        this.sb_desc = sb_desc;
    }
}
