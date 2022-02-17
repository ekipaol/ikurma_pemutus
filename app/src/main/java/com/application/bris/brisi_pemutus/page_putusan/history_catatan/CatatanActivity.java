package com.application.bris.brisi_pemutus.page_putusan.history_catatan;

import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.history_putusan.ReqHistoryPutusan;
import com.application.bris.brisi_pemutus.api.model.request.putusan_pemutus.ReqSetujuPutusan;
import com.application.bris.brisi_pemutus.api.model.request.req_kode_skk.ReqKodeSkk;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.baseapp.RouteApp;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.cs_model.CsModel;
import com.application.bris.brisi_pemutus.model.history_catatan.HistoryCatatan;
import com.application.bris.brisi_pemutus.model.info_cs_pencairan.InfoCs;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.front_menu.PutusanFrontMenuKmg;
import com.application.bris.brisi_pemutus.page_putusan.PutusanFrontMenu;
import com.application.bris.brisi_pemutus.page_putusan.adapters.HistoryCatatanAdapter;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityPreviewFotoSecondary;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.view.corelayout.CoreLayoutActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class CatatanActivity extends AppCompatActivity {

    @BindView(R.id.bottom_sheet)
    LinearLayout bottom_sheet;
    @BindView(R.id.button_putusan)
    Button button_putusan;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.tb_regular)
    Toolbar tb_regular;

    //objek bottom sheets
    @BindView(R.id.text_catatan)
    TextFieldBoxes text_catatan;
    @BindView(R.id.extended_catatan)
    ExtendedEditText extended_catatan;
    @BindView(R.id.iv_capsule_close)
    ImageView iv_capsule_close;


    @BindView(R.id.bt_setuju)
    FButton bt_setuju;
    @BindView(R.id.bt_tolak)
    FButton bt_tolak;
    @BindView(R.id.bt_kembalikan)
    FButton bt_kembalikan;
    @BindView(R.id.bt_tunda)
    FButton bt_tunda;

    @BindView(R.id.cv_data_pembiayaan_catatan)
    CardView cv_data_pembiayaan_catatan;
    @BindView(R.id.tv_nama_nasabah_catatan_putusan)
    TextView tv_nama_nasabah_catatan_putusan;
    @BindView(R.id.tv_tujuan_penggunaan_catatan_putusan)
    TextView tv_tujuan_penggunaan_catatan_putusan;
    @BindView(R.id.tv_produk_catatan_putusan)
    TextView tv_produk_catatan_putusan;
    @BindView(R.id.tv_tenor_catatan_putusan)
    TextView tv_tenor_catatan_putusan;
    @BindView(R.id.tv_plafon_catatan_putusan)
    TextView tv_plafon_catatan_putusan;

    @BindView(R.id.bt_sembunyi_info)
    Button bt_sembunyi_info;
    @BindView(R.id.bt_tampil_info)
    Button bt_tampil_info;

    @BindView(R.id.ll_foto_formulir_catatan)
    LinearLayout ll_foto_formulir_catatan;

    @BindView(R.id.ll_content_info)
    LinearLayout ll_content_info;



    BottomSheetBehavior bottomSheetBehavior;

    //variable putusan related
    int statusPutusanInt = 0;
    String fidStatus;


    int hideButtonClickIndicator=0;






    //variable to pass and send and connection related
    ApiClientAdapter apiClientAdapter;
    @BindView(R.id.rv_historycatatan)
    RecyclerView rv_historyfasilitas;
    private List<HistoryCatatan> listDataCatatan;
    private String cif,dataCatatan;
    private int idAplikasi;
    InfoCs dataCs;
    List<CsModel> cekCs;
    String adaCs="belum";
    String idProgram="";
    AppPreferences appPreferences;
    AllDataFront superData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_fragment_historycatatan);
        ButterKnife.bind(this);
        appPreferences=new AppPreferences(this);

        //connection related instanziation
        apiClientAdapter=new ApiClientAdapter(this);
