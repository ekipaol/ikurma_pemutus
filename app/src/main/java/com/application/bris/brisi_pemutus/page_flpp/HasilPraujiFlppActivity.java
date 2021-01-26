package com.application.bris.brisi_pemutus.page_flpp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.monitoring.ReqMonitoringNasabah;
import com.application.bris.brisi_pemutus.api.model.request.putusan_pemutus.ReqUid;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.flpp.HasilPraujiFlpp;
import com.application.bris.brisi_pemutus.model.monitoring.NasabahMonitoring;
import com.application.bris.brisi_pemutus.model.monitoring.SummaryMonitoring;
import com.application.bris.brisi_pemutus.page_monitoring.AdapterMonitoringNasabah;
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

import static android.view.View.GONE;

public class HasilPraujiFlppActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.rv_list_prauji_flpp)
    RecyclerView rv_list_prauji_flpp;
    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.animWhale)
    ImageView whale;
    @BindView(R.id.tvWhale)
    TextView tvWhale;


    @BindView(R.id.ll_shimmer)
    ShimmerFrameLayout shimmer;
    ApiClientAdapter apiClientAdapter;

    Call<ParseResponseArr> call;
    private SearchView searchView;
    List<HasilPraujiFlpp> dataHasilPraujiFlpp;
    AdapterHasilPraujiFlpp adapterpraujiFlpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_prauji_flpp);
        main();
        loadData();
    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
        apiClientAdapter = new ApiClientAdapter(this);



        AppUtil.toolbarRegular(this, "Hasil Pra Uji FLPP ");





    }

    public void loadData(){
        //  dataKcp = getListUser();
        final AppPreferences apppref=new AppPreferences(HasilPraujiFlppActivity.this);
        // progressbar_loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);
        ReqUid req = new ReqUid();
        req.setUid(apppref.getUid());


        //conditioning list yang ditampilkan
        call = apiClientAdapter.getApiInterface().listHasilPraujiFlpp(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                // progressbar_loading.setVisibility(View.GONE);
                shimmer.setVisibility(GONE);
                if(response.isSuccessful()){

                    //tutorial overlay fitur sumary kalau data sukses
//                    AppUtil.tutorialOverlay(PerformanceAoActivity.this,bt_tampil_summary,"Sekarang anda dapat melihat summary performance pembiayaan","tutorial_summary_performance");

                    if(response.body().getStatus().equalsIgnoreCase("00")){

                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<HasilPraujiFlpp>>() {}.getType();

                        dataHasilPraujiFlpp = gson.fromJson(listDataString, type);

                        adapterpraujiFlpp = new AdapterHasilPraujiFlpp(HasilPraujiFlppActivity.this, dataHasilPraujiFlpp);
                        rv_list_prauji_flpp.setLayoutManager(new LinearLayoutManager(HasilPraujiFlppActivity.this));
                        rv_list_prauji_flpp.setItemAnimator(new DefaultItemAnimator());
                        rv_list_prauji_flpp.setAdapter(adapterpraujiFlpp);


                        if(dataHasilPraujiFlpp.size()==0){
                            whale.setVisibility(View.VISIBLE);
                            tvWhale.setVisibility(View.VISIBLE);
                        }
                        else{
                            whale.setVisibility(GONE);
                            tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                    else{
                        AppUtil.notiferror(HasilPraujiFlppActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        whale.setVisibility(View.VISIBLE);
                        tvWhale.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                AppUtil.notiferror(HasilPraujiFlppActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan pada server");
            }
        });
    }

    private void setData(){

    }



    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        HasilPraujiFlppActivity.this.recreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nama Nasabah, Nama Pihak Ketiga, Nama Perumahan, dll ..");
        searchPipeline();

        return true;

    }

    private void searchPipeline(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterpraujiFlpp.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapterpraujiFlpp.getFilter().filter(query);
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
    protected void onResume() {
        super.onResume();
        loadData();
    }
}