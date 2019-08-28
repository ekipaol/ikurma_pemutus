package com.application.bris.brisi_pemutus.api.model.request.delete_aom;

import com.google.gson.annotations.SerializedName;

public class ReqDeleteAom {
    @SerializedName("uid")
    private int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
