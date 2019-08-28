package com.application.bris.brisi_pemutus.page_putusan.agunan_kendaraan;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseError;
import com.application.bris.brisi_pemutus.api.model.request.agunan_global_idapl_agunan_cif.AgunanGlobal;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.agunan_kendaraan.AgunanKendaraan;
import com.application.bris.brisi_pemutus.page_putusan.adapters.StepAdapterAgunanKendaraan;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgunanKendaraanActivity extends AppCompatActivity implements StepperLayout.StepperListener {

    @BindView(R.id.stepperlayout)
    StepperLayout stepperlayout;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_back)
    ImageView btn_back;

    private static final String CURRENT_STEP_POSITION_KEY = "position";
    private String cif, idAplikasi, loan_type, tp_produk, idAgunan;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    private int startingStepPosition;
    private AgunanKendaraan dataAgunan;
    private String id_agunan_response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_datalengkap);
        ButterKnife.bind(this);
//        apiClientAdapter = new ApiClientAdapter(AgunanTanahBangunanInputActivity.this);
        appPreferences = new AppPreferences(this);
        apiClientAdapter = new ApiClientAdapter(this);

        backgroundStatusBar();
        AppUtil.toolbarRegular(AgunanKendaraanActivity.this, "Agunan Kendaraan");


        dataAgunan = new AgunanKendaraan();

        if (getIntent().hasExtra("cif")) {
            cif = getIntent().getStringExtra("cif");
        }

        idAplikasi = getIntent().getStringExtra("idAplikasi");
        loan_type = getIntent().getStringExtra("loan_type");
        tp_produk = getIntent().getStringExtra("tp_produk");
//        Log.d("Cek tp_produk", tp_produk);
        idAgunan = getIntent().getStringExtra("idAgunan");
//        Log.d("Cek idAplikasi", idAplikasi);
//        Log.d("Cek idAgunan", idAgunan);
        getData();


//        if (!idAgunan.equalsIgnoreCase("0")) {
//            getData();
//        } else {
//            startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
//            stepperlayout.setAdapter(new StepAdapterAgunanKendaraan(getSupportFragmentManager(), AgunanKendaraanActivity.this, dataAgunan, idAgunan, loan_type, tp_produk), startingStepPosition );
//            stepperlayout.setListener(AgunanKendaraanActivity.this);
//        }
    }

    private void getData() {
        AgunanGlobal req = new AgunanGlobal();

        //pantekan
//        req.setIdApl(101678);
//        req.setIdAgunan(13370);
//        req.setIdCif(81857);

        //real data
        req.setIdApl(Integer.parseInt(idAplikasi));
        req.setIdAgunan(Integer.parseInt(idAgunan));
        req.setIdCif(Integer.parseInt(cif));

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryAgunanKendaraan(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataAgunan = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<AgunanKendaraan>() {}.getType();

                            dataAgunan = gson.fromJson(listDataAgunan, type);


                            stepperlayout.setAdapter(new StepAdapterAgunanKendaraan(getSupportFragmentManager(), AgunanKendaraanActivity.this, dataAgunan, idAgunan, loan_type, tp_produk), startingStepPosition );
                            stepperlayout.setListener(AgunanKendaraanActivity.this);

                        } else{
                            AppUtil.notiferror(AgunanKendaraanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{

                        AppUtil.notiferror(AgunanKendaraanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("Throwable", t.toString());
                AppUtil.notiferror(AgunanKendaraanActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
            }
        });
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
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
        finish();

    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }




}
