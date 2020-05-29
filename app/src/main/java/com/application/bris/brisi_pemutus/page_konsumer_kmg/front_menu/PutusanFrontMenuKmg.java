package com.application.bris.brisi_pemutus.page_konsumer_kmg.front_menu;


import android.content.BroadcastReceiver;
import android.content.Intent;

import com.application.bris.brisi_pemutus.api.model.Error;
import com.application.bris.brisi_pemutus.api.model.ParseResponseError;
import com.application.bris.brisi_pemutus.api.model.request.id_aplikasi.ReqIdAplikasi;
import com.application.bris.brisi_pemutus.model.detailHotprospek.DetailHotprospek;
import com.application.bris.brisi_pemutus.page_konsumer_purna.page_konsumer_purna.data_finansial.DataFinansialPurnaActivity;
import com.application.bris.brisi_pemutus.page_konsumer_purna.page_konsumer_purna.data_lengkap.DataLengkapActivityPurna;
import com.application.bris.brisi_pemutus.page_konsumer_purna.page_konsumer_purna.kelengkapan_dokumen.KelengkapanDokumenPrapurnaActivity;
import com.application.bris.brisi_pemutus.page_konsumer_purna.page_konsumer_purna.kelengkapan_dokumen.KelengkapanDokumenPurnaActivity;
import com.application.bris.brisi_pemutus.page_putusan.PutusanFrontMenu;
import com.application.bris.brisi_pemutus.page_putusan.data_lengkap.ActivityDataLengkap;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.putusan_pemutus.ReqSetujuPutusan;
import com.application.bris.brisi_pemutus.api.model.request.req_kode_skk.ReqKodeSkk;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.cs_model.CsModel;
import com.application.bris.brisi_pemutus.model.hotprospek.HotProspek;
import com.application.bris.brisi_pemutus.model.info_cs_pencairan.InfoCs;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.model.putusan_akad.PutusanAkad;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.data_finansial.DataFinansialKonsumerKmgActivity;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.data_lengkap.DataLengkapKonsumerKmgActivity;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.kelengkapan_dokumen.KelengkapanDokumenKonsumerKmgActivity;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.scoring.ScoringKonsumerKmgActivity;

import com.application.bris.brisi_pemutus.page_putusan.history.HistoryActivity;
import com.application.bris.brisi_pemutus.page_putusan.history_catatan.CatatanActivity;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityFotoKelengkapanDokumen;
import com.application.bris.brisi_pemutus.page_putusan.menu.MenuDaftarPutusanActivity;
import com.application.bris.brisi_pemutus.page_putusan.prescreening.PrescreeningActivity;
import com.application.bris.brisi_pemutus.page_putusan.sektor_ekonomi.SektorEkonomiActivity;
import com.application.bris.brisi_pemutus.page_riwayat.ActivityMenuRiwayat;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import info.hoang8f.widget.FButton;
import me.grantland.widget.AutofitTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class PutusanFrontMenuKmg extends AppCompatActivity {
    CardView prescreening, datalengkap, agunan, scoring, history, sektor_ekonomi, kelengkapan_dokumen,data_finansial;
    LinearLayout bottom_sheet;
    ImageView check_prescreening, check_datalengkap, check_agunan, check_scoring,check_sektor_ekonomi,check_kelengkapan, foto,check_data_finansial;
    ImageView capsule_close;
    ExtendedEditText catatan;
    Boolean statusPutusan = false;

    AllDataFront superData;

    //textviews
    TextView nama, tenor, produk, plafon, id_aplikasi, cif_syiar, tujuan_penggunaan,no_akad;
    AutofitTextView status;
    ApiClientAdapter apiClientAdapter;
    int statusPutusanInt = 0;
    DetailHotprospek detailHotprospek;


    TextFieldBoxes catatanBox;
    BroadcastReceiver mMessageReceiver;
    FButton btPutusan, btSetuju, btTolak, btTunda, btKembalikan;
    @BindView(R.id.tb_regular)
    Toolbar tb_regular;

    @BindView(R.id.rl_no_akad_front)
    RelativeLayout rl_no_akad_front;

    @BindView(R.id.cv_nomor_akad)
    CardView cv_nomor_akad;

    @BindView(R.id.autoTv_no_akad)
    AutofitTextView autoTv_no_akad;

    @BindView(R.id.cv_nomor_akad_qardh)
    CardView cv_nomor_akad_qardh;

    @BindView(R.id.autoTv_no_akad_qardh)
    AutofitTextView autoTv_no_akad_qardh;

    @BindView(R.id.autoTv_jatuh_tempo_qardh)
    AutofitTextView autoTv_jatuh_tempo_qardh;

    @BindView(R.id.iv_foto_putusan_front)
    ImageView iv_foto_putusan_front;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    HotProspek datahotProspek;
    Putusan dataPutusan;
    PutusanAkad dataPutusanAkad;
    InfoCs dataCs;
    List<CsModel> cekCs;
    String adaCs="belum";
    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.putusan_front_menu_kmg);
        apiClientAdapter = new ApiClientAdapter(PutusanFrontMenuKmg.this);
        appPreferences=new AppPreferences(this);

        //membuat kondisi preference dalam kondisi default, tidak ada yang diceklis kecuali agunan
