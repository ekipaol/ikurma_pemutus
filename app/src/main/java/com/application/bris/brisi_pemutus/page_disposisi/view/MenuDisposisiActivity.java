package com.application.bris.brisi_pemutus.page_disposisi.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.util.AppUtil;

public class MenuDisposisiActivity extends AppCompatActivity {
    ImageView daftarDisposisi, riwayatDisposisi;
    TextView notifDisposisi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disposisi_menu);
        daftarDisposisi=findViewById(R.id.bt_daftar_disposisi);
        riwayatDisposisi =findViewById(R.id.bt_riwayat_disposisi);
        notifDisposisi=findViewById(R.id.tv_notifikasi_disposisi);

        notifDisposisi.setVisibility(View.GONE);


        AppUtil.toolbarRegular(this, "Menu Disposisi");

        int jumlahNotif=getIntent().getIntExtra("notifDisposisi",0);
        if(jumlahNotif!=0){
            notifDisposisi.setText(Integer.toString(jumlahNotif));
            notifDisposisi.setVisibility(View.VISIBLE);
        }

        daftarDisposisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuDisposisiActivity.this, DaftarDisposisiActivity.class);
                intent.putExtra("menuAsal","daftarDisposisi");

                startActivity(intent);


            }
        });

        riwayatDisposisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuDisposisiActivity.this, DaftarDisposisiActivity.class);
                    intent.putExtra("menuAsal","riwayatDisposisi");
                startActivity(intent);
//                Toast.makeText(MenuDaftarPutusanActivity.this, "Masih menunggu middletier", Toast.LENGTH_SHORT).show();
            }
        });


    }
}