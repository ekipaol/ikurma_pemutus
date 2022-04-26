package com.application.bris.brisi_pemutus.page_disposisi.view;

import android.graphics.Color;
import android.os.Build;

import com.application.bris.brisi_pemutus.api.model.request.EmptyRequest;
import com.application.bris.brisi_pemutus.databinding.ActivityDetailDisposisiBinding;
import com.application.bris.brisi_pemutus.model.disposisi.DetailDisposisi;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.simpan_disposisi.ReqSimpanDisposisi;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.aom_disposisi.AomDisposisi;
import com.application.bris.brisi_pemutus.model.disposisi.Disposisi;
import com.application.bris.brisi_pemutus.util.AppBarStateChangedListener;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDisposisiActivity extends AppCompatActivity  {
MaterialSpinner sp_ao;
    ArrayAdapter<String> adapter;
    ApiClientAdapter apiClientAdapter;
    List<AomDisposisi> dataAom;
    List<String> dataAomString;
    List<String> kodeAomString;

    String statusDisposisi="belum";
    DetailDisposisi dtDisposisi;
    ActivityDetailDisposisiBinding binding;
    String idDisposisi;
    AppPreferences appPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailDisposisiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        apiClientAdapter=new ApiClientAdapter(this);
        appPreferences=new AppPreferences(this);

        if(getIntent().hasExtra("id")){
            idDisposisi=getIntent().getStringExtra("id");
        }
        else{
            idDisposisi="";
        }
        //membuat status bar transparan
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setSupportActionBar(binding.tbCustom);
        AppUtil.toolbarRegular(this,"Nama Nasabah");


        if(getIntent().getStringExtra("menuAsal")!=null){
           binding.cvDatapemrakarsa.setVisibility(View.VISIBLE);
           binding.tvTanggalAssigned.setText(dtDisposisi.getDisposisiTanggal().substring(0,16));//substirng karena ada detiknya segala, jadi gausah diambil
           binding.tvNamaAo.setText(dtDisposisi.getDisposisiKepadaUserName());
           binding.tvUid.setText(dtDisposisi.getDisposisiUserId());
           binding.btDisposisi.setVisibility(View.GONE);

        }


        checkCollapse();
        allOnClick();
        loadDisposisi();
    }

    private void checkCollapse(){

        binding.appbar.addOnOffsetChangedListener(new AppBarStateChangedListener() {

            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("statenya",state.name());
                if(state.name().equalsIgnoreCase("COLLAPSED")){
                    binding.tvPageTitle.setVisibility(View.VISIBLE);

                    try{
                        Disposisi dDisposisi = (Disposisi)getIntent().getSerializableExtra("disposisi");
                        if(dDisposisi.getNAMA_LENGKAP().length()>=16){

                            binding.tvPageTitle.setText(dDisposisi.getNAMA_LENGKAP().substring(0,16)+"...");
                        }
                        else{
                            binding.tvPageTitle.setText(dDisposisi.getNAMA_LENGKAP());
                        }

                    }
                    catch (Exception e){
                        binding.tvPageTitle.setText("Detail Disposisi");
                    }

                }
                else{

                    binding.tvPageTitle.setVisibility(View.INVISIBLE);

                }
            }
        } );

    }

    private void loadDisposisi(){
        //connect to server
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().detailDisposisi(idDisposisi);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                if(response.isSuccessful()){
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    if(response.body().getStatus().equalsIgnoreCase("00")){
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<DetailDisposisi>() {}.getType();

                        dtDisposisi = gson.fromJson(listDataString, type);
                        setData();

                    }
                    else{
                      AppUtil.notiferror(DetailDisposisiActivity.this,binding.getRoot(),response.body().getMessage());
                        finish();
                    }
                }
                else{
                    AppUtil.notiferror(DetailDisposisiActivity.this,binding.getRoot(),"Gagal Terhubung Ke Server");
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                AppUtil.notiferror(DetailDisposisiActivity.this,binding.getRoot(),"Gagal Terhubung Ke Server");
                finish();
            }
        });
    }

//    private void getListAom(){
//        //connect to server
//        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
//        Call<ParseResponse> call = apiClientAdapter.getApiInterface().getAomDisposisi(req);
//        call.enqueue(new Callback<ParseResponse>() {
//            @Override
//            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
//                if(response.isSuccessful()){
//                    binding.loading.progressbarLoading.setVisibility(View.GONE);
//                    if(response.body().getStatus().equalsIgnoreCase("00")){
//                        String listDataString = response.body().getData().get("dtAOM").toString();
//                        Gson gson = new Gson();
//                        Type type = new TypeToken<List<AomDisposisi>>() {}.getType();
//
//                         = gson.fromJson(listDataString, type);
//
//
//                        for (int i = 0; i <dataAom.size() ; i++) {
//                            dataAomString.add(dataAom.get(i).getNAMA_PEGAWAI());
//                           kodeAomString.add(dataAom.get(i).getUID());
//
//                            try{
//
//                                adapter = new ArrayAdapter<>(DetailDisposisiActivity.this, android.R.layout.simple_list_item_1, dataAomString);
//                                sp_ao.setAdapter(adapter);
//                            }
//                            catch(Exception e){
//
//                            }
////                            realm.beginTransaction();
////                            realm.copyToRealm(dataCabang.get(i));
//                        }
////                        realm.commitTransaction();
//
//
//                    }
//                    else{
//                        Toasty.error(DetailDisposisiActivity.this,"Gagal mendapatkan daftar AOM");
//                        finish();
//                    }
//                }
//                else{
//                    Toasty.error(DetailDisposisiActivity.this,"Gagal terhubung ke jaringan");
//                    finish();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ParseResponse> call, Throwable t) {
//                Log.d("LOG D", t.getMessage());
//            }
//        });
//    }

