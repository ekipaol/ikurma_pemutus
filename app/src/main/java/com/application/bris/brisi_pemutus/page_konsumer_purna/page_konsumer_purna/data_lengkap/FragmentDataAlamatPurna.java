package com.application.bris.brisi_pemutus.page_konsumer_purna.page_konsumer_purna.data_lengkap;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkap;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkapKonsumerKmg;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityFotoKelengkapanDokumen;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.KeyValue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.makeramen.roundedimageview.RoundedDrawable;
import com.makeramen.roundedimageview.RoundedImageView;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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

@SuppressLint("ValidFragment")
public class FragmentDataAlamatPurna extends Fragment implements Step{

    //KTP
    @BindView(R.id.ll_dataalamat)
    LinearLayout ll_dataalamat;
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

    @BindView(R.id.img_fotorumah1)
    RoundedImageView img_fotorumah1;
    @BindView(R.id.btn_upload_fotorumah1)
    ImageView btn_upload_fotorumah1;
    @BindView(R.id.img_fotorumah2)
    RoundedImageView img_fotorumah2;
    @BindView(R.id.btn_upload_fotorumah2)
    ImageView btn_upload_fotorumah2;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.progress_rumah1)
    ProgressBar progress_rumah1;
    @BindView(R.id.progress_rumah2)
    ProgressBar progress_rumah2;


    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private DataLengkapKonsumerKmg dataLengkap;
    private String approved;


    private String isSelectPhoto = "";

    private final int PICK_PICTURE_RUMAH1 = 1;
    private final int PICK_PICTURE_RUMAH2 = 2;
    private final int TAKE_PICTURE_RUMAH1 = 11;
    private final int TAKE_PICTURE_RUMAH2 = 22;

    private Uri uriPhotoRumah1, uriPhotoRumah2;
    private Bitmap bitmapPhotoRumah1, bitmapPhotoRumah2, loadedPicture;

//    private String sudahUploadRumah1 = "belum";
//    private String sudahUploadRumah2 = "belum";

//    private int idFotoRumah1 = 0;
//    private int idFotoRumah2 = 0;

    private int val_domAsId = 0;
    private String btnAddressString = "";

//    public FragmentDataAlamatPurna(DataLengkap mdataLengkap) {
//        dataLengkap = mdataLengkap;
//    }

    public FragmentDataAlamatPurna(DataLengkapKonsumerKmg mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_dataalamat_purna, container, false);
        ButterKnife.bind(this, view);
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());

        setData();

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

            AppUtil.disableEditTexts(ll_dataalamat);
            btn_ktp.setVisibility(View.GONE);
            btn_dom.setVisibility(View.GONE);
            sw_domisiliktpsama.setVisibility(View.GONE);
            btn_upload_fotorumah1.setVisibility(View.GONE);
            btn_upload_fotorumah2.setVisibility(View.GONE);

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
        {
            return null;
    }

}

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {

    }
}
