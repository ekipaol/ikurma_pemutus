package com.application.bris.brisi_pemutus.page_ranking;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.EmptyRequest;
import com.application.bris.brisi_pemutus.api.model.request.history_putusan.ReqHistoryPutusan;
import com.application.bris.brisi_pemutus.api.model.request.list_disposisi.ReqListDisposisi;
import com.application.bris.brisi_pemutus.api.model.request.putusan_pemutus.ReqSetujuPutusan;
import com.application.bris.brisi_pemutus.api.model.request.req_kode_skk.ReqKodeSkk;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.baseapp.RouteApp;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.cs_model.CsModel;
import com.application.bris.brisi_pemutus.model.disposisi.Disposisi;
import com.application.bris.brisi_pemutus.model.history_catatan.HistoryCatatan;
import com.application.bris.brisi_pemutus.model.info_cs_pencairan.InfoCs;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.model.ranking_cabang.RankingCabang;
import com.application.bris.brisi_pemutus.page_disposisi.adapter.AdapterDaftarDisposisi;
import com.application.bris.brisi_pemutus.page_putusan.PutusanFrontMenu;
import com.application.bris.brisi_pemutus.page_putusan.adapters.HistoryCatatanAdapter;
import com.application.bris.brisi_pemutus.page_putusan.agunan_retry.AgunanTerikatActivity;
import com.application.bris.brisi_pemutus.page_putusan.data_lengkap.ActivityDataLengkap;
import com.application.bris.brisi_pemutus.page_putusan.history.HistoryActivity;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityKelengkapanDokumen;
import com.application.bris.brisi_pemutus.page_putusan.lkn.LknActivity;
import com.application.bris.brisi_pemutus.page_putusan.prescreening.PrescreeningActivity;
import com.application.bris.brisi_pemutus.page_putusan.rpc.RpcActivity;
import com.application.bris.brisi_pemutus.page_putusan.scoring.ScoringActivity;
import com.application.bris.brisi_pemutus.page_putusan.sektor_ekonomi.SektorEkonomiActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.view.corelayout.CoreLayoutActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class RankingActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;

    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
//    @BindView(R.id.animWhale)
//    LottieAnimationView whale;
//    @BindView(R.id.tvWhale)
//    TextView tvWhale;

    @BindView(R.id.tv_firstplace_amount)
    TextView tv_firstplace_amount;
    @BindView(R.id.et_nama_1st_place)
    TextView et_nama_1st_place;
    @BindView(R.id.tv_secondplace_amount)
    TextView tv_secondplace_amount;
    @BindView(R.id.et_nama_2nd_place)
    TextView et_nama_2nd_place;
    @BindView(R.id.tv_thirdplace_amount)
    TextView tv_thirdplace_amount;
    @BindView(R.id.et_nama_3rd_place)
    TextView et_nama_3rd_place;

    @BindView(R.id.tv_top1_regional_name)
    TextView tv_top1_regional_name;
    @BindView(R.id.tv_top2_regional_name)
    TextView tv_top2_regional_name;
    @BindView(R.id.tv_top3_regional_name)
    TextView tv_top3_regional_name;

    @BindView(R.id.tv_top1_regional_amount)
    TextView tv_top1_regional_amount;
    @BindView(R.id.tv_top2_regional_amount)
    TextView tv_top2_regional_amount;
    @BindView(R.id.tv_top3_regional_amount)
    TextView tv_top3_regional_amount;


    private ApiClientAdapter apiClientAdapter ;


    private SearchView searchView;
    List<RankingCabang> dataCabangRanking;
    AdapterDaftarDisposisi adapterDisposisi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        apiClientAdapter= new ApiClientAdapter(this);
        main();
        getDataRankingSelindo();




    }

    public void main(){
//        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Ranking");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);


    }

    //AMBIL TOP 3 SELINDO
    public void getDataRankingSelindo() {
        //  dataUser = getListUser();
        //progressbar_loading.setVisibility(View.VISIBLE);
//        shimmer.setVisibility(View.VISIBLE);
        AppPreferences appPreferences=new AppPreferences(this);



        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().getRankingTotal(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<RankingCabang>>() {
                        }.getType();

                        dataCabangRanking = gson.fromJson(response.body().getData(), type);

                        et_nama_1st_place.setText("1. "+dataCabangRanking.get(0).getCABANG());
                        et_nama_2nd_place.setText("2. "+dataCabangRanking.get(1).getCABANG());
                        et_nama_3rd_place.setText("3. "+dataCabangRanking.get(2).getCABANG());


                        tv_firstplace_amount.setText(AppUtil.parseRupiah(dataCabangRanking.get(0).getAMOUNT()));
                        tv_secondplace_amount.setText(AppUtil.parseRupiah(dataCabangRanking.get(1).getAMOUNT()));
                        tv_thirdplace_amount.setText(AppUtil.parseRupiah(dataCabangRanking.get(2).getAMOUNT()));

                        //KARNA MASIH ROLLOUT JABODETABEK, JADI LIST JABODETABEK LANGSUNG DIMASUKKAN DISINI, TETAPI HARUSNYA ADA REQUEST BARU KHUSUS UNTUK REGIONAL TERTENTU.

                        tv_top1_regional_name.setText(dataCabangRanking.get(0).getCABANG());
                        tv_top2_regional_name.setText(dataCabangRanking.get(1).getCABANG());
                        tv_top3_regional_name.setText(dataCabangRanking.get(2).getCABANG());

                        tv_top1_regional_amount.setText(AppUtil.parseRupiah(dataCabangRanking.get(0).getAMOUNT()));
                        tv_top2_regional_amount.setText(AppUtil.parseRupiah(dataCabangRanking.get(1).getAMOUNT()));
                        tv_top3_regional_amount.setText(AppUtil.parseRupiah(dataCabangRanking.get(2).getAMOUNT()));


                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
            }
        });
    }
    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        RankingActivity.this.recreate();
    }



    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
}
