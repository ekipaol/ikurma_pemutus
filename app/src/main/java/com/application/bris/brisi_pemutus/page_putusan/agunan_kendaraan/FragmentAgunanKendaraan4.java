package com.application.bris.brisi_pemutus.page_putusan.agunan_kendaraan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.agunan_kendaraan.AgunanKendaraan;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityFotoKelengkapanDokumen;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.BitmapImageViewRounded;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * A simple {@link Fragment} subclass.
 */

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunanKendaraan4 extends Fragment implements Step{

    @BindView(R.id.tf_nama_informan)
    TextFieldBoxes tf_nama_informan;
    @BindView(R.id.et_nama_informan)
    EditText et_nama_informan;

    @BindView(R.id.tf_alamat_informan)
    TextFieldBoxes tf_alamat_informan;
    @BindView(R.id.et_alamat_informan)
    EditText et_alamat_informan;

    @BindView(R.id.tf_telp_informan)
    TextFieldBoxes tf_telp_informan;
    @BindView(R.id.et_telp_informan)
    EditText et_telp_informan;

    @BindView(R.id.tf_keterangan)
    TextFieldBoxes tf_keterangan;
    @BindView(R.id.et_keterangan)
    EditText et_keterangan;

    @BindView(R.id.img_agunan_1)
    BitmapImageViewRounded img_agunan_1;
    @BindView(R.id.btn_upload_agunan_1)
    ImageView btn_upload_agunan_1;

    @BindView(R.id.img_agunan_2)
    BitmapImageViewRounded img_agunan_2;
    @BindView(R.id.btn_upload_agunan_2)
    ImageView btn_upload_agunan_2;

    @BindView(R.id.img_agunan_3)
    BitmapImageViewRounded img_agunan_3;
    @BindView(R.id.btn_upload_agunan_3)
    ImageView btn_upload_agunan_3;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    private AgunanKendaraan dataAgunan;
    private String idAgunan;

    public static String val_NamaInforman = "";
    public static String val_AlamatInforman = "";
    public static String val_TelpInforman = "";
    public static String val_Keterangan = "";
    public static String val_ImgAgunan1 = "";
    public static String val_ImgAgunan2 = "";
    public static String val_ImgAgunan3 = "";

    private final int PICK_IMAGE_AGUNAN1 = 1;
    private final int TAKE_PICTURE_AGUNAN1 = 11;

    private final int PICK_IMAGE_AGUNAN2 = 2;
    private final int TAKE_PICTURE_AGUNAN2 = 21;

    private final int PICK_IMAGE_AGUNAN3 = 3;
    private final int TAKE_PICTURE_AGUNAN3 = 31;

    private Uri uriPhotoAgunan1, uriPhotoAgunan2, uriPhotoAgunan3;
    private Bitmap bitmapPhotoAgunan1, bitmapPhotoAgunan2, bitmapPhotoAgunan3;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    private String idGlobal = "";

    private String sudahUploadAgunan1 = "belum";
    private String sudahUploadAgunan2 = "belum";
    private String sudahUploadAgunan3 = "belum";

    private int idFotoAgunan1 = 0;
    private int idFotoAgunan2 = 0;
    private int idFotoAgunan3 = 0;

    public FragmentAgunanKendaraan4() {
    }

    public FragmentAgunanKendaraan4(String midAgunan, AgunanKendaraan magunanData) {
        dataAgunan = magunanData;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_kendaraan_4, container, false);
        ButterKnife.bind(this, view);
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());

        setData();
        disableTextfield();

//        if (!idAgunan.equalsIgnoreCase("0")) {
//            setData();
//        }

        return view;
    }

    private void setData() {
        et_nama_informan.setText(dataAgunan.getNamaPemberiInfo1());
        et_alamat_informan.setText(dataAgunan.getAlamatPemberiInfo1());
        et_telp_informan.setText(dataAgunan.getNoTelpPemberiInfo1());
        et_keterangan.setText(dataAgunan.getKeterangan());

        final int id_agunan_1 = dataAgunan.getIdPhotoKDUtama();
        final int id_agunan_2 = dataAgunan.getIdPhotoKDInterior();
        final int id_agunan_3 = dataAgunan.getIdPhotoKDMesin();

//        String ImgTanah = UriApi.Baseurl.URL + UriApi.foto.urlFoto + id_agunan_1;
        val_ImgAgunan1 = String.valueOf(id_agunan_1);
//        Glide
//                .with(getContext())
//                .asBitmap()
//                .load(ImgTanah)
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        img_agunan_1.setImageBitmap(resource);
//                        bitmapPhotoAgunan1 = resource;
//                        sudahUploadAgunan1 = "sudah";
//                    }
//                });

        AppUtil.setImageGlideInt(getContext(),id_agunan_1,img_agunan_1);




//        String ImgBatasUtara = UriApi.Baseurl.URL + UriApi.foto.urlFoto + id_agunan_2;
//        val_ImgAgunan2 = String.valueOf(id_agunan_2);
//        Glide
//                .with(getContext())
//                .asBitmap()
//                .load(ImgBatasUtara)
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        img_agunan_2.setImageBitmap(resource);
//                        bitmapPhotoAgunan2 = resource;
//                        sudahUploadAgunan2 = "sudah";
//                    }
//                });

        AppUtil.setImageGlideInt(getContext(),id_agunan_2,img_agunan_2);

//        String ImgBatasSelatan = UriApi.Baseurl.URL + UriApi.foto.urlFoto + id_agunan_3;
//        val_ImgAgunan3 = String.valueOf(id_agunan_3);
//        Glide
//                .with(getContext())
//                .asBitmap()
//                .load(ImgBatasSelatan)
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        img_agunan_3.setImageBitmap(resource);
//                        bitmapPhotoAgunan3 = resource;
//                        sudahUploadAgunan3 = "sudah";
//                    }
//                });

        AppUtil.setImageGlideInt(getContext(),id_agunan_3,img_agunan_3);


        img_agunan_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(id_agunan_1));
                startActivity(intent);
            }
        });

        img_agunan_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(id_agunan_2));
                startActivity(intent);
            }
        });

        img_agunan_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(id_agunan_3));
                startActivity(intent);
            }
        });
    }

    private void disableTextfield() {

        et_nama_informan.setInputType(InputType.TYPE_NULL);
        et_nama_informan.setFocusable(false);

        et_alamat_informan.setInputType(InputType.TYPE_NULL);
        et_alamat_informan.setFocusable(false);

        et_telp_informan.setInputType(InputType.TYPE_NULL);
        et_telp_informan.setFocusable(false);

        et_keterangan.setInputType(InputType.TYPE_NULL);
        et_keterangan.setFocusable(false);
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
        AppUtil.notifwarning(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }




}