//        setFrontPreferenceDefault();
        setFrontPreferenceKmg();

        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Putusan Pembiayaan");

        //toolbar back configuration, hard to explain, just ask to mr eki. In short, this is needed so the activity flows as eki wants
        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent().getSerializableExtra("data_putusan_akad")!=null){
                    Intent intent=new Intent(PutusanFrontMenuKmg.this, ActivityMenuRiwayat.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                    startActivity(intent);
                }
                else{
                    SweetAlertDialog dialogWarning=new SweetAlertDialog(PutusanFrontMenuKmg.this,SweetAlertDialog.WARNING_TYPE);
                    dialogWarning.setTitle("Pembiayaan belum diputus");
                    dialogWarning.setContentText("Anda belum memutus pembiayaan ini, apakah anda yakin ingin kembali ke menu awal?");
                    dialogWarning.setCancelText("Batal");
                    dialogWarning.setConfirmText("Ya");
                    dialogWarning.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Intent intent=new Intent(PutusanFrontMenuKmg.this, MenuDaftarPutusanActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                            startActivity(intent);
                        }
                    });
                    dialogWarning.show();

                }

            }
        });

        //biar keyboard gak nongol di awal activity kalau ada edittext
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //instansiasi objeck icon check
        check_prescreening = findViewById(R.id.check_prescreening);
        check_datalengkap = findViewById(R.id.check_data_lengkap);
        check_agunan = findViewById(R.id.check_agunan);
        check_scoring = findViewById(R.id.check_scoring);
        check_sektor_ekonomi=findViewById(R.id.check_sektor_ekonomi);
        check_kelengkapan=findViewById(R.id.check_kelengkapan_data);
        check_data_finansial=findViewById(R.id.check_data_finansial);




        //onclick checklist
        check_prescreening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.notifCheck(PutusanFrontMenuKmg.this, findViewById(android.R.id.content), "Data prescreening sudah lengkap");
            }
        });
        check_datalengkap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.notifCheck(PutusanFrontMenuKmg.this, findViewById(android.R.id.content), "Data lengkap sudah terisi");
            }
        });

        check_data_finansial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.notifCheck(PutusanFrontMenuKmg.this, findViewById(android.R.id.content), "Data Finansial sudah lengkap");
            }
        });

        check_agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.notifCheck(PutusanFrontMenuKmg.this, findViewById(android.R.id.content), "Data Agunan sudah lengkap");
            }
        });

        check_scoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.notifCheck(PutusanFrontMenuKmg.this, findViewById(android.R.id.content), "Data scoring sudah lengkap");
            }
        });




        //set views dengan data dan kondisi menerima dari putusan atau dari riwayat akad
        if(getIntent().getSerializableExtra("data_putusan")!=null){
            dataPutusan = (Putusan) getIntent().getSerializableExtra("data_putusan");
//            Log.d("datasdeviasifront",dataPutusan.getFid_photo());
            initializeDataFront();
            setDataPutusan(dataPutusan);
            loadDataHotprospek(Integer.parseInt(dataPutusan.getId_aplikasi()));
            setSuperData();

            iv_foto_putusan_front.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(PutusanFrontMenuKmg.this, ActivityFotoKelengkapanDokumen.class);
                    intent.putExtra("id_foto",Integer.parseInt(dataPutusan.getFid_photo()));
                    startActivity(intent);
                }
            });
        }
        else if (getIntent().getSerializableExtra("data_putusan_akad")!=null){
            dataPutusanAkad = (PutusanAkad) getIntent().getSerializableExtra("data_putusan_akad");
            initializeDataFrontAkad();

            setDataPutusanAkad(dataPutusanAkad);
            loadDataHotprospek(Integer.parseInt(dataPutusanAkad.getId_aplikasi()));
            setSuperDataAkad();

            cv_nomor_akad.setVisibility(View.VISIBLE);
            if(dataPutusanAkad.getNO_AKAD()==null){
                cv_nomor_akad.setVisibility(View.GONE);
            }
            else{
                autoTv_no_akad.setText(dataPutusanAkad.getNO_AKAD());
            }


            if(dataPutusanAkad.getNO_AKAD_QARDH()!=null&&!dataPutusanAkad.getNO_AKAD_QARDH().isEmpty()){
                cv_nomor_akad_qardh.setVisibility(View.VISIBLE);
                autoTv_no_akad_qardh.setText(dataPutusanAkad.getNO_AKAD_QARDH());

                try{
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.getDefault());
                    String originDate = dataPutusanAkad.getJT_QARDH();
                    SimpleDateFormat formatIn = new SimpleDateFormat("ddMMyyy");
                    SimpleDateFormat formatOut = new SimpleDateFormat("dd MMM yyy");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(formatIn.parse(originDate));

                    String newDate = formatOut.format(calendar.getTime());
                    autoTv_jatuh_tempo_qardh.setText(newDate);

                }
                catch(Exception e){

                }



            }
//            else if(dataPutusanAkad.getNO_AKAD_QARDH().isEmpty()){
//                cv_nomor_akad_qardh.setVisibility(View.GONE);
//            }
            else {
                cv_nomor_akad_qardh.setVisibility(View.GONE);
            }


            btPutusan.setVisibility(View.GONE);

            iv_foto_putusan_front.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(PutusanFrontMenuKmg.this, ActivityFotoKelengkapanDokumen.class);
                    intent.putExtra("id_foto",Integer.parseInt(dataPutusanAkad.getFid_photo()));
                    startActivity(intent);
                }
            });

