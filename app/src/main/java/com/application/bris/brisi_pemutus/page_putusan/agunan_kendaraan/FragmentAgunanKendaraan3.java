package com.application.bris.brisi_pemutus.page_putusan.agunan_kendaraan;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.agunan_kendaraan.AgunanKendaraan;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.NumberTextWatcherCanNolForThousand;
import com.application.bris.brisi_pemutus.util.NumberTextWatcherForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
public class FragmentAgunanKendaraan3 extends Fragment implements Step {

    //VIEW BINDING
    @BindView(R.id.tf_tipe_kendaraan)
    TextFieldBoxes tf_tipe_kendaraan;
    @BindView(R.id.et_tipe_kendaraan)
    ExtendedEditText et_tipe_kendaraan;

    @BindView(R.id.tf_merk_kendaraan)
    TextFieldBoxes tf_merk_kendaraan;
    @BindView(R.id.et_merk_kendaraan)
    ExtendedEditText et_merk_kendaraan;

    @BindView(R.id.tf_kendaraan_jepang)
    TextFieldBoxes tf_kendaraan_jepang;
    @BindView(R.id.et_kendaraan_jepang)
    ExtendedEditText et_kendaraan_jepang;

    @BindView(R.id.tf_daya_angkut)
    TextFieldBoxes tf_daya_angkut;
    @BindView(R.id.et_daya_angkut)
    ExtendedEditText et_daya_angkut;

    @BindView(R.id.tf_kapasitas)
    TextFieldBoxes tf_kapasitas;
    @BindView(R.id.et_kapasitas)
    ExtendedEditText et_kapasitas;

    @BindView(R.id.tf_check_samsat)
    TextFieldBoxes tf_check_samsat;
    @BindView(R.id.et_check_samsat)
    ExtendedEditText et_check_samsat;

    @BindView(R.id.tf_dengan_siapa)
    TextFieldBoxes tf_dengan_siapa;
    @BindView(R.id.et_dengan_siapa)
    ExtendedEditText et_dengan_siapa;

    @BindView(R.id.tf_no_telp)
    TextFieldBoxes tf_no_telp;
    @BindView(R.id.et_no_telp)
    ExtendedEditText et_no_telp;

    @BindView(R.id.tf_hasil)
    TextFieldBoxes tf_hasil;
    @BindView(R.id.et_hasil)
    ExtendedEditText et_hasil;

    @BindView(R.id.tf_nilai_market)
    TextFieldBoxes tf_nilai_market;
    @BindView(R.id.et_nilai_market)
    ExtendedEditText et_nilai_market;

    @BindView(R.id.tf_nilai_likuidasi)
    TextFieldBoxes tf_nilai_likuidasi;
    @BindView(R.id.et_nilai_likuidasi)
    ExtendedEditText et_nilai_likuidasi;

    //END OF VIEW BINDING

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String idAgunan;
    private AgunanKendaraan dataAgunan;

    public static String val_TipeKendaraan = "";
    public static String val_MerkKendaraan = "";
    public static String val_KendaraanJepang = "";
    public static String val_DayaAngkut = "";
    public static String val_Kapasitas = "";
    public static String val_CheckSamsat = "";
    public static String val_DenganSiapa = "";
    public static String val_NoTelp = "";
    public static String val_Hasil = "";
    public static String val_NilaiMarket = "";
    public static String val_NilaiLikuidasi = "";

    public FragmentAgunanKendaraan3() {
    }

    public FragmentAgunanKendaraan3(String midAgunan, AgunanKendaraan magunanData) {
        dataAgunan = magunanData;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_kendaraan_3, container, false);
        ButterKnife.bind(this, view);

        if (!String.valueOf(idAgunan).equalsIgnoreCase("0")) {
            Log.d("Cek idAgunan", String.valueOf(idAgunan));
            setData();
            disableTextfield();
        }

        onSelectDialog();

        return view;
    }

    private void onSelectDialog() {
        et_kendaraan_jepang.setFocusable(false);
        et_kendaraan_jepang.setInputType(InputType.TYPE_NULL);



        et_check_samsat.setFocusable(false);
        et_check_samsat.setInputType(InputType.TYPE_NULL);



        et_hasil.setFocusable(false);
        et_hasil.setInputType(InputType.TYPE_NULL);



        et_nilai_market.addTextChangedListener(new NumberTextWatcherForThousand(et_nilai_market));
        et_nilai_likuidasi.addTextChangedListener(new NumberTextWatcherForThousand(et_nilai_likuidasi));
    }



    private void setData() {
        et_tipe_kendaraan.setText(dataAgunan.getTipeKendaraan());
        et_merk_kendaraan.setText(dataAgunan.getMerkKendaraan());
        et_kendaraan_jepang.setText(dataAgunan.getMobiljepang());
        et_daya_angkut.setText(dataAgunan.getDayaAngkut());
        et_kapasitas.setText(dataAgunan.getKapasitas());
        et_check_samsat.setText(dataAgunan.getCheckSamsat());
        et_dengan_siapa.setText(dataAgunan.getDenganSiapa());
        et_no_telp.setText(dataAgunan.getNoTelpon());
        et_hasil.setText(dataAgunan.getHasil());

        et_nilai_market.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilai_market));
        et_nilai_market.setText(dataAgunan.getNilaiMarket());

        et_nilai_likuidasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilai_likuidasi));
        et_nilai_likuidasi.setText(dataAgunan.getNilaiTaksasi());
    }

    private void disableTextfield() {
        et_tipe_kendaraan.setInputType(InputType.TYPE_NULL);
        et_tipe_kendaraan.setFocusable(false);

        et_merk_kendaraan.setInputType(InputType.TYPE_NULL);
        et_merk_kendaraan.setFocusable(false);

        et_kendaraan_jepang.setInputType(InputType.TYPE_NULL);
        et_kendaraan_jepang.setFocusable(false);

        et_daya_angkut.setInputType(InputType.TYPE_NULL);
        et_daya_angkut.setFocusable(false);

        et_kapasitas.setInputType(InputType.TYPE_NULL);
        et_kapasitas.setFocusable(false);

        et_check_samsat.setInputType(InputType.TYPE_NULL);
        et_check_samsat.setFocusable(false);

        et_dengan_siapa.setInputType(InputType.TYPE_NULL);
        et_dengan_siapa.setFocusable(false);

        et_no_telp.setInputType(InputType.TYPE_NULL);
        et_no_telp.setFocusable(false);

        et_hasil.setInputType(InputType.TYPE_NULL);
        et_hasil.setFocusable(false);

        et_nilai_market.setInputType(InputType.TYPE_NULL);
        et_nilai_market.setFocusable(false);

        et_nilai_likuidasi.setInputType(InputType.TYPE_NULL);
        et_nilai_likuidasi.setFocusable(false);



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
