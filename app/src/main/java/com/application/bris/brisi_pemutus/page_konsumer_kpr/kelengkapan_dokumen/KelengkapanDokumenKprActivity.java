package com.application.bris.brisi_pemutus.page_konsumer_kpr.kelengkapan_dokumen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
import com.application.bris.brisi_pemutus.model.kelengkapan_dokumen.KelengkapanDokumenKprKaryawanBris;
import com.application.bris.brisi_pemutus.model.kelengkapan_dokumen.KonsumerKmgKelengkapanDokumen;
import com.application.bris.brisi_pemutus.model.kelengkapan_dokumen_agunan.KelengkapanDokumenAgunan;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_konsumer_kpr.PutusanFrontMenuKpr;
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


public class KelengkapanDokumenKprActivity extends AppCompatActivity {

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

    @BindView(R.id.cv_surat_keterangan_bekerja)
    CardView cv_surat_keterangan_bekerja;
    @BindView(R.id.cb_surat_keterangan_bekerja)
    CheckBox cb_surat_keterangan_bekerja;
    @BindView(R.id.tv_surat_keterangan_bekerja)
    TextView tv_surat_keterangan_bekerja;

    @BindView(R.id.cv_surat_rab)
    CardView cv_surat_rab;
    @BindView(R.id.cb_surat_rab)
    CheckBox cb_surat_rab;
    @BindView(R.id.tv_surat_rab)
    TextView tv_surat_rab;

    @BindView(R.id.cv_surat_rekomendasi)
    CardView cv_surat_rekomendasi;
    @BindView(R.id.cb_surat_rekomendasi)
    CheckBox cb_surat_rekomendasi;
    @BindView(R.id.tv_surat_rekomendasi)
    TextView tv_surat_rekomendasi;

    @BindView(R.id.cv_skpg)
    CardView cv_skpg;
    @BindView(R.id.cb_skpg)
    CheckBox cb_skpg;
    @BindView(R.id.tv_skpg)
    TextView tv_skpg;


    @BindView(R.id.cv_sk_penghasilan)
    CardView cv_sk_penghasilan;
    @BindView(R.id.cb_sk_penghasilan)
    CheckBox cb_sk_penghasilan;
    @BindView(R.id.tv_sk_penghasilan)
    TextView tv_sk_penghasilan;

    @BindView(R.id.cv_rek_koran)
    CardView cv_rek_koran;
    @BindView(R.id.cb_rek_koran)
    CheckBox cb_rek_koran;
    @BindView(R.id.tv_rek_koran)
    TextView tv_rek_koran;


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

    @BindView(R.id.bt_surat_keterangan_bekerja)
    Button bt_surat_keterangan_bekerja;
    @BindView(R.id.bt_surat_rab)
    Button bt_surat_rab;
    @BindView(R.id.bt_surat_rekomendasi)
    Button bt_surat_rekomendasi;
    @BindView(R.id.bt_skpg)
    Button bt_skpg;
    @BindView(R.id.bt_sk_penghasilan)
    Button bt_sk_penghasilan;
    @BindView(R.id.bt_rek_koran)
    Button bt_rek_koran;

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
    KelengkapanDokumenKprKaryawanBris dataKelengkapan;
    List<KelengkapanDokumenAgunan> dataKelengkapanAgunan;

