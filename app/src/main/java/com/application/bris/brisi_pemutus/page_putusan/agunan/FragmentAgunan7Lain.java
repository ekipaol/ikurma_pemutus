package com.application.bris.brisi_pemutus.page_putusan.agunan;



import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.widget.EditText;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.model.agunan.Agunan;
import com.application.bris.brisi_pemutus.model.list_foto_agunan.ListFotoAgunan;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityFotoKelengkapanDokumen;
import com.application.bris.brisi_pemutus.util.BitmapImageViewRounded;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunan7Lain extends Fragment implements Step {

    @BindView(R.id.tf_pendapat_harga)
    TextFieldBoxes tf_pendapat_harga;
    @BindView(R.id.et_pendapat_harga)
    EditText et_pendapat_harga;

    @BindView(R.id.tf_kondisi_lingkungan)
    TextFieldBoxes tf_kondisi_lingkungan;
    @BindView(R.id.et_kondisi_lingkungan)
    EditText et_kondisi_lingkungan;

    @BindView(R.id.tf_pendapat_kondisi_jaminan)
    TextFieldBoxes tf_pendapat_kondisi_jaminan;
    @BindView(R.id.et_pendapat_kondisi_jaminan)
    EditText et_pendapat_kondisi_jaminan;

    @BindView(R.id.img_agunan)
    BitmapImageViewRounded img_agunan;
    @BindView(R.id.img_utara)
    BitmapImageViewRounded img_utara;
    @BindView(R.id.img_selatan)
    BitmapImageViewRounded img_selatan;
    @BindView(R.id.img_barat)
    BitmapImageViewRounded img_barat;
    @BindView(R.id.img_timur)
    BitmapImageViewRounded img_timur;

    private Calendar calLahir;
    private Calendar calLahirPasangan;
    private Calendar calExpiredNik;

    private DatePickerDialog dpTanggalLahir;
    private DatePickerDialog dpTanggalLahirPasangan;
    private DatePickerDialog dpExpiredNik;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    public static SimpleDateFormat dateServer = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private Agunan dataLengkap;
    private List<ListFotoAgunan> listFoto;

    public static String val_NamaAlias ="";
    public static String val_NoKtpPasangan ="";
    public static String val_StatusNikah ="";
    public static String val_NoHp ="";
    public static String val_NamaNasabah ="";
    public static String val_Npwp ="";
    public static String val_PendTerakhir ="";
    public static String val_KetGelar ="";
    public static String val_KetAgama ="";
    public static String val_Agama ="";
    public static String val_NamaIbu ="";
    public static String val_NamaPasangan ="";
    public static String val_Email ="";
    public static String val_TelpKeluarga ="";
    public static String val_ExpId ="";
    public static String val_TglLahirPasangan ="";
    public static String val_NoKtp ="";
    public static int val_JlhTanggungan = 0;
    public static String val_TglLahir ="";
    public static String val_Keluarga ="";
    public static String val_TptLahir ="";
    public static String val_TipePendapatan ="";
    public static String val_Jenkel ="";



    public FragmentAgunan7Lain() {
    }

    public FragmentAgunan7Lain(Agunan mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    public FragmentAgunan7Lain(Agunan mdataLengkap,List<ListFotoAgunan> listpoto) {
        dataLengkap = mdataLengkap;
        listFoto=listpoto;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_7_lain, container, false);
      ButterKnife.bind(this, view);
         setData();

        return view;
    }

    private void setData(){
        et_pendapat_harga.setText(dataLengkap.getPendapatHarga());
        et_kondisi_lingkungan.setText(dataLengkap.getKondisiSekitar());
        et_pendapat_kondisi_jaminan.setText(dataLengkap.getPendapatKondisiJaminan());


        final int id_bangunan =dataLengkap.getIdPhotoTBbangunan();
        final int id_utara = dataLengkap.getIdPhotoTButara();
        final int id_selatan = dataLengkap.getIdPhotoTBselatan();
        final int id_barat = dataLengkap.getIdPhotoTBbarat();
        final int id_timur = dataLengkap.getIdPhotoTBtimur();

        RequestOptions options = new RequestOptions();
//                                .centerCrop()
//                                .placeholder(R.mipmap.ico_img_for_upload)
//                                .diskCacheStrategy(DiskCacheStrategy.ALL);


        Glide.with(getActivity())
                .asBitmap()
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+id_bangunan)
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        img_agunan.setImageBitmap(resource);

                    }
                });

        Glide.with(getActivity())
                .asBitmap()
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+id_utara)
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        img_utara.setImageBitmap(resource);

                    }
                });

        Glide.with(getActivity())
                .asBitmap()
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+id_selatan)
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        img_selatan.setImageBitmap(resource);

                    }
                });

        Glide.with(getActivity())
                .asBitmap()
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+id_timur)
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        img_timur.setImageBitmap(resource);

                    }
                });

        Glide.with(getActivity())
                .asBitmap()
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+id_barat)
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        img_barat.setImageBitmap(resource);

                    }
                });

        img_agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(id_bangunan));
                startActivity(intent);
            }
        });

        img_utara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(id_utara));
                startActivity(intent);
            }
        });
        img_selatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(id_selatan));
                startActivity(intent);
            }
        });
        img_timur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(id_timur));
                startActivity(intent);
            }
        });
        img_barat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(id_barat));
                startActivity(intent);
            }
        });

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

    }
}




