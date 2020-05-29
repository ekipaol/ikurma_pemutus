package com.application.bris.brisi_pemutus.page_konsumer_purna.page_konsumer_purna.kelengkapan_dokumen;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.os.Build;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.kelengkapan_dokumen.ReqKelengkapanDokumen;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.kelengkapan_dokumen.KonsumerKmgKelengkapanDokumen;
import com.application.bris.brisi_pemutus.model.kelengkapan_dokumen_agunan.KelengkapanDokumenAgunan;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.front_menu.PutusanFrontMenuKmg;
import com.application.bris.brisi_pemutus.page_putusan.history_catatan.CatatanActivity;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityFotoKelengkapanDokumen;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityPreviewFotoSecondary;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.AdapterKelengkapanDokumenAgunan;
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

public class KelengkapanDokumenPurnaActivity extends AppCompatActivity {

    @BindView(R.id.tv_dokumen_agunan)
    TextView tv_dokumen_agunan;

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

    @BindView(R.id.cv_sk_pensiun)
    CardView cv_sk_pensiun;
    @BindView(R.id.cb_sk_pensiun)
    CheckBox cb_sk_pensiun;
    @BindView(R.id.tv_sk_pensiun)
    TextView tv_sk_pensiun;

    @BindView(R.id.cv_karip)
    CardView cv_karip;
    @BindView(R.id.cb_karip)
    CheckBox cb_karip;
    @BindView(R.id.tv_karip)
    TextView tv_karip;

    @BindView(R.id.cv_bukti_pensiun)
    CardView cv_bukti_pensiun;
    @BindView(R.id.cb_bukti_pensiun)
    CheckBox cb_bukti_pensiun;
    @BindView(R.id.tv_bukti_pensiun)
    TextView tv_bukti_pensiun;

    @BindView(R.id.cv_sk_janda_duda)
    CardView cv_sk_janda_duda;
    @BindView(R.id.cb_sk_janda_duda)
    CheckBox cb_sk_janda_duda;
    @BindView(R.id.tv_sk_janda_duda)
    TextView tv_sk_janda_duda;

    @BindView(R.id.cv_spk)
    CardView cv_spk;
    @BindView(R.id.cb_spk)
    CheckBox cb_spk;
    @BindView(R.id.tv_spk)
    TextView tv_spk;

    @BindView(R.id.cv_surat_keterangan_nasabah)
    CardView cv_surat_keterangan_nasabah;
    @BindView(R.id.cb_surat_keterangan_nasabah)
    CheckBox cb_surat_keterangan_nasabah;
    @BindView(R.id.tv_surat_keterangan_nasabah)
    TextView tv_surat_keterangan_nasabah;


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

    @BindView(R.id.bt_sk_pensiun)
    Button bt_sk_pensiun;
    @BindView(R.id.bt_karip)
    Button bt_karip;
    @BindView(R.id.bt_bukti_pensiun)
    Button bt_bukti_pensiun;
    @BindView(R.id.bt_sk_janda_duda)
    Button bt_sk_janda_duda;
    @BindView(R.id.bt_spk)
    Button bt_spk;
    @BindView(R.id.bt_surat_keterangan_nasabah)
    Button bt_surat_keterangan_nasabah;

    @BindView(R.id.rv_agunan_kelengkapan)
    RecyclerView rv_agunan_kelengkapan;

    @BindView(R.id.bt_lanjut_kelengkapan_data)
    Button bt_lanjut_kelengkapan_data;

    AllDataFront superData;
    Call<ParseResponse> call;




    private SearchView searchView;
    List<Putusan> dataHotProspek;
    AdapterKelengkapanDokumenAgunan adapterKelengkapanDokumenAgunan;
    LinearLayoutManager layoutPipeline;
    ApiClientAdapter apiClientAdapter;
    KonsumerKmgKelengkapanDokumen dataKelengkapan;
    List<KelengkapanDokumenAgunan> dataKelengkapanAgunan;

