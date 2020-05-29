package com.application.bris.brisi_pemutus.page_konsumer_purna.page_konsumer_purna.kelengkapan_dokumen;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
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

public class KelengkapanDokumenPrapurnaActivity extends AppCompatActivity {

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

    @BindView(R.id.cv_sk_penghasilan)
    CardView cv_sk_penghasilan;
    @BindView(R.id.cb_sk_penghasilan)
    CheckBox cb_sk_penghasilan;
    @BindView(R.id.tv_sk_penghasilan)
    TextView tv_sk_penghasilan;

    @BindView(R.id.cv_sk_pengangkatan)
    CardView cv_sk_pengangkatan;
    @BindView(R.id.cb_sk_pengangkatan)
    CheckBox cb_sk_pengangkatan;
    @BindView(R.id.tv_sk_pengangkatan)
    TextView tv_sk_pengangkatan;

    @BindView(R.id.cv_sk_jabatan_terakhir)
    CardView cv_sk_jabatan_terakhir;
    @BindView(R.id.cb_sk_jabatan_terakhir)
    CheckBox cb_sk_jabatan_terakhir;
    @BindView(R.id.tv_sk_jabatan_terakhir)
    TextView tv_sk_jabatan_terakhir;
    

    @BindView(R.id.cv_spk)
    CardView cv_spk;
    @BindView(R.id.cb_spk)
    CheckBox cb_spk;
    @BindView(R.id.tv_spk)
    TextView tv_spk;

    @BindView(R.id.cv_surat_keterangan_rpc)
    CardView cv_surat_keterangan_rpc;
    @BindView(R.id.cb_surat_keterangan_rpc)
    CheckBox cb_surat_keterangan_rpc;
    @BindView(R.id.tv_surat_keterangan_rpc)
    TextView tv_surat_keterangan_rpc;


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
    @BindView(R.id.bt_sk_penghasilan)
    Button bt_sk_penghasilan;

    @BindView(R.id.bt_sk_pengangkatan)
    Button bt_sk_pengangkatan;
    @BindView(R.id.bt_sk_jabatan_terakhir)
    Button bt_sk_jabatan_terakhir;
    @BindView(R.id.bt_spk)
    Button bt_spk;
    @BindView(R.id.bt_surat_keterangan_rpc)
    Button bt_surat_keterangan_rpc;

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
        setContentView(R.layout.kelengkapan_dokumen_prapurna_activity);

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

        if(superData.getAsalHalaman().equalsIgnoreCase("riwayat")){
            bt_lanjut_kelengkapan_data.setVisibility(View.GONE);
        }

        bt_lanjut_kelengkapan_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(KelengkapanDokumenPrapurnaActivity.this, CatatanActivity.class);
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
                Intent intent = new Intent(KelengkapanDokumenPrapurnaActivity.this, PutusanFrontMenuKmg.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        bt_ktp_kelengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(KelengkapanDokumenPrapurnaActivity.this, ActivityFotoKelengkapanDokumen.class);
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
//        Toast.makeText(KelengkapanDokumenKonsumerKmgActivity.this, "Id Aplikasi masih hardcode", Toast.LENGTH_SHORT).show();

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

                            if(dataKelengkapan.getFC_NPWP_PRIBADI()){
                                cb_npwp.setChecked(true);
                                bt_npwp_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_npwp.setChecked(false);
                                bt_npwp_kelengkapan.setVisibility(View.GONE);
                            }

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


                            if(dataKelengkapan.getSURAT_KETERANGAN_PEGAWAI_TETAP()){
                                cb_sk_pengangkatan.setChecked(true);
                                bt_sk_pengangkatan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_sk_pengangkatan.setChecked(false);
                                bt_sk_pengangkatan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getSK_JABATAN_TERAKHIR_PNS()){
                                cb_sk_jabatan_terakhir.setChecked(true);
                                bt_sk_jabatan_terakhir.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_sk_jabatan_terakhir.setChecked(false);
                                bt_sk_jabatan_terakhir.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getSLIP_GAJI()){
                                cb_sk_penghasilan.setChecked(true);
                                bt_sk_penghasilan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_sk_penghasilan.setChecked(false);
                                bt_sk_penghasilan.setVisibility(View.GONE);
                            }
//
//                            if(dataKelengkapan.getSuratKesehatan()){
//                                cb_spk.setChecked(true);
//                                bt_spk.setVisibility(View.VISIBLE);
//                            }
//                            else{
//                                cb_spk.setChecked(false);
//                                bt_spk.setVisibility(View.GONE);
//                            }

                            if(dataKelengkapan.getSuratNasabahRpc()){
                                cb_surat_keterangan_rpc.setChecked(true);
                                bt_surat_keterangan_rpc.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_surat_keterangan_rpc.setChecked(false);
                                bt_surat_keterangan_rpc.setVisibility(View.GONE);
                            }



                            //end kondisi checklist


                            //belum ada agunan

//                            adapterKelengkapanDokumenAgunan = new AdapterKelengkapanDokumenAgunan(KelengkapanDokumenKonsumerKmgActivity.this, dataKelengkapanAgunan);
//                            rv_agunan_kelengkapan.setLayoutManager(new LinearLayoutManager(KelengkapanDokumenKonsumerKmgActivity.this));
//                            rv_agunan_kelengkapan.setItemAnimator(new DefaultItemAnimator());
//                            rv_agunan_kelengkapan.setAdapter(adapterKelengkapanDokumenAgunan);



                            //START ONCLICK LIHAT FOTO dan pdf
                            try{
                                onClickLihatFoto(bt_ktp_kelengkapan, dataKelengkapan.getID_DOKUMEN_KTP());
                                onClickLihatFoto(bt_kartu_keluarga_kelengkapan, dataKelengkapan.getID_DOKUMEN_KK());
                                onClickLihatFoto(bt_pas_photo_kelengkapan, dataKelengkapan.getID_DOKUMEN_PAS_PHOTO());
                                onClickLihatFoto(bt_npwp_kelengkapan, dataKelengkapan.getID_DOKUMEN_NPWP_PRIBADI());
                                onClickLihatFoto(bt_formulir_kelengkapan, dataKelengkapan.getID_DOKUMEN_APLIKASI());

                                //start onclick lihat pdf
                                onClickLihatPdf(bt_surat_nikah_kelengkapan, dataKelengkapan.getID_DOKUMEN_SURAT_NIKAH());
                                onClickLihatPdf(bt_sk_pengangkatan, dataKelengkapan.getID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP());
                                onClickLihatPdf(bt_sk_jabatan_terakhir, dataKelengkapan.getID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS());
                                onClickLihatPdf(bt_sk_penghasilan, dataKelengkapan.getID_SLIP_GAJI());
//                                onClickLihatPdf(bt_spk, dataKelengkapan.getIdPhotosuratKesehatan());
                                onClickLihatPdf(bt_surat_keterangan_rpc, dataKelengkapan.getIdPhotosuratNasabahRpc());


                                //end onclick lihat foto dan pdf
                            }
                            catch (Exception e) {

                                Log.d("Exception_foto",e.toString());

                            }




                        }
                        else{
                            AppUtil.notiferror(KelengkapanDokumenPrapurnaActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
                AppUtil.notiferror(KelengkapanDokumenPrapurnaActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                finish();
            }
        });


    }

    private void onClickLihatFoto(Button butt, final int id_foto){
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(KelengkapanDokumenPrapurnaActivity.this, ActivityPreviewFotoSecondary.class);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(KelengkapanDokumenPrapurnaActivity.this);
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
