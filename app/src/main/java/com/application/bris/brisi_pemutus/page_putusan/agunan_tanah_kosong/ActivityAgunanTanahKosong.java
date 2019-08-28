package com.application.bris.brisi_pemutus.page_putusan.agunan_tanah_kosong;



import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.BuildConfig;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.agunan_tanah_kosong.ReqAgunanTanahKosong;
import com.application.bris.brisi_pemutus.api.model.request.agunan_tanah_kosong.ReqSaveAgunanTanahKosong;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.listeners.KeyValueListener;
import com.application.bris.brisi_pemutus.model.agunan_tanah_kosong.AgunanTanahKosong;
import com.application.bris.brisi_pemutus.model.keyvalue.keyvalue;
import com.application.bris.brisi_pemutus.page_putusan.agunan.DialogKeyValue;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityFotoKelengkapanDokumen;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.ImageHandler;
import com.application.bris.brisi_pemutus.util.NumberTextWatcherForThousand;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import static com.application.bris.brisi_pemutus.page_putusan.agunan.FragmentAgunan1Identifikasi.dateClient;

public class ActivityAgunanTanahKosong extends FragmentActivity implements KeyValueListener, TextWatcher {


    //EXTENDED EDITTEXT
    @BindView(R.id.et_collateral_id_syiar)
    ExtendedEditText et_collateral_id_syiar;
    @BindView(R.id.et_tanggal_pemeriksaan_tanah_kosong)
    ExtendedEditText et_tanggal_pemeriksaan_tanah_kosong;
    @BindView(R.id.et_alamat_jaminan_tanah_kosong)
    ExtendedEditText et_alamat_jaminan_tanah_kosong;
    @BindView(R.id.et_kelurahan_dan_kecamatan_tanah_kosong)
    ExtendedEditText et_kelurahan_dan_kecamatan_tanah_kosong;
    @BindView(R.id.et_wilayah_dan_kota)
    ExtendedEditText et_wilayah_dan_kota;
    @BindView(R.id.et_hub_pemegang_hak_dengan_nasabah_tanah_kosong)
    ExtendedEditText et_hub_pemegang_hak_dengan_nasabah_tanah_kosong;
    @BindView(R.id.et_nama_penggarap)
    ExtendedEditText et_nama_penggarap;
    @BindView(R.id.et_nama_pemegang_hak)
    ExtendedEditText et_nama_pemegang_hak;
    @BindView(R.id.et_status_penggarap)
    ExtendedEditText et_status_penggarap;
    @BindView(R.id.et_hub_penggarap_dengan_pemegang_hak)
    ExtendedEditText et_hub_penggarap_dengan_pemegang_hak;
    @BindView(R.id.et_jenis_dokumen_tanah_kosong)
    ExtendedEditText et_jenis_dokumen_tanah_kosong;
    @BindView(R.id.et_no_bukti_hak)
    ExtendedEditText et_no_bukti_hak;
    @BindView(R.id.et_no_gs)
    ExtendedEditText et_no_gs;
    @BindView(R.id.et_jatuh_tempo_sertifikat)
    ExtendedEditText et_jatuh_tempo_sertifikat;
    @BindView(R.id.et_luas_tanah_tanah_kosong)
    ExtendedEditText et_luas_tanah_tanah_kosong;
    @BindView(R.id.et_harga_per_meter_tanah_kosong)
    ExtendedEditText et_harga_per_meter_tanah_kosong;
    @BindView(R.id.et_nilai_market_tanah_kosong)
    ExtendedEditText et_nilai_market_tanah_kosong;
    @BindView(R.id.et_nilai_likuidasi_tanah_kosong)
    ExtendedEditText et_nilai_likuidasi_tanah_kosong;
    @BindView(R.id.et_field_rekomendasi)
    ExtendedEditText et_field_rekomendasi;
    @BindView(R.id.et_nama_pemberi_informasi)
    ExtendedEditText et_nama_pemberi_informasi;
    @BindView(R.id.et_alamat_pemberi_informasi)
    ExtendedEditText et_alamat_pemberi_informasi;
    @BindView(R.id.et_no_pemberi_informasi)
    ExtendedEditText et_no_pemberi_informasi;
    @BindView(R.id.et_check_bpn_tanah_kosong)
    ExtendedEditText et_check_bpn_tanah_kosong;
    @BindView(R.id.et_dengan_siapa)
    ExtendedEditText et_dengan_siapa;
    @BindView(R.id.et_no_telp_bpn)
    ExtendedEditText et_no_telp_bpn;
    @BindView(R.id.et_hasil_bpn)
    ExtendedEditText et_hasil_bpn;
    @BindView(R.id.et_lebar_jalan_didepan_tanah_kosong)
    ExtendedEditText et_lebar_jalan_didepan_tanah_kosong;
    @BindView(R.id.et_pendapat_pemeriksa)
    ExtendedEditText et_pendapat_pemeriksa;



    //TEXTFIELDLAYOUT

