package com.application.bris.brisi_pemutus.database.model;

import android.content.Context;


import com.application.bris.brisi_pemutus.model.ao_silang_list_user.ListUser;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by idong on 27/05/2019.
 */

public class AoModel {
    private Realm realm;
    private Context context;

    public AoModel(Context context) {
        this.context = context;
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        this.realm = Realm.getInstance(config);


    }

    public void add(String fid_JHOOFF, String LIMIT_MIKRO, String LIMIT_BAWAH_KKB, String SECRET_CODE, String NAMA_PEGAWAI, String IMEI, String USER_NAME, String LIMIT_KPR, String KODE_CABANG, String UID, String LIMIT_BAWAH_KPR, String NO_PEGAWAI, String PASSWORD, String FID_ROLE, String limit, String SK, String LIMIT_KKB, String status){


        realm.beginTransaction();
        ListUser data = realm.createObject(ListUser.class);




        data.setFid_JHOOFF(fid_JHOOFF);
        data.setLIMIT_MIKRO(LIMIT_MIKRO);
        data.setLIMIT_BAWAH_KKB(LIMIT_BAWAH_KKB);
        data.setSECRET_CODE(SECRET_CODE);
        data.setNAMA_PEGAWAI(NAMA_PEGAWAI);
        data.setIMEI(IMEI);
        data.setUSER_NAME(USER_NAME);
        data.setLIMIT_KPR(LIMIT_KPR);
        data.setKODE_CABANG(KODE_CABANG);
        data.setUID(UID);
        data.setLIMIT_BAWAH_KPR(LIMIT_BAWAH_KPR);
        data.setNO_PEGAWAI(NO_PEGAWAI);
        data.setPASSWORD(PASSWORD);
        data.setFID_ROLE(FID_ROLE);
        data.setLimit(limit);
        data.setSK(SK);
        data.setLIMIT_KKB(LIMIT_KKB);
        data.setStatus(status);
        realm.commitTransaction();
    }

    public void clear() {
        RealmResults<ListUser> data = realm.where(ListUser.class).findAll();
        if(data.size() > 0){
            realm.beginTransaction();
            data.deleteAllFromRealm();
            realm.commitTransaction();
        }
    }
}
