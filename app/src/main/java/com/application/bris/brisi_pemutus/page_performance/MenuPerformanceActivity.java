package com.application.bris.brisi_pemutus.page_performance;

import android.os.Bundle;

import com.application.bris.brisi_pemutus.R;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.view.corelayout.CoreLayoutActivity;

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


        //kalok dia mencet back, di pojok kiri atas, halaman home gak loading lagi, jadi gak berat broooo
        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(MenuPerformanceActivity.this, CoreLayoutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                startActivity(intent);


            }
        });



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

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent=new Intent(MenuPerformanceActivity.this, CoreLayoutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        startActivity(intent);
    }
}
