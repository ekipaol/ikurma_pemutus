package com.application.bris.brisi_pemutus.model.kelengkapan_dokumen_agunan;

import com.google.gson.annotations.SerializedName;

public class KelengkapanDokumenAgunan {
    @SerializedName("KATEGORI")
    private String KATEGORI;
    @SerializedName("FID_AGUNAN")
    private String FID_AGUNAN;
    @SerializedName("ID")
    private String ID;
    @SerializedName("NO_SERTIFIKAT")
    private String NO_SERTIFIKAT;

    public String getKATEGORI() {
        return KATEGORI;
    }

    public void setKATEGORI(String KATEGORI) {
        this.KATEGORI = KATEGORI;
    }

    public String getFID_AGUNAN() {
        return FID_AGUNAN;
    }

    public void setFID_AGUNAN(String FID_AGUNAN) {
        this.FID_AGUNAN = FID_AGUNAN;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNO_SERTIFIKAT() {
        return NO_SERTIFIKAT;
    }

    public void setNO_SERTIFIKAT(String NO_SERTIFIKAT) {
        this.NO_SERTIFIKAT = NO_SERTIFIKAT;
    }
}
