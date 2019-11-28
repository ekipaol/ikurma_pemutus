package com.application.bris.brisi_pemutus.view.corelayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.dashboard.RequestDashboard;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.dashboard.DashboardCred;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.page_login.view.LoginActivity;
import com.application.bris.brisi_pemutus.page_performance.PerformanceActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.BackStackFragment;
import com.application.bris.brisi_pemutus.view.corelayout.dashboard.FragmentDashboard;
import com.application.bris.brisi_pemutus.view.corelayout.home.FragmentHome;
import com.application.bris.brisi_pemutus.view.corelayout.profile.FragmentProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoreLayoutActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private Fragment fragment;
    private boolean doubleBackToExitPressedOnce = false;

    @BindView(R.id.bn_main)
    BottomNavigationView bottomNavigationView;
    ApiClientAdapter apiClientAdapter;
    List<Putusan> dataPutusan;
    DashboardCred dataDashboard;
    AppPreferences appPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corelayout);
        ButterKnife.bind(this);
      //  loaddData();
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        appPreferences=new AppPreferences(this);


        //get firebase instance, only used in splashscreen
//        FirebaseInstanceId.getInstance().getInstanceId()
//                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w("EMBERFIRE", "getInstanceId failed", task.getException());
//                            return;
//                        }
//
//                        // Get new Instance ID token
//                        String token = task.getResult().getToken();
//
//                        // Log and toast
//                        String msg = token;
//                        Log.d("EMBERFIRE", token);
//                      //  Toast.makeText(CoreLayoutActivity.this, msg, Toast.LENGTH_SHORT).show();
//                    }
//                });
//
        loadFragment(new FragmentHome());


       // FirebaseInstanceId.getInstance().getInstanceId();
    }

//    public void loaddData(){
//        apiClientAdapter= new ApiClientAdapter(this);
//        RequestDashboard req = new RequestDashboard();
//        AppPreferences appPreferences =new AppPreferences(this);
//        req.setUid(Integer.parseInt(appPreferences.getUid()));
//
//        Call<ParseResponse> call = apiClientAdapter.getApiInterface().dashboardRequest(req);
//        call.enqueue(new Callback<ParseResponse>() {
//            @Override
//            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
//
//                if(response.isSuccessful()){
//
//                    if(response.body().getStatus().equalsIgnoreCase("00")){
//                        String listDataString = response.body().getData().toString();
//                        String listDataPutusanString = response.body().getData().get("listPutusan").toString();
//                        Gson gson = new Gson();
//                        Type type1 = new TypeToken<DashboardCred>() {}.getType();
//                        Type type2 = new TypeToken<List<Putusan>>() {}.getType();
//
//                        dataDashboard = gson.fromJson(listDataString, type1);
//                        dataPutusan=gson.fromJson(listDataPutusanString, type2);
//
//
//
//
//
//
//                        Log.d("main notif ekipaol",Integer.toString(dataDashboard.getNotifDashboard()));
//                        // holder.tv_badgemenu.setText(dataUser.getNotifDashboard());
//                        Log.d("main PUTUSAN ekipaol",Integer.toString(dataPutusan.size()));
//
//
//                    }
//                    else{
//                        Toast.makeText(CoreLayoutActivity.this, "gagal", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ParseResponse> call, Throwable t) {
//                Log.d("LOG D", t.getMessage());
//
//            }
//        });
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (android.support.v4.app.Fragment frg : getSupportFragmentManager().getFragments()) {
            frg.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        if (!BackStackFragment.handleBackPressed(getSupportFragmentManager())) {{

            if(appPreferences.getStatusAmbilAlih().equalsIgnoreCase("tidak")){

                if(doubleBackToExitPressedOnce){
//                finish();
                    Intent intent=new Intent(CoreLayoutActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    return;
                }
                doubleBackToExitPressedOnce = true;
                AppUtil.showToastShort(this, "Tekan sekali lagi untuk keluar aplikasi");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
            else{
                AppUtil.showToastShort(this, "Klik keluar ambil alih untuk kembali ke user awal");
            }


        }}
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragment  = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
//                AppUtil.showToastShort(getApplicationContext(), "home");
                fragment = new FragmentHome();
                break;
            case R.id.navigation_dashboard:
//                AppUtil.showToastShort(getApplicationContext(), "Dashboard dalam pengembangan");
                //hide dashboard for a while
//                fragment = new FragmentDashboard();

                AppPreferences appPreferences=new AppPreferences(CoreLayoutActivity.this);
//                Intent intent=new Intent(CoreLayoutActivity.this, PerformanceActivity.class);
//                intent.putExtra("kodeKanwil",appPreferences.getKodeKanwil());
//                startActivity(intent);
                Toasty.info(CoreLayoutActivity.this,"Masih dalam pengembangan").show();
                break;
            case R.id.navigation_notifications:
//                AppUtil.showToastShort(getApplicationContext(), "user");
                fragment = new FragmentProfile();
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
}
