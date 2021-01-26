package com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Build;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.kelengkapan_dokumen.ReqKelengkapanDokumen;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.kelengkapan_dokumen.KelengkapanDokumen;
import com.application.bris.brisi_pemutus.model.kelengkapan_dokumen_agunan.KelengkapanDokumenAgunan;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_putusan.PutusanFrontMenu;
import com.application.bris.brisi_pemutus.page_putusan.history_catatan.CatatanActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import me.grantland.widget.AutofitTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityKelengkapanDokumen extends AppCompatActivity {

    @BindView(R.id.cv_ktp)
    CardView cv_ktp;
    @BindView(R.id.cb_ktp)
    CheckBox cb_ktp;
    @BindView(R.id.tv_ktp)
    AutofitTextView tv_ktp;

    @BindView(R.id.cv_kartu_keluarga)
    CardView cv_kartu_keluarga;
    @BindView(R.id.cb_kartu_keluarga)
    CheckBox cb_kartu_keluarga;
    @BindView(R.id.tv_kartu_keluarga)
    TextView tv_kartu_keluarga;


    @BindView(R.id.cv_surat_nikah)
    CardView cv_surat_nikah;
    @BindView(R.id.cb_surat_nikah)
    CheckBox cb_surat_nikah;
    @BindView(R.id.tv_surat_nikah)
    TextView tv_surat_nikah;


    @BindView(R.id.cv_pas_photo)
    CardView cv_pas_photo;
    @BindView(R.id.cb_pas_photo)
    CheckBox cb_pas_photo;
    @BindView(R.id.tv_pas_photo)
    TextView tv_pas_photo;


    @BindView(R.id.cv_npwp)
    CardView cv_npwp;
    @BindView(R.id.cb_npwp)
    CheckBox cb_npwp;
    @BindView(R.id.tv_npwp)
    TextView tv_npwp;


    @BindView(R.id.cv_formulir_aplikasi)
    CardView cv_formulir_aplikasi;
    @BindView(R.id.cb_formulir_aplikasi)
    CheckBox cb_formulir_aplikasi;
    @BindView(R.id.tv_formulir_aplikasi)
    TextView tv_formulir_aplikasi;


    @BindView(R.id.cv_surat_ijin_usaha)
    CardView cv_surat_ijin_usaha;
    @BindView(R.id.cb_surat_ijin_usaha)
    CheckBox cb_surat_ijin_usaha;
    @BindView(R.id.tv_surat_ijin_usaha)
    TextView tv_surat_ijin_usaha;


    @BindView(R.id.cv_catatan_keuangan)
    CardView cv_catatan_keuangan;
    @BindView(R.id.cb_catatan_keuangan)
    CheckBox cb_catatan_keuangan;
    @BindView(R.id.tv_catatan_keuangan)
    AutofitTextView tv_catatan_keuangan;


    @BindView(R.id.cv_daftar_rencana_pembiayaan)
    CardView cv_daftar_rencana_pembiayaan;
    @BindView(R.id.cb_daftar_rencana_pembiayaan)
    CheckBox cb_daftar_rencana_pembiayaan;
    @BindView(R.id.tv_daftar_rencana_pembiayaan)
    AutofitTextView tv_daftar_rencana_pembiayaan;


    @BindView(R.id.cv_surat_pernyataan_kur)
    CardView cv_surat_pernyataan_kur;
    @BindView(R.id.cb_surat_pernyataan_kur)
    CheckBox cb_surat_pernyataan_kur;
    @BindView(R.id.tv_surat_pernyataan_kur)
    AutofitTextView tv_surat_pernyataan_kur;


    @BindView(R.id.cv_surat_keterangan_lunas)
    CardView cv_surat_keterangan_lunas;
    @BindView(R.id.cb_surat_keterangan_lunas)
    CheckBox cb_surat_keterangan_lunas;
    @BindView(R.id.tv_surat_keterangan_lunas)
    AutofitTextView tv_surat_keterangan_lunas;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    //button lihat foto

    @BindView(R.id.bt_ktp_kelengkapan)
    Button bt_ktp_kelengkapan;
    @BindView(R.id.bt_kartu_keluarga_kelengkapan)
    Button bt_kartu_keluarga_kelengkapan;
    @BindView(R.id.bt_surat_nikah_kelengkapan)
    Button bt_surat_nikah_kelengkapan;
    @BindView(R.id.bt_pas_photo_kelengkapan)
    Button bt_pas_photo_kelengkapan;
    @BindView(R.id.bt_npwp_kelengkapan)
    Button bt_npwp_kelengkapan;
    @BindView(R.id.bt_formulir_kelengkapan)
    Button bt_formulir_kelengkapan;
    @BindView(R.id.bt_surat_ijin_kelengkapan)
    Button bt_surat_ijin_kelengkapan;
    @BindView(R.id.bt_catatan_keuangan_kelengkapan)
    Button bt_catatan_keuangan_kelengkapan;
    @BindView(R.id.bt_daftar_rencana_kelengkapan)
    Button bt_daftar_rencana_kelengkapan;
    @BindView(R.id.bt_pernyataan_kur)
    Button bt_pernyataan_kur;
    @BindView(R.id.bt_keterangan_lunas)
    Button bt_keterangan_lunas;

    @BindView(R.id.rv_agunan_kelengkapan)
    RecyclerView rv_agunan_kelengkapan;

    @BindView(R.id.bt_lanjut_kelengkapan_data)
    Button bt_lanjut_kelengkapan_data;

    @BindView(R.id.tv_dokumen_agunan)
    TextView tv_dokumen_agunan;

    @BindView(R.id.et_nomorsiup)
    EditText et_nomorsiup;

    AllDataFront superData;




    private SearchView searchView;
    List<Putusan> dataHotProspek;
    AdapterKelengkapanDokumenAgunan adapterKelengkapanDokumenAgunan;
    LinearLayoutManager layoutPipeline;
    ApiClientAdapter apiClientAdapter;
    KelengkapanDokumen dataKelengkapan;
    List<KelengkapanDokumenAgunan> dataKelengkapanAgunan;

    AppPreferences appPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelengkapan_data);



        main();
        loadData();
        backgroundStatusBar();

        //set kelengkapan as already read
        appPreferences = new AppPreferences(this);
        appPreferences.setReadKelengkapan("yes");

        superData=(AllDataFront)getIntent().getSerializableExtra("superData");



        //kalau belum dilihat semua, gak bisa di putus
        if(appPreferences.getReadKelengkapan().equalsIgnoreCase("no")||appPreferences.getReadPreScreening().equalsIgnoreCase("no")||appPreferences.getReadDataLengkap().equalsIgnoreCase("no")||appPreferences.getReadSektorEkonomi().equalsIgnoreCase("no")||appPreferences.getReadLkn().equalsIgnoreCase("no")||appPreferences.getReadRpc().equalsIgnoreCase("no")||appPreferences.getReadAgunan().equalsIgnoreCase("no")||appPreferences.getReadScoring().equalsIgnoreCase("no")){
            bt_lanjut_kelengkapan_data.setVisibility(View.GONE);
        }

        if(superData.getAsalHalaman().equalsIgnoreCase("riwayat")){
            bt_lanjut_kelengkapan_data.setVisibility(View.GONE);
        }


        bt_lanjut_kelengkapan_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityKelengkapanDokumen.this, CatatanActivity.class);
                intent.putExtra("cif", superData.getCif());
                intent.putExtra("idAplikasi", Integer.parseInt(superData.getIdAplikasi()));
                intent.putExtra("fidStatus",superData.getFidStatus());
                intent.putExtra("superData",superData);


                //when back make this thing go to putusan frontmenu
                startActivity(intent);
            }
        });



        //start onclick listeners - HANYA DIGUNAKAN DI AO, NONAKTIFKAN DI PEMUTUS