    @BindView(R.id.tf_collateral_id_syiar)
    TextFieldBoxes tf_collateral_id_syiar;
    @BindView(R.id.tf_tanggal_pemeriksaan_tanah_kosong)
    TextFieldBoxes tf_tanggal_pemeriksaan_tanah_kosong;
    @BindView(R.id.tf_alamat_jaminan_tanah_kosong)
    TextFieldBoxes tf_alamat_jaminan_tanah_kosong;
    @BindView(R.id.tf_nama_pemegang_hak)
    TextFieldBoxes tf_nama_pemegang_hak;
    @BindView(R.id.tf_kelurahan_dan_kecamatan_tanah_kosong)
    TextFieldBoxes tf_kelurahan_dan_kecamatan_tanah_kosong;
    @BindView(R.id.tf_wilayah_dan_kota)
    TextFieldBoxes tf_wilayah_dan_kota;
    @BindView(R.id.tf_hub_pemegang_hak_dengan_nasabah_tanah_kosong)
    TextFieldBoxes tf_hub_pemegang_hak_dengan_nasabah_tanah_kosong;
    @BindView(R.id.tf_nama_penggarap)
    TextFieldBoxes tf_nama_penggarap;
    @BindView(R.id.tf_status_penggarap)
    TextFieldBoxes tf_status_penggarap;
    @BindView(R.id.tf_hub_penggarap_dengan_pemegang_hak)
    TextFieldBoxes tf_hub_penggarap_dengan_pemegang_hak;
    @BindView(R.id.tf_jenis_dokumen_tanah_kosong)
    TextFieldBoxes tf_jenis_dokumen_tanah_kosong;
    @BindView(R.id.tf_no_bukti_hak)
    TextFieldBoxes tf_no_bukti_hak;
    @BindView(R.id.tf_no_gs)
    TextFieldBoxes tf_no_gs;
    @BindView(R.id.tf_jatuh_tempo_sertifikat)
    TextFieldBoxes tf_jatuh_tempo_sertifikat;
    @BindView(R.id.tf_luas_tanah_tanah_kosong)
    TextFieldBoxes tf_luas_tanah_tanah_kosong;
    @BindView(R.id.tf_harga_per_meter_tanah_kosong)
    TextFieldBoxes tf_harga_per_meter_tanah_kosong;
    @BindView(R.id.tf_nilai_market_tanah_kosong)
    TextFieldBoxes tf_nilai_market_tanah_kosong;
    @BindView(R.id.tf_nilai_likuidasi_tanah_kosong)
    TextFieldBoxes tf_nilai_likuidasi_tanah_kosong;
    @BindView(R.id.tf_field_rekomendasi)
    TextFieldBoxes tf_field_rekomendasi;
    @BindView(R.id.tf_nama_pemberi_informasi)
    TextFieldBoxes tf_nama_pemberi_informasi;
    @BindView(R.id.tf_alamat_pemberi_informasi)
    TextFieldBoxes tf_alamat_pemberi_informasi;
    @BindView(R.id.tf_no_pemberi_informasi)
    TextFieldBoxes tf_no_pemberi_informasi;
    @BindView(R.id.tf_check_bpn_tanah_kosong)
    TextFieldBoxes tf_check_bpn_tanah_kosong;
    @BindView(R.id.tf_dengan_siapa)
    TextFieldBoxes tf_dengan_siapa;
    @BindView(R.id.tf_no_telp_bpn)
    TextFieldBoxes tf_no_telp_bpn;
    @BindView(R.id.tf_hasil_bpn)
    TextFieldBoxes tf_hasil_bpn;
    @BindView(R.id.tf_lebar_jalan_didepan_tanah_kosong)
    TextFieldBoxes tf_lebar_jalan_didepan_tanah_kosong;
    @BindView(R.id.tf_pendapat_pemeriksa)
    TextFieldBoxes tf_pendapat_pemeriksa;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_page_title)
    TextView tb_regular;

    @BindView(R.id.bt_simpan_agunan_tanah_kosong)
    Button bt_simpan_agunan_tanah_kosong;

    @BindView(R.id.iv_foto1)
    ImageView iv_foto1;

    //button foto
    @BindView(R.id.btn_upload_agunan)
    ImageView btn_upload_agunan;
    @BindView(R.id.btn_upload_barat)
    ImageView btn_upload_barat;
    @BindView(R.id.btn_upload_selatan)
    ImageView btn_upload_selatan;
    @BindView(R.id.btn_upload_timur)
    ImageView btn_upload_timur;
    @BindView(R.id.btn_upload_utara)
    ImageView btn_upload_utara;

    //bpn foto tombol
    @BindView(R.id.btn_upload_bpn_tanah_kosong)
    ImageView btn_upload_bpn_tanah_kosong;


    @BindView(R.id.img_agunan)
    ImageView img_agunan;
    @BindView(R.id.img_barat)
    ImageView img_barat;
    @BindView(R.id.img_selatan)
    ImageView img_selatan;
    @BindView(R.id.img_timur)
    ImageView img_timur;
    @BindView(R.id.img_utara)
    ImageView img_utara;

    //bpn imageview
    @BindView(R.id.img_bpn_tanah_kosong)
    ImageView img_bpn_tanah_kosong;


    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    @BindView(R.id.view_content_tanah_kosong)
    ScrollView content;

    private DatePickerDialog dpTanggalTerbit;
    private Calendar calTerbit;


    AgunanTanahKosong dataTanahKosong;
    List<AgunanTanahKosong> listDataTanahKosong;
    ApiClientAdapter apiClientAdapter;
    Bitmap bitmap_foto_1;

    LinearLayout bottom_sheet;
    LinearLayout pilihGaleri;
    LinearLayout pilihKamera;



    //KODE FOTO
    int REQUEST_FOTO_TANAH=0;
    int REQUEST_FOTO_UTARA=1;
    int REQUEST_FOTO_SELATAN=2;
    int REQUEST_FOTO_BARAT=3;
    int REQUEST_FOTO_TIMUR=4;
    //request foto bpn
    int REQUEST_FOTO_BPN=5;


    int REQUEST_GLOBAL=5;

    long KODE_FOTO_TANAH=0;
    long KODE_FOTO_UTARA=0;
    long KODE_FOTO_SELATAN=0;
    long KODE_FOTO_BARAT=0;
    long KODE_FOTO_TIMUR=0;
    //kode foto bpn
    long KODE_FOTO_BPN=0;

    String sudahUploadAgunan="belum";
    String sudahUploadUtara="belum";
    String sudahUploadSelatan="belum";
    String sudahUploadTimur="belum";
    String sudahUploadBarat="belum";
    String sudahUploadBpn="belum";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agunan_tanah_kosong);
        apiClientAdapter=new ApiClientAdapter(this);
        ButterKnife.bind(this);
        toolbarSettings();

       // loading.setVisibility(View.VISIBLE);
        bottom_sheet = findViewById(R.id.bottom_sheet);
        pilihKamera=findViewById(R.id.ll_takephoto);
        pilihGaleri=findViewById(R.id.ll_pickphoto);
        final BottomSheetBehavior behaviorBottomSheet = BottomSheetBehavior.from(bottom_sheet);

        inquiryTanahKosong();

        //expand on this for the rest of of the datepickers
        et_tanggal_pemeriksaan_tanah_kosong.setInputType(InputType.TYPE_NULL);
        et_tanggal_pemeriksaan_tanah_kosong.setFocusable(false);

        et_jatuh_tempo_sertifikat.setInputType(InputType.TYPE_NULL);
        et_jatuh_tempo_sertifikat.setFocusable(false);

        et_hub_penggarap_dengan_pemegang_hak.setInputType(InputType.TYPE_NULL);
        et_hub_penggarap_dengan_pemegang_hak.setFocusable(false);

        et_status_penggarap.setInputType(InputType.TYPE_NULL);
        et_status_penggarap.setFocusable(false);

        et_jenis_dokumen_tanah_kosong.setInputType(InputType.TYPE_NULL);
        et_jenis_dokumen_tanah_kosong.setFocusable(false);

        et_check_bpn_tanah_kosong.setInputType(InputType.TYPE_NULL);
        et_check_bpn_tanah_kosong.setFocusable(false);

        et_hasil_bpn.setInputType(InputType.TYPE_NULL);
        et_hasil_bpn.setFocusable(false);

        //menyesuaikan nilai likuidasi = 70% nilai market
        et_nilai_market_tanah_kosong.addTextChangedListener(new NumberTextWatcherForThousand(et_nilai_market_tanah_kosong));
        et_nilai_likuidasi_tanah_kosong.addTextChangedListener(new NumberTextWatcherForThousand(et_nilai_likuidasi_tanah_kosong));
        et_nilai_market_tanah_kosong.addTextChangedListener(this);







