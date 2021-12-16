package com.application.bris.brisi_pemutus.page_putusan.agunan_mesin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.Error;
import com.application.bris.brisi_pemutus.api.model.ParseResponseError;
import com.application.bris.brisi_pemutus.api.model.request.agunan_global_idapl_agunan_cif.AgunanGlobal;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.databinding.AgunanMesinActivityBinding;
import com.application.bris.brisi_pemutus.model.MGenericModel;
import com.application.bris.brisi_pemutus.model.agunan_mesin.AgunanMesin;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.File;
import java.lang.reflect.Type;
import java.security.acl.Permission;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgunanMesinActivity extends AppCompatActivity  {



    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    private String idAgunan;
    private String idApl;
    private String idCif;

    private AgunanMesin dataAgunan;
    private List<AgunanMesin> listDataAgunan;
    private List<MGenericModel> dropdownKehadiran=new ArrayList<>();
    private List<MGenericModel> dropdownXbrl=new ArrayList<>();

    private Bitmap bitmap_foto1, bitmap_foto2,loadedPicture;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private AgunanMesinActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=AgunanMesinActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        AppUtil.toolbarRegular(this, "Agunan Mesin");
        idAgunan = getIntent().getStringExtra("idAgunan");
        idApl = getIntent().getStringExtra("idAplikasi");
        idCif = getIntent().getStringExtra("cif");
        backgroundStatusBar();
        isiDropdown();
        loadData();
        disableEditTexts();


    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void loadData() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        AgunanGlobal req = new AgunanGlobal();
        req.setIdAgunan(Integer.parseInt(idAgunan));
        req.setIdApl(Integer.parseInt(idApl));
        req.setIdCif(Integer.parseInt(idCif));
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryAgunanMesin(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<AgunanMesin>>() {}.getType();

                            String listDataString = response.body().getData().toString();
                            listDataAgunan = gson.fromJson(listDataString, type);
                            dataAgunan=listDataAgunan.get(0);
                            setData();
                        }
                        else{
                            AppUtil.notiferror(AgunanMesinActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 2000);
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanMesinActivity.this, findViewById(android.R.id.content), error.getMessage());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 2000);
                }
            }


            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                AppUtil.notiferror(AgunanMesinActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    private void setData(){
        binding.etTanggalPemeriksaan.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTglPeriksa(), "ddMMyyyy", "dd-MM-yyyy"));
        binding.etJenisDokumen.setText(dataAgunan.getKelengkapanDokumen());
        binding.etTanggalLaporan.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTglLaporan(), "ddMMyyyy", "dd-MM-yyyy"));

        for (int i = 0; i <dropdownKehadiran.size() ; i++) {
            if(dropdownKehadiran.get(i).getID().equalsIgnoreCase(dataAgunan.getKehadiranPemilik())){
                binding.etKehadiranPemilik.setText(dropdownKehadiran.get(i).getDESC());
            }
        }
        for (int i = 0; i <dropdownXbrl.size() ; i++) {
            if(dropdownXbrl.get(i).getID().equalsIgnoreCase(dataAgunan.getJenisAgunanXbrl())){
                binding.etJenisAgunanXbrl.setText(dropdownXbrl.get(i).getDESC());
            }
        }
        binding.etMerk.setText(dataAgunan.getMerkMesin());
        binding.etKapasitas.setText(dataAgunan.getKapasitasMesin());
        binding.etModel.setText(dataAgunan.getModelMesin());
        binding.etTahun.setText(dataAgunan.getTahunMesin());
        binding.etSerialNumber.setText(dataAgunan.getSerialNumberMesin());
        binding.etLokasi.setText(dataAgunan.getLokasiMesin());
        binding.etNamaPemilik.setText(dataAgunan.getNamaPemilikMesin());
        binding.etHubungan.setText(dataAgunan.getHubungan());
        binding.etDataPembanding.setText(dataAgunan.getDataPembanding());
        binding.etNilaiMarket.setText(dataAgunan.getNilaiMarket());
        binding.etNilaiLikuidasi.setText(dataAgunan.getNilaiLikuidasi());
        binding.etKeterangan.setText(dataAgunan.getKeteranganLain());

        bitmap_foto1 = setLoadImage(binding.ivFoto1, Integer.parseInt(dataAgunan.getIdFoto1()));
        bitmap_foto2 = setLoadImage(binding.ivFoto2, Integer.parseInt(dataAgunan.getIdFoto2()));

    }

    private void isiDropdown(){
        dropdownKehadiran.add(new MGenericModel("1","Hadir di Lokasi Agunan"));
        dropdownKehadiran.add(new MGenericModel("2","Tidak Hadir dan Diwakilkan"));
        dropdownKehadiran.add(new MGenericModel("3","Tidak Hadir dan Tidak Diwakilkan"));

        dropdownXbrl.add(new MGenericModel("318","Aset Berwujud - Mesin yang menjadi satu dengan tanah"));
        dropdownXbrl.add(new MGenericModel("319","Aset Berwujud - Mesin yang tidak menjadi satu dengan tanah"));
    }

  private void disableEditTexts(){
        AppUtil.disableEditTexts(binding.getRoot());
  }


    public Bitmap setLoadImage(final ImageView iv, int idFoto){
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFoto + idFoto;
        Glide
                .with(AgunanMesinActivity.this)
                .asBitmap()
                .load(url_photo)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv.setImageBitmap(resource);
                        loadedPicture = resource;
                    }
                });
        return loadedPicture;
    }


    }