//            no_akad.setText(dataPutusanAkad.getNO_AKAD());
//            rl_no_akad_front.setVisibility(View.VISIBLE);

            //preview foto







        }
        else{
            Toast.makeText(this, "Belum ada data diterima", Toast.LENGTH_SHORT).show();
        }

    }



    private void setDataPutusan(final Putusan dataPutusan){

        //Bottom Sheet instantiation
        bottom_sheet = findViewById(R.id.bottom_sheet);
        final BottomSheetBehavior behaviorBottomSheet = BottomSheetBehavior.from(bottom_sheet);

        prescreening = findViewById(R.id.cv_prescreening);
        datalengkap = findViewById(R.id.cv_data_lengkap);

        //instansiasi menu icon

        agunan = findViewById(R.id.cv_agunan);
        scoring = findViewById(R.id.cv_scoring);
        history = findViewById(R.id.cv_history);
        sektor_ekonomi = findViewById(R.id.cv_sektor_ekonomi);
        data_finansial = findViewById(R.id.cv_data_finansial);
        kelengkapan_dokumen = findViewById(R.id.cv_kelengkapan_data);

//        capsule=findViewById(R.id.cv_capsule_close);
        capsule_close = findViewById(R.id.iv_capsule_close);

        catatan = findViewById(R.id.extended_catatan);
        catatanBox = findViewById(R.id.text_catatan);

        //disable catatan putusan, biar keyboard gak muncul sendiri, enable lagi ketika muncul bottom sheet putusan
        catatanBox.setEnabled(false);
        capsule_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        btPutusan = findViewById(R.id.button_putusan);




        // oN CLICK LISTENER
        datalengkap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent intent=new Intent(PutusanFrontMenuKmg.this,ActivityPutusan.class);
                Intent intent;
                if(produk.getText().toString()!=null&&produk.getText().toString().toLowerCase().contains("purna")){
                    intent=new Intent(PutusanFrontMenuKmg.this, DataLengkapActivityPurna.class);
                    Log.d("purnafaedah","masuk ke purna");
                }
                else{
                    intent = new Intent(PutusanFrontMenuKmg.this, DataLengkapKonsumerKmgActivity.class);
                    
                    //pantekan testing purna
//                    intent=new Intent(PutusanFrontMenuKmg.this, DataLengkapActivityPurna.class);
//                    Toast.makeText(PutusanFrontMenuKmg.this, "Hardcode purna, PUTUSAN FRONT MENU KMG  399", Toast.LENGTH_SHORT).show();
                }
//                 intent = new Intent(PutusanFrontMenuKmg.this, DataLengkapKonsumerKmgActivity.class);
                intent.putExtra("cif", cif_syiar.getText());
                intent.putExtra("idAplikasi", id_aplikasi.getText());
                intent.putExtra("superData", superData);
                startActivity(intent);
            }
        });

        data_finansial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                if(produk.getText().toString()!=null&&produk.getText().toString().toLowerCase().contains("purna")){
                    intent = new Intent(PutusanFrontMenuKmg.this, DataFinansialPurnaActivity.class);
//                    Log.d("purnafaedah","masuk ke purna");
                }
                else{
                    intent=new Intent(PutusanFrontMenuKmg.this, DataFinansialKonsumerKmgActivity.class);

                    //pantekan testing purna
//                    intent=new Intent(PutusanFrontMenuKmg.this, DataFinansialPurnaActivity.class);
//                    Toast.makeText(PutusanFrontMenuKmg.this, "Hardcode purna, PUTUSAN FRONT MENU KMG  425", Toast.LENGTH_SHORT).show();
                }

                //real data
                intent.putExtra("cif", cif_syiar.getText());
                intent.putExtra("idAplikasi", id_aplikasi.getText());
                intent.putExtra("tujuanPembiayaan", tujuan_penggunaan.getText());
                intent.putExtra("jw", Integer.parseInt(dataPutusan.getTenor()));
                intent.putExtra("plafond", dataPutusan.getPlafond_induk());
                intent.putExtra("superData",superData);

                //pantekan
//                intent.putExtra("cif", "81857");
//                intent.putExtra("idAplikasi", "101694");
//                intent.putExtra("tujuanPembiayaan", "Barang Modal ");
//                intent.putExtra("jw", Integer.parseInt("60"));
//                intent.putExtra("plafond", "2500000");

                startActivity(intent);
                // Toast.makeText(PutusanFrontMenuKmg.this, "Fitur ini masih dalam pengembangan", Toast.LENGTH_SHORT).show();
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PutusanFrontMenuKmg.this, HistoryActivity.class);
                intent.putExtra("cif", cif_syiar.getText());
                intent.putExtra("idAplikasi", Integer.parseInt(id_aplikasi.getText().toString()));
                intent.putExtra("superData",superData);
                startActivity(intent);
            }
        });

        agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //kmg belum ada agunan fixed asset
                Toasty.info(PutusanFrontMenuKmg.this,"Pembiayaan beragun SK").show();

//                Intent intent = new Intent(PutusanFrontMenuKmg.this, AgunanTerikatActivity.class);
//
//                //real data
//                intent.putExtra("cif", cif_syiar.getText());
//                intent.putExtra("idAplikasi", id_aplikasi.getText()).toString();
//                intent.putExtra("idAgunan", dataPutusan.getFid_agunan());
//                intent.putExtra("superData",superData);
//
//                //pantekan
////                intent.putExtra("cif", "81857");
////                intent.putExtra("idAplikasi", "101694");
////                intent.putExtra("idAgunan", "257");
//
//                startActivity(intent);
            }
        });
//        agunan.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                //nampilin google maps berdasar kordinat
//
////                String uri = String.format(Locale.ENGLISH, "geo:59,915494,30,409456");
////                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
////                startActivity(intent);
//
//                //ganti activity ke map
//                Intent intent = new Intent(PutusanFrontMenuKmg.this, MapsActivity.class);
//              //  startActivity(intent);
//                startActivityForResult(intent,1);
//
//
//                return true;
//            }
//        });

        kelengkapan_dokumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;

                //seleksi untuk kmg purna dan prapurna
                if(superData.getLoanType()!=null&&superData.getLoanType().equalsIgnoreCase("125")){
                    intent = new Intent(PutusanFrontMenuKmg.this, KelengkapanDokumenPurnaActivity.class);
                    Log.d("purnafaedah","masuk ke purna");
                }
                else if(superData.getLoanType()!=null&&superData.getLoanType().equalsIgnoreCase("129")){
                    intent = new Intent(PutusanFrontMenuKmg.this, KelengkapanDokumenPrapurnaActivity.class);
                    Log.d("purnafaedah","masuk ke prapurna");
                }

                //seleksi untuk mikro purna dan prapurna, if-nya dipisah, soalnya ngeri, takut ngegabungin and dan or dalam satu if, takut ga masuk
                else if(superData.getLoanType()!=null&&superData.getLoanType().equalsIgnoreCase("429")){
                    intent = new Intent(PutusanFrontMenuKmg.this, KelengkapanDokumenPurnaActivity.class);
                    Log.d("purnafaedah","masuk ke purna");
                }
                else if(superData.getLoanType()!=null&&superData.getLoanType().equalsIgnoreCase("430")){
                    intent = new Intent(PutusanFrontMenuKmg.this, KelengkapanDokumenPrapurnaActivity.class);
                    Log.d("purnafaedah","masuk ke prapurna");
                }
                else{
                    intent = new Intent(PutusanFrontMenuKmg.this, KelengkapanDokumenKonsumerKmgActivity.class);

                }

                //real data
                intent.putExtra("idAplikasi", id_aplikasi.getText());
                intent.putExtra("superData",superData);

                //pantekan
