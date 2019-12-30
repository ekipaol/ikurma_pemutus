package com.application.bris.brisi_pemutus.page_putusan.lkn;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.listeners.KeyValueListener;
import com.application.bris.brisi_pemutus.model.lkn.DataLkn;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityFotoKelengkapanDokumen;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.KeyValue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentLembarKunjungan extends Fragment implements Step{

    @BindView(R.id.tf_tanggalkunjungan)
    TextFieldBoxes tf_tanggalkunjungan;
    @BindView(R.id.et_tanggalkunjungan)
    EditText et_tanggalkunjungan;
    @BindView(R.id.tf_statuspermohonan)
    TextFieldBoxes tf_statuspermohonan;
    @BindView(R.id.et_statuspermohonan)
    EditText et_statuspermohonan;
    @BindView(R.id.tf_namaorangyangditemui)
    TextFieldBoxes tf_namaorangyangditemui;
    @BindView(R.id.et_namaorangyangditemui)
    EditText et_namaorangyangditemui;
    @BindView(R.id.tf_hubungan)
    TextFieldBoxes tf_hubungan;
    @BindView(R.id.et_hubungan)
    EditText et_hubungan;


    @BindView(R.id.tf_bidangusaha)
    TextFieldBoxes tf_bidangusaha;
    @BindView(R.id.et_bidangusaha)
    EditText et_bidangusaha;
    @BindView(R.id.tf_namausaha)
    TextFieldBoxes tf_namausaha;
    @BindView(R.id.et_namausaha)
    EditText et_namausaha;
    @BindView(R.id.tf_lamausaha)
    TextFieldBoxes tf_lamausaha;
    @BindView(R.id.et_lamausaha)
    ExtendedEditText et_lamausaha;
    @BindView(R.id.tf_nomortelponusaha)
    TextFieldBoxes tf_nomortelponusaha;
    @BindView(R.id.et_nomortelponusaha)
    EditText et_nomortelponusaha;

    @BindView(R.id.tf_alamatusaha)
    TextFieldBoxes tf_alamatusaha;
    @BindView(R.id.et_alamatusaha)
    EditText et_alamatusaha;
    @BindView(R.id.tf_lokasiusaha)
    TextFieldBoxes tf_lokasiusaha;
    @BindView(R.id.et_lokasiusaha)
    EditText et_lokasiusaha;
    @BindView(R.id.tf_statustempatusaha)
    TextFieldBoxes tf_statustempatusaha;
    @BindView(R.id.et_statustempatusaha)
    EditText et_statustempatusaha;
    @BindView(R.id.tf_jenistempatusaha)
    TextFieldBoxes tf_jenistempatusaha;
    @BindView(R.id.et_jenistempatusaha)
    EditText et_jenistempatusaha;
    @BindView(R.id.tf_aspekpemasaran)
    TextFieldBoxes tf_aspekpemasaran;
    @BindView(R.id.et_aspekpemasaran)
    EditText et_aspekpemasaran;
    @BindView(R.id.tf_jenisusaha)
    TextFieldBoxes tf_jenisusaha;
    @BindView(R.id.et_jenisusaha)
    EditText et_jenisusaha;
    @BindView(R.id.tf_jaraklokasiusahakeums)
    TextFieldBoxes tf_jaraklokasiusahakeums;
    @BindView(R.id.et_jaraklokasiusahakeums)
    EditText et_jaraklokasiusahakeums;

    @BindView(R.id.iv_foto_usaha_1)
    ImageView iv_foto_usaha_1;
    @BindView(R.id.iv_foto_usaha_2)
    ImageView iv_foto_usaha_2;
    @BindView(R.id.iv_foto_usaha_3)
    ImageView iv_foto_usaha_3;

    String labelLamaUsaha="";

    private Calendar cal;

    private DatePickerDialog dpTanggal;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    public static SimpleDateFormat dateServer = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private DataLkn data;

    public static String val_tanggalKunjungan ="";
    public static String val_statusPermohonan ="";
    public static String val_namaOrangyangditemui ="";
    public static String val_hubungan ="";
    public static String val_lokasiUsaha ="";
    public static String val_statusTempatUsaha = "";
    public static String val_jenisTempatUsaha ="";
    public static String val_aspekPemasaran ="";
    public static String val_jenisUsaha ="";
    public static String val_jarakLokasiUsahakeUms ="";

    public FragmentLembarKunjungan() {
    }

    public FragmentLembarKunjungan(DataLkn mdata) {
        data = mdata;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_lembarkunjungan, container, false);
        ButterKnife.bind(this, view);
        disableTextfield();
        setData();

        return view;
    }

    private void setData(){
        et_tanggalkunjungan.setText(AppUtil.parseTanggalGeneral(data.gettANGGALPENILAIAN(), "ddMMyyyy", "dd-MM-yyyy"));
        et_statuspermohonan.setText(data.getsTATUSPERMOHONAN());
        et_namaorangyangditemui.setText(data.getnAMAORANGDITEMUI());
        et_hubungan.setText(data.gethUBUNGAN());
        et_bidangusaha.setText(KeyValue.getKeyUsahaorJob(data.getbIDANGUSAHA()));
        et_namausaha.setText(data.nAMAUSAHA);
        et_lamausaha.setText(String.valueOf(parseLamaUsaha()));
        et_lamausaha.setSuffix(labelLamaUsaha);
        et_nomortelponusaha.setText(data.gettELPKANTOR());
        et_alamatusaha.setText(data.getaLAMATTEMPATKERJA1());
        et_lokasiusaha.setText(data.getlOKASIUSAHA());
        et_statustempatusaha.setText(data.getsTATUSTEMPATUSAHA());
        et_jenistempatusaha.setText(data.getjENISTEMPATUSAHA());
        et_aspekpemasaran.setText(data.getaSPEKPEMASARAN());
        et_jenisusaha.setText(data.getjENISUSAHA());
        et_jaraklokasiusahakeums.setText(String.valueOf(data.getjARAKLOKASI()));


        //glide for foto usaha

        RequestOptions options = new RequestOptions();
//                                .centerCrop()
//                                .placeholder(R.mipmap.ico_img_for_upload)
//                                .diskCacheStrategy(DiskCacheStrategy.ALL);


        Glide.with(getActivity())
                .asBitmap()
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+data.getFID_PHOTO_DEPAN())
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv_foto_usaha_1.setImageBitmap(resource);

                    }
                });

        Glide.with(getActivity())
                .asBitmap()
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+data.getFID_PHOTO_DALAM())
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv_foto_usaha_2.setImageBitmap(resource);

                    }
                });

        Glide.with(getActivity())
                .asBitmap()
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+data.getFID_PHOTO_LINGKUNGAN())
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv_foto_usaha_3.setImageBitmap(resource);

                    }
                });

        //end of glide

        //on click for photos


        iv_foto_usaha_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(Integer.parseInt(data.getFID_PHOTO_DEPAN())));
                startActivity(intent);
            }
        });


        iv_foto_usaha_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(Integer.parseInt(data.getFID_PHOTO_DALAM())));
                startActivity(intent);
            }
        });

        iv_foto_usaha_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(Integer.parseInt(data.getFID_PHOTO_LINGKUNGAN())));
                startActivity(intent);
            }
        });
    }

    private void disableTextfield(){
        et_tanggalkunjungan.setInputType(InputType.TYPE_NULL);
        et_tanggalkunjungan.setFocusable(false);
        et_statuspermohonan.setInputType(InputType.TYPE_NULL);
        et_statuspermohonan.setFocusable(false);
        et_namaorangyangditemui.setInputType(InputType.TYPE_NULL);
        et_namaorangyangditemui.setFocusable(false);
        et_hubungan.setInputType(InputType.TYPE_NULL);
        et_hubungan.setFocusable(false);
        et_bidangusaha.setInputType(InputType.TYPE_NULL);
        et_bidangusaha.setFocusable(false);
        et_namausaha.setInputType(InputType.TYPE_NULL);
        et_namausaha.setFocusable(false);
        et_nomortelponusaha.setInputType(InputType.TYPE_NULL);
        et_nomortelponusaha.setFocusable(false);
        et_alamatusaha.setInputType(InputType.TYPE_NULL);
        et_alamatusaha.setFocusable(false);
        et_lokasiusaha.setInputType(InputType.TYPE_NULL);
        et_lokasiusaha.setFocusable(false);
        et_statustempatusaha.setInputType(InputType.TYPE_NULL);
        et_statustempatusaha.setFocusable(false);
        et_jenistempatusaha.setInputType(InputType.TYPE_NULL);
        et_jenistempatusaha.setFocusable(false);
        et_aspekpemasaran.setInputType(InputType.TYPE_NULL);
        et_aspekpemasaran.setFocusable(false);
        et_jenisusaha.setInputType(InputType.TYPE_NULL);
        et_jenisusaha.setFocusable(false);
        et_jaraklokasiusahakeums.setInputType(InputType.TYPE_NULL);
        et_jaraklokasiusahakeums.setFocusable(false);
    }












    public long parseLamaUsaha(){
        try {
            String val = (data.getlAMABEKERJA());
            String d1 = val.substring(0, 2);
            String d2 = val.substring(2,4);

            if (!d2.equalsIgnoreCase("00")){

                labelLamaUsaha="Tahun";
                return AppUtil.parseLongWithDefault(d2, 0);
            }
            else if (d2.equalsIgnoreCase("00") && !d1.equalsIgnoreCase("00")){
                labelLamaUsaha="Bulan";
                return AppUtil.parseLongWithDefault(d1, 0);
            }
            else {
                return 0;
            }
        }
        catch (Exception e){
            return 0;
        }

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
