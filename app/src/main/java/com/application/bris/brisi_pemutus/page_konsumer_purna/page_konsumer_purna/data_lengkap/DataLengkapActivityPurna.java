package com.application.bris.brisi_pemutus.page_konsumer_purna.page_konsumer_purna.data_lengkap;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseError;
import com.application.bris.brisi_pemutus.api.model.Error;
import com.application.bris.brisi_pemutus.api.model.request.data_lengkap.ReqDataLengkap;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkap;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkapKonsumerKmg;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.data_lengkap.DataLengkapKonsumerKmgActivity;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.front_menu.PutusanFrontMenuKmg;
import com.application.bris.brisi_pemutus.page_putusan.prescreening.PrescreeningActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.google.gson.Gson;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataLengkapActivityPurna extends AppCompatActivity implements StepperLayout.StepperListener{
    @BindView(R.id.stepperlayout)
    StepperLayout stepperlayout;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_back)
    ImageView btn_back;


    private static final String CURRENT_STEP_POSITION_KEY = "position";
    public static String cif,approved, gimmick;
    public static int uid;
    public static int idAplikasi;
    public static int plafond;
    AllDataFront superData;


    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataPribadiString;
    private DataLengkapKonsumerKmg dataLengkap;
    private int startingStepPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_datalengkap);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        superData = (AllDataFront) getIntent().getSerializableExtra("superData");
        cif =superData.getCif();
        uid = Integer.parseInt(appPreferences.getUid());
        idAplikasi = Integer.parseInt(superData.getIdAplikasi());

        //set session as read
        appPreferences.setReadDataLengkap("yes");

        //toolbar back configuration, hard to explain, just ask to mr eki. In short, this is needed so the activity flows as eki wants
        ImageView backToolbar = findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataLengkapActivityPurna.this, PutusanFrontMenuKmg.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        //pantekan gimmick
//        gimmick = "14";

        //real gimmick
        gimmick = superData.getKodeGimmick();
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Lengkap");
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
        ReqDataLengkap req = new ReqDataLengkap( );
        req.setCif(cif);
        req.setIdAplikasi(String.valueOf(idAplikasi));


        //pantekan
//        req.setCif("81272");
//        req.setIdAplikasi("100997");

        Call<ParseResponse> call;

        //purna mikro atau purna konsumer


        //kalau kmg/kmg purna mikro
        if(superData.getLoanType().equalsIgnoreCase("429")||superData.getLoanType().equalsIgnoreCase("430")||superData.getLoanType().equalsIgnoreCase("317")||superData.getLoanType().equalsIgnoreCase("321")){
          call = apiClientAdapter.getApiInterface().inquiryDataLengkapKmgMikro(req);
        }

        //kalau kmg purna konsumer
        else{
           call = apiClientAdapter.getApiInterface().inquiryDataLengkapKonsumerKmg(req);
        }


        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            dataPribadiString = response.body().getData().get("nasabah").toString();
                            dataLengkap = gson.fromJson(dataPribadiString, DataLengkapKonsumerKmg.class);
                            stepperlayout.setAdapter(new DataLengkapStepAdapterPurna(getSupportFragmentManager(), DataLengkapActivityPurna.this, dataLengkap, gimmick), startingStepPosition );
                            stepperlayout.setListener(DataLengkapActivityPurna.this);
                        }
                        else{
                            AppUtil.notiferror(DataLengkapActivityPurna.this, findViewById(android.R.id.content), response.body().getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 2000);
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataLengkapActivityPurna.this, findViewById(android.R.id.content), error.getMessage());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 2000);
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(DataLengkapActivityPurna.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }




    @Override
    public void onCompleted(View completeButton) {
        Intent intent = new Intent(DataLengkapActivityPurna.this, PrescreeningActivity.class);

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
    public void onStepSelected(int newStepPosition) {
    }

    @Override
    public void onReturn() {
        finish();
    }


}
