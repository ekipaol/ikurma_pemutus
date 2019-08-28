package com.application.bris.brisi_pemutus.page_putusan.agunan;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import android.widget.Toast;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.listeners.KeyValueListener;
import com.application.bris.brisi_pemutus.model.agunan.Agunan;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkap;
import com.application.bris.brisi_pemutus.model.keyvalue.keyvalue;
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
public class FragmentAgunan1Identifikasi extends Fragment implements Step {



//VIEW BINDING
    @BindView(R.id.tf_bentuk_bidang_tanah)
     TextFieldBoxes tf_bentuk_bidang_tanah;
    @BindView(R.id.et_bentuk_bidang_tanah)
    ExtendedEditText et_bentuk_bidang_tanah;

    @BindView(R.id.tf_lokasi_tanah)
    TextFieldBoxes tf_lokasi_tanah;
    @BindView(R.id.et_lokasi_tanah)
    ExtendedEditText et_lokasi_tanah;

    @BindView(R.id.tf_kodepos_agunan)
    TextFieldBoxes tf_kodepos_agunan;
    @BindView(R.id.et_kodepos_agunan)
    ExtendedEditText et_kodepos_agunan;

    @BindView(R.id.tf_kelurahan_agunan)
    TextFieldBoxes tf_kelurahan_agunan;
    @BindView(R.id.et_kelurahan_agunan)
    ExtendedEditText et_kelurahan_agunan;

    @BindView(R.id.tf_kota_agunan)
    TextFieldBoxes tf_kota_agunan;
    @BindView(R.id.et_kota_agunan)
    ExtendedEditText et_kota_agunan;

    @BindView(R.id.tf_batas_utara)
    TextFieldBoxes tf_batas_utara;
    @BindView(R.id.et_batas_utara)
    ExtendedEditText et_batas_utara;

    @BindView(R.id.tf_batas_selatan)
    TextFieldBoxes tf_batas_selatan;
    @BindView(R.id.et_batas_selatan_tanah)
    ExtendedEditText et_batas_selatan_tanah;


    @BindView(R.id.tf_batas_barat)
    TextFieldBoxes tf_batas_barat;
    @BindView(R.id.et_batas_barat)
    ExtendedEditText et_batas_barat;

    @BindView(R.id.tf_batas_timur)
    TextFieldBoxes tf_batas_timur;
    @BindView(R.id.et_batas_timur)
    ExtendedEditText et_batas_timur;

    @BindView(R.id.tf_kav_blok)
    TextFieldBoxes tf_kav_blok;
    @BindView(R.id.et_kavblok)
    ExtendedEditText et_kavblok;

    @BindView(R.id.tf_rt_agunan)
    TextFieldBoxes tf_rt_agunan;
    @BindView(R.id.et_rt_agunan)
    ExtendedEditText et_rt_agunan;

    @BindView(R.id.tf_rw_agunan)
    TextFieldBoxes tf_rw_agunan;
    @BindView(R.id.et_rw_agunan)
    ExtendedEditText et_rw_agunan;


    @BindView(R.id.tf_kecamatan_agunan)
    TextFieldBoxes tf_kecamatan_agunan;
    @BindView(R.id.et_kecamatan_agunan)
    ExtendedEditText et_kecamatan_agunan;

    @BindView(R.id.tf_propinsi_agunan)
    TextFieldBoxes tf_propinsi_agunan;
    @BindView(R.id.et_propinsi_agunan)
    ExtendedEditText et_propinsi_agunan;

    @BindView(R.id.tf_permukaan_tanah)
    TextFieldBoxes tf_permukaan_tanah;
    @BindView(R.id.et_permukaan_tanah)
    ExtendedEditText et_permukaan_tanah;

    @BindView(R.id.btn_set_loc)
    Button btn_set_loc;

    //END OF VIEW BINDING







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



    public FragmentAgunan1Identifikasi() {
    }

