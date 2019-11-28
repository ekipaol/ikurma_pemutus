package com.application.bris.brisi_pemutus.model.dashboard;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DashboardCred implements Serializable {
    @SerializedName("jlhPutusan")
    private int notifDashboard;

    @SerializedName("jlhDisposisi")
    private int notifDashboardDisposisi;

    @SerializedName("jlhPutusanDeviasi")
    private int jlhPutusanDeviasi;

    public int getJlhPutusanDeviasi() {
        return jlhPutusanDeviasi;
    }

    public void setJlhPutusanDeviasi(int jlhPutusanDeviasi) {
        this.jlhPutusanDeviasi = jlhPutusanDeviasi;
    }

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
