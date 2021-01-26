package com.application.bris.brisi_pemutus.page_putusan.menu;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.page_aom.view.PutusanActivity;
import com.application.bris.brisi_pemutus.page_flpp.HasilPraujiFlppActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.view.corelayout.CoreLayoutActivity;

public class MenuDaftarPutusanActivity extends AppCompatActivity {
    ImageView putusanPembiayaan, putusanDeviasi, putusanKoreksi,praujiFlpp;
    TextView tvNotifikasiPembiayaan,tvNotifikasiDeviasi,tvNotifikasiPraujiFlpp;
    CardView cvPutusanKoreksi,cvHasilPrauji;
    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.putusan_daftar_menu);
        putusanPembiayaan=findViewById(R.id.bt_putusan_pembiayaan);
        putusanDeviasi =findViewById(R.id.bt_putusan_deviasi);
        putusanKoreksi =findViewById(R.id.bt_putusan_koreksi);
        praujiFlpp =findViewById(R.id.bt_hasil_prauji_flpp);

        cvPutusanKoreksi=findViewById(R.id.cv_putusan_koreksi);
        cvHasilPrauji=findViewById(R.id.cv_hasil_prauji_flpp);

        tvNotifikasiPembiayaan=findViewById(R.id.tv_notifikasi_putusan_pembiayaan);
        tvNotifikasiDeviasi=findViewById(R.id.tv_notifikasi_putusan_deviasi);
        tvNotifikasiPraujiFlpp=findViewById(R.id.tv_notifikasi_hasil_prauji_flpp);

        tvNotifikasiPembiayaan.setVisibility(View.GONE);
        tvNotifikasiDeviasi.setVisibility(View.GONE);

        appPreferences=new AppPreferences(this);



       otherViewChanges();

        AppUtil.toolbarRegular(this, "Menu Putusan");

        //toolbar back configuration, hard to explain, just ask to mr eki. In short, this is needed so the activity flows as eki wants
        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(MenuDaftarPutusanActivity.this, CoreLayoutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                startActivity(intent);


            }
        });

        int notifPembiayaan=getIntent().getIntExtra("notifPembiayaan",0);
        int notifPembiayaanDeviasi=getIntent().getIntExtra("notifPembiayaanDeviasi",0);

        if(notifPembiayaan!=0){
            tvNotifikasiPembiayaan.setText(Integer.toString(notifPembiayaan));
            tvNotifikasiPembiayaan.setVisibility(View.VISIBLE);
        }
        if(notifPembiayaanDeviasi!=0){
            tvNotifikasiDeviasi.setText(Integer.toString(notifPembiayaanDeviasi));
            tvNotifikasiDeviasi.setVisibility(View.VISIBLE);
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

        praujiFlpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuDaftarPutusanActivity.this, HasilPraujiFlppActivity.class);
                startActivity(intent);
//                Toast.makeText(MenuDaftarPutusanActivity.this, "Ting Tong anggap saja sedang masuk menu pra uji", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent=new Intent(MenuDaftarPutusanActivity.this, CoreLayoutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        startActivity(intent);
    }

    private void otherViewChanges(){
        //hide putusan koreksi until reinstated to work on again
        cvPutusanKoreksi.setVisibility(View.GONE);

        if(appPreferences.getFidRole().equalsIgnoreCase("76")||appPreferences.getFidRole().equalsIgnoreCase("79")||appPreferences.getFidRole().equalsIgnoreCase("77")){
            cvHasilPrauji.setVisibility(View.VISIBLE);
            tvNotifikasiPraujiFlpp.setVisibility(View.GONE);
        }
        else{
            cvHasilPrauji.setVisibility(View.GONE);
        }
    }


}

