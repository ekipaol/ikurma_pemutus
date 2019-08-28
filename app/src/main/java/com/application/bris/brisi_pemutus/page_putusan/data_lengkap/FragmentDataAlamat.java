package com.application.bris.brisi_pemutus.page_putusan.data_lengkap;


import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkap;
import com.application.bris.brisi_pemutus.util.KeyValue;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentDataAlamat extends Fragment implements Step {

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
//    @BindView(R.id.btn_ktp)
//    Button btn_ktp;

    //DOMISILI
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

    private DataLengkap dataLengkap;

    public static String val_AlamatId ="";
    public static String val_RtId ="";
    public static String val_RwId ="";
    public static String val_ProvId ="";
    public static String val_KotaId ="";
    public static String val_KecId ="";
    public static String val_KelId ="";
    public static String val_KodePosId ="";
    public static String val_StatusTptTinggal ="";
    public static int val_LamaMenetap = 0;
    public static String val_AlamatDom ="";
    public static String val_RtDom ="";
    public static String val_RwDom ="";
    public static String val_ProvDom ="";
    public static String val_KotaDom ="";
    public static String val_KecDom ="";
    public static String val_KelDom ="";
    public static String val_KodePosDom ="";

    private int val_domAsId = 0;
    private String btnAddressString = "";

    public FragmentDataAlamat(DataLengkap mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_dataalamat, container, false);
        ButterKnife.bind(this, view);

        setData();



        return view;
    }

    public void setData(){
        et_alamatktp.setText(dataLengkap.getAlamatId());
        et_alamatktp.setTextIsSelectable(true);
        et_alamatktp.setFocusable(false);
        et_alamatktp.setInputType(InputType.TYPE_NULL);
        et_alamatktp.setKeyListener(null);

        et_rtktp.setText(dataLengkap.getRtId());
        et_rtktp.setTextIsSelectable(true);
        et_rtktp.setFocusable(false);
        et_rtktp.setInputType(InputType.TYPE_NULL);
        et_rtktp.setKeyListener(null);

        et_rwktp.setText(dataLengkap.getRwId());
        et_rwktp.setTextIsSelectable(true);
        et_rwktp.setFocusable(false);
        et_rwktp.setInputType(InputType.TYPE_NULL);
        et_rwktp.setKeyListener(null);

        et_provinsiktp.setText(dataLengkap.getProvId());
        et_provinsiktp.setTextIsSelectable(true);
        et_provinsiktp.setFocusable(false);
        et_provinsiktp.setInputType(InputType.TYPE_NULL);
        et_provinsiktp.setKeyListener(null);

        et_kotaktp.setText(dataLengkap.getKotaId());
        et_kotaktp.setTextIsSelectable(true);
        et_kotaktp.setFocusable(false);
        et_kotaktp.setInputType(InputType.TYPE_NULL);
        et_kotaktp.setKeyListener(null);

        et_kecamatanktp.setText(dataLengkap.getKecId());
        et_kecamatanktp.setTextIsSelectable(true);
        et_kecamatanktp.setFocusable(false);
        et_kecamatanktp.setInputType(InputType.TYPE_NULL);
        et_kecamatanktp.setKeyListener(null);

        et_kelurahanktp.setText(dataLengkap.getKelId());
        et_kelurahanktp.setTextIsSelectable(true);
        et_kelurahanktp.setFocusable(false);
        et_kelurahanktp.setInputType(InputType.TYPE_NULL);
        et_kelurahanktp.setKeyListener(null);

        et_kodeposktp.setText(dataLengkap.getKodePosId());
        et_kodeposktp.setTextIsSelectable(true);
        et_kodeposktp.setFocusable(false);
        et_kodeposktp.setInputType(InputType.TYPE_NULL);
        et_kodeposktp.setKeyListener(null);

        et_statusdom.setText(KeyValue.getKeyStatusTempatDomisili(dataLengkap.getStatusTptTinggal()));
        et_statusdom.setTextIsSelectable(true);
        et_statusdom.setFocusable(false);
        et_statusdom.setInputType(InputType.TYPE_NULL);
        et_statusdom.setKeyListener(null);

        et_lamadom.setText(String.valueOf(dataLengkap.getLamaMenetap()));
        et_lamadom.setTextIsSelectable(true);
        et_lamadom.setFocusable(false);
        et_lamadom.setInputType(InputType.TYPE_NULL);
        et_lamadom.setKeyListener(null);

        et_alamatdom.setText(dataLengkap.getAlamatDom());
        et_alamatdom.setTextIsSelectable(true);
        et_alamatdom.setFocusable(false);
        et_alamatdom.setInputType(InputType.TYPE_NULL);
        et_alamatdom.setKeyListener(null);

        et_rtdom.setText(dataLengkap.getRtDom());
        et_rtdom.setTextIsSelectable(true);
        et_rtdom.setFocusable(false);
        et_rtdom.setInputType(InputType.TYPE_NULL);
        et_rtdom.setKeyListener(null);

        et_rwdom.setText(dataLengkap.getRwDom());
        et_rwdom.setTextIsSelectable(true);
        et_rwdom.setFocusable(false);
        et_rwdom.setInputType(InputType.TYPE_NULL);
        et_rwdom.setKeyListener(null);

        et_provinsidom.setText(dataLengkap.getProvDom());
        et_provinsidom.setTextIsSelectable(true);
        et_provinsidom.setFocusable(false);
        et_provinsidom.setInputType(InputType.TYPE_NULL);
        et_provinsidom.setKeyListener(null);

        et_kotadom.setText(dataLengkap.getKotaDom());
        et_kotadom.setTextIsSelectable(true);
        et_kotadom.setFocusable(false);
        et_kotadom.setInputType(InputType.TYPE_NULL);
        et_kotadom.setKeyListener(null);

        et_kecamatandom.setText(dataLengkap.getKecDom());
        et_kecamatandom.setTextIsSelectable(true);
        et_kecamatandom.setFocusable(false);
        et_kecamatandom.setInputType(InputType.TYPE_NULL);
        et_kecamatandom.setKeyListener(null);

        et_kelurahandom.setText(dataLengkap.getKelDom());
        et_kelurahandom.setTextIsSelectable(true);
        et_kelurahandom.setFocusable(false);
        et_kelurahandom.setInputType(InputType.TYPE_NULL);
        et_kelurahandom.setKeyListener(null);

        et_kodeposdom.setText(dataLengkap.getKodePosDom());
        et_kodeposdom.setTextIsSelectable(true);
        et_kodeposdom.setFocusable(false);
        et_kodeposdom.setInputType(InputType.TYPE_NULL);
        et_kodeposdom.setKeyListener(null);

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
