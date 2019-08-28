package com.application.bris.brisi_pemutus.page_putusan.agunan;





import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.application.bris.brisi_pemutus.model.agunan.Agunan;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkap;
import com.application.bris.brisi_pemutus.model.keyvalue.keyvalue;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.KeyValue;
import com.application.bris.brisi_pemutus.util.NumberTextWatcherCanNolForThousand;
import com.application.bris.brisi_pemutus.util.NumberTextWatcherForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunan6Hasil extends Fragment implements Step {

    @BindView(R.id.tf_tanggal_pemeriksaan)
    TextFieldBoxes tf_tanggal_pemeriksaan;
    @BindView(R.id.et_tanggal_pemeriksaan)
    EditText et_tanggal_pemeriksaan;

    @BindView(R.id.tf_data_pembanding_1)
    TextFieldBoxes tf_data_pembanding_1;
    @BindView(R.id.et_data_pembanding_1)
    EditText et_data_pembanding_1;

    @BindView(R.id.tf_data_pembanding_2)
    TextFieldBoxes tf_data_pembanding_2;
    @BindView(R.id.et_data_pembanding_2)
    EditText et_data_pembanding_2;

    @BindView(R.id.tf_data_pembanding_3)
    TextFieldBoxes tf_data_pembanding_3;
    @BindView(R.id.et_data_pembanding_3)
    EditText et_data_pembanding_3;

    @BindView(R.id.tf_luas_tanah_hasil)
    TextFieldBoxes tf_luas_tanah_hasil;
    @BindView(R.id.et_luas_tanah_hasil)
    EditText et_luas_tanah_hasil;

    @BindView(R.id.tf_harga_tanah)
    TextFieldBoxes tf_harga_tanah;
    @BindView(R.id.et_harga_tanah)
    EditText et_harga_tanah;

    @BindView(R.id.tf_luas_bangunan_imb)
    TextFieldBoxes tf_luas_bangunan_imb;
    @BindView(R.id.et_luas_bangunan_imb)
    EditText et_luas_bangunan_imb;

    @BindView(R.id.tf_harga_bangunan_imb)
    TextFieldBoxes tf_harga_bangunan_imb;
    @BindView(R.id.et_harga_bangunan_imb)
    EditText et_harga_bangunan_imb;

    @BindView(R.id.tf_luas_bangunan_tidak_imb)
    TextFieldBoxes tf_luas_bangunan_tidak_imb;
    @BindView(R.id.et_luas_bangunan_tidak_imb)
    EditText et_luas_bangunan_tidak_imb;

    @BindView(R.id.tf_harga_bangunan_tidak_imb)
    TextFieldBoxes tf_harga_bangunan_tidak_imb;
    @BindView(R.id.et_harga_bangunan_tidak_imb)
    EditText et_harga_bangunan_tidak_imb;

    @BindView(R.id.tf_nilai_likuidasi)
    TextFieldBoxes tf_nilai_likuidasi;
    @BindView(R.id.et_nilai_likudasi)
    EditText et_nilai_likudasi;

    @BindView(R.id.tf_luas_total)
    TextFieldBoxes tf_luas_total;
    @BindView(R.id.et_luas_total)
    EditText et_luas_total;

    @BindView(R.id.tf_npw_tanah)
    TextFieldBoxes tf_npw_tanah;
    @BindView(R.id.et_npw_tanah)
    EditText et_npw_tanah;

    @BindView(R.id.tf_npw_bangunan)
    TextFieldBoxes tf_npw_bangunan;
    @BindView(R.id.et_npw_bangunan)
    EditText et_npw_bangunan;

    @BindView(R.id.tf_npw_tanah_dan_bangunan)
    TextFieldBoxes tf_npw_tanah_dan_bangunan;
    @BindView(R.id.et_npw_tanah_dan_bangunan)
    EditText et_npw_tanah_dan_bangunan;

    @BindView(R.id.tf_npw_bangunan_diasuransikan)
    TextFieldBoxes tf_npw_bangunan_diasuransikan;
    @BindView(R.id.et_npw_bangunan_diasuransikan)
    EditText et_npw_bangunan_diasuransikan;

    private BigDecimal luas_tanah_big=new BigDecimal(0);
    private BigDecimal harga_tanah=new BigDecimal(0);
    private BigDecimal npw_tanah=new BigDecimal(0);



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



    public FragmentAgunan6Hasil() {
    }

    public FragmentAgunan6Hasil(Agunan mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_6_hasil_penilaian, container, false);
      ButterKnife.bind(this, view);

      et_harga_tanah.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
              if (!et_harga_tanah.getText().toString().isEmpty()) {
                  luas_tanah_big = new BigDecimal(et_luas_tanah_hasil.getText().toString());
              }

              harga_tanah = new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_harga_tanah.getText().toString().trim()));
              npw_tanah = luas_tanah_big.multiply(harga_tanah).setScale(0, RoundingMode.HALF_UP);

              et_npw_tanah.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_npw_tanah));
              et_npw_tanah.setText(String.valueOf(npw_tanah));
          }

          @Override
          public void afterTextChanged(Editable s) {

          }
      });
         setData();

        return view;
    }

    private void setData(){
        et_tanggal_pemeriksaan.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTanggalPemeriksaan(), "ddMMyyyy", "dd-MM-yyyy"));
        et_data_pembanding_1.setText(dataLengkap.getDataPembanding1());
        et_data_pembanding_2.setText(dataLengkap.getDataPembanding2());
        et_data_pembanding_3.setText(dataLengkap.getDataPembanding3());

        et_luas_tanah_hasil.setText(dataLengkap.getLuasTanahSertifikat());