//        cv_ktp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(cb_ktp.isChecked()){
//                    cb_ktp.setChecked(false);
//                }
//                else{
//                    cb_ktp.setChecked(true);
//                }
//
//            }
//        });
//
//        cv_kartu_keluarga.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(cb_kartu_keluarga.isChecked()){
//                    cb_kartu_keluarga.setChecked(false);
//                }
//                else{
//                    cb_kartu_keluarga.setChecked(true);
//                }
//
//            }
//        });
//
//        cv_surat_nikah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(cb_surat_nikah.isChecked()){
//                    cb_surat_nikah.setChecked(false);
//                }
//                else{
//                    cb_surat_nikah.setChecked(true);
//                }
//
//            }
//        });
//
//        cv_pas_photo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(cb_pas_photo.isChecked()){
//                    cb_pas_photo.setChecked(false);
//                }
//                else{
//                    cb_pas_photo.setChecked(true);
//                }
//
//            }
//        });
//
//
//        cv_npwp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(cb_npwp.isChecked()){
//                    cb_npwp.setChecked(false);
//                }
//                else{
//                    cb_npwp.setChecked(true);
//                }
//
//            }
//        });
//
//
//        cv_formulir_aplikasi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(cb_formulir_aplikasi.isChecked()){
//                    cb_formulir_aplikasi.setChecked(false);
//                }
//                else{
//                    cb_formulir_aplikasi.setChecked(true);
//                }
//
//            }
//        });
//
//
//        cv_surat_ijin_usaha.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(cb_surat_ijin_usaha.isChecked()){
//                    cb_surat_ijin_usaha.setChecked(false);
//                }
//                else{
//                    cb_surat_ijin_usaha.setChecked(true);
//                }
//
//            }
//        });
//
//
//        cv_catatan_keuangan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(cb_catatan_keuangan.isChecked()){
//                    cb_catatan_keuangan.setChecked(false);
//                }
//                else{
//                    cb_catatan_keuangan.setChecked(true);
//                }
//
//            }
//        });
//
//        cv_daftar_rencana_pembiayaan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(cb_daftar_rencana_pembiayaan.isChecked()){
//                    cb_daftar_rencana_pembiayaan.setChecked(false);
//                }
//                else{
//                    cb_daftar_rencana_pembiayaan.setChecked(true);
//                }
//
//            }
//        });
//
//        cv_daftar_rencana_pembiayaan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(cb_daftar_rencana_pembiayaan.isChecked()){
//                    cb_daftar_rencana_pembiayaan.setChecked(false);
//                }
//                else{
//                    cb_daftar_rencana_pembiayaan.setChecked(true);
//                }
//
//            }
//        });
//
//        cv_surat_pernyataan_kur.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(cb_surat_pernyataan_kur.isChecked()){
//                    cb_surat_pernyataan_kur.setChecked(false);
//                }
//                else{
//                    cb_surat_pernyataan_kur.setChecked(true);
//                }
//
//            }
//        });
//
//        cv_surat_keterangan_lunas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(cb_surat_keterangan_lunas.isChecked()){
//                    cb_surat_keterangan_lunas.setChecked(false);
//                }
//                else{
//                    cb_surat_keterangan_lunas.setChecked(true);
//                }
//
//            }
//        });



    }

    @Override
    public void onBackPressed() { finish(); }



