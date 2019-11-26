package com.application.bris.brisi_pemutus.page_ambil_alih;

import android.app.SearchManager;
import android.content.Context;
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
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.ambil_alih.ReqAmbilAlih;
import com.application.bris.brisi_pemutus.api.model.request.data_cabang.RequestDataCabang;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_ao.Ao;
import com.application.bris.brisi_pemutus.model.user_ambil_alih.UserAmbilAlih;
import com.application.bris.brisi_pemutus.page_ambil_alih.adapter.AdapterDaftarAmbilAlih;
import com.application.bris.brisi_pemutus.page_daftar_user.adapters.AdapterDaftarUser;
import com.application.bris.brisi_pemutus.page_daftar_user.view.UserActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
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

public class AmbilAlihActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.rv_listuser_ambil_alih)
    RecyclerView rv_listuser;
    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.animWhale)
    LottieAnimationView whale;
    @BindView(R.id.tvWhale)
    TextView tvWhale;
    @BindView(R.id.rl_whale)
    RelativeLayout rl_whale;
    @BindView(R.id.tb_regular)
    Toolbar tb_regular;

    ApiClientAdapter apiClientAdapter;
    Call<ParseResponse> call;
    List<Ao> dataUser;
    List<UserAmbilAlih> dataUserAmbilAlih;
    AdapterDaftarAmbilAlih adapterDaftarAmbilAlih;


    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambil_alih);

        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Ambil Alih Putusan");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);

        apiClientAdapter=new ApiClientAdapter(this);

        initializeUser();

    }

    public void initializeUser(){

        final AppPreferences apppref=new AppPreferences(AmbilAlihActivity.this);
        progressbar_loading.setVisibility(View.VISIBLE);


        //khusus m3, menggunakan service lain, karena ada perbedaan struktur data di database, jadi tidak bisa diambil dari service untuk daftar user
        if(apppref.getFidRole().equalsIgnoreCase("72")){
            ReqAmbilAlih reqAmbilAlih=new ReqAmbilAlih();
            reqAmbilAlih.setKodeSkk(apppref.getKodeSkk());
            reqAmbilAlih.setKodeKanwil(apppref.getKodeKanwil());
            reqAmbilAlih.setFidRole(apppref.getFidRole());
            call = apiClientAdapter.getApiInterface().listAmbilAlih(reqAmbilAlih);
            call.enqueue(new Callback<ParseResponse>() {
                @Override
                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                    // progressbar_loading.setVisibility(View.GONE);

                    progressbar_loading.setVisibility(View.GONE);

                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){

                                String listDataString = response.body().getData().get("listUser").toString();
                                Gson gson = new Gson();
                                Type type = new TypeToken<List<UserAmbilAlih>>() {}.getType();
                                dataUserAmbilAlih = gson.fromJson(listDataString, type);


                                if (dataUserAmbilAlih.size() == 0) {
//                                whale.setVisibility(View.VISIBLE);
//                                tvWhale.setVisibility(View.VISIBLE);
                                    rl_whale.setVisibility(View.VISIBLE);
                                    rv_listuser.setVisibility(View.GONE);
                                } else {
//                                whale.setVisibility(View.GONE);
//                                tvWhale.setVisibility(View.INVISIBLE);
//                                    Log.d("dataambilalihactivity",Integer.toString(dataUserAmbilAlih.size()));
                                    rl_whale.setVisibility(View.GONE);
                                    adapterDaftarAmbilAlih = new AdapterDaftarAmbilAlih(AmbilAlihActivity.this, dataUserAmbilAlih);
                                    adapterDaftarAmbilAlih.notifyDataSetChanged();

                                }


                            rv_listuser.setLayoutManager(new LinearLayoutManager(AmbilAlihActivity.this));
                            rv_listuser.setItemAnimator(new DefaultItemAnimator());
                            rv_listuser.setAdapter(adapterDaftarAmbilAlih);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ParseResponse> call, Throwable t) {
                    Log.d("LOG D", t.getMessage());
                }
            });
        }

        else {

            RequestDataCabang req = new RequestDataCabang();
            req.setKodeCabang(apppref.getKodeCabang());

            //conditioning list yang ditampilkan

            if(apppref.getFidRole().equalsIgnoreCase("79")){ //pincapem
                call = apiClientAdapter.getApiInterface().dataUh(req);
                req.setKodeSkk(apppref.getKodeSkk());
            }
            else if(apppref.getFidRole().equalsIgnoreCase("76")){ //pinca
                call = apiClientAdapter.getApiInterface().dataPincaLengkap(req);
                req.setKodeCabang(apppref.getKodeSkk());
            }
            else if(apppref.getFidRole().equalsIgnoreCase("72")){//mmm
                call = apiClientAdapter.getApiInterface().dataPincaLengkap(req);
                req.setKodeCabang(apppref.getKodeSkk());
            }
            else{
                Toast.makeText(this, "Anda belum dapat mengakses halaman ini", Toast.LENGTH_SHORT).show();
            }

            call.enqueue(new Callback<ParseResponse>() {
                @Override
                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                    // progressbar_loading.setVisibility(View.GONE);

                    progressbar_loading.setVisibility(View.GONE);

                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            if (apppref.getFidRole().equalsIgnoreCase("79")) {
                                String listDataString = response.body().getData().get("listUh").toString();
                                Gson gson = new Gson();
                                Type type = new TypeToken<List<Ao>>() {
                                }.getType();
                                dataUser = gson.fromJson(listDataString, type);

                                if (dataUser.size() == 0) {
//                                whale.setVisibility(View.VISIBLE);
//                                tvWhale.setVisibility(View.VISIBLE);
                                    rl_whale.setVisibility(View.VISIBLE);
                                    rv_listuser.setVisibility(View.GONE);
                                } else {
//                                whale.setVisibility(View.GONE);
//                                tvWhale.setVisibility(View.INVISIBLE);
                                    rl_whale.setVisibility(View.GONE);
                                    adapterDaftarAmbilAlih = new AdapterDaftarAmbilAlih(AmbilAlihActivity.this, dataUser, apppref
                                            .getFidRole());
                                    adapterDaftarAmbilAlih.notifyDataSetChanged();
                                }
                            } else if (apppref.getFidRole().equalsIgnoreCase("76") || apppref.getFidRole().equalsIgnoreCase("72")) {
                                String listDataStringAo = response.body().getData().get("listBawahanLangsung").toString();
                                List<Ao> dataFilter = new ArrayList<>();
                                Gson gson = new Gson();
                                Type type = new TypeToken<List<Ao>>() {
                                }.getType();
                                dataUser = gson.fromJson(listDataStringAo, type);


                                if (dataUser.size() == 0) {
//                                whale.setVisibility(View.VISIBLE);
//                                tvWhale.setVisibility(View.VISIBLE);
                                    rl_whale.setVisibility(View.VISIBLE);
                                    rv_listuser.setVisibility(View.GONE);
                                } else {
                                    rv_listuser.setVisibility(View.VISIBLE);
                                    rl_whale.setVisibility(View.GONE);
//                                whale.setVisibility(View.GONE);
//                                tvWhale.setVisibility(View.INVISIBLE);
                                    adapterDaftarAmbilAlih = new AdapterDaftarAmbilAlih(AmbilAlihActivity.this, dataUser, apppref.getFidRole());
                                    adapterDaftarAmbilAlih.notifyDataSetChanged();
                                }
                            }


                            rv_listuser.setLayoutManager(new LinearLayoutManager(AmbilAlihActivity.this));
                            rv_listuser.setItemAnimator(new DefaultItemAnimator());
                            rv_listuser.setAdapter(adapterDaftarAmbilAlih);
                        }
                    }

                }

                @Override
                public void onFailure(Call<ParseResponse> call, Throwable t) {
                    Log.d("LOG D", t.getMessage());
                }
            });

        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        AmbilAlihActivity.this.recreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Cari dengan nama, jabatan, kantor, dll");

        search();

        return true;

    }

    private void search(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterDaftarAmbilAlih.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapterDaftarAmbilAlih.getFilter().filter(query);
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