//        Toast.makeText(this, "masih shoot vimen", Toast.LENGTH_SHORT).show();
        cif = getIntent().getStringExtra("cif");
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        fidStatus=getIntent().getStringExtra("fidStatus");

        if(getIntent().hasExtra("idProgram")){
            idProgram=getIntent().getStringExtra("idProgram");
        }

        superData=(AllDataFront)getIntent().getSerializableExtra("superData");

        if(superData.getKodeGimmick()!=null&&superData.getKodeGimmick().equalsIgnoreCase("222")){
            idProgram="222";
        }

        //end of connection related

        AppUtil.toolbarRegular(this, "Lakukan Putusan");
        ImageView backToolbar = findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(superData.getJenisPembiayaan()!=null&&superData.getJenisPembiayaan().equalsIgnoreCase("kmg")){
                    Intent intent = new Intent(CatatanActivity.this, PutusanFrontMenuKmg.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(CatatanActivity.this, PutusanFrontMenu.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }
        });

        bottomSheetBehavior=BottomSheetBehavior.from(bottom_sheet);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bt_setuju.setButtonColor(getResources().getColor(R.color.colorSuccessGreen));
        bt_setuju.setTextColor(getResources().getColor(R.color.colorWhite));
        bt_tolak.setButtonColor(getResources().getColor(R.color.red));
        bt_tolak.setTextColor(getResources().getColor(R.color.colorWhite));
        bt_kembalikan.setButtonColor(getResources().getColor(R.color.main_orange_light_color));
        bt_kembalikan.setTextColor(getResources().getColor(R.color.colorWhite));
        bt_tunda.setButtonColor(getResources().getColor(R.color.main_orange_light_color));
        bt_tunda.setTextColor(getResources().getColor(R.color.colorWhite));
//        extended_catatan.setFocusable(false);
//        extended_catatan.setInputType(InputType.TYPE_NULL);


        //biar keyboard gak nongol di awal activity kalau ada edittext
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //set info data
        tv_nama_nasabah_catatan_putusan.setText(superData.getNamaNasabah());
        tv_tujuan_penggunaan_catatan_putusan.setText(superData.getTujuanPembiayaan());
        tv_plafon_catatan_putusan.setText(AppUtil.parseRupiah(superData.getPlafond()));

        //tenor bulan to tahun conversion
        if (superData.getJw().equalsIgnoreCase("0")) {
            tv_tenor_catatan_putusan.setText("Belum ada data tenor");
        } else {
            int tenorInt = Integer.parseInt(superData.getJw());
            if (tenorInt >= 13 && tenorInt % 12 > 0) {
                tv_tenor_catatan_putusan.setText(tenorInt / 12 + " tahun " + tenorInt % 12 + " bulan");
            } else if (tenorInt >= 13 && tenorInt % 12 == 0) {
                tv_tenor_catatan_putusan.setText(tenorInt / 12 + " tahun");
            } else {
                tv_tenor_catatan_putusan.setText(superData.getJw() + " bulan");
            }
        }

        tv_produk_catatan_putusan.setText(superData.getNamaProduk());

        //button hide info pembiayaan

        bt_sembunyi_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("buttonindicator",Integer.toString(hideButtonClickIndicator));
                if(hideButtonClickIndicator==0){

                    //animation still fails, need more testing

//                    Animation slideUp = AnimationUtils.loadAnimation(CatatanActivity.this, R.anim.slide_up);
//                    cv_data_pembiayaan_catatan.startAnimation(slideUp);
                    ll_content_info.animate().translationY(0).setDuration(500);
                    ll_content_info.setVisibility(View.GONE);

//                    cv_data_pembiayaan_catatan.setVisibility(View.GONE);
                  bt_sembunyi_info.setVisibility(View.GONE);
                  bt_tampil_info.setVisibility(View.VISIBLE);
                    hideButtonClickIndicator++;

                }

            }
        });

        bt_tampil_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if(hideButtonClickIndicator==1){
                    cv_data_pembiayaan_catatan.setVisibility(View.VISIBLE);
//                     ll_content_info.animate().translationY(ll_content_info.getHeight()).setDuration(500);
                     ll_content_info.setVisibility(View.VISIBLE);
                    bt_tampil_info.setVisibility(View.GONE);
                    bt_sembunyi_info.setVisibility(View.VISIBLE);
                    hideButtonClickIndicator--;
                }
            }
        });


        //click listener untuk melihat foto preview formulir aplikasi
        ll_foto_formulir_catatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CatatanActivity.this, ActivityPreviewFotoSecondary.class);
                intent.putExtra("id_foto",Integer.parseInt(appPreferences.getIdFotoFormulir()));

                //dicomment karena ketimpah toast di halaman foto
//                Toasty.info(CatatanActivity.this,"Pastikan data aplikasi sudah sesuai formulir").show();
                startActivity(intent);
            }
        });



        //end of formulir foto aplikasi





