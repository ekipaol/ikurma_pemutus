package com.application.bris.brisi_pemutus.page_daftar_user.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.page_putusan.agunan_retry.ActivityAgunanRetry;
import com.application.bris.brisi_pemutus.page_putusan.lkn.LknActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;

public class MenuDaftarUser extends AppCompatActivity {
ImageView daftarUser,tambahUser,aktifinaktif,maintenance,reaktif;
CardView cv_tambah_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_daftar_menu);
        daftarUser=findViewById(R.id.btDaftarUser);
        tambahUser=findViewById(R.id.btTambah);
        maintenance=findViewById(R.id.btMaintenance);
        aktifinaktif=findViewById(R.id.btActiveInactive);
        reaktif=findViewById(R.id.btPassword);
        cv_tambah_user=findViewById(R.id.cv_tambah_user);
        AppUtil.toolbarRegular(this, "Menu User");

        AppPreferences appPreferences=new AppPreferences(this);

        //sementara fitur tambah dinonaktifkan sampai fix kode ao dan kode pemutus
        if(appPreferences.getJabatan().equalsIgnoreCase("pinca")||appPreferences.getJabatan().equalsIgnoreCase("pimpinan cabang")||appPreferences.getJabatan().equalsIgnoreCase("mmm")){
            cv_tambah_user.setVisibility(View.GONE);
        }


        //UNTUK HIDE TAMBAH USER
//        cv_tambah_user.setVisibility(View.GONE);

        daftarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuDaftarUser.this,UserActivity.class);
                startActivity(intent);


            }
        });

        //delete this later
//        daftarUser.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Intent intent=new Intent(MenuDaftarUser.this, ActivityAgunanRetry.class);
//                startActivity(intent);
//                return false;
//            }
//        });

        tambahUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuDaftarUser.this,TambahUserActivity.class);
                startActivity(intent);
            }
        });
        aktifinaktif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuDaftarUser.this,ActivityStatusUser.class);
                startActivity(intent);
            }
        });
        maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuDaftarUser.this,MaintenanceUserActivity.class);
              //  startActivity(intent);
                Toast.makeText(MenuDaftarUser.this, "Fitur ini masih dalam pengembangan", Toast.LENGTH_SHORT).show();
            }
        });
        reaktif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuDaftarUser.this,ReactivePasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
