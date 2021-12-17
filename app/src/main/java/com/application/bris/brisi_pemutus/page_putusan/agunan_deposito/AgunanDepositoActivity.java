package com.application.bris.brisi_pemutus.page_putusan.agunan_deposito;
import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.application.bris.brisi_pemutus.BuildConfig;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.agunan_global_idapl_agunan_cif.AgunanGlobal;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.agunan_deposito.AgunanDeposito;
import com.application.bris.brisi_pemutus.page_putusan.agunan_kios.AgunanKiosActivity;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityFotoKelengkapanDokumen;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.NumberTextWatcherCanNolForThousand;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class AgunanDepositoActivity extends AppCompatActivity implements  TextWatcher{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.tf_tanggalpemeriksaan)
    TextFieldBoxes tf_tanggalpemeriksaan;
    @BindView(R.id.et_tanggalpemeriksaan)
    EditText et_tanggalpemeriksaan;
    @BindView(R.id.tf_jenisdeposito)
    TextFieldBoxes tf_jenisdeposito;
    @BindView(R.id.et_jenisdeposito)
    EditText et_jenisdeposito;
    @BindView(R.id.tf_namapemilik)
    TextFieldBoxes tf_namapemilik;
    @BindView(R.id.et_namapemilik)
    EditText et_namapemilik;
    @BindView(R.id.tf_alamatpemilik)
    TextFieldBoxes tf_alamatpemilik;
    @BindView(R.id.et_alamatpemilik)
    EditText et_alamatpemilik;

    @BindView(R.id.tf_hubungandengannasabah)
    TextFieldBoxes tf_hubungandengannasabah;
    @BindView(R.id.et_hubungandengannasabah)
    EditText et_hubungandengannasabah;
    @BindView(R.id.tf_nomorbilyet)
    TextFieldBoxes tf_nomorbilyet;
    @BindView(R.id.et_nomorbilyet)
    EditText et_nomorbilyet;
    @BindView(R.id.tf_bankpenerbit)
    TextFieldBoxes tf_bankpenerbit;
    @BindView(R.id.et_bankpenerbit)
    EditText et_bankpenerbit;
    @BindView(R.id.tf_tanggalpenerbitan)
    TextFieldBoxes tf_tanggalpenerbitan;
    @BindView(R.id.et_tanggalpenerbitan)
    EditText et_tanggalpenerbitan;
    @BindView(R.id.tf_tanggaljatuhtempo)
    TextFieldBoxes tf_tanggaljatuhtempo;
    @BindView(R.id.et_tanggaljatuhtempo)
    EditText et_tanggaljatuhtempo;
    @BindView(R.id.tf_aro)
    TextFieldBoxes tf_aro;
    @BindView(R.id.et_aro)
    EditText et_aro;
    @BindView(R.id.tf_nilainominal)
    TextFieldBoxes tf_nilainominal;
    @BindView(R.id.et_nilainominal)
    EditText et_nilainominal;
    @BindView(R.id.tf_nilailikuidasi)
    TextFieldBoxes tf_nilailikuidasi;
    @BindView(R.id.et_nilailikuidasi)
    EditText et_nilailikuidasi;
    @BindView(R.id.et_keterangan)
    EditText et_keterangan;
    @BindView(R.id.iv_foto)
    ImageView iv_foto;
    @BindView(R.id.btn_foto)
    ImageView btn_foto;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    private Calendar calTanggalPemeriksaan;
    private Calendar calTanggalPenerbitan;
    private Calendar calTanggalJatuhTempo;
    private DatePickerDialog dpTanggalPemeriksaan;
    private DatePickerDialog dpTanggalPenerbitan;
    private DatePickerDialog dpTanggalJatuhTempo;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


    private String idAgunan;
    private String idApl;
    private String idCif;
    private String typeProduk;
    private String loanType;

    private AgunanDeposito dataAgunan;
    private List<AgunanDeposito> listDataAgunan;

    private String idAgunanResponse;

    private String val_tanggalPemeriksaan;
    private String val_jenisDeposito;
    private String val_namaPemilik;
    private String val_alamatPemilik;
    private String val_hubunganDenganNasabah;
    private String val_nomorBilyet;
    private String val_bankPenerbit;
    private String val_tanggalPenerbitan;
    private String val_tanggalJatuhTempo;
    private String val_aro;
    private String val_nilaiNominal;
    private String val_nilaiTaksasi;
    private String val_keterangan;
    private int val_idPhoto = 0;

    private final int TAKE_PICTURE= 1;
    private final int PICT_PICTURE = 0;


    private String isSelectPhoto = "";
    private Uri uri_foto;

    private Bitmap bitmap_foto;
    private Bitmap loadedPicture;


    private String dataString;


    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_agunandeposito_input);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        AppUtil.toolbarRegular(this, "Agunan Deposito");
        idAgunan = getIntent().getStringExtra("idAgunan");
        idApl = getIntent().getStringExtra("idAplikasi");
        idCif = getIntent().getStringExtra("cif");
        //not used

        typeProduk = getIntent().getStringExtra("tp_produk");
        loanType = getIntent().getStringExtra("loan_type");
        //


        backgroundStatusBar();

            loadData();
            editTextSetting();


    }

    private void loadData() {
        loading.setVisibility(View.VISIBLE);
        sm_placeholder.startShimmer();
        AgunanGlobal req = new AgunanGlobal();


        req.setIdAgunan(Integer.parseInt(idAgunan));
        req.setIdApl(Integer.parseInt(idApl));
        req.setIdCif(Integer.parseInt(idCif));

//        req.setIdAgunan(Integer.parseInt("257"));
//        req.setIdApl(Integer.parseInt("101694"));
//        req.setIdCif(Integer.parseInt("81857"));




        ApiClientAdapter aps = new ApiClientAdapter(this);


        Call<ParseResponse> call = aps.getApiInterface().inquiryAgunanDeposito(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            String listDataString = response.body().getData().toString();
                            dataAgunan= gson.fromJson(listDataString, AgunanDeposito.class);

                            et_tanggalpemeriksaan.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTglPemeriksaan(), "ddMMyyyy", "dd-MM-yyyy"));
                            et_jenisdeposito.setText(dataAgunan.getJenisDeposito());
                            et_namapemilik.setText(dataAgunan.getNamaPemilik());
                            et_alamatpemilik.setText(dataAgunan.getAlamatPemilik());
                            et_hubungandengannasabah.setText(dataAgunan.getHubungan());
                            et_nomorbilyet.setText(dataAgunan.getNoBilyet());
                            et_bankpenerbit.setText(dataAgunan.getBankPenerbit());
                            et_tanggalpenerbitan.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTanggalPenerbitan(), "ddMMyyyy", "dd-MM-yyyy"));
                            et_tanggaljatuhtempo.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTanggalJatuhTempo(), "ddMMyyyy", "dd-MM-yyyy"));
                            et_aro.setText(dataAgunan.getIsAro());
                            et_nilainominal.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilainominal));
                            et_nilainominal.setText(dataAgunan.getNilaiNominal());

                            et_nilailikuidasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilailikuidasi));
                            et_nilailikuidasi.setText(dataAgunan.getNilaiTaksasi());
                            et_keterangan.setText(dataAgunan.getKeterangan());
                            bitmap_foto = setLoadImage(iv_foto, dataAgunan.getIdPhoto());
                            val_idPhoto = dataAgunan.getIdPhoto();
                            val_aro = dataAgunan.getIsAro();
                        }
                        else{
                            AppUtil.notiferror(AgunanDepositoActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 2000);
                        }
                    }
                    else {
//                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanDepositoActivity.this, findViewById(android.R.id.content), "terjadi kesalahan");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 2000);
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(AgunanDepositoActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }






    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }







    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static int CAMERA_CODE_FORE_PERMISSION = 0;
    public void checkCameraPermission(int cameraCode)
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            CAMERA_CODE_FORE_PERMISSION = cameraCode;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        } else {
            directOpenCamera(cameraCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_CAMERA_REQUEST_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                AppUtil.showToastLong(this, "Camera Permission Granted");
                directOpenCamera(CAMERA_CODE_FORE_PERMISSION);
            }
            else {
                AppUtil.showToastLong(this, "Camera Permission Denied");
            }
        }
    }

    private void directOpenCamera(int cameraCode){
        Uri outputFileUri = getCaptureImageOutputUri();
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(captureIntent, cameraCode);
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                outputFileUri = FileProvider.getUriForFile(AgunanDepositoActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotoagunandeposito.png"));
            }
            else{
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "fotoagunandeposito.png"));
            }
        }
        return outputFileUri;
    }


    public Bitmap setLoadImage(final ImageView iv, final int idFoto){
//        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFoto + idFoto;
//        Glide
//                .with(AgunanDepositoActivity.this)
//                .asBitmap()
//                .load(url_photo)
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        iv.setImageBitmap(resource);
//
//                        loadedPicture = resource;
//                    }
//                });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AgunanDepositoActivity.this, ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",idFoto);
                startActivity(intent);
            }
        });