//        extended_catatan.setFocusable(false);

        initializeCatatan();
        setDataPutusan();



    }

    private void initializeCatatan(){
        loading.setVisibility(View.VISIBLE);

        //real data
        ReqHistoryPutusan req = new ReqHistoryPutusan(AppUtil.parseIntWithDefault(cif, 0), idAplikasi);



        //pantekan buat testing
//        ReqHistoryPutusan req=new ReqHistoryPutusan();
//                req.setCif(81857);
//        req.setId_aplikasi(101678);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryHistory(req);

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            dataCatatan = response.body().getData().get("historyCatatanPemutus").toString();

                            //DATA HISTORY FASILITAS
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<HistoryCatatan>>() {}.getType();
                            listDataCatatan = gson.fromJson(dataCatatan, type);
                            HistoryCatatanAdapter adp = new HistoryCatatanAdapter(CatatanActivity.this, listDataCatatan);
                            rv_historyfasilitas.setLayoutManager(new LinearLayoutManager(CatatanActivity.this));
                            rv_historyfasilitas.setItemAnimator(new DefaultItemAnimator());
                            rv_historyfasilitas.setAdapter(adp);
                            //TUTORIAL OVERLAY

//                            AppUtil.tutorialOverlay(CatatanActivity.this,cv_data_pembiayaan_catatan,"Informasi pembiayaan nasabah dapat dilihat sebelum putusan","tutorial_catatan_putusan_v2");

                            //END OF TUTORIAL OVERLAY
                        }
                        else{
                            AppUtil.notiferror(CatatanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
                AppUtil.notiferror(CatatanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                finish();
            }
        });
    }

    private void setDataPutusan(){

        button_putusan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                extended_catatan.setEnabled(true);
                extended_catatan.setFocusable(true);
//                extended_catatan.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);

//                Toast.makeText(CatatanActivity.this, "woooooyy focusablenya udah gua enable in!!!!", Toast.LENGTH_SHORT).show();
                extended_catatan.requestFocus();
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });


        iv_capsule_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extended_catatan.setInputType(InputType.TYPE_NULL);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            }
        });







        //setuju on click
        bt_setuju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SweetAlertDialog dialog1 = new SweetAlertDialog(CatatanActivity.this, SweetAlertDialog.NORMAL_TYPE);

                if (extended_catatan.getText().toString().isEmpty()) {
                    text_catatan.setError("Catatan Wajib Diisi", true);
                } else {
                    if (statusPutusanInt >= 1) {
                        Toasty.info(CatatanActivity.this,"Pembiayaan sudah diputus").show();
                        RouteApp route=new RouteApp(CatatanActivity.this);
                        route.openActivityAndClearAllPrevious(CoreLayoutActivity.class);
                    }
                    else{
//                        Log.d("cekkcpanda",appPreferences.getNamaKantor().substring(4,7).trim());
                        //galau seleksi  kc dan kcp dari kode uker atau dari nama skk
//                        if(appPreferences.getUker().equalsIgnoreCase("R")||appPreferences.getUker().equalsIgnoreCase("K")){

                        //seleksi kalo dia dari KCP atau dari unit(qanun) maka cek CS, selain itu cek ADP
                        if(appPreferences.getNamaSKK().substring(0,3).equalsIgnoreCase("KCP")||appPreferences.getNamaSKK().substring(0,4).equalsIgnoreCase("UNIT")){
                            cekAdaCs(dialog1);//isi method cek cs masih di tembak untuk langsung setuju, jadi sebenernya tidak ada pengecekan CS

                        }
//                        else if (appPreferences.getUker().equalsIgnoreCase("B")||appPreferences.getUker().equalsIgnoreCase("W")){

                       else if(!appPreferences.getNamaSKK().substring(0,3).equalsIgnoreCase("KCP")){
                           if(appPreferences.getFidRole().equalsIgnoreCase("76")){
                               setujuPembiayaan(dialog1);
                           }
                           else{
                               cekAdaAdp(dialog1);
                           }


//                            setujuPembiayaan(dialog1);
                        }
                        else{
                            Toasty.error(CatatanActivity.this,"Kode uker anda bukan KCP dan bukan KC, silahkan hubungi IT").show();
                        }
                    }

//                    setujuPembiayaan(dialog1);
//                    Log.d("adacs",adaCs);


                }}
        });
        //tolak on click
        bt_tolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (extended_catatan.getText().toString().isEmpty()) {
                    text_catatan.setError("Catatan Wajib Diisi", true);
                } else {
                    if (statusPutusanInt >= 1) {
                        Toasty.info(CatatanActivity.this,"Pembiayaan sudah diputus").show();
                        RouteApp route=new RouteApp(CatatanActivity.this);
                        route.openActivityAndClearAllPrevious(CoreLayoutActivity.class);
                    }
                    else{
                        final SweetAlertDialog dialog1 = new SweetAlertDialog(CatatanActivity.this, SweetAlertDialog.NORMAL_TYPE);
                        dialog1.setTitleText("Konfirmasi")
                                .setContentText("Anda yakin akan menolak pembiayaan?")
                                .setConfirmText("Ya")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        if (statusPutusanInt > 0) {
                                            dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                            dialog1.setTitleText("GAGAL");
                                            dialog1.setContentText("Pembiayaan sudah diputus");
                                        } else if (statusPutusanInt <= 0) {
                                            dialog1.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                            dialog1.setTitleText("Memproses");
                                            dialog1.setContentText("");
                                            AppPreferences appPreferences = new AppPreferences(CatatanActivity.this);
                                            ReqSetujuPutusan req = new ReqSetujuPutusan();
                                            req.setRole_id(appPreferences.getFidRole());
                                            req.setSt_aplikasid(fidStatus);
                                            req.setFid_aplikasi(Integer.toString(idAplikasi));
                                            req.setId_pemutus(appPreferences.getUid());
                                            req.setCatatan_pemutus(extended_catatan.getText().toString());
                                            req.setKode_dsn(appPreferences.getDsnCode());


//                                            Call<ParseResponse> call = apiClientAdapter.getApiInterface().pemutusTolak(req);
                                            Call<ParseResponse> call= apiClientAdapter.getApiInterface().pemutusTolak(req);
//                                        Log.d("superdete",superData.getJenisPembiayaan());
                                            if(superData.getJenisPembiayaan().equalsIgnoreCase("kmg")){
                                                call = apiClientAdapter.getApiInterface().pemutusTolakKmg(req);
                                            }
                                            else{
                                                call = apiClientAdapter.getApiInterface().pemutusTolak(req);
                                            }
                                            call.enqueue(new Callback<ParseResponse>() {
                                                @Override
                                                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                                                    if (response.isSuccessful()) {

                                                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                                                            statusPutusanInt++;
                                                            String listDataString = response.body().getData().toString();
                                                            Gson gson = new Gson();

//                                                    Type type = new TypeToken<InfoCs>() {
//                                                    }.getType();
//                                                    dataCs= gson.fromJson(listDataString, type);



                                                            dialog1.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                            dialog1.setTitleText("Putusan Berhasil");
//                                                    if(dataCs.getNama_petugas().length()>0){
//                                                        dialog1.setContentText("CS Pencairan : "+dataCs.getNama_petugas());
//                                                    }
//                                                    else{
//                                                        dialog1.setContentText("Pembiayaan berhasil ditolak");
//                                                    }
                                                            dialog1.setContentText("Pembiayaan berhasil ditolak");

                                                            dialog1.setConfirmText("OK");
                                                            dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                @Override
                                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                    dialog1.dismissWithAnimation();
                                                                    RouteApp route=new RouteApp(CatatanActivity.this);
                                                                    route.openActivityAndClearAllPrevious(CoreLayoutActivity.class);
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

                }

            }
        });

        //kembalikan on click
        bt_kembalikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiClientAdapter = new ApiClientAdapter(CatatanActivity.this);
                final SweetAlertDialog dialog1 = new SweetAlertDialog(CatatanActivity.this, SweetAlertDialog.NORMAL_TYPE);

                if (extended_catatan.getText().toString().isEmpty()) {
                    text_catatan.setError("Catatan Wajib Diisi", true);
                } else {
                    if (statusPutusanInt >= 1) {
                        Toasty.info(CatatanActivity.this,"Pembiayaan sudah diputus").show();
                        RouteApp route=new RouteApp(CatatanActivity.this);
                        route.openActivityAndClearAllPrevious(CoreLayoutActivity.class);
                    }
                    else{
                        dialog1.setTitleText("Konfirmasi")
                                .setContentText("Anda yakin akan mengembalikan pembiayaan ke AO?")
                                .setConfirmText("Ya")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        if (statusPutusanInt > 0) {
                                            dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                            dialog1.setTitleText("GAGAL");
                                            dialog1.setContentText("Pembiayaan sudah diputus");
                                        } else if (statusPutusanInt <= 0) {
                                            dialog1.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                            dialog1.setTitleText("Memproses");
                                            dialog1.setContentText("");
                                            AppPreferences appPreferences = new AppPreferences(CatatanActivity.this);
                                            ReqSetujuPutusan req = new ReqSetujuPutusan();
                                            req.setRole_id(appPreferences.getFidRole());
                                            req.setSt_aplikasid(fidStatus);
                                            req.setFid_aplikasi(Integer.toString(idAplikasi));
                                            req.setId_pemutus(appPreferences.getUid());
                                            req.setCatatan_pemutus(extended_catatan.getText().toString());
                                            req.setKode_dsn(appPreferences.getDsnCode());

                                            Call<ParseResponse> call= apiClientAdapter.getApiInterface().pemutusKembalikan(req);
//                                        Log.d("superdete",superData.getJenisPembiayaan());
                                            if(superData.getJenisPembiayaan().equalsIgnoreCase("kmg")){
                                                call = apiClientAdapter.getApiInterface().pemutusKembalikanKmg(req);
                                            }
                                            else{
                                                call = apiClientAdapter.getApiInterface().pemutusKembalikan(req);
                                            }

                                            call.enqueue(new Callback<ParseResponse>() {
                                                @Override
                                                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                                                    if (response.isSuccessful()) {

                                                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                                                            statusPutusanInt++;
                                                            String listDataString = response.body().getData().toString();
                                                            Gson gson = new Gson();

//                                                    Type type = new TypeToken<InfoCs>() {
//                                                    }.getType();
//                                                    dataCs= gson.fromJson(listDataString, type);


                                                            statusPutusanInt=1;

                                                            dialog1.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                            dialog1.setTitleText("Putusan Berhasil");
//                                                    if(dataCs.getNama_petugas().length()>0){
//                                                        dialog1.setContentText("CS Pencairan : "+dataCs.getNama_petugas());
//                                                    }
//                                                    else{
//                                                        dialog1.setContentText("Pembiayaan berhasil ditolak");
//                                                    }
                                                            dialog1.setContentText("Pembiayaan berhasil dikembalikan ke AO");

                                                            dialog1.setConfirmText("OK");
                                                            dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                @Override
                                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                    dialog1.dismissWithAnimation();
                                                                    RouteApp route=new RouteApp(CatatanActivity.this);
                                                                    route.openActivityAndClearAllPrevious(CoreLayoutActivity.class);
                                                                }
                                                            });
                                                            dialog1.showCancelButton(false);


                                                        } else {
                                                            dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                                            dialog1.setTitle("Terjadi Kesalahan");
                                                            dialog1.setContentText(response.body().getMessage()+"\n\n");
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
                }




            }
        });

        //tunda on click
        bt_tunda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extended_catatan.setFocusable(false);
                extended_catatan.setInputType(InputType.TYPE_NULL);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }


        });

        //on long click edittext
        text_catatan.getPanel().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                Toast.makeText(CatatanActivity.this, "long-clicked", Toast.LENGTH_SHORT).show();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if (clipboard.hasPrimaryClip()) {
                    android.content.ClipDescription description = clipboard.getPrimaryClipDescription();
                    android.content.ClipData data = clipboard.getPrimaryClip();
                    if (data != null && description != null && description.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))
                        extended_catatan.setText(String.valueOf(data.getItemAt(0).getText()));
                    Toasty.info(CatatanActivity.this,"Teks berhasil di-paste").show();
                }
                return true;
            }
        });
    }


    //gausah cek CS kata mas wildan, eh barusan katanya biarin aja ada cek CS
    private void cekAdaCs(final SweetAlertDialog dialog1){

        //langsung pantek setuju, kalau cek cs diabaikan dulu
//        setujuPembiayaan(dialog1);

//
        dialog1.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        dialog1.show();

        AppPreferences appPreferences = new AppPreferences(CatatanActivity.this);
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

    private void cekAdaAdp(final SweetAlertDialog dialog1){

        //langsung pantek setuju kalau tidak diperlukan lagi cek adp
//        setujuPembiayaan(dialog1);

        dialog1.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        dialog1.show();

        AppPreferences appPreferences = new AppPreferences(CatatanActivity.this);
        ReqKodeSkk req = new ReqKodeSkk();
        req.setKodeSkk(appPreferences.getKodeSkk());



        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().cekAdaAdp(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();

                        Type type = new TypeToken<List<CsModel>>() {
                        }.getType();

                        //ini nama variabel cekCS, tapi disini dia nerima list ADP
                        cekCs= gson.fromJson(listDataString, type);

                        if(cekCs.size()<=0){
                            adaCs="belum";
                            dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog1.setTitleText("Gagal");
                            dialog1.setContentText("Kantor cabang anda belum memiliki user ADP, harap lakukan pembuatan user ADP dahulu");
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
                            final AppPreferences appPreferences = new AppPreferences(CatatanActivity.this);
                            ReqSetujuPutusan req = new ReqSetujuPutusan();

                            //cek apakah sedang ambil alih putusan, tambah parameter jika ya
                            if(appPreferences.getStatusAmbilAlih().equalsIgnoreCase("YA")){
                                req.setId_pemutus2(appPreferences.getIdPengambilAlih());
                                req.setAmbil_alih(true);
                            }

                            req.setRole_id(appPreferences.getFidRole());
                            req.setSt_aplikasid(fidStatus);
                            req.setFid_aplikasi(Integer.toString(idAplikasi));
                            req.setId_pemutus(appPreferences.getUid());
                            req.setCatatan_pemutus(extended_catatan.getText().toString());
                            req.setKode_dsn(appPreferences.getDsnCode());


                            Call<ParseResponse> call;
                                        Log.d("superdete",superData.getJenisPembiayaan());
                            Log.d("superdeteidprogram",idProgram);

                          if(idProgram.equalsIgnoreCase("222")){
                                call = apiClientAdapter.getApiInterface().pemutusSetujuFlpp(req);
                            }
                           else  if(superData.getJenisPembiayaan().equalsIgnoreCase("kpr")){

                               call = apiClientAdapter.getApiInterface().pemutusSetujuKmg(req);

                            }

                            else{
                                call = apiClientAdapter.getApiInterface().pemutusSetuju(req);
                            }
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



                                            dialog1.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                            dialog1.setTitleText("Putusan Berhasil");

                                            if(dataCs.getNama_petugas()==null){
                                                dataCs.setNama_petugas("");
                                            }

                                            if (dataCs.getNama_petugas().equalsIgnoreCase("[GAGAL]")) {
                                                dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                                dialog1.setTitleText("Putusan Gagal");
                                                dialog1.setContentText("Gagal menerima user");
                                                dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                        finish();
                                                    }
                                                });
                                            } else if (dataCs.getNama_petugas().length() > 0) {
                                                statusPutusanInt++;
//                                                if(appPreferences.getUker().equalsIgnoreCase("R")||appPreferences.getUker().equalsIgnoreCase("K")){

                                                //ngeliat dia dari KC apa KCP
                                                if(appPreferences.getNamaSKK().substring(0,3).equalsIgnoreCase("KCP")){
                                                    dialog1.setContentText("Dilanjutkan ke ADP/CS : " + dataCs.getNama_petugas()+"\n");
                                                    dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                            RouteApp route=new RouteApp(CatatanActivity.this);
                                                            route.openActivityAndClearAllPrevious(CoreLayoutActivity.class);
                                                        }
                                                    });
                                                }
                                                //masih galau seleksi nentuin dia dari kc dan kcp