//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        ActivityKelengkapanDokumen.this.recreate();
//    }



    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void main(){
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        AppUtil.toolbarRegular(this, "Kelengkapan Dokumen");
        ImageView backToolbar = findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityKelengkapanDokumen.this, PutusanFrontMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        bt_ktp_kelengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityKelengkapanDokumen.this,ActivityFotoKelengkapanDokumen.class);
                startActivity(intent);
            }
        });

    }

    public void loadData(){
        loading.setVisibility(View.VISIBLE);
        //real data
        ReqKelengkapanDokumen req = new ReqKelengkapanDokumen();
        req.setId_aplikasi(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));


        //pantekan buat testing
//       ReqKelengkapanDokumen req = new ReqKelengkapanDokumen();
//       req.setId_aplikasi(101705);


        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryKelengkapanDokumen(req);

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){

                            String dataKelengkapanString = response.body().getData().get("kelDokumen").toString();
                            String fotoKategoriString=response.body().getData().get("kelDokumen").getAsJsonObject().get("ID_DOKUMEN_AGUNAN").toString();

                            Log.d("fotokategoriString",fotoKategoriString);
                            Gson gson = new Gson();
                            Type type = new TypeToken<KelengkapanDokumen>() {
                            }.getType();

                            Type type2 = new TypeToken<List<KelengkapanDokumenAgunan>>() {
                            }.getType();

                            dataKelengkapan = gson.fromJson(dataKelengkapanString, type);
                            dataKelengkapanAgunan=gson.fromJson(fotoKategoriString,type2);


                            if(dataKelengkapanAgunan.size()==0){
                                tv_dokumen_agunan.setVisibility(View.GONE);
                            }


                            //menyimpan id formulir ke preferences, agar foto formulir bisa diakses kembali di halaman putusan
                         appPreferences.setIdFotoFormulir(Integer.toString(dataKelengkapan.getId_foto_dokumen_aplikasi()));
                            //start kondisi checklist
                            if(dataKelengkapan.getSuratPernyataanNasabah()){
                                cb_daftar_rencana_pembiayaan.setChecked(true);
                                bt_daftar_rencana_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_daftar_rencana_pembiayaan.setChecked(false);
                                bt_daftar_rencana_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getFcNpwpPribadi()){
                                cb_npwp.setChecked(true);
                                bt_npwp_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_npwp.setChecked(false);
                                bt_npwp_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getFcSuratNikah()){
                                cb_surat_nikah.setChecked(true);
                                bt_surat_nikah_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_surat_nikah.setChecked(false);
                                bt_surat_nikah_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getFcKtp()){
                                cb_ktp.setChecked(true);
                                bt_ktp_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_ktp.setChecked(false);
                                bt_ktp_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getLaporanKeuangan()){
                                cb_catatan_keuangan.setChecked(true);
                                bt_catatan_keuangan_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_catatan_keuangan.setChecked(false);
                                bt_catatan_keuangan_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getPasPhoto()){
                                cb_pas_photo.setChecked(true);
                                bt_pas_photo_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_pas_photo.setChecked(false);
                                bt_pas_photo_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getAplikasiPermohonan()){
                                cb_formulir_aplikasi.setChecked(true);
                                bt_formulir_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_formulir_aplikasi.setChecked(false);
                                bt_formulir_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getFcKk()){
                                cb_kartu_keluarga.setChecked(true);
                                bt_kartu_keluarga_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_kartu_keluarga.setChecked(false);
                                bt_kartu_keluarga_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getFcSiup()){
                                cb_surat_ijin_usaha.setChecked(true);
                                bt_surat_ijin_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_surat_ijin_usaha.setChecked(false);
                                bt_surat_ijin_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getSuratKeteranganLunasKur()){
                                cb_surat_keterangan_lunas.setChecked(true);
                                bt_keterangan_lunas.setVisibility(View.VISIBLE);
                            }

                            else{
                                cb_surat_keterangan_lunas.setChecked(false);
                                bt_keterangan_lunas.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getSuratPernyataanKur()){
                                cb_surat_pernyataan_kur.setChecked(true);
                                bt_pernyataan_kur.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_surat_pernyataan_kur.setChecked(false);
                                bt_pernyataan_kur.setVisibility(View.GONE);
                            }

                            //end kondisi checklist

                            adapterKelengkapanDokumenAgunan = new AdapterKelengkapanDokumenAgunan(ActivityKelengkapanDokumen.this, dataKelengkapanAgunan);
                            rv_agunan_kelengkapan.setLayoutManager(new LinearLayoutManager(ActivityKelengkapanDokumen.this));
                            rv_agunan_kelengkapan.setItemAnimator(new DefaultItemAnimator());
                            rv_agunan_kelengkapan.setAdapter(adapterKelengkapanDokumenAgunan);



                            //START ONCLICK LIHAT FOTO
                            try{
                                onClickLihatFoto(bt_ktp_kelengkapan, dataKelengkapan.getId_foto_dokumen_ktp());
                                onClickLihatFoto(bt_kartu_keluarga_kelengkapan, dataKelengkapan.getId_foto_dokumen_kk());
                                onClickLihatFoto(bt_surat_nikah_kelengkapan, dataKelengkapan.getId_foto_dokumen_surat_nikah());
                                onClickLihatFoto(bt_pas_photo_kelengkapan, dataKelengkapan.getId_foto_dokumen_pas_photo());
                                onClickLihatFoto(bt_npwp_kelengkapan, dataKelengkapan.getId_foto_dokumen_npwp());
                                onClickLihatFoto(bt_formulir_kelengkapan, dataKelengkapan.getId_foto_dokumen_aplikasi());
                                onClickLihatFoto(bt_surat_ijin_kelengkapan, dataKelengkapan.getId_foto_dokumen_siup());
                                onClickLihatFoto(bt_catatan_keuangan_kelengkapan, dataKelengkapan.getId_foto_dokumen_laporan_keuangan());
                                onClickLihatFoto(bt_daftar_rencana_kelengkapan, dataKelengkapan.getId_foto_dokumen_pernyataan_nasabah());
                                onClickLihatFoto(bt_pernyataan_kur, dataKelengkapan.getId_foto_dokumen_pernyataan_kur());
                                onClickLihatFoto(bt_keterangan_lunas, dataKelengkapan.getId_foto_dokumen_lunas_kur());

                                //end onclick lihat foto

                                //set no SKU
                                et_nomorsiup.setText("No SIUP : "+dataKelengkapan.getNoSku());
                            }
                            catch (Exception e) {

                            Log.d("Exception_foto",e.toString());

                            }




                        }
                        else{
                            AppUtil.notiferror(ActivityKelengkapanDokumen.this, findViewById(android.R.id.content), response.body().getMessage());
                            finish();
                        }
                    }
                    else {
                        //error message minta ke bang idong
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityKelengkapanDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                finish();
            }
        });


    }

    private void onClickLihatFoto(Button butt, final int id_foto){
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityKelengkapanDokumen.this,ActivityPreviewFotoSecondary.class);
                intent.putExtra("id_foto",id_foto);
                startActivity(intent);
            }
        });
    }








}
