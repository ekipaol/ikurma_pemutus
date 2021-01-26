package com.application.bris.brisi_pemutus.page_konsumer_purna.page_konsumer_purna.data_lengkap;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import androidx.fragment.app.Fragment;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkapKonsumerKmg;
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

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

@SuppressLint("ValidFragment")
public class FragmentDataPekerjaanPurna extends Fragment implements Step {

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

    @BindView(R.id.tf_sisa_plafond_perusahaan)
    TextFieldBoxes tf_sisa_plafond_perusahaan;
    @BindView(R.id.et_sisa_plafond_perusahaan)
    EditText et_sisa_plafond_perusahaan;

    @BindView(R.id.tf_institusi_pembayaran_gaji_pensiun)
    TextFieldBoxes tf_institusi_pembayaran_gaji_pensiun;
    @BindView(R.id.et_institusi_pembayaran_gaji_pensiun)
    EditText et_institusi_pembayaran_gaji_pensiun;

    @BindView(R.id.tf_direct_marketing)
    TextFieldBoxes tf_direct_marketing;
    @BindView(R.id.et_direct_marketing)
    EditText et_direct_marketing;

    @BindView(R.id.tf_kategori_calon_nasabah_pensiunan)
    TextFieldBoxes tf_kategori_calon_nasabah_pensiunan;
    @BindView(R.id.et_kategori_calon_nasabah_pensiunan)
    EditText et_kategori_calon_nasabah_pensiunan;

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

    @BindView(R.id.tf_nomor_sk_pegawai_tetap)
    TextFieldBoxes tf_nomor_sk_pegawai_tetap;
    @BindView(R.id.et_nomor_sk_pegawai_tetap)
    EditText et_nomor_sk_pegawai_tetap;

    @BindView(R.id.tf_nomor_sk_pangkat_terakhir)
    TextFieldBoxes tf_nomor_sk_pangkat_terakhir;
    @BindView(R.id.et_nomor_sk_pangkat_terakhir)
    EditText et_nomor_sk_pangkat_terakhir;

    @BindView(R.id.tf_nomor_sk_pensiun)
    TextFieldBoxes tf_nomor_sk_pensiun;
    @BindView(R.id.et_nomor_sk_pensiun)
    EditText et_nomor_sk_pensiun;

    @BindView(R.id.tf_nomor_taspen)
    TextFieldBoxes tf_nomor_taspen;
    @BindView(R.id.et_nomor_taspen)
    EditText et_nomor_taspen;

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

    @BindView(R.id.tf_tanggalmulaipensiun)
    TextFieldBoxes tf_tanggalmulaipensiun;
    @BindView(R.id.et_tanggalmulaipensiun)
    EditText et_tanggalmulaipensiun;

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


    @BindView(R.id.rl_fotokantor1)
    RelativeLayout rl_fotokantor1;
    @BindView(R.id.rl_fotokantor2)
    RelativeLayout rl_fotokantor2;

    private Realm realm;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private DataLengkapKonsumerKmg dataLengkap;
    private Calendar calTanggalMulaiUsaha;
    private Calendar calTanggalverifikasi;
    private DatePickerDialog dpTanggalMulaiUsaha;
    private DatePickerDialog dpTanggalVerifikasi;
    private String approved, gimmick;

    private String val_BidangUsaha = "";
    private String val_NamaUsaha = "";
    private String val_TglMulaiUsaha = "";
    private String val_NoTelpUsaha = "";
    private String val_AlamatUsaha = "";
    private String val_RtUsaha = "";
    private String val_RwUsaha = "";
    private String val_ProvUsaha = "";
    private String val_KotaUsaha = "";
    private String val_KecUsaha = "";
    private String val_KelUsaha = "";
    private String val_KodePosUsaha = "";
    private String val_status_kepegawaian = "";
    private String val_bidang_pekerjaan = "";
    private String val_sisa_plafond = "";
    private String val_no_sk_pegawai_tetap = "";
    private String val_no_sk_pangkat_terakhir = "";
    private String val_no_induk_pegawai = "";
    private String val_usia_mpp = "";
    private String val_posisi_jabatan = "";
    private String val_pembayaran_gaji_melalui = "";
    private String val_tanggal_verifikasi = "";
    private String val_nama_pejabat_berwenang = "";
    private String val_no_surat_rekomendasi = "";
    private String val_kategori_nasabah = "";
    private String val_id_instansi = "";
    private String val_nomor_sk_pensiun = "";
    private String val_nomor_taspen = "";
    private int val_ImgKantor1 = 0;
    private int val_ImgKantor2 = 0;
    private String isSelectPhoto = "";