//                                                else  if(appPreferences.getUker().equalsIgnoreCase("B")||appPreferences.getUker().equalsIgnoreCase("W")){
                                                     else  if(!appPreferences.getNamaSKK().substring(0,3).equalsIgnoreCase("KCP")){
                                                    dialog1.setContentText("Dilanjutkan ke ADP/CS : " + dataCs.getNama_petugas()+"\n");
                                                    dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                            RouteApp route=new RouteApp(CatatanActivity.this);
                                                            route.openActivityAndClearAllPrevious(CoreLayoutActivity.class);
                                                        }
                                                    });
                                                }
                                                else{
                                                    Toasty.error(CatatanActivity.this,"Uker anda bukan KC dan bukan KCP, silahkan hubungi IT").show();
                                                }

                                            } else {
                                                statusPutusanInt++;
                                                dialog1.setContentText("Pembiayaan berhasil disetujui\n");

                                            }

                                            dialog1.setConfirmText("OK");
                                            dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                    dialog1.dismissWithAnimation();
                                                    RouteApp route=new RouteApp(CatatanActivity.this);
                                                    route.openActivityAndClearAllPrevious(CoreLayoutActivity.class);
                                                }
                                            });
                                            dialog1.showCancelButton(false);


                                        } else {
                                            dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                            dialog1.setTitle("Terjadi Kesalahan");
                                            dialog1.setContentText(response.body().getMessage()+"\n\n");
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
}