//        return loadedPicture;
        return AppUtil.setImageGlideReturnBitmap(AgunanDepositoActivity.this,idFoto,iv);
    }
private void editTextSetting(){
    et_tanggalpemeriksaan.setInputType(InputType.TYPE_NULL);
    et_tanggalpemeriksaan.setFocusable(false);
    et_jenisdeposito.setInputType(InputType.TYPE_NULL);
    et_jenisdeposito.setFocusable(false);
    et_namapemilik.setInputType(InputType.TYPE_NULL);
    et_namapemilik.setFocusable(false);
    et_alamatpemilik.setInputType(InputType.TYPE_NULL);
    et_alamatpemilik.setFocusable(false);
    et_hubungandengannasabah.setInputType(InputType.TYPE_NULL);
    et_hubungandengannasabah.setFocusable(false);
    et_nomorbilyet.setInputType(InputType.TYPE_NULL);
    et_nomorbilyet.setFocusable(false);
    et_bankpenerbit.setInputType(InputType.TYPE_NULL);
    et_bankpenerbit.setFocusable(false);
    et_tanggalpenerbitan.setInputType(InputType.TYPE_NULL);
    et_tanggalpenerbitan.setFocusable(false);
    et_tanggaljatuhtempo.setInputType(InputType.TYPE_NULL);
    et_tanggaljatuhtempo.setFocusable(false);
    et_aro.setInputType(InputType.TYPE_NULL);
    et_aro.setFocusable(false);
    et_tanggalpemeriksaan.setInputType(InputType.TYPE_NULL);
    et_tanggalpemeriksaan.setFocusable(false);
    et_nilainominal.setInputType(InputType.TYPE_NULL);
    et_nilainominal.setFocusable(false);
    et_nilailikuidasi.setInputType(InputType.TYPE_NULL);
    et_nilailikuidasi.setFocusable(false);
    et_keterangan.setInputType(InputType.TYPE_NULL);
    et_keterangan.setFocusable(false);

}
















    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (et_nilainominal.getText().toString().trim().length() > 0 || !et_nilainominal.getText().toString().isEmpty()){
            et_nilailikuidasi.setText(et_nilainominal.getText().toString().trim());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}