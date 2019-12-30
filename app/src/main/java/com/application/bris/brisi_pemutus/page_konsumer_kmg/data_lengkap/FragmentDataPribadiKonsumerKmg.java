package com.application.bris.brisi_pemutus.page_konsumer_kmg.data_lengkap;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkapKonsumerKmg;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.KeyValue;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

@SuppressLint("ValidFragment")
public class FragmentDataPribadiKonsumerKmg extends Fragment implements Step {

    @BindView(R.id.tf_nik)
    TextFieldBoxes tf_nik;
    @BindView(R.id.et_nik)
    EditText et_nik;
    @BindView(R.id.tf_expirednik)
    TextFieldBoxes tf_expirednik;
    @BindView(R.id.et_expirednik)
    EditText et_expirednik;
    @BindView(R.id.tf_npwp)
    TextFieldBoxes tf_npwp;
    @BindView(R.id.et_npwp)
    EditText et_npwp;
    @BindView(R.id.tf_nama)
    TextFieldBoxes tf_nama;
    @BindView(R.id.et_nama)
    EditText et_nama;
    @BindView(R.id.tf_namaalias)
    TextFieldBoxes tf_namaalias;
    @BindView(R.id.et_namaalias)
    EditText et_namaalias;
    @BindView(R.id.tf_tempatlahir)
    TextFieldBoxes tf_tempatlahir;
    @BindView(R.id.et_tempatlahir)
    EditText et_tempatlahir;
    @BindView(R.id.tf_tanggallahir)
    TextFieldBoxes tf_tanggallahir;
    @BindView(R.id.et_tanggallahir)
    EditText et_tanggallahir;
    @BindView(R.id.tf_jeniskelamin)
    TextFieldBoxes tf_jeniskelamin;
    @BindView(R.id.et_jeniskelamin)
    EditText et_jeniskelamin;
    @BindView(R.id.tf_nomorhp)
    TextFieldBoxes tf_nomorhp;
    @BindView(R.id.et_nomorhp)
    EditText et_nomorhp;
    @BindView(R.id.tf_email)
    TextFieldBoxes tf_email;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.tf_agama)
    TextFieldBoxes tf_agama;
    @BindView(R.id.et_agama)
    EditText et_agama;
    @BindView(R.id.tf_ketagama)
    TextFieldBoxes tf_ketagama;
    @BindView(R.id.et_ketagama)
    EditText et_ketagama;
    @BindView(R.id.tf_namaibukandung)
    TextFieldBoxes tf_namaibukandung;
    @BindView(R.id.et_namaibukandung)
    EditText et_namaibukandung;
    @BindView(R.id.tf_statusnikah)
    TextFieldBoxes tf_statusnikah;
    @BindView(R.id.et_statusnikah)
    EditText et_statusnikah;
    @BindView(R.id.tf_nikpasangan)
    TextFieldBoxes tf_nikpasangan;
    @BindView(R.id.et_nikpasangan)
    EditText et_nikpasangan;
    @BindView(R.id.tf_namapasangan)
    TextFieldBoxes tf_namapasangan;
    @BindView(R.id.et_namapasangan)
    EditText et_namapasangan;
    @BindView(R.id.tf_tanggallahirpasangan)
    TextFieldBoxes tf_tanggallahirpasangan;
    @BindView(R.id.et_tanggallahirpasangan)
    EditText et_tanggallahirpasangan;
    @BindView(R.id.tf_namakeluarga)
    TextFieldBoxes tf_namakeluarga;
    @BindView(R.id.et_namakeluarga)
    EditText et_namakeluarga;
    @BindView(R.id.tf_nomorhpkeluarga)
    TextFieldBoxes tf_nomorhpkeluarga;
    @BindView(R.id.et_nomorhpkeluarga)
    EditText et_nomorhpkeluarga;
    @BindView(R.id.tf_jumlahtanggungan)
    TextFieldBoxes tf_jumlahtanggungan;
    @BindView(R.id.et_jumlahtanggungan)
    EditText et_jumlahtanggungan;
    @BindView(R.id.tf_tipependapatan)
    TextFieldBoxes tf_tipependapatan;
    @BindView(R.id.et_tipependapatan)
    EditText et_tipependapatan;
    @BindView(R.id.tf_pendidikanterakhir)
    TextFieldBoxes tf_pendidikanterakhir;
    @BindView(R.id.et_pendidikanterakhir)
    EditText et_pendidikanterakhir;
    @BindView(R.id.tf_gelar)
    TextFieldBoxes tf_gelar;
    @BindView(R.id.et_gelar)
    EditText et_gelar;

    @BindView(R.id.tf_jenis_kmg)
    TextFieldBoxes tf_jenis_kmg;
    @BindView(R.id.et_jenis_kmg)
    EditText et_jenis_kmg;

    @BindView(R.id.tf_referensi)
    TextFieldBoxes tf_referensi;
    @BindView(R.id.et_referensi)
    EditText et_referensi;

    @BindView(R.id.ll_pasangan)
    LinearLayout ll_pasangan;

    private Calendar calLahirPasangan;
    private Calendar calLahir;
    private Calendar calExpiredNik;

    private DatePickerDialog dpTanggalLahirPasangan;
    private DatePickerDialog dpTanggalLahir;
    private DatePickerDialog dpExpiredNik;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    private DataLengkapKonsumerKmg dataLengkap;

    //for npwp formatting
    int dot1=0;
    int dot2=0;
    int dot3=0;
    int dot4=0;
    int dot5=0;

    private String val_NamaAlias ="";
    private String val_NoKtpPasangan ="";
    private String val_StatusNikah ="";
    private String val_NoHp ="";
    private String val_NamaNasabah ="";
    private String val_Npwp ="";
    private String val_PendTerakhir ="";
    private String val_KetGelar ="";
    private String val_KetAgama ="";
    private String val_Agama ="";
    private String val_NamaIbu ="";
    private String val_NamaPasangan ="";
    private String val_Email ="";
    private String val_TelpKeluarga ="";
    private String val_ExpId ="";
    private String val_TglLahirPasangan ="";
    private String val_NoKtp ="";
    private String val_JlhTanggungan ;
    private String val_TglLahir ="";
    private String val_Keluarga ="";
    private String val_TptLahir ="";
    private String val_TipePendapatan ="";
    private String val_Jenkel ="";

    private Realm realm;



    public FragmentDataPribadiKonsumerKmg() {
    }

    public FragmentDataPribadiKonsumerKmg(DataLengkapKonsumerKmg mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_data_pribadi_konsumer_kmg, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
//        onSelectDialog();


        //ini diatas karena dia on text change listener
//        setDynamicIcon();

        npwpFormattingTextChange(et_npwp);
        setData();

        //ini dibawah karena dia checking is empty atau tidak, jadi kalau diatas dia pasti nilainya isempty, padahal emang datanya belum diterima
//        setDynamicIconDropDown();


        return view;
    }

    private void setData(){

        et_nik.setText(dataLengkap.getNoKtp());
        et_expirednik.setText(AppUtil.parseTanggalGeneral(dataLengkap.getExpId(), "ddMMyyyy", "dd-MM-yyyy"));
//            et_npwp.setText(dataLengkap.getNpwp().replaceAll("[-.]", ""));
        et_npwp.setText(AppUtil.parseNpwp(dataLengkap.getNpwp()));
        et_nama.setText(dataLengkap.getNamaNasabah());
        et_namaalias.setText(dataLengkap.getNamaAlias());
        et_tempatlahir.setText(dataLengkap.getTptLahir());
        et_tanggallahir.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglLahir(), "ddMMyyyy", "dd-MM-yyyy"));
        et_jeniskelamin.setText(KeyValue.getKeyJenisKelamin(dataLengkap.getJenkel()));
        et_nomorhp.setText(dataLengkap.getNoHp());
        et_email.setText(dataLengkap.getEmail());
        et_agama.setText(KeyValue.getKeyAgama(dataLengkap.getAgama()));
        et_ketagama.setText(dataLengkap.getKetAgama());
        et_namaibukandung.setText(dataLengkap.getNamaIbu());
        et_statusnikah.setText(KeyValue.getKeyStatusNikah(dataLengkap.getStatusNikah()));
        et_nikpasangan.setText(dataLengkap.getNoKtpPasangan());
        et_namapasangan.setText(dataLengkap.getNamaPasangan());
        et_tanggallahirpasangan.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTgllahirPasangan(), "ddMMyyyy", "dd-MM-yyyy"));
        et_namakeluarga.setText(dataLengkap.getKeluarga());
        et_nomorhpkeluarga.setText(dataLengkap.getTelpKeluarga());
        et_jumlahtanggungan.setText(String.valueOf(dataLengkap.getJlhTanggungan()));
