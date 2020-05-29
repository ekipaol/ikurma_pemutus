package com.application.bris.brisi_pemutus.page_putusan.history;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.history_putusan.ReqHistoryPutusan;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.history_fasilitas.HistoryFasilitas;
import com.application.bris.brisi_pemutus.page_putusan.adapters.AdapterHistory;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//GAK DIPAKE

public class NotUsedActivityHistory extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.rv_listuser)
    RecyclerView rv_listuser;
    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.animWhale)
    LottieAnimationView whale;
    @BindView(R.id.tvWhale)
    TextView tvWhale;
    @BindView(R.id.ll_shimmer)
    ShimmerFrameLayout shimmer;


    private SearchView searchView;
    List<HistoryFasilitas> dataUser;
    AdapterHistory adapterUser;
    LinearLayoutManager layoutUser;
    private ApiClientAdapter apiClientAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        apiClientAdapter = new ApiClientAdapter(this);
        main();
        initializeUser();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        NotUsedActivityHistory.this.recreate();
    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "History Aplikasi");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);

    }

    public void initializeUser(){
        //  dataUser = getListUser();
        AppPreferences apppref=new AppPreferences(NotUsedActivityHistory.this);
        // progressbar_loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);
        ReqHistoryPutusan req = new ReqHistoryPutusan();
       req.setCif(Integer.parseInt(getIntent().getStringExtra("cif")));
       req.setId_aplikasi(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));

        //pantekan untuk testing
//        req.setCif(81857);
//        req.setId_aplikasi(101678);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryHistory(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                // progressbar_loading.setVisibility(View.GONE);
                shimmer.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    if(response.body().getStatus().equalsIgnoreCase("00")){
                        String listDataString = response.body().getData().get("historyFasilitas").toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<HistoryFasilitas>>() {}.getType();

                        dataUser = gson.fromJson(listDataString, type);
                        adapterUser = new AdapterHistory(NotUsedActivityHistory.this, dataUser);
                        rv_listuser.setLayoutManager(new LinearLayoutManager(NotUsedActivityHistory.this));
                        rv_listuser.setItemAnimator(new DefaultItemAnimator());
                        rv_listuser.setAdapter(adapterUser);
                        if(dataUser.size()==0){
                            whale.setVisibility(View.VISIBLE);
                            tvWhale.setVisibility(View.VISIBLE);
                        }
                        else{
                            whale.setVisibility(View.GONE);
                            tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
            }
        });



    }



    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        NotUsedActivityHistory.this.recreate();
    }




}