    AppPreferences appPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelengkapan_dokumen_purna_activity);

        superData=(AllDataFront)getIntent().getSerializableExtra("superData");


        main();
        loadData();
        backgroundStatusBar();

        //set kelengkapan as already read
        appPreferences = new AppPreferences(this);
        appPreferences.setReadKelengkapan("yes");






//        kalau belum dilihat semua, gak bisa di putus
        if(appPreferences.getReadKelengkapan().equalsIgnoreCase("no")||appPreferences.getReadPreScreening().equalsIgnoreCase("no")||appPreferences.getReadDataLengkap().equalsIgnoreCase("no")||appPreferences.getReadSektorEkonomi().equalsIgnoreCase("no")||appPreferences.getReadAgunan().equalsIgnoreCase("no")||appPreferences.getReadDataFinansial().equalsIgnoreCase("no")||appPreferences.getReadScoring().equalsIgnoreCase("no")){
            bt_lanjut_kelengkapan_data.setVisibility(View.GONE);
        }
        else{
            bt_lanjut_kelengkapan_data.setVisibility(View.VISIBLE);
        }

        Log.d("asal halaman",superData.getAsalHalaman());
        if(superData.getAsalHalaman().equalsIgnoreCase("riwayat")){
            bt_lanjut_kelengkapan_data.setVisibility(View.GONE);
        }

        bt_lanjut_kelengkapan_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(KelengkapanDokumenPurnaActivity.this, CatatanActivity.class);
                intent.putExtra("cif", superData.getCif());
                intent.putExtra("idAplikasi", Integer.parseInt(superData.getIdAplikasi()));
                intent.putExtra("fidStatus",superData.getFidStatus());
                intent.putExtra("superData",superData);


                //when back make this thing go to putusan frontmenu
                startActivity(intent);
            }
        });





    }

    @Override
    public void onBackPressed() { finish(); }




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
                Intent intent = new Intent(KelengkapanDokumenPurnaActivity.this, PutusanFrontMenuKmg.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        bt_ktp_kelengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(KelengkapanDokumenPurnaActivity.this, ActivityFotoKelengkapanDokumen.class);
                startActivity(intent);
            }
        });

    }

    public void loadData(){
        loading.setVisibility(View.VISIBLE);
        //real data
//        ReqKelengkapanDokumen req = new ReqKelengkapanDokumen();
//        req.setId_aplikasi(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));


        //pantekan buat testing
        ReqKelengkapanDokumen req = new ReqKelengkapanDokumen();
//       req.setId_aplikasi(101928);
//        Toast.makeText(KelengkapanDokumenPurnaActivity.this, "Id Aplikasi masih hardcode", Toast.LENGTH_SHORT).show();

        req.setId_aplikasi(Integer.parseInt(superData.getIdAplikasi()));



        //multifaedah or konsumer
        if(superData.getKodeProduk().equalsIgnoreCase("428")){
            call = apiClientAdapter.getApiInterface().inquiryKelengkapanDokumenKmgMikro(req);
        }
        else{
            call = apiClientAdapter.getApiInterface().inquiryKelengkapanDokumenKonsumer(req);
        }
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){

                            String dataKelengkapanString = response.body().getData().get("kelDokumen").toString();
//                            String fotoKategoriString=response.body().getData().get("kelDokumen").getAsJsonObject().get("ID_DOKUMEN_AGUNAN").toString();

//                            Log.d("fotokategoriString",fotoKategoriString);
                            Gson gson = new Gson();
                            Type type = new TypeToken<KonsumerKmgKelengkapanDokumen>() {
                            }.getType();

                            Type type2 = new TypeToken<List<KelengkapanDokumenAgunan>>() {
                            }.getType();

                            dataKelengkapan = gson.fromJson(dataKelengkapanString, type);

                            //hapus comment disekitar tv_dokumen visibility, jika sudah ada dokumen agunan yang dapat diterima
//                            dataKelengkapanAgunan=gson.fromJson(fotoKategoriString,type2);
//                            if(dataKelengkapanAgunan.size()==0){
                            tv_dokumen_agunan.setVisibility(View.GONE);
//                            }



                            //menyimpan id formulir ke preferences, agar di halaman putusan bisa diakses kembali untuk bagian sumary pmebiayaan diatas yang warnanya hijau

                            appPreferences.setIdFotoFormulir(Integer.toString(dataKelengkapan.getID_DOKUMEN_APLIKASI()));
                            //start kondisi checklist
                            if(dataKelengkapan.getFC_KTP()){
                                cb_ktp.setChecked(true);
                                bt_ktp_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_ktp.setChecked(false);
                                bt_ktp_kelengkapan.setVisibility(View.GONE);
                            }

//                            if(dataKelengkapan.getFC_NPWP_PRIBADI()){
//                                cb_npwp.setChecked(true);
//                                bt_npwp_kelengkapan.setVisibility(View.VISIBLE);
//                            }
//                            else{
//                                cb_npwp.setChecked(false);
//                                bt_npwp_kelengkapan.setVisibility(View.GONE);
//                            }

                            if(dataKelengkapan.getAPLIKASI_PERMOHONAN()){
                                cb_formulir_aplikasi.setChecked(true);
                                bt_formulir_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_formulir_aplikasi.setChecked(false);
                                bt_formulir_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getFC_KK()){
                                cb_kartu_keluarga.setChecked(true);
                                bt_kartu_keluarga_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_kartu_keluarga.setChecked(false);
                                bt_kartu_keluarga_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getFC_SURAT_NIKAH()){
                                cb_surat_nikah.setChecked(true);
                                bt_surat_nikah_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_surat_nikah.setChecked(false);
                                bt_surat_nikah_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getPAS_PHOTO()){
                                cb_pas_photo.setChecked(true);
                                bt_pas_photo_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_pas_photo.setChecked(false);
                                bt_pas_photo_kelengkapan.setVisibility(View.GONE);
                            }


                            if(dataKelengkapan.getSuratPensiun()){
                                cb_sk_pensiun.setChecked(true);
                                bt_sk_pensiun.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_sk_pensiun.setChecked(false);
                                bt_sk_pensiun.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getIdentitasPensiun()){
                                cb_karip.setChecked(true);
                                bt_karip.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_karip.setChecked(false);
                                bt_karip.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getSLIP_GAJI()){
                                cb_bukti_pensiun.setChecked(true);
                                bt_bukti_pensiun.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_bukti_pensiun.setChecked(false);
                                bt_bukti_pensiun.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getSuratJandaDuda()){
                                cb_sk_janda_duda.setChecked(true);
                                bt_sk_janda_duda.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_sk_janda_duda.setChecked(false);
                                bt_sk_janda_duda.setVisibility(View.GONE);
                            }

//                            if(dataKelengkapan.getSuratKesehatan()){
//                                cb_spk.setChecked(true);
//                                bt_spk.setVisibility(View.VISIBLE);
//                            }
//                            else{
//                                cb_spk.setChecked(false);
//                                bt_spk.setVisibility(View.GONE);
//                            }

                            if(dataKelengkapan.getSuratNasabahRpc()){
                                cb_surat_keterangan_nasabah.setChecked(true);
                                bt_surat_keterangan_nasabah.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_surat_keterangan_nasabah.setChecked(false);
                                bt_surat_keterangan_nasabah.setVisibility(View.GONE);
                            }



                            //end kondisi checklist


                            //belum ada agunan

//                            adapterKelengkapanDokumenAgunan = new AdapterKelengkapanDokumenAgunan(KelengkapanDokumenPurnaActivity.this, dataKelengkapanAgunan);
//                            rv_agunan_kelengkapan.setLayoutManager(new LinearLayoutManager(KelengkapanDokumenPurnaActivity.this));
//                            rv_agunan_kelengkapan.setItemAnimator(new DefaultItemAnimator());
//                            rv_agunan_kelengkapan.setAdapter(adapterKelengkapanDokumenAgunan);



                            //START ONCLICK LIHAT FOTO
                            try{
                                onClickLihatFoto(bt_ktp_kelengkapan, dataKelengkapan.getID_DOKUMEN_KTP());
                                onClickLihatFoto(bt_kartu_keluarga_kelengkapan, dataKelengkapan.getID_DOKUMEN_KK());
                                onClickLihatFoto(bt_pas_photo_kelengkapan, dataKelengkapan.getID_DOKUMEN_PAS_PHOTO());
//                                onClickLihatFoto(bt_npwp_kelengkapan, dataKelengkapan.getID_DOKUMEN_NPWP_PRIBADI());
                                onClickLihatFoto(bt_formulir_kelengkapan, dataKelengkapan.getID_DOKUMEN_APLIKASI());

                                //end onclick lihat foto

                                //start onclick lihat pdf
                                onClickLihatPdf(bt_surat_nikah_kelengkapan, dataKelengkapan.getID_DOKUMEN_SURAT_NIKAH());
                                onClickLihatPdf(bt_sk_pensiun, dataKelengkapan.getIdPhotosuratPensiun());
                                onClickLihatPdf(bt_karip, dataKelengkapan.getIdPhotoidentitasPensiun());
                                onClickLihatPdf(bt_surat_keterangan_nasabah, dataKelengkapan.getIdPhotosuratNasabahRpc());
                                onClickLihatPdf(bt_bukti_pensiun, dataKelengkapan.getID_SLIP_GAJI());
                                onClickLihatPdf(bt_sk_janda_duda, dataKelengkapan.getIdPhotosuratJandaDuda());
//                                onClickLihatPdf(bt_spk, dataKelengkapan.getIdPhotosuratKesehatan());


                                //end onclick lihat pdf
                            }
                            catch (Exception e) {

                                Log.d("Exception_foto",e.toString());
                                AppUtil.notiferror(KelengkapanDokumenPurnaActivity.this, findViewById(android.R.id.content),"Terjadi Kesalahan");

                            }

                        }
                        else{
                            AppUtil.notiferror(KelengkapanDokumenPurnaActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            finish();
                        }
                    }
                    else {
                        AppUtil.notiferror(KelengkapanDokumenPurnaActivity.this, findViewById(android.R.id.content),"Terjadi Kesalahan");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KelengkapanDokumenPurnaActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                finish();
            }
        });


    }

    private void onClickLihatFoto(Button butt, final int id_foto){
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(KelengkapanDokumenPurnaActivity.this, ActivityPreviewFotoSecondary.class);
                intent.putExtra("id_foto",id_foto);
                startActivity(intent);
            }
        });
    }

    private void onClickLihatPdf(Button butt, final int id_pdf){
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_pdf = UriApi.Baseurl.URL + UriApi.getPdf.urlPdf + id_pdf;
                Uri external = Uri.parse(url_pdf);
                Intent intentPdf;
                intentPdf = new Intent(Intent.ACTION_VIEW);
                intentPdf.setDataAndType(external, "application/pdf");
                try {
                    startActivity(intentPdf);
                } catch (ActivityNotFoundException e) {
                    // No application to view, ask to download one
                    AlertDialog.Builder builder = new AlertDialog.Builder(KelengkapanDokumenPurnaActivity.this);
                    builder.setTitle("Anda Belum Memiliki Aplikasi untuk Membaca PDF");
                    builder.setMessage("Download Aplikasi PDF dari Play Store??");
                    builder.setPositiveButton("Download",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent marketIntent = new Intent(Intent.ACTION_VIEW);
                                    marketIntent
                                            .setData(Uri
                                                    .parse("market://details?id=com.adobe.reader"));
                                    startActivity(marketIntent);
                                }
                            });
                    builder.setNegativeButton("Batal", null);
                    builder.create().show();
                }
            }
        });
    }








}