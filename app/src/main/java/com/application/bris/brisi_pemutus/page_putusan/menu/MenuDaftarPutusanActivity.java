package com.application.bris.brisi_pemutus.page_putusan.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.page_aom.view.PutusanActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;

public class MenuDaftarPutusanActivity extends AppCompatActivity {
    ImageView putusanPembiayaan, putusanDeviasi, putusanKoreksi;
    TextView tvNotifikasiPembiayaan,tvNotifikasiDeviasi;
    CardView cvPutusanKoreksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.putusan_daftar_menu);
        putusanPembiayaan=findViewById(R.id.bt_putusan_pembiayaan);
        putusanDeviasi =findViewById(R.id.bt_putusan_deviasi);
        putusanKoreksi =findViewById(R.id.bt_putusan_koreksi);

        cvPutusanKoreksi=findViewById(R.id.cv_putusan_koreksi);

        tvNotifikasiPembiayaan=findViewById(R.id.tv_notifikasi_putusan_pembiayaan);
        tvNotifikasiDeviasi=findViewById(R.id.tv_notifikasi_putusan_deviasi);

        tvNotifikasiPembiayaan.setVisibility(View.GONE);
        tvNotifikasiDeviasi.setVisibility(View.GONE);


        //hide putusan koreksi until reinstated to work on again
        cvPutusanKoreksi.setVisibility(View.GONE);

        AppUtil.toolbarRegular(this, "Menu Putusan");

        int notifPembiayaan=getIntent().getIntExtra("notifPembiayaan",0);

        if(notifPembiayaan!=0){
            tvNotifikasiPembiayaan.setText(Integer.toString(notifPembiayaan));
            tvNotifikasiPembiayaan.setVisibility(View.VISIBLE);
        }



        putusanPembiayaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuDaftarPutusanActivity.this, PutusanActivity.class);
                intent.putExtra("kodePutusan","putusanPembiayaan");
                startActivity(intent);


            }
        });

        putusanDeviasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          Intent intent=new Intent(MenuDaftarPutusanActivity.this, PutusanActivity.class);
                intent.putExtra("kodePutusan","putusanDeviasi");
                startActivity(intent);
//                Toast.makeText(MenuDaftarPutusanActivity.this, "Masih menunggu middletier", Toast.LENGTH_SHORT).show();
            }
        });
        putusanKoreksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(MenuDaftarPutusanActivity.this, ActivityStatusUser.class);
//                intent.putExtra("kodePutusan","putusanKoreksi");
//                startActivity(intent);
                Toast.makeText(MenuDaftarPutusanActivity.this, "Masih dalam pengembangan", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

