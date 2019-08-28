package com.application.bris.brisi_pemutus.page_daftar_user.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.util.AppUtil;

import butterknife.BindView;
import info.hoang8f.widget.FButton;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class ProsesMaintenanceUser extends AppCompatActivity {
    ExtendedEditText nik,nama,kantor,jabatan,pin;
    String uid="";
    FButton validasi;
    @BindView(R.id.tb_regular)
    Toolbar tb_regular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_proses_maintenance_user);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this,"Ubah Officer Code");


        nama=findViewById(R.id.extended_nama);
        kantor=findViewById(R.id.extended_kantor);
        jabatan=findViewById(R.id.extended_jabatan);
        pin=findViewById(R.id.extended_old_officer_code);
        validasi=findViewById(R.id.btValidasi);
        validasi.setButtonColor(getResources().getColor(R.color.colorPrimary));

try{
    nama.setText(getIntent().getStringExtra("nama"));
    kantor.setText(getIntent().getStringExtra("kantor"));
    jabatan.setText(getIntent().getStringExtra("jabatan"));
    pin.setText(getIntent().getStringExtra("officer_code"));
    uid=getIntent().getStringExtra("uid");
}
catch(Exception e){
    Log.d("ekipaol mt user","telah terjadi error di trycatch intent");
}


    }
}
