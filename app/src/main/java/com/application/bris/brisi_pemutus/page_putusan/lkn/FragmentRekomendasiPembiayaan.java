package com.application.bris.brisi_pemutus.page_putusan.lkn;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.lkn.DataLkn;
import com.application.bris.brisi_pemutus.model.lkn.DataRekomendasiLkn;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.NumberTextWatcherCanNolForThousand;
import com.application.bris.brisi_pemutus.util.NumberTextWatcherForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentRekomendasiPembiayaan extends Fragment implements Step{

    @BindView(R.id.tv_tujuanpembiayaan)
    TextView tv_tujuanpembiayaan;
    @BindView(R.id.tv_plafondinduk)
    TextView tv_plafondinduk;
    @BindView(R.id.tv_tenor)
    TextView tv_tenor;
    @BindView(R.id.tf_nilaimodalkerja)
    TextFieldBoxes tf_nilaimodalkerja;
    @BindView(R.id.et_nilaimodalkerja)
    EditText et_nilaimodalkerja;
    @BindView(R.id.tf_waktumodalkerja)
    TextFieldBoxes tf_waktumodalkerja;
    @BindView(R.id.et_waktumodalkerja)
    EditText et_waktumodalkerja;
    @BindView(R.id.tf_nilaiinvestasi)
    TextFieldBoxes tf_nilaiinvestasi;
    @BindView(R.id.et_nilaiinvestasi)
    EditText et_nilaiinvestasi;
    @BindView(R.id.tf_waktuinvestasi)
    TextFieldBoxes tf_waktuinvestasi;
    @BindView(R.id.et_waktuinvestasi)
    EditText et_waktuinvestasi;
    @BindView(R.id.tf_nilaikonsumtif)
    TextFieldBoxes tf_nilaikonsumtif;
    @BindView(R.id.et_nilaikonsumtif)
    EditText et_nilaikonsumtif;
    @BindView(R.id.tf_waktukonsumtif)
    TextFieldBoxes tf_waktukonsumtif;
    @BindView(R.id.et_waktukonsumtif)
    EditText et_waktukonsumtif;
    @BindView(R.id.tf_nilaitakeover)
    TextFieldBoxes tf_nilaitakeover;
    @BindView(R.id.et_nilaitakeover)
    EditText et_nilaitakeover;
    @BindView(R.id.tf_waktutakeover)
    TextFieldBoxes tf_waktutakeover;
    @BindView(R.id.et_waktutakeover)
    EditText et_waktutakeover;
    @BindView(R.id.tf_waktuqardh)
    TextFieldBoxes tf_waktuqardh;
    @BindView(R.id.et_waktuqardh)
    EditText et_waktuqardh;
    @BindView(R.id.tf_totalrekomendasikomite)
    TextFieldBoxes tf_totalrekomendasikomite;
    @BindView(R.id.et_totalrekomendasikomite)
    EditText et_totalrekomendasikomite;


    @BindView(R.id.tf_angsuranpinjamansaatini)
    TextFieldBoxes tf_angsuranpinjamansaatini;
    @BindView(R.id.et_angsuranpinjamansaatini)
    EditText et_angsuranpinjamansaatini;

    @BindView(R.id.tf_totaleksposur)
    TextFieldBoxes tf_totaleksposur;
    @BindView(R.id.et_totaleksposur)
    EditText et_totaleksposur;


    @BindView(R.id.tf_margin)
    TextFieldBoxes tf_margin;
    @BindView(R.id.et_margin)
    EditText et_margin;
    @BindView(R.id.tf_jenisangsuran)
    TextFieldBoxes tf_jenisangsuran;
    @BindView(R.id.et_jenisangsuran)
    EditText et_jenisangsuran;
    @BindView(R.id.tf_intervaljenisangsuran)
    TextFieldBoxes tf_intervaljenisangsuran;
    @BindView(R.id.et_intervaljenisangsuran)
    EditText et_intervaljenisangsuran;
    @BindView(R.id.ll_btn_cekrekomendasi)
    LinearLayout ll_btn_cekrekomendasi;
    @BindView(R.id.btn_cekrekomendasi)
    Button btn_cekrekomendasi;
    @BindView(R.id.ll_hasilrekomendasipembiayaan)
    LinearLayout ll_hasilrekomendasipembiayaan;
    @BindView(R.id.tv_rekomendasiangsuran)
    TextView tv_rekomendasiangsuran;
    @BindView(R.id.tv_disposableincome)
    TextView tv_disposableincome;
    @BindView(R.id.tv_idir)
    TextView tv_idir;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    private DataLkn data;
    private DataRekomendasiLkn dataRekomendasiLkn;

    public static String val_modalKerja ="";
    public static String val_investasi ="";
    public static String val_konsumtif ="";
    public static String val_takeover ="";
    public static String val_jwModalKerja ="";
    public static String val_jwInvestasi ="";
    public static String val_jwKonsumtif ="";
    public static String val_jwTakeover ="";
    public static String val_qardh ="";
    public static String val_totalRekomendasiKomite ="";
    public static String val_angsuranSaatIni ="";
    public static String val_totalEksposur ="";
    public static String val_margin ="";
    public static String val_jenisAngsuran ="";
    public static String val_intervalJenisAngsuran ="";
    public static String val_rekomendasiAngsuran ="";
    public static String val_disposableIncome ="";
    public static String val_idir ="";
    public static String val_marginModalKerja ="";
    public static String val_marginInvestasi ="";
    public static String val_marginKonsumtif ="";
    public static String val_marginTakeover ="";

    private String dataCekRekomendasiString;
//    private CekRekomendasi dataCekRekomendasi;


    public static boolean flagCekRekomendasi = false;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    public FragmentRekomendasiPembiayaan() {
    }

    public FragmentRekomendasiPembiayaan(DataLkn mdata) {
        data = mdata;
    }
    public FragmentRekomendasiPembiayaan(DataLkn mdata,DataRekomendasiLkn mDataRekomendasi) {
        data = mdata;
        dataRekomendasiLkn=mDataRekomendasi;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_rekomendasipembiayaan, container, false);
        ButterKnife.bind(this, view);
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());
        disableTextfield();



        tv_tujuanpembiayaan.setText(LknActivity.tujuanPembiayaan);
        tv_plafondinduk.setText(AppUtil.parseRupiah((LknActivity.plafond)));
        tv_tenor.setText(Integer.toString(LknActivity.jw)+" Bulan");
        et_jenisangsuran.setText("Reguler");
        et_intervaljenisangsuran.setText("");

