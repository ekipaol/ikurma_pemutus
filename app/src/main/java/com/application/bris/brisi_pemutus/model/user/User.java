package com.application.bris.brisi_pemutus.model.user;

import com.google.gson.annotations.SerializedName;

public class User {
   // private String nama,kantor,jabatan,limit,status,lock,uid,pin;

    @SerializedName("FID_PHOTO")
    private String status;
    @SerializedName("NAMA")
    private String nama;
    @SerializedName("JENIS_PRODUK")
    private String kantor;
    @SerializedName("PLAFOND")
    private String jabatan;
    @SerializedName("JW")
    private String limit;
    @SerializedName("TGL_INPUT")
    private String uid;

    private String lock,pin;

    public User(String nama,String kantor,String jabatan,String limit,String status,String lock,String uid,String pin){
        this.nama=nama;
        this.kantor=kantor;
        this.jabatan=jabatan;
        this.limit=limit;
        this.status=status;
        this.uid=uid;
       // this.pin=pin;
        // this.lock=lock;

        //delete below if real data has been received
        this.lock="tidak terkunci";
        this.pin="12345678";



    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKantor() {
        return kantor;
    }

    public void setKantor(String kantor) {
        this.kantor = kantor;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
