package com.application.bris.brisi_pemutus.page_putusan.data_lengkap;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkap;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.KeyValue;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentDataUsaha extends Fragment implements Step {

    @BindView(R.id.tf_bidangusaha)
    TextFieldBoxes tf_bidangusaha;
    @BindView(R.id.et_bidangusaha)
    EditText et_bidangusaha;

    @BindView(R.id.tf_namausaha)
    TextFieldBoxes tf_namausaha;
    @BindView(R.id.et_namausaha)
    EditText et_namausaha;

    @BindView(R.id.tf_tanggalmulaiusaha)
    TextFieldBoxes tf_tanggalmulaiusaha;
    @BindView(R.id.et_tanggalmulaiusaha)
    EditText et_tanggalmulaiusaha;

    @BindView(R.id.tf_nomortelponusaha)
    TextFieldBoxes tf_nomortelponusaha;
    @BindView(R.id.et_nomortelponusaha)
    EditText et_nomortelponusaha;

    //USAHA

    @BindView(R.id.ll_alamatusaha)
    LinearLayout ll_alamatusaha;
    @BindView(R.id.tf_alamatusaha)
    TextFieldBoxes tf_alamatusaha;
    @BindView(R.id.et_alamatusaha)
    EditText et_alamatusaha;
    @BindView(R.id.tf_rtusaha)
    TextFieldBoxes tf_rtusaha;
    @BindView(R.id.et_rtusaha)
    EditText et_rtusaha;
    @BindView(R.id.tf_rwusaha)
    TextFieldBoxes tf_rwusaha;
    @BindView(R.id.et_rwusaha)
    EditText et_rwusaha;
    @BindView(R.id.tf_provinsiusaha)
    TextFieldBoxes tf_provinsiusaha;
    @BindView(R.id.et_provinsiusaha)
    EditText et_provinsiusaha;
    @BindView(R.id.tf_kotausaha)
    TextFieldBoxes tf_kotausaha;
    @BindView(R.id.et_kotausaha)
    EditText et_kotausaha;
    @BindView(R.id.tf_kecamatanusaha)
    TextFieldBoxes tf_kecamatanusaha;
    @BindView(R.id.et_kecamatanusaha)
    EditText et_kecamatanusaha;
    @BindView(R.id.tf_kelurahanusaha)
    TextFieldBoxes tf_kelurahanusaha;
    @BindView(R.id.et_kelurahanusaha)
    EditText et_kelurahanusaha;
    @BindView(R.id.tf_kodeposusaha)
    TextFieldBoxes tf_kodeposusaha;
    @BindView(R.id.et_kodeposusaha)
    EditText et_kodeposusaha;
    @BindView(R.id.btn_usaha)
    Button btn_usaha;

    private DataLengkap dataLengkap;
    private Calendar calTanggalMulaiUsaha;
    private DatePickerDialog dpTanggalMulaiUsaha;

    public static String val_BidangUsaha ="";
    public static String val_NamaUsaha ="";
    public static String val_TglMulaiUsaha ="";
    public static String val_NoTelpUsaha ="";
    public static String val_AlamatUsaha ="";
    public static String val_RtUsaha ="";
    public static String val_RwUsaha ="";
    public static String val_ProvUsaha ="";
    public static String val_KotaUsaha ="";
    public static String val_KecUsaha ="";
    public static String val_KelUsaha ="";
    public static String val_KodePosUsaha ="";
    private int val_usahaAsId = 0;

    @SuppressLint("ValidFragment")
    public FragmentDataUsaha(DataLengkap mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_datausaha, container, false);
        ButterKnife.bind(this, view);


        setData();


        return view;
    }

    private void setData() {
        et_bidangusaha.setText(KeyValue.getKeyUsahaorJob(dataLengkap.getBidangUsaha()));
        et_bidangusaha.setTextIsSelectable(true);
        et_bidangusaha.setFocusable(false);
        et_bidangusaha.setInputType(InputType.TYPE_NULL);
        et_bidangusaha.setKeyListener(null);

        et_namausaha.setText(dataLengkap.getNamaUsaha());
        et_namausaha.setTextIsSelectable(true);
        et_namausaha.setFocusable(false);
        et_namausaha.setInputType(InputType.TYPE_NULL);
        et_namausaha.setKeyListener(null);

        et_tanggalmulaiusaha.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglMulaiUsaha(), "ddMMyyyy", "dd-MM-yyyy"));
        et_tanggalmulaiusaha.setTextIsSelectable(true);
        et_tanggalmulaiusaha.setFocusable(false);
        et_tanggalmulaiusaha.setInputType(InputType.TYPE_NULL);
        et_tanggalmulaiusaha.setKeyListener(null);

        et_nomortelponusaha.setText(dataLengkap.getNoTelpUsaha());
        et_nomortelponusaha.setTextIsSelectable(true);
        et_nomortelponusaha.setFocusable(false);
        et_nomortelponusaha.setInputType(InputType.TYPE_NULL);
        et_nomortelponusaha.setKeyListener(null);

        et_alamatusaha.setText(dataLengkap.getAlamatUsaha());
        et_alamatusaha.setTextIsSelectable(true);
        et_alamatusaha.setFocusable(false);
        et_alamatusaha.setInputType(InputType.TYPE_NULL);
        et_alamatusaha.setKeyListener(null);

        et_rtusaha.setText(dataLengkap.getRtUsaha());
        et_rtusaha.setTextIsSelectable(true);
        et_rtusaha.setFocusable(false);
        et_rtusaha.setInputType(InputType.TYPE_NULL);
        et_rtusaha.setKeyListener(null);

        et_rwusaha.setText(dataLengkap.getRwUsaha());
        et_rwusaha.setTextIsSelectable(true);
        et_rwusaha.setFocusable(false);
        et_rwusaha.setInputType(InputType.TYPE_NULL);
        et_rwusaha.setKeyListener(null);

        et_provinsiusaha.setText(dataLengkap.getProvUsaha());
        et_provinsiusaha.setTextIsSelectable(true);
        et_provinsiusaha.setFocusable(false);
        et_provinsiusaha.setInputType(InputType.TYPE_NULL);
        et_provinsiusaha.setKeyListener(null);

        et_kotausaha.setText(dataLengkap.getKotaUsaha());
        et_kotausaha.setTextIsSelectable(true);
        et_kotausaha.setFocusable(false);
        et_kotausaha.setInputType(InputType.TYPE_NULL);
        et_kotausaha.setKeyListener(null);

        et_kecamatanusaha.setText(dataLengkap.getKecUsaha());
        et_kecamatanusaha.setTextIsSelectable(true);
        et_kecamatanusaha.setFocusable(false);
        et_kecamatanusaha.setInputType(InputType.TYPE_NULL);
        et_kecamatanusaha.setKeyListener(null);

        et_kelurahanusaha.setText(dataLengkap.getKelUsaha());
        et_kelurahanusaha.setTextIsSelectable(true);
        et_kelurahanusaha.setFocusable(false);
        et_kelurahanusaha.setInputType(InputType.TYPE_NULL);
        et_kelurahanusaha.setKeyListener(null);

        et_kodeposusaha.setText(dataLengkap.getKodePosUsaha());
        et_kodeposusaha.setTextIsSelectable(true);
        et_kodeposusaha.setFocusable(false);
        et_kodeposusaha.setInputType(InputType.TYPE_NULL);
        et_kodeposusaha.setKeyListener(null);


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
