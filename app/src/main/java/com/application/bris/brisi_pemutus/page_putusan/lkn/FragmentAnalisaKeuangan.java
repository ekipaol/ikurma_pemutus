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
import com.application.bris.brisi_pemutus.util.NumberTextWatcherForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.math.BigDecimal;
import java.math.RoundingMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAnalisaKeuangan extends Fragment implements Step{

    @BindView(R.id.tf_pendapatanusaha)
    TextFieldBoxes tf_pendapatanusaha;
    @BindView(R.id.et_pendapatanusaha)
    EditText et_pendapatanusaha;
    @BindView(R.id.tf_hargapokokpenjualanusaha)
    TextFieldBoxes tf_hargapokokpenjualanusaha;
    @BindView(R.id.et_hargapokokpenjualanusaha)
    EditText et_hargapokokpenjualanusaha;


    @BindView(R.id.tf_sewakontrakusaha)
    TextFieldBoxes tf_sewakontrakusaha;
    @BindView(R.id.et_sewakontrakusaha)
    EditText et_sewakontrakusaha;
    @BindView(R.id.tf_gajipegawaiusaha)
    TextFieldBoxes tf_gajipegawaiusaha;
    @BindView(R.id.et_gajipegawaiusaha)
    EditText et_gajipegawaiusaha;
    @BindView(R.id.tf_telponlistrikairusaha)
    TextFieldBoxes tf_telponlistrikairusaha;
    @BindView(R.id.et_telponlistrikairusaha)
    EditText et_telponlistrikairusaha;
    @BindView(R.id.tf_transportasiusaha)
    TextFieldBoxes tf_transportasiusaha;
    @BindView(R.id.et_transportasiusaha)
    EditText et_transportasiusaha;
    @BindView(R.id.tf_pengeluaranlainnyausaha)
    TextFieldBoxes tf_pengeluaranlainnyausaha;
    @BindView(R.id.et_pengeluaranlainnyausaha)
    EditText et_pengeluaranlainnyausaha;
    @BindView(R.id.tf_pengeluaranusaha)
    TextFieldBoxes tf_pengeluaranusaha;
    @BindView(R.id.et_pengeluaranusaha)
    EditText et_pengeluaranusaha;
    @BindView(R.id.tf_keuntunganusaha)
    TextFieldBoxes tf_keuntunganusaha;
    @BindView(R.id.et_keuntunganusaha)
    EditText et_keuntunganusaha;
    @BindView(R.id.tf_penghasilanlainnyausaha)
    TextFieldBoxes tf_penghasilanlainnyausaha;
    @BindView(R.id.et_penghasilanlainnyausaha)
    EditText et_penghasilanlainnyausaha;
    @BindView(R.id.tf_totalpenghasilan)
    TextFieldBoxes tf_totalpenghasilan;
    @BindView(R.id.et_totalpenghasilan)
    EditText et_totalpenghasilan;
    @BindView(R.id.tf_pajakretribusiusaha)
    TextFieldBoxes tf_pajakretribusiusaha;
    @BindView(R.id.et_pajakretribusiusaha)
    EditText et_pajakretribusiusaha;



    @BindView(R.id.tf_belanjarumahtanggart)
    TextFieldBoxes tf_belanjarumahtanggart;
    @BindView(R.id.et_belanjarumahtanggart)
    EditText et_belanjarumahtanggart;
    @BindView(R.id.tf_sewakontrakrt)
    TextFieldBoxes tf_sewakontrakrt;
    @BindView(R.id.et_sewakontrakrt)
    EditText et_sewakontrakrt;
    @BindView(R.id.tf_pendidikanrt)
    TextFieldBoxes tf_pendidikanrt;
    @BindView(R.id.et_pendidikanrt)
    EditText et_pendidikanrt;
    @BindView(R.id.tf_telponlistrikairrt)
    TextFieldBoxes tf_telponlistrikairrt;
    @BindView(R.id.et_telponlistrikairrt)
    EditText et_telponlistrikairrt;
    @BindView(R.id.tf_transportasirt)
    TextFieldBoxes tf_transportasirt;
    @BindView(R.id.et_transportasirt)
    EditText et_transportasirt;
    @BindView(R.id.tf_pengeluaranlainnyart)
    TextFieldBoxes tf_pengeluaranlainnyart;
    @BindView(R.id.et_pengeluaranlainnyart)
    EditText et_pengeluaranlainnyart;
    @BindView(R.id.tf_totalpengeluaranrt)
    TextFieldBoxes tf_totalpengeluaranrt;
    @BindView(R.id.et_totalpengeluaranrt)
    EditText et_totalpengeluaranrt;









    @BindView(R.id.tf_sisapenghasilan)
    TextFieldBoxes tf_sisapenghasilan;
    @BindView(R.id.et_sisapenghasilan)
    EditText et_sisapenghasilan;

    private DataLkn data;

    public static String val_pendapatanUsaha ="";
    public static String val_hargaPokokPenjualan ="";
    public static String val_sewaUsaha ="";
    public static String val_gajiPegawaiUsaha ="";
    public static String val_telponListrikAirUsaha ="";
    public static String val_transportasiUsaha ="";
    public static String val_pengeluaranLainnyaUsaha ="";
    public static String val_pengeluaranUsaha ="";
    public static String val_keuntunganUsaha ="";
    public static String val_penghasilanLainnya ="";
    public static String val_totalPenghasilan ="";
    public static String val_pajakRetribusi ="";
    public static String val_belanjaRT ="";
    public static String val_sewaRT ="";
    public static String val_pendidikanRT ="";
    public static String val_telponListrikAirRT ="";
    public static String val_transportasiRT ="";
    public static String val_pengeluaranLainnyaRT ="";
    public static String val_totalPengeluaranRT ="";
    public static String val_sisaPenghasilan ="";

    public static BigDecimal valBd_hargapokokPenjualan = new BigDecimal(0);
    public static BigDecimal valBd_pendapatanUsaha = new BigDecimal(0);
    public static BigDecimal valBd_grossProfitMargin = new BigDecimal(0);



    public FragmentAnalisaKeuangan() {
    }

    public FragmentAnalisaKeuangan(DataLkn mdata) {
        data = mdata;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_analisakeuangan, container, false);
        ButterKnife.bind(this, view);
        disableTextfield();

        setData();

        return view;
    }

    private void setData(){
        et_pendapatanusaha.addTextChangedListener(new NumberTextWatcherForThousand(et_pendapatanusaha));
        et_pendapatanusaha.setText(data.getpENDAPATANUSAHA());

        et_hargapokokpenjualanusaha.addTextChangedListener(new NumberTextWatcherForThousand(et_hargapokokpenjualanusaha));
        et_hargapokokpenjualanusaha.setText(data.gethARGAPOKOKPENJUALAN());

        et_sewakontrakusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_sewakontrakusaha));
        et_sewakontrakusaha.setText(data.gethARGASEWA());

        et_gajipegawaiusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_gajipegawaiusaha));
        et_gajipegawaiusaha.setText(data.getgAJIPEGAWAI());

        et_telponlistrikairusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_telponlistrikairusaha));
        et_telponlistrikairusaha.setText(data.bIAYATELEPONLISTRIK);

        et_transportasiusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_transportasiusaha));
        et_transportasiusaha.setText(data.getbIAYATRANSPORTASI());

        et_pengeluaranlainnyausaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pengeluaranlainnyausaha));
        et_pengeluaranlainnyausaha.setText(data.getpENGELUARANLAINNYA());

        et_pengeluaranusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pengeluaranusaha));
        et_pengeluaranusaha.setText(data.getpENGELUARANUSAHA());



        et_keuntunganusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_keuntunganusaha));
        et_keuntunganusaha.setText(data.getkEUNTUNGANUSAHA());


        et_penghasilanlainnyausaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_penghasilanlainnyausaha));
        et_penghasilanlainnyausaha.setText(data.getpENGHASILANLAINNYA());


        et_totalpenghasilan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_totalpenghasilan));
        et_totalpenghasilan.setText(data.gettOTALPENGHASILAN());


        et_pajakretribusiusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pajakretribusiusaha));
        et_pajakretribusiusaha.setText(data.getpAJAK());


        et_belanjarumahtanggart.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_belanjarumahtanggart));
        et_belanjarumahtanggart.setText(data.getbELANJART());


        et_sewakontrakrt.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_sewakontrakrt));
        et_sewakontrakrt.setText(data.getbIAYASEWARUMAHRT());


        et_pendidikanrt.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pendidikanrt));
        et_pendidikanrt.setText(data.getbIAYAPENDIDIKAN());


        et_telponlistrikairrt.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_telponlistrikairrt));
        et_telponlistrikairrt.setText(data.getbIAYATELEPONRT());


        et_transportasirt.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_transportasirt));
        et_transportasirt.setText(data.getbIAYATRANSPORTASIRT());


        et_pengeluaranlainnyart.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pengeluaranlainnyart));
        et_pengeluaranlainnyart.setText(data.getpENGELUARANLAINNYART());


        et_totalpengeluaranrt.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_totalpengeluaranrt));
        et_totalpengeluaranrt.setText(data.gettOTALPENGELUARANRT());


        et_sisapenghasilan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_sisapenghasilan));
        et_sisapenghasilan.setText(data.getsISAPENGHASILAN());
    }



    private void disableTextfield(){
        et_pengeluaranusaha.setInputType(InputType.TYPE_NULL);
        et_pengeluaranusaha.setFocusable(false);
        et_keuntunganusaha.setInputType(InputType.TYPE_NULL);
        et_keuntunganusaha.setFocusable(false);
        et_totalpenghasilan.setInputType(InputType.TYPE_NULL);
        et_totalpenghasilan.setFocusable(false);
        et_totalpengeluaranrt.setInputType(InputType.TYPE_NULL);
        et_totalpengeluaranrt.setFocusable(false);
        et_sisapenghasilan.setInputType(InputType.TYPE_NULL);
        et_sisapenghasilan.setFocusable(false);
        et_hargapokokpenjualanusaha.setInputType(InputType.TYPE_NULL);
        et_hargapokokpenjualanusaha.setFocusable(false);
        et_pendapatanusaha.setInputType(InputType.TYPE_NULL);
        et_pendapatanusaha.setFocusable(false);
        et_sewakontrakusaha.setInputType(InputType.TYPE_NULL);
        et_sewakontrakusaha.setFocusable(false);
        et_gajipegawaiusaha.setInputType(InputType.TYPE_NULL);
        et_gajipegawaiusaha.setFocusable(false);
        et_telponlistrikairusaha.setInputType(InputType.TYPE_NULL);
        et_telponlistrikairusaha.setFocusable(false);
        et_transportasiusaha.setInputType(InputType.TYPE_NULL);
        et_transportasiusaha.setFocusable(false);
        et_pengeluaranlainnyausaha.setInputType(InputType.TYPE_NULL);
        et_pengeluaranlainnyausaha.setFocusable(false);
        et_penghasilanlainnyausaha.setInputType(InputType.TYPE_NULL);
        et_penghasilanlainnyausaha.setFocusable(false);
        et_pajakretribusiusaha.setInputType(InputType.TYPE_NULL);
        et_pajakretribusiusaha.setFocusable(false);
        et_belanjarumahtanggart.setInputType(InputType.TYPE_NULL);
        et_belanjarumahtanggart.setFocusable(false);
        et_sewakontrakrt.setInputType(InputType.TYPE_NULL);
        et_sewakontrakrt.setFocusable(false);
        et_pendidikanrt.setInputType(InputType.TYPE_NULL);
        et_pendidikanrt.setFocusable(false);
        et_telponlistrikairrt.setInputType(InputType.TYPE_NULL);
        et_telponlistrikairrt.setFocusable(false);
        et_transportasirt.setInputType(InputType.TYPE_NULL);
        et_transportasirt.setFocusable(false);
        et_pengeluaranlainnyart.setInputType(InputType.TYPE_NULL);
        et_pengeluaranlainnyart.setFocusable(false);


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
