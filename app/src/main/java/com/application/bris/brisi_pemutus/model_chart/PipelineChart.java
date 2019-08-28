package com.application.bris.brisi_pemutus.model_chart;

public class PipelineChart {
    private String nama;
    private int jumlahPipeline;

    public PipelineChart(String nama, int jumlahPipeline) {
        this.nama = nama;
        this.jumlahPipeline = jumlahPipeline;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlahPipeline() {
        return jumlahPipeline;
    }

    public void setJumlahPipeline(int jumlahPipeline) {
        this.jumlahPipeline = jumlahPipeline;
    }
}