//        AppUtil.toolbarRegularFragment(this, "Agunan Tanah Kosong");
//        //on click listeners
//        tf_hub_pemegang_hak_dengan_nasabah_tanah_kosong.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openKeyValueDialog(tf_hub_pemegang_hak_dengan_nasabah_tanah_kosong.getLabelText());
//            }
//        });
//
//        tf_status_penggarap.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openKeyValueDialog(tf_status_penggarap.getLabelText());
//            }
//        });
//
//        tf_jenis_dokumen_tanah_kosong.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openKeyValueDialog(tf_jenis_dokumen_tanah_kosong.getLabelText());
//            }
//        });
//
//        tf_hasil_bpn.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openKeyValueDialog(tf_hasil_bpn.getLabelText());
//            }
//        });
//
//        tf_check_bpn_tanah_kosong.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openKeyValueDialog(tf_check_bpn_tanah_kosong.getLabelText());
//            }
//        });
//        tf_hub_penggarap_dengan_pemegang_hak.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openKeyValueDialog(tf_hub_penggarap_dengan_pemegang_hak.getLabelText());
//            }
//        });
//
//        //TIMEPICKERS LISTENERS
//        et_tanggal_pemeriksaan_tanah_kosong.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                dpTglTerbit(et_tanggal_pemeriksaan_tanah_kosong);
//
//            }
//        });
//        tf_tanggal_pemeriksaan_tanah_kosong.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dpTglTerbit(et_tanggal_pemeriksaan_tanah_kosong);
//            }
//        });
//        et_jatuh_tempo_sertifikat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                dpTglTerbit(et_jatuh_tempo_sertifikat);
//
//            }
//        });
//
//        tf_jatuh_tempo_sertifikat.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dpTglTerbit(et_jatuh_tempo_sertifikat);
//            }
//        });

        //END OF TIMEPICKERS LISTENERS



        //AMBIL FOTO CLICK LISTENERS

        //bottomsheets
        pilihGaleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery(REQUEST_GLOBAL);
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        pilihKamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCameraPermissionThenOpen(REQUEST_GLOBAL);
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        //end of bottom sheets

        //UPLOAD BUTTONS
        btn_upload_agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                REQUEST_GLOBAL=REQUEST_FOTO_TANAH;
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        btn_upload_utara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                REQUEST_GLOBAL=REQUEST_FOTO_UTARA;
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        btn_upload_selatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                REQUEST_GLOBAL=REQUEST_FOTO_SELATAN;
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        btn_upload_barat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                REQUEST_GLOBAL=REQUEST_FOTO_BARAT;
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        btn_upload_timur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                REQUEST_GLOBAL=REQUEST_FOTO_TIMUR;
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        //upload operation BPN
        btn_upload_bpn_tanah_kosong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                REQUEST_GLOBAL=REQUEST_FOTO_BPN;
                behaviorBottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        //SAVE AGUNAN BUTTON
        bt_simpan_agunan_tanah_kosong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasiField();
