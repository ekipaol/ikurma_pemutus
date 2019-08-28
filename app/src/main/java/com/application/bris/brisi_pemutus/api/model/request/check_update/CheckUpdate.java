package com.application.bris.brisi_pemutus.api.model.request.check_update;

import com.google.gson.annotations.SerializedName;
/**
 * Created by idong on 23/05/2019.
 */
public class CheckUpdate {
    @SerializedName("appID")
    private String appID;
    public CheckUpdate(String appID) {
        this.appID = appID;
    }
    public void setAppID(String appID) {
        this.appID = appID;
    }
}
