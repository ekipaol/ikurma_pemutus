package com.application.bris.brisi_pemutus.splash_screen;


import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.BuildConfig;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.brisnotif.ReqRegisterBrisnotif;
import com.application.bris.brisi_pemutus.api.model.request.check_update.CheckUpdate;
import com.application.bris.brisi_pemutus.api.model.request.firebase.ReqFirebase;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.baseapp.RouteApp;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.page_aktivasi.ActivityWelcome;
import com.application.bris.brisi_pemutus.page_login.view.LoginActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.scottyab.rootbeer.RootBeer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class SplashScreen extends AppCompatActivity {

    @BindView(R.id.tv_version)
    TextView tv_version;
    public static final int REQUEST_PERMISSION = 1;
    private AppPreferences appPreferences;
    private ApiClientAdapter apiClientAdapter;
    private String installedVersionName = "";
    private PackageInfo packageInfo;
    private String firebaseToken="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //autostartup
//        addAutoStartup();
        //set logged in preference no
        appPreferences = new AppPreferences(this);
        appPreferences.setLoggedin("no");
//        Toast.makeText(this, FirebaseInstanceId.getInstance().getToken(), Toast.LENGTH_SHORT).show();
//        Log.d("firebaseid",FirebaseMessaging.getInstance().getToken().toString());

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("firebaseid", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        firebaseToken=token;

                        if (firebaseToken != null&&!firebaseToken.isEmpty()) {
                            updateFirebaseId();
                            registerBrisnotif();
                        }

                        // Log and toast
                        Log.d("firebaseidEKIPAOL", token);
                        Log.d("firebaseidEKIPAOL", firebaseToken);
//                        Toast.makeText(SplashScreen.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        appPreferences = new AppPreferences(this);
        apiClientAdapter = new ApiClientAdapter(this, 0, 30, TimeUnit.SECONDS);
        ButterKnife.bind(this);


        //harus buat notification channel agar notifikasi tampil di foreground untuk android diatas OReo
        //nama channel yang dibuat disini, harus dipanggil di kelas notification service, di object NotificationCompatBuilder >line 58
        createNotificationChannel();

        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        tv_version.setText("Version " + packageInfo.versionName);





        if (checkPermission()) {
            //check root
            if(deviceIsRooted()){
                SweetAlertDialog dialog = new SweetAlertDialog(SplashScreen.this, SweetAlertDialog.WARNING_TYPE);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setTitleText("Perangkat Root");
                dialog.setContentText("Aplikasi tidak boleh dijalankan di perangkat yang di root\n");
                dialog.setConfirmText("OK");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismissWithAnimation();
                        finish();

                    }
                });
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       finish();
                    }
                }, 5000);
            }
            else{

                if(BuildConfig.IS_PRODUCTION==false){
                    RouteApp router = new RouteApp(SplashScreen.this);
                    router.openActivityAndClearAllPrevious(LoginActivity.class);
                    Toast.makeText(this, "Skip QR SCAN", Toast.LENGTH_SHORT).show();
                }
                else{
                    checkAvailableUpdate();
                }
            }



        }

        tv_version.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
//                startActivity(intent);
                return false;
            }
        });

    }

    private void checkAvailableUpdate() {
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().checkUpdate(new CheckUpdate("BRISI_PEMUTUS"));
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        installedVersionName = packageInfo.versionName;
                        int installedVersionNameInt = Integer.parseInt(packageInfo.versionName.replace(".", ""));
                        int responseVersionInt = Integer.parseInt(response.body().getData().get("versionName").getAsString().replace(".", ""));

                        //metode lama, kalau versinya baru, tetap keluar error
                        //real if
//                        if (response.body().getData().get("versionName").getAsString().equalsIgnoreCase(installedVersionName)){


                        if (installedVersionNameInt >= responseVersionInt) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    gotoNextActivity();
                                }
                            }, 1000);
                        } else {
                            SweetAlertDialog dialog = new SweetAlertDialog(SplashScreen.this, SweetAlertDialog.WARNING_TYPE);
                            dialog.setTitleText("Versi lama");
                            dialog.setContentText("Aplikasi anda masih dalam versi yang lama, silahkan lakukan update melalui Play Store untuk kembali dapat menggunakan i-Kurma.");

                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    String appPackageName = getPackageName();
                                    finish();
                                    try {
                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                    } catch (android.content.ActivityNotFoundException anfe) {
                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                    }
                                }
                            });
                            dialog.show();