//            et_tipependapatan.setText(KeyValue.getKeyTipePendapatan(dataLengkap.getTipePendapatan()));
        et_pendidikanterakhir.setText(KeyValue.getKeyPendidikanTerakhir(dataLengkap.getPendidikanTerakhir()));
        et_referensi.setText(KeyValue.getKeyReferensi(dataLengkap.getReferensi()));


        if (dataLengkap.getStatusNikah().equalsIgnoreCase("2")){
            ll_pasangan.setVisibility(View.VISIBLE);
        }
        if (dataLengkap.getAgama().equalsIgnoreCase("ZZZ")){
            tf_ketagama.setVisibility(View.VISIBLE);
        }
    }










    @Nullable
    @Override
    public VerificationError verifyStep() {
        return  null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }





    private void npwpFormattingTextChange(final EditText npwp){
        npwp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()==2){
                    dot1=1;
                    npwp.setSelection(npwp.getText().length());

                }
                else if(s.length()==3&&dot1==1&&s.toString().substring(2,3)!="."){
                    dot1=0;
                    npwp.setText(npwp.getText().toString().substring(0,2)+"."+npwp.getText().toString().substring(2,3));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==6){
                    dot2=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==7&&dot2==1&&s.toString().substring(6,7)!="."){
                    dot2=0;
                    npwp.setText(npwp.getText().toString().substring(0,6)+"."+npwp.getText().toString().substring(6,7));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==10){
                    dot3=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==11&&dot3==1&&s.toString().substring(10,11)!="."){
                    dot3=0;
                    npwp.setText(npwp.getText().toString().substring(0,10)+"."+npwp.getText().toString().substring(10,11));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==12){
                    dot4=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==13&&dot4==1&&s.toString().substring(12,13)!="-"){
                    dot4=0;
                    npwp.setText(npwp.getText().toString().substring(0,12)+"-"+npwp.getText().toString().substring(12,13));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==16){
                    dot5=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==17&&dot5==1&&s.toString().substring(16,17)!="."){
                    dot5=0;
                    npwp.setText(npwp.getText().toString().substring(0,16)+"."+npwp.getText().toString().substring(16,17));
                    npwp.setSelection(npwp.getText().length());
                }

//                if(s.length()==10){
//                    npwp.setText(npwp.getText()+".");
//                    npwp.setSelection(npwp.getText().length());
//                }
//                if(s.length()==12){
//                    npwp.setText(npwp.getText()+"-");
//                    npwp.setSelection(npwp.getText().length());
//                }
//                if(s.length()==16){
//                    npwp.setText(npwp.getText()+".");
//                    npwp.setSelection(npwp.getText().length());
//                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
