package com.application.bris.brisi_pemutus.page_aom.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.putusan_pemutus.ReqUid;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.config.pipelinehome.Pipelinehome;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.model.pipeline.pipeline;
import com.application.bris.brisi_pemutus.page_aom.adapter.pipeline.PutusanAdapter;
import com.application.bris.brisi_pemutus.page_putusan.menu.MenuDaftarPutusanActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.view.corelayout.CoreLayoutActivity;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PutusanActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.rv_listpipeline)
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
    List<Putusan> dataHotProspek;
    PutusanAdapter adapaterPipeline;
    LinearLayoutManager layoutPipeline;
    ApiClientAdapter apiClientAdapter;
    String kodePutusan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_pipeline);
        main();
        backgroundStatusBar();
         kodePutusan=getIntent().getStringExtra("kodePutusan");
        if(kodePutusan!=null&&kodePutusan.equalsIgnoreCase("putusanDeviasi")){
            initializeUserDeviasi();
        }
        else{
            initializeUser();
        }

        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(PutusanActivity.this, MenuDaftarPutusanActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                    startActivity(intent);



            }
        });




    }


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
        PutusanActivity.this.recreate();
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
        apiClientAdapter=new ApiClientAdapter(PutusanActivity.this);
        AppUtil.toolbarRegular(this, "Daftar Pembiayaan");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
    }

    public void initializePipelineHome(){
//        dataPipeline = getListPipelineHome();
//        adapaterPipeline = new PutusanAdapter(this, dataPipeline);
//        rv_listpipeline.setLayoutManager(new LinearLayoutManager(PutusanActivity.this));
//        rv_listpipeline.setItemAnimator(new DefaultItemAnimator());
//        rv_listpipeline.setAdapter(adapaterPipeline);
    }

    public void initializeUser() {
        //  dataUser = getListUser();
        //progressbar_loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);
        ReqUid req = new ReqUid();
        AppPreferences appPreferences=new AppPreferences(PutusanActivity.this);
        req.setUid(appPreferences.getUid());
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listPemutus(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                //progressbar_loading.setVisibility(View.GONE);
                shimmer.setVisibility(View.GONE);
                rv_listpipeline.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Putusan>>() {
                        }.getType();

                        dataHotProspek = gson.fromJson(listDataString, type);
                        adapaterPipeline = new PutusanAdapter(PutusanActivity.this, dataHotProspek);
                        rv_listpipeline.setLayoutManager(new LinearLayoutManager(PutusanActivity.this));
                        rv_listpipeline.setItemAnimator(new DefaultItemAnimator());
                        rv_listpipeline.setAdapter(adapaterPipeline);


                        if (dataHotProspek.size() == 0) {
                            whale.setVisibility(View.VISIBLE);
                            tvWhale.setVisibility(View.VISIBLE);
                        } else {
                            whale.setVisibility(View.GONE);
                            tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    public void initializeUserDeviasi() {
        //  dataUser = getListUser();
        //progressbar_loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);
        ReqUid req = new ReqUid();
        AppPreferences appPreferences=new AppPreferences(PutusanActivity.this);
        req.setUid(appPreferences.getUid());

        //ganti jadi list pemutus deviasi jika sudah ada middletier
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listDeviasi(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                //progressbar_loading.setVisibility(View.GONE);
                rv_listpipeline.setVisibility(View.VISIBLE);
                shimmer.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Putusan>>() {
                        }.getType();

                        dataHotProspek = gson.fromJson(listDataString, type);
                        adapaterPipeline = new PutusanAdapter(PutusanActivity.this, dataHotProspek);
                        rv_listpipeline.setLayoutManager(new LinearLayoutManager(PutusanActivity.this));
                        rv_listpipeline.setItemAnimator(new DefaultItemAnimator());
                        rv_listpipeline.setAdapter(adapaterPipeline);


                        if (dataHotProspek.size() == 0) {
                            whale.setVisibility(View.VISIBLE);
                            tvWhale.setVisibility(View.VISIBLE);
                        } else {
                            whale.setVisibility(View.GONE);
                            tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
            }
        });
    }




    private List<pipeline> getListPipelineHome(){
        List<pipeline> pipelines = new ArrayList<>();
        Pipelinehome.listPipeline(this, pipelines);
        return pipelines;
    }

    private void searchPipeline(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapaterPipeline.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapaterPipeline.getFilter().filter(query);
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
//        progressbar_loading.setVisibility(View.VISIBLE);
        rv_listpipeline.setVisibility(View.GONE);
        if(kodePutusan!=null&&kodePutusan.equalsIgnoreCase("putusanDeviasi")){
            initializeUserDeviasi();
        }
        else{
            initializeUser();
        }
//        PutusanActivity.this.recreate();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent=new Intent(PutusanActivity.this, CoreLayoutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        startActivity(intent);
    }


}
