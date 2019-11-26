package com.application.bris.brisi_pemutus.page_daftar_user.view;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.LogPrinter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.data_cabang.RequestDataCabang;
import com.application.bris.brisi_pemutus.api.model.request.list_user.listUser;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.config.user.DaftarUser;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_ao.Ao;
import com.application.bris.brisi_pemutus.model.data_cabang.Cabang;
import com.application.bris.brisi_pemutus.model.data_cabang.CabangModel;
import com.application.bris.brisi_pemutus.model.kantor_cabang.KantorCabang;
import com.application.bris.brisi_pemutus.model.pipeline.pipeline;
import com.application.bris.brisi_pemutus.model.user.User;
import com.application.bris.brisi_pemutus.model.user_mip.UserMip;
import com.application.bris.brisi_pemutus.page_daftar_user.adapters.AdapterDaftarUser;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.ganfra.materialspinner.MaterialSpinner;
import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.spMaterial_role)
    MaterialSpinner role;
    @BindView(R.id.spMaterial_kantor)
    MaterialSpinner kantor;
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
    @BindView(R.id.floatAdd)
    FloatingActionButton btFloat;
//    @BindView(R.id.ll_shimmer)
//    ShimmerFrameLayout shimmer;
    @BindView(R.id.ll_cari)
    LinearLayout cari;
    @BindView(R.id.btflatCari)
    FButton btnCari;

    int jumlahCs = 0;
    int jumlahBos = 0;
    int jumlahUh = 0;

    Call<ParseResponse> call;
    private ApiClientAdapter apiClientAdapter;
    private SearchView searchView;
    List<Ao> dataUser;
    List<Ao> dataUserSetelahSeleksi;
    List<Cabang> dataCabang;
    List<UserMip> dataMip;
    List<String> dataKantor;
    List<String> dataCabangStringTotal;
    List<String> dataUkerCabangStringTotal;
    List<String> kodeCabangStringTotal;
    List<String> kodeUkerCabangStringTotal;
    List<String> dataCabangString;
    List<String> kodeCabangString;
    List<KantorCabang> kantorCabang;
    List<String> listFidRolePinca;
    List<String> listFidRolePincapem;
    CabangModel dataCabangSingular;
    ArrayAdapter<String> adapter;
    AdapterDaftarUser adapterUser;
    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_user);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        main();
//        initializeUser();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        UserActivity.this.recreate();
    }

    @SuppressLint("RestrictedApi")
    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Daftar User");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);

        //seleksi role
        if (appPreferences.getFidRole().equalsIgnoreCase("76")) { //make sure which roletype is "pincapem"
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_pinca_daftar_user, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            role.setAdapter(adapter);
        } else if (appPreferences.getFidRole().equalsIgnoreCase("79")) { //make sure which roletype is "pincapem"
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_pincapem_m3_daftar_user, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            role.setAdapter(adapter);
        }
//        else if (appPreferences.getJabatan().equalsIgnoreCase("mmm")) { //make sure which roletype is "pincapem"
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_pincapem_m3, android.R.layout.simple_spinner_item);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            role.setAdapter(adapter);
//        } else if (appPreferences.getJabatan().equalsIgnoreCase("marketing manager")) { //make sure which roletype is "pincapem"
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_mm, android.R.layout.simple_spinner_item);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            role.setAdapter(adapter);
//        } else if (appPreferences.getJabatan().equalsIgnoreCase("ums head")) { //make sure which roletype is "pincapem"
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_UH, android.R.layout.simple_spinner_item);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            role.setAdapter(adapter);
//        }

        role.setSelection(1);

        dataUkerCabangStringTotal = new ArrayList<>();
        dataUkerCabangStringTotal.add("All");
        adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataUkerCabangStringTotal);
        kantor.setAdapter(adapter);
