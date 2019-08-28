package com.application.bris.brisi_pemutus.view.corelayout.home;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.adapter.disposisi.AdapterDisposisiFront;
import com.application.bris.brisi_pemutus.adapter.eform.EformHomeAdapter;
import com.application.bris.brisi_pemutus.adapter.hotprospek.HotprospekHomeAdapater;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.dashboard.RequestDashboard;
import com.application.bris.brisi_pemutus.api.model.request.list_disposisi.ReqListDisposisi;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.config.eformhome.EformHome;
import com.application.bris.brisi_pemutus.config.hotprospekhome.Hotprospekhome;
import com.application.bris.brisi_pemutus.config.menu.Menu;
import com.application.bris.brisi_pemutus.adapter.menu.MenuAdapter;
import com.application.bris.brisi_pemutus.config.pipelinehome.Pipelinehome;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.listener.menu.MenuClickListener;
import com.application.bris.brisi_pemutus.model.dashboard.DashboardCred;
import com.application.bris.brisi_pemutus.model.disposisi.Disposisi;
import com.application.bris.brisi_pemutus.model.eform.Eform;
import com.application.bris.brisi_pemutus.model.hotprospek.HotProspek;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.model.menu.ListViewMenu;
import com.application.bris.brisi_pemutus.adapter.pipeline.PipelineHomeAdapater;
import com.application.bris.brisi_pemutus.model.pipeline.pipeline;
import com.application.bris.brisi_pemutus.page_akad.ActivityAkad;
import com.application.bris.brisi_pemutus.page_aom.view.PutusanActivity;
import com.application.bris.brisi_pemutus.page_daftar_user.view.MenuDaftarUser;
import com.application.bris.brisi_pemutus.page_disposisi.adapter.AdapterDaftarDisposisi;
import com.application.bris.brisi_pemutus.page_disposisi.view.DaftarDisposisiActivity;
import com.application.bris.brisi_pemutus.page_disposisi.view.MenuDisposisiActivity;
import com.application.bris.brisi_pemutus.page_putusan.menu.MenuDaftarPutusanActivity;
import com.application.bris.brisi_pemutus.util.AppBarStateChangedListener;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PID on 3/29/2019.
 */

public class FragmentHome extends Fragment implements SwipeRefreshLayout.OnRefreshListener, MenuClickListener{
    private View view;

    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbartitle)
    TextView tv_toolbartitle;
    @BindView(R.id.rv_menu)
    RecyclerView rv_menu;
    @BindView(R.id.rv_pipeline)
    RecyclerView rv_pipeline;
    @BindView(R.id.rv_disposisi)
    RecyclerView rv_disposisi;
    @BindView(R.id.imageViewava)
    CircleImageView iv_fotoProfil;
    @BindView(R.id.iv_morepipeline)
    ImageView iv_moreputusan;
    @BindView(R.id.iv_moredisposisi)
    ImageView iv_moredisposisi;

    @BindView(R.id.cl_disposisi)
    ConstraintLayout cl_disposisi;


    @BindView(R.id.animWhale)
    LottieAnimationView whale;

    @BindView(R.id.tvWhale)
    TextView textWhale;

    @BindView(R.id.animWhaleDisposisi)
    LottieAnimationView whaleDisposisi;

    @BindView(R.id.tvWhaleDisposisi)
    TextView textWhaleDisposisi;

    @BindView(R.id.progressbar_front_pemutus)
    ProgressBar progressBar_daftar_pembiayaan;

    @BindView(R.id.progressbar_front_disposisi)
    ProgressBar progressBar_daftar_disposisi;

