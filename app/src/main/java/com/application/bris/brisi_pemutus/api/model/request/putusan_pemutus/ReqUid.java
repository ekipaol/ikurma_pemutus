package com.application.bris.brisi_pemutus.api.model.request.putusan_pemutus;

import com.google.gson.annotations.SerializedName;

public class ReqUid {
    @SerializedName("uid")
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
