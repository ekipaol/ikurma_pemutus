package com.application.bris.brisi_pemutus.page_ao_silang.PilihAoSilang;


import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.application.bris.brisi_pemutus.BuildConfig;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.model.Error;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.ParseResponseError;


import com.application.bris.brisi_pemutus.api.model.request.req_ao_silang.ReqAppraisalKembalikanKeAo;
import com.application.bris.brisi_pemutus.api.model.request.req_ao_silang.ReqKirimAppraisal;
import com.application.bris.brisi_pemutus.api.model.request.req_ao_silang.ReqListRsc;
import com.application.bris.brisi_pemutus.api.model.request.req_ao_silang.ReqListSkk;
import com.application.bris.brisi_pemutus.api.model.request.req_ao_silang.ReqListUser;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;

import com.application.bris.brisi_pemutus.database.model.AoModel;
import com.application.bris.brisi_pemutus.dialog.DialogPilihAo;
import com.application.bris.brisi_pemutus.listeners.ConfirmListener;
import com.application.bris.brisi_pemutus.listeners.PilihAoSilangListener;
import com.application.bris.brisi_pemutus.model.ao_silang_list_kanwil.ListKanwil;
import com.application.bris.brisi_pemutus.model.ao_silang_list_user.ListUser;
import com.application.bris.brisi_pemutus.page_konsumer_kpr.PutusanFrontMenuKpr;
import com.application.bris.brisi_pemutus.page_riwayat.ActivityMenuRiwayat;
import com.application.bris.brisi_pemutus.util.AppBarStateChangedListener;
import com.application.bris.brisi_pemutus.util.AppUtil;

