package com.application.bris.brisi_pemutus.page_ranking;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.EmptyRequest;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.ranking_cabang.RankingCabang;
import com.application.bris.brisi_pemutus.page_disposisi.adapter.AdapterDaftarDisposisi;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