//                intent.putExtra("idAplikasi", "101694");

                startActivity(intent);
            }
        });
        prescreening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PutusanFrontMenuKmg.this, PrescreeningActivity.class);
                intent.putExtra("idAplikasi", id_aplikasi.getText());
                intent.putExtra("superData",superData);
                intent.putExtra("dataPutusan",dataPutusan);
                startActivity(intent);
            }
        });

        sektor_ekonomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PutusanFrontMenuKmg.this, SektorEkonomiActivity.class);

                //real data
                intent.putExtra("cif", Integer.parseInt(cif_syiar.getText().toString()));
                intent.putExtra("idAplikasi", Integer.parseInt(id_aplikasi.getText().toString()));
                intent.putExtra("id_tujuan", dataPutusan.getId_tujuan());
                intent.putExtra("tujuanPembiayaan", tujuan_penggunaan.getText().toString());
                intent.putExtra("jenisPembiayaan", "kmg");//identifier kalau ke sektor ekonomi kmg
                intent.putExtra("superData",superData);

                //pantekan
//                intent.putExtra("cif", 81857);
//                intent.putExtra("idAplikasi", 101694);
//                intent.putExtra("id_tujuan", "40");
//                intent.putExtra("tujuanPembiayaan", "Barang Modal");

                startActivity(intent);
            }
        });


        scoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PutusanFrontMenuKmg.this, ScoringKonsumerKmgActivity.class);

                //real data
                intent.putExtra("idAplikasi", Integer.parseInt(id_aplikasi.getText().toString()));
                intent.putExtra("cif", Integer.parseInt(cif_syiar.getText().toString()));
                intent.putExtra("superData",superData);

//                //pantekan
//                intent.putExtra("cif", 81857);
//                intent.putExtra("idAplikasi", 101694);
                startActivity(intent);
                // Toast.makeText(PutusanFrontMenuKmg.this, "Fitur ini masih dalam pengembangan", Toast.LENGTH_SHORT).show();
            }
        });


        btPutusan.setButtonColor(getResources().getColor(R.color.colorPrimaryDark));
        btPutusan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(superData.getJenisPembiayaan().equalsIgnoreCase("kmg")) {


                    //kalau belum dilihat semua, gak bisa di putus, kecuali agunan, karena kmg
                    if (appPreferences.getReadKelengkapan().equalsIgnoreCase("no") || appPreferences.getReadPreScreening().equalsIgnoreCase("no") || appPreferences.getReadDataLengkap().equalsIgnoreCase("no") || appPreferences.getReadSektorEkonomi().equalsIgnoreCase("no") || appPreferences.getReadDataFinansial().equalsIgnoreCase("no") || appPreferences.getReadScoring().equalsIgnoreCase("no")) {
                        Toasty.info(PutusanFrontMenuKmg.this, "Harus melihat seluruh data nasabah sebelum memutus").show();
                    } else {
                        Intent intent = new Intent(PutusanFrontMenuKmg.this, CatatanActivity.class);
                        intent.putExtra("cif", cif_syiar.getText());
                        intent.putExtra("idAplikasi", Integer.parseInt(id_aplikasi.getText().toString()));
                        intent.putExtra("fidStatus", dataPutusan.getFid_status());
                        intent.putExtra("superData", superData);
                        startActivity(intent);
                    }
                }
                else {


                    //kalau belum dilihat semua, gak bisa di putus, kecuali agunan, karena kmg
                    if (appPreferences.getReadKelengkapan().equalsIgnoreCase("no") || appPreferences.getReadPreScreening().equalsIgnoreCase("no") || appPreferences.getReadDataLengkap().equalsIgnoreCase("no") || appPreferences.getReadSektorEkonomi().equalsIgnoreCase("no") || appPreferences.getReadDataFinansial().equalsIgnoreCase("no") || appPreferences.getReadScoring().equalsIgnoreCase("no")) {
                        Toasty.info(PutusanFrontMenuKmg.this, "Harus melihat seluruh data nasabah sebelum memutus").show();
                    } else {
                        Intent intent = new Intent(PutusanFrontMenuKmg.this, CatatanActivity.class);
                        intent.putExtra("cif", cif_syiar.getText());
                        intent.putExtra("idAplikasi", Integer.parseInt(id_aplikasi.getText().toString()));
                        intent.putExtra("fidStatus", dataPutusan.getFid_status());
                        intent.putExtra("superData", superData);
                        startActivity(intent);
                    }
                }
            }
        });


        if (dataPutusan.getTenor().equalsIgnoreCase("0")) {
            tenor.setText("Belum ada data tenor");
        } else {
            int tenorInt = Integer.parseInt(dataPutusan.getTenor());
            if (tenorInt >= 13 && tenorInt % 12 > 0) {
                tenor.setText("Tenor : " + tenorInt / 12 + " tahun " + tenorInt % 12 + " bulan");
            } else if (tenorInt >= 13 && tenorInt % 12 == 0) {
                tenor.setText("Tenor " + tenorInt / 12 + " tahun");
            } else {
                tenor.setText("Tenor " + dataPutusan.getTenor() + " bulan");
            }
        }
        status.setText(dataPutusan.getStatus_aplikasi());

        //glide options for photo
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .error(R.drawable.banner_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);


        //hidupkan kembali glide jika sudah ada fid photo di listputusan
        Glide.with(PutusanFrontMenuKmg.this)
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto + dataPutusan.getFid_photo())
                .apply(options)
                .into(foto);


        ////////end of proses nerima broadcast intent

    }

    private void setDataPutusanAkad(final PutusanAkad dataPutusan){

        //Bottom Sheet instantiation
        bottom_sheet = findViewById(R.id.bottom_sheet);
        final BottomSheetBehavior behaviorBottomSheet = BottomSheetBehavior.from(bottom_sheet);


        prescreening = findViewById(R.id.cv_prescreening);
        datalengkap = findViewById(R.id.cv_data_lengkap);
        btSetuju = findViewById(R.id.bt_setuju);
        btTolak = findViewById(R.id.bt_tolak);
        btTunda = findViewById(R.id.bt_tunda);
        btKembalikan = findViewById(R.id.bt_kembalikan);
        btSetuju.setButtonColor(getResources().getColor(R.color.colorSuccessGreen));
        btSetuju.setTextColor(getResources().getColor(R.color.colorWhite));
        btTolak.setButtonColor(getResources().getColor(R.color.red));
        btTolak.setTextColor(getResources().getColor(R.color.colorWhite));
        btKembalikan.setButtonColor(getResources().getColor(R.color.main_orange_light_color));
        btKembalikan.setTextColor(getResources().getColor(R.color.colorWhite));
        btTunda.setButtonColor(getResources().getColor(R.color.main_orange_light_color));
        btTunda.setTextColor(getResources().getColor(R.color.colorWhite));


        //instansiasi menu icon
        agunan = findViewById(R.id.cv_agunan);
        scoring = findViewById(R.id.cv_scoring);
        history = findViewById(R.id.cv_history);
        sektor_ekonomi = findViewById(R.id.cv_sektor_ekonomi);
        kelengkapan_dokumen = findViewById(R.id.cv_kelengkapan_data);
        data_finansial=findViewById(R.id.cv_data_finansial);

//        capsule=findViewById(R.id.cv_capsule_close);
        capsule_close = findViewById(R.id.iv_capsule_close);

        catatan = findViewById(R.id.extended_catatan);
        catatanBox = findViewById(R.id.text_catatan);
        capsule_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        btPutusan = findViewById(R.id.button_putusan);




        // oN CLICK LISTENER
        datalengkap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent intent=new Intent(PutusanFrontMenuKmg.this,ActivityPutusan.class);
                Intent intent;
                if(produk.getText().toString()!=null&&produk.getText().toString().toLowerCase().contains("purna")){
                    intent=new Intent(PutusanFrontMenuKmg.this, DataLengkapActivityPurna.class);
                    Log.d("purnafaedah","masuk ke purna");
                }
                else{
                    intent = new Intent(PutusanFrontMenuKmg.this, DataLengkapKonsumerKmgActivity.class);

                    //pantekan testing purna
//                    intent=new Intent(PutusanFrontMenuKmg.this, DataLengkapActivityPurna.class);
//                    Toast.makeText(PutusanFrontMenuKmg.this, "Hardcode purna, PUTUSAN FRONT MENU KMG  399", Toast.LENGTH_SHORT).show();
                }
//                 intent = new Intent(PutusanFrontMenuKmg.this, DataLengkapKonsumerKmgActivity.class);
                intent.putExtra("cif", cif_syiar.getText());
                intent.putExtra("idAplikasi", id_aplikasi.getText());
                intent.putExtra("superData", superData);
                startActivity(intent);
            }
        });

        data_finansial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                if(produk.getText().toString()!=null&&produk.getText().toString().toLowerCase().contains("purna")){
                    intent = new Intent(PutusanFrontMenuKmg.this, DataFinansialPurnaActivity.class);
//                    Log.d("purnafaedah","masuk ke purna");
                }
                else{
                    intent=new Intent(PutusanFrontMenuKmg.this, DataFinansialKonsumerKmgActivity.class);

                    //pantekan testing purna
//                    intent=new Intent(PutusanFrontMenuKmg.this, DataFinansialPurnaActivity.class);
//                    Toast.makeText(PutusanFrontMenuKmg.this, "Hardcode purna, PUTUSAN FRONT MENU KMG  425", Toast.LENGTH_SHORT).show();
                }

                //real data
                intent.putExtra("cif", cif_syiar.getText());
                intent.putExtra("idAplikasi", id_aplikasi.getText());
                intent.putExtra("tujuanPembiayaan", tujuan_penggunaan.getText());
                intent.putExtra("jw", Integer.parseInt(dataPutusan.getJangka_waktu()));
                intent.putExtra("plafond", dataPutusan.getPlafond_induk());
                intent.putExtra("superData",superData);
                //pantekan
