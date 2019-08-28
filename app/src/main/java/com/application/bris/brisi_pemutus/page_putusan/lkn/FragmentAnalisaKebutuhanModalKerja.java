package com.application.bris.brisi_pemutus.page_putusan.lkn;


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
import android.widget.EditText;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.lkn.DataLkn;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.NumberTextWatcherCanNolForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.math.BigDecimal;
import java.math.RoundingMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAnalisaKebutuhanModalKerja extends Fragment implements Step{


    //STEP 1
    @BindView(R.id.tf_persediaaninventori1)
    TextFieldBoxes tf_persediaaninventori1;
    @BindView(R.id.et_persediaaninventori1)
    EditText et_persediaaninventori1;
    @BindView(R.id.tf_dohpersediaaninventori1)
    TextFieldBoxes tf_dohpersediaaninventori1;
    @BindView(R.id.et_dohpersediaaninventori1)
    EditText et_dohpersediaaninventori1;
    @BindView(R.id.tf_piutangdagang1)
    TextFieldBoxes tf_piutangdagang1;
    @BindView(R.id.et_piutangdagang1)
    EditText et_piutangdagang1;
    @BindView(R.id.tf_dohpiutangdagang1)
    TextFieldBoxes tf_dohpiutangdagang1;
    @BindView(R.id.et_dohpiutangdagang1)
    EditText et_dohpiutangdagang1;
    @BindView(R.id.tf_utangdagang1)
    TextFieldBoxes tf_utangdagang1;
    @BindView(R.id.et_utangdagang1)
    EditText et_utangdagang1;
    @BindView(R.id.tf_dohutangdagang1)
    TextFieldBoxes tf_dohutangdagang1;
    @BindView(R.id.et_dohutangdagang1)
    EditText et_dohutangdagang1;
    @BindView(R.id.tf_wineraca)
    TextFieldBoxes tf_wineraca;
    @BindView(R.id.et_wineraca)
    EditText et_wineraca;
    @BindView(R.id.tf_grossprofitmargin)
    TextFieldBoxes tf_grossprofitmargin;
    @BindView(R.id.et_grossprofitmargin)
    EditText et_grossprofitmargin;


    //STEP 2
    @BindView(R.id.tf_persediaaninventori2)
    TextFieldBoxes tf_persediaaninventori2;
    @BindView(R.id.et_persediaaninventori2)
    EditText et_persediaaninventori2;
    @BindView(R.id.tf_piutangdagang2)
    TextFieldBoxes tf_piutangdagang2;
    @BindView(R.id.et_piutangdagang2)
    EditText et_piutangdagang2;
    @BindView(R.id.tf_utangdagang2)
    TextFieldBoxes tf_utangdagang2;
    @BindView(R.id.et_utangdagang2)
    EditText et_utangdagang2;
    @BindView(R.id.tf_dohmodalkerja)
    TextFieldBoxes tf_dohmodalkerja;
    @BindView(R.id.et_dohmodalkerja)
    EditText et_dohmodalkerja;
    @BindView(R.id.tf_winormal)
    TextFieldBoxes tf_winormal;
    @BindView(R.id.et_winormal)
    EditText et_winormal;

    private DataLkn data;

    public static String val_persediaanInventori1 ="";
    public static String val_piutangDagang1 ="";
    public static String val_utangDagang1 ="";
    public static String val_wiNeraca ="";
    public static String val_dohPersediaanInventori ="";
    public static String val_dohPiutangDagang ="";
    public static String val_dohUtangDagang ="";
    public static String val_grossProfitMargin ="";
    public static String val_persediaanInventori2 ="";
    public static String val_piutangDagang2 ="";
    public static String val_utangDagang2 ="";
    public static String val_dohKebutuhanModalKerja ="";
    public static String val_wiNormal ="";


    public FragmentAnalisaKebutuhanModalKerja() {
    }

    public FragmentAnalisaKebutuhanModalKerja(DataLkn mdata) {
        data = mdata;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_analisakebutuhanmodalkerja, container, false);
        ButterKnife.bind(this, view);
        disableTextfield();
        setData();

        return view;
    }

    private void setData(){
        et_persediaaninventori1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_persediaaninventori1));
        et_persediaaninventori1.setText(data.getiNVENTORY());

        et_piutangdagang1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_piutangdagang1));
        et_piutangdagang1.setText(data.getpIUTANGDAGANG());

        et_utangdagang1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_utangdagang1));
        et_utangdagang1.setText(data.getuTANGDAGANG());

        et_wineraca.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_wineraca));
        et_wineraca.setText(data.getwINERACA());

        et_dohpersediaaninventori1.setText(String.valueOf(data.getdOHINVENTORY()));
        et_dohpiutangdagang1.setText(String.valueOf(data.getdOHPIUTANG()));
        et_dohutangdagang1.setText(String.valueOf(data.getdOHUTANG()));
        et_grossprofitmargin.setText(data.getgPM());
        et_dohmodalkerja.setText(Long.toString(data.getkEBUTUHANMODAL()));
        et_persediaaninventori2.setText(String.valueOf(data.getpERPUTARANPERSEDIAAN()));
        et_piutangdagang2.setText(String.valueOf(data.getpERPUTARANPIUTANG()));
        et_utangdagang2.setText(String.valueOf(data.getpERPUTARANUTANG()));
        et_persediaaninventori2.setText(String.valueOf(data.getpERPUTARANPERSEDIAAN()));


        et_winormal.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_winormal));
        et_winormal.setText(data.getwINORMAL());
    }


