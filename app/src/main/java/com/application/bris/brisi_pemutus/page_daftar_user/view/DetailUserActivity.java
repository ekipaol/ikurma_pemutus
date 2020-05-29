package com.application.bris.brisi_pemutus.page_daftar_user.view;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.delete_aom.ReqDeleteAom;
import com.application.bris.brisi_pemutus.api.model.request.insert_update_aom.InsertUpdateAom;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.util.AppUtil;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class DetailUserActivity extends AppCompatActivity  {
    ExtendedEditText nik,nama,kantor,jabatan,pin,uid,limit,status,lock,officer_code,sk;
    TextFieldBoxes Tnik,Tnama,Tkantor,Tjabatan,Tpin,Tuid,Tlimit,Tstatus,Tlock,Tofficer_code,Tsk;
    String oldUsername,oldNik,oldNama;
    FButton validasi,bt_simpan;
    @BindView(R.id.tb_regular)
    Toolbar tb_regular;

    View pembatas1;

    View pembatas2;

    View pembatas3;

    View pembatas4;

    View pembatas5;

    View pembatasSk;

    boolean statusEditNik=false,statusEditUsername=false,statusEditNama=false;




    TextInputEditText password;
    TextView label_password;
    TextInputLayout field_password;
    ApiClientAdapter apiClientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiClientAdapter = new ApiClientAdapter(this);

        setContentView(R.layout.activity_detail_user);
//        nik=findViewById(R.id.extended_nik);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this,"Detail Pegawai");


        nama=findViewById(R.id.extended_nama);
        kantor=findViewById(R.id.extended_kantor);
        jabatan=findViewById(R.id.extended_jabatan);
        pin=findViewById(R.id.extended_username); //ini pin diganti jadi username, tapi nama variabel tetap pin karena butuh banyak tenaga untuk mengubahnya
        sk=findViewById(R.id.extended_sk);
        uid=findViewById(R.id.extended_uid);

        status=findViewById(R.id.extended_status);
        lock=findViewById(R.id.extended_lock);
        officer_code=findViewById(R.id.extended_old_officer_code);
        nik=findViewById(R.id.extended_nik);

        Tnama=findViewById(R.id.text_nama);
        Tkantor=findViewById(R.id.text_kantor);
        Tjabatan=findViewById(R.id.text_jabatan);
        Tpin=findViewById(R.id.text_username);
        Tuid=findViewById(R.id.text_uid);

        Tstatus=findViewById(R.id.text_status);
        Tlock=findViewById(R.id.text_lock);
        Tofficer_code=findViewById(R.id.text_old_officer_code);
        Tnik=findViewById(R.id.text_nik);
        Tsk=findViewById(R.id.text_sk);

        bt_simpan=findViewById(R.id.bt_simpan_detail);
        bt_simpan.setButtonColor(getResources().getColor(R.color.colorPrimary));
        bt_simpan.setTextColor(getResources().getColor(R.color.colorWhite));

        field_password=findViewById(R.id.field_et_password);
        label_password=findViewById(R.id.tv_detail_label_password);
        password=findViewById(R.id.detail_et_password);

        pembatas1=findViewById(R.id.view_1);
        pembatas2=findViewById(R.id.view_2);
        pembatas3=findViewById(R.id.view_3);
        pembatas4=findViewById(R.id.view_4);
        pembatas5=findViewById(R.id.view_5);
        pembatasSk=findViewById(R.id.view_sk);


        nik.setText(getIntent().getStringExtra("nik"));
        nama.setText(getIntent().getStringExtra("nama"));
        kantor.setText(getIntent().getStringExtra("kantor"));
        jabatan.setText(getIntent().getStringExtra("jabatan"));
        pin.setText(getIntent().getStringExtra("username"));
        uid.setText(getIntent().getStringExtra("uid"));


        if(getIntent().getStringExtra("sk")!=null){
            sk.setText(getIntent().getStringExtra("sk"));
        }
        else{
            Tsk.setVisibility(View.GONE);
        }


        status.setText(getIntent().getStringExtra("status"));
        lock.setText(getIntent().getStringExtra("lock"));
        officer_code.setText(getIntent().getStringExtra("officer_code"));

        oldNik=getIntent().getStringExtra("nik");
        oldNama=getIntent().getStringExtra("nama");
        oldUsername=getIntent().getStringExtra("username");



    }

    public void simpanEdit() {
        final SweetAlertDialog dialogKonfirm = new SweetAlertDialog(DetailUserActivity.this, SweetAlertDialog.NORMAL_TYPE);
        dialogKonfirm.setTitleText("Konfirmasi")
                .setContentText("Simpan data user?")
                .setConfirmText("Ya")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                        dialogKonfirm.setContentText("");
                        //check apa yang diupdate, nik, nama, dan/atau username
                        if(!oldNama.equalsIgnoreCase(nama.getText().toString())){
                            statusEditNama=true;
                        }
                        if(!oldNik.equalsIgnoreCase(nik.getText().toString())){
                            statusEditNik=true;
                        }
                        if(!oldUsername.equalsIgnoreCase(pin.getText().toString())){
                            statusEditUsername=true;
                        }

                        AppPreferences apppref=new AppPreferences(DetailUserActivity.this);
                        //  progressbar_loading.setVisibility(View.VISIBLE);
                        InsertUpdateAom req = new InsertUpdateAom();
                        req.setNama(nama.getText().toString());
                        req.setUid(Integer.parseInt(uid.getText().toString())); //UID diisi 0 jika ingin insert, diisi selain 0 jika update
                        req.setNoPegawai(nik.getText().toString());
                        req.setUsername(pin.getText().toString());
                        req.setFid_role(getIntent().getIntExtra("fidRole",0));


                        if(password.getText().length()>0){
                            req.setPassword(password.getText().toString());
                        }
                        else{
                            req.setPassword("");
                        }

                        req.setKodeCabang(apppref.getKodeCabang());
                        req.setUidCreator(Integer.parseInt(apppref.getUid()));
                        Call<ParseResponse> call = apiClientAdapter.getApiInterface().insertUpdateAom(req);
                        call.enqueue(new Callback<ParseResponse>() {
                            @Override
                            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                                if(response.isSuccessful()){
                                    if(response.body().getStatus().equalsIgnoreCase("00")){


                                        dialogKonfirm.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        dialogKonfirm.setTitleText("Berhasil");

                                        //update pesan sesuai dengan field apa yang diedit
                                        String statusNama="";
                                        String statusUsername="";
                                        String statusNik="";
                                        String statusPassword="";
                                        String statusTanpaPerubahan="";

                                        if(!oldNama.equalsIgnoreCase(nama.getText().toString())){
                                            statusNama="\nNama";
                                        }
                                        if(!oldUsername.equalsIgnoreCase(pin.getText().toString())){
                                            statusNik="\nUsername";
                                        }
                                        if(!oldNik.equalsIgnoreCase(nik.getText().toString())){
                                            statusUsername="\nNik";
                                        }
                                        if(password.getText().length()>0){
                                            statusPassword="\nPassword";
                                        }

                                        if(statusEditNama=false){
                                            if(statusEditNik=false){
                                                if(statusEditUsername=false){
                                                    if(password.getText().length()>0){
                                                        statusTanpaPerubahan="Tidak ada";
                                                    }
                                                }
                                            }
                                        }


                                        dialogKonfirm.setContentText("Data yang diubah :"+statusNama+statusUsername+statusNik+statusPassword+statusTanpaPerubahan);

                                        dialogKonfirm.setConfirmText("OK");
                                        dialogKonfirm.showCancelButton(false);
                                        dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                dialogKonfirm.dismissWithAnimation();
                                                nik.setEnabled(false);
                                                nama.setEnabled(false);
                                                pin.setEnabled(false);
                                                bt_simpan.setVisibility(View.GONE);

                                                Tjabatan.setVisibility(View.VISIBLE);
                                                Tkantor.setVisibility(View.VISIBLE);
                                                Tstatus.setVisibility(View.VISIBLE);
                                                Tlock.setVisibility(View.VISIBLE);
                                                Tofficer_code.setVisibility(View.VISIBLE);
                                                showGarisPembatas();


                                                label_password.setVisibility(View.GONE);
                                                password.setVisibility(View.GONE);
                                                field_password.setVisibility(View.GONE);

                                            }
                                        });

                                    }
                                    else{
                                        dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                        dialogKonfirm.setTitleText("Gagal");
                                        dialogKonfirm.setContentText(response.body().getMessage());
                                        dialogKonfirm.setConfirmText("Coba lagi");

                                    }
                                }

                            }


                            @Override
                            public void onFailure(Call<ParseResponse> call, Throwable t) {
                                Log.d("LOG D", t.getMessage());
                                dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialogKonfirm.setTitleText("Gagal");
                                dialogKonfirm.setContentText(t.getMessage());
                                dialogKonfirm.setCancelText("OK");
                            }
                        });

                            }
                        }).setCancelText("Batal")
                                .show();
                    }


    public void deleteAom() {
        final SweetAlertDialog dialogKonfirm = new SweetAlertDialog(DetailUserActivity.this, SweetAlertDialog.NORMAL_TYPE);
        dialogKonfirm.setTitleText("Konfirmasi")
                .setContentText("Hapus user?")
                .setConfirmText("Ya")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                        dialogKonfirm.setContentText("");
                        AppPreferences apppref=new AppPreferences(DetailUserActivity.this);
                        //  progressbar_loading.setVisibility(View.VISIBLE);
                        ReqDeleteAom req = new ReqDeleteAom();
                        req.setUid(Integer.parseInt(uid.getText().toString()));
                        Call<ParseResponse> call = apiClientAdapter.getApiInterface().deleteAom(req);
                        call.enqueue(new Callback<ParseResponse>() {
                            @Override
                            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                                if(response.isSuccessful()){
                                    if(response.body().getStatus().equalsIgnoreCase("00")){

                                        dialogKonfirm.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        dialogKonfirm.setTitleText("Berhasil");
                                        dialogKonfirm.setContentText("User dihapus");
                                        dialogKonfirm.setConfirmText("OK");
                                        dialogKonfirm.showCancelButton(false);
                                        dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                dialogKonfirm.dismissWithAnimation();
                                               finish();
                                            }
                                        });

                                    }
                                    else{
                                        dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                        dialogKonfirm.setTitleText("Gagal");
                                        dialogKonfirm.setContentText(response.body().getMessage());
                                        dialogKonfirm.setConfirmText("Coba lagi");

                                    }
                                }

                            }


                            @Override
                            public void onFailure(Call<ParseResponse> call, Throwable t) {
                                Log.d("LOG D", t.getMessage());
                                dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialogKonfirm.setTitleText("Gagal");
                                dialogKonfirm.setContentText(t.getMessage());
                                dialogKonfirm.setCancelText("OK");
                            }
                        });

                    }
                }).setCancelText("Batal")
                .show();
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //uncheck this when everything is ok
        AppPreferences appPreferences=new AppPreferences(this);
        if (appPreferences.getFidRole().equalsIgnoreCase("76")||appPreferences.getFidRole().equalsIgnoreCase("79")){
            getMenuInflater().inflate(R.menu.detail_user_menu, menu);
        }
