package com.application.bris.brisi_pemutus.page_putusan.agunan_retry;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import android.widget.TextView;


import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.agunan_terikat.ReqAgunanTerikat;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.agunan_terikat.AgunanTerikat;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.front_menu.PutusanFrontMenuKmg;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.scoring.ScoringKonsumerKmgActivity;
import com.application.bris.brisi_pemutus.page_konsumer_kpr.PutusanFrontMenuKpr;
import com.application.bris.brisi_pemutus.page_konsumer_kpr.scoring.ScoringKprActivity;
import com.application.bris.brisi_pemutus.page_putusan.PutusanFrontMenu;
import com.application.bris.brisi_pemutus.page_putusan.adapters.AgunanTerikatAdapater;
import com.application.bris.brisi_pemutus.page_putusan.scoring.ScoringActivity;
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

public class AgunanTerikatActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

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
    TextView textWhale;
    @BindView(R.id.bt_lanjut_agunan)
    Button bt_lanjut_agunan;

    @BindView(R.id.ll_shimmer)
    ShimmerFrameLayout shimmer;


    private SearchView searchView;
    List<AgunanTerikat> dataAgunan;

    AgunanTerikatAdapater adapaterAgunan;
    LinearLayoutManager layoutPipeline;
    ApiClientAdapter apiClientAdapter;

    AllDataFront superData;
    AppPreferences appPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agunan_terikat);

        superData=(AllDataFront)getIntent().getSerializableExtra("superData");
        backgroundStatusBar();
        initializeUser();
        //set sektor ekonomi as already read
        appPreferences = new AppPreferences(this);
        appPreferences.setReadAgunan("yes");

        bt_lanjut_agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("jenispembiayaan",superData.getJenisPembiayaan());
                if(superData.getJenisPembiayaan().equalsIgnoreCase("kmg")){
                    Intent intent=new Intent(AgunanTerikatActivity.this, ScoringKonsumerKmgActivity.class);
                    intent.putExtra("cif", Integer.parseInt(superData.getCif()));
                    intent.putExtra("idAplikasi", Integer.parseInt(superData.getIdAplikasi()));
                    intent.putExtra("superData",superData);
                    startActivity(intent);
                }
                else if(superData.getJenisPembiayaan().equalsIgnoreCase("kpr")){
                    Intent intent=new Intent(AgunanTerikatActivity.this, ScoringKprActivity.class);
                    intent.putExtra("cif", Integer.parseInt(superData.getCif()));
                    intent.putExtra("idAplikasi", Integer.parseInt(superData.getIdAplikasi()));
                    intent.putExtra("superData",superData);
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(AgunanTerikatActivity.this, ScoringActivity.class);
                    intent.putExtra("cif", Integer.parseInt(superData.getCif()));
                    intent.putExtra("idAplikasi", Integer.parseInt(superData.getIdAplikasi()));
                    intent.putExtra("superData",superData);
                    startActivity(intent);
                }


            }
        });

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
        searchView.setQueryHint("Cari Agunan");

        searchPipeline();

        return true;

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        AgunanTerikatActivity.this.recreate();
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




    public void initializeUser() {
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Agunan Terikat");
        ImageView backToolbar = findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(superData.getJenisPembiayaan().equalsIgnoreCase("kmg")){
                    Intent intent = new Intent(AgunanTerikatActivity.this, PutusanFrontMenuKmg.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
                else if(superData.getJenisPembiayaan().equalsIgnoreCase("kpr")){
                    Intent intent = new Intent(AgunanTerikatActivity.this, PutusanFrontMenuKpr.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(AgunanTerikatActivity.this, PutusanFrontMenu.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }

            }
        });
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
        apiClientAdapter = new ApiClientAdapter(AgunanTerikatActivity.this);
        shimmer.setVisibility(View.VISIBLE);
        ReqAgunanTerikat req = new ReqAgunanTerikat();
        AppPreferences appPreferences = new AppPreferences(AgunanTerikatActivity.this);
        req.setIdCif(getIntent().getStringExtra("cif"));
        req.setIdApl(getIntent().getStringExtra("idAplikasi"));

        //pantekan
//        req.setIdCif("261932");
//        req.setIdApl("206014");



        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listAgunan(req);

        //kondisi jika dari halaman riwayat, maka agunan ditampilkan lagi dengan middletier khusus -CATATAN, UDAH GAK PAKE MIDDLETIER KHUSUS LAGI COY, udah disamain semua dari list pengikatan bisa
        if(superData.getAsalHalaman().equalsIgnoreCase("riwayat")){
           call = apiClientAdapter.getApiInterface().listAgunan(req);
        }

        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                Log.d("status", String.valueOf(response.body().getStatus()));
                //progressbar_loading.setVisibility(View.GONE);
                shimmer.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    Log.d("status", String.valueOf(response.body().getStatus()));
                    Gson gson = new Gson();
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Type type = new TypeToken<List<AgunanTerikat>>() {
                        }.getType();

                        dataAgunan = gson.fromJson(listDataString, type);
                        adapaterAgunan = new AgunanTerikatAdapater(AgunanTerikatActivity.this, dataAgunan);
                        rv_listpipeline.setLayoutManager(new LinearLayoutManager(AgunanTerikatActivity.this));
                        rv_listpipeline.setItemAnimator(new DefaultItemAnimator());
                        rv_listpipeline.setAdapter(adapaterAgunan);



                        if (dataAgunan.size() == 0) {
                            whale.setVisibility(View.VISIBLE);
                            textWhale.setVisibility(View.VISIBLE);
                        } else {
                            whale.setVisibility(View.GONE);
                            textWhale.setVisibility(View.GONE);
                        }
                    } else if (response.body().getStatus().equalsIgnoreCase("01"))  {
                        whale.setVisibility(View.VISIBLE);
                        textWhale.setVisibility(View.VISIBLE);
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
                adapaterAgunan.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapaterAgunan.getFilter().filter(query);
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
        AgunanTerikatActivity.this.recreate();
    }


    }
