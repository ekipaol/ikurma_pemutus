package com.application.bris.brisi_pemutus.page_monitoring;

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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.EmptyRequest;
import com.application.bris.brisi_pemutus.api.model.request.req_kode_skk.ReqKodeSkk;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.monitoring.KcMonitoring;
import com.application.bris.brisi_pemutus.model.monitoring.KcpMonitoring;
import com.application.bris.brisi_pemutus.model.monitoring.SummaryMonitoring;
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

public class MonitoringKcActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.rv_list_monitoring_pencairan)
    RecyclerView rv_list_monitoring_pencairan;
    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.animWhale)
    ImageView whale;
    @BindView(R.id.tvWhale)
    TextView tvWhale;



    @BindView(R.id.tv_pencapaian_pencairan)
    TextView tv_pencapaian_pencairan;
    @BindView(R.id.tv_pencapaian_os)
    TextView tv_pencapaian_os;
    @BindView(R.id.tv_pencapaian_dpk)
    TextView tv_pencapaian_dpk;
    @BindView(R.id.tv_total_kol_2)
    TextView tv_total_kol_2;
    @BindView(R.id.tv_total_npf)
    TextView tv_total_npf;

    @BindView(R.id.tv_keterangan_summary)
    TextView tv_keterangan_summary;

//    @BindView(R.id.fb_advanced_search)
//    FloatingActionButton fb_advanced_search;

    //sumary view components
    @BindView(R.id.bt_tampil_summary)
    Button bt_tampil_summary;
    @BindView(R.id.bt_sembunyi_summary)
    Button bt_sembunyi_summary;
    @BindView(R.id.ll_summary_monitoring)
    LinearLayout ll_summary_monitoring;



    @BindView(R.id.ll_shimmer)
    ShimmerFrameLayout shimmer;
    ApiClientAdapter apiClientAdapter;
    AppPreferences apppref;



    Call<ParseResponse> call;
    private SearchView searchView;
    List<KcMonitoring> dataCabang;
    SummaryMonitoring dataTotalMonitoring;
    AdapterMonitoringKc adapterMonitoringKcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring_pencairan);
        main();
        loadData();


    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        apppref=new AppPreferences(this);
        AppUtil.toolbarRegular(this, "Monitoring "+apppref.getNamaKantor().toUpperCase());
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
        apiClientAdapter = new ApiClientAdapter(this);


        otherViewChanges();




        bt_tampil_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_summary_monitoring.setVisibility(View.VISIBLE);
                bt_tampil_summary.setVisibility(GONE);
                setMargins(swipeRefreshLayout,0,0,0,0);

            }
        });
        bt_sembunyi_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_summary_monitoring.setVisibility(GONE);
                bt_tampil_summary.setVisibility(View.VISIBLE);


                //margin adjustmen, because relative layout, so complicated matters to programmatically set margin
                setMargins(swipeRefreshLayout,0,60,0,0);

            }
        });


    }



    public void loadData(){
        //  dataKcp = getListUser();
        // progressbar_loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);


        //conditioning list yang ditampilkan
        call = apiClientAdapter.getApiInterface().listMonitoringKp(new EmptyRequest());

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                // progressbar_loading.setVisibility(View.GONE);
                shimmer.setVisibility(GONE);
                if(response.isSuccessful()){

                    //tutorial overlay fitur sumary kalau data sukses
//                    AppUtil.tutorialOverlay(PerformanceAoActivity.this,bt_tampil_summary,"Sekarang anda dapat melihat summary performance pembiayaan","tutorial_summary_performance");

                    if(response.body().getStatus().equalsIgnoreCase("00")){

                        String listDataString = response.body().getData().get("listKC").toString();
//                        String listTotalString = response.body().getData().get("summary").toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<KcMonitoring>>() {}.getType();
                        Type type2 = new TypeToken<SummaryMonitoring>() {}.getType();
                        dataCabang = gson.fromJson(listDataString, type);
//                        dataTotalMonitoring = gson.fromJson(listTotalString, type2);


                        adapterMonitoringKcp = new AdapterMonitoringKc(MonitoringKcActivity.this, dataCabang);
                        rv_list_monitoring_pencairan.setLayoutManager(new LinearLayoutManager(MonitoringKcActivity.this));
                        rv_list_monitoring_pencairan.setItemAnimator(new DefaultItemAnimator());
                        rv_list_monitoring_pencairan.setAdapter(adapterMonitoringKcp);

                        if(dataTotalMonitoring!=null){
                            setData();
                        }
                        else{
//                            AppUtil.notiferror(MonitoringKcActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan pada data Summary");
                        }


                        if(dataCabang.size()==0){
                            whale.setVisibility(View.VISIBLE);
                            tvWhale.setVisibility(View.VISIBLE);
                        }
                        else{
                            whale.setVisibility(GONE);
                            tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                    else{
                        AppUtil.notiferror(MonitoringKcActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                AppUtil.notiferror(MonitoringKcActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan pada server");
            }
        });
    }

    private void setData(){
        tv_pencapaian_os.setText(dataTotalMonitoring.getTotalOs()+"%");
        tv_pencapaian_dpk.setText(dataTotalMonitoring.getTotalDpk()+"%");
        tv_pencapaian_pencairan.setText(dataTotalMonitoring.getTotalPencairan()+"%");
        tv_total_kol_2.setText(dataTotalMonitoring.getTotalKol2()+"%");
        tv_total_npf.setText(dataTotalMonitoring.getTotalNpf()+"%");
    }




    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        MonitoringKcActivity.this.recreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nama User, Jabatan, Kantor, dll ..");

        searchPipeline();

        return true;

    }

    private void searchPipeline(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterMonitoringKcp.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapterMonitoringKcp.getFilter().filter(query);
                    return false;
                }
                catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    private void otherViewChanges(){

        tv_keterangan_summary.setText("Summary Pencapaian : "+apppref.getNamaKantor());
//        fb_advanced_search.setVisibility(GONE);
    }

}