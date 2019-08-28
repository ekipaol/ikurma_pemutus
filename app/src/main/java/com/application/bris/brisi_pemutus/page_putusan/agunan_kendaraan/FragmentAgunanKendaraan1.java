package com.application.bris.brisi_pemutus.page_putusan.agunan_kendaraan;


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

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.agunan_kendaraan.AgunanKendaraan;
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
 * A simple {@link Fragment} subclass.
 */

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunanKendaraan1 extends Fragment implements Step {

    //VIEW BINDING
    @BindView(R.id.tf_tanggal_pemeriksaan)
    TextFieldBoxes tf_tanggal_pemeriksaan;
    @BindView(R.id.et_tanggal_pemeriksaan)
    ExtendedEditText et_tanggal_pemeriksaan;

    @BindView(R.id.tf_jenis_kendaraan)
    TextFieldBoxes tf_jenis_kendaraan;
    @BindView(R.id.et_jenis_kendaraan)
    ExtendedEditText et_jenis_kendaraan;

    @BindView(R.id.tf_kategori_kendaraan)
    TextFieldBoxes tf_kategori_kendaraan;
    @BindView(R.id.et_kategori_kendaraan)
    ExtendedEditText et_kategori_kendaraan;

    @BindView(R.id.tf_penggunaan_jaminan)
    TextFieldBoxes tf_penggunaan_jaminan;
    @BindView(R.id.et_penggunaan_jaminan)
    ExtendedEditText et_penggunaan_jaminan;

    @BindView(R.id.tf_daerah_operasional)
    TextFieldBoxes tf_daerah_operasional;
    @BindView(R.id.et_daerah_operasional)
    ExtendedEditText et_daerah_operasional;

    @BindView(R.id.tf_kondisi_jaminan)
    TextFieldBoxes tf_kondisi_jaminan;
    @BindView(R.id.et_kondisi_jaminan)
    ExtendedEditText et_kondisi_jaminan;

    @BindView(R.id.tf_nama_pemilik)
    TextFieldBoxes tf_nama_pemilik;
    @BindView(R.id.et_nama_pemilik)
    ExtendedEditText et_nama_pemilik;

    @BindView(R.id.tf_nama_pemilik_saat_ini)
    TextFieldBoxes tf_nama_pemilik_saat_ini;
    @BindView(R.id.et_nama_pemilik_saat_ini)
    ExtendedEditText et_nama_pemilik_saat_ini;

    @BindView(R.id.tf_alamat)
    TextFieldBoxes tf_alamat;
    @BindView(R.id.et_alamat)
    ExtendedEditText et_alamat;

    //END OF VIEW BINDING

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String idAgunan;
    private AgunanKendaraan dataAgunan;

    private Calendar calPeriksa;
    private DatePickerDialog dpTanggalPeriksa;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    public static String val_TanggalPemeriksaan = "";
    public static String val_JenisKendaraan = "";
    public static String val_KategoriKendaraan = "";
    public static String val_PenggunaanJaminan = "";
    public static String val_DaerahOperasional = "";
    public static String val_KondisiJaminan = "";
    public static String val_NamaPemilik = "";
    public static String val_NamaPemilikSaatIni = "";
    public static String val_Alamat = "";

    public FragmentAgunanKendaraan1() {
    }

    public FragmentAgunanKendaraan1(String midAgunan, AgunanKendaraan magunanData) {
        dataAgunan = magunanData;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_kendaraan_1, container, false);
        ButterKnife.bind(this, view);


        if (!String.valueOf(idAgunan).equalsIgnoreCase("0")) {
            Log.d("Cek idAgunan", String.valueOf(idAgunan));
            setData();
            disableTextfield();
        }

        return view;
    }


        private void setData() {

            et_tanggal_pemeriksaan.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTglPemeriksaan(),"ddMMyyyy","dd-MM-yyyy"));
            et_jenis_kendaraan.setText(dataAgunan.getJenisKendaraan());
            et_kategori_kendaraan.setText(dataAgunan.getKategKendaraan());
            et_penggunaan_jaminan.setText(dataAgunan.getPenggunaanJaminan());
            et_daerah_operasional.setText(KeyValue.getKeyOperasional(dataAgunan.getDaerahOperasional()));
            et_kondisi_jaminan.setText(dataAgunan.getKondisi());
            et_nama_pemilik.setText(dataAgunan.getNamaPemilikBPKB());
            et_nama_pemilik_saat_ini.setText(dataAgunan.getNamaPemilikSaatIni());
            et_alamat.setText(dataAgunan.getAlamatPemilik());
        }

    private void disableTextfield(){
        et_tanggal_pemeriksaan.setInputType(InputType.TYPE_NULL);
        et_tanggal_pemeriksaan.setFocusable(false);

        et_jenis_kendaraan.setInputType(InputType.TYPE_NULL);
        et_jenis_kendaraan.setFocusable(false);

        et_kategori_kendaraan.setInputType(InputType.TYPE_NULL);
        et_kategori_kendaraan.setFocusable(false);

        et_penggunaan_jaminan.setInputType(InputType.TYPE_NULL);
        et_penggunaan_jaminan.setFocusable(false);

        et_daerah_operasional.setInputType(InputType.TYPE_NULL);
        et_daerah_operasional.setFocusable(false);

        et_kondisi_jaminan.setInputType(InputType.TYPE_NULL);
        et_kondisi_jaminan.setFocusable(false);

        et_nama_pemilik.setInputType(InputType.TYPE_NULL);
        et_nama_pemilik.setFocusable(false);

        et_nama_pemilik_saat_ini.setInputType(InputType.TYPE_NULL);
        et_nama_pemilik_saat_ini.setFocusable(false);

        et_alamat.setInputType(InputType.TYPE_NULL);
        et_alamat.setFocusable(false);



    }


        private void dpTglPeriksa () {
            calPeriksa = Calendar.getInstance();
            DatePickerDialog.OnDateSetListener ls_expiredDate = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calPeriksa.set(Calendar.YEAR, year);
                    calPeriksa.set(Calendar.MONTH, month);
                    calPeriksa.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    String calExpiredString = dateClient.format(calPeriksa.getTime());
                    et_tanggal_pemeriksaan.setText(calExpiredString);
                }
            };

            dpTanggalPeriksa = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_expiredDate, calPeriksa.get(Calendar.YEAR),
                    calPeriksa.get(Calendar.MONTH), calPeriksa.get(Calendar.DAY_OF_MONTH));
            dpTanggalPeriksa.getDatePicker().setMaxDate(calPeriksa.getTimeInMillis());
            dpTanggalPeriksa.show();
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
