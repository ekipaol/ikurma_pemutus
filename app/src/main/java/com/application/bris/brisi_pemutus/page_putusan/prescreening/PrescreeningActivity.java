package com.application.bris.brisi_pemutus.page_putusan.prescreening;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.kelengkapan_dokumen.ReqKelengkapanDokumen;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.model.kelengkapan_dokumen.KelengkapanDokumen;
import com.application.bris.brisi_pemutus.model.prescreening.Prescreening;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityKelengkapanDokumen;
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

public class PrescreeningActivity extends AppCompatActivity{

ApiClientAdapter apiClientAdapter;
Prescreening dataPrescreening;

RelativeLayout loading;

    @BindView (R.id.tv_dhn)
    TextView tv_dhn;
    @BindView (R.id.tv_slik)
    TextView tv_slik;
    @BindView (R.id.tv_sikp)
    TextView tv_sikp;
    @BindView (R.id.tv_dukcapil)
    TextView tv_dukcapil;
    @BindView (R.id.ll_content_sikp)
    LinearLayout ll_content_sikp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_prescreening);
        loading=findViewById(R.id.progressbar_loading);
        apiClientAdapter=new ApiClientAdapter(this);
        ButterKnife.bind(this);
       // tv_dhn.setVisibility(View.VISIBLE);
        loadData();





        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Prescreening");
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void loadData(){
        loading.setVisibility(View.VISIBLE);
        //real data
        ReqKelengkapanDokumen req = new ReqKelengkapanDokumen();
        req.setId_aplikasi(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        //pantekan buat testing
//        ReqHistoryPutusan req=new ReqHistoryPutusan();
//                req.setCif(81857);
//        req.setId_aplikasi(101678);


        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryPrescreening(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){

                            String dataKelengkapanString = response.body().getData().get("prescreening").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<Prescreening>() {
                            }.getType();
                            dataPrescreening = gson.fromJson(dataKelengkapanString, Prescreening.class);
                            setData();


                        }
                        else{
                            AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
                AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                finish();
            }
        });


    }



    private void setData(){
        Log.d("jakiro",dataPrescreening.getDhn());
        if(dataPrescreening.getDhn().equalsIgnoreCase("true")){
            tv_dhn.setText("LOLOS");
            tv_dhn.setVisibility(View.VISIBLE);
        }

        if(dataPrescreening.getSikp().equalsIgnoreCase("true")){

            tv_sikp.setText("LOLOS");
            tv_sikp.setVisibility(View.VISIBLE);

        }


        if(dataPrescreening.getDukcapil().equalsIgnoreCase("true")){
            tv_dukcapil.setText("LOLOS");
            tv_dukcapil.setVisibility(View.VISIBLE);


        }

        if(dataPrescreening.getSlik().equalsIgnoreCase("true")){
            tv_slik.setText("LOLOS");
            tv_slik.setVisibility(View.VISIBLE);
            ll_content_sikp.setVisibility(View.VISIBLE);

        }
    }
}