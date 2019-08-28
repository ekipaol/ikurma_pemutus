package com.application.bris.brisi_pemutus.page_aktivasi;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseError;
import com.application.bris.brisi_pemutus.api.model.request.aktivasi.Aktivasi;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.baseapp.RouteApp;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.page_login.view.LoginActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.Constants;
import com.google.gson.JsonObject;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityWelcome extends AppCompatActivity implements StepperLayout.StepperListener {

    @BindView(R.id.stepperlayout)
    StepperLayout stepperlayout;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    private static final String CURRENT_STEP_POSITION_KEY = "position";
    private int startingStepPosition;
    private IntentResult intentResult;
    private ApiClientAdapter apiClientAdapter;
    private RouteApp routeApp;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this, 0);
        routeApp = new RouteApp(this);
        appPreferences = new AppPreferences(this);
        startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
        stepperlayout.setAdapter(new WelcomeStepAdapter(getSupportFragmentManager(), ActivityWelcome.this), startingStepPosition );
        stepperlayout.setListener(ActivityWelcome.this);
        backgroundStatusBar();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void openQRScanner(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setCaptureActivity(ScanActivity.class).initiateScan();
    }

    @Override
    public void onCompleted(View view) {
        openQRScanner();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String qrcontent = "";
        if (intentResult != null) {
            if (intentResult.getContents() != null){
                qrcontent = intentResult.getContents();
                processScan(qrcontent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void processScan(String code){
        loading.setVisibility(View.VISIBLE);
        Aktivasi activationRequest = new Aktivasi(code, getDeviceId(), getDeviceName(), "BRISI_PEMUTUS");
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().activation(activationRequest);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        setPrefActivation(response.body().getData());
                        Toasty.success(ActivityWelcome.this,"Aktivasi berhasil, silahkan login").show();
                        routeApp.openActivity(LoginActivity.class);
//                        AppUtil.notifsuccess(ActivityWelcome.this, findViewById(android.R.id.content), "Aktivasi Sukses, Enjoy BRISI");
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                routeApp.openActivity(LoginActivity.class);
//                            }
//                        }, 1000);
                    } else {
                        AppUtil.notiferror(ActivityWelcome.this, findViewById(android.R.id.content), response.body().getMessage());

                        //belum bisa buka appel QR, jadi ketika error occured tetap masuk ke halaman login, uncomment apputil diatas after done with everything

                        //delete this when all is good
//                        appPreferences.setIsActivated("1");
//                        Toasty.success(ActivityWelcome.this,"Aktivasi berhasil, silahkan login").show();
//
//                        routeApp.openActivityAndClearAllPrevious(LoginActivity.class);
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                routeApp.openActivityAndClearAllPrevious(LoginActivity.class);
//                            }
//                        }, 1000);
                        //end of delete


                    }
                } else {
//                    Error error = ParseResponseError.confirmEror(response.errorBody());
//                    AppUtil.notiferror(ActivityWelcome.this, findViewById(android.R.id.content), error.getMessage());
                    AppUtil.notiferror(ActivityWelcome.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityWelcome.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));


            }
        });
    }

    public void setPrefActivation(JsonObject data){
        appPreferences.setIsActivated("1");
        appPreferences.setUsername(AppUtil.encrypt(data.get("username").getAsString()));
        appPreferences.setNama(AppUtil.encrypt(data.get("namaLengkap").getAsString()));
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

    private String getDeviceId() {
        HashMap<String, String> deviceInfo = AppUtil.getDeviceInfo(this);
        String deviceId = deviceInfo.get(Constants.DEVICE_ID);
        return deviceId;
    }

    private String getDeviceName() {
        HashMap<String, String> deviceInfo = AppUtil.getDeviceInfo(this);
        String deviceName = deviceInfo.get(Constants.DEVICE_NAME);
        return deviceName;
    }
}