    private final int PICK_PICTURE_KANTOR1 = 1;
    private final int PICK_PICTURE_KANTOR2 = 2;
    private final int TAKE_PICTURE_KANTOR1 = 11;
    private final int TAKE_PICTURE_KANTOR2 = 22;

    private Uri uriPhotoKantor1, uriPhotoKantor2;
    private Bitmap bitmapPhotoKantor1, bitmapPhotoKantor2, loadedPicture;

    private int val_usahaAsId = 0;

    @SuppressLint("ValidFragment")
//    public FragmentDataPekerjaanPurna(DataLengkap mdataLengkap) {
//        dataLengkap = mdataLengkap;
//    }

    public FragmentDataPekerjaanPurna(DataLengkapKonsumerKmg mdataLengkap, String mgimmick) {
        dataLengkap = mdataLengkap;
        //pantekan gimmick
//        gimmick="14";

        //real gimmick
        gimmick = mgimmick;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_data_perusahaan_purna, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());

        //pantekan gimmick
//        gimmick="14";

        //PENGHILANGAN FIELD TERGANTUNG GIMMICK

        //kmg/kmj embp

        if (gimmick.equalsIgnoreCase("1")||gimmick.equalsIgnoreCase("3") ) {
            tf_institusi_pembayaran_gaji_pensiun.setVisibility(View.GONE);
            tf_direct_marketing.setVisibility(View.GONE);
            tf_kategori_calon_nasabah_pensiunan.setVisibility(View.GONE);
            Log.d("masuk if1", "masuk1");
        }


        //         pra purna embp
        //        //26,27,28 -> kmg pra purna embp
        //        //7-> multifaedah mikro kmg prapurna embp
        //        //5-> multifaedah mikro kmj  prapurna embp
            else if( gimmick.equalsIgnoreCase("26") || gimmick.equalsIgnoreCase("27") || gimmick.equalsIgnoreCase("28")  || gimmick.equalsIgnoreCase("7")|| gimmick.equalsIgnoreCase("5")){

            tf_bidang_pekerjaan.setVisibility(View.VISIBLE);
            tf_namaperusahaan.setVisibility(View.VISIBLE);
            tf_jenis_pekerjaan.setVisibility(View.VISIBLE);
            tf_deskripsi_pekerjaan.setVisibility(View.VISIBLE);
            tf_no_surat_rekomendasi.setVisibility(View.GONE);
            tf_sisa_plafond_perusahaan.setVisibility(View.GONE);

            }


            //purna
            //14,15,16 -> kmg purna
            //6 -> multifaedah mikro purna kmg
            //4 -> multifaedah mikro purna kmj

         else if (gimmick.equalsIgnoreCase("14") || gimmick.equalsIgnoreCase("15") || gimmick.equalsIgnoreCase("16")|| gimmick.equalsIgnoreCase("6")|| gimmick.equalsIgnoreCase("4")) {
            ubahFieldKhususPurna();
            Log.d("masuk if2","masuk2");

        }
         //ini untuk kmg dan kmj prapurna non embp
         else {
            tf_bidang_pekerjaan.setVisibility(View.GONE);
            tf_namaperusahaan.setVisibility(View.GONE);
            tf_jenis_pekerjaan.setVisibility(View.GONE);
            tf_deskripsi_pekerjaan.setVisibility(View.GONE);
            tf_sisa_plafond_perusahaan.setVisibility(View.GONE);
            Log.d("masuk if3","masuk3");
        }

        setData();



        return view;
    }


    private void setData() {
        et_bidang_pekerjaan.setText(KeyValue.getKeyUsahaorJob(dataLengkap.getBidangPekerjaan()));
        et_namaperusahaan.setText(dataLengkap.getNamaPerusahaan());
        et_sisa_plafond_perusahaan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_sisa_plafond_perusahaan));
        et_sisa_plafond_perusahaan.setText(dataLengkap.getSisaPlafondPerusahaan());
        et_institusi_pembayaran_gaji_pensiun.setText(dataLengkap.getNamaInstitusi());
        et_direct_marketing.setText(dataLengkap.getRekDm());
        et_kategori_calon_nasabah_pensiunan.setText(dataLengkap.getJenisKMG());
