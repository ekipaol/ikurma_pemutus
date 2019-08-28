package com.application.bris.brisi_pemutus.api.model.request;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ParseResponseListFoto {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;
    @SerializedName("data")
    private JsonObject data;
    @SerializedName("listPoto")
    private JsonArray listPoto;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }

    public JsonArray getListPoto() {
        return listPoto;
    }

    public void setListPoto(JsonArray listPoto) {
        this.listPoto = listPoto;
    }
}
