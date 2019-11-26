package com.application.bris.brisi_pemutus.page_performance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.application.bris.brisi_pemutus.R;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.page_daftar_user.view.ActivityStatusUser;
import com.application.bris.brisi_pemutus.page_daftar_user.view.MaintenanceUserActivity;
import com.application.bris.brisi_pemutus.page_daftar_user.view.ReactivePasswordActivity;
import com.application.bris.brisi_pemutus.page_daftar_user.view.TambahUserActivity;
import com.application.bris.brisi_pemutus.page_daftar_user.view.UserActivity;
import com.application.bris.brisi_pemutus.page_putusan.agunan_retry.ActivityAgunanRetry;
import com.application.bris.brisi_pemutus.page_putusan.lkn.LknActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;

public class MenuPerformanceActivity extends AppCompatActivity {
    ImageView performance_cabang,performance_ao;
    LinearLayout ll_menu_performance_container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_performance);
        performance_cabang=findViewById(R.id.bt_performance_cabang);
        performance_ao=findViewById(R.id.bt_performance_ao);
        ll_menu_performance_container=findViewById(R.id.ll_menu_performance_container);

        AppUtil.toolbarRegular(this, "Menu Performance");

        final AppPreferences appPreferences=new AppPreferences(this);

//        AppUtil.tutorialOverlay(this,ll_menu_performance_container,"Klik pilihan anda untuk melihat performa cabang anda, atau melihat performa AOM anda","tutorial_daftar_menu_performance");





        performance_cabang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //akses hanya untuk pinca, karena service baru suport kode kanwil bukan kode skk
                if(appPreferences.getFidRole().equalsIgnoreCase("76")){
                    Intent intent=new Intent(MenuPerformanceActivity.this, PerformanceActivity.class);
//                    startActivity(intent);
                    Toast.makeText(MenuPerformanceActivity.this, "Masih dalam pengembangan", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MenuPerformanceActivity.this, "Masih dalam pengembangan", Toast.LENGTH_SHORT).show();
                }

//                Toast.makeText(MenuPerformanceActivity.this, "Masih dalam pengembangan", Toast.LENGTH_SHORT).show();
            }
        });
        performance_ao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(appPreferences.getFidRole().equalsIgnoreCase("71")){
//                  Toast.makeText(MenuPerformanceActivity.this, "User UH tidak dapat mengakses halaman ini", Toast.LENGTH_SHORT).show();
                  Intent intent=new Intent(MenuPerformanceActivity.this, PerformanceAoActivity.class);
                  startActivity(intent);
              }
              else{
                  Intent intent=new Intent(MenuPerformanceActivity.this, PerformanceAoActivity.class);
                  startActivity(intent);
              }

            }
        });
    }
}