//        setValue();


        disableTextfield();
        setData();

        return view;
    }



    private void setData(){

        et_totalrekomendasikomite.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_totalrekomendasikomite));
        et_totalrekomendasikomite.setText(data.gettOTALPEMBIAYAAN());


        et_nilaimodalkerja.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilaimodalkerja));
        et_nilaimodalkerja.setText(data.getpEMBIAYAANPRODUKTIF1());

        et_nilaiinvestasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilaiinvestasi));
        et_nilaiinvestasi.setText(data.getpEMBIAYAANPRODUKTIF2());

        et_nilaikonsumtif.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilaikonsumtif));
        et_nilaikonsumtif.setText(data.getpEMBIAYAANKONSUMTIF());

        et_nilaitakeover.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilaitakeover));
        et_nilaitakeover.setText(data.getpEMBIAYAANTAKEOVER());

        et_waktumodalkerja.setText(data.getjWPRODUKTIF1());
        et_waktuinvestasi.setText(data.getjWPRODUKTIF2());
        et_waktukonsumtif.setText(data.getjWKONSUMTIF());
        et_waktutakeover.setText(data.getjWTAKEOVER());
        et_waktuqardh.setText(data.getjATUHTEMPOQARDH());
//        et_totalrekomendasikomite.setText(data.gettOTALPEMBIAYAAN());
//        et_angsuranpinjamansaatini.setText(String.valueOf(data.getaNGSURANALL()));
//        et_totaleksposur.setText(String.valueOf(data.gettOTALEKSPOSUREBRIS()));
        et_margin.setText(data.getmARGINFLATBULAN());
//        tv_rekomendasiangsuran.setText(data.getrEKOMENDASIPUTUSAN());
        et_totaleksposur.addTextChangedListener(new NumberTextWatcherForThousand(et_totaleksposur));
        et_totaleksposur.setText(data.gettOTALEKSPOSUREBRIS().toString());

        et_angsuranpinjamansaatini.addTextChangedListener(new NumberTextWatcherForThousand(et_angsuranpinjamansaatini));
        et_angsuranpinjamansaatini.setText(Long.toString(data.getaNGSURANALL()));


        //hasil rekomendasi
//        Log.d("rekomendasiLKN",dataRekomendasiLkn.getIDIR().toString());
        tv_idir.setText((dataRekomendasiLkn.getIDIR()+"%"));
      tv_disposableincome.setText(AppUtil.parseRupiah(dataRekomendasiLkn.getDISPOSABLE_INCOME()));
        tv_rekomendasiangsuran.setText(AppUtil.parseRupiah(dataRekomendasiLkn.getREKOMENDASI_ANGSURAN()));

    }

    private void disableTextfield(){
        et_totalrekomendasikomite.setInputType(InputType.TYPE_NULL);
        et_totalrekomendasikomite.setFocusable(false);
        et_angsuranpinjamansaatini.setInputType(InputType.TYPE_NULL);
        et_angsuranpinjamansaatini.setFocusable(false);
        et_totaleksposur.setInputType(InputType.TYPE_NULL);
        et_totaleksposur.setFocusable(false);
        et_jenisangsuran.setInputType(InputType.TYPE_NULL);
        et_jenisangsuran.setFocusable(false);
        et_intervaljenisangsuran.setInputType(InputType.TYPE_NULL);
        et_intervaljenisangsuran.setFocusable(false);
        et_nilaimodalkerja.setInputType(InputType.TYPE_NULL);
        et_nilaimodalkerja.setFocusable(false);
        et_nilaiinvestasi.setInputType(InputType.TYPE_NULL);
        et_nilaiinvestasi.setFocusable(false);
        et_nilaikonsumtif.setInputType(InputType.TYPE_NULL);
        et_nilaikonsumtif.setFocusable(false);
        et_nilaitakeover.setInputType(InputType.TYPE_NULL);
        et_nilaitakeover.setFocusable(false);
        et_waktumodalkerja.setInputType(InputType.TYPE_NULL);
        et_waktumodalkerja.setFocusable(false);
        et_waktuinvestasi.setInputType(InputType.TYPE_NULL);
        et_waktuinvestasi.setFocusable(false);
        et_waktukonsumtif.setInputType(InputType.TYPE_NULL);
        et_waktukonsumtif.setFocusable(false);
        et_waktutakeover.setInputType(InputType.TYPE_NULL);
        et_waktutakeover.setFocusable(false);
        et_waktuqardh.setInputType(InputType.TYPE_NULL);
        et_waktuqardh.setFocusable(false);

        et_margin.setInputType(InputType.TYPE_NULL);
        et_margin.setFocusable(false);
        tv_rekomendasiangsuran.setInputType(InputType.TYPE_NULL);
        tv_rekomendasiangsuran.setFocusable(false);
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
