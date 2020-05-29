package com.application.bris.brisi_pemutus.api.model.request.list_cair;

import com.google.gson.annotations.SerializedName;

public class ReqListCair {

    @SerializedName("uid")
    private String uid;

    @SerializedName("bulanCair")
    private String bulanCair;

    @SerializedName("tahunCair")
    private String tahunCair;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBulanCair() {
        return bulanCair;
    }

    public void setBulanCair(String bulanCair) {
        this.bulanCair = bulanCair;
    }

    public String getTahunCair() {
        return tahunCair;
    }

    public void setTahunCair(String tahunCair) {
        this.tahunCair = tahunCair;
    }
}