//                            CustomDialog.DialogUpdate(SplashScreen.this, getString(R.string.notif_update), SplashScreen.this);
                        }
                    } else {
//                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.showToastShort(SplashScreen.this, "Terjadi Kesalahan");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gotoNextActivity();
                            }
                        }, 1000);
                    }
                } catch (Exception e) {
                    AppUtil.showToastShort(SplashScreen.this, e.getMessage());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gotoNextActivity();
                        }
                    }, 1000);
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.showToastShort(SplashScreen.this, getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gotoNextActivity();
                    }
                }, 1000);
            }
        });
    }

    private void updateFirebaseId() {
        ReqFirebase req = new ReqFirebase();
        req.setAppID("BRISI_PEMUTUS");
        req.setDeviceID(getDeviceId());
        req.setFirebaseMessagingID(firebaseToken);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateFirebase(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {

                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            Log.d("firebaseid","sukses update firebase id");

                        }
                    } else {
//                        Error error = ParseResponseError.confirmEror(response.errorBody());
//                        AppUtil.showToastShort(SplashScreen.this, "Terjadi Kesalahan");

                    }
                } catch (Exception e) {
                    AppUtil.showToastShort(SplashScreen.this, e.getMessage());
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            gotoNextActivity();
//                        }
//                    }, 1000);
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.showToastShort(SplashScreen.this, getString(R.string.txt_connection_failure));
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        gotoNextActivity();
//                    }
//                }, 1000);
            }
        });
    }

    private void registerBrisnotif() {
        ReqRegisterBrisnotif req = new ReqRegisterBrisnotif();
        req.setAppclient("ikurma");
        req.setIdentifier(getDeviceId()+"ikurmapemutus");

        //pantekan token brisnotif
//        req.setToken("fsH-BPF_Ty6FDrhvLvMYC6:APA91bHCG-dxXMQdTSuIklmRvjqOxOLd5LA-_RrmIRQDHYDmCrRNpVvuXWLCSPrBLdZXv6OogLhP07sPuoX0DdaSvhTdsAYOOb8bbEnUdVIHe4t5tXk26hDUG0yD_4Q5NfH-fMv2kRPe");

//        Toast.makeText(this, "ada pantekan token notifkasi di splash screen", Toast.LENGTH_SHORT).show();

      //real token firebase
        req.setToken(firebaseToken);

        //Dalvik/2.1.0 (Linux; U; Android 6.0; Redmi Note 4X MIUI/V10.2.1.0.MBFMIXM), v3.0.7
        req.setAgent("i-Kurma Pemutus/" + packageInfo.versionName + " (Android " + getAndroidVersion() + ";" + getDeviceName() + ")");
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().brisnotifRegister(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {

                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            Log.d("firebaseid", "device id: "+ getDeviceId());
                            Log.d("firebaseid", "sukses register brisnotif");

                        } else {
                            Log.d("firebaseid", "gagal brisnotif register");
                        }
                    } else {
//                        Error error = ParseResponseError.confirmEror(response.errorBody());
//                        AppUtil.showToastShort(SplashScreen.this, "Terjadi Kesalahan");

                    }
                } catch (Exception e) {
                    AppUtil.showToastShort(SplashScreen.this, e.getMessage());
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            gotoNextActivity();
//                        }
//                    }, 1000);
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
//                AppUtil.showToastShort(SplashScreen.this, getString(R.string.txt_connection_failure));
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        gotoNextActivity();
//                    }
//                }, 1000);
            }
        });
    }

    private boolean checkPermission() {
        int readCameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int readLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int readPhoneStatePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        int writeExternalStoragePerm = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        List<String> listPermissionNeeded = new ArrayList<>();
        if (readCameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionNeeded.add(Manifest.permission.CAMERA);
        }
        if (readLocationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (readPhoneStatePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionNeeded.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (writeExternalStoragePerm != PackageManager.PERMISSION_GRANTED) {
            listPermissionNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionNeeded.toArray(new String[listPermissionNeeded.size()]), REQUEST_PERMISSION);
            return false;
        }
        return true;
    }

    private void gotoNextActivity() {
        RouteApp router = new RouteApp(SplashScreen.this);
        if (appPreferences.getIsActivated().equalsIgnoreCase("1")) {
            router.openActivityAndClearAllPrevious(LoginActivity.class);
        } else {
            router.openActivityAndClearAllPrevious(ActivityWelcome.class);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION: {
                Map<String, Integer> perms = new HashMap<>();
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_PHONE_STATE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++) {
                        perms.put(permissions[i], grantResults[i]);
                        if (perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && perms.get(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED &&
                                perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                            gotoNextActivity();
                        } else {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE) ||
                                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                showDialogOK(getResources().getString(R.string.dialog_perms_msg),
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                switch (which) {
                                                    case DialogInterface.BUTTON_POSITIVE:
                                                        checkPermission();
                                                        break;
                                                    case DialogInterface.BUTTON_NEGATIVE:
                                                        finish();
                                                        break;
                                                }
                                            }
                                        });
                            } else {
                                confirmAgain(getResources().getString(R.string.dialog_need_permission));
                            }
                        }
                    }
                }
            }
        }
    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Keluar", okListener)
                .create()
                .show();
    }

    private void confirmAgain(String msg) {
        final androidx.appcompat.app.AlertDialog.Builder dialog = new androidx.appcompat.app.AlertDialog.Builder(this);
        dialog.setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:com.application.bris.brisi")));
                    }
                })
                .setNegativeButton("Keluar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        finish();
                    }
                });
        dialog.show();
    }

    private String getDeviceId() {
        HashMap<String, String> deviceInfo = AppUtil.getDeviceInfo(this);
        String deviceId = deviceInfo.get(Constants.DEVICE_ID);
        return deviceId;
    }

    private void addAutoStartup() {
        //method digunakan untuk autostart aplikasi di hape Oppo ->pengaruh ke notifikasi?
        try {
            Intent intent = new Intent();
            String manufacturer = android.os.Build.MANUFACTURER;
            if ("oppo".equalsIgnoreCase(manufacturer)) {
                intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
            }
            List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if (list.size() > 0) {
                startActivity(intent);
            }
        } catch (Exception e) {
            Log.e("exc", String.valueOf(e));
        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "i-kurma notification";
            String description = "notifikasi i-kurma pemutus";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("kurma2", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            Log.d("notificationChannel", "ithas been made");
        }
    }

    private boolean deviceIsRooted(){
        RootBeer rootBeer = new RootBeer(SplashScreen.this);
        if (rootBeer.isRooted()) {
            if(BuildConfig.ROOT_DETECTION){
                return true;
            }
            else{
                return false;
            }
        } else {
            return false;

        }
    }

    private String getAndroidVersion() {
        Field[] fields = Build.VERSION_CODES.class.getFields();
        String codeName = "";
        for (Field field : fields) {
            try {
                if (field.getInt(Build.VERSION_CODES.class) == Build.VERSION.SDK_INT) {
                    codeName = field.getName();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return codeName;
    }

    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }


    }
}