import com.application.bris.brisi_pemutus.view.corelayout.CoreLayoutActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class KonsumerKPRPilihAoSilangActivity extends AppCompatActivity implements View.OnClickListener, PilihAoSilangListener, ConfirmListener {

    @BindView(R.id.ll_content)
    LinearLayout ll_content;

    @BindView(R.id.tf_kanwil)
    TextFieldBoxes tf_kanwil;
    @BindView(R.id.et_kanwil)
    EditText et_kanwil;

    @BindView(R.id.tf_cabang)
    TextFieldBoxes tf_cabang;
    @BindView(R.id.et_cabang)
    EditText et_cabang;

    @BindView(R.id.tf_rsc)
    TextFieldBoxes tf_rsc;
    @BindView(R.id.et_rsc)
    EditText et_rsc;

    @BindView(R.id.tf_ao)
    TextFieldBoxes tf_ao;
    @BindView(R.id.et_ao)
    EditText et_ao;

    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.btn_kembalikan)
    Button btn_kembalikan;

    //VALUE
    private String val_kanwil = "";
    private String val_cabang = "";
    private String val_rsc = "";
    private String val_ao = "";

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    private static String dataKanwil = "";
    private static String dataCabang = "";
    private static String dataRsc = "";
    private static String dataUser = "";

    private AoModel userModel;
    private List<ListKanwil> listKanwil;
    private List<ListKanwil> listCabang;
    private List<ListKanwil> listRsc;
    private List<ListUser> listUser;


    private String fid_JHOOFF, LIMIT_MIKRO, LIMIT_BAWAH_KKB, SECRET_CODE, NAMA_PEGAWAI, IMEI, USER_NAME, LIMIT_KPR, KODE_CABANG, UID, LIMIT_BAWAH_KPR, NO_PEGAWAI, PASSWORD, FID_ROLE, limit, SK, LIMIT_KKB, status;
    private int val_uid_appraisal,idAplikasi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumer_kpr_agunan_pilih_ao_silang);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        userModel = new AoModel(this);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Pilih Account Officer");
        btn_send.setOnClickListener(this);
        btn_kembalikan.setOnClickListener(this);

        tf_kanwil.setVisibility(View.GONE);
        idAplikasi=getIntent().getIntExtra("idAplikasi",0);

        loadCabang();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorBackgroundTransparent));
        }
    }

    private void loadCabang() {
        ReqListSkk req = new ReqListSkk(appPreferences.getKodeKanwil());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().listCabang(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            dataCabang = response.body().getData().get("listSKK").toString();

                            Type type = new TypeToken<List<ListKanwil>>() {}.getType();
                            Gson gson = new Gson();
                            listCabang = gson.fromJson(dataCabang, type);

                            onclickSelectDialogCabang();


                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKPRPilihAoSilangActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPilihAoSilangActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKPRPilihAoSilangActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKPRPilihAoSilangActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogCabang() {

        et_cabang.setFocusable(false);
        et_cabang.setInputType(InputType.TYPE_NULL);
        et_cabang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPilihAo.display(getSupportFragmentManager(), tf_cabang.getLabelText().toString().trim(), listCabang, KonsumerKPRPilihAoSilangActivity.this, "skk");
            }
        });
        et_cabang.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogPilihAo.display(getSupportFragmentManager(), tf_cabang.getLabelText().toString().trim(), listCabang, KonsumerKPRPilihAoSilangActivity.this, "skk");
            }
        });
        tf_cabang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPilihAo.display(getSupportFragmentManager(), tf_cabang.getLabelText().toString().trim(), listCabang, KonsumerKPRPilihAoSilangActivity.this, "skk");
            }
        });
        tf_cabang.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPilihAo.display(getSupportFragmentManager(), tf_cabang.getLabelText().toString().trim(), listCabang, KonsumerKPRPilihAoSilangActivity.this, "skk");
            }
        });
    }

    @Override
    public void onSelectCabang(String title, ListKanwil data) {
        if (title.equalsIgnoreCase(tf_cabang.getLabelText().toString().trim())) {
            et_cabang.setText(data.getSBDESC());
            et_rsc.setText("Pilih");
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPilihAoSilangActivity.this, tf_cabang, et_cabang);
            loadRsc(data.getKODE_LENGKAP());
        }
    }

    private void loadRsc(String kode_skk) {
        ReqListRsc req = new ReqListRsc(kode_skk);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().listRsc(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            dataRsc = response.body().getData().get("listRSC").toString();

                            Type type = new TypeToken<List<ListKanwil>>() {}.getType();
                            Gson gson = new Gson();
                            listRsc = gson.fromJson(dataRsc, type);

                            onclickSelectDialogRsc();


                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKPRPilihAoSilangActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPilihAoSilangActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKPRPilihAoSilangActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKPRPilihAoSilangActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogRsc() {

        et_rsc.setFocusable(false);
        et_rsc.setInputType(InputType.TYPE_NULL);
        et_rsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPilihAo.display(getSupportFragmentManager(), tf_rsc.getLabelText().toString().trim(), listRsc, KonsumerKPRPilihAoSilangActivity.this, "rsc");
            }
        });
        et_rsc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogPilihAo.display(getSupportFragmentManager(), tf_rsc.getLabelText().toString().trim(), listRsc, KonsumerKPRPilihAoSilangActivity.this, "rsc");
            }
        });
        tf_rsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPilihAo.display(getSupportFragmentManager(), tf_rsc.getLabelText().toString().trim(), listRsc, KonsumerKPRPilihAoSilangActivity.this, "rsc");
            }
        });
        tf_rsc.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPilihAo.display(getSupportFragmentManager(), tf_rsc.getLabelText().toString().trim(), listRsc, KonsumerKPRPilihAoSilangActivity.this, "rsc");
            }
        });
    }

    @Override
    public void onSelectRsc(String title, ListKanwil data) {
        if (title.equalsIgnoreCase(tf_rsc.getLabelText().toString().trim())) {
            et_rsc.setText(data.getSBDESC());
            et_ao.setText("Pilih");
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPilihAoSilangActivity.this, tf_rsc, et_rsc);
            loadUser(data.getKODE_LENGKAP(), "100");
        }
    }

    private void loadUser(String kodeCabang, String fidRole) {
        ReqListUser req = new ReqListUser(kodeCabang, fidRole);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().listUser(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            dataUser = response.body().getData().get("listUser").toString();

                            Type type = new TypeToken<List<ListUser>>() {}.getType();
                            Gson gson = new Gson();
                            listUser = gson.fromJson(dataUser, type);

                            userModel.clear();
                            for (int i = 0; i < listUser.size(); i++){
                                fid_JHOOFF = listUser.get(i).getFid_JHOOFF();
                                LIMIT_MIKRO = listUser.get(i).getLIMIT_MIKRO();
                                LIMIT_BAWAH_KKB = listUser.get(i).getLIMIT_BAWAH_KKB();
                                SECRET_CODE = listUser.get(i).getSECRET_CODE();
                                NAMA_PEGAWAI = listUser.get(i).getNAMA_PEGAWAI();
                                IMEI = listUser.get(i).getIMEI();
                                USER_NAME = listUser.get(i).getUSER_NAME();
                                LIMIT_KPR = listUser.get(i).getLIMIT_KPR();
                                KODE_CABANG = listUser.get(i).getKODE_CABANG();
                                UID = listUser.get(i).getUID();
                                LIMIT_BAWAH_KPR = listUser.get(i).getLIMIT_BAWAH_KPR();
                                NO_PEGAWAI = listUser.get(i).getNO_PEGAWAI();
                                PASSWORD = listUser.get(i).getPASSWORD();
                                FID_ROLE = listUser.get(i).getFID_ROLE();
                                limit = listUser.get(i).getLimit();
                                SK = listUser.get(i).getSK();
                                LIMIT_KKB = listUser.get(i).getLIMIT_KKB();
                                status = listUser.get(i).getStatus();
                                userModel.add(fid_JHOOFF, LIMIT_MIKRO, LIMIT_BAWAH_KKB, SECRET_CODE, NAMA_PEGAWAI, IMEI, USER_NAME, LIMIT_KPR, KODE_CABANG, UID, LIMIT_BAWAH_KPR, NO_PEGAWAI, PASSWORD, FID_ROLE, limit, SK, LIMIT_KKB, status);
                            }

                            onclickSelectDialogUser();

                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKPRPilihAoSilangActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPilihAoSilangActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKPRPilihAoSilangActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKPRPilihAoSilangActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogUser() {

        et_ao.setFocusable(false);
        et_ao.setInputType(InputType.TYPE_NULL);
        et_ao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPilihAo.display(getSupportFragmentManager(), tf_ao.getLabelText().toString().trim(), listUser, KonsumerKPRPilihAoSilangActivity.this, "ao");
            }
        });
        et_ao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogPilihAo.display(getSupportFragmentManager(), tf_ao.getLabelText().toString().trim(), listUser, KonsumerKPRPilihAoSilangActivity.this, "ao");
            }
        });
        tf_ao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPilihAo.display(getSupportFragmentManager(), tf_ao.getLabelText().toString().trim(), listUser, KonsumerKPRPilihAoSilangActivity.this, "ao");
            }
        });
        tf_ao.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPilihAo.display(getSupportFragmentManager(), tf_ao.getLabelText().toString().trim(), listUser, KonsumerKPRPilihAoSilangActivity.this, "ao");
            }
        });
    }

    @Override
    public void onSelectUser(String title, ListUser data) {
        if (title.equalsIgnoreCase(tf_ao.getLabelText().toString().trim())) {
            et_ao.setText(data.getNAMA_PEGAWAI());
            val_uid_appraisal = Integer.parseInt(data.getUID());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPilihAoSilangActivity.this, tf_ao, et_ao);
        }
    }

    private boolean validateForm() {
//        if (et_kanwil.getText().toString().trim().equalsIgnoreCase("Pilih")) {
//            tf_kanwil.setError(tf_kanwil.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }

        Log.d("lolos", "kanwil");

        if (et_cabang.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            tf_cabang.setError(tf_cabang.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }
        Log.d("lolos", "cabang");

        if (et_rsc.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            tf_rsc.setError(tf_rsc.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }
        Log.d("lolos", "rsc");

        if (et_ao.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            tf_ao.setError(tf_ao.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }
        Log.d("lolos", "ao");

        return true;
    }

    private void sendData(SweetAlertDialog dialog) {
//        loading.setVisibility(View.VISIBLE);

        ReqKirimAppraisal req = new ReqKirimAppraisal(idAplikasi, val_uid_appraisal);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().kirimApraisal(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
//                            CustomDialog.DialogSuccess(KonsumerKPRPilihAoSilangActivity.this, "Success!", "Input data Pipeline sukses", KonsumerKPRPilihAoSilangActivity.this);
                           dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            dialog.setTitle("Sukses");
                            dialog.setContentText("Berhasil memilih AO Silang\n");
                            dialog.setConfirmText("Selesai");
                            dialog.showCancelButton(false);
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    Intent intent=new Intent(KonsumerKPRPilihAoSilangActivity.this, CoreLayoutActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                                    startActivity(intent);
                                }
                            });
                            dialog.show();

//                            Toasty.success(KonsumerKPRPilihAoSilangActivity.this,"Berhasil memilih AO silang");
                        } else {
                            AppUtil.notiferror(KonsumerKPRPilihAoSilangActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPilihAoSilangActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KonsumerKPRPilihAoSilangActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    private void kembalikanAoSilang(SweetAlertDialog dialog) {
//        loading.setVisibility(View.VISIBLE);

        ReqAppraisalKembalikanKeAo req = new ReqAppraisalKembalikanKeAo();
        req.setIdApl(idAplikasi);
        req.setUid(Integer.parseInt(appPreferences.getUid()));
        req.setKeterangan("tes pesan masuk");
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().appraisalKembalikanKeAo(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
//                            CustomDialog.DialogSuccess(KonsumerKPRPilihAoSilangActivity.this, "Success!", "Input data Pipeline sukses", KonsumerKPRPilihAoSilangActivity.this);
                           dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            dialog.setTitle("Sukses");
                            dialog.setContentText("Berhasil mengembalikan permintaan AO silang\n");
                            dialog.setConfirmText("Selesai");
                            dialog.showCancelButton(false);
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    Intent intent=new Intent(KonsumerKPRPilihAoSilangActivity.this, CoreLayoutActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                                    startActivity(intent);
                                }
                            });
                            dialog.show();

//                            Toasty.success(KonsumerKPRPilihAoSilangActivity.this,"Berhasil memilih AO silang");
                        } else {
                            AppUtil.notiferror(KonsumerKPRPilihAoSilangActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPilihAoSilangActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KonsumerKPRPilihAoSilangActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    @Override
    public void success(boolean val) {
        if (val) {
            finish();
//            startActivity(new Intent(this, PipelineActivity.class));
            startActivity(new Intent(this, CoreLayoutActivity.class));
        }

    }

    @Override
    public void confirm(boolean val) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_back:
//                CustomDialog.DialogBackpress(this);
//                break;
            case R.id.btn_send:
                if (validateForm()){
                    SweetAlertDialog dialog=new SweetAlertDialog(KonsumerKPRPilihAoSilangActivity.this,SweetAlertDialog.WARNING_TYPE);
                    dialog.setContentText("Apakah anda yakin memilih "+et_ao.getText()+" sebagai AO silang?\n");
                    dialog.setConfirmText("Ya");
                    dialog.setCancelText("Batal");
                    dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                            sendData(dialog);
                        }
                    });
                    dialog.show();
                }
                break;
            case R.id.btn_kembalikan:
                SweetAlertDialog dialog=new SweetAlertDialog(KonsumerKPRPilihAoSilangActivity.this,SweetAlertDialog.WARNING_TYPE);
                dialog.setTitle("Konfirmasi");
                dialog.setContentText("Apakah anda yakin ingin membatalkan permintaan AO silang?");
                dialog.setConfirmText("Ya");
                dialog.setCancelText("Batal");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                        kembalikanAoSilang(dialog);
                    }
                });
                dialog.show();

                break;
        }
    }

//    @Override
//    public void onBackPressed() {
//        CustomDialog.DialogBackpress(this);
//    }
}