//                saveTanahKosong();
            }
        });




        

    }

    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getSupportFragmentManager(), title, this);
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {

//        if (title.equalsIgnoreCase("Hub Pemegang Hak dg Nasabah")){
//            et_hub_pemegang_hak_dengan_nasabah_tanah_kosong.setText(data.getName());
//
//        }
//        else if (title.equalsIgnoreCase("Status Penggarap")){
//            et_status_penggarap.setText(data.getName());
//        }
//        else if (title.equalsIgnoreCase("Jenis Dokumen")){
//            et_jenis_dokumen_tanah_kosong.setText(data.getName());
//        }
//        else if (title.equalsIgnoreCase("hasil")){
//            et_hasil_bpn.setText(data.getName());
//        }
//        else if (title.equalsIgnoreCase("Hub Penggarap dg Pemegang Hak")){
//            et_hub_penggarap_dengan_pemegang_hak.setText(data.getName());
//        }
//        else if (title.equalsIgnoreCase("check bpn")){
//            et_check_bpn_tanah_kosong.setText(data.getName());
//        }

        

    }
//CAMERA STUFF
    private void directOpenCamera(int cameraCode){
        Uri outputFileUri = getCaptureImageOutputUri();
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(captureIntent, cameraCode);
    }

    public void openGallery(int cameraCode) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), cameraCode);
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                outputFileUri = FileProvider.getUriForFile(ActivityAgunanTanahKosong.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotoTanahKosong.png"));
            }
            else{
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "fotoTanahKosong.png"));
            }
        }
        return outputFileUri;
    }

    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }



//ACTIVITY RESULT
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "Harap Tunggu Proses Upload", Toast.LENGTH_SHORT).show();
        try{

            bitmap_foto_1=MediaStore.Images.Media.getBitmap(this.getContentResolver(),  getPickImageResultUri(data));
            bitmap_foto_1=AppUtil.getResizedBitmap(bitmap_foto_1,1024);
            bitmap_foto_1=AppUtil.rotateImageIfRequired(this,bitmap_foto_1,getPickImageResultUri(data));

            if(requestCode==REQUEST_FOTO_TANAH){
                img_agunan.setImageBitmap(bitmap_foto_1);
            }
            else if(requestCode==REQUEST_FOTO_UTARA){
                img_utara.setImageBitmap(bitmap_foto_1);
            }
            else if(requestCode==REQUEST_FOTO_SELATAN){
                img_selatan.setImageBitmap(bitmap_foto_1);
            }
            else if(requestCode==REQUEST_FOTO_BARAT){
                img_barat.setImageBitmap(bitmap_foto_1);
            }
            else if(requestCode==REQUEST_FOTO_TIMUR){
                img_timur.setImageBitmap(bitmap_foto_1);
            }
            //else if bpn
            else if(requestCode==REQUEST_FOTO_BPN){
                img_bpn_tanah_kosong.setImageBitmap(bitmap_foto_1);
            }

            ImageHandler.saveImageToCache(this, bitmap_foto_1, "agunanTanahKosong.jpg");
            uploadFoto(requestCode,"agunanTanahKosong.jpg");
        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    //end of activity result


    public void uploadFoto(final int requestCode, String filename){
        loading.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);
        File imageFile = new File(getApplicationContext().getCacheDir(), filename);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadImage(fileBody);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    loading.setVisibility(View.GONE);
                    content.setVisibility(View.VISIBLE);
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            int idFoto = response.body().getData().get("id").getAsInt();
                            if(requestCode==REQUEST_FOTO_TANAH){
                                KODE_FOTO_TANAH=idFoto;
                                sudahUploadAgunan="sudah";
                            }
                           else if(requestCode==REQUEST_FOTO_UTARA){
                                KODE_FOTO_UTARA=idFoto;
                                sudahUploadUtara="sudah";
                            }
                            else if(requestCode==REQUEST_FOTO_SELATAN){
                                KODE_FOTO_SELATAN=idFoto;
                                sudahUploadSelatan="sudah";
                            }
                            else if(requestCode==REQUEST_FOTO_BARAT){
                                KODE_FOTO_BARAT=idFoto;
                                sudahUploadBarat="sudah";
                            }
                            else if(requestCode==REQUEST_FOTO_TIMUR){
                                KODE_FOTO_TIMUR=idFoto;
                                sudahUploadTimur="sudah";
                            }
                            //else if 2 bpn
                            else if(requestCode==REQUEST_FOTO_BPN){
                                KODE_FOTO_BPN=idFoto;
                                sudahUploadBpn="sudah";
                            }
                            Toast.makeText(ActivityAgunanTanahKosong.this, "Foto Berhasil diupload", Toast.LENGTH_SHORT).show();
                            Log.d("kodefoto1",Long.toString(KODE_FOTO_TANAH));
                            Log.d("kodefoto2",Long.toString(KODE_FOTO_UTARA));
                            Log.d("kodefoto3",Long.toString(KODE_FOTO_SELATAN));
                            Log.d("kodefoto4",Long.toString(KODE_FOTO_BARAT));
                            Log.d("kodefoto4",Long.toString(KODE_FOTO_TIMUR));


                        }
                        else{
                            loading.setVisibility(View.GONE);
                            content.setVisibility(View.VISIBLE);
                            AppUtil.notiferror(ActivityAgunanTanahKosong.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        loading.setVisibility(View.GONE);
                        content.setVisibility(View.VISIBLE);
                        Toast.makeText(ActivityAgunanTanahKosong.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
//                        AppUtil.notiferror(ActivityAgunanTanahKosong.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                content.setVisibility(View.VISIBLE);
                AppUtil.notiferror(ActivityAgunanTanahKosong.this, findViewById(android.R.id.content), ("Terjadi Kesalahan"));
            }
        });
    }

    public void saveTanahKosong(){
        loading.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);
        ReqSaveAgunanTanahKosong req = new ReqSaveAgunanTanahKosong();

        //kasih if kalo bisa edit, kalo 0  tandanya insert
        req.setIdAgunan(0);

        //pantekan
        req.setIdApl(101655);
        req.setIdCif(36961);
        req.setTypeProduk(20);
        //end of pantekan

