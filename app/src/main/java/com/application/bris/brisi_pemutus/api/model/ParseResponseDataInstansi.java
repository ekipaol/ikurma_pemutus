package com.application.bris.brisi_pemutus.api.model;


import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ParseResponseDataInstansi {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;
    @SerializedName("data")
    private JsonObject data;
    @SerializedName("data_instansi")
    private JsonObject dataInstansi;

    @SerializedName("data_angsuran")
    private JsonObject dataAngsuran;

    public JsonObject getDataInstansi() {
        return dataInstansi;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public JsonObject getData() {
        return data;
    }

    public JsonObject getDataAngsuran() {
        return dataAngsuran;
    }
}
