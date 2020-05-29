package com.application.bris.brisi_pemutus.page_konsumer_kmg.data_lengkap;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkapKonsumerKmg;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityFotoKelengkapanDokumen;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.KeyValue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.makeramen.roundedimageview.RoundedImageView;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

@SuppressLint("ValidFragment")
public class FragmentDataAlamatKonsumerKmg extends Fragment implements Step {

    //KTP
    @BindView(R.id.tf_alamatktp)
    TextFieldBoxes tf_alamatktp;
    @BindView(R.id.et_alamatktp)
    EditText et_alamatktp;
    @BindView(R.id.tf_rtktp)
    TextFieldBoxes tf_rtktp;
    @BindView(R.id.et_rtktp)
    EditText et_rtktp;
    @BindView(R.id.tf_rwktp)
    TextFieldBoxes tf_rwktp;
    @BindView(R.id.et_rwktp)
    EditText et_rwktp;
    @BindView(R.id.tf_provinsiktp)
    TextFieldBoxes tf_provinsiktp;
    @BindView(R.id.et_provinsiktp)
    EditText et_provinsiktp;
    @BindView(R.id.tf_kotaktp)
    TextFieldBoxes tf_kotaktp;
    @BindView(R.id.et_kotaktp)
    EditText et_kotaktp;
    @BindView(R.id.tf_kecamatanktp)
    TextFieldBoxes tf_kecamatanktp;
    @BindView(R.id.et_kecamatanktp)
    EditText et_kecamatanktp;
    @BindView(R.id.tf_kelurahanktp)
    TextFieldBoxes tf_kelurahanktp;
    @BindView(R.id.et_kelurahanktp)
    EditText et_kelurahanktp;
    @BindView(R.id.tf_kodeposktp)
    TextFieldBoxes tf_kodeposktp;
    @BindView(R.id.et_kodeposktp)
    EditText et_kodeposktp;
    @BindView(R.id.btn_ktp)
    Button btn_ktp;

    @BindView(R.id.img_fotorumah1)
    RoundedImageView img_fotorumah1;
    @BindView(R.id.btn_upload_fotorumah1)
    ImageView btn_upload_fotorumah1;
    @BindView(R.id.img_fotorumah2)
    RoundedImageView img_fotorumah2;

    //DOMISILI
    @BindView(R.id.sw_domisiliktpsama)
    Switch sw_domisiliktpsama;
    @BindView(R.id.tf_statusdom)
    TextFieldBoxes tf_statusdom;
    @BindView(R.id.et_statusdom)
    EditText et_statusdom;
    @BindView(R.id.tf_lamadom)
    TextFieldBoxes tf_lamadom;
    @BindView(R.id.et_lamadom)
    EditText et_lamadom;
    @BindView(R.id.ll_alamatdom)
    LinearLayout ll_alamatdom;
    @BindView(R.id.tf_alamatdom)
    TextFieldBoxes tf_alamatdom;
    @BindView(R.id.et_alamatdom)
    EditText et_alamatdom;
    @BindView(R.id.tf_rtdom)
    TextFieldBoxes tf_rtdom;
    @BindView(R.id.et_rtdom)
    EditText et_rtdom;
    @BindView(R.id.tf_rwdom)
    TextFieldBoxes tf_rwdom;
    @BindView(R.id.et_rwdom)
    EditText et_rwdom;
    @BindView(R.id.tf_provinsidom)
    TextFieldBoxes tf_provinsidom;
    @BindView(R.id.et_provinsidom)
    EditText et_provinsidom;
    @BindView(R.id.tf_kotadom)
    TextFieldBoxes tf_kotadom;
    @BindView(R.id.et_kotadom)
    EditText et_kotadom;
    @BindView(R.id.tf_kecamatandom)
    TextFieldBoxes tf_kecamatandom;
    @BindView(R.id.et_kecamatandom)
    EditText et_kecamatandom;
    @BindView(R.id.tf_kelurahandom)
    TextFieldBoxes tf_kelurahandom;
    @BindView(R.id.et_kelurahandom)
    EditText et_kelurahandom;
    @BindView(R.id.tf_kodeposdom)
    TextFieldBoxes tf_kodeposdom;
    @BindView(R.id.et_kodeposdom)
    EditText et_kodeposdom;
    @BindView(R.id.btn_dom)
    Button btn_dom;

    private Bitmap bitmapPhotoRumah1, bitmapPhotoRumah2, loadedPicture;

    private Realm realm;

    private DataLengkapKonsumerKmg dataLengkap;