try {
    req.setTglPemeriksaan(AppUtil.parseTanggalGeneral(et_tanggal_pemeriksaan_tanah_kosong.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
    req.setAlamatJaminan(et_alamat_jaminan_tanah_kosong.getText().toString());
    req.setKelKec(et_kelurahan_dan_kecamatan_tanah_kosong.getText().toString());
    req.setWilayahKota(et_wilayah_dan_kota.getText().toString());
    req.setNamaPemegangHak(et_nama_pemegang_hak.getText().toString());
    req.setHubPemegangHak(et_hub_pemegang_hak_dengan_nasabah_tanah_kosong.getText().toString());
    req.setNamaPenggarap(et_nama_penggarap.getText().toString());

    req.setHubPenggarap(et_hub_penggarap_dengan_pemegang_hak.getText().toString());
    req.setJenisDokumen(et_jenis_dokumen_tanah_kosong.getText().toString());
    req.setNoBuktiHak(et_no_bukti_hak.getText().toString());
    req.setStatusPenggarap(et_status_penggarap.getText().toString());
    req.setNoGs(et_no_gs.getText().toString());
    req.setTglJatuhTempoSertifikat(AppUtil.parseTanggalGeneral(et_jatuh_tempo_sertifikat.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
    req.setLuasTanah(Integer.parseInt(et_luas_tanah_tanah_kosong.getText().toString()));
    req.setHargaTanah(et_harga_per_meter_tanah_kosong.getText().toString());
    req.setNilaiMarketTanah(et_nilai_market_tanah_kosong.getText().toString());
    req.setNamaPemberiInfo1(et_nama_pemberi_informasi.getText().toString());
    req.setAlamatPemberiInfo1(et_alamat_pemberi_informasi.getText().toString());
    req.setNoTelpPemberiInfo1(et_no_pemberi_informasi.getText().toString());
    req.setCheckBpn(et_check_bpn_tanah_kosong.getText().toString());
    req.setNamaCheckBpn(et_dengan_siapa.getText().toString());


    req.setNotelCheckBpn(et_no_telp_bpn.getText().toString());
    req.setLebarJalan(et_lebar_jalan_didepan_tanah_kosong.getText().toString());
    req.setPendapatPemeriksa(et_pendapat_pemeriksa.getText().toString());
    req.setNilaiTaksasi(et_nilai_likuidasi_tanah_kosong.getText().toString());
    req.setFtv("");
    req.setHasilBpn(et_hasil_bpn.getText().toString());
    req.setNilaiPengikatan("");
    req.setKlasifikasiAgunan("");
    req.setJenisPengikatan("");
    req.setDescPengikatan("");
    req.setFotoAgunanTanahKosong("");
    req.setCollIdSyiar("");
    req.setIdPhotoTKutama(KODE_FOTO_TANAH);
    req.setIdPhotoTKbarat(KODE_FOTO_BARAT);
    req.setIdPhotoTKselatan(KODE_FOTO_SELATAN);
    req.setIdPhotoTKtimur(KODE_FOTO_TIMUR);
    req.setIdPhotoTKutara(KODE_FOTO_UTARA);
    //request kode foto bpn
    req.setIdPhotoTKbpn(KODE_FOTO_BPN);
}
catch(Exception e){
    Toast.makeText(this, "Terjadi kesalahan pada android", Toast.LENGTH_SHORT).show();
    Log.d("kesalahan",e.toString());
}




        Call<ParseResponse> call = apiClientAdapter.getApiInterface().saveTanahKosong(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    loading.setVisibility(View.GONE);
                    content.setVisibility(View.VISIBLE);
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
//                            int idFoto = response.body().getData().get("id").getAsInt();
                            Toast.makeText(ActivityAgunanTanahKosong.this, "Data Agunan Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            loading.setVisibility(View.GONE);
                            content.setVisibility(View.VISIBLE);
                            AppUtil.notiferror(ActivityAgunanTanahKosong.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        loading.setVisibility(View.GONE);
                        content.setVisibility(View.VISIBLE);
                        Toast.makeText(ActivityAgunanTanahKosong.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
//                        AppUtil.notiferror(ActivityAgunanTanahKosong.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                content.setVisibility(View.VISIBLE);
                AppUtil.notiferror(ActivityAgunanTanahKosong.this, findViewById(android.R.id.content), ("Terjadi Kesalahan"));
            }
        });
    }

    //PERMISSION CAMERA

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static int CAMERA_CODE_FORE_PERMISSION = 0;
    public void checkCameraPermissionThenOpen(int cameraCode)
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



    //END OF CAMERA STUFF

    private void toolbarSettings(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tb_regular.setText("Agunan Tanah Kosong");
    }

    private void dpTglTerbit(final ExtendedEditText edittext){
        calTerbit = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_expiredDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calTerbit.set(Calendar.YEAR, year);
                calTerbit.set(Calendar.MONTH, month);
                calTerbit.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calExpiredString = dateClient.format(calTerbit.getTime());
                edittext.setText(calExpiredString);
            }
        };

        dpTanggalTerbit = new DatePickerDialog(ActivityAgunanTanahKosong.this, R.style.AppTheme_TimePickerTheme, ls_expiredDate, calTerbit.get(Calendar.YEAR),
                calTerbit.get(Calendar.MONTH), calTerbit.get(Calendar.DAY_OF_MONTH));
        dpTanggalTerbit.getDatePicker().setMaxDate(calTerbit.getTimeInMillis());
        dpTanggalTerbit.show();
    }

    private void validasiField(){
        if(et_tanggal_pemeriksaan_tanah_kosong.getText().toString().isEmpty()||et_tanggal_pemeriksaan_tanah_kosong.getText().toString().equalsIgnoreCase("pilih")){
            Toast.makeText(this, "Tanggal pemeriksaan belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_alamat_jaminan_tanah_kosong.getText().toString().isEmpty()){
            tf_alamat_jaminan_tanah_kosong.setError("Harap diisi",true);
            Toast.makeText(this, "Alamat jaminan belum terisi", Toast.LENGTH_SHORT).show();
            et_alamat_jaminan_tanah_kosong.requestFocus();
        }
        else if(et_kelurahan_dan_kecamatan_tanah_kosong.getText().toString().isEmpty()){
            tf_kelurahan_dan_kecamatan_tanah_kosong.setError("Harap diisi",true);
            Toast.makeText(this, "Kelurahan dan kecamatan belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_wilayah_dan_kota.getText().toString().isEmpty()){
            tf_wilayah_dan_kota.setError("Harap diisi",true);
            Toast.makeText(this, "Wiltayah dan kota belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_nama_pemegang_hak.getText().toString().isEmpty()){
            tf_nama_pemegang_hak.setError("Harap diisi",true);
            Toast.makeText(this, "Nama pemegang hak belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_hub_pemegang_hak_dengan_nasabah_tanah_kosong.getText().toString().isEmpty()||et_hub_pemegang_hak_dengan_nasabah_tanah_kosong.getText().toString().equalsIgnoreCase("pilih")){
            tf_hub_pemegang_hak_dengan_nasabah_tanah_kosong.setError("Harap diisi",true);
            Toast.makeText(this, "Hub pemegang hak belum diisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_nama_penggarap.getText().toString().isEmpty()){
            tf_nama_penggarap.setError("Harap diisi",true);
            Toast.makeText(this, "Nama penggarap belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_hub_penggarap_dengan_pemegang_hak.getText().toString().isEmpty()||et_hub_penggarap_dengan_pemegang_hak.getText().toString().equalsIgnoreCase("pilih")){
            tf_hub_penggarap_dengan_pemegang_hak.setError("Harap diisi",true);
            Toast.makeText(this, "Hubungan penggarap belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_jenis_dokumen_tanah_kosong.getText().toString().isEmpty()||et_jenis_dokumen_tanah_kosong.getText().toString().equalsIgnoreCase("pilih")){
            tf_jenis_dokumen_tanah_kosong.setError("Harap diisi",true);
            Toast.makeText(this, "Jenis dokumen belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_no_bukti_hak.getText().toString().isEmpty()){
            tf_no_bukti_hak.setError("Harap diisi",true);
            Toast.makeText(this, "No bukti hak belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_status_penggarap.getText().toString().isEmpty()||et_status_penggarap.getText().toString().equalsIgnoreCase("pilih")){
            tf_status_penggarap.setError("Harap diisi",true);
            Toast.makeText(this, "Status penggarap belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_no_gs.getText().toString().isEmpty()){
            tf_no_gs.setError("Harap diisi",true);
            Toast.makeText(this, "No GS belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_jatuh_tempo_sertifikat.getText().toString().isEmpty()||et_jatuh_tempo_sertifikat.getText().toString().equalsIgnoreCase("pilih")){
            tf_jatuh_tempo_sertifikat.setError("Harap diisi",true);
            Toast.makeText(this, "Tanggal jatuh tempo belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_luas_tanah_tanah_kosong.getText().toString().isEmpty()){
            tf_luas_tanah_tanah_kosong.setError("Harap diisi",true);
            Toast.makeText(this, "Luas tanah belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_harga_per_meter_tanah_kosong.getText().toString().isEmpty()){
            tf_harga_per_meter_tanah_kosong.setError("Harap diisi",true);
            Toast.makeText(this, "Nilai market belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_nilai_market_tanah_kosong.getText().toString().isEmpty()){
            tf_nilai_market_tanah_kosong.setError("Harap diisi",true);
            Toast.makeText(this, "Nilai Market belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_nama_pemberi_informasi.getText().toString().isEmpty()){
            tf_nama_pemberi_informasi.setError("Harap diisi",true);
            Toast.makeText(this, "Nama pemberi informasi belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_alamat_pemberi_informasi.getText().toString().isEmpty()){
            tf_alamat_pemberi_informasi.setError("Harap diisi",true);
            Toast.makeText(this, "Alamat pemberi informasi belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_no_pemberi_informasi.getText().toString().isEmpty()){
            tf_no_pemberi_informasi.setError("Harap diisi",true);
            Toast.makeText(this, "Nomor pemberi informasi belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_check_bpn_tanah_kosong.getText().toString().isEmpty()||et_check_bpn_tanah_kosong.getText().toString().equalsIgnoreCase("pilih")){
            tf_check_bpn_tanah_kosong.setError("Harap diisi",true);
            Toast.makeText(this, "Cek Bpn belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_no_telp_bpn.getText().toString().isEmpty()&&et_check_bpn_tanah_kosong.getText().toString().equalsIgnoreCase("ya")){
            tf_no_telp_bpn.setError("Harap diisi",true);
            Toast.makeText(this, "No telepom pihak Bpn belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if((et_hasil_bpn.getText().toString().isEmpty() ||et_hasil_bpn.getText().toString().equalsIgnoreCase("pilih"))&&et_check_bpn_tanah_kosong.getText().toString().equalsIgnoreCase("ya")){
            tf_hasil_bpn.setError("Harap diisi",true);
            Toast.makeText(this, "Hasil Bpn belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_dengan_siapa.getText().toString().isEmpty()){
            tf_dengan_siapa.setError("Harap diisi",true);
            Toast.makeText(this, "Nama pihak BPN belum diisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_lebar_jalan_didepan_tanah_kosong.getText().toString().isEmpty()){
            tf_lebar_jalan_didepan_tanah_kosong.setError("Harap diisi",true);
            Toast.makeText(this, "Lebar jalan belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_pendapat_pemeriksa.getText().toString().isEmpty()){
            tf_pendapat_pemeriksa.setError("Harap diisi",true);
            Toast.makeText(this, "Pendapat pemeriksa belum terisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_nilai_likuidasi_tanah_kosong.getText().toString().isEmpty()){
            tf_nilai_likuidasi_tanah_kosong.setError("Harap diisi",true);
            Toast.makeText(this, "Nilai likuidasi belum terisi", Toast.LENGTH_SHORT).show();
        }

        //APAKAH SUDAH UPLOAD FOTO
        else if(sudahUploadAgunan.equalsIgnoreCase("belum")){
            Toast.makeText(this, "Masukkan foto tanah sebelum menyimpan", Toast.LENGTH_SHORT).show();
        }
        else if(sudahUploadUtara.equalsIgnoreCase("belum")){
            Toast.makeText(this, "Masukkan foto batas utara sebelum menyimpan", Toast.LENGTH_SHORT).show();
        }
        else if(sudahUploadSelatan.equalsIgnoreCase("belum")){
            Toast.makeText(this, "Masukkan foto batas selatan sebelum menyimpan", Toast.LENGTH_SHORT).show();
        }
        else if(sudahUploadBarat.equalsIgnoreCase("belum")){
            Toast.makeText(this, "Masukkan foto batas barat sebelum menyimpan", Toast.LENGTH_SHORT).show();
        }
        else if(sudahUploadTimur.equalsIgnoreCase("belum")){
            Toast.makeText(this, "Masukkan foto batas timur sebelum menyimpan", Toast.LENGTH_SHORT).show();
        }
        //ELSE IF 3 UPLOAD BPN
        else if(sudahUploadBpn.equalsIgnoreCase("belum")&&et_check_bpn_tanah_kosong.getText().toString().equalsIgnoreCase("ya")){
            Toast.makeText(this, "Masukkan foto bukti sertifikat BPN sebelum menyimpan", Toast.LENGTH_SHORT).show();
        }
        else{
            saveTanahKosong();
        }

    }

    public void inquiryTanahKosong() {
        //  dataUser = getListUser();
        //progressbar_loading.setVisibility(View.VISIBLE);

        content.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        ReqAgunanTanahKosong req = new ReqAgunanTanahKosong();
        AppPreferences appPreferences=new AppPreferences(ActivityAgunanTanahKosong.this);

//        pantekan
//        req.setIdAgunan(Integer.parseInt("6817"));
//        req.setIdApl(Integer.parseInt("101655"));


//        data real
        req.setIdAgunan(Integer.parseInt(getIntent().getStringExtra("idAgunan")));
        req.setIdApl(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        req.setIdCif(Integer.parseInt(getIntent().getStringExtra("cif")));

        //ganti jadi list pemutus deviasi jika sudah ada middletier
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryTanahKosong(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                //progressbar_loading.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<AgunanTanahKosong>>() {
                        }.getType();
                        content.setVisibility(View.VISIBLE);
                        loading.setVisibility(View.GONE);
                        listDataTanahKosong = gson.fromJson(listDataString, type);
                        dataTanahKosong=listDataTanahKosong.get(0);
                        et_collateral_id_syiar.setText(dataTanahKosong.getCollIdSyiar());


                        et_tanggal_pemeriksaan_tanah_kosong.setText( AppUtil.parseTanggalGeneral(dataTanahKosong.getTglPemeriksaan().trim(), "ddMMyyyy", "dd-MM-yyyy"));
                        et_alamat_jaminan_tanah_kosong.setText(dataTanahKosong.getAlamatJaminan());
                        et_kelurahan_dan_kecamatan_tanah_kosong.setText(dataTanahKosong.getKelKec());
                        et_wilayah_dan_kota.setText(dataTanahKosong.getWilayahKota());
                        et_hub_pemegang_hak_dengan_nasabah_tanah_kosong.setText(dataTanahKosong.getHubPemegangHak());
                        et_nama_penggarap.setText(dataTanahKosong.getNamaPenggarap());
                        et_nama_pemegang_hak.setText(dataTanahKosong.getNamaPemegangHak());
                        et_status_penggarap.setText(dataTanahKosong.getStatusPenggarap());
                        et_hub_penggarap_dengan_pemegang_hak.setText(dataTanahKosong.getHubPenggarap());
                        et_jenis_dokumen_tanah_kosong.setText(dataTanahKosong.getJenisDokumen());
                        et_no_bukti_hak.setText(dataTanahKosong.getNoBuktiHak());
                        et_no_gs.setText(dataTanahKosong.getNoGs());
                        et_jatuh_tempo_sertifikat.setText( AppUtil.parseTanggalGeneral(dataTanahKosong.getTglJatuhTempoSertifikat().trim(), "ddMMyyyy", "dd-MM-yyyy"));
                        et_luas_tanah_tanah_kosong.setText(dataTanahKosong.getLuasTanah());
                        et_harga_per_meter_tanah_kosong.setText(AppUtil.parseRupiah(dataTanahKosong.getHargaTanah()));
                        et_nilai_market_tanah_kosong.setText(dataTanahKosong.getNilaiMarketTanah());
                        et_nilai_likuidasi_tanah_kosong.setText(dataTanahKosong.getNilaiTaksasi());

                        et_nama_pemberi_informasi.setText(dataTanahKosong.getNamaPemberiInfo1());
                        et_alamat_pemberi_informasi.setText(dataTanahKosong.getAlamatPemberiInfo1());
                        et_no_pemberi_informasi.setText(dataTanahKosong.getNoTelpPemberiInfo1());
                        et_check_bpn_tanah_kosong.setText(dataTanahKosong.getCheckBpn());
                        et_dengan_siapa.setText(dataTanahKosong.getNamaCheckBpn());
                        et_no_telp_bpn.setText(dataTanahKosong.getNotelCheckBpn());
                        et_hasil_bpn.setText(dataTanahKosong.getHasilBpn());
                        et_lebar_jalan_didepan_tanah_kosong.setText(dataTanahKosong.getLebarJalan());
                        et_pendapat_pemeriksa.setText(dataTanahKosong.getPendapatPemeriksa());
                       et_alamat_jaminan_tanah_kosong.setText(dataTanahKosong.getAlamatJaminan());



                       //load foto

                        RequestOptions options = new RequestOptions();
                        options.error(R.mipmap.ico_img_for_upload);
                        options.placeholder(R.mipmap.ico_img_for_upload);
//                                .centerCrop()
//                                .placeholder(R.mipmap.ico_img_for_upload)
//                                .diskCacheStrategy(DiskCacheStrategy.ALL);


                        Glide.with(ActivityAgunanTanahKosong.this)
                                .asBitmap()
                                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+Long.toString(dataTanahKosong.getIdPhotoTKutama()))
                                .apply(options)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        img_agunan.setImageBitmap(resource);

                                    }
                                });



                        Glide.with(ActivityAgunanTanahKosong.this)
                                .asBitmap()
                                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+Long.toString(dataTanahKosong.getIdPhotoTKselatan()))
                                .apply(options)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        img_selatan.setImageBitmap(resource);

                                    }
                                });

                        Glide.with(ActivityAgunanTanahKosong.this)
                                .asBitmap()
                                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+Long.toString(dataTanahKosong.getIdPhotoTKbarat()))
                                .apply(options)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        img_barat.setImageBitmap(resource);

                                    }
                                });

                        Glide.with(ActivityAgunanTanahKosong.this)
                                .asBitmap()
                                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+Long.toString(dataTanahKosong.getIdPhotoTKutara()))
                                .apply(options)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        img_utara.setImageBitmap(resource);

                                    }
                                });

                        Glide.with(ActivityAgunanTanahKosong.this)
                                .asBitmap()
                                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+Long.toString(dataTanahKosong.getIdPhotoTKtimur()))
                                .apply(options)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        img_timur.setImageBitmap(resource);

                                    }
                                });

                        //glide bpn

                        Glide.with(ActivityAgunanTanahKosong.this)
                                .asBitmap()
                                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+Long.toString(dataTanahKosong.getIdPhotoTKbpn()))
                                .apply(options)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        img_bpn_tanah_kosong.setImageBitmap(resource);

                                    }
                                });

                        img_agunan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(ActivityAgunanTanahKosong.this, ActivityFotoKelengkapanDokumen.class);
                                intent.putExtra("id_foto",Long.valueOf(dataTanahKosong.getIdPhotoTKutama()).intValue());
                                startActivity(intent);
                            }
                        });

                        img_utara.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(ActivityAgunanTanahKosong.this, ActivityFotoKelengkapanDokumen.class);
                                intent.putExtra("id_foto",Long.valueOf(dataTanahKosong.getIdPhotoTKutara()).intValue());
                                startActivity(intent);
                            }
                        });

                        img_selatan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(ActivityAgunanTanahKosong.this, ActivityFotoKelengkapanDokumen.class);
                                intent.putExtra("id_foto",Long.valueOf(dataTanahKosong.getIdPhotoTKselatan()).intValue());
                                startActivity(intent);
                            }
                        });

                        img_barat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(ActivityAgunanTanahKosong.this, ActivityFotoKelengkapanDokumen.class);
                                intent.putExtra("id_foto",Long.valueOf(dataTanahKosong.getIdPhotoTKbarat()).intValue());
                                startActivity(intent);
                            }
                        });

                        img_timur.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(ActivityAgunanTanahKosong.this, ActivityFotoKelengkapanDokumen.class);
                                intent.putExtra("id_foto",Long.valueOf(dataTanahKosong.getIdPhotoTKtimur()).intValue());
                                startActivity(intent);
                            }
                        });

                        img_bpn_tanah_kosong.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(ActivityAgunanTanahKosong.this, ActivityFotoKelengkapanDokumen.class);
                                intent.putExtra("id_foto",Long.valueOf(dataTanahKosong.getIdPhotoTKbpn()).intValue());
                                startActivity(intent);
                            }
                        });




                    }
                    else{
                        content.setVisibility(View.VISIBLE);
                        loading.setVisibility(View.GONE);
                        Toast.makeText(ActivityAgunanTanahKosong.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    content.setVisibility(View.VISIBLE);
                    loading.setVisibility(View.GONE);
                    Toast.makeText(ActivityAgunanTanahKosong.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                content.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                Toast.makeText(ActivityAgunanTanahKosong.this, "Gagal tersambung ke server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        BigDecimal nilaiLikuidasi=new BigDecimal(0);
        if(et_nilai_market_tanah_kosong.getText().toString().length()>0 || !et_nilai_market_tanah_kosong.getText().toString().isEmpty()){
            nilaiLikuidasi=new BigDecimal(70).divide(new BigDecimal(100), 2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_nilai_market_tanah_kosong.getText().toString()))).setScale(0,BigDecimal.ROUND_HALF_UP);
        }
        et_nilai_likuidasi_tanah_kosong.setText(nilaiLikuidasi.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
