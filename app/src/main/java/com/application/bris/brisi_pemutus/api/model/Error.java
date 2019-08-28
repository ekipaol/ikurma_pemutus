package com.application.bris.brisi_pemutus.api.model;

import com.google.gson.annotations.SerializedName;

public class Error {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;
    @SerializedName("data")
    private Object data;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
