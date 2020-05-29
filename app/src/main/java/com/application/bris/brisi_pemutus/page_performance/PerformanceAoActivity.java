package com.application.bris.brisi_pemutus.page_performance;

import android.app.SearchManager;
import android.content.Context;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.performance.ReqPerformanceAo;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.config.user.DaftarUser;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_ao.Ao;
import com.application.bris.brisi_pemutus.model.performance_ao.PerformanceAo;
import com.application.bris.brisi_pemutus.model.user.User;
import com.application.bris.brisi_pemutus.page_performance.adapters.AdapterPerformanceAo;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class PerformanceAoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.rv_list_performance_ao)
    RecyclerView rv_list_performance_ao;
    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.animWhale)
    LottieAnimationView whale;
    @BindView(R.id.tvWhale)
    TextView tvWhale;


    @BindView(R.id.tv_total_pencairan)
    TextView tv_total_pencairan;
    @BindView(R.id.tv_total_pipeline)
    TextView tv_total_pipeline;
    @BindView(R.id.tv_total_hotprospek)
    TextView tv_total_hotprospek;
    @BindView(R.id.tv_total_di_adp)
    TextView tv_total_di_adp;
    @BindView(R.id.tv_total_ditolak)
    TextView tv_total_ditolak;
    @BindView(R.id.tv_total_noa)
    TextView tv_total_noa;

    //sumary view components
    @BindView(R.id.bt_tampil_summary)
    Button bt_tampil_summary;
    @BindView(R.id.bt_sembunyi_summary)
    Button bt_sembunyi_summary;
    @BindView(R.id.ll_summary_performance)
    LinearLayout ll_summary_performance;



    @BindView(R.id.ll_shimmer)
    ShimmerFrameLayout shimmer;
    ApiClientAdapter apiClientAdapter;


    Call<ParseResponse> call;
    private SearchView searchView;
    List<PerformanceAo> dataUser;
    List<Ao> dataPerson;
    AdapterPerformanceAo adapterPerformanceAo;
    LinearLayoutManager layoutUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_ao);
        main();
        initializeUser();
    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Performance AO");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
        apiClientAdapter = new ApiClientAdapter(this);



        bt_tampil_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_summary_performance.setVisibility(View.VISIBLE);
                bt_tampil_summary.setVisibility(GONE);
                setMargins(swipeRefreshLayout,0,0,0,0);

            }
        });
        bt_sembunyi_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_summary_performance.setVisibility(GONE);
                bt_tampil_summary.setVisibility(View.VISIBLE);


                //margin adjustmen, because relative layout, so complicated matters to programmatically set margin
                setMargins(swipeRefreshLayout,0,60,0,0);

            }
        });


    }

    public void initializeUser(){
        //  dataUser = getListUser();
        final AppPreferences apppref=new AppPreferences(PerformanceAoActivity.this);
        // progressbar_loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);
        ReqPerformanceAo req = new ReqPerformanceAo();
        req.setKodeSkk(apppref.getKodeSkk());
        req.setRole(apppref.getFidRole());

        //conditioning list yang ditampilkan
            call = apiClientAdapter.getApiInterface().performanceAo(req);

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                // progressbar_loading.setVisibility(View.GONE);
                shimmer.setVisibility(GONE);
                if(response.isSuccessful()){

                    //tutorial overlay fitur sumary kalau data sukses
//                    AppUtil.tutorialOverlay(PerformanceAoActivity.this,bt_tampil_summary,"Sekarang anda dapat melihat summary performance pembiayaan","tutorial_summary_performance");

                    if(response.body().getStatus().equalsIgnoreCase("00")){

                            String listDataString = response.body().getData().get("listPerformanceAo").toString();
                            String listDataPersonnel=response.body().getData().get("listBawahanLangsung").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<PerformanceAo>>() {}.getType();
                        Type type2 = new TypeToken<List<Ao>>() {}.getType();
                            dataUser = gson.fromJson(listDataString, type);
                            dataPerson=gson.fromJson(listDataPersonnel, type2);

                        adapterPerformanceAo = new AdapterPerformanceAo(PerformanceAoActivity.this, dataUser,dataPerson);
                        rv_list_performance_ao.setLayoutManager(new LinearLayoutManager(PerformanceAoActivity.this));
                        rv_list_performance_ao.setItemAnimator(new DefaultItemAnimator());
                        rv_list_performance_ao.setAdapter(adapterPerformanceAo);
                        if(dataUser.size()==0){
                            whale.setVisibility(View.VISIBLE);
                            tvWhale.setVisibility(View.VISIBLE);
                        }
                        else{
                            whale.setVisibility(GONE);
                            tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                    else{
                        Toasty.info(PerformanceAoActivity.this,response.body().getMessage()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                Toasty.info(PerformanceAoActivity.this,"Terjadi Kesalahan Pada Server").show();
            }
        });
    }

    public void updateTotal(String pencairan,String pipeline,String hotprospek,String disetujui,String ditolak,String noa){
        tv_total_pencairan.setText(pencairan);
        tv_total_hotprospek.setText(hotprospek);
        tv_total_pipeline.setText(pipeline);
        tv_total_di_adp.setText(disetujui);
        tv_total_ditolak.setText(ditolak);
        tv_total_noa.setText(noa);
    }

    private List<User> getListUser(){
        List<User> user = new ArrayList<>();
        DaftarUser.listUser(this, user);

        return user;
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
        PerformanceAoActivity.this.recreate();
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
                adapterPerformanceAo.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapterPerformanceAo.getFilter().filter(query);
                    return false;
                }
                catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

}
