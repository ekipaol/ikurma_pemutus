package com.application.bris.brisi_pemutus.page_ao_silang;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.model.ao_silang.AoSilang;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

public class AoSilangActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

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


    Call<ParseResponse> call;
    private SearchView searchView;
    List<AoSilang> dataUser;
    AdapterAoSilang adapterUser;
    LinearLayoutManager layoutUser;
    ApiClientAdapter apiClientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_user);
        main();
        //simulasi initialize user
        progressbar_loading.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressbar_loading.setVisibility(View.GONE);
               initializeUserSimulated();
            }
        }, 3500);

        //initialize beneran
//        initializeUser();

    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Daftar AO Silang");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
        apiClientAdapter = new ApiClientAdapter(this);

    }

    public void initializeUserSimulated(){

        dataUser=new ArrayList<>();

        AoSilang ao1=new AoSilang();
        AoSilang ao2=new AoSilang();
        AoSilang ao3=new AoSilang();
        AoSilang ao4=new AoSilang();
        AoSilang ao5=new AoSilang();
        AoSilang ao6=new AoSilang();

        ao1.setNamaAoSilang("Eki Yusandhi");
        ao1.setNamaKantorAoSilang("KCP Serang");
        ao1.setNamaPartnerAoSilang("Virminsih");
        ao1.setNamaKantorPartnerAoSilang("KC Sukabumi");
        ao1.setNikAoSilang(Integer.toString(300 +  (int)(Math.random()*(2282))));
        ao1.setNikPartnerAoSilang(Integer.toString(300 +  (int)(Math.random()*(2282))));

        ao2.setNamaAoSilang("Muhammad Irfan");
        ao2.setNamaKantorAoSilang("KC Medan Ahmad Yani");
        ao2.setNamaPartnerAoSilang("Hadwi Fauzi");
        ao2.setNamaKantorPartnerAoSilang("KCP Petisah");
        ao2.setNikAoSilang(Integer.toString(300 +  (int)(Math.random()*(2282))));
        ao2.setNikPartnerAoSilang(Integer.toString(300 +  (int)(Math.random()*(2282))));

        ao3.setNamaAoSilang("Cut Dara Maghfirah");
        ao3.setNamaKantorAoSilang("KCP Lhoeknga");
        ao3.setNamaPartnerAoSilang("Diah Putri Ariyani");
        ao3.setNamaKantorPartnerAoSilang("KC Aceh Darussalam");
        ao2.setNikAoSilang(Integer.toString(300 +  (int)(Math.random()*(2282))));
        ao2.setNikPartnerAoSilang(Integer.toString(300 +  (int)(Math.random()*(2282))));

        ao4.setNamaAoSilang("Fitria Adine");
        ao4.setNamaKantorAoSilang("KCP Lhoeknga");
        ao4.setNamaPartnerAoSilang("Diah Putri Ariyani");
        ao4.setNamaKantorPartnerAoSilang("KC Aceh Darussalam");
        ao4.setNikAoSilang(Integer.toString(300 +  (int)(Math.random()*(2282))));
        ao4.setNikPartnerAoSilang(Integer.toString(300 +  (int)(Math.random()*(2282))));

        ao5.setNamaAoSilang("Mutia Irani");
        ao5.setNamaKantorAoSilang("KC Bojongsoang");
        ao5.setNamaPartnerAoSilang("Eki Yusandhi Iskandar");
        ao5.setNamaKantorPartnerAoSilang("KCP Serang");
        ao5.setNikAoSilang(Integer.toString(300 +  (int)(Math.random()*(2282))));
        ao5.setNikPartnerAoSilang(Integer.toString(300 +  (int)(Math.random()*(2282))));

        ao6.setNamaAoSilang("Nisrina Sari");
        ao6.setNamaKantorAoSilang("KCP Medan Gatsu");
        ao6.setNamaPartnerAoSilang("Muhammad Irfan");
        ao6.setNamaKantorPartnerAoSilang("KC Medan Ahmad Yani");
        ao6.setNikAoSilang(Integer.toString(300 +  (int)(Math.random()*(2282))));
        ao6.setNikPartnerAoSilang(Integer.toString(300 +  (int)(Math.random()*(2282))));

        dataUser.add(ao1);
        dataUser.add(ao2);
        dataUser.add(ao3);
        dataUser.add(ao4);
        dataUser.add(ao5);
        dataUser.add(ao6);

        adapterUser = new AdapterAoSilang(AoSilangActivity.this, dataUser);
                        rv_listuser.setLayoutManager(new LinearLayoutManager(AoSilangActivity.this));
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

    public void initializeUser(){
//        final AppPreferences apppref=new AppPreferences(AoSilangActivity.this);
//        // progressbar_loading.setVisibility(View.VISIBLE);
//        shimmer.setVisibility(View.VISIBLE);
////        RequestDataCabang req = new RequestDataCabang();
////        //conditioning list yang ditampilkan
////
////        call = apiClientAdapter.getApiInterface().dataMmm(req);
////        req.setKodeCabang(apppref.getKodeSkk());getKodeSkk
//
//        call.enqueue(new Callback<ParseResponse>() {
//
//            @Override
//            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
//                // progressbar_loading.setVisibility(View.GONE);
//                shimmer.setVisibility(View.GONE);
//                if(response.isSuccessful()){
//                    if(response.body().getStatus().equalsIgnoreCase("00")){
//                        if(apppref.getFidRole().equalsIgnoreCase("79")){
//                            String listDataString = response.body().getData().get("listUh").toString();
//                            Gson gson = new Gson();
//                            Type type = new TypeToken<List<AoSilang>>() {}.getType();
//                            dataUser = gson.fromJson(listDataString, type);
//
//                        }
//
//                        else if(apppref.getFidRole().equalsIgnoreCase("76")){
//                            String listDataString = response.body().getData().get("listBawahanLangsung").toString();
//                            Gson gson = new Gson();
//                            Type type = new TypeToken<List<AoSilang>>() {}.getType();
//                            dataUser = gson.fromJson(listDataString, type);
//
//                        }
//                        List<AoSilang> dataUserAktif=new ArrayList<>();
//                        for (int i = 0; i <dataUser.size() ; i++) {
//                            if(dataUser.get(i).getStatus().equalsIgnoreCase("aktif")){
//                                dataUserAktif.add(dataUser.get(i));
//
//                            }
//                        }
//                        adapterUser = new AdapterReactivePassword(AoSilangActivity.this, dataUserAktif);
//                        rv_listuser.setLayoutManager(new LinearLayoutManager(AoSilangActivity.this));
//                        rv_listuser.setItemAnimator(new DefaultItemAnimator());
//                        rv_listuser.setAdapter(adapterUser);
//                        if(dataUser.size()==0){
//                            whale.setVisibility(View.VISIBLE);
//                            tvWhale.setVisibility(View.VISIBLE);
//                        }
//                        else{
//                            whale.setVisibility(View.GONE);
//                            tvWhale.setVisibility(View.INVISIBLE);
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ParseResponse> call, Throwable t) {
//                Log.d("LOG D", t.getMessage());
//            }
//        });
    }



    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        AoSilangActivity.this.recreate();
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
                adapterUser.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapterUser.getFilter().filter(query);
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