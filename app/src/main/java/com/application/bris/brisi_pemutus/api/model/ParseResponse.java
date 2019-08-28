package com.application.bris.brisi_pemutus.api.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ParseResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;
    @SerializedName("data")
    private JsonObject data;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public JsonObject getData() {
        return data;
    }
}
