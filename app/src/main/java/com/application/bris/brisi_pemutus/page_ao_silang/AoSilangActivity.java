package com.application.bris.brisi_pemutus.page_ao_silang;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.putusan_pemutus.ReqUid;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.ao_silang.AoSilang;
import com.application.bris.brisi_pemutus.model.detailHotprospek.DetailHotprospekKpr;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.view.corelayout.CoreLayoutActivity;
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
    List<DetailHotprospekKpr> dataAppraisal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_user);
        main();
        //simulasi initialize user
//        progressbar_loading.setVisibility(View.VISIBLE);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                progressbar_loading.setVisibility(View.GONE);
//               initializeUserSimulated();
//            }
//        }, 3500);

        //initialize beneran
        initializeUser();

        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AoSilangActivity.this, CoreLayoutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                startActivity(intent);



            }
        });

    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Daftar Permintaan Appraisal");
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

//        adapterUser = new AdapterAoSilang(AoSilangActivity.this, dataUser);
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

    }

    public void initializeUser(){
        final AppPreferences apppref=new AppPreferences(AoSilangActivity.this);

        //pantekan token
//        apppref.setToken("123123123");
//        Toast.makeText(this, "ada pantekan token", Toast.LENGTH_SHORT).show();

        // progressbar_loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);
        ReqUid req = new ReqUid();
//        //conditioning list yang ditampilkan
//
        call = apiClientAdapter.getApiInterface().listAppraisal(req);
        req.setUid(apppref.getUid());
//        Toast.makeText(this, "Ada pantekan uid", Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<ParseResponse>() {

            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                // progressbar_loading.setVisibility(View.GONE);
                shimmer.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listAppraisalString = response.body().getData().get("listPermintaanApraisal").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<DetailHotprospekKpr>>() {}.getType();
                            dataAppraisal = gson.fromJson(listAppraisalString, type);


//
                        adapterUser = new AdapterAoSilang(AoSilangActivity.this, dataAppraisal);
                        rv_listuser.setLayoutManager(new LinearLayoutManager(AoSilangActivity.this));
                        rv_listuser.setItemAnimator(new DefaultItemAnimator());
                        rv_listuser.setAdapter(adapterUser);
                        if(dataAppraisal.size()==0){
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



    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        shimmer.setVisibility(View.VISIBLE);
//        progressbar_loading.setVisibility(View.VISIBLE);
        rv_listuser.setVisibility(View.GONE);
            initializeUser();

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

    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent=new Intent(AoSilangActivity.this, CoreLayoutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        startActivity(intent);
    }
}