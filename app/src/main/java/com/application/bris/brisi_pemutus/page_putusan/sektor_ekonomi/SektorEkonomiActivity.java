package com.application.bris.brisi_pemutus.page_putusan.sektor_ekonomi;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseError;
import com.application.bris.brisi_pemutus.api.model.request.sektor_ekonomi.ReqSektorEkonomi;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_pembiayaan.DataPbySebelumPutusan;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_putusan.PutusanFrontMenu;
import com.application.bris.brisi_pemutus.page_putusan.data_lengkap.ActivityDataLengkap;
import com.application.bris.brisi_pemutus.page_putusan.history.HistoryActivity;
import com.application.bris.brisi_pemutus.page_putusan.lkn.LknActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class SektorEkonomiActivity extends AppCompatActivity {

    @BindView(R.id.ll_content)
    LinearLayout ll_content;

    @BindView(R.id.ll_shimmer)
    ShimmerFrameLayout ll_shimmer;

    @BindView(R.id.tf_tujuanpenggunaan)
    TextFieldBoxes tf_tujuanpenggunaan;
    @BindView(R.id.et_tujuanpenggunaan)
    EditText et_tujuanpenggunaan;

    @BindView(R.id.tf_bidangusaha)
    TextFieldBoxes tf_bidangusaha;
    @BindView(R.id.et_bidangusaha)
    EditText et_bidangusaha;

    @BindView(R.id.tf_sifatpembiayaan)
    TextFieldBoxes tf_sifatpembiayaan;
    @BindView(R.id.et_sifatpembiayaan)
    EditText et_sifatpembiayaan;

    @BindView(R.id.tf_jenispenggunaan)
    TextFieldBoxes tf_jenispenggunaan;
    @BindView(R.id.et_jenispenggunaan)
    EditText et_jenispenggunaan;

    @BindView(R.id.tf_jenispenggunaanlbu)
    TextFieldBoxes tf_jenispenggunaanlbu;
    @BindView(R.id.et_jenispenggunaanlbu)
    EditText et_jenispenggunaanlbu;

    @BindView(R.id.tf_jenispembiayaanlbu)
    TextFieldBoxes tf_jenispembiayaanlbu;
    @BindView(R.id.et_jenispembiayaanlbu)
    EditText et_jenispembiayaanlbu;

    @BindView(R.id.tf_sifatpembiayaanlbu)
    TextFieldBoxes tf_sifatpembiayaanlbu;
    @BindView(R.id.et_sifatpembiayaanlbu)
    EditText et_sifatpembiayaanlbu;

    @BindView(R.id.tf_kategoripembiayaanlbu)
    TextFieldBoxes tf_kategoripembiayaanlbu;
    @BindView(R.id.et_kategoripembiayaanlbu)
    EditText et_kategoripembiayaanlbu;

    @BindView(R.id.tf_sektorekonomi)
    TextFieldBoxes tf_sektorekonomi;
    @BindView(R.id.et_sektorekonomi)
    EditText et_sektorekonomi;

    @BindView(R.id.tf_hubungannasabahdgnbank)
    TextFieldBoxes tf_hubungannasabahdgnbank;
    @BindView(R.id.et_hubungannasabahdgnbank)
    EditText et_hubungannasabahdgnbank;

    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    @BindView(R.id.bt_lanjut_sektor_ekonomi)
    Button bt_lanjut_sektor_ekonomi;

    private int idAplikasi;
    private int cifLas;
    private int idTujuan;
    private String namaTujuan;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    AllDataFront superData;

    private DataPbySebelumPutusan dataPbySebelumPutusan;

    private String dtKatLBUString, dtJenisPenggunaanLbuString, dtSifatPbyLBUString, dtSifatPbyString,dtBidangUsahaString,
            dtJenisPenggunaanString, dtJenisKreditLbuString, dtHubDebiturString, dataPbySebelumPutusanString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_sektor_ekonomi);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);

        //set sektor ekonomi as already read
        appPreferences = new AppPreferences(this);
        appPreferences.setReadSektorEkonomi("yes");

        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        cifLas = getIntent().getIntExtra("cifLas", 0);
        idTujuan = getIntent().getIntExtra("idTujuan", 0);
        namaTujuan = getIntent().getStringExtra("tujuanPembiayaan");
        superData= (AllDataFront)getIntent().getSerializableExtra("superData");

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Sektor Ekonomi");
        ImageView backToolbar = findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SektorEkonomiActivity.this, PutusanFrontMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        bt_lanjut_sektor_ekonomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SektorEkonomiActivity.this, LknActivity.class);
                intent.putExtra("cif", superData.getCif());
                intent.putExtra("idAplikasi", superData.getIdAplikasi());
                intent.putExtra("tujuanPembiayaan", superData.getTujuanPembiayaan());
                intent.putExtra("jw", Integer.parseInt(superData.getJw()));
                intent.putExtra("plafond", superData.getPlafond());
                intent.putExtra("superData",superData);
                startActivity(intent);
            }
        });

        //TUJUAN PENGGUNAAN


    }

    @Override
    protected void onResume() {
        super.onResume();
        ll_shimmer.setVisibility(View.VISIBLE);
        sm_placeholder.startShimmer();
        loadData();
    }



    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void loadData() {
        ReqSektorEkonomi req = new ReqSektorEkonomi();



        //data real
        req.setIdAplikasi(idAplikasi);
        req.setIdRole(Integer.parseInt(appPreferences.getFidRole()));


        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquirySektorEkonomi(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){

                            Gson gson = new Gson();
                            dataPbySebelumPutusanString = response.body().getData().get("dataPbySebelumPutusan").toString();
                            dataPbySebelumPutusan = gson.fromJson(dataPbySebelumPutusanString, DataPbySebelumPutusan.class);

                            setData();
                        }
                        else{
                            finish();
                            AppUtil.notiferror(SektorEkonomiActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        finish();
                      //error message
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                finish();
                AppUtil.notiferror(SektorEkonomiActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
            }
        });

    }

    public void setData(){
        et_tujuanpenggunaan.setText(namaTujuan);
        et_bidangusaha.setText(dataPbySebelumPutusan.getBidangUsahaText());
        et_sifatpembiayaan.setText(dataPbySebelumPutusan.getSifatKreditText());
        et_jenispenggunaan.setText(dataPbySebelumPutusan.getJenisPenggunaanText());
        et_jenispenggunaanlbu.setText(dataPbySebelumPutusan.getJenisPenggunaanText());
        et_jenispembiayaanlbu.setText(dataPbySebelumPutusan.getJenisKreditLBUText());
        et_sifatpembiayaanlbu.setText(dataPbySebelumPutusan.getSifatKreditLBUText());
        et_kategoripembiayaanlbu.setText(dataPbySebelumPutusan.getKategoriKreditLBUText());
        et_sektorekonomi.setText(dataPbySebelumPutusan.getSektorEkonomiText());
        et_hubungannasabahdgnbank.setText(dataPbySebelumPutusan.getHubDebiturDgnBankText());


    }

    private void disableTextField(){
        et_tujuanpenggunaan.setInputType(InputType.TYPE_NULL);
        et_tujuanpenggunaan.setFocusable(false);
        et_tujuanpenggunaan.setKeyListener(null);

        et_bidangusaha.setInputType(InputType.TYPE_NULL);
        et_bidangusaha.setFocusable(false);
        et_bidangusaha.setKeyListener(null);

        et_sifatpembiayaan.setInputType(InputType.TYPE_NULL);
        et_sifatpembiayaan.setFocusable(false);
        et_sifatpembiayaan.setKeyListener(null);

        et_jenispenggunaan.setInputType(InputType.TYPE_NULL);
        et_jenispenggunaan.setFocusable(false);
        et_jenispenggunaan.setKeyListener(null);

        et_jenispenggunaanlbu.setInputType(InputType.TYPE_NULL);
        et_jenispenggunaanlbu.setFocusable(false);
        et_jenispenggunaanlbu.setKeyListener(null);

        et_jenispembiayaanlbu.setInputType(InputType.TYPE_NULL);
        et_jenispembiayaanlbu.setFocusable(false);
        et_jenispembiayaanlbu.setKeyListener(null);

        et_sifatpembiayaanlbu.setInputType(InputType.TYPE_NULL);
        et_sifatpembiayaanlbu.setFocusable(false);
        et_sifatpembiayaanlbu.setKeyListener(null);

        et_kategoripembiayaanlbu.setInputType(InputType.TYPE_NULL);
        et_kategoripembiayaanlbu.setFocusable(false);
        et_kategoripembiayaanlbu.setKeyListener(null);

        et_sektorekonomi.setInputType(InputType.TYPE_NULL);
        et_sektorekonomi.setFocusable(false);
        et_sektorekonomi.setKeyListener(null);

        et_hubungannasabahdgnbank.setInputType(InputType.TYPE_NULL);
        et_hubungannasabahdgnbank.setFocusable(false);
        et_hubungannasabahdgnbank.setKeyListener(null);
    }











}