//    @BindView(R.id.rv_eform)
//    RecyclerView rv_eform;
//    @BindView(R.id.rv_hotprospek)
//    RecyclerView rv_hotprospek;
    TextView namauser,jabatan,kantor;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    AppPreferences appPreferences;


    List<ListViewMenu> dataMenu;
    List<Putusan> dataPutusan;
    List<Disposisi> dataDisposisi;
    DashboardCred dataNotif=new DashboardCred();
    Putusan testing;
    MenuAdapter adapterMenu;
    PipelineHomeAdapater adapaterPipeline;
    AdapterDisposisiFront adapterDisposisiFront;
    GridLayoutManager layoutMenu;
    int coloumMenu = 4;
    LinearLayoutManager layoutPipelineHome;
    LinearLayoutManager layoutDisposisiHome;
    LinearLayoutManager layoutEformHome;
    BroadcastReceiver mMessageReceiver;
    TextView judul_1;
    DashboardCred dataDashboard;
    ApiClientAdapter apiClientAdapter;
    int notifPutusan=0;
    int notifDisposisi=0;

    public FragmentHome() {
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClientAdapter=new ApiClientAdapter(getContext());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        appPreferences=new AppPreferences(view.getContext());
        namauser=view.findViewById(R.id.nameuser1);
        jabatan=view.findViewById(R.id.walletuser2);
        kantor=view.findViewById(R.id.kantorUser);
        namauser.setText(appPreferences.getNama());
        jabatan.setText(appPreferences.getJabatan());
        kantor.setText(appPreferences.getNamaSKK());
        judul_1=view.findViewById(R.id.tv_pipeline);
        swipeRefreshLayout.setOnRefreshListener(this);





//        dataPutusan=new ArrayList<>();
//       dataPutusan=adapterMenu.sendListPutusan();

        //menerima broadcast dari adapterMenu untuk mendapatkan objek dashboard dalam 1 kali tembakan ke server.
       // receiveBroadcastObject();
//        LocalBroadcastManager.getInstance(view.getContext()).registerReceiver(mMessageReceiver,
//                new IntentFilter("from_adapter"));
       // testing=adapterMenu.getPutusanSingle();
    //    Log.d("testing ekipaol",testing.getNama_nasabah());

        checkCollapse();
        initializePipelineHome();

        //disposisi tidak perlu di initialize sendiri, karena data disposisi sudah didapatkan di initialize pipeline dengan 1 request ke server
//        initializeDisposisi();
        initializeMenu();
        onclickStuff();
        //initializeHotprospekHome();
       // initializeEformHome();
        return view;
    }

    public void initializeMenu(){

//get profile pic
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .error(R.drawable.banner_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);


        Glide.with(this)
                .load(UriApi.Baseurl.URL + UriApi.fotoProfil.urlFotoProfil+appPreferences.getNik())
                .apply(options)
                .into(iv_fotoProfil);


        //end of profile pic


        rv_menu.setHasFixedSize(true);
        dataMenu = getListMenu();
        adapterMenu = new MenuAdapter(getContext(), dataMenu, this,dataNotif);
        layoutMenu = new GridLayoutManager(getContext(), coloumMenu);
        rv_menu.setLayoutManager(layoutMenu);
        rv_menu.setAdapter(adapterMenu);
        ViewCompat.setNestedScrollingEnabled(rv_menu, false);

    }

