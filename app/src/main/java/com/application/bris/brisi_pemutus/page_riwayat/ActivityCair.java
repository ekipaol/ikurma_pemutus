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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.list_cair.ReqListCair;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.putusan_akad.PutusanAkad;
import com.application.bris.brisi_pemutus.page_riwayat.adapters.AdapterAkad;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
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

public class ActivityCair extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.rv_listakad)
    RecyclerView rv_listcair;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.animWhale)
    LottieAnimationView whale;
    @BindView(R.id.tvWhale)
    TextView tvWhale;
    @BindView(R.id.ll_shimmer)
    ShimmerFrameLayout shimmer;

    //filter objects

    @BindView(R.id.ll_filter_bulan_tahun)
    LinearLayout ll_filter_bulan_tahun;

    @BindView(R.id.ll_filter_pencairan_box)
    LinearLayout ll_filter_pencairan;

    @BindView(R.id.ll_info_filter)
    LinearLayout ll_info_filter;

    @BindView(R.id.sp_filter_bulan)
    MaterialSpinner sp_filter_bulan;

    @BindView(R.id.sp_filter_tahun)
    MaterialSpinner sp_filter_tahun;

    @BindView(R.id.tv_info_filter)
    TextView tv_info_filter;

    @BindView(R.id.bt_sembunyi_filter)
    Button bt_sembunyi_filter;

    @BindView(R.id.bt_tampil_filter)
    Button bt_tampil_filter;

    @BindView(R.id.bt_cari_filter)
    Button bt_cari_filter;

    int hideButtonClickIndicator=0;

    String tahunSekarang=Integer.toString(Calendar.getInstance().get(Calendar.YEAR));

    //calendar.getmonth mulai dari indeks 0, jadi tambah 1 untuk menampilkan bulans saat ini
    String bulanSekarang=String.format("%02d", (Calendar.getInstance().get(Calendar.MONTH)+1));

    String teksBulanSekarang;

    //end of filter objects


    private SearchView searchView;
    List<PutusanAkad> dataAkad;
    AdapterAkad adapterAkad;
    LinearLayoutManager layoutPipeline;
    ApiClientAdapter apiClientAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_cair);
        main();
        backgroundStatusBar();
        String kodePutusan=getIntent().getStringExtra("kodePutusan");
        Log.d("bulansekarang",bulanSekarang);
        initializeListCair(bulanSekarang,tahunSekarang);
        initializeSpinnerFilter();

        //button hide info pembiayaan

        bt_sembunyi_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("buttonindicator",Integer.toString(hideButtonClickIndicator));
                if(hideButtonClickIndicator==1){

                    //animation still fails, need more testing

//                    Animation slideUp = AnimationUtils.loadAnimation(CatatanActivity.this, R.anim.slide_up);
//                    cv_data_pembiayaan_catatan.startAnimation(slideUp);
                    ll_filter_pencairan.animate().translationY(0).setDuration(500);
                    ll_filter_bulan_tahun.setVisibility(View.GONE);
                    ll_info_filter.setVisibility(View.VISIBLE);

//                    cv_data_pembiayaan_catatan.setVisibility(View.GONE);
                    bt_sembunyi_filter.setVisibility(View.GONE);
                    bt_tampil_filter.setVisibility(View.VISIBLE);
                    hideButtonClickIndicator--;

                }

            }
        });

        bt_tampil_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(hideButtonClickIndicator==0){
                    ll_filter_bulan_tahun.setVisibility(View.VISIBLE);
                    ll_info_filter.setVisibility(View.GONE);
//                     ll_content_info.animate().translationY(ll_content_info.getHeight()).setDuration(500);
                    bt_tampil_filter.setVisibility(View.GONE);
                    bt_sembunyi_filter.setVisibility(View.VISIBLE);
                    hideButtonClickIndicator++;
                }
            }
        });

        bt_cari_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameBulan=null;
                boolean lolosValidasiBulan=false;
                boolean lolosValidasiTahun=false;
                if(sp_filter_bulan != null && sp_filter_bulan.getSelectedItem() !=null ) {
                    nameBulan = (String)sp_filter_bulan.getSelectedItem();
                } else  {
                    sp_filter_bulan.setError("Harap isi");
                }

                String nameTahun=null;
                if(sp_filter_tahun != null && sp_filter_tahun.getSelectedItem() !=null ) {
                    nameTahun = (String)sp_filter_tahun.getSelectedItem();
                } else  {
                    sp_filter_tahun.setError("Harap isi");

                }


                if(nameBulan!=null&&nameTahun!=null){
                    rv_listcair.setVisibility(View.GONE);
                    initializeListCair(getNomorBulan(sp_filter_bulan.getSelectedItem().toString()),sp_filter_tahun.getSelectedItem().toString());
                    Toasty.info(ActivityCair.this,"Menampilkan data pencairan "+sp_filter_bulan.getSelectedItem().toString()+" "+sp_filter_tahun.getSelectedItem().toString()).show();
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
        searchView.setQueryHint("Nama Nasabah, Produk, dll ..");

        searchPipeline();

        return true;

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ActivityCair.this.recreate();
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
        apiClientAdapter=new ApiClientAdapter(ActivityCair.this);
        AppUtil.toolbarRegular(this, "Daftar Pembiayaan Cair");
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

    public void initializeListCair(String bulan,String tahun) {
        //  dataUser = getListUser();
        //loading.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        ReqListCair req = new ReqListCair();
        AppPreferences appPreferences=new AppPreferences(ActivityCair.this);

        //pantekan
//        req.setUid("19230");

        //data real
        req.setUid(appPreferences.getUid());
        req.setBulanCair(bulan);
        req.setTahunCair(tahun);

        tv_info_filter.setText("Menampilkan data pencairan "+getTeksBulan(bulan)+" "+tahun);



        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listPutusanCair(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                loading.setVisibility(View.GONE);
//                shimmer.setVisibility(View.GONE);
//                shimmer.stopShimmer();
                rv_listcair.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<PutusanAkad>>() {
                        }.getType();

                        dataAkad = gson.fromJson(listDataString, type);
                        Log.d("akadActivity",listDataString);
                        adapterAkad = new AdapterAkad(ActivityCair.this, dataAkad);
                        rv_listcair.setLayoutManager(new LinearLayoutManager(ActivityCair.this));
                        rv_listcair.setItemAnimator(new DefaultItemAnimator());
                        rv_listcair.setAdapter(adapterAkad);
                        adapterAkad.notifyDataSetChanged();


                        if (dataAkad.size() == 0) {
                            rv_listcair.setVisibility(View.GONE);
                            whale.setVisibility(View.VISIBLE);
                            tvWhale.setVisibility(View.VISIBLE);
                        } else {
                            whale.setVisibility(View.GONE);
                            tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                    else if(response.body().getStatus().equalsIgnoreCase("01")){
                        rv_listcair.setVisibility(View.GONE);
                        whale.setVisibility(View.VISIBLE);
                        tvWhale.setVisibility(View.VISIBLE);
                    }
                    else{
//                        Toasty.error(ActivityCair.this,"Terjadi kesalahan");
                        AppUtil.notiferror(ActivityCair.this,findViewById(android.R.id.content),response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityCair.this,findViewById(android.R.id.content),t.getMessage());
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
        rv_listcair.setVisibility(View.GONE);
        initializeListCair(bulanSekarang,tahunSekarang);
//        ActivityCair.this.recreate();
    }

    private void initializeSpinnerFilter(){
        String[] Bulan = {"ALL","Januari","Februari","Maret","April","Mei","Juni","Juli","Agustus","September","Oktober","November","Desember"};
        ArrayList<String> Tahun = new ArrayList<>();

        //mulai tahun dari 2019, hingga tahun sekarang (future proof)
//        Tahun.add("2019");
        int tahunSekarang= Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 2019; i <=tahunSekarang ; i++) {
            Tahun.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapterBulan = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Bulan);
        ArrayAdapter<String> adapterTahun = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Tahun);
        adapterBulan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTahun.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_filter_bulan.setAdapter(adapterBulan);
        sp_filter_tahun.setAdapter(adapterTahun);
    }

    //convert angka bulan jadi nama, dipake untuk mengubah tv_info_filter berdasarkan bulan yang dipilih
    private String getTeksBulan(String nomorBulan){
        //tanpa break, karena menggunakan return, jadi tidak perlu di break-break

        switch(nomorBulan){
            case "":
                return "ALL";
            case "01":
                return "Januari";
            case "02":
                return "Februari";
            case "03":
                return "Maret";
            case "04":
                return "April";
            case "05":
                return "Mei";
            case "06":
                return "Juni";
            case "07":
                return "Juli";
            case "08":
                return "Agustus";
            case "09":
                return "September";
            case "10":
                return "Oktober";
            case "11":
                return "November";
            case "12":
                return "Desember";
            default:
                return "-";

        }

    }

    //convert teks bulan, jadi angka, dipake untuk convert data dari spinner bulan, jadi angka, untuk dikirim sebagai request

    private String getNomorBulan(String teksBulan){
        //tanpa break, karena menggunakan return, jadi tidak perlu di break-break

        switch(teksBulan){
            case "ALL":
                return "";
            case "Januari":
                return "01";
            case "Februari":
                return "02";
            case "Maret":
                return "03";
            case "April":
                return "04";
            case "Mei":
                return "05";
            case "Juni":
                return "06";
            case "Juli":
                return "07";
            case "Agustus":
                return "08";
            case "September":
                return "09";
            case "Oktober":
                return "10";
            case "November":
                return "11";
            case "Desember":
                return "12";
            default:
                return "-";

        }

    }
}

