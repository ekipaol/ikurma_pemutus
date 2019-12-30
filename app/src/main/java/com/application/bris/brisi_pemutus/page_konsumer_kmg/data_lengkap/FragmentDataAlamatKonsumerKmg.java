package com.application.bris.brisi_pemutus.page_konsumer_kmg.data_lengkap;


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
import com.application.bris.brisi_pemutus.listeners.KeyValueListener;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkap;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkapKonsumerKmg;
import com.application.bris.brisi_pemutus.page_putusan.agunan.DialogKeyValue;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.KeyValue;
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

    private Realm realm;

    private DataLengkapKonsumerKmg dataLengkap;

    private String val_AlamatId ="";
    private String val_RtId ="";
    private String val_RwId ="";
    private String val_ProvId ="";
    private String val_KotaId ="";
    private String val_KecId ="";
    private String val_KelId ="";
    private String val_KodePosId ="";
    private String val_StatusTptTinggal ="";
    private String val_LamaMenetap ;
    private String val_AlamatDom ="";
    private String val_RtDom ="";
    private String val_RwDom ="";
    private String val_ProvDom ="";
    private String val_KotaDom ="";
    private String val_KecDom ="";
    private String val_KelDom ="";
    private String val_KodePosDom ="";

    private int val_domAsId = 0;
    private String btnAddressString = "";

    public FragmentDataAlamatKonsumerKmg(DataLengkapKonsumerKmg mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_data_alamat_konsumer_kmg, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        sw_domisiliktpsama.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ll_alamatdom.setVisibility(View.GONE);
                    val_domAsId = 1;
                }
                else {
                    ll_alamatdom.setVisibility(View.VISIBLE);
                    val_domAsId = 0;
                }
            }
        });

        setDynamicIcon();
        setData();
        setDynamicIconDropDown();
        onSelectDialog();


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
    }

    private void onSelectDialog(){
        //STATUS DOMISILI
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