//        et_tanggalmulaiperusahaan.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglMulaiBekerja(), "ddMMyyyy", "dd-MM-yyyy"));
        et_tanggalmulaipensiun.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglPensiunan(), "ddMMyyyy", "dd-MM-yyyy"));
        et_nomortelponperusahaan.setText(dataLengkap.getNoTelpPerusahaan());
        et_alamatperusahaan.setText(dataLengkap.getAlamatPerusahaan());
//        et_rtperusahaan.setText(dataLengkap.getRtUsaha());
//        et_rwperusahaan.setText(dataLengkap.getRwUsaha());
        et_provinsiperusahaan.setText(dataLengkap.getProvPerusahaan());
        et_kotaperusahaan.setText(dataLengkap.getKotaPerusahaan());
        et_kecamatanperusahaan.setText(dataLengkap.getKecPerusahaan());
        et_kelurahanperusahaan.setText(dataLengkap.getKelPerusahaan());
        et_kodeposperusahaan.setText(dataLengkap.getKodePosPerusahaan());
        et_nomor_sk_pegawai_tetap.setText(dataLengkap.getNoSKPertama());
        et_nomor_sk_pangkat_terakhir.setText(dataLengkap.getNoSKterakhir());

        //trim the SK, karena dari service dapet spasi banyak banget
        et_nomor_sk_pegawai_tetap.setText(et_nomor_sk_pegawai_tetap.getText().toString().trim());
        et_nomor_sk_pangkat_terakhir.setText(et_nomor_sk_pangkat_terakhir.getText().toString().trim());


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
        et_nomor_taspen.setText(dataLengkap.getNomorTaspen());
        et_nomor_sk_pensiun.setText(dataLengkap.getSkPensiun());

        bitmapPhotoKantor1 = setLoadImage(img_fotokantor1, dataLengkap.getFID_PHOTO_KANTOR1());
        bitmapPhotoKantor2 = setLoadImage(img_fotokantor2, dataLengkap.getFID_PHOTO_KANTOR2());
        val_ImgKantor1 = dataLengkap.getFID_PHOTO_KANTOR1();
        val_ImgKantor2 = dataLengkap.getFID_PHOTO_KANTOR2();


            AppUtil.disableEditTexts(ll_datapekerjaan);
            btn_perusahaan.setVisibility(View.GONE);
            btn_upload_fotokantor1.setVisibility(View.GONE);
            btn_upload_fotokantor2.setVisibility(View.GONE);

    }

    public Bitmap setLoadImage(final ImageView iv, int idFoto) {
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

    private void ubahFieldKhususPurna(){

        tf_nomor_sk_pensiun.setVisibility(View.VISIBLE);
        tf_nomor_taspen.setVisibility(View.VISIBLE);

        //hide semua field yang berhubungan dengan instansi
        tf_tanggalmulaiperusahaan.setVisibility(View.GONE);
        tf_tanggalmulaipensiun.setVisibility(View.GONE);
        tf_nomor_sk_pangkat_terakhir.setVisibility(View.GONE);
        tf_nomor_sk_pegawai_tetap.setVisibility(View.GONE);
        tf_status_kepegawaian.setVisibility(View.GONE);
        tf_posisi_jabatan.setVisibility(View.GONE);
        tf_bidang_pekerjaan.setVisibility(View.GONE);
        tf_namaperusahaan.setVisibility(View.GONE);
        tf_jenis_pekerjaan.setVisibility(View.GONE);
        tf_deskripsi_pekerjaan.setVisibility(View.GONE);
        tf_sisa_plafond_perusahaan.setVisibility(View.GONE);
        tf_alamatperusahaan.setVisibility(View.GONE);
        tf_provinsiperusahaan.setVisibility(View.GONE);
        tf_kecamatanperusahaan.setVisibility(View.GONE);
        tf_kelurahanperusahaan.setVisibility(View.GONE);
        tf_rtperusahaan.setVisibility(View.GONE);
        tf_rwperusahaan.setVisibility(View.GONE);
        tf_kotaperusahaan.setVisibility(View.GONE);
        tf_kodeposperusahaan.setVisibility(View.GONE);
        btn_perusahaan.setVisibility(View.GONE);
        ll_alamatperusahaan.setVisibility(View.GONE);
        rl_fotokantor1.setVisibility(View.GONE);
        rl_fotokantor2.setVisibility(View.GONE);

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