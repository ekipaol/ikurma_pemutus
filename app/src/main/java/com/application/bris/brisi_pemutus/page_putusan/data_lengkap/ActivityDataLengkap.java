package com.application.bris.brisi_pemutus.page_putusan.data_lengkap;

import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.data_lengkap.ReqDataLengkap;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkap;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_putusan.PutusanFrontMenu;
import com.application.bris.brisi_pemutus.page_putusan.adapters.SampleFragmentStepAdapter;
import com.application.bris.brisi_pemutus.page_putusan.sektor_ekonomi.SektorEkonomiActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.google.gson.Gson;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDataLengkap extends AppCompatActivity implements StepperLayout.StepperListener {

    @BindView(R.id.stepperlayout)
    StepperLayout stepperlayout;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    private static final String CURRENT_STEP_POSITION_KEY = "position";
    private String cif;
    private String idAplikasi;
    AllDataFront superData;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataPribadiString;
    private DataLengkap dataLengkap;
    private ReqDataLengkap dataLengkapReq;
    private int startingStepPosition;

    //DATA PRIBADI
    public static String val_NamaAlias = "";
    public static String val_NoKtpPasangan = "";
    public static String val_StatusNikah = "";
    public static String val_NoHp = "";
    public static String val_NamaNasabah = "";
    public static String val_Npwp = "";
    public static String val_PendTerakhir = "";
    public static String val_KetGelar = "";
    public static String val_KetAgama = "";
    public static String val_Agama = "";
    public static String val_NamaIbu = "";
    public static String val_NamaPasangan = "";
    public static String val_Email = "";
    public static String val_TelpKeluarga = "";
    public static String val_ExpId = "";
    public static String val_TglLahirPasangan = "";
    public static String val_NoKtp = "";
    public static int val_JlhTanggungan = 0;
    public static String val_TglLahir = "";
    public static String val_Keluarga = "";
    public static String val_TptLahir = "";
    public static String val_TipePendapatan = "";
    public static String val_Jenkel = "";

    //DATA ALAMAT
    public static String val_AlamatId = "";
    public static String val_RtId = "";
    public static String val_RwId = "";
    public static String val_ProvId = "";
    public static String val_KotaId = "";
    public static String val_KecId = "";
    public static String val_KelId = "";
    public static String val_KodePosId = "";
    public static String val_StatusTptTinggal = "";
    public static int val_LamaMenetap = 0;
    public static String val_AlamatDom = "";
    public static String val_RtDom = "";
    public static String val_RwDom = "";
    public static String val_ProvDom = "";
    public static String val_KotaDom = "";
    public static String val_KecDom = "";
    public static String val_KelDom = "";
    public static String val_KodePosDom = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_datalengkap);
        ButterKnife.bind(this);
        superData = (AllDataFront) getIntent().getSerializableExtra("superData");
        apiClientAdapter = new ApiClientAdapter(this);

        //set data lengkap as already read
        appPreferences = new AppPreferences(this);
        appPreferences.setReadDataLengkap("yes");
        dataLengkapReq = new ReqDataLengkap();
        cif = getIntent().getStringExtra("cif");
        idAplikasi = getIntent().getStringExtra("idAplikasi");
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Lengkap");

        //toolbar back configuration, hard to explain, just ask to mr eki. In short, this is needed so the activity flows as eki wants
        ImageView backToolbar = findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityDataLengkap.this, PutusanFrontMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;

        if(superData.getKodeProduk().equalsIgnoreCase("71")||superData.getKodeProduk().equalsIgnoreCase("72")||superData.getKodeProduk().equalsIgnoreCase("73")){

        }
        loadDataLengkap();

    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void loadDataLengkap() {
        loading.setVisibility(View.VISIBLE);
        ReqDataLengkap req = new ReqDataLengkap();

        //real data
        req.setCif(cif);
        req.setIdAplikasi(idAplikasi);

        //pantekan
//        req.setCif("81857");
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryDataLengkap(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            Gson gson = new Gson();
                            dataPribadiString = response.body().getData().get("nasabah").toString();
                            dataLengkap = gson.fromJson(dataPribadiString, DataLengkap.class);

                            stepperlayout.setAdapter(new SampleFragmentStepAdapter(getSupportFragmentManager(), ActivityDataLengkap.this, dataLengkap, 1), startingStepPosition);
                            stepperlayout.setListener(ActivityDataLengkap.this);
                        }
                    } else {
//                        Error error = ParseResponseError.confirmEror(response.errorBody());
//                        AppUtil.notiferror(ActivityDataLengkap.this, findViewById(android.R.id.content), error.getMessage());
//                        finish();
                        //minta kelas error sama bang idong
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityDataLengkap.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
                finish();
            }
        });
    }


    @Override
    public void onCompleted(View view) {

        Intent intent = new Intent(ActivityDataLengkap.this, SektorEkonomiActivity.class);

        intent.putExtra("cif", superData.getCif());
        intent.putExtra("idAplikasi", Integer.parseInt(superData.getIdAplikasi()));
        intent.putExtra("id_tujuan", superData.getIdTujuan());
        intent.putExtra("tujuanPembiayaan", superData.getTujuanPembiayaan());
        intent.putExtra("superData", superData);


        startActivity(intent);
    }



    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int i) {

    }

    @Override
    public void onReturn() {

    }
}
