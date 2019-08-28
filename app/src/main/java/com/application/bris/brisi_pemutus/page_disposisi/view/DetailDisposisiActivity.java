package com.application.bris.brisi_pemutus.page_disposisi.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.data_cabang.RequestDataCabang;
import com.application.bris.brisi_pemutus.api.model.request.req_kode_skk.ReqKodeSkk;
import com.application.bris.brisi_pemutus.api.model.request.simpan_disposisi.ReqSimpanDisposisi;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.aom_disposisi.AomDisposisi;
import com.application.bris.brisi_pemutus.model.data_cabang.Cabang;
import com.application.bris.brisi_pemutus.model.data_cabang.CabangModel;
import com.application.bris.brisi_pemutus.model.disposisi.Disposisi;
import com.application.bris.brisi_pemutus.page_daftar_user.view.TambahUserActivity;
import com.application.bris.brisi_pemutus.page_putusan.PutusanFrontMenu;
import com.application.bris.brisi_pemutus.util.AppBarStateChangedListener;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import fr.ganfra.materialspinner.MaterialSpinner;
import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDisposisiActivity extends AppCompatActivity  {
FButton bt_disposisi,bt_confirm,disposisi,bt_cancel_disposisi;
MaterialSpinner sp_ao;
TextView segmen,produk,plafond,tenor,nik,nama,tempatlahir,tanggllahir,nohp,jenisUsaha,omsetHari,alamat,kecamatan,kota,provinsi,namaAo,uid,page_title,email,rtrw,waktu_pengajuan,kode_unik,id_aplikasi,kodepos,tanggal_disposisi;
ImageView capsule_close;
CardView dataPemrakarsa;
    @BindView(R.id.tb_custom)
    Toolbar tb_regular;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;
    LinearLayout bottom_sheet;
    AppBarLayout mappbar;
    ArrayAdapter<String> adapter;
    ApiClientAdapter apiClientAdapter;
    RelativeLayout loading;
    List<AomDisposisi> dataAom;
    List<String> dataAomString;
    List<String> kodeAomString;

    String statusDisposisi="belum";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_disposisi);
        ButterKnife.bind(DetailDisposisiActivity.this);
        apiClientAdapter=new ApiClientAdapter(this);
        final AppPreferences appPreferences=new AppPreferences(this);

        //membuat status bar dan tombol tombol navigasi transparan
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }

        //membuat status bar transparan
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setSupportActionBar(tb_regular);
        tb_regular.setTitle("Joni");
        collapsing_toolbar.setTitle("Joni");
        collapsing_toolbar=findViewById(R.id.collapsing_toolbar);
        AppUtil.toolbarRegular(this,"Nama Nasabah");
         mappbar=findViewById(R.id.appbar);

        dataPemrakarsa=findViewById(R.id.cv_datapemrakarsa);


        bt_disposisi=findViewById(R.id.bt_disposisi);
        bt_confirm=findViewById(R.id.bt_confirm_disposisi);
        bt_cancel_disposisi=findViewById(R.id.bt_batal_disposisi);
        bt_disposisi.setButtonColor(getResources().getColor(R.color.colorPrimary));
        bt_disposisi.setTextColor(getResources().getColor(R.color.colorWhite));
        bt_confirm.setButtonColor(getResources().getColor(R.color.main_green_stroke_color));
        bt_cancel_disposisi.setButtonColor(getResources().getColor(R.color.colorWhite));

        capsule_close=findViewById(R.id.iv_capsule_close);

        sp_ao=findViewById(R.id.sp_list_ao);

        loading=findViewById(R.id.progressbar_loading);


        segmen=findViewById(R.id.tv_segmen);
        produk=findViewById(R.id.tv_produk);
        plafond=findViewById(R.id.tv_plafond);
        tenor=findViewById(R.id.tv_tenor);
        nama=findViewById(R.id.tv_nama);
        kecamatan=findViewById(R.id.tv_kecamatan);
        provinsi=findViewById(R.id.tv_provinsi);
        kota=findViewById(R.id.tv_kota);
        email=findViewById(R.id.tv_email);
        rtrw=findViewById(R.id.tv_rt);
        kode_unik=findViewById(R.id.tv_kode_unik);
        kodepos=findViewById(R.id.tv_kodepos_disposisi);
        id_aplikasi=findViewById(R.id.tv_id_aplikasi_disposisi);
        waktu_pengajuan=findViewById(R.id.tv_waktu_pengajuan);
        namaAo=findViewById(R.id.tv_nama_ao);
        uid=findViewById(R.id.tv_uid);
        tanggal_disposisi=findViewById(R.id.tv_tanggal_assigned);



        alamat=findViewById(R.id.tv_alamat);
        nik=findViewById(R.id.tv_nik);
        tempatlahir=findViewById(R.id.tv_tempatlahir);
        tanggllahir=findViewById(R.id.tv_tanggallahir);
        nohp=findViewById(R.id.tv_nomorhp);
        jenisUsaha=findViewById(R.id.tv_jenisusaha);
        omsetHari=findViewById(R.id.tv_pendapatan);

        page_title=findViewById(R.id.tv_page_title);

        //Bottom Sheet instantiation
        bottom_sheet=findViewById(R.id.bottom_sheet);

        //check apakah melihat daftar AO atau riwayat AO
        Disposisi dDisposisi = (Disposisi)getIntent().getSerializableExtra("disposisi");

        if(getIntent().getStringExtra("menuAsal")!=null){
           dataPemrakarsa.setVisibility(View.VISIBLE);
           tanggal_disposisi.setText(dDisposisi.getTANGGAL_ASSIGNED().substring(0,16));//substirng karena ada detiknya segala, jadi gausah diambil
           namaAo.setText(dDisposisi.getNAMA_ASSIGNED());
           uid.setText(dDisposisi.getUID_ASSIGNED());
           bt_disposisi.setVisibility(View.GONE);

        }


        getListAom();



        checkCollapse();
        final BottomSheetBehavior behaviorBottomSheet=BottomSheetBehavior.from(bottom_sheet);

        bt_disposisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp_ao.getSelectedItem()==null|| sp_ao.getSelectedItem().toString().isEmpty()){
                    sp_ao.setError("Harap Pilih AO Disposisi");
                }
                else {
                    if(statusDisposisi.equalsIgnoreCase("sudah")){
                        finish();
                        Toasty.info(DetailDisposisiActivity.this,"Disposisi sudah dilakukan").show();
                    }
                    else{
                        new SweetAlertDialog(DetailDisposisiActivity.this, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("Konfirmasi Disposisi")
                                .setContentText("Anda yakin akan melakukan disposisi ke AO : "+sp_ao.getSelectedItem().toString())
                                .setConfirmText("Ya")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        lakukanDisposisi(id_aplikasi.getText().toString(),kodeAomString.get(sp_ao.getSelectedItemPosition()-1),appPreferences.getUid(),sDialog);
//                                   Log.d("kodeAo",kodeAomString.get(sp_ao.getSelectedItemPosition()));
                                    }
                                }).setCancelText("Batal")
                                .show();
                    }


                }
            }
        });
        bt_cancel_disposisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        capsule_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });



        try{


            //TEXTVIEW SET DATAS

            segmen.setText(dDisposisi.getNAMA_PRODUK());
//            produk.setText(dDisposisi.getNAMA_PRODUK());
            nama.setText(dDisposisi.getNAMA_LENGKAP());
            kecamatan.setText(dDisposisi.getKEC_KTP());
            provinsi.setText(dDisposisi.getPROV_KTP());
            kota.setText(dDisposisi.getKOTA_KTP());
            alamat.setText(dDisposisi.getALAMAT_KTP());
            nik.setText(dDisposisi.getNO_KTP());
            tenor.setText(dDisposisi.getJANGKA_WAKTU()+" bulan");
            kodepos.setText(dDisposisi.getKODE_POS_KTP());
            id_aplikasi.setText(dDisposisi.getFID_APLIKASI());
            kode_unik.setText(dDisposisi.getKODE_UNIK());
            waktu_pengajuan.setText(dDisposisi.getTGL_CREATED().substring(0,16));


            //tenor specialTreatment
            if(dDisposisi.getJANGKA_WAKTU().equalsIgnoreCase("0")){
                tenor.setText("Belum ada data tenor");
            }
            else{
                int tenorInt=Integer.parseInt(dDisposisi.getJANGKA_WAKTU());
                if(tenorInt>=13 && tenorInt%12>0){
                    tenor.setText(tenorInt/12+ " Tahun "+tenorInt%12+" Bulan");
                }
                else if(tenorInt>=13 && tenorInt%12==0){
                    tenor.setText(tenorInt/12+ " Tahun");
                }
                else{
                    tenor.setText(dDisposisi.getJANGKA_WAKTU()+ " Bulan");
                }
            }

            plafond.setText(AppUtil.parseRupiah(dDisposisi.getPLAFOND()));
            email.setText(dDisposisi.getEMAIL());
            rtrw.setText(dDisposisi.getRT_KTP()+"/"+dDisposisi.getRW_KTP());
//            tempatlahir.setText(dDisposisi.getta());
//            tanggllahir.setText(dDisposisi.getTanggal_lahir());
            nohp.setText(dDisposisi.getNO_HP());
//            jenisUsaha.setText(dDisposisi.getBidang_usaha());
//            omsetHari.setText(dDisposisi.getOmzet_per_hari());
        }
        catch(Exception e){

        }




    }

    private void checkCollapse(){

        mappbar.addOnOffsetChangedListener(new AppBarStateChangedListener() {

            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("statenya",state.name());
                if(state.name().equalsIgnoreCase("COLLAPSED")){
                    page_title.setVisibility(View.VISIBLE);

                    try{
                        Disposisi dDisposisi = (Disposisi)getIntent().getSerializableExtra("disposisi");
                        if(dDisposisi.getNAMA_LENGKAP().length()>=16){

                            page_title.setText(dDisposisi.getNAMA_LENGKAP().substring(0,16)+"...");
                        }
                        else{
                            page_title.setText(dDisposisi.getNAMA_LENGKAP());
                        }

                    }
                    catch (Exception e){
                        page_title.setText("Detail Disposisi");
                    }

                }
                else{

                    page_title.setVisibility(View.INVISIBLE);

                }
            }
        } );

    }

    private void getListAom(){
        //connect to server
        loading.setVisibility(View.VISIBLE);
        ReqKodeSkk req = new ReqKodeSkk();
        AppPreferences apppref=new AppPreferences(this);
        req.setKodeSkk(apppref.getKodeSkk());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().getAomDisposisi(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                if(response.isSuccessful()){
                    loading.setVisibility(View.GONE);
                    if(response.body().getStatus().equalsIgnoreCase("00")){
                        String listDataString = response.body().getData().get("dtAOM").toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<AomDisposisi>>() {}.getType();

                        dataAom = gson.fromJson(listDataString, type);
                        dataAomString=new ArrayList<>();
                        kodeAomString=new ArrayList<>();





                        for (int i = 0; i <dataAom.size() ; i++) {
                            dataAomString.add(dataAom.get(i).getNAMA_PEGAWAI());
                           kodeAomString.add(dataAom.get(i).getUID());

                            try{

                                adapter = new ArrayAdapter<>(DetailDisposisiActivity.this, android.R.layout.simple_list_item_1, dataAomString);
                                sp_ao.setAdapter(adapter);
                            }
                            catch(Exception e){

                            }
//                            realm.beginTransaction();
//                            realm.copyToRealm(dataCabang.get(i));
                        }
//                        realm.commitTransaction();


                    }
                    else{
                        Toasty.error(DetailDisposisiActivity.this,"Gagal mendapatkan daftar AOM");
                        finish();
                    }
                }
                else{
                    Toasty.error(DetailDisposisiActivity.this,"Gagal terhubung ke jaringan");
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    private void lakukanDisposisi(String idAplikasi, String uid_pemrakarsa, String uid_pemutus, final SweetAlertDialog dialog1){
        //connect to server

        loading.setVisibility(View.VISIBLE);
        ReqSimpanDisposisi req = new ReqSimpanDisposisi();
        req.setIdAplikasi(idAplikasi);
        req.setUidAssigned(uid_pemrakarsa);
        req.setUidAssigner(uid_pemutus);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().simpanDisposisi(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                if(response.isSuccessful()){
                    loading.setVisibility(View.GONE);
                    if(response.body().getStatus().equalsIgnoreCase("00")){
                        statusDisposisi="sudah";
                        dialog1.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        dialog1.setTitleText("Berhasil");
                        dialog1.setContentText("Berhasil melakukan disposisi\n");
                        dialog1.setConfirmText("Ok");
                        dialog1.showCancelButton(false);
                        dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialog1.dismissWithAnimation();
                                finish();
                            }
                        });

                    }
                    else{
                        Toasty.error(DetailDisposisiActivity.this,"Gagal mendapatkan daftar AOM");
                        finish();
                    }
                }
                else{
                    Toasty.error(DetailDisposisiActivity.this,"Gagal terhubung ke jaringan");
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
            }
        });
    }




}