//                intent.putExtra("cif", "81857");
//                intent.putExtra("idAplikasi", "101694");
//                intent.putExtra("tujuanPembiayaan", "Barang Modal ");
//                intent.putExtra("jw", Integer.parseInt("60"));
//                intent.putExtra("plafond", "2500000");

                startActivity(intent);
                // Toast.makeText(PutusanFrontMenuKmg.this, "Fitur ini masih dalam pengembangan", Toast.LENGTH_SHORT).show();
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PutusanFrontMenuKmg.this, HistoryActivity.class);
                intent.putExtra("cif", cif_syiar.getText());
                intent.putExtra("idAplikasi", Integer.parseInt(id_aplikasi.getText().toString()));
                intent.putExtra("superData",superData);
                startActivity(intent);
            }
        });

        agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.info(PutusanFrontMenuKmg.this,"Pembiayaan beragun SK").show();
//                Intent intent = new Intent(PutusanFrontMenuKmg.this, AgunanTerikatActivity.class);
//                intent.putExtra("cif", cif_syiar.getText());
//                intent.putExtra("idAplikasi", id_aplikasi.getText()).toString();
//                intent.putExtra("idAgunan", dataPutusan.getFid_agunan());
//                intent.putExtra("menuAsal", "riwayat");
//                intent.putExtra("superData",superData);
//                startActivity(intent);
            }
        });


        kelengkapan_dokumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;


                //seleksi untuk kmg purna dan prapurna
                if(superData.getLoanType()!=null&&superData.getLoanType().equalsIgnoreCase("125")){
                    intent = new Intent(PutusanFrontMenuKmg.this, KelengkapanDokumenPurnaActivity.class);
                    Log.d("purnafaedah","masuk ke purna");
                }
                else if(superData.getLoanType()!=null&&superData.getLoanType().equalsIgnoreCase("129")){
                    intent = new Intent(PutusanFrontMenuKmg.this, KelengkapanDokumenPrapurnaActivity.class);
                    Log.d("purnafaedah","masuk ke prapurna");
                }

                //seleksi untuk mikro purna dan prapurna, if-nya dipisah, soalnya ngeri, takut ngegabungin and dan or dalam satu if, takut ga masuk
                else if(superData.getLoanType()!=null&&superData.getLoanType().equalsIgnoreCase("429")){
                    intent = new Intent(PutusanFrontMenuKmg.this, KelengkapanDokumenPurnaActivity.class);
                    Log.d("purnafaedah","masuk ke purna");
                }
                else if(superData.getLoanType()!=null&&superData.getLoanType().equalsIgnoreCase("430")){
                    intent = new Intent(PutusanFrontMenuKmg.this, KelengkapanDokumenPrapurnaActivity.class);
                    Log.d("purnafaedah","masuk ke prapurna");
                }
                else{
                    intent = new Intent(PutusanFrontMenuKmg.this, KelengkapanDokumenKonsumerKmgActivity.class);

                }

                //real data
                intent.putExtra("idAplikasi", id_aplikasi.getText());
                intent.putExtra("superData",superData);

                //pantekan
