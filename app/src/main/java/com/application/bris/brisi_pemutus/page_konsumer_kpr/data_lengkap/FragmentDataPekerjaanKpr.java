package com.application.bris.brisi_pemutus.page_konsumer_kpr.data_lengkap;


import android.Manifest;
import android.annotation.SuppressLint;
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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkapKpr;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityFotoKelengkapanDokumen;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.KeyValue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;
import com.makeramen.roundedimageview.RoundedImageView;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentDataPekerjaanKpr extends Fragment implements Step {

    @BindView(R.id.ll_datapekerjaan)
    LinearLayout ll_datapekerjaan;

    @BindView(R.id.tf_bidang_pekerjaan)
    TextFieldBoxes tf_bidang_pekerjaan;
    @BindView(R.id.et_bidang_pekerjaan)
    EditText et_bidang_pekerjaan;

    @BindView(R.id.tf_jenis_pekerjaan)
    TextFieldBoxes tf_jenis_pekerjaan;
    @BindView(R.id.et_jenis_pekerjaan)
    EditText et_jenis_pekerjaan;

    @BindView(R.id.tf_namaperusahaan)
    TextFieldBoxes tf_namaperusahaan;
    @BindView(R.id.et_namaperusahaan)
    EditText et_namaperusahaan;

    @BindView(R.id.tf_tanggalmulaiperusahaan)
    TextFieldBoxes tf_tanggalmulaiperusahaan;
    @BindView(R.id.et_tanggalmulaiperusahaan)
    EditText et_tanggalmulaiperusahaan;

    @BindView(R.id.tf_nomortelponperusahaan)
    TextFieldBoxes tf_nomortelponperusahaan;
    @BindView(R.id.et_nomortelponperusahaan)
    EditText et_nomortelponperusahaan;

    //USAHA
    @BindView(R.id.sw_usahaktpsama)
    Switch sw_usahaktpsama;
    @BindView(R.id.ll_alamatperusahaan)
    LinearLayout ll_alamatperusahaan;
    @BindView(R.id.tf_alamatperusahaan)
    TextFieldBoxes tf_alamatperusahaan;
    @BindView(R.id.et_alamatperusahaan)
    EditText et_alamatperusahaan;
    @BindView(R.id.tf_rtperusahaan)
    TextFieldBoxes tf_rtperusahaan;
    @BindView(R.id.et_rtperusahaan)
    EditText et_rtperusahaan;
    @BindView(R.id.tf_rwperusahaan)
    TextFieldBoxes tf_rwperusahaan;
    @BindView(R.id.et_rwperusahaan)
    EditText et_rwperusahaan;
    @BindView(R.id.tf_provinsiperusahaan)
    TextFieldBoxes tf_provinsiperusahaan;
    @BindView(R.id.et_provinsiperusahaan)
    EditText et_provinsiperusahaan;
    @BindView(R.id.tf_kotaperusahaan)
    TextFieldBoxes tf_kotaperusahaan;
    @BindView(R.id.et_kotaperusahaan)
    EditText et_kotaperusahaan;
    @BindView(R.id.tf_kecamatanperusahaan)
    TextFieldBoxes tf_kecamatanperusahaan;
    @BindView(R.id.et_kecamatanperusahaan)
    EditText et_kecamatanperusahaan;
    @BindView(R.id.tf_kelurahanperusahaan)
    TextFieldBoxes tf_kelurahanperusahaan;
    @BindView(R.id.et_kelurahanperusahaan)
    EditText et_kelurahanperusahaan;
    @BindView(R.id.tf_kodeposperusahaan)
    TextFieldBoxes tf_kodeposperusahaan;
    @BindView(R.id.et_kodeposperusahaan)
    EditText et_kodeposperusahaan;


    @BindView(R.id.tf_nomor_sk_pangkat_terakhir)
    TextFieldBoxes tf_nomor_sk_pangkat_terakhir;
    @BindView(R.id.et_nomor_sk_pangkat_terakhir)
    EditText et_nomor_sk_pangkat_terakhir;


    @BindView(R.id.tf_status_kepegawaian)
    TextFieldBoxes tf_status_kepegawaian;
    @BindView(R.id.et_status_kepegawaian)
    EditText et_status_kepegawaian;

    @BindView(R.id.tf_deskripsi_pekerjaan)
    TextFieldBoxes tf_deskripsi_pekerjaan;
    @BindView(R.id.et_deskripsi_pekerjaan)
    EditText et_deskripsi_pekerjaan;

    @BindView(R.id.tf_usia_mpp)
    TextFieldBoxes tf_usia_mpp;
    @BindView(R.id.et_usia_mpp)
    EditText et_usia_mpp;

    @BindView(R.id.tf_posisi_jabatan)
    TextFieldBoxes tf_posisi_jabatan;
    @BindView(R.id.et_posisi_jabatan)
    EditText et_posisi_jabatan;

    @BindView(R.id.tf_nama_pihak_ketiga)
    TextFieldBoxes tf_nama_pihak_ketiga;
    @BindView(R.id.et_nama_pihak_ketiga)
    EditText et_nama_pihak_ketiga;

    @BindView(R.id.tf_nama_project)
    TextFieldBoxes tf_nama_project;
    @BindView(R.id.et_nama_project)
    EditText et_nama_project;

    @BindView(R.id.tf_jenis_kpr)
    TextFieldBoxes tf_jenis_kpr;
    @BindView(R.id.et_jenis_kpr)
    EditText et_jenis_kpr;



    @BindView(R.id.btn_perusahaan)
    Button btn_perusahaan;

    @BindView(R.id.img_fotokantor1)
    RoundedImageView img_fotokantor1;
    @BindView(R.id.btn_upload_fotokantor1)
    ImageView btn_upload_fotokantor1;
    @BindView(R.id.img_fotokantor2)
    RoundedImageView img_fotokantor2;
    @BindView(R.id.btn_upload_fotokantor2)
    ImageView btn_upload_fotokantor2;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.progress_kantor1)
    ProgressBar progress_kantor1;
    @BindView(R.id.progress_kantor2)
    ProgressBar progress_kantor2;

    private Realm realm;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private DataLengkapKpr dataLengkap;


    private Uri uriPhotoKantor1, uriPhotoKantor2;
    private Bitmap bitmapPhotoKantor1, bitmapPhotoKantor2, loadedPicture;

