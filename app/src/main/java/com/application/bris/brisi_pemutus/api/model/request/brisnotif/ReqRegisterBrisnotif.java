package com.application.bris.brisi_pemutus.api.model.request.brisnotif;

import com.google.gson.annotations.SerializedName;

public class ReqRegisterBrisnotif {
    @SerializedName("appclient")
    private String appclient;
    @SerializedName("identifier")
    private String identifier;
    @SerializedName("token")
    private String token;
    @SerializedName("agent")
    private String agent;

    public String getAppclient() {
        return appclient;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public void setAppclient(String appclient) {
        this.appclient = appclient;
    }
}
