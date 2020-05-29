package com.application.bris.brisi_pemutus.page_konsumer_kmg.data_lengkap;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.InputType;
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
import com.application.bris.brisi_pemutus.util.NumberTextWatcherCanNolForThousand;
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
public class FragmentDataPekerjaanKonsumerKmg extends Fragment implements Step{

    @BindView(R.id.sv_data_pekerjaan_konsumer_kmg)
    ViewGroup sv_data_pekerjaan_konsumer_kmg;
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

    @BindView(R.id.tf_sisa_plafond_perusahaan)
    TextFieldBoxes tf_sisa_plafond_perusahaan;
    @BindView(R.id.et_sisa_plafond_perusahaan)
    EditText et_sisa_plafond_perusahaan;

    @BindView(R.id.tf_nomor_sk_pegawai_tetap)
    TextFieldBoxes tf_nomor_sk_pegawai_tetap;
    @BindView(R.id.et_nomor_sk_pegawai_tetap)
    EditText et_nomor_sk_pegawai_tetap;

    @BindView(R.id.tf_nomor_sk_pangkat_terakhir)
    TextFieldBoxes tf_nomor_sk_pangkat_terakhir;
    @BindView(R.id.et_nomor_sk_pangkat_terakhir)
    EditText et_nomor_sk_pangkat_terakhir;

    @BindView(R.id.tf_nomor_induk_pegawai)
    TextFieldBoxes tf_nomor_induk_pegawai;
    @BindView(R.id.et_nomor_induk_pegawai)
    EditText et_nomor_induk_pegawai;

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

    @BindView(R.id.tf_pembayaran_gaji_melalui)
    TextFieldBoxes tf_pembayaran_gaji_melalui;
    @BindView(R.id.et_pembayaran_gaji_melalui)
    EditText et_pembayaran_gaji_melalui;

    @BindView(R.id.tf_tanggal_verifikasi_pejabat)
    TextFieldBoxes tf_tanggal_verifikasi_pejabat;
    @BindView(R.id.et_tanggal_verifikasi_pejabat_berwenang)
    EditText et_tanggal_verifikasi_pejabat_berwenang;

    @BindView(R.id.tf_nama_pejabat_berwenang)
    TextFieldBoxes tf_nama_pejabat_berwenang;
    @BindView(R.id.et_nama_pejabat_berwenang)
    EditText et_nama_pejabat_berwenang;

    @BindView(R.id.tf_no_surat_rekomendasi)
    TextFieldBoxes tf_no_surat_rekomendasi;
    @BindView(R.id.et_no_surat_rekomendasi)
    EditText et_no_surat_rekomendasi;

    @BindView(R.id.img_fotokantor1)
    RoundedImageView img_fotokantor1;
    @BindView(R.id.btn_upload_fotokantor1)
    ImageView btn_upload_fotokantor1;
    @BindView(R.id.img_fotokantor2)
    RoundedImageView img_fotokantor2;
    @BindView(R.id.btn_upload_fotokantor2)
    ImageView btn_upload_fotokantor2;




    @BindView(R.id.btn_perusahaan)
    Button btn_perusahaan;

    private Bitmap bitmapPhotoKantor1, bitmapPhotoKantor2, loadedPicture;

    private Realm realm;

    private DataLengkapKonsumerKmg dataLengkap;


    @SuppressLint("ValidFragment")
    public FragmentDataPekerjaanKonsumerKmg(DataLengkapKonsumerKmg mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_data_perusahaan_konsumer_kmg, container, false);
        ButterKnife.bind(this, view);