//    public void receiveBroadcastObject(){
//
//        //receive broadcast message in object form
//        mMessageReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                // Get extra data included in the Intent
//                dataDashboard = (DashboardCred)intent.getSerializableExtra("data_dashboard");
//                dataPutusan=(List<Putusan>)intent.getSerializableExtra("data_putusan");
//                testing=(Putusan)intent.getSerializableExtra("testing");
//
//
//                Log.d("judul_1", judul_1.getText().toString());
//
//                //change something on the main page here, by utilizing the dataDashboard object
//
//            }
//        };
//    }

    private List<ListViewMenu> getListMenu() {
        List<ListViewMenu> menu = new ArrayList<>();
        Menu.mainMenuAO(getContext(), menu);
        return menu;
    }

    public void  initializePipelineHome(){
       // dataPipeline = getListPipelineHome();
        //load notifikasi di icon tertentu

            apiClientAdapter= new ApiClientAdapter(getContext());
            RequestDashboard req = new RequestDashboard();
            AppPreferences appPreferences =new AppPreferences(getContext());




        //real data
            req.setUid(Integer.parseInt(appPreferences.getUid()));
            req.setKodeSkk(appPreferences.getKodeSkk());

            progressBar_daftar_pembiayaan.setVisibility(View.VISIBLE);
        progressBar_daftar_disposisi.setVisibility(View.VISIBLE);

            Call<ParseResponse> call = apiClientAdapter.getApiInterface().dashboardRequest(req);
            call.enqueue(new Callback<ParseResponse>() {
                @Override
                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                    if(response.isSuccessful()){

                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataPutusanString = response.body().getData().get("listPutusan").toString();
                            String listDataDisposisiString = response.body().getData().get("listDisposisi").toString();
                            String listDataNotifikasi = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type1 = new TypeToken<DashboardCred>() {}.getType();
                            Type type2 = new TypeToken<List<Putusan>>() {}.getType();
                            Type type3 = new TypeToken<List<Disposisi>>() {}.getType();

                            dataNotif=gson.fromJson(listDataNotifikasi, type1);


                            //initialize menu, memang 2 kali di initialize, di oncreate dan di method ini
                            initializeMenu();
//                            adapterMenu = new MenuAdapter(getContext(), dataMenu,  ,dataNotif.getNotifDashboard());
//                            layoutMenu = new GridLayoutManager(getContext(), coloumMenu);
//                            rv_menu.setLayoutManager(layoutMenu);
//                            rv_menu.setAdapter(adapterMenu);


                            dataPutusan=gson.fromJson(listDataPutusanString, type2);
                            dataDisposisi=gson.fromJson(listDataDisposisiString, type3);
                            if(dataPutusan.size()==0){
//                                whale.setVisibility(View.VISIBLE);
                                textWhale.setVisibility(View.VISIBLE);

                            }

                            //delete below biar disposisi keluar di dashboard , di clear untuk persiapan UAT tanpa disposisi
                            dataDisposisi.clear();
                            if(dataDisposisi.size()==0){
//                                whale.setVisibility(View.VISIBLE);
                                textWhaleDisposisi.setVisibility(View.VISIBLE);

                            }

                            //adapter putusan settings
                            adapaterPipeline = new PipelineHomeAdapater(getContext(),dataPutusan);
                            layoutPipelineHome = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                            progressBar_daftar_pembiayaan.setVisibility(View.GONE);
                            rv_pipeline.setLayoutManager(layoutPipelineHome);
                            rv_pipeline.setAdapter(adapaterPipeline);
                            ViewCompat.setNestedScrollingEnabled(rv_pipeline, false);

                            //adapter disposisi settings
                            progressBar_daftar_disposisi.setVisibility(View.GONE);
                            adapterDisposisiFront = new AdapterDisposisiFront(getContext(), dataDisposisi);
                            layoutDisposisiHome = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                            rv_disposisi.setLayoutManager(new LinearLayoutManager(getContext()));
                            rv_disposisi.setItemAnimator(new DefaultItemAnimator());
                            rv_disposisi.setAdapter(adapterDisposisiFront);
                            ViewCompat.setNestedScrollingEnabled(rv_disposisi, false);
                            try{
                                Log.d("pipeline adp ekipaol ",dataPutusan.get(0).getNama_nasabah());
                            }
                            catch (Exception e){

                            }


                        }
                        else{
                            Toast.makeText(getContext(), "gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ParseResponse> call, Throwable t) {
                    Log.d("LOG D", t.getMessage());

                }
            });


    }

    public void initializeDisposisi() {
        //  dataUser = getListUser();
        //progressbar_loading.setVisibility(View.VISIBLE);
        progressBar_daftar_disposisi.setVisibility(View.VISIBLE);
        ReqListDisposisi req = new ReqListDisposisi();
        AppPreferences appPreferences=new AppPreferences(getContext());


        //pantekan
//        req.setKode_skk("0999901059");
//        req.setSudahDisposisi(false);

        //real data
        req.setKode_skk(appPreferences.getKodeSkk());
        req.setSudahDisposisi(false);




        Call<ParseResponse> call = apiClientAdapter.getApiInterface().listDisposisi(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                //progressbar_loading.setVisibility(View.GONE);
                progressBar_daftar_disposisi.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().get("dtReferal").toString();
                        Log.d("listdatastring",listDataString);
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Disposisi>>() {
                        }.getType();

                        dataDisposisi = gson.fromJson(listDataString, type);
                        adapterDisposisiFront = new AdapterDisposisiFront(getContext(), dataDisposisi);
                        layoutDisposisiHome = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        rv_disposisi.setLayoutManager(new LinearLayoutManager(getContext()));
                        rv_disposisi.setItemAnimator(new DefaultItemAnimator());
                        rv_disposisi.setAdapter(adapterDisposisiFront);
                        if (dataDisposisi.size() == 0) {
//                            whaleDisposisi.setVisibility(View.VISIBLE);
                            textWhaleDisposisi.setVisibility(View.VISIBLE);
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

    private List<Eform> getListEformHome(){
        List<Eform> eform = new ArrayList<>();
        EformHome.listEform(getContext(), eform);
        return eform;
    }



    private List<pipeline> getListPipelineHome(){
        List<pipeline> pipelines = new ArrayList<>();
        Pipelinehome.listPipeline(getContext(), pipelines);
        return pipelines;
    }



    private List<HotProspek> getListHotprospekHome(){
        List<HotProspek> HotProspeks = new ArrayList<>();
        Hotprospekhome.listHotprospek(getContext(), HotProspeks);
        return HotProspeks;
    }



    @Override
    public void onMenuClick(String menu) {

        if (menu.equalsIgnoreCase(getString(R.string.menu_pengajuan))){
            Intent it = new Intent(getContext(), MenuDaftarPutusanActivity.class);
            it.putExtra("notifPembiayaan",dataNotif.getNotifDashboard());
            startActivity(it);
        }
        else if(menu.equalsIgnoreCase(getString(R.string.menu_disetujui))){

            //DELETE THIS AFTER TESTING MANAGEMENT USER PINCA
//            Intent intent2 = new Intent(getContext(), MenuDaftarUser.class);
//            startActivity(intent2);

            if(appPreferences.getFidRole().equalsIgnoreCase("72")||appPreferences.getFidRole().equalsIgnoreCase("72")){
                Intent intent2 = new Intent(getContext(), MenuDaftarUser.class);
                startActivity(intent2);
//                Toast.makeText(getActivity(), "Fitur ini belum dapat digunakan", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent = new Intent(getContext(), MenuDaftarUser.class);
                startActivity(intent);
//                Toast.makeText(getActivity(), "Fitur ini belum dapat digunakan", Toast.LENGTH_SHORT).show();
            }

        }
        else if(menu.equalsIgnoreCase("Disposisi")){
            Intent intent = new Intent(getContext(), MenuDisposisiActivity.class);
            intent.putExtra("notifDisposisi",dataNotif.getNotifDashboardDisposisi());
            startActivity(intent);
//            Toast.makeText(getActivity(), "Fitur ini masih dalam pengembangan", Toast.LENGTH_SHORT).show();
        }
        else if(menu.equalsIgnoreCase("approved")){
            Intent intent = new Intent(getContext(), ActivityAkad.class);
            startActivity(intent);
//            Toast.makeText(getActivity(), "Fitur ini masih dalam pengembangan", Toast.LENGTH_SHORT).show();
        }
        else if(menu.equalsIgnoreCase("logout")){
            final SweetAlertDialog logout=new SweetAlertDialog(getContext(),SweetAlertDialog.WARNING_TYPE);
            logout.setTitleText("Keluar");
            logout.setContentText("Apakah anda yakin ingin log out dari aplikasi?");
            logout.setConfirmText("Ya");
            logout.setCancelText("Batal");
            logout.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    logout.dismissWithAnimation();
                    getActivity().finish();
                }
            });
            logout.show();
//            Toast.makeText(getActivity(), "Fitur ini masih dalam pengembangan", Toast.LENGTH_SHORT).show();
        }
        else{
            AppUtil.showToastShort(getContext(), menu);
        }
    }

    private void checkCollapse(){
        appbar.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d(getClass().getCanonicalName(), state.name());
                if (state.name().equalsIgnoreCase("COLLAPSED") || state.name().equalsIgnoreCase("IDLE")){
                    tv_toolbartitle.setText("BRISI");
                    backgroundStatusBar(state.name());
                    swipeRefreshLayout.setEnabled(false);

                }
                else {
                    backgroundStatusBar(state.name());
                    tv_toolbartitle.setText("");

                }
            }
        });
    }

    private void backgroundStatusBar(String state){
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            if (state.equalsIgnoreCase("COLLAPSED") || state.equalsIgnoreCase("IDLE")){
                window.setStatusBarColor(getActivity().getResources().getColor(R.color.colorWhite));
            }
            else {
                window.setStatusBarColor(getActivity().getResources().getColor(R.color.colorBackgroundTransparent));
                swipeRefreshLayout.setEnabled(true);
                swipeRefreshLayout.setDistanceToTriggerSync(220);
            }
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        getActivity().recreate();
    }

    private void onclickStuff(){
        iv_moreputusan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), PutusanActivity.class);
                intent.putExtra("kodePutusan","putusanKredit");
                startActivity(intent);
            }
        });

        iv_moredisposisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), DaftarDisposisiActivity.class);
                intent.putExtra("menuAsal","daftarDisposisi");

                //delete the comment below to enable clicking more in disposisi list
//                startActivity(intent);
            }
        });
    }





}