//        getMenuInflater().inflate(R.menu.detail_user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.detail_menu_edit:



                Toasty.info(DetailUserActivity.this,"Silahkan Edit Data", Toast.LENGTH_LONG).show();


                //MEMBUAT FIELD YANG BISA DIEDIT AGAR BISA DIEDIT
                Tnama.setEnabled(true);
                Tpin.setEnabled(false);
                Tnik.setEnabled(false);

                //MENGHILANGKAN FIELD YANG TAK BISA DIEDIT KECUALI FIELD UID
                Tjabatan.setVisibility(View.GONE);
                Tkantor.setVisibility(View.GONE);
                Tstatus.setVisibility(View.GONE);
                Tlock.setVisibility(View.GONE);
//                Tsk.setVisibility(View.GONE);
                Tofficer_code.setVisibility(View.GONE);
                hideGarisPembatas();





                label_password.setVisibility(View.VISIBLE);
                password.setVisibility(View.VISIBLE);
                field_password.setVisibility(View.VISIBLE);


                bt_simpan.setVisibility(View.VISIBLE);
                bt_simpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       simpanEdit();
                    }
                });
                return true;
//            case R.id.detail_menu_delete:
////                Toasty.info(DetailUserActivity.this,"Delete", Toast.LENGTH_LONG).show();
//                deleteAom();
//                return true;
//            case R.id.detail_menu_maintenance:
//                Toasty.info(DetailUserActivity.this,"Maintenance", Toast.LENGTH_LONG).show();
//                Intent intent=new Intent(DetailUserActivity.this,ProsesMaintenanceUser.class);
//                intent.putExtra("uid",uid.getText().toString());
//                intent.putExtra("nama",nama.getText().toString());
//                intent.putExtra("jabatan",jabatan.getText().toString());
//                intent.putExtra("kantor",kantor.getText().toString());
//                intent.putExtra("officer_code",officer_code.getText().toString());
//                startActivity(intent);
//                return true;
            default:

                return super.onOptionsItemSelected(item);

        }
    }

    public void showGarisPembatas(){
        pembatas1.setVisibility(View.VISIBLE);
        pembatas2.setVisibility(View.VISIBLE);
        pembatas3.setVisibility(View.VISIBLE);
        pembatas4.setVisibility(View.VISIBLE);
        pembatas5.setVisibility(View.VISIBLE);
        pembatasSk.setVisibility(View.VISIBLE);
    }

    public void hideGarisPembatas(){
        pembatas1.setVisibility(View.GONE);
        pembatas2.setVisibility(View.GONE);
        pembatas3.setVisibility(View.GONE);
        pembatas4.setVisibility(View.GONE);
        pembatas5.setVisibility(View.GONE);
//        pembatasSk.setVisibility(View.GONE);
    }
}