        setDynamicIcon();
        setData();
        setDynamicIconDropDown();
        onSelectDialog();
        AppUtil.disableEditTexts(sv_data_pekerjaan_konsumer_kmg);

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
        et_sisa_plafond_perusahaan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_sisa_plafond_perusahaan));
        et_sisa_plafond_perusahaan.setText(dataLengkap.getSisaPlafondPerusahaan());
        et_nomor_sk_pegawai_tetap.setText(dataLengkap.getNoSKPertama());
        et_nomor_sk_pangkat_terakhir.setText(dataLengkap.getNoSKterakhir());
        et_nomor_induk_pegawai.setText(dataLengkap.getNIP());
        et_deskripsi_pekerjaan.setText(dataLengkap.getDeskripsiPekerjaan());
        et_status_kepegawaian.setText(KeyValue.getKeyStatusKepegawaian(dataLengkap.getStatusKepegawaian()));
        et_jenis_pekerjaan.setText(dataLengkap.getJenisPekerjaan());
        et_usia_mpp.setText(dataLengkap.getUsiaMpp());
        et_posisi_jabatan.setText(KeyValue.getKeyPosisiJabatan(dataLengkap.getJabatan()));
        et_pembayaran_gaji_melalui.setText(KeyValue.getKeyPembayaranGaji(dataLengkap.getPembayaranGaji()));
        et_tanggal_verifikasi_pejabat_berwenang.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglVerifikasi(), "ddMMyyyy", "dd-MM-yyyy"));
        et_nama_pejabat_berwenang.setText(dataLengkap.getNamaPejabat());
        et_no_surat_rekomendasi.setText(dataLengkap.getNoRekomendasi());

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

    private void onSelectDialog() {
        //BIDANG USAHA
        et_bidang_pekerjaan.setFocusable(false);


//        tf_bidang_pekerjaan.setOnClickListener(this);
//        tf_bidang_pekerjaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openKeyValueDialog(tf_bidang_pekerjaan.getLabelText().toString().trim());
//            }
//        });

        //EXPIRED NIK
        et_tanggalmulaiperusahaan.setFocusable(false);


        et_tanggal_verifikasi_pejabat_berwenang.setInputType(InputType.TYPE_NULL);




//        et_namaperusahaan.setInputType(InputType.TYPE_NULL);
        et_namaperusahaan.setFocusable(false);

        et_status_kepegawaian.setFocusable(false);


        et_jenis_pekerjaan.setFocusable(false);

//        et_deskripsi_pekerjaan.setInputType(InputType.TYPE_NULL);
        et_deskripsi_pekerjaan.setFocusable(false);

        et_namaperusahaan.setFocusable(false);

//        et_posisi_jabatan.setInputType(InputType.TYPE_NULL);
        et_posisi_jabatan.setFocusable(false);

        et_pembayaran_gaji_melalui.setFocusable(false);



        et_tanggal_verifikasi_pejabat_berwenang.setFocusable(false);


        et_sisa_plafond_perusahaan.setFocusable(false);


        et_provinsiperusahaan.setInputType(InputType.TYPE_NULL);
        et_provinsiperusahaan.setFocusable(false);
        et_kotaperusahaan.setInputType(InputType.TYPE_NULL);
        et_kotaperusahaan.setFocusable(false);
        et_kecamatanperusahaan.setInputType(InputType.TYPE_NULL);
        et_kecamatanperusahaan.setFocusable(false);
        et_kelurahanperusahaan.setInputType(InputType.TYPE_NULL);
        et_kelurahanperusahaan.setFocusable(false);
        et_kodeposperusahaan.setInputType(InputType.TYPE_NULL);
        et_kodeposperusahaan.setFocusable(false);
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













    private void setDynamicIcon(){
        AppUtil.dynamicIconLogoChange(getContext(),et_namaperusahaan,tf_namaperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_nomortelponperusahaan,tf_nomortelponperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_alamatperusahaan,tf_alamatperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_rtperusahaan,tf_rtperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_rwperusahaan,tf_rwperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_provinsiperusahaan,tf_provinsiperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_kotaperusahaan,tf_kotaperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_kecamatanperusahaan,tf_kecamatanperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_kelurahanperusahaan,tf_kelurahanperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_kodeposperusahaan,tf_kodeposperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_sisa_plafond_perusahaan,tf_sisa_plafond_perusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_nomor_sk_pegawai_tetap,tf_nomor_sk_pegawai_tetap);
        AppUtil.dynamicIconLogoChange(getContext(),et_nomor_sk_pangkat_terakhir,tf_nomor_sk_pangkat_terakhir);
        AppUtil.dynamicIconLogoChange(getContext(),et_nomor_induk_pegawai,tf_nomor_induk_pegawai);
        AppUtil.dynamicIconLogoChange(getContext(),et_jenis_pekerjaan,tf_jenis_pekerjaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_deskripsi_pekerjaan,tf_deskripsi_pekerjaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_deskripsi_pekerjaan,tf_deskripsi_pekerjaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_usia_mpp,tf_usia_mpp);
        AppUtil.dynamicIconLogoChange(getContext(),et_nama_pejabat_berwenang,tf_nama_pejabat_berwenang);
        AppUtil.dynamicIconLogoChange(getContext(),et_no_surat_rekomendasi,tf_no_surat_rekomendasi);








    }

    private void setDynamicIconDropDown(){
        AppUtil.dynamicIconLogoChangeDropdown(getContext(), tf_bidang_pekerjaan, et_bidang_pekerjaan);
        AppUtil.dynamicIconLogoChangeDropdown(getContext(),tf_tanggalmulaiperusahaan,et_tanggalmulaiperusahaan);
        AppUtil.dynamicIconLogoChangeDropdown(getContext(), tf_bidang_pekerjaan ,et_bidang_pekerjaan);
        AppUtil.dynamicIconLogoChangeDropdown(getContext(), tf_status_kepegawaian ,et_status_kepegawaian);
        AppUtil.dynamicIconLogoChangeDropdown(getContext(), tf_posisi_jabatan ,et_posisi_jabatan);
        AppUtil.dynamicIconLogoChangeDropdown(getContext(), tf_pembayaran_gaji_melalui ,et_pembayaran_gaji_melalui);
        AppUtil.dynamicIconLogoChangeDropdown(getContext(), tf_tanggal_verifikasi_pejabat ,et_tanggal_verifikasi_pejabat_berwenang);




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
}