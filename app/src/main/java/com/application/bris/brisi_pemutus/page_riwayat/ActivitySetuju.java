package com.application.bris.brisi_pemutus.page_riwayat;


import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.putusan_pemutus.ReqPutusan;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.putusan_akad.PutusanAkad;
import com.application.bris.brisi_pemutus.page_riwayat.adapters.AdapterAkad;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySetuju extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.rv_listakad)
    RecyclerView rv_listpipeline;
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
    List<PutusanAkad> dataAkad;
    AdapterAkad adapterAkad;
    LinearLayoutManager layoutPipeline;
    ApiClientAdapter apiClientAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_akad);
        main();
        backgroundStatusBar();
        String kodePutusan=getIntent().getStringExtra("kodePutusan");
        initializeUser();


    }

    @Override
    public void onBackPressed() { finish(); }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nama Nasabah, Produk, dll ..");

        searchPipeline();

        return true;

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ActivitySetuju.this.recreate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        apiClientAdapter=new ApiClientAdapter(ActivitySetuju.this);
        AppUtil.toolbarRegular(this, "Daftar Pembiayaan Disetujui");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
    }

    public void initializePipelineHome(){
//        dataPipeline = getListPipelineHome();
//        adapterAkad = new PutusanAdapter(this, dataPipeline);
//        rv_listpipeline.setLayoutManager(new LinearLayoutManager(PutusanActivity.this));
//        rv_listpipeline.setItemAnimator(new DefaultItemAnimator());
//        rv_listpipeline.setAdapter(adapterAkad);
    }

    public void initializeUser() {
        //  dataUser = getListUser();
        //progressbar_loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);
        ReqPutusan req = new ReqPutusan();
        AppPreferences appPreferences=new AppPreferences(ActivitySetuju.this);

        //pantekan
//        req.setUid("19230");

        //data real
        req.setUid(appPreferences.getUid());



        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listPutusanSetuju(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                //progressbar_loading.setVisibility(View.GONE);
                shimmer.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<PutusanAkad>>() {
                        }.getType();

                        dataAkad = gson.fromJson(listDataString, type);
                        Log.d("akadActivity",listDataString);
                        adapterAkad = new AdapterAkad(ActivitySetuju.this, dataAkad);
                        rv_listpipeline.setLayoutManager(new LinearLayoutManager(ActivitySetuju.this));
                        rv_listpipeline.setItemAnimator(new DefaultItemAnimator());
                        rv_listpipeline.setAdapter(adapterAkad);


                        if (dataAkad.size() == 0) {
                            whale.setVisibility(View.VISIBLE);
                            tvWhale.setVisibility(View.VISIBLE);
                        } else {
                            whale.setVisibility(View.GONE);
                            tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                    else if(response.body().getStatus().equalsIgnoreCase("01")){
                        whale.setVisibility(View.VISIBLE);
                        tvWhale.setVisibility(View.VISIBLE);
                    }
                    else{
                        Toasty.error(ActivitySetuju.this,"Terjadi kesalahan");
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
            }
        });
    }



    private void searchPipeline(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterAkad.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapterAkad.getFilter().filter(query);
                    return false;
                }
                catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        ActivitySetuju.this.recreate();
    }
}
