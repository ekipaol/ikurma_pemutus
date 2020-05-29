package com.application.bris.brisi_pemutus.view.corelayout.home;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.fragment.app.Fragment;
import androidx.core.view.ViewCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.adapter.disposisi.AdapterDisposisiFront;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.dashboard.RequestDashboard;
import com.application.bris.brisi_pemutus.api.model.request.list_disposisi.ReqListDisposisi;
import com.application.bris.brisi_pemutus.api.model.request.login.LoginRequest;
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
import com.application.bris.brisi_pemutus.model.login_cred.LoginCred;
import com.application.bris.brisi_pemutus.model.menu.ListViewMenu;
import com.application.bris.brisi_pemutus.adapter.pipeline.PipelineHomeAdapater;
import com.application.bris.brisi_pemutus.model.pipeline.pipeline;
import com.application.bris.brisi_pemutus.page_ambil_alih.AmbilAlihActivity;
import com.application.bris.brisi_pemutus.page_ao_silang.AoSilangActivity;
import com.application.bris.brisi_pemutus.page_performance.MenuPerformanceActivity;
import com.application.bris.brisi_pemutus.page_aom.view.PutusanActivity;
import com.application.bris.brisi_pemutus.page_daftar_user.view.MenuDaftarUser;
import com.application.bris.brisi_pemutus.page_disposisi.view.DaftarDisposisiActivity;
import com.application.bris.brisi_pemutus.page_disposisi.view.MenuDisposisiActivity;
import com.application.bris.brisi_pemutus.page_login.view.LoginActivity;
import com.application.bris.brisi_pemutus.page_putusan.menu.MenuDaftarPutusanActivity;
import com.application.bris.brisi_pemutus.page_riwayat.ActivityMenuRiwayat;
import com.application.bris.brisi_pemutus.util.AppBarStateChangedListener;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.view.corelayout.CoreLayoutActivity;
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
import es.dmoral.toasty.Toasty;
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
    @BindView(R.id.rv_deviasi)
    RecyclerView rv_deviasi;
    @BindView(R.id.rv_disposisi)
    RecyclerView rv_disposisi;
    @BindView(R.id.imageViewava)
    CircleImageView iv_fotoProfil;
    @BindView(R.id.iv_morepipeline)
    ImageView iv_moreputusan;
    @BindView(R.id.iv_moredisposisi)
    ImageView iv_moredisposisi;
    @BindView(R.id.iv_moredeviasi)
    ImageView iv_moredeviasi;

    @BindView(R.id.cl_disposisi)
    ConstraintLayout cl_disposisi;
    @BindView(R.id.cl_deviasi)
    ConstraintLayout cl_deviasi;
    @BindView(R.id.cl_pipeline)
    ConstraintLayout cl_pipeline;
    @BindView(R.id.ll_pesan_dashboard)
    LinearLayout ll_pesan_dashboard;
    @BindView(R.id.tv_pesan_dashboard)
    TextView tv_pesan_dashboard;



    @BindView(R.id.animWhale)
    LottieAnimationView whale;

    @BindView(R.id.tvWhale)
    TextView textWhale;

    @BindView(R.id.tvWhaledeviasi)
    TextView textWhaleDeviasi;

    @BindView(R.id.animWhaleDisposisi)
    LottieAnimationView whaleDisposisi;

    @BindView(R.id.tvWhaleDisposisi)
    TextView textWhaleDisposisi;

    @BindView(R.id.progressbar_front_pemutus)
    ProgressBar progressBar_daftar_pembiayaan;

    @BindView(R.id.progressbar_front_disposisi)
    ProgressBar progressBar_daftar_disposisi;

    @BindView(R.id.progressbar_front_deviasi)
    ProgressBar progressbar_daftar_deviasi;

    @BindView(R.id.ll_menu_container)
    LinearLayout ll_menu_container;

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
    List<Putusan> dataDeviasi;
    List<Disposisi> dataDisposisi;
    DashboardCred dataNotif=new DashboardCred();
    Putusan testing;
    MenuAdapter adapterMenu;
    PipelineHomeAdapater adapaterPipeline;
    PipelineHomeAdapater adapterDeviasi;
    AdapterDisposisiFront adapterDisposisiFront;
    GridLayoutManager layoutMenu;
    int coloumMenu = 4;
    LinearLayoutManager layoutPipelineHome;
    LinearLayoutManager layoutDisposisiHome;
    LinearLayoutManager layoutdeviasiHome;
    LinearLayoutManager layoutEformHome;
    BroadcastReceiver mMessageReceiver;
    TextView judul_1;
    DashboardCred dataDashboard;
    ApiClientAdapter apiClientAdapter;
    SweetAlertDialog dialog1;
    LoginCred dataUser;
    Call<ParseResponse> call;

    int notifPutusan=0;
    int notifDisposisi=0;

    boolean indikatorSelesaiLoading=false;

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
//        kantor.setText(appPreferences.getJabatan()+"\n"+appPreferences.getNamaSKK());
        judul_1=view.findViewById(R.id.tv_pipeline);
        swipeRefreshLayout.setOnRefreshListener(this);


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


        //ubah nilai versi notifikasi disini dengan nilai baru, jika ada notifikasi baru/update baru
        //update 1.4.7 versi notifikasi 1 ( ubah jadi 2 atau nilai lain di versi selanjutnya
        String versiNotifikasi="1";
        if(!appPreferences.getNotificationVersion().equalsIgnoreCase(versiNotifikasi)){
            appPreferences.setNotificationVersion(versiNotifikasi);
            appPreferences.setUpdateNotification("true");
        }


        if(appPreferences.isUpdateNotificationOn().equalsIgnoreCase("true")){

            //MENAMPILKAN CHANGELOG UNTUK UPDATE TERBARU KORMA
            AppUtil.DialogUpdateInformation(getContext(),
                    "Fitur Terbaru i-Kurma Pemutus",
                    "-Penambahan support untuk pembiayaan Purna dan Prapurna\n\n" +
                    "-Perbaikan pada halaman data finansial untuk pembiayaan multifaedah mikro\n\n" +
                    "-Pencarian data pembiayaan cair berdasarkan bulan dan tahun\n");
        }






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

        initializeMenu();
        onclickStuff();
        //initializeHotprospekHome();
       // initializeEformHome();
        return view;
    }

    public void initializeMenu(){
//
////get profile pic
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .placeholder(R.drawable.banner_placeholder)
//                .error(R.drawable.banner_placeholder)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .priority(Priority.HIGH);
//
//
//        Glide.with(this)
//                .load(UriApi.Baseurl.URL + UriApi.fotoProfil.urlFotoProfil+appPreferences.getNik())
//                .apply(options)
//                .into(iv_fotoProfil);
//
//
//        //end of profile pic

        rv_menu.setHasFixedSize(true);
        dataMenu = getListMenu();
        adapterMenu = new MenuAdapter(getContext(), dataMenu, this,dataNotif);
        layoutMenu = new GridLayoutManager(getContext(), coloumMenu);
        rv_menu.setLayoutManager(layoutMenu);
        rv_menu.setAdapter(adapterMenu);
        ViewCompat.setNestedScrollingEnabled(rv_menu, false);



        //tutorial overlay menu baru : performance
        if(!appPreferences.getFidRole().equalsIgnoreCase("71")){
//            AppUtil.tutorialOverlay(getActivity(),ll_menu_container,"Sekarang ada menu performance untuk melihat performa cabang ataupun AO/AOM","tutorial_menu_performance_v3");
        }


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
            final AppPreferences appPreferences =new AppPreferences(getContext());



            //pantekan
//        req.setUid(Integer.parseInt("9812"));
//        req.setKodeSkk("123123123123");


        //real data
            req.setUid(Integer.parseInt(appPreferences.getUid()));
            req.setKodeSkk(appPreferences.getKodeSkk());

            progressBar_daftar_pembiayaan.setVisibility(View.VISIBLE);
        progressBar_daftar_disposisi.setVisibility(View.VISIBLE);
        progressbar_daftar_deviasi.setVisibility(View.VISIBLE);
        call = apiClientAdapter.getApiInterface().dashboardRequest(req);
        call.enqueue(new Callback<ParseResponse>() {
                @Override
                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                    rv_pipeline.setVisibility(View.VISIBLE);
                    rv_deviasi.setVisibility(View.VISIBLE);
                    rv_disposisi.setVisibility(View.VISIBLE);


                    if(response.isSuccessful()){

                        if(response.body().getStatus().equalsIgnoreCase("00")){

                            //kalau ada pesan dari server, ditampilkan disini
                            if(response.body().getData().get("pesanDashboard")!=null&&!response.body().getData().get("pesanDashboard").getAsString().isEmpty()){
                                AppUtil.notifinfoLong(getContext(),getActivity().findViewById(android.R.id.content),response.body().getData().get("pesanDashboard").getAsString());
                            }

                            String listDataPutusanString = response.body().getData().get("listPutusan").toString();

                            String listDataPutusanDeviasiString ="";
                            if(response.body().getData().get("listPutusanDeviasi")!=null){
                                 listDataPutusanDeviasiString = response.body().getData().get("listPutusanDeviasi").toString();
                            }
                            else{
                                 listDataPutusanDeviasiString ="[]";
                            }


                            //diberi if, karena kembalian dari middletier itu null kalau appprefnya sudah dirubah setelah login
                            if (appPreferences.getFidRole().equalsIgnoreCase("76")) {
                                if( response.body().getData().get("kodeKc")!=null && response.body().getData().get("namaKc")!=null){
                                    String kodeKc = response.body().getData().get("kodeKc").toString().replace("\"", "");
                                    String namaKc = response.body().getData().get("namaKc").toString().replace("\"", "");
                                    appPreferences.setKodeSkk(kodeKc);
                                    appPreferences.setNamaKantor(namaKc);

                                    //reset sessioon perubahan kode SKK
                                    appPreferences.setStatusKodeSkkPinca("berubah");
                                    indikatorSelesaiLoading=true;
                                }
                            }
                            String listDataDisposisiString="";

                            //hanya mengambil listdisposisi jika yang login pinca, pincapem, atau mmm (kecuali UH role 71)

                            if(!appPreferences.getFidRole().equalsIgnoreCase("71")){
                                 listDataDisposisiString = response.body().getData().get("listDisposisi").toString();
                            }

                            String listDataNotifikasi = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type1 = new TypeToken<DashboardCred>() {}.getType();
                            Type type2 = new TypeToken<List<Putusan>>() {}.getType();
                            Type type3 = new TypeToken<List<Disposisi>>() {}.getType();

                            dataNotif=gson.fromJson(listDataNotifikasi, type1);


                            //initialize menu, memang 2 kali di initialize, di oncreate dan di method ini
                            initializeMenu();



                            dataPutusan=gson.fromJson(listDataPutusanString, type2);
                            dataDeviasi=gson.fromJson(listDataPutusanDeviasiString, type2);
                            dataDisposisi=gson.fromJson(listDataDisposisiString, type3);
                            if(dataPutusan.size()==0){
//                                whale.setVisibility(View.VISIBLE);
                                textWhale.setVisibility(View.VISIBLE);

                            }
                            if(dataDeviasi.size()==0){
//                                whale.setVisibility(View.VISIBLE);
                                textWhaleDeviasi.setVisibility(View.VISIBLE);

                            }

                            if(dataDisposisi!=null){
                                if(dataDisposisi.size()==0){
//                                whale.setVisibility(View.VISIBLE);
                                    progressBar_daftar_disposisi.setVisibility(View.GONE);
                                    textWhaleDisposisi.setVisibility(View.VISIBLE);

                                }
                                else{
                                    //delete below biar disposisi keluar di dashboard , di clear untuk persiapan UAT tanpa disposisi
//                                dataDisposisi.clear();

                                    //adapter disposisi settings
                                    progressBar_daftar_disposisi.setVisibility(View.GONE);
                                    adapterDisposisiFront = new AdapterDisposisiFront(getContext(), dataDisposisi);
                                    layoutDisposisiHome = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                                    rv_disposisi.setLayoutManager(new LinearLayoutManager(getContext()));
                                    rv_disposisi.setItemAnimator(new DefaultItemAnimator());
                                    rv_disposisi.setAdapter(adapterDisposisiFront);
                                    ViewCompat.setNestedScrollingEnabled(rv_disposisi, false);
                                }
                            }
                            else{
                                progressBar_daftar_disposisi.setVisibility(View.GONE);
                                textWhaleDisposisi.setVisibility(View.VISIBLE);
                            }


                            //adapter putusan settings
                            adapaterPipeline = new PipelineHomeAdapater(getContext(),dataPutusan);
                            adapterDeviasi = new PipelineHomeAdapater(getContext(),dataDeviasi);
                            layoutPipelineHome = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                            layoutdeviasiHome = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                            progressBar_daftar_pembiayaan.setVisibility(View.GONE);
                            progressbar_daftar_deviasi.setVisibility(View.GONE);
                            rv_pipeline.setLayoutManager(layoutPipelineHome);
                            rv_pipeline.setAdapter(adapaterPipeline);
                            rv_deviasi.setLayoutManager(layoutdeviasiHome);
                            rv_deviasi.setAdapter(adapterDeviasi);
                            ViewCompat.setNestedScrollingEnabled(rv_pipeline, false);
                            ViewCompat.setNestedScrollingEnabled(rv_deviasi, false);


                            try{
                                Log.d("pipeline adp ekipaol ",dataPutusan.get(0).getNama_nasabah());
                            }
                            catch (Exception e){

                            }


                        }
                        else if(response.body().getStatus().equalsIgnoreCase("01")){
                            //hide all dashboard recycle stuff
                            cl_pipeline.setVisibility(View.GONE);
                            cl_deviasi.setVisibility(View.GONE);
                            cl_disposisi.setVisibility(View.GONE);
                            rv_pipeline.setVisibility(View.GONE);
                            rv_deviasi.setVisibility(View.GONE);
                            rv_disposisi.setVisibility(View.GONE);

                            ll_pesan_dashboard.setVisibility(View.VISIBLE);
                            tv_pesan_dashboard.setText(response.body().getMessage());
                        }
                        else{
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
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

        //MENU PUTUSAN
        //cek apakah user dalam status ambil alih atau tidak
        if(appPreferences.getStatusAmbilAlih().equalsIgnoreCase("Ya")){
            if (menu.equalsIgnoreCase(getString(R.string.menu_pengajuan))){
                Intent it = new Intent(getContext(), MenuDaftarPutusanActivity.class);
                it.putExtra("notifPembiayaan",dataNotif.getNotifDashboard());
                startActivity(it);
            }
            else  if (menu.equalsIgnoreCase("Keluar Ambil Alih")){
               keluarAmbilAlih();
            }
            else{
                Toasty.info(getContext(),"Anda tidak dapet mengakses menu ini ketika ambil alih user", Toast.LENGTH_LONG).show();
            }
        }
        else{
            if (menu.equalsIgnoreCase(getString(R.string.menu_pengajuan))){
                Intent it = new Intent(getContext(), MenuDaftarPutusanActivity.class);
                it.putExtra("notifPembiayaan",dataNotif.getNotifDashboard());
                it.putExtra("notifPembiayaanDeviasi",dataNotif.getJlhPutusanDeviasi());
                startActivity(it);
            }

            //MENU MANAJEMEN USER
            else if(menu.equalsIgnoreCase(getString(R.string.menu_disetujui))){

                //DELETE THIS AFTER TESTING MANAGEMENT USER PINCA
//            Intent intent = new Intent(getContext(), MenuDaftarUser.class);
//            startActivity(intent);

                if(appPreferences.getFidRole().equalsIgnoreCase("76")||appPreferences.getFidRole().equalsIgnoreCase("79")){
                    Intent intent2 = new Intent(getContext(), MenuDaftarUser.class);
                    startActivity(intent2);
//                Toast.makeText(getActivity(), "Fitur ini belum dapat digunakan", Toast.LENGTH_SHORT).show();
                }
                else{
//                Intent intent = new Intent(getContext(), MenuDaftarUser.class);
//                startActivity(intent);
                    Toast.makeText(getActivity(), "Fitur ini hanya dapat digunakan oleh Pinca dan Pincapem", Toast.LENGTH_SHORT).show();
                }

            }

            //MENU DISPOSISI
            else if(menu.equalsIgnoreCase("Disposisi")){
                Intent intent = new Intent(getContext(), MenuDisposisiActivity.class);
                intent.putExtra("notifDisposisi",dataNotif.getNotifDashboardDisposisi());
                startActivity(intent);
//            Toast.makeText(getActivity(), "Fitur ini masih dalam pengembangan", Toast.LENGTH_SHORT).show();
            }

            //MENU AO SILANG
            else if(menu.equalsIgnoreCase("ao silang")){
                Intent intent = new Intent(getContext(), AoSilangActivity.class);
                startActivity(intent);
//            Toast.makeText(getActivity(), "Fitur ini masih dalam pengembangan", Toast.LENGTH_SHORT).show();
            }


            //MENU RIWAYAT
            else if(menu.equalsIgnoreCase("riwayat")){
                Intent intent = new Intent(getContext(), ActivityMenuRiwayat.class);
                startActivity(intent);
//            Toast.makeText(getActivity(), "Fitur ini masih dalam pengembangan", Toast.LENGTH_SHORT).show();
            }

            //MENU PERFORMANCE
            else if(menu.equalsIgnoreCase("performance")){

                if(appPreferences.getFidRole().equalsIgnoreCase("77")){
                    Toasty.info(getContext(),"Menu ini belum bisa digunakan user MM");
                }
                else{
                    Intent intent = new Intent(getContext(), MenuPerformanceActivity .class);
                    startActivity(intent);
                }


//            Toast.makeText(getActivity(), "Fitur ini masih dalam pengembangan", Toast.LENGTH_SHORT).show();
            }

            //MENU AMBIL ALIH
            else if(menu.equalsIgnoreCase("ambil alih putusan")){
                if(indikatorSelesaiLoading==false&&!appPreferences.getFidRole().equalsIgnoreCase("76")){//ini untuk akses tombol setelah login khusus non pinca
                    Intent intent = new Intent(getContext(), AmbilAlihActivity.class);
                    startActivity(intent);
                }
                else if(appPreferences.getStatusKodeSkkPinca().equalsIgnoreCase("berubah")){ //ini untuk akses tombol setelah akses menu menu lain, tapi kembali ke dashboard lagi untuk akses menu ambil alih khusus pinca
                    Intent intent = new Intent(getContext(), AmbilAlihActivity.class);
                    startActivity(intent);
                }
                else if(indikatorSelesaiLoading==false&&appPreferences.getFidRole().equalsIgnoreCase("76")){//ini untuk akses tombol setelah login khusus pinca
                    Toasty.info(getContext(),"Tunggu hingga loading selesai, sebelum mengakses menu ambil alih putusan",Toast.LENGTH_LONG).show();
                }

                else{
                    Toasty.info(getContext(),"Tunggu hingga loading selesai, sebelum mengakses menu ambil alih putusan",Toast.LENGTH_LONG).show();
                }

//            Intent intent = new Intent(getContext(), AmbilAlihActivity.class);
//            startActivity(intent);
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
//                    getActivity().finish();
                        Intent intent=new Intent(getContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
                logout.show();
//            Toast.makeText(getActivity(), "Fitur ini masih dalam pengembangan", Toast.LENGTH_SHORT).show();
            }
            else{
                AppUtil.showToastShort(getContext(), menu);
            }
        }


    }

    private void checkCollapse(){
        appbar.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d(getClass().getCanonicalName(), state.name());
                if (state.name().equalsIgnoreCase("COLLAPSED") || state.name().equalsIgnoreCase("IDLE")){
                    tv_toolbartitle.setText("i-Kurma");
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

    public void keluarAmbilAlih(){
        dialog1=new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE);
        dialog1.setTitleText("Konfirmasi");
        dialog1.setContentText("Anda yakin ingin keluar dari ambil alih user?");
        dialog1.setConfirmText("Ya");
        dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                //start login ulang ke user yang mengambil alih, usernamenya masih tersimpan di preferences
                dialog1.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                dialog1.setTitleText("Harap Tunggu");
                dialog1.showContentText(false);
                dialog1.showCancelButton(true);
                dialog1.setCancelText("Batal");
                LoginRequest req = new LoginRequest();
                req.setUsername(appPreferences.getUsername());
                req.setPassword(AppUtil.hashMd5("-"));
                req.setDeviceId("-");
                req.setAppId("BRISI_PEMUTUS");
                Call<ParseResponse> call = apiClientAdapter.getApiInterface().secretLogin(req);
                call.enqueue(new Callback<ParseResponse>() {
                    @Override
                    public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                        if(response.isSuccessful()){

                            if(response.body().getStatus().equalsIgnoreCase("00")){
                                String listDataString = response.body().getData().toString();
                                Gson gson = new Gson();
                                Type type = new TypeToken<LoginCred>() {}.getType();

                                dataUser = gson.fromJson(listDataString, type);

                                //hanya pemutus yang bisa login,yaitu : uh, pincapem, m3, pinca
                                if(dataUser.getFid_role().toString().equalsIgnoreCase("72")||dataUser.getFid_role().toString().equalsIgnoreCase("71")||dataUser.getFid_role().toString().equalsIgnoreCase("76")||dataUser.getFid_role().toString().equalsIgnoreCase("79")){
                                    Toasty.success(getContext(),"Selamat Datang Kembali "+dataUser.getNama(),Toast.LENGTH_LONG, true).show();

                                    //ganti preference logged in
                                    appPreferences.setLoggedin("yes");

                                    // Toast.makeText(LoginActivity.this, "Selamat datang "+dataUser.getNama(), Toast.LENGTH_SHORT).show();
                                    //menyimpan data login user dalam session preference
                                    appPreferences.setToken(dataUser.getToken());
                                    appPreferences.setRoleType(dataUser.getStatus());
                                    appPreferences.setNamaKanwil(dataUser.getNama_kanwil());
                                    appPreferences.setFidKantor(dataUser.getFid_kantor());
                                    appPreferences.setJabatan(dataUser.getJabatan());
                                    appPreferences.setNamaKantor(dataUser.getNama_kantor());
                                    appPreferences.setKodeSkk(dataUser.getKode_skk());
                                    appPreferences.setDsnCode(dataUser.getDsn_code());
                                    appPreferences.setKodeKanwil(dataUser.getKode_kanwil());
                                    appPreferences.setFidRole(dataUser.getFid_role());
                                    appPreferences.setUid(dataUser.getUid());
                                    appPreferences.setNik(dataUser.getNik());
                                    appPreferences.setNama(dataUser.getNama());
                                    appPreferences.setKodeCabang(dataUser.getKode_cabang());
                                    appPreferences.setUker(dataUser.getUker());
                                    appPreferences.setNamaSkk(dataUser.getNama_skk());
                                    appPreferences.setKodeAo(dataUser.getKode_ao());
                                    appPreferences.setKantor(dataUser.getKantor());
                                    appPreferences.setUsername(appPreferences.getUsername());

                                    //reset status ambil alih
                                    appPreferences.setStatusAmbilAlih("TIDAK");

                                    //reset sessioon perubahan kode SKK
                                    appPreferences.setStatusKodeSkkPinca("belum berubah");

                                    dialog1.dismissWithAnimation();

                                    Intent intent=new Intent(getContext(), CoreLayoutActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                    dialog1.setTitleText("Gagal");
                                    dialog1.setContentText("Anda tidak memiliki akses untuk aplikasi pemutus");
                                    dialog1.setConfirmText("Kembali");
                                    dialog1.showCancelButton(false);
                                }


                            }

                            else{
                                dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialog1.setTitle("Gagal Keluar Ambil Alih");
                                dialog1.setContentText(response.body().getMessage());
                                dialog1.setConfirmText("Ok");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ParseResponse> call, Throwable t) {
                        Log.d("LOG D", t.getMessage());
                        dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        dialog1.setTitle("Gagal");
                        dialog1.setContentText("Gagal terhubung ke server");
                        dialog1.setConfirmText("Ok");
                    }
                });

            }
        });
        dialog1.setCancelable(true);
        dialog1.setCancelText("Batal");
//        dialog1.getProgressHelper().setBarColor(Color.parseColor("#fd9c00"));
        dialog1.show();

//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
//                            dialog1.setTitle("Time out");
//                            dialog1.setContentText("Silahkan coba lagi");
//                            dialog1.setConfirmText("Ok");
//
//                        }
//                    },60000);


    }

    @Override
    public void onRefresh() {

        rv_pipeline.setVisibility(View.GONE);
        rv_deviasi.setVisibility(View.GONE);
        rv_disposisi.setVisibility(View.GONE);
        textWhale.setVisibility(View.GONE);
        textWhaleDeviasi.setVisibility(View.GONE);
        textWhaleDisposisi.setVisibility(View.GONE);

        checkCollapse();
        initializePipelineHome();
        initializeMenu();
        onclickStuff();
        swipeRefreshLayout.setRefreshing(false);
//        swipeRefreshLayout.setOnRefreshListener(this);
//        getActivity().recreate();
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

        iv_moredeviasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), PutusanActivity.class);
                intent.putExtra("kodePutusan","putusanDeviasi");
                startActivity(intent);
            }
        });

//        iv_moreputusan.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Intent intent=new Intent(getContext(), PerformanceAoActivity.class);
////                intent.putExtra("kodePutusan","putusanKredit");
//                startActivity(intent);
//        return true;
//    }
//});

        iv_moredisposisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), DaftarDisposisiActivity.class);
                intent.putExtra("menuAsal","daftarDisposisi");

                //delete the comment below to enable clicking more in disposisi list
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        call.cancel();
        super.onDestroy();

    }
}