//        kantor.setSelection(1);

        dataCabangString = new ArrayList<>();
        dataCabangString.add("All");
        dataCabangStringTotal = new ArrayList<>();
        dataCabangStringTotal.add("All");

        if (appPreferences.getFidRole().equalsIgnoreCase("76")) {
            getUkerPinca();
            getKantor();

            initializeUser("All", "All");

            role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (role.getSelectedItem() == null) {
//                    adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
//                    kantor.setAdapter(adapter);
                    } else if (role.getSelectedItem().toString().equalsIgnoreCase("mmm")) {
                        adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                        kantor.setAdapter(adapter);
                    } else if (role.getSelectedItem().toString().equalsIgnoreCase("pincapem")) {
                        adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataCabangStringTotal);
                        kantor.setAdapter(adapter);
                    } else if (role.getSelectedItem().toString().equalsIgnoreCase("adp")) {
                        adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                        kantor.setAdapter(adapter);
                    } else if (role.getSelectedItem().toString().equalsIgnoreCase("mo")) {
                        adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                        kantor.setAdapter(adapter);
                    } else if (role.getSelectedItem().toString().equalsIgnoreCase("uh")) {
                        //semua uker dibawah nya + kc
                        if (appPreferences.getFidRole().equalsIgnoreCase("76")) {
                            adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataUkerCabangStringTotal);
                            kantor.setAdapter(adapter);
                        } else {
                            adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                            kantor.setAdapter(adapter);
                        }
                    } else if (role.getSelectedItem().toString().equalsIgnoreCase("bos")) {
                        if (appPreferences.getFidRole().equalsIgnoreCase("76")) {
                            adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataUkerCabangStringTotal);
                            kantor.setAdapter(adapter);
                        } else {
                            adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                            kantor.setAdapter(adapter);
                        }
                    } else if (role.getSelectedItem().toString().equalsIgnoreCase("cs")) {
                        if (appPreferences.getFidRole().equalsIgnoreCase("76")) {
                            adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataUkerCabangStringTotal);
                            kantor.setAdapter(adapter);
                        } else {
                            adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                            kantor.setAdapter(adapter);
                        }
                    } else if (role.getSelectedItem().toString().equalsIgnoreCase("ao")) {
                        adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                        kantor.setAdapter(adapter);
                    } else if (role.getSelectedItem().toString().equalsIgnoreCase("aom")) {
                        //semua uker dibawah nya + kc
                        if (appPreferences.getFidRole().equalsIgnoreCase("76")) {
                            adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataUkerCabangStringTotal);
                            kantor.setAdapter(adapter);
                        } else {
                            adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                            kantor.setAdapter(adapter);
                        }
                    }
                    Log.d("Role dipilih", role.getSelectedItem().toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            btnCari.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (role.getSelectedItem() == null) {
                        role.setError("Harap isi role terlebih dahulu");
                    }
                    if (kantor.getSelectedItem() == null) {
                        kantor.setError("Harap isi kantor terlebih dahulu");
                    }
//
                    else if (role.getSelectedItem().toString().equalsIgnoreCase("All")) {
                        initializeUser("All", kantor.getSelectedItem().toString());
                    }
//                    else if (role.getSelectedItem().toString().equalsIgnoreCase("BOS")) {
//                        String fid_role = "26";
//                        initializeUser(fid_role, kantor.getSelectedItem().toString());
//                    }
                    else {
                        String fid_role = "";
                        if (role.getSelectedItem().toString().equalsIgnoreCase("MMM")) {
                            fid_role = "72";
                        } else if (role.getSelectedItem().toString().equalsIgnoreCase("Pincapem")) {
                            fid_role = "79";
                        } else if (role.getSelectedItem().toString().equalsIgnoreCase("ADP")) {
                            fid_role = "22";
                        } else if (role.getSelectedItem().toString().equalsIgnoreCase("MO")) {
                            fid_role = "25";
                        } else if (role.getSelectedItem().toString().equalsIgnoreCase("UH")) {
                            fid_role = "71";
                        } else if (role.getSelectedItem().toString().equalsIgnoreCase("BOS")) {
                            fid_role = "26";
                        } else if (role.getSelectedItem().toString().equalsIgnoreCase("CS")) {
                            fid_role = "23";
                        } else if (role.getSelectedItem().toString().equalsIgnoreCase("AO")) {
                            fid_role = "100";
                        } else if (role.getSelectedItem().toString().equalsIgnoreCase("AOM")) {
                            fid_role = "8";
                        }
                        initializeUser(fid_role, kantor.getSelectedItem().toString());
                    }

//                    //still needs work, filter tnapa request ke DB
//else if (role.getSelectedItem() != null &&kantor.getSelectedItem() != null){
////                        initializeUser("all", kantor.getSelectedItem().toString());
//
//                        if(role.getSelectedItem().toString().equalsIgnoreCase("all")){
//                            filterUser("all",kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition()-1),dataUser);
//                        }
//                        else if(role.getSelectedItem().toString().equalsIgnoreCase("uh")||role.getSelectedItem().toString().equalsIgnoreCase("cs")||role.getSelectedItem().toString().equalsIgnoreCase("bos")||role.getSelectedItem().toString().equalsIgnoreCase("pincapem")){
//                            filterUser(listFidRolePinca.get(role.getSelectedItemPosition()-1),kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition()),dataUser);
//                        }
//                        else if(role.getSelectedItem().toString().equalsIgnoreCase("aom")){
//                            filterUser(listFidRolePinca.get(role.getSelectedItemPosition()-1),kodeCabangStringTotal.get(kantor.getSelectedItemPosition()),dataUser);
//                        }
//                        else {
//                            filterUser(listFidRolePinca.get(role.getSelectedItemPosition()-1),appPreferences.getKodeSkk(),dataUser);
//                        }
//
//                    }



                }
            });
        }

        if (!appPreferences.getFidRole().equalsIgnoreCase("76")) {
            cari.setVisibility(View.GONE);
            initializeUser("All", "All");
        }


        //MENGHILANGKAN AKSES KE HALAMAN INSERT , SAMPAI SELURUH FIELD MANAJEMEN USER PINCA SUDAH TEPAT
        if(appPreferences.getJabatan().equalsIgnoreCase("pinca")||appPreferences.getJabatan().equalsIgnoreCase("pimpinan cabang")||appPreferences.getJabatan().equalsIgnoreCase("mmm")){
//            btFloat.setVisibility(View.GONE);
            btFloat.setVisibility( View.GONE);
        }
        btFloat.setVisibility( View.GONE);
