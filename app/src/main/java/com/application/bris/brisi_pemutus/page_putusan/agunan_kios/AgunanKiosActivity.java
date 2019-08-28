package com.application.bris.brisi_pemutus.page_putusan.agunan_kios;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.airbnb.lottie.parser.IntegerParser;
import com.application.bris.brisi_pemutus.BuildConfig;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.ParseResponseError;
import com.application.bris.brisi_pemutus.api.model.request.agunan_global_idapl_agunan_cif.AgunanGlobal;
import com.application.bris.brisi_pemutus.api.model.request.agunan_tanah_kosong.ReqAgunanTanahKosong;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.agunan_kios.AgunanKios;
import com.application.bris.brisi_pemutus.page_putusan.agunan.DialogKeyValue;
import com.application.bris.brisi_pemutus.page_putusan.agunan_deposito.AgunanDepositoActivity;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityFotoKelengkapanDokumen;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.ImageHandler;
import com.application.bris.brisi_pemutus.util.NumberTextWatcherCanNolForThousand;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class AgunanKiosActivity extends AppCompatActivity implements TextWatcher{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.tf_tanggalpemeriksaan)
    TextFieldBoxes tf_tanggalpemeriksaan;
    @BindView(R.id.et_tanggalpemeriksaan)
    EditText et_tanggalpemeriksaan;
    @BindView(R.id.tf_jenisagunan)
    TextFieldBoxes tf_jenisagunan;
    @BindView(R.id.et_jenisagunan)
    EditText et_jenisagunan;
    @BindView(R.id.tf_dokumenbuktihakkios)
    TextFieldBoxes tf_dokumenbuktihakkios;
    @BindView(R.id.et_dokumenbuktihakkios)
    EditText et_dokumenbuktihakkios;
    @BindView(R.id.tf_nomordokumen)
    TextFieldBoxes tf_nomordokumen;
    @BindView(R.id.et_nomordokumen)
    EditText et_nomordokumen;
    @BindView(R.id.tf_namapemeganghak)
    TextFieldBoxes tf_namapemeganghak;
    @BindView(R.id.et_namapemeganghak)
    EditText et_namapemeganghak;
    @BindView(R.id.tf_hubunganpemeganghakdengannasabah)
    TextFieldBoxes tf_hubunganpemeganghakdengannasabah;
    @BindView(R.id.et_hubunganpemeganghakdengannasabah)
    EditText et_hubunganpemeganghakdengannasabah;
    @BindView(R.id.tf_masaberlakuijinkios)
    TextFieldBoxes tf_masaberlakuijinkios;
    @BindView(R.id.et_masaberlakuijinkios)
    EditText et_masaberlakuijinkios;
    @BindView(R.id.tf_luaskios)
    TextFieldBoxes tf_luaskios;
    @BindView(R.id.et_luaskios)
    EditText et_luaskios;
    @BindView(R.id.tf_namapasar)
    TextFieldBoxes tf_namapasar;
    @BindView(R.id.et_namapasar)
    EditText et_namapasar;
    @BindView(R.id.tf_blok)
    TextFieldBoxes tf_blok;
    @BindView(R.id.et_blok)
    EditText et_blok;
    @BindView(R.id.tf_nomorkios)
    TextFieldBoxes tf_nomorkios;
    @BindView(R.id.et_nomorkios)
    EditText et_nomorkios;
    @BindView(R.id.tf_wilayahkota)
    TextFieldBoxes tf_wilayahkota;
    @BindView(R.id.et_wilayahkota)
    EditText et_wilayahkota;
    @BindView(R.id.tf_lokasijaminan)
    TextFieldBoxes tf_lokasijaminan;
    @BindView(R.id.et_lokasijaminan)
    EditText et_lokasijaminan;
    @BindView(R.id.tf_tahundibangunrenovasi)
    TextFieldBoxes tf_tahundibangunrenovasi;
    @BindView(R.id.et_tahundibangunrenovasi)
    EditText et_tahundibangunrenovasi;
    @BindView(R.id.tf_retribusi)
    TextFieldBoxes tf_retribusi;
    @BindView(R.id.et_retribusi)
    EditText et_retribusi;
    @BindView(R.id.tf_listrik)
    TextFieldBoxes tf_listrik;
    @BindView(R.id.et_listrik)
    EditText et_listrik;
    @BindView(R.id.tf_dayalistrik)
    TextFieldBoxes tf_dayalistrik;
    @BindView(R.id.et_dayalistrik)
    EditText et_dayalistrik;
    @BindView(R.id.tf_telepon)
    TextFieldBoxes tf_telepon;
    @BindView(R.id.et_telepon)
    EditText et_telepon;
    @BindView(R.id.tf_nomortelepon)
    TextFieldBoxes tf_nomortelepon;
    @BindView(R.id.et_nomortelepon)
    EditText et_nomortelepon;
    @BindView(R.id.tf_namapemberiinformasi)
    TextFieldBoxes tf_namapemberiinformasi;
    @BindView(R.id.et_namapemberiinformasi)
    EditText et_namapemberiinformasi;
    @BindView(R.id.tf_alamatpemberiinformasi)
    TextFieldBoxes tf_alamatpemberiinformasi;
    @BindView(R.id.et_alamatpemberiinformasi)
    EditText et_alamatpemberiinformasi;
    @BindView(R.id.tf_nohppemberiinformasi)
    TextFieldBoxes tf_nohppemberiinformasi;
    @BindView(R.id.et_nohppemberiinformasi)
    EditText et_nohppemberiinformasi;
    @BindView(R.id.tf_nilaimarket)
    TextFieldBoxes tf_nilaimarket;
    @BindView(R.id.et_nilaimarket)
    EditText et_nilaimarket;
    @BindView(R.id.tf_nilailikuidasi)
    TextFieldBoxes tf_nilailikuidasi;
    @BindView(R.id.et_nilailikuidasi)
    EditText et_nilailikuidasi;
    @BindView(R.id.iv_fotobpn)
    ImageView iv_fotobpn;
    @BindView(R.id.btn_fotobpn)
    ImageView btn_fotobpn;
    @BindView(R.id.iv_fotokios)
    ImageView iv_fotokios;
    @BindView(R.id.btn_fotokios)
    ImageView btn_fotokios;
    @BindView(R.id.iv_batasutara)
    ImageView iv_batasutara;
    @BindView(R.id.btn_batasutara)
    ImageView btn_batasutara;
    @BindView(R.id.iv_batasselatan)
    ImageView iv_batasselatan;
    @BindView(R.id.btn_batasselatan)
    ImageView btn_batasselatan;
    @BindView(R.id.iv_batastimur)
    ImageView iv_batastimur;
    @BindView(R.id.btn_batastimur)
    ImageView btn_batastimur;
    @BindView(R.id.iv_batasbarat)
    ImageView iv_batasbarat;
    @BindView(R.id.btn_batasbarat)
    ImageView btn_batasbarat;
    @BindView(R.id.et_pendapatkondisijaminan)
    EditText et_pendapatkondisijaminan;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;


    private Calendar calTanggalPemeriksaan;
    private Calendar calTanggalMasaBerlaku;
    private DatePickerDialog dpTanggalPemeriksaan;
    private DatePickerDialog dpTanggalMasaBerlaku;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


    private String idAgunan;
    private String idApl;
    private String idCif;
    private String typeProduk;
    private String loanType;

    private AgunanKios dataAgunan;
    private List<AgunanKios> listDataAgunan;

    private String idAgunanResponse;

    private String val_tanggalPemeriksaan;
    private String val_jenisAgunan;
    private String val_dokumenBukti;
    private String val_noDokumen;
    private String val_namaPemegangHak;
    private String val_hubunganPemegangHak;
    private String val_masaBerlakuIjin;
    private int val_luasKios;
    private String val_namaPasar;
    private String val_losKios;
    private String val_noKios;
    private String val_wilayahKota;
    private String val_lokasiJaminan;
    private String val_tahunRenovasi;
    private String val_retribusi;
    private String val_listrik;
    private String val_besaranDaya;
    private String val_telepon;
    private String val_noTelp;
    private String val_nilaiMarket;
    private String val_nilaiTaksasi;
    private String val_ftv = "";
    private String val_namaPemberiInfo1;
    private String val_alamatPemberiInfo1;
    private String val_noTelpPemberiInfo1;
    private String val_pendapatPemeriksa;
    private String val_klasifikasiAgunan = "";
    private String val_jenisPengikatan = "";
    private String val_descPengikatan = "";
    private String val_fotoAgunanKios = "";
    private String val_nilaiPengikatan = "";
    private String val_collIdSyiar = "";
    private int val_idPhotoKbpn = 0;
    private int val_idPhotoKutama = 0;
    private int val_idPhotoKbarat = 0;
    private int val_idPhotoKutara = 0;
    private int val_idPhotoKselatan = 0;
    private int val_idPhotoKtimur = 0;


    private String isSelectPhoto = "";

    private final int TAKE_PICTURE_FOTOKIOS = 1;
    private final int TAKE_PICTURE_BATASUTARA = 2;
    private final int TAKE_PICTURE_BATASSELATAN = 3;
    private final int TAKE_PICTURE_BATASTIMUR = 4;
    private final int TAKE_PICTURE_BATASBARAT = 5;
    private final int TAKE_PICTURE_FOTOBPN = 6;

    private final int PICK_PICTURE_FOTOKIOS = 11;
    private final int PICK_PICTURE_BATASUTARA = 22;
    private final int PICK_PICTURE_BATASSELATAN = 33;
    private final int PICK_PICTURE_BATASTIMUR = 44;
    private final int PICK_PICTURE_BATASBARAT = 55;
    private final int PICK_PICTURE_FOTOBPN = 66;


    private Uri uri_fotobpn, uri_fotokios, uri_batasutara, uri_batasselatan, uri_batastimur,
            uri_batasbarat;

    private Bitmap bitmap_fotobpn, bitmap_fotokios, bitmap_batasutara, bitmap_batasselatan, bitmap_batastimur, bitmap_batasbarat, loadedPicture;


    private String dataString;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_agunankios_input);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        AppUtil.toolbarRegular(this, "Agunan Kios");
        idAgunan = getIntent().getStringExtra("idAgunan");
        idApl = getIntent().getStringExtra("idAplikasi");
        idCif = getIntent().getStringExtra("cif");
        typeProduk = getIntent().getStringExtra("tp_produk");
        loanType = getIntent().getStringExtra("loan_type");
        backgroundStatusBar();
        loadData();
        editTextSetting();

    }

    private void loadData() {

        loading.setVisibility(View.VISIBLE);
        AgunanGlobal req = new AgunanGlobal();

        //real data
        req.setIdAgunan(Integer.parseInt(idAgunan));
        req.setIdApl(Integer.parseInt(idApl));
        req.setIdCif(Integer.parseInt(idCif));

        //pantekan
//        req.setIdAgunan(Integer.parseInt("1165"));
//        req.setIdApl(Integer.parseInt("101678"));


        ApiClientAdapter aps = new ApiClientAdapter(this);


        Call<ParseResponseArr> call = aps.getApiInterface().inquiryAgunanKios(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
               loading.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<AgunanKios>>() {}.getType();

                            String listDataString = response.body().getData().toString();
                            listDataAgunan = gson.fromJson(listDataString, type);
                            dataAgunan=listDataAgunan.get(0);

                            et_tanggalpemeriksaan.setText(AppUtil.parseTanggalGeneral(dataAgunan.gettANGGALPEMERIKSAAN(), "ddMMyyyy", "dd-MM-yyyy"));
                            et_jenisagunan.setText(dataAgunan.getjENISJAMINAN());
                            et_dokumenbuktihakkios.setText(dataAgunan.getdOKUMENBUKTI());
                            et_nomordokumen.setText(dataAgunan.getnODOKUMEN());
                            et_namapemeganghak.setText(dataAgunan.getnAMAPEMEGANGHAK());
                            et_hubunganpemeganghakdengannasabah.setText(dataAgunan.gethUBUNGANPEMEGANGHAK());
                            et_masaberlakuijinkios.setText(AppUtil.parseTanggalGeneral(dataAgunan.getmASABERLAKUIJIN(), "ddMMyyyy", "dd-MM-yyyy"));
                            et_luaskios.setText(String.valueOf(dataAgunan.getlUASKIOS()));
                            et_namapasar.setText(dataAgunan.getnAMAPASAR());
                            et_blok.setText(dataAgunan.getlOSKIOS());
                            et_nomorkios.setText(dataAgunan.getnOKIOS());
                            et_wilayahkota.setText(dataAgunan.getwILAYAHKOTA());
                            et_lokasijaminan.setText(dataAgunan.getlOKASIJAMINAN());
                            et_tahundibangunrenovasi.setText(dataAgunan.gettAHUNRENOVASI());

                            et_retribusi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_retribusi));
                            et_retribusi.setText(dataAgunan.getrETRIBUSI());
                            et_listrik.setText(dataAgunan.getlISTRIK());
                            et_dayalistrik.setText(String.valueOf(dataAgunan.getbESARANDAYA()));
                            et_telepon.setText(dataAgunan.gettELEPON());
                            et_nomortelepon.setText(dataAgunan.getnOTLP());
                            et_namapemberiinformasi.setText(dataAgunan.getnAMAPEMBERIINFO1());
                            et_alamatpemberiinformasi.setText(dataAgunan.getaLAMATPEMBERIINFO1());
                            et_nohppemberiinformasi.setText(dataAgunan.getnOTELPEMBERIINFO1());

                            et_nilaimarket.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilaimarket));
                            et_nilaimarket.setText(dataAgunan.getnILAIMARKET());

                            et_nilailikuidasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilailikuidasi));
                            et_nilailikuidasi.setText(dataAgunan.getnILAITAKSASI());

                            et_pendapatkondisijaminan.setText(dataAgunan.getpENDAPATPEMERIKSA());

                            bitmap_fotobpn = setLoadImage(iv_fotobpn, dataAgunan.getIdPhotoKbpn());
                            bitmap_fotokios = setLoadImage(iv_fotokios, dataAgunan.getIdPhotoKutama());
                            bitmap_batasutara = setLoadImage(iv_batasutara, dataAgunan.getIdPhotoKutara());
                            bitmap_batasselatan = setLoadImage(iv_batasselatan, dataAgunan.getIdPhotoKselatan());
                            bitmap_batastimur = setLoadImage(iv_batastimur, dataAgunan.idPhotoKtimur);
                            bitmap_batasbarat = setLoadImage(iv_batasbarat, dataAgunan.getIdPhotoKbarat());

                            val_idPhotoKbpn = dataAgunan.getIdPhotoKbpn();
                            val_idPhotoKutama = dataAgunan.getIdPhotoKutama();
                            val_idPhotoKutara = dataAgunan.getIdPhotoKutara();
                            val_idPhotoKselatan = dataAgunan.getIdPhotoKselatan();
                            val_idPhotoKtimur = dataAgunan.getIdPhotoKtimur();
                            val_idPhotoKbarat = dataAgunan.getIdPhotoKbarat();
                        }
                        else{
                            AppUtil.notiferror(AgunanKiosActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 2000);
                        }
                    }
                    else {

                        AppUtil.notiferror(AgunanKiosActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
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
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                AppUtil.notiferror(AgunanKiosActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    private void editTextSetting(){
        et_tanggalpemeriksaan.setInputType(InputType.TYPE_NULL);
        et_tanggalpemeriksaan.setFocusable(false);
        et_jenisagunan.setInputType(InputType.TYPE_NULL);
        et_jenisagunan.setFocusable(false);
        et_dokumenbuktihakkios.setInputType(InputType.TYPE_NULL);
        et_dokumenbuktihakkios.setFocusable(false);
        et_nomordokumen.setInputType(InputType.TYPE_NULL);
        et_nomordokumen.setFocusable(false);
        et_namapemeganghak.setInputType(InputType.TYPE_NULL);
        et_namapemeganghak.setFocusable(false);
        et_hubunganpemeganghakdengannasabah.setInputType(InputType.TYPE_NULL);
        et_hubunganpemeganghakdengannasabah.setFocusable(false);
        et_masaberlakuijinkios.setInputType(InputType.TYPE_NULL);
        et_masaberlakuijinkios.setFocusable(false);
        et_luaskios.setInputType(InputType.TYPE_NULL);
        et_luaskios.setFocusable(false);
        et_namapasar.setInputType(InputType.TYPE_NULL);
        et_namapasar.setFocusable(false);
        et_blok.setInputType(InputType.TYPE_NULL);
        et_blok.setFocusable(false);
        et_luaskios.setInputType(InputType.TYPE_NULL);
        et_luaskios.setFocusable(false);
        et_nomorkios.setInputType(InputType.TYPE_NULL);
        et_nomorkios.setFocusable(false);
        et_luaskios.setInputType(InputType.TYPE_NULL);
        et_luaskios.setFocusable(false);
        et_wilayahkota.setInputType(InputType.TYPE_NULL);
        et_wilayahkota.setFocusable(false);
        et_lokasijaminan.setInputType(InputType.TYPE_NULL);
        et_lokasijaminan.setFocusable(false);
        et_tahundibangunrenovasi.setInputType(InputType.TYPE_NULL);
        et_tahundibangunrenovasi.setFocusable(false);
        et_retribusi.setInputType(InputType.TYPE_NULL);
        et_retribusi.setFocusable(false);
        et_listrik.setInputType(InputType.TYPE_NULL);
        et_listrik.setFocusable(false);
        et_dayalistrik.setInputType(InputType.TYPE_NULL);
        et_dayalistrik.setFocusable(false);
        et_telepon.setInputType(InputType.TYPE_NULL);
        et_telepon.setFocusable(false);
        et_nomortelepon.setInputType(InputType.TYPE_NULL);
        et_nomortelepon.setFocusable(false);
        et_namapemberiinformasi.setInputType(InputType.TYPE_NULL);
        et_namapemberiinformasi.setFocusable(false);
        et_alamatpemberiinformasi.setInputType(InputType.TYPE_NULL);
        et_alamatpemberiinformasi.setFocusable(false);
        et_nohppemberiinformasi.setInputType(InputType.TYPE_NULL);
        et_nohppemberiinformasi.setFocusable(false);
        et_nilaimarket.setInputType(InputType.TYPE_NULL);
        et_nilaimarket.setFocusable(false);
        et_nilailikuidasi.setInputType(InputType.TYPE_NULL);
        et_nilailikuidasi.setFocusable(false);
        et_pendapatkondisijaminan.setInputType(InputType.TYPE_NULL);
        et_pendapatkondisijaminan.setFocusable(false);

    }





    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }


    public Bitmap setLoadImage(final ImageView iv, final int idFoto){
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFoto + idFoto;
        Glide
                .with(AgunanKiosActivity.this)
                .asBitmap()
                .load(url_photo)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv.setImageBitmap(resource);
                        iv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(AgunanKiosActivity.this, ActivityFotoKelengkapanDokumen.class);
                                intent.putExtra("id_foto",idFoto);
                                startActivity(intent);
                            }
                        });
                        loadedPicture = resource;
                    }
                });
        return loadedPicture;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        BigDecimal bd_nilailikuidasi = new BigDecimal(0);
        if (et_nilaimarket.getText().toString().trim().length() > 0 || !et_nilaimarket.getText().toString().isEmpty()){
            bd_nilailikuidasi = new BigDecimal(70).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_nilaimarket.getText().toString().trim()))).setScale(0, BigDecimal.ROUND_HALF_UP);
        }
        et_nilailikuidasi.setText(bd_nilailikuidasi.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}