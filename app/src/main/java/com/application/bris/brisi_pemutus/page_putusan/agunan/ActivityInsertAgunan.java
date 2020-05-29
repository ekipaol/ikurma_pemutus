package com.application.bris.brisi_pemutus.page_putusan.agunan;


import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.agunan_global_idapl_agunan_cif.AgunanGlobal;
import com.application.bris.brisi_pemutus.api.model.request.data_lengkap.ReqDataLengkap;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.agunan.Agunan;
import com.application.bris.brisi_pemutus.page_putusan.adapters.StepAdapterAgunan;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.google.gson.Gson;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityInsertAgunan extends AppCompatActivity implements StepperLayout.StepperListener{

    @BindView(R.id.stepperlayout)
    StepperLayout stepperlayout;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    private static final String CURRENT_STEP_POSITION_KEY = "position";
    private String cif;
    private int idAplikasi;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataPribadiString;
    private Agunan dataAgunan;
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
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        dataLengkapReq = new ReqDataLengkap();
        cif = getIntent().getStringExtra("cif");
        idAplikasi = Integer.parseInt(getIntent().getStringExtra("idAplikasi"));
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Lengkap");
        startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
        stepperlayout.setAdapter(new StepAdapterAgunan(getSupportFragmentManager(),ActivityInsertAgunan.this,dataAgunan ), startingStepPosition );
        stepperlayout.setListener(ActivityInsertAgunan.this);
        loadDataLengkap();




    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void loadDataLengkap() {
        loading.setVisibility(View.VISIBLE);
        AgunanGlobal req = new AgunanGlobal();

        //data real
//        req.setIdApl((getIntent().getStringExtra("idAplikasi")));
//        req.setIdAgunan((getIntent().getStringExtra("idAgunan")));

        Log.d("ekipaol_id_agunan",getIntent().getStringExtra("idAplikasi"));

        //panteg'an
        req.setIdApl(101678);
        req.setIdAgunan(48885);
        req.setIdCif(818912);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryAgunan(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){


                            Gson gson = new Gson();

                            dataPribadiString = response.body().getData().get("data_detail").toString();
                            dataAgunan = gson.fromJson(dataPribadiString, Agunan.class);
                            stepperlayout.setAdapter(new StepAdapterAgunan(getSupportFragmentManager(),ActivityInsertAgunan.this,dataAgunan ), startingStepPosition );
                            stepperlayout.setListener(ActivityInsertAgunan.this);
                        }
                    }
                    else {
//                        Error error = ParseResponseError.confirmEror(response.errorBody());
//                        AppUtil.notiferror(ActivityDataLengkap.this, findViewById(android.R.id.content), error.getMessage());
//                        finish();
                        //minta kelas error sama bang idong
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityInsertAgunan.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
                finish();
            }
        });

    }


    @Override
    public void onCompleted(View view) {

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

