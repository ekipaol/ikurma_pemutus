package com.application.bris.brisi_pemutus.page_daftar_user.view;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.view.corelayout.CoreLayoutActivity;

public class MenuDaftarUser extends AppCompatActivity {
ImageView daftarUser,tambahUser,aktifinaktif,maintenance,reaktif,aoSilang;
CardView cv_tambah_user,cv_ao_silang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_daftar_menu);
        daftarUser=findViewById(R.id.btDaftarUser);
        tambahUser=findViewById(R.id.btTambah);
        maintenance=findViewById(R.id.btMaintenance);
        aktifinaktif=findViewById(R.id.btActiveInactive);
        reaktif=findViewById(R.id.btPassword);
        aoSilang=findViewById(R.id.btAoSilang);
        cv_tambah_user=findViewById(R.id.cv_tambah_user);
        cv_ao_silang=findViewById(R.id.cv_ao_silang);


        AppUtil.toolbarRegular(this, "Menu User");

        AppPreferences appPreferences=new AppPreferences(this);

        //kalok dia mencet back, di pojok kiri atas, halaman home gak loading lagi, jadi gak berat broooo
        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(MenuDaftarUser.this, CoreLayoutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                startActivity(intent);


            }
        });




        //UNTUK HIDE TAMBAH USER dan ao silang
        cv_tambah_user.setVisibility(View.GONE);
        cv_ao_silang.setVisibility(View.GONE);

        //sementara fitur tambah dinonaktifkan sampai fix kode ao dan kode pemutus
        if(appPreferences.getFidRole().equalsIgnoreCase("76")||appPreferences.getFidRole().equalsIgnoreCase("79")){
            cv_tambah_user.setVisibility(View.VISIBLE);
        }

        //AO SILANG HANYA UNTUK PINCA DAN PINCAPEM
//        if(appPreferences.getFidRole().equalsIgnoreCase("76")||appPreferences.getFidRole().equalsIgnoreCase("79")){
//            cv_ao_silang.setVisibility(View.VISIBLE);
//        }

        daftarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuDaftarUser.this,UserActivity.class);
                startActivity(intent);
            }
        });

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
        aoSilang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(MenuDaftarUser.this,ReactivePasswordActivity.class);
//                startActivity(intent);
                Toast.makeText(MenuDaftarUser.this, "Dalam pengembangan", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent=new Intent(MenuDaftarUser.this, CoreLayoutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        startActivity(intent);
    }
}
