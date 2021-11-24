package com.application.bris.brisi_pemutus.api.model.request;

import com.google.gson.annotations.SerializedName;

public class ReqUidRole {
    @SerializedName("role")
    private Integer role;
    @SerializedName("uid")
    private String uid;

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
