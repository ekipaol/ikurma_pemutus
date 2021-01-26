package com.application.bris.brisi_pemutus.model.monitoring;

import com.google.gson.annotations.SerializedName;

public class SummaryMonitoringSalamDigital {

    @SerializedName("totalPengajuan")
    private String totalPengajuan;

    @SerializedName("totalLolosPrescreening")
    private String totalLolosPrescreening;

    @SerializedName("totalGagalPrescreening")
    private String totalGagalPrescreening;

    @SerializedName("totalTelahDisposisi")
    private String totalTelahDisposisi;

    public String getTotalPengajuan() {
        return totalPengajuan;
    }

    public void setTotalPengajuan(String totalPengajuan) {
        this.totalPengajuan = totalPengajuan;
    }

    public String getTotalLolosPrescreening() {
        return totalLolosPrescreening;
    }

    public void setTotalLolosPrescreening(String totalLolosPrescreening) {
        this.totalLolosPrescreening = totalLolosPrescreening;
    }

    public String getTotalGagalPrescreening() {
        return totalGagalPrescreening;
    }

    public void setTotalGagalPrescreening(String totalGagalPrescreening) {
        this.totalGagalPrescreening = totalGagalPrescreening;
    }

    public String getTotalTelahDisposisi() {
        return totalTelahDisposisi;
    }

    public void setTotalTelahDisposisi(String totalTelahDisposisi) {
        this.totalTelahDisposisi = totalTelahDisposisi;
    }
}
