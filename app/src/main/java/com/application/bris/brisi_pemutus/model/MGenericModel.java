package com.application.bris.brisi_pemutus.model;

import com.google.gson.annotations.SerializedName;

public class MGenericModel {
    @SerializedName("ID")
    private String ID;

    @SerializedName("DESC")
    private String DESC;

    @SerializedName("Nama")
    private String NAMA;

    public String getID() {
        return ID;
    }

    public MGenericModel(String ID, String DESC) {
        this.ID = ID;
        this.DESC = DESC;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDESC() {
        return DESC;
    }

    public void setDESC(String DESC) {
        this.DESC = DESC;
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }
}