//    private void lakukanDisposisi(String idAplikasi, String uid_pemrakarsa, String uid_pemutus, final SweetAlertDialog dialog1){
//        //connect to server
//
//        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
//        ReqSimpanDisposisi req = new ReqSimpanDisposisi();
//        req.setIdReferal(dtDisposisi.getID());
//        req.setUidAssigned(uid_pemrakarsa);
//        req.setUidAssigner(uid_pemutus);
//
//        Call<ParseResponse> call = apiClientAdapter.getApiInterface().detailDisposisi(new EmptyRequest());
//        call.enqueue(new Callback<ParseResponse>() {
//            @Override
//            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
//                if(response.isSuccessful()){
//                    binding.loading.progressbarLoading.setVisibility(View.GONE);
//                    if(response.body().getStatus().equalsIgnoreCase("00")){
//                        statusDisposisi="sudah";
//                        dialog1.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                        dialog1.setTitleText("Berhasil");
//                        dialog1.setContentText("Berhasil melakukan disposisi\n");
//                        dialog1.setConfirmText("Ok");
//                        dialog1.showCancelButton(false);
//                        dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                dialog1.dismissWithAnimation();
//                                finish();
//                            }
//                        });
//
//                    }
//                    else{
//                        dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
//                        dialog1.setTitleText("Gagal");
//                        dialog1.setContentText(response.body().getMessage());
//                        dialog1.setConfirmText("Ok");
//                        dialog1.showCancelButton(false);
//                        dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                dialog1.dismissWithAnimation();
//                                finish();
//                            }
//                        });
//                    }
//                }
//                else{
//                    Toasty.error(DetailDisposisiActivity.this,"Gagal terhubung ke jaringan");
//                    finish();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ParseResponse> call, Throwable t) {
//                Log.d("LOG D", t.getMessage());
//            }
//        });
//    }

    private void allOnClick(){
        BottomSheetBehavior behaviorBottomSheet=BottomSheetBehavior.from(binding.layoutBottomsheet.bottomSheet);

        binding.btDisposisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        binding.layoutBottomsheet.btConfirmDisposisi.setOnClickListener(new View.OnClickListener() {
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
//                                        lakukanDisposisi(binding.tvIdAplikasiDisposisi.getText().toString(),kodeAomString.get(sp_ao.getSelectedItemPosition()-1),appPreferences.getUid(),sDialog);
//                                   Log.d("kodeAo",kodeAomString.get(sp_ao.getSelectedItemPosition()));
                                    }
                                }).setCancelText("Batal")
                                .show();
                    }


                }
            }
        });
        binding.layoutBottomsheet.btBatalDisposisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        binding.layoutBottomsheet.ivCapsuleClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

    private void setData(){
        try{


            //TEXTVIEW SET DATAS

            binding.tvProduk.setText(dtDisposisi.getJenisProdukDesc());
            binding.tvNama.setText(dtDisposisi.getNamaDebitur());
            binding.tvKecamatan.setText(dtDisposisi.getKecamatanUsaha());
            binding.tvProvinsi.setText(dtDisposisi.getProvinsiUsaha());
            binding.tvKota.setText(dtDisposisi.getKotaUsaha());
            binding.tvAlamat.setText(dtDisposisi.getAlamatUsaha());
            binding.tvNik.setText(dtDisposisi.getNoId());
            binding.tvNikPasangan.setText(dtDisposisi.getNoId2());
            binding.tvTenor.setText(dtDisposisi.getTenor()+" bulan");
            binding.tvKodeposDisposisi.setText(dtDisposisi.getKodePosUsaha());
            binding.tvIdAplikasiDisposisi.setText(dtDisposisi.getId());
            binding.tvReferal.setText(dtDisposisi.getReferal());
//            binding.tvKodeReferal.setText(dtDisposisi.getReffNo());
            binding.tvPlafond.setText(AppUtil.parseRupiah(dtDisposisi.getPlafond()));
            binding.tvEmail.setText(dtDisposisi.getEmail());
            binding.tvRt.setText(dtDisposisi.getRtUsaha()+"/"+dtDisposisi.getRwUsaha());
            binding.tvNomorhp.setText(dtDisposisi.getNoHp());

            //belum ada
//            binding.tvWaktuPengajuan.setText(dDisposisi.getTGL_CREATED().substring(0,16));


            //tenor specialTreatment
            if(dtDisposisi.getTenor().equalsIgnoreCase("0")){
                binding.tvTenor.setText("Belum ada data tenor");
            }
            else{
                int tenorInt=Integer.parseInt(dtDisposisi.getTenor());
                if(tenorInt>=13 && tenorInt%12>0){
                    binding.tvTenor.setText(tenorInt/12+ " Tahun "+tenorInt%12+" Bulan");
                }
                else if(tenorInt>=13 && tenorInt%12==0){
                    binding.tvTenor.setText(tenorInt/12+ " Tahun");
                }
                else{
                    binding.tvTenor.setText(dtDisposisi.getTenor()+ " Bulan");
                }
            }

            AppUtil.setImageGlide(DetailDisposisiActivity.this, dtDisposisi.getFotoKtp(),binding.ivFoto);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }




}
