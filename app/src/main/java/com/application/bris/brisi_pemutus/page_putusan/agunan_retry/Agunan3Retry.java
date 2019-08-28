package com.application.bris.brisi_pemutus.page_putusan.agunan_retry;




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
import com.application.bris.brisi_pemutus.listeners.KeyValueListener;
import com.application.bris.brisi_pemutus.model.agunan.Agunan;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkap;
import com.application.bris.brisi_pemutus.model.keyvalue.keyvalue;
import com.application.bris.brisi_pemutus.page_putusan.agunan.DialogKeyValue;
import com.application.bris.brisi_pemutus.util.AppUtil;
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
public class Agunan3Retry extends Fragment implements Step, KeyValueListener {


    @BindView(R.id.tf_jenis_bangunan)
    TextFieldBoxes tf_jenis_bangunan;
    @BindView(R.id.et_jenis_bangunan)
    ExtendedEditText et_jenis_bangunan;

    @BindView(R.id.tf_lokasi_bangunan)
    TextFieldBoxes tf_lokasi_bangunan;
    @BindView(R.id.et_lokasi_bangunan)
    ExtendedEditText et_lokasi_bangunan;

    @BindView(R.id.tf_no_imb)
    TextFieldBoxes tf_no_imb;
    @BindView(R.id.et_no_imb)
    ExtendedEditText et_no_imb;

    @BindView(R.id.tf_alamat_imb)
    TextFieldBoxes tf_alamat_imb;
    @BindView(R.id.et_alamat_imb)
    ExtendedEditText et_alamat_imb;

    @BindView(R.id.tf_jenis_agunan_xbrl)
    TextFieldBoxes tf_jenis_agunan_xbrl;
    @BindView(R.id.et_jenis_agunan_xbrl)
    ExtendedEditText et_jenis_agunan_xbrl;

    @BindView(R.id.tf_nama_penghuni)
    TextFieldBoxes tf_nama_penghuni;
    @BindView(R.id.et_nama_penghuni)
    ExtendedEditText et_nama_penghuni;

    @BindView(R.id.tf_status_penghuni)
    TextFieldBoxes tf_status_penghuni;
    @BindView(R.id.et_status_penghuni)
    ExtendedEditText et_status_penghuni;

    @BindView(R.id.tf_hub_penghuni_pemegang_hak)
    TextFieldBoxes tf_hub_penghuni_pemegang_hak;
    @BindView(R.id.et_hub_penghuni_pemegang_hak)
    ExtendedEditText et_hub_penghuni_pemegang_hak;

    @BindView(R.id.tf_jumlah_lantai)
    TextFieldBoxes tf_jumlah_lantai;
    @BindView(R.id.et_jumlah_lantai)
    ExtendedEditText et_jumlah_lantai;

    @BindView(R.id.tf_atas_nama_ijin)
    TextFieldBoxes tf_atas_nama_ijin;
    @BindView(R.id.et_atas_nama_ijin)
    ExtendedEditText et_atas_nama_ijin;


    @BindView(R.id.tf_tahun_mendirikan)
    TextFieldBoxes tf_tahun_mendirikan;
    @BindView(R.id.et_tahun_mendirikan)
    ExtendedEditText et_tahun_mendirikan;



    private Calendar calLahir;
    private Calendar calLahirPasangan;
    private Calendar calExpiredNik;

    private DatePickerDialog dpTanggalLahir;
    private DatePickerDialog dpTanggalLahirPasangan;
    private DatePickerDialog dpExpiredNik;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    public static SimpleDateFormat dateServer = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private Agunan dataAgunan;
    private DataLengkap dataLengkapContoh;

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

String agunanMbakMini;

    public Agunan3Retry() {
    }

    public Agunan3Retry(DataLengkap mdataLengkap) {
        dataLengkapContoh = mdataLengkap;
    }