//        btFloat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(UserActivity.this,TambahUserActivity.class);
//                startActivity(intent);
//            }
//        });

//        kantor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////                Log.d("Kantor dipilih", kantor.getSelectedItem().toString());
////                Log.d("Role dipilih", role.getSelectedItem().toString());
//                if (role.getSelectedItem().toString().equalsIgnoreCase("All")) {
//                    initializeUser("All", kantor.getSelectedItem().toString());
//                }
//                else if (role.getSelectedItem().toString().equalsIgnoreCase("BOS")) {
//                    initializeUser("BO", kantor.getSelectedItem().toString());
//                }
//                else {
//                    initializeUser(role.getSelectedItem().toString(), kantor.getSelectedItem().toString());
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }

    private void getKantor(){
        //connect to server
        progressbar_loading.setVisibility(View.VISIBLE);
        RequestDataCabang req = new RequestDataCabang();
        req.setKodeCabang(appPreferences.getKodeSkk());
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().dataCabang(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                if (response.isSuccessful()) {
                    progressbar_loading.setVisibility(View.GONE);
//                    content.setVisibility(View.VISIBLE);
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Cabang>>() {
                        }.getType();

                        dataCabang = gson.fromJson(listDataString, type);
                        dataCabangSingular = new CabangModel(UserActivity.this);
                        dataCabangSingular.clear();

                        for (int i = 0; i < dataCabang.size(); i++) {
                            dataCabangString.add(dataCabang.get(i).getSb_desc());
                            dataCabangSingular.addCabang(dataCabang.get(i));

                            try {
                                Log.d("ebdesc ekipaol", dataCabang.get(i).getSb_desc());
                                adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                                kantor.setAdapter(adapter);


                                //kantor on selected dipindahkan disini
//                                kantor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                    @Override
//                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////                Log.d("Kantor dipilih", kantor.getSelectedItem().toString());
////                Log.d("Role dipilih", role.getSelectedItem().toString());
//                                        if (role.getSelectedItem().toString().equalsIgnoreCase("All")) {
//                                            initializeUser("All", kantor.getSelectedItem().toString());
//                                        }
//                                        else if (role.getSelectedItem().toString().equalsIgnoreCase("BOS")) {
//                                            initializeUser("BO", kantor.getSelectedItem().toString());
//                                        }
//                                        else {
//                                            initializeUser(role.getSelectedItem().toString(), kantor.getSelectedItem().toString());
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                                    }
//                                });
                            } catch (Exception e) {

                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
            }
        });

    }

    private void getUkerPinca() {
        progressbar_loading.setVisibility(View.VISIBLE);
        RequestDataCabang req = new RequestDataCabang();
        req.setKodeCabang(appPreferences.getKodeSkk());
        req.setKodeSkk(appPreferences.getKodeSkk());
        call = apiClientAdapter.getApiInterface().dataPinca(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call2, Response<ParseResponse> response) {
                if (response.isSuccessful()) {
                    progressbar_loading.setVisibility(View.GONE);
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataKantorCabangString = response.body().getData().get("listKcp").toString();
//                        dataCabangStringTotal = new ArrayList<>();
//                        dataUkerCabangStringTotal = new ArrayList<>();
                        kodeCabangStringTotal = new ArrayList<>();
                        kodeUkerCabangStringTotal = new ArrayList<>();
                        Gson gson = new Gson();
                        Type type1 = new TypeToken<List<KantorCabang>>() {
                        }.getType();
                        kantorCabang = gson.fromJson(listDataKantorCabangString, type1);

//                        dataCabangStringTotal.add("All");
//                        dataUkerCabangStringTotal.add("All");

                        dataUkerCabangStringTotal.add(appPreferences.getNamaKantor());
                        kodeUkerCabangStringTotal.add(appPreferences.getKodeSkk());

                        for (int i = 0; i < kantorCabang.size(); i++) {
                            dataCabangStringTotal.add(kantorCabang.get(i).getSb_desc());
                            dataUkerCabangStringTotal.add(kantorCabang.get(i).getSb_desc());
                            kodeCabangStringTotal.add(kantorCabang.get(i).getKode_cabang());
                            kodeUkerCabangStringTotal.add(kantorCabang.get(i).getKode_cabang());
                            Log.d("inilahKantorCabang", kantorCabang.get(i).getSb_desc());
                        }
                        progressbar_loading.setVisibility(View.GONE);
//                        content.setVisibility(View.VISIBLE);
                        adapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, dataUkerCabangStringTotal);
                        kantor.setAdapter(adapter);
                        kantor.setSelection(1);
                    } else {
                        Toast.makeText(UserActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call2, Throwable t) {
                Log.d("LOG D", t.getMessage());
                Toast.makeText(UserActivity.this, "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void initializeUser(final String role, final String kantor){
        Log.d("Cek role", role);
        //  dataUser = getListUser();
        final AppPreferences apppref=new AppPreferences(UserActivity.this);
        // progressbar_loading.setVisibility(View.VISIBLE);
//        shimmer.setVisibility(View.VISIBLE);
        progressbar_loading.setVisibility(View.VISIBLE);
        RequestDataCabang req = new RequestDataCabang();
        req.setKodeCabang(apppref.getKodeCabang());

        //conditioning list yang ditampilkan
        if(apppref.getFidRole().equalsIgnoreCase("79")){
            call = apiClientAdapter.getApiInterface().dataUh(req);
            req.setKodeSkk(apppref.getKodeSkk());
        }
        else if(apppref.getFidRole().equalsIgnoreCase("71")){
             call = apiClientAdapter.getApiInterface().dataAo(req);
            req.setKodeCabang(apppref.getKodeSkk());
        }
        else if(apppref.getFidRole().equalsIgnoreCase("76")){
            call = apiClientAdapter.getApiInterface().dataPincaLengkap(req);
            req.setKodeCabang(apppref.getKodeSkk());
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
//                shimmer.setVisibility(View.GONE);
                progressbar_loading.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    if(response.body().getStatus().equalsIgnoreCase("00")){
                        if(apppref.getFidRole().equalsIgnoreCase("79")){
                            String listDataString = response.body().getData().get("listUh").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Ao>>() {}.getType();
                            dataUser = gson.fromJson(listDataString, type);
                            jumlahBos=Integer.parseInt(response.body().getData().get("jumlahBOS").toString());
                            jumlahUh=Integer.parseInt(response.body().getData().get("jumlahUH").toString());
                            jumlahCs=Integer.parseInt(response.body().getData().get("jumlahCS").toString());

                            if (dataUser.size() == 0) {
                                whale.setVisibility(View.VISIBLE);
                                tvWhale.setVisibility(View.VISIBLE);
                                rv_listuser.setVisibility(View.GONE);
                            } else {
                                whale.setVisibility(View.GONE);
                                tvWhale.setVisibility(View.INVISIBLE);
                                adapterUser = new AdapterDaftarUser(UserActivity.this, dataUser);
                                adapterUser.notifyDataSetChanged();
                            }
                        }
                        else if(apppref.getFidRole().equalsIgnoreCase("71")){
                            String listDataStringAo = response.body().getData().get("listAom").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Ao>>() {}.getType();
                            dataUser = gson.fromJson(listDataStringAo, type);
                            adapterUser = new AdapterDaftarUser(UserActivity.this, dataUser);
                        }
                        else if(apppref.getFidRole().equalsIgnoreCase("76")) {
                            String listDataStringAo = response.body().getData().get("listBawahanLangsung").toString();
                            List<Ao> dataFilter = new ArrayList<>();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Ao>>() {
                            }.getType();
                            dataUser = gson.fromJson(listDataStringAo, type);

                            if (!role.equalsIgnoreCase("All") && !kantor.equalsIgnoreCase("All")){
                                for (int i = 0; i < dataUser.size(); i++) {
                                    if (dataUser.get(i).getFid_role().equalsIgnoreCase(role) &&
                                            dataUser.get(i).getSbdesc().equalsIgnoreCase(kantor)){
                                        dataFilter.add(dataUser.get(i));
                                    }
                                }
                            } else if (role.equalsIgnoreCase("All") && !kantor.equalsIgnoreCase("All")) {
                                for (int i = 0; i < dataUser.size(); i++) {
                                    if (dataUser.get(i).getSbdesc().equalsIgnoreCase(kantor)){
                                        Log.d("Cek data", dataUser.get(i).getSbdesc());
                                        dataFilter.add(dataUser.get(i));
                                    }
                                }
                            } else if (!role.equalsIgnoreCase("All") && kantor.equalsIgnoreCase("All")) {
                                for (int i = 0; i < dataUser.size(); i++) {
                                    if (dataUser.get(i).getFid_role().equalsIgnoreCase(role)){
                                        dataFilter.add(dataUser.get(i));
                                    }
                                }
                            } else {
                                dataFilter = dataUser;
                            }

                            Log.d("dataFilter", String.valueOf(dataFilter.size()));
                            if (dataFilter.size() == 0) {
                                whale.setVisibility(View.VISIBLE);
                                tvWhale.setVisibility(View.VISIBLE);
                                rv_listuser.setVisibility(View.GONE);
                            } else {
                                rv_listuser.setVisibility(View.VISIBLE);
                                whale.setVisibility(View.GONE);
                                tvWhale.setVisibility(View.INVISIBLE);
                                adapterUser = new AdapterDaftarUser(UserActivity.this, dataFilter);
                                adapterUser.notifyDataSetChanged();
                            }
                        }
                        else if(apppref.getFidRole().equalsIgnoreCase("72")){
                            String listDataStringAo = response.body().getData().get("listUh").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Ao>>() {}.getType();
                            dataUser = gson.fromJson(listDataStringAo, type);
                            adapterUser = new AdapterDaftarUser(UserActivity.this, dataUser);
                        }

//                        adapterUser = new AdapterDaftarUser(UserActivity.this, dataUser, "MMM", "KCP Sunter");
                        rv_listuser.setLayoutManager(new LinearLayoutManager(UserActivity.this));
                        rv_listuser.setItemAnimator(new DefaultItemAnimator());
                        rv_listuser.setAdapter(adapterUser);
//                        if(dataUser.size()==0){
//                            whale.setVisibility(View.VISIBLE);
//                            tvWhale.setVisibility(View.VISIBLE);
//                            rv_listuser.setVisibility(View.GONE);
//                        }
//                        else{
//                            whale.setVisibility(View.GONE);
//                            tvWhale.setVisibility(View.INVISIBLE);
//                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    public void filterUser(String idRole,String kodeSkk,List<Ao> dataSeluruhUser){
        //filter user berdasarkan input di spinner
        List<Ao> dataFilteredUser=new ArrayList<>();


        for (int i = 0; i <dataSeluruhUser.size() ; i++) {

            //kondisi role all dan kantor spesifik
            if(idRole.equalsIgnoreCase("all")&& !kodeSkk.equalsIgnoreCase("all")) {
                if (dataSeluruhUser.get(i).getKode_skk().equalsIgnoreCase(kodeSkk)) {
                    dataFilteredUser.add(dataSeluruhUser.get(i));
                }
            }

            //kondisi role spesifik dan kantor all
            else if(!idRole.equalsIgnoreCase("all")&& kodeSkk.equalsIgnoreCase("all")) {
                if(dataSeluruhUser.get(i).getFid_role().equalsIgnoreCase(idRole)){
                    dataFilteredUser.add(dataSeluruhUser.get(i));
                }
            }
            //kondisi kantor spesifik dan role spesifik
            else{
                if(dataSeluruhUser.get(i).getFid_role().equalsIgnoreCase(idRole)&&dataSeluruhUser.get(i).getKode_skk().equalsIgnoreCase(kodeSkk)){
                    dataFilteredUser.add(dataSeluruhUser.get(i));
                }
            }
            adapterUser = new AdapterDaftarUser(UserActivity.this, dataFilteredUser);
        rv_listuser.setAdapter(adapterUser);
        adapterUser.notifyDataSetChanged();


        }

    }

    private List<User> getListUser(){
        List<User> user = new ArrayList<>();
        DaftarUser.listUser(this, user);
        return user;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        UserActivity.this.recreate();
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
