package com.application.bris.brisi_pemutus.page_daftar_user.view;

import android.app.SearchManager;
import android.content.Context;
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
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.data_cabang.RequestDataCabang;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.config.user.DaftarUser;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_ao.Ao;
import com.application.bris.brisi_pemutus.model.user.User;
import com.application.bris.brisi_pemutus.page_daftar_user.adapters.AdapterMaintenanceUser;
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

public class MaintenanceUserActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

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
    ApiClientAdapter apiClientAdapter;
    private SearchView searchView;
    List<Ao> dataUser;
    AdapterMaintenanceUser adapterUser;
    LinearLayoutManager layoutUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_user);
        main();
        initializeUser();

    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Maintenance User");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
        apiClientAdapter = new ApiClientAdapter(this);

    }

    public void initializeUser(){
        AppPreferences apppref=new AppPreferences(MaintenanceUserActivity.this);
        //progressbar_loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);
        RequestDataCabang req = new RequestDataCabang();
        if(apppref.getFidRole().equalsIgnoreCase("79")){
            call = apiClientAdapter.getApiInterface().dataUh(req);
            req.setKodeCabang(apppref.getKodeSkk());
        }
        else if(apppref.getFidRole().equalsIgnoreCase("71")){
            call = apiClientAdapter.getApiInterface().dataAo(req);
            req.setKodeCabang(apppref.getKodeCabang());
        }
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
              //  progressbar_loading.setVisibility(View.GONE);
                shimmer.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    if(response.body().getStatus().equalsIgnoreCase("00")){
                        String listDataString = response.body().getData().get("listUh").toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Ao>>() {}.getType();

                        dataUser = gson.fromJson(listDataString, type);
                        List<Ao> dataUserAktif=new ArrayList<>();
                        for (int i = 0; i <dataUser.size() ; i++) {
                            if(dataUser.get(i).getStatus().equalsIgnoreCase("aktif")){
                                dataUserAktif.add(dataUser.get(i));

                            }
                        }
                        adapterUser = new AdapterMaintenanceUser(MaintenanceUserActivity.this, dataUserAktif);
                        rv_listuser.setLayoutManager(new LinearLayoutManager(MaintenanceUserActivity.this));
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
        List<User> userAktif=new ArrayList<>();
        for (int i = 0; i < user.size(); i++) {
            if(user.get(i).getStatus().equalsIgnoreCase("aktif")){
                userAktif.add(user.get(i));
            }
        }
        return userAktif;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        MaintenanceUserActivity.this.recreate();
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