//        et_luas_tanah_hasil.setText(insertAgunan.getLuasTanahFisik());

        et_harga_tanah.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_harga_tanah));
        et_harga_tanah.setText(dataLengkap.getHargaMeterTanah());
        et_luas_bangunan_imb.setText(dataLengkap.getLuasBangunan1());

        et_harga_bangunan_imb.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_harga_bangunan_imb));
        et_harga_bangunan_imb.setText(dataLengkap.getHargaBangunan1());
        et_luas_bangunan_tidak_imb.setText(dataLengkap.getLuasBangunan2());

        et_harga_bangunan_tidak_imb.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_harga_bangunan_tidak_imb));
        et_harga_bangunan_tidak_imb.setText(dataLengkap.getHargaBangunan2());
        et_nilai_likudasi.setText(dataLengkap.getNLTanahBangunan());

        et_npw_tanah_dan_bangunan.addTextChangedListener(new NumberTextWatcherForThousand(et_npw_tanah_dan_bangunan));
        et_npw_tanah_dan_bangunan.setText(dataLengkap.getNPWTanahBangunan());

        et_npw_bangunan.addTextChangedListener(new NumberTextWatcherForThousand(et_npw_bangunan));
        et_npw_bangunan.setText(Long.toString(dataLengkap.getNPWBangunan()));



        et_luas_total.setText(Long.toString(Long.parseLong(dataLengkap.getLuasBangunan1())+Long.parseLong(dataLengkap.getLuasBangunan2())));

        et_nilai_likudasi.addTextChangedListener(new NumberTextWatcherForThousand(et_nilai_likudasi));
        et_nilai_likudasi.setText(dataLengkap.getNLTanahBangunan());

        et_npw_bangunan_diasuransikan.addTextChangedListener(new NumberTextWatcherForThousand(et_npw_bangunan_diasuransikan));
        et_npw_bangunan_diasuransikan.setText(dataLengkap.getNPWBangunanBRIS());





//        et_luas_total.setText(insertAgunan.getLua);
//        et_npw_tanah.setText();
//        et_npw_bangunan.setText(insertAgunan.getNPWBangunan());
//        et_npw_tanah_dan_bangunan.setText(insertAgunan.getNPWTanahBangunan());
//        et_npw_bangunan_diasuransikan.setText(insertAgunan.getNPWBangunanBrins());
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