    public Agunan3Retry(Agunan mdataLengkap) {
        dataAgunan = mdataLengkap;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_3_uraian_bangunan, container, false);
        ButterKnife.bind(this, view);
         setData();
     //   Log.d("kota dom ekipaol",dataLengkapContoh.getKotaDom());
//        et_alamat_imb.setText(dataAgunan.getKodePos());
//        et_lokasi_bangunan.setText(dataAgunan.getLokasiTanah());


        return view;
    }

    private void setData(){

        disableTextFocus(et_jenis_bangunan);
        et_jenis_bangunan.setText(KeyValue.getKeyJenisBangunan(dataAgunan.getJenisBangunan()));

        disableTextFocus(et_lokasi_bangunan);
        et_lokasi_bangunan.setText(dataAgunan.getLokasiBangunanBrins());

        disableTextFocus(et_no_imb);
        et_no_imb.setText(dataAgunan.getNoIMB());

        disableTextFocus(et_alamat_imb);
        et_alamat_imb.setText(dataAgunan.getAlamatIMB());

        disableTextFocus(et_jenis_agunan_xbrl);
        et_jenis_agunan_xbrl.setText(KeyValue.getKeyJenisAgunanXBRL(dataAgunan.getJenisAgunanXBRL()));

        disableTextFocus(et_nama_penghuni);
        et_nama_penghuni.setText(dataAgunan.getNamaPenghuni());

        disableTextFocus(et_status_penghuni);
        et_status_penghuni.setText(dataAgunan.getStatusPenghuni());

        disableTextFocus(et_hub_penghuni_pemegang_hak);
        et_hub_penghuni_pemegang_hak.setText(KeyValue.getKeyHubPenghuniDenganPemegangHak(dataAgunan.getHubPenghuniDgPemilik()));

        disableTextFocus(et_jumlah_lantai);
        et_jumlah_lantai.setText(dataAgunan.getTingkat());

        disableTextFocus(et_atas_nama_ijin);
        et_atas_nama_ijin.setText(dataAgunan.getAtasNamaIMB());


        disableTextFocus(et_tahun_mendirikan);
        et_tahun_mendirikan.setText(dataAgunan.getTahunBangunan());


    }

//    private void setData(){
//
//        disableTextFocus(et_jenis_bangunan);
//        et_jenis_bangunan.setText(dataLengkap.getJenisBangunan());
//
//        disableTextFocus(et_lokasi_bangunan);
//        et_lokasi_bangunan.setText(dataLengkap.getLokasiBangunanBrins());
//
//        disableTextFocus(et_no_imb);
//        et_no_imb.setText(dataLengkap.getNoIMB());
//
//        disableTextFocus(et_alamat_imb);
//        et_alamat_imb.setText(dataLengkap.getAlamatIMB());
//
//        disableTextFocus(et_jenis_agunan_xbrl);
//        et_jenis_agunan_xbrl.setText(dataLengkap.getJenisAgunanXBRL());
//
//        disableTextFocus(et_nama_penghuni);
//        et_nama_penghuni.setText(dataLengkap.getNamaPenghuni());
//
//        disableTextFocus(et_status_penghuni);
//        et_status_penghuni.setText(dataLengkap.getStatusPenghuni());
//
//        disableTextFocus(et_hub_penghuni_pemegang_hak);
//        et_hub_penghuni_pemegang_hak.setText(dataLengkap.getHubNasabahDgnPemegangHak());
//
//        disableTextFocus(et_jumlah_lantai);
//        et_jumlah_lantai.setText(dataLengkap.getLantai());
//
//        disableTextFocus(et_atas_nama_ijin);
//        et_atas_nama_ijin.setText(dataLengkap.getAtasNamaIMB());
//
//
//        disableTextFocus(et_tahun_mendirikan);
//        et_tahun_mendirikan.setText(dataLengkap.getTahunBangunan());
//
//
//
//
//    }

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

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("jenis bangunan")){
            et_jenis_bangunan.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("lokasi bangunan")){
            et_lokasi_bangunan.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("hub penghuni dengan pemegang hak")){
            et_hub_penghuni_pemegang_hak.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("jenis agunan xbrl")){
            et_jenis_agunan_xbrl.setText(data.getName());
        }

    }
}


