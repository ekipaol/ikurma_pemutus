package com.application.bris.brisi_pemutus.page_putusan.lkn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseError;
import com.application.bris.brisi_pemutus.api.model.request.lkn.ReqLkn;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.lkn.DataLkn;
import com.application.bris.brisi_pemutus.model.lkn.DataRekomendasiLkn;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_putusan.PutusanFrontMenu;
import com.application.bris.brisi_pemutus.page_putusan.adapters.LknStepAdapter;
import com.application.bris.brisi_pemutus.page_putusan.data_lengkap.ActivityDataLengkap;
import com.application.bris.brisi_pemutus.page_putusan.rpc.RpcActivity;
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

public class LknActivity extends AppCompatActivity implements StepperLayout.StepperListener{
    @BindView(R.id.stepperlayout)
    StepperLayout stepperlayout;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_back)
    ImageView btn_back;

    @BindView(R.id.animWhale)
    LottieAnimationView whale;
    @BindView(R.id.tvWhale)
    TextView textWhale;
    private static final String CURRENT_STEP_POSITION_KEY = "position";

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataLknString;
    private String dataRekomendasiString;
    private DataLkn data;
    private DataRekomendasiLkn dataRekomendasiLkn;
    private ReqLkn dataReq;
    private int startingStepPosition;

    //DATA PRIBADI
    public static String val_NamaAlias ="";
    public static String val_NoKtpPasangan ="";
    public static String val_StatusNikah ="";
    public static String val_NoHp ="";
    public static String val_NamaNasabah ="";
    public static String val_Npwp ="";
    public static String val_PendTerakhir ="";
    public static String val_KetGelar ="";
    public static String val_KetAgama ="";
    public static String val_Agama ="";
    public static String val_NamaIbu ="";
    public static String val_NamaPasangan ="";
    public static String val_Email ="";
    public static String val_TelpKeluarga ="";
    public static String val_ExpId ="";
    public static String val_TglLahirPasangan ="";
    public static String val_NoKtp ="";
    public static int val_JlhTanggungan = 0;
    public static String val_TglLahir ="";
    public static String val_Keluarga ="";
    public static String val_TptLahir ="";
    public static String val_TipePendapatan ="";
    public static String val_Jenkel ="";

    //DATA ALAMAT
    public static String val_AlamatId ="";
    public static String val_RtId ="";
    public static String val_RwId ="";
    public static String val_ProvId ="";
    public static String val_KotaId ="";
    public static String val_KecId ="";
    public static String val_KelId ="";
    public static String val_KodePosId ="";
    public static String val_StatusTptTinggal ="";
    public static int val_LamaMenetap = 0;
    public static String val_AlamatDom ="";
    public static String val_RtDom ="";
    public static String val_RwDom ="";
    public static String val_ProvDom ="";
    public static String val_KotaDom ="";
    public static String val_KecDom ="";
    public static String val_KelDom ="";
    public static String val_KodePosDom ="";

    public static String cif;
    public static String idAplikasi;
    public static String tujuanPembiayaan;
    public static String plafond;
    public static int jw;

    AllDataFront superData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_lkn);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
       //set sektor ekonomi as already read
        appPreferences = new AppPreferences(this);
        appPreferences.setReadLkn("yes");
        dataReq = new ReqLkn();
        cif = getIntent().getStringExtra("cif");
        idAplikasi = getIntent().getStringExtra("idAplikasi");
        tujuanPembiayaan=getIntent().getStringExtra("tujuanPembiayaan");
        plafond=getIntent().getStringExtra("plafond");
        jw=getIntent().getIntExtra("jw",0);
        Log.d("nilaitenor",Integer.toString(jw));
        superData=(AllDataFront)getIntent().getSerializableExtra("superData");

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "LKN");
        ImageView backToolbar = findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LknActivity.this, PutusanFrontMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
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

        //real data
       ReqLkn req = new ReqLkn(idAplikasi, cif);

        //PANTEKAN
//        ReqLkn req = new ReqLkn("101678", "81857");

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquireLkn(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            dataLknString = response.body().getData().toString();
                            dataRekomendasiString=response.body().getData().get("DATA_VALIDASI_LKN").getAsJsonObject().toString();



//                            Log.d("rekomendasiString",dataRekomendasiString);
                            data = gson.fromJson(dataLknString, DataLkn.class);
                            dataRekomendasiLkn=gson.fromJson(dataRekomendasiString, DataRekomendasiLkn.class);
                            stepperlayout.setAdapter(new LknStepAdapter(getSupportFragmentManager(), LknActivity.this, data,dataRekomendasiLkn), startingStepPosition );
                            stepperlayout.setListener(LknActivity.this);
                        }
                        else{
                            stepperlayout.setVisibility(View.GONE);
                            whale.setVisibility(View.VISIBLE);
                            textWhale.setVisibility(View.VISIBLE);

                        }
                    }
                    else {
                        Toast.makeText(LknActivity.this, "Terjadi kesalahan dalam pengambilan LKN", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                catch (Exception e){
                    Log.d("bangidong",e.getMessage());
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(LknActivity.this, findViewById(android.R.id.content), "terjadi kesalahan");
                finish();
            }
        });
    }



//    public long parseLamaUsaha(){
//        try {
//            String val = data.getlAMABEKERJA();
//            String d1 = val.substring(0, 2);
//            String d2 = val.substring(2, 4);
//
//            if (!d2.equalsIgnoreCase("00")) {
//                return AppUtil.parseLongWithDefault(d2, 0);
//            } else if (d2.equalsIgnoreCase("00") && !d1.equalsIgnoreCase("00")) {
//                return AppUtil.parseLongWithDefault(d1, 0);
//            } else {
//                return 0;
//            }
//        }
//        catch (Exception e){
//            return 0;
//        }
//    }






    @Override
    public void onCompleted(View view) {

        Intent intent = new Intent(LknActivity.this, RpcActivity.class);

        intent.putExtra("idAplikasi",superData.getIdAplikasi());
        intent.putExtra("superData",superData);
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

