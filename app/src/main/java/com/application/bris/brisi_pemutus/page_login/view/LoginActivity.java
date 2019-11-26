package com.application.bris.brisi_pemutus.page_login.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.aktivasi.Aktivasi;
import com.application.bris.brisi_pemutus.api.model.request.list_user.listUser;
import com.application.bris.brisi_pemutus.api.model.request.login.LoginRequest;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.login_cred.LoginCred;
import com.application.bris.brisi_pemutus.model.user.User;
import com.application.bris.brisi_pemutus.page_aktivasi.ActivityWelcome;
import com.application.bris.brisi_pemutus.page_aktivasi.ScanActivity;
import com.application.bris.brisi_pemutus.page_daftar_user.adapters.AdapterDaftarUser;
import com.application.bris.brisi_pemutus.page_daftar_user.view.UserActivity;
import com.application.bris.brisi_pemutus.page_putusan.PutusanFrontMenu;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.Constants;
import com.application.bris.brisi_pemutus.view.corelayout.CoreLayoutActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import es.dmoral.toasty.Toasty;
import me.toptas.fancyshowcase.FancyShowCaseView;
import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
EditText username,password;
FancyButton btLogin;
LoginCred dataUser;
AppPreferences apppref;
RelativeLayout loading;
SweetAlertDialog dialog1;
ImageView logo, logo_letter,logo_ikurma;
int counterSecretLogin;



    private IntentResult intentResult;



    private ApiClientAdapter apiClientAdapter= new ApiClientAdapter(LoginActivity.this,0);

    //login untuk prod
//private ApiClientAdapter apiClientAdapter= new ApiClientAdapter(LoginActivity.this,"https://intel.brisyariah.co.id:55056/MobileBRISIAPI/webresources/",0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


      //  apiClientAdapter = new ApiClientAdapter(LoginActivity.this,0);
        apppref=new AppPreferences(LoginActivity.this);
        apppref.setLoggedin("no");
        username=findViewById(R.id.et_username);
        password=findViewById(R.id.et_password);
        btLogin=findViewById(R.id.btn_login);
        loading=findViewById(R.id.progressbar_loading);
        logo=findViewById(R.id.iv_top_logo_wheel);
        logo_letter=findViewById(R.id.iv_top_logo_letter);
        logo_ikurma=findViewById(R.id.iv_top_logo_login_2);


       // animasi logo berputar bagaikan kincir-kincir ditiup angin lembut membawa kenangan senja bersama kopi untukku berkelana
        RotateAnimation rotate = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.37f
        );

        rotate.setDuration(14000);
        rotate.setRepeatCount(Animation.INFINITE);
//        logo.startAnimation(rotate);

        username.setSingleLine();
