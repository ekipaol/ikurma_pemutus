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
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.monitoring.ReqMonitoringNasabah;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.monitoring.NasabahMonitoring;
import com.application.bris.brisi_pemutus.model.monitoring.SummaryMonitoring;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;


public class MonitoringNasabahActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

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
    @BindView(R.id.tv_total_data)
    TextView tv_total_data;



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

//    @BindView(R.id.fb_advanced_search)
//    FloatingActionButton fb_advanced_search;

    //sumary view components
    @BindView(R.id.bt_tampil_summary)
    Button bt_tampil_summary;
    @BindView(R.id.bt_sembunyi_summary)
    Button bt_sembunyi_summary;
    @BindView(R.id.ll_summary_monitoring)
    LinearLayout ll_summary_monitoring;


    @BindView(R.id.tv_keterangan_summary)
    TextView tv_keterangan_summary;

    //bottom sheet
    @BindView(R.id.bottom_sheet)
    LinearLayout bottom_sheet;
    @BindView(R.id.iv_capsule_close)
    ImageView iv_capsule_close;
    @BindView(R.id.sp_filter_1)
    MaterialSpinner sp_filter_1;
    @BindView(R.id.sp_filter_2)
    MaterialSpinner sp_filter_2;
    @BindView(R.id.bt_cari_filter)
    Button bt_cari_filter;






    @BindView(R.id.ll_shimmer)
    ShimmerFrameLayout shimmer;
    ApiClientAdapter apiClientAdapter;
    
    int uid,no_pegawai;
    String namaPegawai;


    Call<ParseResponse> call;
    private SearchView searchView;
    List<NasabahMonitoring> dataNasabah;
    List<NasabahMonitoring> dataNasabahFiltered;
    SummaryMonitoring dataTotalMonitoring;
    AdapterMonitoringNasabah adapterMonitoringNasabah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring_nasabah);
        main();
        loadData();
    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
        apiClientAdapter = new ApiClientAdapter(this);
        
        uid=getIntent().getIntExtra("uid",0);
        no_pegawai=getIntent().getIntExtra("noPegawai",0);
        namaPegawai=getIntent().getStringExtra("namaPegawai");

        AppUtil.toolbarRegular(this, "Nasabah "+namaPegawai.toUpperCase());

        tv_keterangan_summary.setText("Summary Pencapaian AO : "+namaPegawai);

//        initializeSpinnerFilter();





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
                setMargins(swipeRefreshLayout,0,20,0,0);

            }
        });

        //bottom sheet activities
        final BottomSheetBehavior behaviorBottomSheet=BottomSheetBehavior.from(bottom_sheet);

//        fb_advanced_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
//            }
//        });

        iv_capsule_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });


    }

    public void loadData(){
        //  dataKcp = getListUser();
        final AppPreferences apppref=new AppPreferences(MonitoringNasabahActivity.this);
        // progressbar_loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);
        ReqMonitoringNasabah req = new ReqMonitoringNasabah();
        req.setUid(uid);
        req.setNoPegawai(no_pegawai);

        //conditioning list yang ditampilkan
        call = apiClientAdapter.getApiInterface().listMonitoringNasabah(req);

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                // progressbar_loading.setVisibility(View.GONE);
                shimmer.setVisibility(GONE);
                if(response.isSuccessful()){

                    //tutorial overlay fitur sumary kalau data sukses
//                    AppUtil.tutorialOverlay(PerformanceAoActivity.this,bt_tampil_summary,"Sekarang anda dapat melihat summary performance pembiayaan","tutorial_summary_performance");

                    if(response.body().getStatus().equalsIgnoreCase("00")){

                        String listDataString = response.body().getData().get("listNasabah").toString();
                        String listTotalString = response.body().getData().get("summary").toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<NasabahMonitoring>>() {}.getType();
                        Type type2 = new TypeToken<SummaryMonitoring>() {}.getType();
                        dataNasabah = gson.fromJson(listDataString, type);
                        dataTotalMonitoring = gson.fromJson(listTotalString, type2);


                        dataNasabahFiltered=dataNasabah;

                        adapterMonitoringNasabah = new AdapterMonitoringNasabah(MonitoringNasabahActivity.this, dataNasabahFiltered);
                        rv_list_monitoring_pencairan.setLayoutManager(new LinearLayoutManager(MonitoringNasabahActivity.this));
                        rv_list_monitoring_pencairan.setItemAnimator(new DefaultItemAnimator());
                        rv_list_monitoring_pencairan.setAdapter(adapterMonitoringNasabah);

                        if(dataTotalMonitoring!=null){
                            setData();
                        }
                        else{
                            AppUtil.notiferror(MonitoringNasabahActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan pada data Summary");
                        }


                        if(dataNasabah.size()==0){
                            whale.setVisibility(View.VISIBLE);
                            tvWhale.setVisibility(View.VISIBLE);
                        }
                        else{
                            whale.setVisibility(GONE);
                            tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                    else{
                        AppUtil.notiferror(MonitoringNasabahActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        whale.setVisibility(View.VISIBLE);
                        tvWhale.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                AppUtil.notiferror(MonitoringNasabahActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan pada server");
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
        MonitoringNasabahActivity.this.recreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nama Nasabah, Kol, Produk, Tanggal Jatuh Tempo dll ..");
        searchPipeline();

        return true;

    }

    private void searchPipeline(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterMonitoringNasabah.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapterMonitoringNasabah.getFilter().filter(query);
                    return false;
                }
                catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    private void initializeSpinnerFilter(){
        String[] pencarian1 = {"Produk","Kol","Tanggal Jatuh Tempo"};
        ArrayList<String> pencarian2 = new ArrayList<>();


        ArrayAdapter<String> adapterPencarian1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pencarian1);
        adapterPencarian1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_filter_1.setAdapter(adapterPencarian1);


        sp_filter_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    pencarian2.clear();
                    pencarian2.add("Mikro");
                    pencarian2.add("Multi faedah");
                    pencarian2.add("Griya Faedah");
                    pencarian2.add("Purna Faedah");
                    pencarian2.add("Oto Faedah");

                }
                else{
                    pencarian2.clear();
                    pencarian2.add("Belum Tersedia");
                }
                ArrayAdapter<String> adapterPencarian2 = new ArrayAdapter<String>(MonitoringNasabahActivity.this, android.R.layout.simple_spinner_item, pencarian2);
                adapterPencarian2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                sp_filter_2.setAdapter(adapterPencarian2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //default spinner selector
        sp_filter_1.setSelection(1);


        //click listener for button cari


    }

    public void updateTotalData(String jumlahData){
        tv_total_data.setText("Total Data : "+jumlahData);
        tv_total_data.setVisibility(View.VISIBLE);
    }

}