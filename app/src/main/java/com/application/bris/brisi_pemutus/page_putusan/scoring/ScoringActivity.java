package com.application.bris.brisi_pemutus.page_putusan.scoring;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.scoring.ReqScoring;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_pembiayaan.DataPbySebelumPutusan;
import com.application.bris.brisi_pemutus.model.scoring.Scoring;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_putusan.PutusanFrontMenu;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityKelengkapanDokumen;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.KeyValue;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class ScoringActivity extends AppCompatActivity  {

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.tf_rpcratio)
    TextFieldBoxes tf_rpcratio;
    @BindView(R.id.et_rpcratio)
    EditText et_rpcratio;

    @BindView(R.id.tf_ratio_agunan)
    TextFieldBoxes tf_ratio_agunan;
    @BindView(R.id.et_ratio_agunan)
    EditText et_ratio_agunan;
    @BindView(R.id.tf_currentratio)
    TextFieldBoxes tf_currentratio;
    @BindView(R.id.et_currentratio)
    EditText et_currentratio;
    @BindView(R.id.tf_profitability)
    TextFieldBoxes tf_profitability;
    @BindView(R.id.et_profitability)
    EditText et_profitability;
    @BindView(R.id.tf_reputasiusaha)
    TextFieldBoxes tf_reputasiusaha;
    @BindView(R.id.et_reputasiusaha)
    EditText et_reputasiusaha;
    @BindView(R.id.tf_riwayathubunganbank)
    TextFieldBoxes tf_riwayathubunganbank;
    @BindView(R.id.et_riwayathubunganbank)
    EditText et_riwayathubunganbank;
    @BindView(R.id.tf_lamausaha)
    TextFieldBoxes tf_lamausaha;
    @BindView(R.id.et_lamausaha)
    EditText et_lamausaha;
    @BindView(R.id.tf_prospekusaha)
    TextFieldBoxes tf_prospekusaha;
    @BindView(R.id.et_prospekusaha)
    EditText et_prospekusaha;
    @BindView(R.id.tf_ketergantunganthdsupplier)
    TextFieldBoxes tf_ketergantunganthdsupplier;
    @BindView(R.id.et_ketergantunganthdsupplier)
    EditText et_ketergantunganthdsupplier;
    @BindView(R.id.tf_ketergantunganthdpelanggan)
    TextFieldBoxes tf_ketergantunganthdpelanggan;
    @BindView(R.id.et_ketergantunganthdpelanggan)
    EditText et_ketergantunganthdpelanggan;
    @BindView(R.id.tf_wilayahpemasaran)
    TextFieldBoxes tf_wilayahpemasaran;
    @BindView(R.id.et_wilayahpemasaran)
    EditText et_wilayahpemasaran;
    @BindView(R.id.tf_jenisproduk)
    TextFieldBoxes tf_jenisproduk;
    @BindView(R.id.et_jenisproduk)
    EditText et_jenisproduk;
    @BindView(R.id.tf_jangkawaktu)
    TextFieldBoxes tf_jangkawaktu;
    @BindView(R.id.et_jangkawaktu)
    EditText et_jangkawaktu;
    @BindView(R.id.tf_jenispembiayaan)
    TextFieldBoxes tf_jenispembiayaan;
    @BindView(R.id.et_jenispembiayaan)
    EditText et_jenispembiayaan;

    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.ll_contenthasil)
    LinearLayout ll_contenthasil;
    @BindView(R.id.ll_hasil)
    LinearLayout ll_hasil;
    @BindView(R.id.tv_titlehasil)
    TextView tv_titlehasil;
    @BindView(R.id.tv_kethasil)
    TextView tv_kethasil;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    @BindView(R.id.bt_lanjut_scoring)
    Button bt_lanjut_scoring;


    private int idAplikasi;
    private int cif;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    AllDataFront superData;

    private Scoring data;


    private DataPbySebelumPutusan dataPbySebelumPutusan;

    private String dtKatLBUString, dtJenisPenggunaanLbuString, dtSifatPbyLBUString, dtSifatPbyString,dtBidangUsahaString,
            dtJenisPenggunaanString, dtJenisKreditLbuString, dtHubDebiturString, dataPbySebelumPutusanString;

    private long val_ketergantunganPelanggan;
    private long val_jenisPembiayaan;
    private long val_lamaUsaha;
    private long val_deviasiMikro;
    private long val_jenisProduk;
    private long val_hubunganBank;
    private long val_jangkaWaktu;
    private long val_wilayahPemasaran;
    private long val_rpcRatio;
    private long val_reputasiUsaha;
    private long val_ketergantunganSupplier;
    private long val_prospekUsaha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_scoring);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);

        //set scoring as already read
        appPreferences = new AppPreferences(this);
        appPreferences.setReadScoring("yes");

        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        cif = getIntent().getIntExtra("cif", 0);
        superData=(AllDataFront)getIntent().getSerializableExtra("superData");
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Scoring");
        ImageView backToolbar = findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoringActivity.this, PutusanFrontMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        bt_lanjut_scoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ScoringActivity.this, ActivityKelengkapanDokumen.class);
                intent.putExtra("idAplikasi", superData.getIdAplikasi());
                intent.putExtra("superData",superData);


                //when back make this thing go to putusan frontmenu
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        sm_placeholder.startShimmer();
        loadData();
        disableTextfield();

    }

    @Override
    protected void onPause() {
        super.onPause();
        sm_placeholder.stopShimmer();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void loadData() {


        //real data
        ReqScoring req = new ReqScoring(cif,idAplikasi);

        //pantekan
//        ReqScoring req = new ReqScoring(81857,101678);


        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryScoring(req);
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
                            data = gson.fromJson(response.body().getData().get("dataScoring").toString(), Scoring.class);
                            setData();
                        }
                        else{
                            finish();
                            AppUtil.notiferror(ScoringActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        finish();
                        Toast.makeText(ScoringActivity.this, "Terjadi kesalahan di Scoring", Toast.LENGTH_SHORT).show();
                       // AppUtil.notiferror(ScoringActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                finish();
                AppUtil.notiferror(ScoringActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
            }
        });

    }

    public void setData(){

        //ini dikasih if karena belum bisa mendapatkan kode produk di putusanfront menu
        if(superData.getKodeProduk()!=null){
            et_rpcratio.setText(KeyValue.getKeyRpcRatio(superData.getKodeProduk(),String.valueOf(data.getrPCRATIO())));
        }

        if (data.getCurrentratio()!=null&&data.getCurrentratio()!=0){
            tf_currentratio.setVisibility(View.VISIBLE);
            et_currentratio.setText(KeyValue.getKeyCurrentRatio(String.valueOf(data.getCurrentratio())));
        }

        if (data.getProfitabiility()!=null&&data.getProfitabiility()!=0){
            tf_profitability.setVisibility(View.VISIBLE);
            et_profitability.setText(KeyValue.getKeyProfitability(String.valueOf(data.getProfitabiility())));
        }

        if (data.getRatioAgunan()!=null&&data.getRatioAgunan()!=0){
            tf_ratio_agunan.setVisibility(View.VISIBLE);
            et_ratio_agunan.setText(KeyValue.getKeyAgunanRatio(String.valueOf(data.getRatioAgunan())));
        }


        et_reputasiusaha.setText(KeyValue.getKeyReputasiUsaha(String.valueOf(data.getrEPUTASIUSAHA())));
        et_riwayathubunganbank.setText(KeyValue.getKeyRiwayatHubdgnBank(String.valueOf(data.gethUBUNGANBANK())));
        et_lamausaha.setText(KeyValue.getKeyLamaUsaha(String.valueOf(data.getlAMAUSAHA())));
        et_prospekusaha.setText(KeyValue.getKeyProspekUsaha(String.valueOf(data.getpROSPEKUSAHA())));
        et_ketergantunganthdsupplier.setText(KeyValue.getKeyKetergantunganSupplierdanPelanggan(String.valueOf(data.getkETERGANTUNGANSUPPLIER())));
        et_ketergantunganthdpelanggan.setText(KeyValue.getKeyKetergantunganSupplierdanPelanggan(String.valueOf(data.getkETERGANTUNGANPELANGGAN())));
        et_wilayahpemasaran.setText(KeyValue.getKeyWilayahPemasaran(String.valueOf(data.getwILAYAHPEMASARAN())));
        et_jenisproduk.setText(KeyValue.getKeyJenisProduk(String.valueOf(data.getjENISPRODUK())));
        et_jangkawaktu.setText(KeyValue.getKeyJangkaWaktu(String.valueOf(data.getjANGKAWAKTU())));
        et_jenispembiayaan.setText(KeyValue.getKeyJenisPembiayaan(String.valueOf(data.getjENISPEMBIAYAAN())));

        if (!data.getHasil().equalsIgnoreCase("") || !data.getHasil().isEmpty()){
            ll_contenthasil.setVisibility(View.VISIBLE);
            tv_titlehasil.setText(data.getHasil());
            tv_kethasil.setText(data.getPesan());
            if (data.getHasil().equalsIgnoreCase("HIGH RISK")){
                ll_hasil.setBackgroundResource(R.drawable.shapered);
            }
            else if (data.getHasil().equalsIgnoreCase("MEDIUM RISK")){
                ll_hasil.setBackgroundResource(R.drawable.shapeyellow);
            }
            else{
                ll_hasil.setBackgroundResource(R.drawable.shapegreen);
            }
        }

        val_ketergantunganPelanggan = data.getkETERGANTUNGANPELANGGAN();
        val_jenisPembiayaan = data.getjENISPEMBIAYAAN();
        val_lamaUsaha = data.getlAMAUSAHA();
        val_deviasiMikro = data.getdEVIASIMIKRO();
        val_jenisProduk = data.getjENISPRODUK();
        val_hubunganBank = data.gethUBUNGANBANK();
        val_jangkaWaktu = data.getjANGKAWAKTU();
        val_wilayahPemasaran = data.getwILAYAHPEMASARAN();
        val_rpcRatio = data.getrPCRATIO();
        val_reputasiUsaha = data.getrEPUTASIUSAHA();
        val_ketergantunganSupplier = data.getkETERGANTUNGANSUPPLIER();
        val_prospekUsaha = data.getpROSPEKUSAHA();


    }

    private void disableTextfield() {

        et_rpcratio.setInputType(InputType.TYPE_NULL);
        et_rpcratio.setFocusable(false);

        et_ratio_agunan.setInputType(InputType.TYPE_NULL);
        et_ratio_agunan.setFocusable(false);

        et_currentratio.setInputType(InputType.TYPE_NULL);
        et_currentratio.setFocusable(false);

        et_profitability.setInputType(InputType.TYPE_NULL);
        et_profitability.setFocusable(false);

        et_reputasiusaha.setInputType(InputType.TYPE_NULL);
        et_reputasiusaha.setFocusable(false);

        et_riwayathubunganbank.setInputType(InputType.TYPE_NULL);
        et_riwayathubunganbank.setFocusable(false);

        et_lamausaha.setInputType(InputType.TYPE_NULL);
        et_lamausaha.setFocusable(false);

        et_prospekusaha.setInputType(InputType.TYPE_NULL);
        et_prospekusaha.setFocusable(false);

        et_ketergantunganthdsupplier.setInputType(InputType.TYPE_NULL);
        et_ketergantunganthdsupplier.setFocusable(false);

        et_ketergantunganthdpelanggan.setInputType(InputType.TYPE_NULL);
        et_ketergantunganthdpelanggan.setFocusable(false);

        et_wilayahpemasaran.setInputType(InputType.TYPE_NULL);
        et_wilayahpemasaran.setFocusable(false);

        et_jenisproduk.setInputType(InputType.TYPE_NULL);
        et_jenisproduk.setFocusable(false);

        et_jangkawaktu.setInputType(InputType.TYPE_NULL);
        et_jangkawaktu.setFocusable(false);

        et_jenispembiayaan.setInputType(InputType.TYPE_NULL);
        et_jenispembiayaan.setFocusable(false);
    }












}