    public FragmentAgunan1Identifikasi(Agunan mdataLengkap) {
        dataLengkap = mdataLengkap;
//        Log.d("TEST", "BISAAA");
//        Log.d("datalengkap",dataLengkap.getNAMAPENGHUNI());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_1_identifikasi, container, false);
        ButterKnife.bind(this,view);
        et_permukaan_tanah=view.findViewById(R.id.et_permukaan_tanah);
//        et_permukaan_tanah.setText(dataLengkap.getPERMUKAANTANAH());
        setData();
        btn_set_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //nampilin google maps berdasar kordinat
                //un-comment kalau sudah ada koordinat di inquiry agunan
//
                if(dataLengkap.getKoordinat()!=null){

                    //pake google maps tidak bisa, kalo pake yang dibawah, gak tampil titik tujuannya, di aplikasi lain kayak waze, grab dll bisa
//                    String uri = String.format(Locale.ENGLISH, "geo:"+dataLengkap.getKoordinat());
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                    startActivity(intent);

                    Uri gmmIntentUri = Uri.parse("google.navigation:q="+dataLengkap.getKoordinat());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }

                else if(dataLengkap.getKoordinat()==null){
                    Toast.makeText(getContext(), "Kordinat tidak ditemukan untuk aplikasi ini", Toast.LENGTH_SHORT).show();

                    //pantekan testing
//                    String uri = String.format(Locale.ENGLISH, "geo:-6.2348961333010955,106.82217959314586");
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(), "Belum ada data koordinat untuk agunan ini", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }

    private void setData(){
        disableTextFocus(et_bentuk_bidang_tanah);
        et_bentuk_bidang_tanah.setText(dataLengkap.getBentukTanah());

        disableTextFocus(et_lokasi_tanah);
        et_lokasi_tanah.setText(dataLengkap.getLokasiTanah());

        disableTextFocus(et_kodepos_agunan);
        et_kodepos_agunan.setText(dataLengkap.getKodePos());

        disableTextFocus(et_kelurahan_agunan);
        et_kelurahan_agunan.setText(dataLengkap.getKelurahan());

        disableTextFocus(et_kota_agunan);
        et_kota_agunan.setText(dataLengkap.getKota());

        disableTextFocus(et_batas_utara);
        et_batas_utara.setText(dataLengkap.getBatasUtaraTanah());

        disableTextFocus(et_batas_selatan_tanah);
        et_batas_selatan_tanah.setText(dataLengkap.getBatasSelatanTanah());

        disableTextFocus(et_batas_barat);
        et_batas_barat.setText(dataLengkap.getBatasBaratTanah());

        disableTextFocus(et_batas_timur);
        et_batas_timur.setText(dataLengkap.getBatasTimurTanah());

        disableTextFocus(et_kavblok);
        et_kavblok.setText(dataLengkap.getKavBlok());

        disableTextFocus(et_rt_agunan);
        et_rt_agunan.setText(dataLengkap.getRT());

        disableTextFocus(et_rw_agunan);
        et_rw_agunan.setText(dataLengkap.getRW());

        disableTextFocus(et_kecamatan_agunan);
        et_kecamatan_agunan.setText(dataLengkap.getKecamatan());

        disableTextFocus(et_propinsi_agunan);
        et_propinsi_agunan.setText(dataLengkap.getPropinsi());

        disableTextFocus(et_permukaan_tanah);
        et_permukaan_tanah.setText(dataLengkap.getPermukaanTanah());



    }


    private void disableTextFocus(ExtendedEditText et){
        et.setFocusable(false);
        et.setInputType(InputType.TYPE_NULL);
        et.setKeyListener(null);

    }

//    private void openKeyValueDialog(String title){
//        DialogKeyValue.display(getFragmentManager(), title, this);
//    }


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

//    @Override
//    public void onKeyValueSelect(String title, keyvalue data) {
//        if (title.equalsIgnoreCase("bentuk bidang tanah")){
//            et_bentuk_bidang_tanah.setText(data.getName());
//        }
//        else if (title.equalsIgnoreCase("permukaan tanah")){
//            et_permukaan_tanah.setText(data.getName());
//        }
//    }
}