//    private void setValue(){
//        et_grossprofitmargin.setText(NumberTextWatcherCanNolForThousand.getDecimalFormat(String.valueOf(FragmentAnalisaKeuangan.valBd_grossProfitMargin))); //SET WI NERACA
//    }



    private void disableTextfield(){
        et_wineraca.setInputType(InputType.TYPE_NULL);
        et_wineraca.setFocusable(false);
        et_dohpersediaaninventori1.setInputType(InputType.TYPE_NULL);
        et_dohpersediaaninventori1.setFocusable(false);
        et_dohpiutangdagang1.setInputType(InputType.TYPE_NULL);
        et_dohpiutangdagang1.setFocusable(false);
        et_dohutangdagang1.setInputType(InputType.TYPE_NULL);
        et_dohutangdagang1.setFocusable(false);
        et_grossprofitmargin.setInputType(InputType.TYPE_NULL);
        et_grossprofitmargin.setFocusable(false);
        et_dohmodalkerja.setInputType(InputType.TYPE_NULL);
        et_dohmodalkerja.setFocusable(false);
        et_winormal.setInputType(InputType.TYPE_NULL);
        et_winormal.setFocusable(false);
        et_persediaaninventori1.setInputType(InputType.TYPE_NULL);
        et_persediaaninventori1.setFocusable(false);
        et_piutangdagang1.setInputType(InputType.TYPE_NULL);
        et_piutangdagang1.setFocusable(false);
        et_utangdagang1.setInputType(InputType.TYPE_NULL);
        et_utangdagang1.setFocusable(false);
        et_persediaaninventori2.setInputType(InputType.TYPE_NULL);
        et_persediaaninventori2.setFocusable(false);
        et_piutangdagang2.setInputType(InputType.TYPE_NULL);
        et_piutangdagang2.setFocusable(false);
        et_utangdagang2.setInputType(InputType.TYPE_NULL);
        et_utangdagang2.setFocusable(false);
        et_persediaaninventori2.setInputType(InputType.TYPE_NULL);
        et_persediaaninventori2.setFocusable(false);
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

    private void disableTextFocus(ExtendedEditText et){
        et.setFocusable(false);
        et.setInputType(InputType.TYPE_NULL);
        et.setKeyListener(null);
    }


}

