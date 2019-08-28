package com.application.bris.brisi_pemutus.model.data_cabang;

import android.content.Context;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

public class CabangModel {

    Context context;
    Realm realm;

    public CabangModel(){
//        this.context=context;
//        this.realm=Realm.getDefaultInstance();
    }

    public CabangModel(Context contextku){
        this.context=contextku;
        this.realm= Realm.getDefaultInstance();
    }

    public void addCabang(Cabang cabang){

        realm.beginTransaction();
        Cabang cabangData=realm.createObject(Cabang.class);
        cabangData.setSb_desc(cabang.getSb_desc());
        cabangData.setKode_kanwil(cabang.getKode_kanwil());
        cabangData.setKota(cabang.getKota());
        cabangData.setKode_skpp(cabang.getKode_skpp());
        cabangData.setAlamat_kantor(cabang.getAlamat_kantor());
        cabangData.setKode_ol(cabang.getKode_ol());
        cabangData.setKode_induk(cabang.getKode_induk());
        cabangData.setKode_lengkap(cabang.getKode_lengkap());
        cabangData.setSubbr(cabang.getSubbr());
        cabangData.setUnit_kerja(cabang.getUnit_kerja());
        realm.commitTransaction();


    }

    public void clear(){
        RealmResults<Cabang> dataCabang=realm.where(Cabang.class).findAll();
        Log.d("ukurannya",Integer.toString(dataCabang.size()));
        if(dataCabang.size()>0){
            realm.beginTransaction();
            dataCabang.deleteAllFromRealm();
            realm.commitTransaction();
        }
    }
}
