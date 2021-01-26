package com.application.bris.brisi_pemutus.page_riwayat;


import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
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
import com.application.bris.brisi_pemutus.api.model.request.putusan_pemutus.ReqUid;
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

public class ActivityDitolak extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.rv_listakad)
    RecyclerView rv_listditolak;
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
        initializeListDitolak();


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
        ActivityDitolak.this.recreate();
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
        apiClientAdapter=new ApiClientAdapter(ActivityDitolak.this);
        AppUtil.toolbarRegular(this, "Daftar Pembiayaan Ditolak");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
    }

    public void initializePipelineHome(){
//        dataPipeline = getListPipelineHome();
//        adapterAkad = new PutusanAdapter(this, dataPipeline);
//        rv_listakad.setLayoutManager(new LinearLayoutManager(PutusanActivity.this));
//        rv_listakad.setItemAnimator(new DefaultItemAnimator());
//        rv_listakad.setAdapter(adapterAkad);
    }

    public void initializeListDitolak() {
        //  dataUser = getListUser();
        //loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);
        ReqUid req = new ReqUid();
        AppPreferences appPreferences=new AppPreferences(ActivityDitolak.this);

        //pantekan
//        req.setUid("19230");

        //data real
        req.setUid(appPreferences.getUid());



        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listPutusanDitolak(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                //loading.setVisibility(View.GONE);
                shimmer.setVisibility(View.GONE);
                rv_listditolak.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<PutusanAkad>>() {
                        }.getType();

                        dataAkad = gson.fromJson(listDataString, type);
                        Log.d("akadActivity",listDataString);
                        adapterAkad = new AdapterAkad(ActivityDitolak.this, dataAkad);
                        rv_listditolak.setLayoutManager(new LinearLayoutManager(ActivityDitolak.this));
                        rv_listditolak.setItemAnimator(new DefaultItemAnimator());
                        rv_listditolak.setAdapter(adapterAkad);


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
                        Toasty.error(ActivityDitolak.this,"Terjadi kesalahan");
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
        swipeRefreshLayout.setRefreshing(false);
        shimmer.setVisibility(View.VISIBLE);
        shimmer.startShimmer();
        rv_listditolak.setVisibility(View.GONE);
        initializeListDitolak();
//        ActivityDitolak.this.recreate();
    }
}
