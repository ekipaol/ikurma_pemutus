package com.application.bris.brisi_pemutus.page_konsumer_kpr.scoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.Error;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseError;
import com.application.bris.brisi_pemutus.api.model.request.scoring.ReqScoring;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.kelengkapan_dokumen.KelengkapanDokumenFlpp;
import com.application.bris.brisi_pemutus.model.scoring.ScoringKonsumerKmg;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.front_menu.PutusanFrontMenuKmg;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.kelengkapan_dokumen.KelengkapanDokumenKonsumerKmgActivity;
import com.application.bris.brisi_pemutus.page_konsumer_kpr.PutusanFrontMenuKpr;
import com.application.bris.brisi_pemutus.page_konsumer_kpr.kelengkapan_dokumen.KelengkapanDokumenFlppActivity;
import com.application.bris.brisi_pemutus.page_konsumer_kpr.kelengkapan_dokumen.KelengkapanDokumenKprActivity;
import com.application.bris.brisi_pemutus.page_konsumer_purna.page_konsumer_purna.kelengkapan_dokumen.KelengkapanDokumenPrapurnaActivity;
import com.application.bris.brisi_pemutus.page_konsumer_purna.page_konsumer_purna.kelengkapan_dokumen.KelengkapanDokumenPurnaActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScoringKprActivity extends AppCompatActivity{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    @BindView(R.id.tv_skor_penilaian_risiko)
    TextView tv_skor_penilaian_risiko;
    @BindView(R.id.tv_grade_skoring)
    TextView tv_grade_skoring;
    @BindView(R.id.tv_definisi)
    TextView tv_definisi;
    @BindView(R.id.tv_kesimpulan)
    TextView tv_kesimpulan;
    @BindView(R.id.sv_rpc)
    ScrollView sv_rpc;
    @BindView(R.id.btn_selesai)
    Button btn_selesai;

    private int idAplikasi;
    private String cif;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataString;
    private ScoringKonsumerKmg data;
    private AllDataFront superData;
    Call<ParseResponse> call;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoring_konsumer_kmg_activity);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);

        appPreferences=new AppPreferences(this);
        //set data scoring as already read
        superData= (AllDataFront)getIntent().getSerializableExtra("superData");
        appPreferences.setReadScoring("yes");
        idAplikasi = Integer.parseInt(superData.getIdAplikasi());
        cif = superData.getCif();
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Scoring");
        ImageView backToolbar = findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoringKprActivity.this, PutusanFrontMenuKpr.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

            }
        });

        btn_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;

                //purna kmg
                if(superData.getKodeGimmick().equalsIgnoreCase("222")){
                    intent = new Intent(ScoringKprActivity.this, KelengkapanDokumenFlppActivity.class);
                }
                else{
                    intent = new Intent(ScoringKprActivity.this, KelengkapanDokumenKprActivity.class);
                }


                //real data
                intent.putExtra("idAplikasi", Integer.parseInt(superData.getIdAplikasi()));
                intent.putExtra("superData",superData);

                //pantekan
//                intent.putExtra("idAplikasi", "101694");

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }


    private void loadData() {
        loading.setVisibility(View.VISIBLE);
        ReqScoring req = new ReqScoring(); //101928


        //pantekan
//        req.setIdAplikasi(101928);
//        req.setCif(81862);
//        Toasty.info(ScoringKprActivity.this,"Id aplikasi masih hardcode").show();

        req.setIdAplikasi(idAplikasi);
        req.setCif(Integer.parseInt(cif));

            call = apiClientAdapter.getApiInterface().updateScoringKpr(req);

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {


                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            dataString = response.body().getData().toString();
                            data = gson.fromJson(dataString, ScoringKonsumerKmg.class);

                            tv_skor_penilaian_risiko.setText(data.getScore());
                            tv_definisi.setText(data.getDesc());
                            tv_grade_skoring.setText(data.getGrade());
                            tv_kesimpulan.setText(data.getKesimpulan());

//                            sv_rpc.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    sv_rpc.fullScroll(ScrollView.FOCUS_DOWN);
//                                }
//                            });
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ScoringKprActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(ScoringKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }
}