//    private String sudahUploadKantor1 = "belum";
//    private String sudahUploadKantor2 = "belum";
//
//    private int idFotoKantor1 = 0;
//    private int idFotoKantor2 = 0;



    @SuppressLint("ValidFragment")

    public FragmentDataPekerjaanKpr(DataLengkapKpr mdataLengkap) {
        dataLengkap = mdataLengkap;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_data_perusahaan_kpr, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());


        setData();
        AppUtil.disableEditTexts(ll_datapekerjaan);

        return view;
    }



    private void setData() {



        et_bidang_pekerjaan.setText(KeyValue.getKeyUsahaorJob(dataLengkap.getBidangPekerjaan()));
        et_namaperusahaan.setText(dataLengkap.getNamaPerusahaan());
        et_tanggalmulaiperusahaan.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglMulaiBekerja(), "ddMMyyyy", "dd-MM-yyyy"));
        et_nomortelponperusahaan.setText(dataLengkap.getNoTelpPerusahaan());
        et_alamatperusahaan.setText(dataLengkap.getAlamatPerusahaan());
//        et_rtperusahaan.setText(dataLengkap.getRtUsaha());
//        et_rwperusahaan.setText(dataLengkap.getRwUsaha());
        et_provinsiperusahaan.setText(dataLengkap.getProvPerusahaan());
        et_kotaperusahaan.setText(dataLengkap.getKotaPerusahaan());
        et_kecamatanperusahaan.setText(dataLengkap.getKecPerusahaan());
        et_kelurahanperusahaan.setText(dataLengkap.getKelPerusahaan());
        et_kodeposperusahaan.setText(dataLengkap.getKodePosPerusahaan());
        et_nomor_sk_pangkat_terakhir.setText(dataLengkap.getNoSKterakhir());

        et_nama_project.setText(dataLengkap.getNamaProject());
        et_nama_pihak_ketiga.setText(dataLengkap.getNamaPihakKetiga());
        et_jenis_kpr.setText(dataLengkap.getJenisKPR());

        //trim the SK, karena dari service dapet spasi banyak banget
        et_nomor_sk_pangkat_terakhir.setText(et_nomor_sk_pangkat_terakhir.getText().toString().trim());

        et_deskripsi_pekerjaan.setText(dataLengkap.getDetilJenisPekerjaanDesc());
        et_jenis_pekerjaan.setText(dataLengkap.getJenisPekerjaanDesc());


        et_status_kepegawaian.setText(KeyValue.getKeyStatusKepegawaian(dataLengkap.getStatusKepegawaian()));

        et_usia_mpp.setText(dataLengkap.getUsiaMpp());
        et_posisi_jabatan.setText(KeyValue.getKeyPosisiJabatan(dataLengkap.getJabatan()));

        bitmapPhotoKantor1 = setLoadImage(img_fotokantor1, dataLengkap.getFID_PHOTO_KANTOR1());
        bitmapPhotoKantor2 = setLoadImage(img_fotokantor2, dataLengkap.getFID_PHOTO_KANTOR2());

        img_fotokantor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(dataLengkap.getFID_PHOTO_KANTOR1()));
                startActivity(intent);
            }
        });

        img_fotokantor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(dataLengkap.getFID_PHOTO_KANTOR2()));
                startActivity(intent);
            }
        });


    }

    public Bitmap setLoadImage(final ImageView iv, int idFoto){
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFoto + idFoto;
        Glide
                .with(getContext())
                .asBitmap()
                .load(url_photo)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv.setImageBitmap(resource);
                        loadedPicture = resource;
                    }
                });
        return loadedPicture;
    }







    @Nullable
    @Override
    public VerificationError verifyStep() {

        return null;

    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

}