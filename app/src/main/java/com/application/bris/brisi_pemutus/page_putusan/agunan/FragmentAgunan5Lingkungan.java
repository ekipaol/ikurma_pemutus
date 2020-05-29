package com.application.bris.brisi_pemutus.page_putusan.agunan;





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

import android.text.InputType;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.agunan.Agunan;
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
public class FragmentAgunan5Lingkungan extends Fragment implements Step {

    @BindView(R.id.tf_peruntukan_lingkungan)
    TextFieldBoxes tf_peruntukan_lingkungan;
    @BindView(R.id.et_peruntukan_lingkungan)
    ExtendedEditText et_peruntukan_lingkungan;

    @BindView(R.id.tf_perkembangan_lingkungan)
    TextFieldBoxes tf_perkembangan_lingkungan;
    @BindView(R.id.et_perkembangan_lingkungan)
    ExtendedEditText et_perkembangan_lingkungan;

    @BindView(R.id.tf_kepadatan)
    TextFieldBoxes tf_kepadatan;
    @BindView(R.id.et_kepadatan)
    ExtendedEditText et_kepadatan;

    @BindView(R.id.tf_akses_capai)
    TextFieldBoxes tf_akses_capai;
    @BindView(R.id.et_akses_capai)
    ExtendedEditText et_akses_capai;

    @BindView(R.id.tf_akses_jalan)
    TextFieldBoxes tf_akses_jalan;
    @BindView(R.id.et_akses_jalan)
    ExtendedEditText et_akses_jalan;

    @BindView(R.id.tf_fasilitas_sosial)
    TextFieldBoxes tf_fasilitas_sosial;
    @BindView(R.id.et_fasilitas_sosial)
    ExtendedEditText et_fasilitas_sosial;

    @BindView(R.id.tf_lebar_jalan_depan)
    TextFieldBoxes tf_lebar_jalan_depan;
    @BindView(R.id.et_lebar_jalan_depan)
    ExtendedEditText et_lebar_jalan_depan;

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



    public FragmentAgunan5Lingkungan() {
    }

    public FragmentAgunan5Lingkungan(Agunan mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_5_lingkungan, container, false);
        ButterKnife.bind(this, view);
         setData();

        return view;
    }

    private void setData(){


        disableTextFocus(et_peruntukan_lingkungan);
        et_peruntukan_lingkungan.setText(dataLengkap.getPeruntukanLokasi());

        disableTextFocus(et_perkembangan_lingkungan);
        et_perkembangan_lingkungan.setText(dataLengkap.getPerkembanganLingkungan());

        disableTextFocus(et_kepadatan);
        et_kepadatan.setText(dataLengkap.getKepadatan());

        disableTextFocus(et_akses_capai);
        et_akses_capai.setText(dataLengkap.getAksesPencapaian());

        disableTextFocus(et_akses_jalan);
        et_akses_jalan.setText(dataLengkap.getAksesJalanObjek());

        disableTextFocus(et_fasilitas_sosial);
        et_fasilitas_sosial.setText(dataLengkap.getFasilitasSosial());

        disableTextFocus(et_lebar_jalan_depan);
        et_lebar_jalan_depan.setText(dataLengkap.getLebarJalanDepan());


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
}


