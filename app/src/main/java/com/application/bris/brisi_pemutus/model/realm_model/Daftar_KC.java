package com.application.bris.brisi_pemutus.model.realm_model;

import io.realm.RealmObject;

public class Daftar_KC extends RealmObject {
    int id ;
    String nama;

    public Daftar_KC(){
        id=0;
        nama="zero";
    }

    public  Daftar_KC(String nama){
        this.nama=nama;
        this.id=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