//        Toast.makeText(this, FirebaseInstanceId.getInstance().getToken(), Toast.LENGTH_SHORT).show();


        //membuat status bar dan tombol tombol navigasi transparan
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }


        //membuat secret login, dengan menekan logo >=4 kali, lalu mengklik tahan logo
        logo_ikurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterSecretLogin++;
            }
        });

        logo_ikurma.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(counterSecretLogin>=4&&password.getText().toString().equalsIgnoreCase("superduper"))
                {
                    secretLogin();
                }
                else{
                    Toast.makeText(LoginActivity.this, "@ekipaol", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        //membuat status bar transparan
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        if(!apppref.getUsername().isEmpty()){
                username.setText(apppref.getUsername());
                password.requestFocus();
            //ubah warna icon di edittext
            Drawable mDrawable = getResources().getDrawable(R.drawable.ic_lock_black_24dp);
            mDrawable = DrawableCompat.wrap(mDrawable);
            DrawableCompat.setTint(mDrawable, Color.parseColor("#fd9c00"));
            password.setCompoundDrawablesWithIntrinsicBounds(mDrawable, null, null, null);
            password.setBackgroundResource(R.drawable.shapeemailhighligh);
        }





        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().isEmpty()){
                    username.setError("Harap isi username");
                    username.requestFocus();
                }
                else if(password.getText().toString().isEmpty()){
                    password.setError("Harap masukkan password");
                    password.requestFocus();
                }
                else {
                     dialog1=new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    dialog1.setTitleText("Harap tunggu");
                    dialog1.setCancelable(true);
                    dialog1.setCancelText("Batal");
                    dialog1.getProgressHelper().setBarColor(Color.parseColor("#fd9c00"));
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

                    //start login
                    LoginRequest req = new LoginRequest();
                    req.setUsername(username.getText().toString());
                    Log.d("pass md5",AppUtil.hashMd5(username.getText().toString()).toUpperCase());
                    req.setPassword(AppUtil.hashMd5(password.getText().toString()).toUpperCase());
                    req.setDeviceId(getDeviceId());
                    req.setAppId("BRISI_PEMUTUS");
                    Call<ParseResponse> call = apiClientAdapter.getApiInterface().loginRequest(req);
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
                                        Toasty.success(LoginActivity.this,"Selamat datang "+dataUser.getNama(),Toast.LENGTH_LONG, true).show();

                                        //ganti preference logged in
                                        apppref.setLoggedin("yes");

                                        // Toast.makeText(LoginActivity.this, "Selamat datang "+dataUser.getNama(), Toast.LENGTH_SHORT).show();
                                        //menyimpan data login user dalam session preference
                                        apppref.setToken(dataUser.getToken());
                                        apppref.setRoleType(dataUser.getStatus());
                                        apppref.setNamaKanwil(dataUser.getNama_kanwil());
                                        apppref.setFidKantor(dataUser.getFid_kantor());
                                        apppref.setJabatan(dataUser.getJabatan());
                                        apppref.setNamaKantor(dataUser.getNama_kantor());
                                        apppref.setKodeSkk(dataUser.getKode_skk());
                                        apppref.setDsnCode(dataUser.getDsn_code());
                                        apppref.setKodeKanwil(dataUser.getKode_kanwil());
                                        apppref.setFidRole(dataUser.getFid_role());
                                        apppref.setUid(dataUser.getUid());
                                        apppref.setNik(dataUser.getNik());
                                        apppref.setNama(dataUser.getNama());
                                        apppref.setKodeCabang(dataUser.getKode_cabang());
                                        apppref.setUker(dataUser.getUker());
                                        apppref.setNamaSkk(dataUser.getNama_skk());
                                        apppref.setKodeAo(dataUser.getKode_ao());
                                        apppref.setKantor(dataUser.getKantor());
                                        apppref.setUsername(username.getText().toString());

                                        //set status ambil alih
                                        apppref.setStatusAmbilAlih("TIDAK");

                                        dialog1.dismissWithAnimation();

                                        //reset sessioon perubahan kode SKK
                                        apppref.setStatusKodeSkkPinca("belum berubah");

                                        Intent intent=new Intent(LoginActivity.this, CoreLayoutActivity.class);
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
                                else if(response.body().getStatus().equalsIgnoreCase("21")){

                                    dialog1.changeAlertType(SweetAlertDialog.WARNING_TYPE);
                                    dialog1.setTitle("Belum Terdaftar");
                                    dialog1.setContentText("User anda tidak terdaftar di perangkat ini, silahkan lakukan aktivasi melalui menu BRISI di halaman APPEL anda\n");
                                    dialog1.setConfirmText("Aktivasi");
                                    dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            dialog1.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                            dialog1.setContentText("");
                                            dialog1.setTitleText("Harap tunggu");
                                            dialog1.setConfirmText("Memproses");
                                            dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                    //nothing happens when clicking activation button
                                                }
                                            });
                                            openQRScanner();
                                            dialog1.dismissWithAnimation();
                                        }
                                    });

                                }
                                else{
                                    dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                    dialog1.setTitle("Login Gagal");
                                    dialog1.setContentText(response.body().getMessage());
                                    dialog1.setConfirmText("Ok");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ParseResponse> call, Throwable t) {
                            Log.d("LOG D", t.getMessage());
                            dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog1.setTitle("Login Gagal");
                            dialog1.setContentText("Gagal terhubung ke server");
                            dialog1.setConfirmText("Ok");
                        }
                    });


                }

            }
        });

      username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
          @Override
          public void onFocusChange(View view, boolean b) {

              //ubah warna icon di edittext
              Drawable mDrawable = getResources().getDrawable(R.drawable.ic_person_black_24dp);
              mDrawable = DrawableCompat.wrap(mDrawable);
              DrawableCompat.setTint(mDrawable, Color.parseColor("#fd9c00"));

              Drawable mDrawablePassword = getResources().getDrawable(R.drawable.ic_lock_black_24dp);
              mDrawablePassword=DrawableCompat.wrap(mDrawablePassword);
              DrawableCompat.setTint(mDrawablePassword,Color.parseColor("black"));

              //ubah warna border edittext
              username.setCompoundDrawablesWithIntrinsicBounds(mDrawable, null, null, null);
              password.setCompoundDrawablesWithIntrinsicBounds(mDrawablePassword, null, null, null);
              username.setBackgroundResource(R.drawable.shapeemailhighligh);
              password.setBackgroundResource(R.drawable.shapeemail);
          }
      });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                //ubah warna icon di edittext
                Drawable mDrawable = getResources().getDrawable(R.drawable.ic_lock_black_24dp);

                mDrawable = DrawableCompat.wrap(mDrawable);
                DrawableCompat.setTint(mDrawable, Color.parseColor("#fd9c00"));

                Drawable mDrawableUsername = getResources().getDrawable(R.drawable.ic_person_black_24dp);
                mDrawableUsername=DrawableCompat.wrap(mDrawableUsername);
                DrawableCompat.setTint(mDrawableUsername,Color.parseColor("black"));
                password.setCompoundDrawablesWithIntrinsicBounds(mDrawable, null, null, null);
                username.setCompoundDrawablesWithIntrinsicBounds(mDrawableUsername, null, null, null);

                username.setBackgroundResource(R.drawable.shapeemail);
                password.setBackgroundResource(R.drawable.shapeemailhighligh);
            }
        });
    }

    public void openQRScanner(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setCaptureActivity(ScanActivity.class).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String qrcontent = "";
        if (intentResult != null) {
            if (intentResult.getContents() != null){
                qrcontent = intentResult.getContents();
                processScan(qrcontent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void processScan(String code){
        loading.setVisibility(View.VISIBLE);
        Aktivasi activationRequest = new Aktivasi(code, getDeviceId(), getDeviceName(), "BRISI_PEMUTUS");
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().activation(activationRequest);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                dialog1.dismissWithAnimation();
                loading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        Toasty.success(LoginActivity.this,"Aktivasi berhasil, silahkan login").show();

//                        AppUtil.notifsuccess(ActivityWelcome.this, findViewById(android.R.id.content), "Aktivasi Sukses, Enjoy BRISI");
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                routeApp.openActivity(LoginActivity.class);
//                            }
//                        }, 1000);
                    } else {
                        AppUtil.notiferror(LoginActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                        //belum bisa buka appel QR, jadi ketika error occured tetap masuk ke halaman login, uncomment apputil diatas after done with everything

                        //delete this when all is good
//                        appPreferences.setIsActivated("1");
//                        Toasty.success(ActivityWelcome.this,"Aktivasi berhasil, silahkan login").show();
//
//                        routeApp.openActivityAndClearAllPrevious(LoginActivity.class);
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                routeApp.openActivityAndClearAllPrevious(LoginActivity.class);
//                            }
//                        }, 1000);
                        //end of delete


                    }
                } else {
//                    Error error = ParseResponseError.confirmEror(response.errorBody());
//                    AppUtil.notiferror(ActivityWelcome.this, findViewById(android.R.id.content), error.getMessage());
                    AppUtil.notiferror(LoginActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                dialog1.dismissWithAnimation();
                AppUtil.notiferror(LoginActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));


            }
        });
    }

    private String getDeviceId() {
        HashMap<String, String> deviceInfo = AppUtil.getDeviceInfo(this);
        String deviceId = deviceInfo.get(Constants.DEVICE_ID);
        return deviceId;
    }

    private String getDeviceName() {
        HashMap<String, String> deviceInfo = AppUtil.getDeviceInfo(this);
        String deviceName = deviceInfo.get(Constants.DEVICE_NAME);
        return deviceName;
    }

    private void secretLogin(){
        dialog1=new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        dialog1.setTitleText("Harap tunggu");
        dialog1.setCancelable(true);
        dialog1.setCancelText("Batal");
        dialog1.getProgressHelper().setBarColor(Color.parseColor("#fd9c00"));
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

        //start login
        LoginRequest req = new LoginRequest();
        req.setUsername(username.getText().toString());
        Log.d("pass md5",AppUtil.hashMd5(username.getText().toString()).toUpperCase());
        req.setPassword(AppUtil.hashMd5(password.getText().toString()).toUpperCase());
        req.setDeviceId(getDeviceId());
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
                            Toasty.success(LoginActivity.this,"Selamat datang "+dataUser.getNama(),Toast.LENGTH_LONG, true).show();

                            //ganti preference logged in
                            apppref.setLoggedin("yes");

                            // Toast.makeText(LoginActivity.this, "Selamat datang "+dataUser.getNama(), Toast.LENGTH_SHORT).show();
                            //menyimpan data login user dalam session preference
                            apppref.setToken(dataUser.getToken());
                            apppref.setRoleType(dataUser.getStatus());
                            apppref.setNamaKanwil(dataUser.getNama_kanwil());
                            apppref.setFidKantor(dataUser.getFid_kantor());
                            apppref.setJabatan(dataUser.getJabatan());
                            apppref.setNamaKantor(dataUser.getNama_kantor());
                            apppref.setKodeSkk(dataUser.getKode_skk());
                            apppref.setDsnCode(dataUser.getDsn_code());
                            apppref.setKodeKanwil(dataUser.getKode_kanwil());
                            apppref.setFidRole(dataUser.getFid_role());
                            apppref.setUid(dataUser.getUid());
                            apppref.setNik(dataUser.getNik());
                            apppref.setNama(dataUser.getNama());
                            apppref.setKodeCabang(dataUser.getKode_cabang());
                            apppref.setUker(dataUser.getUker());
                            apppref.setNamaSkk(dataUser.getNama_skk());
                            apppref.setKodeAo(dataUser.getKode_ao());
                            apppref.setKantor(dataUser.getKantor());
                            apppref.setUsername(username.getText().toString());

                            //reset status ambil alih
                            apppref.setStatusAmbilAlih("TIDAK");

                            //reset sessioon perubahan kode SKK
                            apppref.setStatusKodeSkkPinca("belum berubah");

                            dialog1.dismissWithAnimation();

                            Intent intent=new Intent(LoginActivity.this, CoreLayoutActivity.class);
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
                    else if(response.body().getStatus().equalsIgnoreCase("21")){

                        dialog1.changeAlertType(SweetAlertDialog.WARNING_TYPE);
                        dialog1.setTitle("Belum Terdaftar");
                        dialog1.setContentText("User anda tidak terdaftar di perangkat ini, silahkan lakukan aktivasi melalui menu BRISI di halaman APPEL anda\n");
                        dialog1.setConfirmText("Aktivasi");
                        dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialog1.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                dialog1.setContentText("");
                                dialog1.setTitleText("Harap tunggu");
                                dialog1.setConfirmText("Memproses");
                                dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        //nothing happens when clicking activation button
                                    }
                                });
                                openQRScanner();
                                dialog1.dismissWithAnimation();
                            }
                        });

                    }
                    else{
                        dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        dialog1.setTitle("Login Gagal");
                        dialog1.setContentText(response.body().getMessage());
                        dialog1.setConfirmText("Ok");
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                dialog1.setTitle("Login Gagal");
                dialog1.setContentText("Gagal terhubung ke server");
                dialog1.setConfirmText("Ok");
            }
        });



    }

}
