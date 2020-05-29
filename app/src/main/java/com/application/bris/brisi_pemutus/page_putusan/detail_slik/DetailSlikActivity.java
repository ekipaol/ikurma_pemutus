package com.application.bris.brisi_pemutus.page_putusan.detail_slik;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.id_aplikasi.ReqIdAplikasi;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.util.AppUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by idong on 20/06/2019.
 */

public class DetailSlikActivity extends AppCompatActivity{

    @BindView(R.id.vp_detail_slik)
    ViewPager vp_detail_slik;
    @BindView(R.id.tab_detail_slik)
    TabLayout tab_detail_slik;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

    private String cif;
    private int idAplikasi;
    private ApiClientAdapter apiClientAdapter;
//    public String dataStatus, dataFasilitas,dataCatatan;
    public String dataSlikNasabah,dataSlikPasangan;
    Call<ParseResponse> call;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_slik);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
//        cif = getIntent().getStringExtra("cif");
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);

//        Log.d("slikaplikasi",Integer.toString(idAplikasi));

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Detail SLIK");

        loadData();


    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void loadData() {
        loading.setVisibility(View.VISIBLE);

        //real data
        ReqIdAplikasi req = new ReqIdAplikasi();

        //real data
        req.setIdAplikasi(idAplikasi);

        //pantekan
//        req.setIdAplikasi(101785);


        //pantekan buat testing
//        ReqHistoryPutusan req=new ReqHistoryPutusan();
//                req.setCif(81857);
//        req.setId_aplikasi(101678);



        //konsumer atau multifaedah mikro
        if(getIntent().getStringExtra("kodeProduk").equalsIgnoreCase("428")){
            call = apiClientAdapter.getApiInterface().inquiryRemaksSlikKmgMikro(req);
        }
        else{
            call = apiClientAdapter.getApiInterface().inquiryDetailSlik(req);
        }

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            dataSlikNasabah = response.body().getData().get("dtMemosales").toString();
                            dataSlikPasangan = response.body().getData().get("dtMemosalesPasangan").toString();

                            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
                            vp_detail_slik.setAdapter(adapter);
                            tab_detail_slik.setupWithViewPager(vp_detail_slik);
                        }
                        else{
                            AppUtil.notiferror(DetailSlikActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            finish();
                        }
                    }
                    else {
                        //error message minta ke bang idong
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(DetailSlikActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                finish();
            }
        });

    }

    // Adapter for the viewpager using FragmentPagerAdapter
    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fr = null;
            Bundle bdl = new Bundle();
            switch (position){
                case 0:
                    fr = new FragmentSlikNasabah();
                    bdl.putString("dataSlikNasabah", dataSlikNasabah);
                    fr.setArguments(bdl);
                    return fr;

                case 1:
                    fr = new FragmentSlikPasangan();
                    bdl.putString("dataSlikPasangan", dataSlikPasangan);
                    fr.setArguments(bdl);
                    return fr;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "SLIK Nasabah";
                case 1: return "SLIK Pasangan";
                default: return null;
            }
        }
    }

}