    AppPreferences appPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelengkapan_dokumen_kpr_karyawan_bris_activity);

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
                Intent intent=new Intent(KelengkapanDokumenKprActivity.this, CatatanActivity.class);
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
                Intent intent = new Intent(KelengkapanDokumenKprActivity.this, PutusanFrontMenuKpr.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

//        bt_ktp_kelengkapan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(KelengkapanDokumenKprActivity.this, ActivityFotoKelengkapanDokumen.class);
//                startActivity(intent);
//            }
//        });

    }

    public void loadData(){
        loading.setVisibility(View.VISIBLE);
        //real data
//        ReqKelengkapanDokumen req = new ReqKelengkapanDokumen();
//        req.setId_aplikasi(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));


        //pantekan buat testing
        ReqKelengkapanDokumen req = new ReqKelengkapanDokumen();
//       req.setId_aplikasi(101928);
//        Toast.makeText(KelengkapanDokumenKprActivity.this, "Id Aplikasi masih hardcode", Toast.LENGTH_SHORT).show();

        req.setId_aplikasi(Integer.parseInt(superData.getIdAplikasi()));

        call = apiClientAdapter.getApiInterface().inquiryKelengkapanDokumenKpr(req);


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
                            Type type = new TypeToken<KelengkapanDokumenKprKaryawanBris>() {
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
                            if(dataKelengkapan.getFC_KTP()!=null&&dataKelengkapan.getFC_KTP()){
                                cb_ktp.setChecked(true);
                                bt_ktp_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_ktp.setChecked(false);
                                bt_ktp_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getFC_NPWP_PRIBADI()!=null&&dataKelengkapan.getFC_NPWP_PRIBADI()){
                                cb_npwp.setChecked(true);
                                bt_npwp_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_npwp.setChecked(false);
                                bt_npwp_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getAPLIKASI_PERMOHONAN()!=null&&dataKelengkapan.getAPLIKASI_PERMOHONAN()){
                                cb_formulir_aplikasi.setChecked(true);
                                bt_formulir_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_formulir_aplikasi.setChecked(false);
                                bt_formulir_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getFC_KK()!=null&&dataKelengkapan.getFC_KK()){
                                cb_kartu_keluarga.setChecked(true);
                                bt_kartu_keluarga_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_kartu_keluarga.setChecked(false);
                                bt_kartu_keluarga_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getFC_SURAT_NIKAH()!=null&&dataKelengkapan.getFC_SURAT_NIKAH()){
                                cb_surat_nikah.setChecked(true);
                                bt_surat_nikah_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_surat_nikah.setChecked(false);
                                bt_surat_nikah_kelengkapan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getPAS_PHOTO()!=null&&dataKelengkapan.getPAS_PHOTO()){
                                cb_pas_photo.setChecked(true);
                                bt_pas_photo_kelengkapan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_pas_photo.setChecked(false);
                                bt_pas_photo_kelengkapan.setVisibility(View.GONE);
                            }


                            if(dataKelengkapan.getSURAT_KETERANGAN_BEKERJA()!=null&&dataKelengkapan.getSURAT_KETERANGAN_BEKERJA()){
                                cb_surat_keterangan_bekerja.setChecked(true);
                                bt_surat_keterangan_bekerja.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_surat_keterangan_bekerja.setChecked(false);
                                bt_surat_keterangan_bekerja.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getSPR_SPP_RAB()!=null&&dataKelengkapan.getSPR_SPP_RAB()){
                                cb_surat_rab.setChecked(true);
                                bt_surat_rab.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_surat_rab.setChecked(false);
                                bt_surat_rab.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getSURAT_REKOMENDASI()!=null&&dataKelengkapan.getSURAT_REKOMENDASI()){
                                cb_surat_rekomendasi.setChecked(true);
                                bt_surat_rekomendasi.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_surat_rekomendasi.setChecked(false);
                                bt_surat_rekomendasi.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getSURAT_KUASA_POTONG_GAJI()!=null&&dataKelengkapan.getSURAT_KUASA_POTONG_GAJI()){
                                cb_skpg.setChecked(true);
                                bt_skpg.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_skpg.setChecked(false);
                                bt_skpg.setVisibility(View.GONE);
                            }


                            if(dataKelengkapan.getSLIP_GAJI()!=null&&dataKelengkapan.getSLIP_GAJI()){
                                cb_sk_penghasilan.setChecked(true);
                                bt_sk_penghasilan.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_sk_penghasilan.setChecked(false);
                                bt_sk_penghasilan.setVisibility(View.GONE);
                            }

                            if(dataKelengkapan.getREKENING_KORAN()!=null&&dataKelengkapan.getREKENING_KORAN()){
                                cb_rek_koran.setChecked(true);
                                bt_rek_koran.setVisibility(View.VISIBLE);
                            }
                            else{
                                cb_rek_koran.setChecked(false);
                                bt_rek_koran.setVisibility(View.GONE);
                            }





                            //end kondisi checklist


                            //belum ada agunan

//                            adapterKelengkapanDokumenAgunan = new AdapterKelengkapanDokumenAgunan(KelengkapanDokumenKprActivity.this, dataKelengkapanAgunan);
//                            rv_agunan_kelengkapan.setLayoutManager(new LinearLayoutManager(KelengkapanDokumenKprActivity.this));
//                            rv_agunan_kelengkapan.setItemAnimator(new DefaultItemAnimator());
//                            rv_agunan_kelengkapan.setAdapter(adapterKelengkapanDokumenAgunan);



                            //START ONCLICK LIHAT FOTO
                            try{
                                onClickLihatFoto(bt_ktp_kelengkapan, dataKelengkapan.getID_DOKUMEN_KTP());
                                onClickLihatFoto(bt_kartu_keluarga_kelengkapan, dataKelengkapan.getID_DOKUMEN_KK());
                                onClickLihatFoto(bt_pas_photo_kelengkapan, dataKelengkapan.getID_DOKUMEN_PAS_PHOTO());
                                onClickLihatFoto(bt_npwp_kelengkapan, dataKelengkapan.getID_FC_NPWP_PRIBADI());
                                onClickLihatFoto(bt_formulir_kelengkapan, dataKelengkapan.getID_DOKUMEN_APLIKASI());
                                onClickLihatFoto(bt_surat_rekomendasi, dataKelengkapan.getID_DOKUMEN_SURAT_REKOMENDASI());
                                onClickLihatFoto(bt_skpg, dataKelengkapan.getID_DOKUMEN_SURAT_KUASA_POTONG_GAJI());

                                //start onclick lihat pdf
                                onClickLihatPdf(bt_surat_nikah_kelengkapan, dataKelengkapan.getID_DOKUMEN_SURAT_NIKAH());
                                onClickLihatPdf(bt_surat_keterangan_bekerja, dataKelengkapan.getID_DOKUMEN_SURAT_KETERANGAN_BEKERJA());
                                onClickLihatPdf(bt_surat_rab, dataKelengkapan.getID_DOKUMEN_SPR_SPP_RAB());
                                onClickLihatPdf(bt_sk_penghasilan, dataKelengkapan.getID_DOKUMEN_SLIP_GAJI());
                                onClickLihatPdf(bt_rek_koran, dataKelengkapan.getID_DOKUMEN_REKENING_KORAN());



                                //end onclick lihat foto
                            }
                            catch (Exception e) {

                                Log.d("Exception_foto",e.toString());

                            }




                        }
                        else{
                            AppUtil.notiferror(KelengkapanDokumenKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
                AppUtil.notiferror(KelengkapanDokumenKprActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                finish();
            }
        });


    }

    private void onClickLihatFoto(Button butt, final int id_foto){
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(KelengkapanDokumenKprActivity.this, ActivityPreviewFotoSecondary.class);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(KelengkapanDokumenKprActivity.this);
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