//                intent.putExtra("idAplikasi", "101694");

                startActivity(intent);
            }
        });
        prescreening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PutusanFrontMenuKmg.this, PrescreeningActivity.class);

                intent.putExtra("idAplikasi", id_aplikasi.getText());

                intent.putExtra("superData",superData);
                startActivity(intent);
            }
        });

        sektor_ekonomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PutusanFrontMenuKmg.this, SektorEkonomiActivity.class);
                intent.putExtra("cif", Integer.parseInt(cif_syiar.getText().toString()));
                intent.putExtra("idAplikasi", Integer.parseInt(id_aplikasi.getText().toString()));
                intent.putExtra("id_tujuan", dataPutusan.getId_tujuan());
                intent.putExtra("tujuanPembiayaan", tujuan_penggunaan.getText().toString());
                intent.putExtra("jenisPembiayaan", "kmg");//identifier kalau ke sektor ekonomi kmg
                intent.putExtra("superData",superData);

                startActivity(intent);
            }
        });

        scoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PutusanFrontMenuKmg.this, ScoringKonsumerKmgActivity.class);
                intent.putExtra("idAplikasi", Integer.parseInt(id_aplikasi.getText().toString()));
                intent.putExtra("cif", Integer.parseInt(cif_syiar.getText().toString()));
                intent.putExtra("superData",superData);
                startActivity(intent);
                // Toast.makeText(PutusanFrontMenuKmg.this, "Fitur ini masih dalam pengembangan", Toast.LENGTH_SHORT).show();
            }
        });




        nama.setText(dataPutusan.getNama_nasabah());
        produk.setText(dataPutusan.getNAMA_PRODUK());
        plafon.setText(AppUtil.parseRupiah(dataPutusan.getPlafond_induk()));
        cif_syiar.setText(dataPutusan.getCif_appel());
        id_aplikasi.setText(dataPutusan.getId_aplikasi());
        tujuan_penggunaan.setText(dataPutusan.getTujuan_penggunaan());


        if (dataPutusan.getJangka_waktu().equalsIgnoreCase("0")) {
            tenor.setText("Belum ada data tenor");
        } else {
            int tenorInt = Integer.parseInt(dataPutusan.getJangka_waktu());
            if (tenorInt >= 13 && tenorInt % 12 > 0) {
                tenor.setText("Tenor : " + tenorInt / 12 + " tahun " + tenorInt % 12 + " bulan");
            } else if (tenorInt >= 13 && tenorInt % 12 == 0) {
                tenor.setText("Tenor " + tenorInt / 12 + " tahun");
            } else {
                tenor.setText("Tenor " + dataPutusan.getJangka_waktu() + " bulan");
            }
        }
        status.setText(dataPutusan.getStatus_aplikasi());

        //glide options for photo
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .error(R.drawable.banner_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);


        //hidupkan kembali glide jika sudah ada fid photo di listputusan
        Glide.with(PutusanFrontMenuKmg.this)
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto + dataPutusan.getFid_photo())
                .apply(options)
                .into(foto);


    }

    private void cekAdaCs(final SweetAlertDialog dialog1){

        dialog1.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        dialog1.show();

        AppPreferences appPreferences = new AppPreferences(PutusanFrontMenuKmg.this);
        ReqKodeSkk req = new ReqKodeSkk();
        req.setKodeSkk(appPreferences.getKodeSkk());



        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().cekAdaCs(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();

                        Type type = new TypeToken<List<CsModel>>() {
                        }.getType();
                        cekCs= gson.fromJson(listDataString, type);

                        if(cekCs.size()<=0){
                            adaCs="belum";
                            dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog1.setTitleText("Gagal");
                            dialog1.setContentText("Kantor cabang anda belum memiliki user CS, harap lakukan pembuatan user CS dahulu");
                            dialog1.setConfirmText("OK");
                            dialog1.showCancelButton(false);
                            dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog1.dismissWithAnimation();
                                }
                            });
                            dialog1.show();
                        }
                        else{
                            setujuPembiayaan(dialog1);
                        }




                    } else {
                        dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        dialog1.setTitle("Terjadi Kesalahan");
                        dialog1.setContentText(response.body().getMessage());
                        dialog1.setConfirmText("Coba lagi");
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                dialog1.setTitle("Gagal");
                dialog1.setContentText("Gagal terhubung ke server");
                dialog1.setConfirmText("Ok");

            }
        });
    }

    private void setujuPembiayaan(final SweetAlertDialog dialog1){
        dialog1.changeAlertType(SweetAlertDialog.NORMAL_TYPE);
        dialog1.setTitleText("Konfirmasi")
                .setContentText("Anda yakin akan menyetujui pembiayaan?")
                .setConfirmText("Ya")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {

                        dialog1.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                        dialog1.setTitleText("Memproses");
                        dialog1.setContentText("");

                        if (statusPutusanInt >= 1) {
                            dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog1.setTitleText("GAGAL");
                            dialog1.setContentText("Pembiayaan sudah diputus");
                        } else if (statusPutusanInt <= 0) {
                            AppPreferences appPreferences = new AppPreferences(PutusanFrontMenuKmg.this);
                            ReqSetujuPutusan req = new ReqSetujuPutusan();
                            req.setRole_id(appPreferences.getFidRole());
                            req.setSt_aplikasid(dataPutusan.getFid_status());
                            req.setFid_aplikasi(id_aplikasi.getText().toString());
                            req.setId_pemutus(appPreferences.getUid());
                            req.setCatatan_pemutus(catatan.getText().toString());
                            req.setKode_dsn(appPreferences.getDsnCode());


                            Call<ParseResponse> call = apiClientAdapter.getApiInterface().pemutusSetuju(req);
                            call.enqueue(new Callback<ParseResponse>() {
                                @Override
                                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                                    if (response.isSuccessful()) {

                                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                                            String listDataString = response.body().getData().toString();
                                            Gson gson = new Gson();
                                            Type type = new TypeToken<InfoCs>() {
                                            }.getType();
                                            dataCs = gson.fromJson(listDataString, type);


                                            statusPutusan = true;
                                            dialog1.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                            dialog1.setTitleText("Putusan Berhasil");
                                            if (dataCs.getNama_petugas().equalsIgnoreCase("[GAGAL]")) {

                                                dialog1.setTitleText("Putusan Gagal");
                                                dialog1.setContentText("Gagal menerima user CS");
                                                dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                        finish();
                                                    }
                                                });
                                            } else if (dataCs.getNama_petugas().length() > 0) {
                                                statusPutusanInt++;
                                                dialog1.setContentText("Dilanjutkan ke CS : " + dataCs.getNama_petugas());
                                                dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                        finish();
                                                    }
                                                });
                                            } else {
                                                statusPutusanInt++;
                                                dialog1.setContentText("Pembiayaan berhasil disetujui");

                                            }

                                            dialog1.setConfirmText("OK");
                                            dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                    dialog1.dismissWithAnimation();
                                                    finish();
                                                }
                                            });
                                            dialog1.showCancelButton(false);


                                        } else {
                                            dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                            dialog1.setTitle("Terjadi Kesalahan");
                                            dialog1.setContentText(response.body().getMessage());
                                            dialog1.setConfirmText("Coba lagi");
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<ParseResponse> call, Throwable t) {
                                    Log.d("LOG D", t.getMessage());
                                    dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                    dialog1.setTitle("Gagal");
                                    dialog1.setContentText("Gagal terhubung ke server");
                                    dialog1.setConfirmText("Ok");
                                }
                            });

                        }


                    }
                }).setCancelText("Batal")
                .show();

    }

    private void setSuperData(){
        superData=new AllDataFront();
        superData.setIdAplikasi(id_aplikasi.getText().toString());
        superData.setCif(cif_syiar.getText().toString());
        superData.setFidStatus(dataPutusan.getFid_status());
        superData.setTujuanPembiayaan(tujuan_penggunaan.getText().toString());
        superData.setJw(dataPutusan.getTenor());
        superData.setPlafond(dataPutusan.getPlafond_induk());
        superData.setIdAgunan(dataPutusan.getFid_agunan());
        superData.setIdTujuan(dataPutusan.getId_tujuan());
        superData.setNamaNasabah(nama.getText().toString());
        if(dataPutusan.getNama_produk()!=null){
            superData.setNamaProduk(dataPutusan.getNama_produk());
        }
        if(dataPutusan.getNAMA_GIMMICK()!=null){
            superData.setNamaGimmick(dataPutusan.getNAMA_GIMMICK());
        }
        if(dataPutusan.getKODE_PRODUK()!=null){
            superData.setKodeProduk(dataPutusan.getKODE_PRODUK());
        }
        if(getIntent().getStringExtra("jenisPembiayaan")!=null){
            Log.d("sureki","intent berhasil didapatkan");
            superData.setJenisPembiayaan(getIntent().getStringExtra("jenisPembiayaan"));
        }
        else{
            superData.setJenisPembiayaan("mikro");
        }

        superData.setAsalHalaman("putusan");

    }

    private void setSuperDataAkad(){
        superData=new AllDataFront();
        superData.setIdAplikasi(id_aplikasi.getText().toString());
        superData.setCif(cif_syiar.getText().toString());
        superData.setFidStatus(dataPutusanAkad.getFid_status());
        superData.setTujuanPembiayaan(tujuan_penggunaan.getText().toString());
        superData.setJw(dataPutusanAkad.getJangka_waktu());
        superData.setPlafond(dataPutusanAkad.getPlafond_induk());
        superData.setIdAgunan(dataPutusanAkad.getFid_agunan());
        superData.setIdTujuan(dataPutusanAkad.getId_tujuan());
        superData.setNamaNasabah(nama.getText().toString());
        if(dataPutusanAkad.getNAMA_PRODUK()!=null){
            superData.setNamaProduk(dataPutusanAkad.getNAMA_PRODUK());
        }

        if(dataPutusanAkad.getKODE_PRODUK()!=null){
            superData.setKodeProduk(dataPutusanAkad.getKODE_PRODUK());
        }
        if(getIntent().getStringExtra("jenisPembiayaan")!=null){
            Log.d("sureki","intent berhasil didapatkan");
            superData.setJenisPembiayaan(getIntent().getStringExtra("jenisPembiayaan"));
        }
        else{
            superData.setJenisPembiayaan("mikro");
        }

        superData.setAsalHalaman("riwayat");
    }

    private void initializeDataFront(){
        nama = findViewById(R.id.tv_nama);
        produk = findViewById(R.id.tv_produk);
        tenor = findViewById(R.id.tv_tenor);
        plafon = findViewById(R.id.tv_plafon);
        status = findViewById(R.id.autoTv_status);
        id_aplikasi = findViewById(R.id.tv_id_aplikasi);
        cif_syiar = findViewById(R.id.tv_cif_syiar);
        tujuan_penggunaan = findViewById(R.id.tv_tujuan_penggunaan);
        foto = findViewById(R.id.iv_foto_putusan_front);
        no_akad=findViewById(R.id.tv_no_akad_front_menu);

        nama.setText(dataPutusan.getNama_nasabah());

        //coba ambil nama produk bunggg, di prosedurnya tampilin nama produk
        produk.setText(dataPutusan.getNama_produk());
        plafon.setText(AppUtil.parseRupiah(dataPutusan.getPlafond_induk()));
        cif_syiar.setText(dataPutusan.getCif_appel());
        id_aplikasi.setText(dataPutusan.getId_aplikasi());
        tujuan_penggunaan.setText(dataPutusan.getTujuan_penggunaan());
    }

    private void initializeDataFrontAkad(){
        //instansiasi textview & foto
        nama = findViewById(R.id.tv_nama);
        produk = findViewById(R.id.tv_produk);
        tenor = findViewById(R.id.tv_tenor);
        plafon = findViewById(R.id.tv_plafon);
        status = findViewById(R.id.autoTv_status);
        id_aplikasi = findViewById(R.id.tv_id_aplikasi);
        cif_syiar = findViewById(R.id.tv_cif_syiar);
        tujuan_penggunaan = findViewById(R.id.tv_tujuan_penggunaan);
        foto = findViewById(R.id.iv_foto_putusan_front);
        no_akad=findViewById(R.id.tv_no_akad_front_menu);

        nama.setText(dataPutusanAkad.getNama_nasabah());
        produk.setText(dataPutusanAkad.getNAMA_PRODUK());
        plafon.setText(AppUtil.parseRupiah(dataPutusanAkad.getPlafond_induk()));
        cif_syiar.setText(dataPutusanAkad.getCif_appel());
        id_aplikasi.setText(dataPutusanAkad.getId_aplikasi());
        tujuan_penggunaan.setText(dataPutusanAkad.getTujuan_penggunaan());
    }

    private void  setFrontPreferenceDefault(){
        appPreferences.setReadPreScreening("no");
        appPreferences.setReadDataLengkap("no");
        appPreferences.setReadSektorEkonomi("no");
        appPreferences.setReadDataFinansial("no");
        appPreferences.setReadAgunan("no");
        appPreferences.setReadScoring("no");
        appPreferences.setReadKelengkapan("no");
        appPreferences.setIdFotoFormulir("0");

    }

    private void  setFrontPreferenceKmg(){
        appPreferences.setReadPreScreening("no");
        appPreferences.setReadDataLengkap("no");
        appPreferences.setReadSektorEkonomi("no");
        appPreferences.setReadDataFinansial("no");
        appPreferences.setReadAgunan("yes");
        appPreferences.setReadScoring("no");
        appPreferences.setReadKelengkapan("no");
        appPreferences.setIdFotoFormulir("0");

    }

    @Override
    protected void onResume() {
        super.onResume();

        //memberi ceklis di modul yang sudah dilihat user, berdasarkan preference (hanya di halaman putusan. tidak di halaman riwayat
        if(!superData.getAsalHalaman().equalsIgnoreCase("riwayat")){
            if(appPreferences.getReadPreScreening().equalsIgnoreCase("yes")){
                check_prescreening.setVisibility(View.VISIBLE);
            }

            if(appPreferences.getReadDataLengkap().equalsIgnoreCase("yes")){
                check_datalengkap.setVisibility(View.VISIBLE);
            }

            if(appPreferences.getReadSektorEkonomi().equalsIgnoreCase("yes")){
                check_sektor_ekonomi.setVisibility(View.VISIBLE);
            }
            if(appPreferences.getReadDataFinansial().equalsIgnoreCase("yes")){
                check_data_finansial.setVisibility(View.VISIBLE);
            }
            if(appPreferences.getReadAgunan().equalsIgnoreCase("yes")){
                check_agunan.setVisibility(View.VISIBLE);
            }
            if(appPreferences.getReadScoring().equalsIgnoreCase("yes")){
                check_scoring.setVisibility(View.VISIBLE);
            }
            if(appPreferences.getReadKelengkapan().equalsIgnoreCase("yes")){
                check_kelengkapan.setVisibility(View.VISIBLE);
            }
        }


    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if(getIntent().getSerializableExtra("data_putusan_akad")!=null){

            Intent intent=new Intent(PutusanFrontMenuKmg.this, ActivityMenuRiwayat.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
            startActivity(intent);
        }
        else{
            SweetAlertDialog dialogWarning=new SweetAlertDialog(PutusanFrontMenuKmg.this,SweetAlertDialog.WARNING_TYPE);
            dialogWarning.setTitle("Pembiayaan belum diputus");
            dialogWarning.setContentText("Anda belum memutus pembiayaan ini, apakah anda yakin ingin kembali ke menu awal?");
            dialogWarning.setCancelText("Batal");
            dialogWarning.setConfirmText("Ya");
            dialogWarning.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Intent intent=new Intent(PutusanFrontMenuKmg.this, MenuDaftarPutusanActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                    startActivity(intent);
                }
            });
            dialogWarning.show();

        }
    }

    private void loadDataHotprospek(int idAplikasi) {
        loading.setVisibility(View.VISIBLE);
        ReqIdAplikasi req = new ReqIdAplikasi();
        req.setIdAplikasi(idAplikasi);

        Call<ParseResponse> call ;

        //seleksi apakah dia data akad atau data pembiayaan, kalau dataPutusan null, berarti data putusan akad gak null
        if(dataPutusan!=null){

            //seleksi apakah dia multifaedah mikro atau kmg biasa
            if(dataPutusan.getKODE_PRODUK()!=null&&!dataPutusan.getKODE_PRODUK().isEmpty()&&dataPutusan.getKODE_PRODUK().equalsIgnoreCase("428")){
                call = apiClientAdapter.getApiInterface().detailHotprospekMikro(req);
            }
            else{
                call = apiClientAdapter.getApiInterface().detailHotprospek(req);
            }
        }
        else{
            //seleksi apakah dia multifaedah mikro atau kmg biasa
            if(dataPutusanAkad.getKODE_PRODUK()!=null&&!dataPutusanAkad.getKODE_PRODUK().isEmpty()&&dataPutusanAkad.getKODE_PRODUK().equalsIgnoreCase("428")){
                call = apiClientAdapter.getApiInterface().detailHotprospekMikro(req);
            }
            else{
                call = apiClientAdapter.getApiInterface().detailHotprospek(req);
            }
        }

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00"))
                        {
                            Gson gson = new Gson();
                            String dataString = response.body().getData().get("aplikasi").toString();
                            detailHotprospek = gson.fromJson(dataString, DetailHotprospek.class);

                            if(detailHotprospek.getPROGRAM_NAME()!=null&&detailHotprospek.getPROGRAM_NAME().toLowerCase().contains("purna")){                                   produk.setText(detailHotprospek.getPROGRAM_NAME());
                            }
                            else{
//                                produk.setText(detailHotprospek.getNama_produk());
                                produk.setText(detailHotprospek.getPROGRAM_NAME());
                            }

                            //set super data khusus gimmick dan loan type, kalau ditaruh di setsuperdata, akan terjadi error karena asynchronous
                            if(detailHotprospek!=null&&detailHotprospek.getGIMMICK_ID()!=null){
                                superData.setKodeGimmick(detailHotprospek.getGIMMICK_ID());
                            }
                            if(detailHotprospek!=null&&detailHotprospek.getLOAN_TYPE()!=null){
                                superData.setLoanType(detailHotprospek.getLOAN_TYPE());
                            }


                        }
                        else {

                            AppUtil.notiferror(PutusanFrontMenuKmg.this, findViewById(android.R.id.content), response.body().getMessage());
//                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    finish();
//                                }
//                            }, 2000);
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(PutusanFrontMenuKmg.this, findViewById(android.R.id.content), error.getMessage());
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                finish();
//                            }
//                        }, 2000);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            finish();
//                        }
//                    }, 2000);
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(PutusanFrontMenuKmg.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        finish();
//                    }
//                }, 2000);
            }
        });
    }
}