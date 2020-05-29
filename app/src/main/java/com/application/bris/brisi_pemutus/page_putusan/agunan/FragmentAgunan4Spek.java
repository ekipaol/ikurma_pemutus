package com.application.bris.brisi_pemutus.page_putusan.agunan;




import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;


/**
 * A simple {@link Fragment} subclass.
 */
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.InputType;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.listeners.KeyValueListener;
import com.application.bris.brisi_pemutus.model.agunan.Agunan;
import com.application.bris.brisi_pemutus.model.keyvalue.keyvalue;
import com.application.bris.brisi_pemutus.util.KeyValue;
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
public class FragmentAgunan4Spek extends Fragment implements Step, KeyValueListener {

    @BindView(R.id.tf_kondisi_bangunan)
    TextFieldBoxes tf_kondisi_bangunan;
    @BindView(R.id.et_kondisi_bangunan)
    ExtendedEditText et_kondisi_bangunan;

    @BindView(R.id.tf_pondasi)
    TextFieldBoxes tf_pondasi;
    @BindView(R.id.et_pondasi)
    ExtendedEditText et_pondasi;

    @BindView(R.id.tf_jenis_bangunan_spek)
    TextFieldBoxes tf_jenis_bangunan_spek;
    @BindView(R.id.et_jenis_bangunan_spek)
    ExtendedEditText et_jenis_bangunan_spek;

    @BindView(R.id.tf_dinding)
    TextFieldBoxes tf_dinding;
    @BindView(R.id.et_dinding)
    ExtendedEditText et_dinding;

    @BindView(R.id.tf_plafon_agunan)
    TextFieldBoxes tf_plafon_agunan;
    @BindView(R.id.et_plafon_agunan)
    ExtendedEditText et_plafon_agunan;

    @BindView(R.id.tf_lantai_spek)
    TextFieldBoxes tf_lantai_spek;
    @BindView(R.id.et_lantai_spek)
    ExtendedEditText et_lantai_spek;

    @BindView(R.id.tf_pagar)
    TextFieldBoxes tf_pagar;
    @BindView(R.id.et_pagar)
    ExtendedEditText et_pagar;


    @BindView(R.id.tf_listrik)
    TextFieldBoxes tf_listrik;
    @BindView(R.id.et_listrik)
    ExtendedEditText et_listrik;

    @BindView(R.id.tf_air)
    TextFieldBoxes tf_air;
    @BindView(R.id.et_air)
    ExtendedEditText et_air;

    @BindView(R.id.tf_telepon)
    TextFieldBoxes tf_telepon;
    @BindView(R.id.et_telepon)
    ExtendedEditText et_telepon;

    @BindView(R.id.tf_atap)
    TextFieldBoxes tf_atap;
    @BindView(R.id.et_atap)
    ExtendedEditText et_atap;

    @BindView(R.id.tf_teras)
    TextFieldBoxes tf_teras;
    @BindView(R.id.et_teras)
    ExtendedEditText et_teras;

    @BindView(R.id.tf_garasi)
    TextFieldBoxes tf_garasi;
    @BindView(R.id.et_garasi)
    ExtendedEditText et_garasi;

    @BindView(R.id.ada_listrik)
    CheckBox ada_listrik;
    @BindView(R.id.ada_air)
    CheckBox ada_air;
    @BindView(R.id.ada_telepon)
    CheckBox ada_telepon;



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



    public FragmentAgunan4Spek() {
    }

    public FragmentAgunan4Spek(Agunan mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_4_spesifikasi, container, false);
        ButterKnife.bind(this, view);
        setData();

        return view;
    }

    private void setData(){

        disableTextFocus(et_kondisi_bangunan);
        et_kondisi_bangunan.setText(KeyValue.getKeyKondisiBangunan(dataLengkap.getPerawatan()));
        disableTextFocus(et_pondasi);
        et_pondasi.setText(KeyValue.getKeyPondasi(dataLengkap.getPondasi()));
        disableTextFocus(et_jenis_bangunan_spek);
        et_jenis_bangunan_spek.setText(KeyValue.getKeyJenisBangunanSpek(dataLengkap.getJenisBangunanBRINS()));
        disableTextFocus(et_dinding);
        et_dinding.setText(KeyValue.getKeyDinding(dataLengkap.getDinding()));
        disableTextFocus(et_plafon_agunan);
        et_plafon_agunan.setText(dataLengkap.getPlafond());
        disableTextFocus(et_lantai_spek);
        et_lantai_spek.setText(dataLengkap.getLantai());
        disableTextFocus(et_pagar);
        et_pagar.setText(dataLengkap.getPagar());
        disableTextFocus(et_listrik);
        et_listrik.setText(dataLengkap.getTeganganListrik());
        if(dataLengkap.getTeganganListrik().length()>0){
            ada_listrik.isChecked();
        }
        disableTextFocus(et_air);
        et_air.setText(dataLengkap.getJenisAir());
        if(dataLengkap.getJenisAir().length()>0){
            ada_air.isChecked();
        }
        disableTextFocus(et_telepon);
        et_telepon.setText(dataLengkap.getNoTelepon());
        if(dataLengkap.getNoTelepon().length()>0){
            ada_telepon.isChecked();
        }
        disableTextFocus(et_atap);
        et_atap.setText(KeyValue.getKeyAtap(dataLengkap.getAtap()));
        disableTextFocus(et_teras);
        et_teras.setText(dataLengkap.getTeras());
        disableTextFocus(et_garasi);
        et_garasi.setText(dataLengkap.getGarasiCarport());









    }




    private void disableTextFocus(ExtendedEditText et){
        et.setFocusable(false);
        et.setInputType(InputType.TYPE_NULL);
        et.setKeyListener(null);
    }


    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("jenis bangunan")){
            et_jenis_bangunan_spek.setText(data.getName());
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


