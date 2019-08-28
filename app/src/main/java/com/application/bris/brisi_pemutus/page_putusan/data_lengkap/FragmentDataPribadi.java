package com.application.bris.brisi_pemutus.page_putusan.data_lengkap;


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
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkap;
import com.application.bris.brisi_pemutus.model.keyvalue.keyvalue;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.KeyValue;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentDataPribadi extends Fragment implements Step {

    @BindView(R.id.tf_nik)
    TextFieldBoxes tf_nik;
    @BindView(R.id.et_nik)
    EditText et_nik;
    @BindView(R.id.tf_expirednik)
    TextFieldBoxes tf_expirednik;
    @BindView(R.id.et_expirednik)
    EditText et_expirednik;
    @BindView(R.id.tf_npwp)
    TextFieldBoxes tf_npwp;
    @BindView(R.id.et_npwp)
    EditText et_npwp;
    @BindView(R.id.tf_nama)
    TextFieldBoxes tf_nama;
    @BindView(R.id.et_nama)
    EditText et_nama;
    @BindView(R.id.tf_namaalias)
    TextFieldBoxes tf_namaalias;
    @BindView(R.id.et_namaalias)
    EditText et_namaalias;
    @BindView(R.id.tf_tempatlahir)
    TextFieldBoxes tf_tempatlahir;
    @BindView(R.id.et_tempatlahir)
    EditText et_tempatlahir;
    @BindView(R.id.tf_tanggallahir)
    TextFieldBoxes tf_tanggallahir;
    @BindView(R.id.et_tanggallahir)
    EditText et_tanggallahir;
    @BindView(R.id.tf_jeniskelamin)
    TextFieldBoxes tf_jeniskelamin;
    @BindView(R.id.et_jeniskelamin)
    EditText et_jeniskelamin;
    @BindView(R.id.tf_nomorhp)
    TextFieldBoxes tf_nomorhp;
    @BindView(R.id.et_nomorhp)
    EditText et_nomorhp;
    @BindView(R.id.tf_email)
    TextFieldBoxes tf_email;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.tf_agama)
    TextFieldBoxes tf_agama;
    @BindView(R.id.et_agama)
    EditText et_agama;
    @BindView(R.id.tf_ketagama)
    TextFieldBoxes tf_ketagama;
    @BindView(R.id.et_ketagama)
    EditText et_ketagama;
    @BindView(R.id.tf_namaibukandung)
    TextFieldBoxes tf_namaibukandung;
    @BindView(R.id.et_namaibukandung)
    EditText et_namaibukandung;
    @BindView(R.id.tf_statusnikah)
    TextFieldBoxes tf_statusnikah;
    @BindView(R.id.et_statusnikah)
    EditText et_statusnikah;
    @BindView(R.id.tf_nikpasangan)
    TextFieldBoxes tf_nikpasangan;
    @BindView(R.id.et_nikpasangan)
    EditText et_nikpasangan;
    @BindView(R.id.tf_namapasangan)
    TextFieldBoxes tf_namapasangan;
    @BindView(R.id.et_namapasangan)
    EditText et_namapasangan;
    @BindView(R.id.tf_tanggallahirpasangan)
    TextFieldBoxes tf_tanggallahirpasangan;
    @BindView(R.id.et_tanggallahirpasangan)
    EditText et_tanggallahirpasangan;
    @BindView(R.id.tf_namakeluarga)
    TextFieldBoxes tf_namakeluarga;
    @BindView(R.id.et_namakeluarga)
    EditText et_namakeluarga;
    @BindView(R.id.tf_nomorhpkeluarga)
    TextFieldBoxes tf_nomorhpkeluarga;
    @BindView(R.id.et_nomorhpkeluarga)
    EditText et_nomorhpkeluarga;
    @BindView(R.id.tf_jumlahtanggungan)
    TextFieldBoxes tf_jumlahtanggungan;
    @BindView(R.id.et_jumlahtanggungan)
    EditText et_jumlahtanggungan;
    @BindView(R.id.tf_tipependapatan)
    TextFieldBoxes tf_tipependapatan;
    @BindView(R.id.et_tipependapatan)
    EditText et_tipependapatan;
    @BindView(R.id.tf_pendidikanterakhir)
    TextFieldBoxes tf_pendidikanterakhir;
    @BindView(R.id.et_pendidikanterakhir)
    EditText et_pendidikanterakhir;
    @BindView(R.id.tf_gelar)
    TextFieldBoxes tf_gelar;
    @BindView(R.id.et_gelar)
    EditText et_gelar;

    @BindView(R.id.ll_pasangan)
    LinearLayout ll_pasangan;

    private Calendar calLahir;
    private Calendar calLahirPasangan;
    private Calendar calExpiredNik;

    private DatePickerDialog dpTanggalLahir;
    private DatePickerDialog dpTanggalLahirPasangan;
    private DatePickerDialog dpExpiredNik;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    public static SimpleDateFormat dateServer = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private DataLengkap dataLengkap;

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



    public FragmentDataPribadi() {
    }

    public FragmentDataPribadi(DataLengkap mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_datapribadi, container, false);
        ButterKnife.bind(this, view);
        setData();

        return view;
    }

    private void setData(){
        et_nik.setText(dataLengkap.getNoKtp());
       et_nik.setTextIsSelectable(true);
        et_nik.setFocusable(false);
        et_nik.setInputType(InputType.TYPE_NULL);
        et_nik.setKeyListener(null);

        et_expirednik.setText(AppUtil.parseTanggalGeneral(dataLengkap.getExpId(), "ddMMyyyy", "dd-MM-yyyy"));
        et_expirednik.setTextIsSelectable(true);
        et_expirednik.setFocusable(false);
        et_expirednik.setInputType(InputType.TYPE_NULL);
        et_expirednik.setKeyListener(null);

        et_npwp.setText(dataLengkap.getNpwp());
        et_npwp.setTextIsSelectable(true);
        et_npwp.setFocusable(false);
        et_npwp.setInputType(InputType.TYPE_NULL);
        et_npwp.setKeyListener(null);

        et_nama.setText(dataLengkap.getNamaNasabah());
        et_nama.setTextIsSelectable(true);
        et_nama.setFocusable(false);
        et_nama.setInputType(InputType.TYPE_NULL);
        et_nama.setKeyListener(null);

        et_namaalias.setText(dataLengkap.getNamaAlias());
        et_namaalias.setTextIsSelectable(true);
        et_namaalias.setFocusable(false);
        et_namaalias.setInputType(InputType.TYPE_NULL);
        et_namaalias.setKeyListener(null);

        et_tempatlahir.setText(dataLengkap.getTptLahir());
        et_tempatlahir.setTextIsSelectable(true);
        et_tempatlahir.setFocusable(false);
        et_tempatlahir.setInputType(InputType.TYPE_NULL);
        et_tempatlahir.setKeyListener(null);

        et_tanggallahir.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglLahir(), "ddMMyyyy", "dd-MM-yyyy"));
        et_tanggallahir.setTextIsSelectable(true);
        et_tanggallahir.setFocusable(false);
        et_tanggallahir.setInputType(InputType.TYPE_NULL);
        et_tanggallahir.setKeyListener(null);

        et_jeniskelamin.setText(KeyValue.getKeyJenisKelamin(dataLengkap.getJenkel()));
        et_jeniskelamin.setTextIsSelectable(true);
        et_jeniskelamin.setFocusable(false);
        et_jeniskelamin.setInputType(InputType.TYPE_NULL);
        et_jeniskelamin.setKeyListener(null);

        et_nomorhp.setText(dataLengkap.getNoHp());
        et_nomorhp.setTextIsSelectable(true);
        et_nomorhp.setFocusable(false);
        et_nomorhp.setInputType(InputType.TYPE_NULL);
        et_nomorhp.setKeyListener(null);

        et_email.setText(dataLengkap.getEmail());
        et_email.setTextIsSelectable(true);
        et_email.setFocusable(false);
        et_email.setInputType(InputType.TYPE_NULL);
        et_email.setKeyListener(null);

        et_agama.setText(KeyValue.getKeyAgama(dataLengkap.getAgama()));
        et_agama.setTextIsSelectable(true);
        et_agama.setFocusable(false);
        et_agama.setInputType(InputType.TYPE_NULL);
        et_agama.setKeyListener(null);

        et_namaibukandung.setText(dataLengkap.getNamaIbu());
        et_namaibukandung.setTextIsSelectable(true);
        et_namaibukandung.setFocusable(false);
        et_namaibukandung.setInputType(InputType.TYPE_NULL);
        et_namaibukandung.setKeyListener(null);

        et_statusnikah.setText(KeyValue.getKeyStatusNikah(dataLengkap.getStatusNikah()));
        et_statusnikah.setTextIsSelectable(true);
        et_statusnikah.setFocusable(false);
        et_statusnikah.setInputType(InputType.TYPE_NULL);
        et_statusnikah.setKeyListener(null);

        et_nikpasangan.setText(dataLengkap.getNoKtpPasangan());
        et_nikpasangan.setTextIsSelectable(true);
        et_nikpasangan.setFocusable(false);
        et_nikpasangan.setInputType(InputType.TYPE_NULL);
        et_nikpasangan.setKeyListener(null);

        et_tanggallahirpasangan.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglLahirPasangan(), "ddMMyyyy", "dd-MM-yyyy"));
        et_tanggallahirpasangan.setTextIsSelectable(true);
        et_tanggallahirpasangan.setFocusable(false);
        et_tanggallahirpasangan.setInputType(InputType.TYPE_NULL);
        et_tanggallahirpasangan.setKeyListener(null);

        et_namakeluarga.setText(dataLengkap.getKeluarga());
        et_namakeluarga.setTextIsSelectable(true);
        et_namakeluarga.setFocusable(false);
        et_namakeluarga.setInputType(InputType.TYPE_NULL);
        et_namakeluarga.setKeyListener(null);

        et_nomorhpkeluarga.setText(dataLengkap.getTelpKeluarga());
        et_nomorhpkeluarga.setTextIsSelectable(true);
        et_nomorhpkeluarga.setFocusable(false);
        et_nomorhpkeluarga.setInputType(InputType.TYPE_NULL);
        et_nomorhpkeluarga.setKeyListener(null);

        et_jumlahtanggungan.setText(String.valueOf(dataLengkap.getJlhTanggungan()));
        et_jumlahtanggungan.setTextIsSelectable(true);
        et_jumlahtanggungan.setFocusable(false);
        et_jumlahtanggungan.setInputType(InputType.TYPE_NULL);
        et_jumlahtanggungan.setKeyListener(null);

        et_tipependapatan.setText(KeyValue.getKeyTipePendapatan(dataLengkap.getTipePendapatan()));
        et_tipependapatan.setTextIsSelectable(true);
        et_tipependapatan.setFocusable(false);
        et_tipependapatan.setInputType(InputType.TYPE_NULL);
        et_tipependapatan.setKeyListener(null);

        et_pendidikanterakhir.setText(KeyValue.getKeyPendidikanTerakhir(dataLengkap.getPendTerakhir()));
        et_pendidikanterakhir.setTextIsSelectable(true);
        et_pendidikanterakhir.setFocusable(false);
        et_pendidikanterakhir.setInputType(InputType.TYPE_NULL);
        et_pendidikanterakhir.setKeyListener(null);

        et_gelar.setText(dataLengkap.getKetGelar());
        et_gelar.setTextIsSelectable(true);
        et_gelar.setFocusable(false);
        et_gelar.setInputType(InputType.TYPE_NULL);
        et_gelar.setKeyListener(null);


        if (dataLengkap.getStatusNikah().equalsIgnoreCase("2")){
            ll_pasangan.setVisibility(View.VISIBLE);
            et_nikpasangan.setText(dataLengkap.getNoKtpPasangan());
            et_nikpasangan.setTextIsSelectable(true);
            et_nikpasangan.setFocusable(false);
            et_nikpasangan.setInputType(InputType.TYPE_NULL);
            et_nikpasangan.setKeyListener(null);

            et_namapasangan.setText(dataLengkap.getNamaPasangan());
            et_namapasangan.setTextIsSelectable(true);
            et_namapasangan.setFocusable(false);
            et_namapasangan.setInputType(InputType.TYPE_NULL);
            et_namapasangan.setKeyListener(null);

            et_tanggallahirpasangan.setText(dataLengkap.getTglLahirPasangan());
            et_tanggallahirpasangan.setTextIsSelectable(true);
            et_tanggallahirpasangan.setFocusable(false);
            et_tanggallahirpasangan.setInputType(InputType.TYPE_NULL);
            et_tanggallahirpasangan.setKeyListener(null);

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

    }
}