    public FragmentDataAlamatKonsumerKmg(DataLengkapKonsumerKmg mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_data_alamat_konsumer_kmg, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        setDynamicIcon();
        setData();
        setDynamicIconDropDown();
        disableEditTexts();


        return view;
    }

    public void setData(){
        et_alamatktp.setText(dataLengkap.getAlamatId());
        et_rtktp.setText(dataLengkap.getRtId());
        et_rwktp.setText(dataLengkap.getRwId());
        et_provinsiktp.setText(dataLengkap.getProvId());
        et_kotaktp.setText(dataLengkap.getKotaId());
        et_kecamatanktp.setText(dataLengkap.getKecId());
        et_kelurahanktp.setText(dataLengkap.getKelId());
        et_kodeposktp.setText(dataLengkap.getKodePosId());

        et_statusdom.setText(KeyValue.getKeyStatusTempatDomisili(dataLengkap.getStatusTptTinggal()));
        et_lamadom.setText(String.valueOf(dataLengkap.getLamaMenetap()));
        et_alamatdom.setText(dataLengkap.getAlamatDom());
        et_rtdom.setText(dataLengkap.getRtDom());
        et_rwdom.setText(dataLengkap.getRwDom());
        et_provinsidom.setText(dataLengkap.getProvDom());
        et_kotadom.setText(dataLengkap.getKotaDom());
        et_kecamatandom.setText(dataLengkap.getKecDom());
        et_kelurahandom.setText(dataLengkap.getKelDom());
        et_kodeposdom.setText(dataLengkap.getKodePosDom());
        bitmapPhotoRumah1 = setLoadImage(img_fotorumah1, dataLengkap.getFID_PHOTO_RUMAH1());
        bitmapPhotoRumah2 = setLoadImage(img_fotorumah2, dataLengkap.getFID_PHOTO_RUMAH2());

        img_fotorumah1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(dataLengkap.getFID_PHOTO_RUMAH1()));
                startActivity(intent);
            }
        });

        img_fotorumah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",(dataLengkap.getFID_PHOTO_RUMAH2()));
                startActivity(intent);
            }
        });
    }

    private void disableEditTexts(){
        //STATUS DOMISILI


        et_alamatktp.setFocusable(false);
        et_rtktp.setFocusable(false);
        et_rwktp.setFocusable(false);
        et_lamadom.setFocusable(false);
        et_alamatdom.setFocusable(false);
        et_rtdom.setFocusable(false);
        et_rwdom.setFocusable(false);


        et_statusdom.setFocusable(false);
        et_provinsiktp.setFocusable(false);
        et_kotaktp.setFocusable(false);
        et_kecamatanktp.setFocusable(false);
        et_kelurahanktp.setFocusable(false);
        et_kodeposktp.setFocusable(false);
        et_provinsidom.setFocusable(false);
        et_kotadom.setFocusable(false);
        et_kecamatandom.setFocusable(false);
        et_kelurahandom.setFocusable(false);
        et_kodeposdom.setFocusable(false);
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


    private void setDynamicIcon(){
        AppUtil.dynamicIconLogoChange(getContext(),et_alamatktp,tf_alamatktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_rtktp,tf_rtktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_rwktp,tf_rwktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_provinsiktp,tf_provinsiktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_kotaktp,tf_kotaktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_kecamatanktp,tf_kecamatanktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_kelurahanktp,tf_kelurahanktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_kodeposktp,tf_kodeposktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_lamadom,tf_lamadom);
        AppUtil.dynamicIconLogoChange(getContext(),et_alamatdom,tf_alamatdom);
        AppUtil.dynamicIconLogoChange(getContext(),et_rtdom,tf_rtdom);
        AppUtil.dynamicIconLogoChange(getContext(),et_rwdom,tf_rwdom);
        AppUtil.dynamicIconLogoChange(getContext(),et_provinsidom,tf_provinsidom);
        AppUtil.dynamicIconLogoChange(getContext(),et_kotadom,tf_kotadom);
        AppUtil.dynamicIconLogoChange(getContext(),et_kecamatandom,tf_kecamatandom);

        AppUtil.dynamicIconLogoChange(getContext(),et_kelurahandom,tf_kelurahandom);
        AppUtil.dynamicIconLogoChange(getContext(),et_kodeposdom,tf_kodeposdom);



    }

    private void setDynamicIconDropDown(){
        AppUtil.dynamicIconLogoChangeDropdown(getContext(),tf_statusdom,et_statusdom);


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
