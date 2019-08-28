package com.application.bris.brisi_pemutus.page_putusan.agunan;



import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 */
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;



import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.listeners.KeyValueListener;
import com.application.bris.brisi_pemutus.model.agunan.Agunan;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkap;
import com.application.bris.brisi_pemutus.model.keyvalue.keyvalue;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityFotoKelengkapanDokumen;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.BitmapImageViewRounded;
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
public class FragmentAgunan2Surat extends Fragment implements Step, KeyValueListener {

    @BindView(R.id.tf_jenis_surat)
    TextFieldBoxes tf_jenis_surat;
    @BindView(R.id.et_jenis_surat)
    ExtendedEditText et_jenis_surat;

    @BindView(R.id.tf_no_sertifikat)
    TextFieldBoxes tf_no_sertifikat;
    @BindView(R.id.et_no_sertifikat)
    ExtendedEditText et_no_sertifikat;

    @BindView(R.id.tf_no_gbr)
    TextFieldBoxes tf_no_gbr;
    @BindView(R.id.et_no_gbr)
    ExtendedEditText et_no_gbr;

    @BindView(R.id.tf_atas_nama_sertifikat)
    TextFieldBoxes tf_atas_nama_sertifikat;
    @BindView(R.id.et_atas_nama_sertifikat)
    ExtendedEditText et_atas_nama_sertifikat;

    @BindView(R.id.tf_tanggal_terbit_sertifikat)
    TextFieldBoxes tf_tanggal_terbit_sertifikat;
    @BindView(R.id.et_tanggal_terbit_sertifikat)
    ExtendedEditText et_tanggal_terbit_sertifikat;

    @BindView(R.id.tf_luas_tanah_sertifikat)
    TextFieldBoxes tf_luas_tanah_sertifikat;
    @BindView(R.id.et_luas_tanah_sertifikat)
    ExtendedEditText et_luas_tanah_sertifikat;


    @BindView(R.id.tf_hub_nasabah_dengan_pemegang_hak)
    TextFieldBoxes tf_hub_nasabah_dengan_pemegang_hak;
    @BindView(R.id.et_hub_nasabah_dengan_pemegang_hak)
    ExtendedEditText et_hub_nasabah_dengan_pemegang_hak;

    @BindView(R.id.tf_masa_hak_atas_tanah)
    TextFieldBoxes tf_masa_hak_atas_tanah;
    @BindView(R.id.et_masa_hak_atas_tanah)
    ExtendedEditText et_masa_hak_atas_tanah;

    @BindView(R.id.img_bpn)
    BitmapImageViewRounded img_bpn;


    private Calendar calLahir;
    private Calendar calLahirPasangan;
    private Calendar calExpiredNik;

    private DatePickerDialog dpTanggalLahir;
    private DatePickerDialog dpTanggalLahirPasangan;
    private DatePickerDialog dpExpiredNik;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    public static SimpleDateFormat dateServer = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private Agunan dataLengkap;

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



    public FragmentAgunan2Surat() {
    }

    public FragmentAgunan2Surat(Agunan mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_2_identifikasi_surat, container, false);
        ButterKnife.bind(this,view);
//et_jenis_surat.setText(dataLengkap.getJenisSuratTanah());
        setData();

        return view;
    }

    private void setData(){

        disableTextFocus(et_jenis_surat);
        et_jenis_surat.setText(dataLengkap.getJenisSuratTanah());

        disableTextFocus(et_no_sertifikat);
        et_no_sertifikat.setText(dataLengkap.getNoSertifikat());

        disableTextFocus(et_no_gbr);
        et_no_gbr.setText(dataLengkap.getNoGS());


        disableTextFocus(et_atas_nama_sertifikat);
        et_atas_nama_sertifikat.setText(dataLengkap.getAtasNamaSertifikat());

        disableTextFocus(et_tanggal_terbit_sertifikat);
        et_tanggal_terbit_sertifikat.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTanggalSertifikat(),"ddMMyyyy","dd-MM-yyyy"));

        disableTextFocus(et_luas_tanah_sertifikat);
        et_luas_tanah_sertifikat.setText(dataLengkap.getLuasTanahSertifikat());

        disableTextFocus(et_hub_nasabah_dengan_pemegang_hak);
        et_hub_nasabah_dengan_pemegang_hak.setText(dataLengkap.getHubNasabahDgnPemegangHak());

        disableTextFocus(et_masa_hak_atas_tanah);
        et_masa_hak_atas_tanah.setText(dataLengkap.getMasaHakAtasTanah());

        RequestOptions options = new RequestOptions();
//                                .centerCrop()
//                                .placeholder(R.mipmap.ico_img_for_upload)
//                                .diskCacheStrategy(DiskCacheStrategy.ALL);


        Glide.with(getActivity())
                .asBitmap()
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+dataLengkap.getIdPhotoTBbpn())
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        img_bpn.setImageBitmap(resource);

                    }
                });

        img_bpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(dataLengkap.getIdPhotoTBbpn()));
                startActivity(intent);
            }
        });









    }


    private void disableTextFocus(ExtendedEditText et){
        et.setFocusable(false);
        et.setInputType(InputType.TYPE_NULL);
        et.setKeyListener(null);
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

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("jenis surat tanah")){
            et_jenis_surat.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("Hub nasabah dengan pemegang hak")){
            et_hub_nasabah_dengan_pemegang_hak.setText(data.getName());
        }
    }
}

