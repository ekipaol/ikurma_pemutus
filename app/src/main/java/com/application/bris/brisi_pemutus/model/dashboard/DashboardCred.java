package com.application.bris.brisi_pemutus.model.dashboard;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DashboardCred implements Serializable {
    @SerializedName("jlhPutusan")
    private int notifDashboard;

    @SerializedName("jlhDisposisi")
    private int notifDashboardDisposisi;

    public int getNotifDashboardDisposisi() {
        return notifDashboardDisposisi;
    }

    public void setNotifDashboardDisposisi(int notifDashboardDisposisi) {
        this.notifDashboardDisposisi = notifDashboardDisposisi;
    }

    public int getNotifDashboard() {
        return notifDashboard;
    }

    public void setNotifDashboard(int notifDashboard) {
        this.notifDashboard = notifDashboard;
    }

}
