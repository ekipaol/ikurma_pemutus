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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * A simple {@link Fragment} subclass.
 */

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunanKendaraan2 extends Fragment implements Step{
    //VIEW BINDING
    @BindView(R.id.tf_hub_pemilik_dengan_nasabah)
    TextFieldBoxes tf_hub_pemilik_dengan_nasabah;
    @BindView(R.id.et_hub_pemilik_dengan_nasabah)
    ExtendedEditText et_hub_pemilik_dengan_nasabah;
    @BindView(R.id.tf_no_faktur)
    TextFieldBoxes tf_no_faktur;
    @BindView(R.id.et_no_faktur)
    ExtendedEditText et_no_faktur;
    @BindView(R.id.tf_no_mesin)
    TextFieldBoxes tf_no_mesin;
    @BindView(R.id.et_no_mesin)
    ExtendedEditText et_no_mesin;
    @BindView(R.id.tf_bukti_gesek_mesin)
    TextFieldBoxes tf_bukti_gesek_mesin;
    @BindView(R.id.et_bukti_gesek_mesin)
    ExtendedEditText et_bukti_gesek_mesin;
    @BindView(R.id.tf_no_rangka)
    TextFieldBoxes tf_no_rangka;
    @BindView(R.id.et_no_rangka)
    ExtendedEditText et_no_rangka;
    @BindView(R.id.tf_bukti_gesek_rangka)
    TextFieldBoxes tf_bukti_gesek_rangka;
    @BindView(R.id.et_bukti_gesek_rangka)
    ExtendedEditText et_bukti_gesek_rangka;
    @BindView(R.id.tf_no_polisi)
    TextFieldBoxes tf_no_polisi;
    @BindView(R.id.et_no_polisi)
    ExtendedEditText et_no_polisi;
    @BindView(R.id.tf_plat_kuning)
    TextFieldBoxes tf_plat_kuning;
    @BindView(R.id.et_plat_kuning)
    ExtendedEditText et_plat_kuning;
    @BindView(R.id.tf_no_bpkb)
    TextFieldBoxes tf_no_bpkb;
    @BindView(R.id.et_no_bpkb)
    ExtendedEditText et_no_bpkb;
    @BindView(R.id.tf_no_stnk)
    TextFieldBoxes tf_no_stnk;
    @BindView(R.id.et_no_stnk)
    ExtendedEditText et_no_stnk;
    @BindView(R.id.tf_warna)
    TextFieldBoxes tf_warna;
    @BindView(R.id.et_warna)
    ExtendedEditText et_warna;
    @BindView(R.id.tf_thn_pembuatan)
    TextFieldBoxes tf_thn_pembuatan;
    @BindView(R.id.et_thn_pembuatan)
    ExtendedEditText et_thn_pembuatan;
    @BindView(R.id.img_bpkb)
    BitmapImageViewRounded img_bpkb;
    @BindView(R.id.btn_upload_bpkb)
    ImageView btn_upload_bpkb;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    //END OF VIEW BINDING
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String idAgunan;
    private AgunanKendaraan dataAgunan;
    public static String val_HubPemilikDenganNasabah ="";
    public static String val_NoFaktur ="";
    public static String val_NoMesin ="";
    public static String val_BuktiGesekMesin ="";
    public static String val_NoRangka ="";
    public static String val_BuktiGesekRangka ="";
    public static String val_NoPolisi ="";
    public static String val_PlatKuning ="";
    public static String val_NoBpkb ="";
    public static String val_NoStnk ="";
    public static String val_Warna ="";
    public static String val_ThnPembuatan ="";
    public static String val_ImgBpkb="";
    private final int PICK_IMAGE_BPKB = 1;
    private final int TAKE_PICTURE_BPKB = 11;
    private Uri uriPhotoBpkb;
    private Bitmap bitmapPhotoBpkb;
    private String idBtnBpkb = "";
    private String idGlobal = "";
    private String sudahUploadBpkb = "belum";
    private int idFotoBpkb = 0;
    public FragmentAgunanKendaraan2() {
    }
    public FragmentAgunanKendaraan2(String midAgunan, AgunanKendaraan magunanData) {
        dataAgunan = magunanData;
        idAgunan = midAgunan;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_kendaraan_2, container, false);
        ButterKnife.bind(this,view);
//        apiClientAdapter = new ApiClientAdapter(getContext());
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());
        onSelectDialog();
        if(!String.valueOf(idAgunan).equalsIgnoreCase("0")) {
            Log.d("Cek idAgunan", String.valueOf(idAgunan));
            setData();
            disableTextfield();
        }

        return view;
    }
    private void onSelectDialog(){
        et_hub_pemilik_dengan_nasabah.setFocusable(false);
        et_hub_pemilik_dengan_nasabah.setInputType(InputType.TYPE_NULL);

        et_bukti_gesek_mesin.setFocusable(false);
        et_bukti_gesek_mesin.setInputType(InputType.TYPE_NULL);

        et_bukti_gesek_rangka.setFocusable(false);
        et_bukti_gesek_rangka.setInputType(InputType.TYPE_NULL);

        et_plat_kuning.setFocusable(false);
        et_plat_kuning.setInputType(InputType.TYPE_NULL);

    }

    private void setData(){
        et_hub_pemilik_dengan_nasabah.setText(dataAgunan.getHubungan());
        et_no_faktur.setText(dataAgunan.getNoFaktur());
        et_no_mesin.setText(dataAgunan.getNoMesin());
        et_bukti_gesek_mesin.setText(dataAgunan.getBuktiGesekMesin());
        et_no_rangka.setText(dataAgunan.getNoRangka());
        et_bukti_gesek_rangka.setText(dataAgunan.getBuktiGesekRangka());
        et_no_polisi.setText(dataAgunan.getNoPolisi());
        et_plat_kuning.setText(dataAgunan.getJenisPlat());
        et_no_bpkb.setText(dataAgunan.getNoBKPB());
        et_no_stnk.setText(dataAgunan.getNoSTNK());
        et_warna.setText(dataAgunan.getWarna());
        et_thn_pembuatan.setText(dataAgunan.getTahunPembuatan());
        final int id_bpn = dataAgunan.getIdPhotoKDBPKB();
//        String ImgBpn = UriApi.Baseurl.URL + UriApi.foto.urlFoto + id_bpn;
        val_ImgBpkb = String.valueOf(id_bpn);
//        Glide
//                .with(getContext())
//                .asBitmap()
//                .load(ImgBpn)
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        img_bpkb.setImageBitmap(resource);
//                        bitmapPhotoBpkb = resource;
//                        sudahUploadBpkb = "sudah";
//                    }
//                });

        AppUtil.setImageGlideInt(getContext(),id_bpn,img_bpkb);

        img_bpkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(id_bpn));
                startActivity(intent);
            }
        });

    }

    private void disableTextfield(){
        et_hub_pemilik_dengan_nasabah.setInputType(InputType.TYPE_NULL);
        et_hub_pemilik_dengan_nasabah.setFocusable(false);

        et_no_faktur.setInputType(InputType.TYPE_NULL);
        et_no_faktur.setFocusable(false);

        et_no_mesin.setInputType(InputType.TYPE_NULL);
        et_no_mesin.setFocusable(false);

        et_bukti_gesek_mesin.setInputType(InputType.TYPE_NULL);
        et_bukti_gesek_mesin.setFocusable(false);

        et_no_rangka.setInputType(InputType.TYPE_NULL);
        et_no_rangka.setFocusable(false);

        et_bukti_gesek_rangka.setInputType(InputType.TYPE_NULL);
        et_bukti_gesek_rangka.setFocusable(false);

        et_no_polisi.setInputType(InputType.TYPE_NULL);
        et_no_polisi.setFocusable(false);

        et_plat_kuning.setInputType(InputType.TYPE_NULL);
        et_plat_kuning.setFocusable(false);

        et_no_bpkb.setInputType(InputType.TYPE_NULL);
        et_no_bpkb.setFocusable(false);

        et_no_stnk.setInputType(InputType.TYPE_NULL);
        et_no_stnk.setFocusable(false);

        et_warna.setInputType(InputType.TYPE_NULL);
        et_warna.setFocusable(false);

        et_thn_pembuatan.setInputType(InputType.TYPE_NULL);
        et_thn_pembuatan.setFocusable(false);





    }


    @Nullable
    @Override
    public VerificationError verifyStep() {
        return  null;
    }
    @Override
    public void onSelected() {
    }
    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notifwarning(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

}
