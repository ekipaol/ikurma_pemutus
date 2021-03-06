package com.application.bris.brisi_pemutus.page_daftar_user.view;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.data_cabang.RequestDataCabang;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.config.user.DaftarUser;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_ao.Ao;
import com.application.bris.brisi_pemutus.model.user.User;
import com.application.bris.brisi_pemutus.page_daftar_user.adapters.AdapterStatusUser;
import com.application.bris.brisi_pemutus.util.AppUtil;
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

public class ActivityStatusUser extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

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
    ApiClientAdapter apiClientAdapter;


    Call<ParseResponse> call;
    private SearchView searchView;
    List<Ao> dataUser;
    AdapterStatusUser adapterUser;
    LinearLayoutManager layoutUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_user);
        main();
        initializeUser();
    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Status User");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
        apiClientAdapter = new ApiClientAdapter(this);


    }

    public void initializeUser(){
        //  dataUser = getListUser();
        final AppPreferences apppref=new AppPreferences(ActivityStatusUser.this);
        // progressbar_loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);
        RequestDataCabang req = new RequestDataCabang();
        req.setKodeCabang(apppref.getKodeCabang());

        //pantekan
//        req.setKodeCabang("0999909845");

        //conditioning list yang ditampilkan
        if(apppref.getFidRole().equalsIgnoreCase("79")){
            call = apiClientAdapter.getApiInterface().dataUh(req);
            req.setKodeCabang(apppref.getKodeCabang());
            req.setKodeSkk(apppref.getKodeSkk());

        }
        else if(apppref.getFidRole().equalsIgnoreCase("71")){
            call = apiClientAdapter.getApiInterface().dataAo(req);
            req.setKodeCabang(apppref.getKodeSkk());
        }
        else if(apppref.getFidRole().equalsIgnoreCase("76")){
            call = apiClientAdapter.getApiInterface().dataPincaLengkap(req);
            req.setKodeCabang(apppref.getKodeSkk());
            //pantekan
//        req.setKodeCabang("0999900108");
        }
        else if(apppref.getFidRole().equalsIgnoreCase("72")){
            call = apiClientAdapter.getApiInterface().dataMmm(req);
            req.setKodeCabang(apppref.getKodeSkk());
        }
        else{
            Toast.makeText(this, "Anda belum dapat mengakses halaman ini", Toast.LENGTH_SHORT).show();
        }


        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                // progressbar_loading.setVisibility(View.GONE);
                shimmer.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    if(response.body().getStatus().equalsIgnoreCase("00")){
                        if(apppref.getFidRole().equalsIgnoreCase("79")){
                            String listDataString = response.body().getData().get("listUh").toString();
                            String datalistDataString = response.body().getData().get("listUh").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Ao>>() {}.getType();
                            dataUser = gson.fromJson(listDataString, type);
                        }
                        else if(apppref.getFidRole().equalsIgnoreCase("71")){
                            String listDataStringAo = response.body().getData().get("listAom").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Ao>>() {}.getType();
                            dataUser = gson.fromJson(listDataStringAo, type);
                        }
                        else if(apppref.getFidRole().equalsIgnoreCase("76")){
                            String listDataStringAo = response.body().getData().get("listBawahanLangsung").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Ao>>() {}.getType();
                            dataUser = gson.fromJson(listDataStringAo, type);
                        }
                        else if(apppref.getFidRole().equalsIgnoreCase("72")){
                            String listDataStringAo = response.body().getData().get("listUh").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Ao>>() {}.getType();
                            dataUser = gson.fromJson(listDataStringAo, type);
                            adapterUser = new AdapterStatusUser(ActivityStatusUser.this, dataUser);
                        }
                        adapterUser = new AdapterStatusUser(ActivityStatusUser.this, dataUser);
                        rv_listuser.setLayoutManager(new LinearLayoutManager(ActivityStatusUser.this));
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
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    private List<User> getListUser(){
        List<User> user = new ArrayList<>();
        DaftarUser.listUser(this, user);

        return user;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        ActivityStatusUser.this.recreate();
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
