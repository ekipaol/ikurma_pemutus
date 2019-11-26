package com.application.bris.brisi_pemutus.page_riwayat;

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
import com.application.bris.brisi_pemutus.page_putusan.PutusanFrontMenu;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.view.corelayout.CoreLayoutActivity;

public class ActivityMenuRiwayat extends AppCompatActivity {
    ImageView riwayatDisetujui, riwayatDitolak, riwayatProses,riwayatCair;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_riwayat);
       riwayatDisetujui=findViewById(R.id.bt_riwayat_disetujui);
        riwayatDitolak=findViewById(R.id.bt_riwayat_rejected);
        riwayatProses=findViewById(R.id.bt_riwayat_proses);
        riwayatCair=findViewById(R.id.bt_riwayat_cair);



        AppUtil.toolbarRegular(this, "Menu Riwayat");

        //toolbar back configuration, hard to explain, just ask to mr eki. In short, this is needed so the activity flows as eki wants
        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent=new Intent(ActivityMenuRiwayat.this, CoreLayoutActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                    startActivity(intent);


            }
        });






        riwayatDisetujui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityMenuRiwayat.this, ActivitySetuju.class);
                startActivity(intent);


            }
        });

        riwayatDitolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityMenuRiwayat.this, ActivityDitolak.class);
                startActivity(intent);


            }
        });

        riwayatProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityMenuRiwayat.this, ActivityAkad.class);
                startActivity(intent);


            }
        });

        riwayatCair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityMenuRiwayat.this, ActivityCair.class);
                startActivity(intent);


            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ActivityMenuRiwayat.this, CoreLayoutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        startActivity(intent);
    }
}

