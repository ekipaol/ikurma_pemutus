package com.application.bris.brisi_pemutus.page_konsumer_kmg.data_finansial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.Error;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseDataInstansi;
import com.application.bris.brisi_pemutus.api.model.ParseResponseError;
import com.application.bris.brisi_pemutus.api.model.request.kelengkapan_dokumen.ReqKelengkapanDokumen;
import com.application.bris.brisi_pemutus.api.model.request.validasi_data_finansial.ValidasiDataFinansialKmg;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_finansial.DataFinansial;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.KeyValue;
import com.application.bris.brisi_pemutus.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class DataFinansialKonsumerKmgActivity extends AppCompatActivity implements TextWatcher {

    @BindView(R.id.tb_regular)
    Toolbar toolbar;
    @BindView(R.id.tv_page_title)
    TextView tv_page_title;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    //Data Finansial
    @BindView(R.id.tf_gaji_pokok)
    TextFieldBoxes tf_gaji_pokok;
    @BindView(R.id.et_gaji_pokok)
    EditText et_gaji_pokok;

    @BindView(R.id.tf_nilai_permohonan_pembiayaan)
    TextFieldBoxes tf_nilai_permohonan_pembiayaan;
    @BindView(R.id.et_nilai_permohonan_pembiayaan)
    EditText et_nilai_permohonan_pembiayaan;

    @BindView(R.id.tf_penghasilan_bersih)
    TextFieldBoxes tf_penghasilan_bersih;
    @BindView(R.id.et_penghasilan_bersih)
    EditText et_penghasilan_bersih;

    @BindView(R.id.tf_harga_beli)
    TextFieldBoxes tf_harga_beli;
    @BindView(R.id.et_harga_beli)
    EditText et_harga_beli;


    @BindView(R.id.tf_tunjangan_tetap_lainnya)
    TextFieldBoxes tf_tunjangan_tetap_lainnya;
    @BindView(R.id.et_tunjangan_tetap_lainnya)
    EditText et_tunjangan_tetap_lainnya;

    @BindView(R.id.tf_angsuran_pinjaman_eksisting_1)
    TextFieldBoxes tf_angsuran_pinjaman_eksisting_1;
    @BindView(R.id.et_angsuran_pinjaman_eksisting_1)
    EditText et_angsuran_pinjaman_eksisting_1;

    @BindView(R.id.tf_margin_pertahun)
    TextFieldBoxes tf_margin_pertahun;
    @BindView(R.id.et_margin_pertahun)
    EditText et_margin_pertahun;


    @BindView(R.id.tf_jangka_waktu)
    TextFieldBoxes tf_jangka_waktu;
    @BindView(R.id.et_jangka_waktu)
    EditText et_jangka_waktu;

    @BindView(R.id.tf_jumlah_plafon_pembiayaan_diusulkan)
    TextFieldBoxes tf_jumlah_plafon_pembiayaan_diusulkan;
    @BindView(R.id.et_jumlah_plafon_pembiayaan_diusulkan)
    EditText et_jumlah_plafon_pembiayaan_diusulkan;

    @BindView(R.id.tf_rpc)
    TextFieldBoxes tf_rpc;
    @BindView(R.id.et_rpc)
    EditText et_rpc;

    @BindView(R.id.tf_angsuran_)
    TextFieldBoxes tf_angsuran_;
    @BindView(R.id.et_angsuran)
    EditText et_angsuran;

    @BindView(R.id.tf_ftv_ratio)
    TextFieldBoxes tf_ftv_ratio;
    @BindView(R.id.et_ftv_ratio)
    EditText et_ftv_ratio;

    @BindView(R.id.tf_tujuan_penggunaan)
    TextFieldBoxes tf_tujuan_penggunaan;
    @BindView(R.id.et_tujuan_penggunaan)
    EditText et_tujuan_penggunaan;

    @BindView(R.id.tf_kewajiban_lain)
    TextFieldBoxes tf_kewajiban_lain;
    @BindView(R.id.et_kewajiban_lain)
    EditText et_kewajiban_lain;

    int maxJw=0;

    BigDecimal rateAnuitas=new BigDecimal(0);
    

 
    //variabel buat menghitung berapa field yang udah lolos validasi
    private int jumlahValidasi = 0;
    int idAplikasi=0;


    //VALUE
    private static String val_jenis_tiering = "";


    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    DataFinansial dataFinansial;
    DataFinansial dataInstansi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumer_data_finansial_kmg);

//        //push up when keyboard shown
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ButterKnife.bind(this);

        apiClientAdapter = new ApiClientAdapter(this,"http://10.1.25.55:8080/MobileBRISIAPI-BAYU/webresources/");
        appPreferences = new AppPreferences(this);
//        setGps();
        backgroundStatusBar();
//        checkCollapse();
        tv_page_title.setText("Form Data Finansial");
        idAplikasi=getIntent().getIntExtra("idAplikasi",0);
        onclickSelectDialog();
        loadData();




        //disabling total penghasilan field


        et_angsuran_pinjaman_eksisting_1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_angsuran_pinjaman_eksisting_1));
        et_nilai_permohonan_pembiayaan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilai_permohonan_pembiayaan));
        et_gaji_pokok.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_gaji_pokok));
        et_tunjangan_tetap_lainnya.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_tunjangan_tetap_lainnya));
        et_angsuran_pinjaman_eksisting_1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_angsuran_pinjaman_eksisting_1));
        et_penghasilan_bersih.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_penghasilan_bersih));
        et_harga_beli.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_harga_beli));
        et_jumlah_plafon_pembiayaan_diusulkan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_jumlah_plafon_pembiayaan_diusulkan));
        et_angsuran.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_angsuran));
        et_kewajiban_lain.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_angsuran));

        //for dynamic icon in textfieldboxes
        AppUtil.dynamicIconLogoChange(DataFinansialKonsumerKmgActivity.this,et_nilai_permohonan_pembiayaan,tf_nilai_permohonan_pembiayaan);
        AppUtil.dynamicIconLogoChange(DataFinansialKonsumerKmgActivity.this,et_gaji_pokok,tf_gaji_pokok);
        AppUtil.dynamicIconLogoChange(DataFinansialKonsumerKmgActivity.this,et_tunjangan_tetap_lainnya,tf_tunjangan_tetap_lainnya);
        AppUtil.dynamicIconLogoChange(DataFinansialKonsumerKmgActivity.this,et_angsuran_pinjaman_eksisting_1,tf_angsuran_pinjaman_eksisting_1);
        AppUtil.dynamicIconLogoChange(DataFinansialKonsumerKmgActivity.this,et_penghasilan_bersih,tf_penghasilan_bersih);
        AppUtil.dynamicIconLogoChange(DataFinansialKonsumerKmgActivity.this,et_harga_beli,tf_harga_beli);
        AppUtil.dynamicIconLogoChange(DataFinansialKonsumerKmgActivity.this,et_jumlah_plafon_pembiayaan_diusulkan,tf_jumlah_plafon_pembiayaan_diusulkan);
        AppUtil.dynamicIconLogoChange(DataFinansialKonsumerKmgActivity.this,et_margin_pertahun,tf_margin_pertahun);
        AppUtil.dynamicIconLogoChange(DataFinansialKonsumerKmgActivity.this,et_jangka_waktu,tf_jangka_waktu);
        AppUtil.dynamicIconLogoChange(DataFinansialKonsumerKmgActivity.this,et_rpc,tf_rpc);
        AppUtil.dynamicIconLogoChange(DataFinansialKonsumerKmgActivity.this,et_ftv_ratio,tf_ftv_ratio);
        AppUtil.dynamicIconLogoChange(DataFinansialKonsumerKmgActivity.this,et_angsuran,tf_angsuran_);
        AppUtil.dynamicIconLogoChange(DataFinansialKonsumerKmgActivity.this,et_kewajiban_lain,tf_kewajiban_lain);


        //disable edittexts
        et_angsuran_pinjaman_eksisting_1.setFocusable(false);
        et_penghasilan_bersih.setFocusable(false);
        et_margin_pertahun.setFocusable(false);
        et_rpc.setFocusable(false);
        et_angsuran.setFocusable(false);
        et_tujuan_penggunaan.setFocusable(false);





//metode bang idong dia ngirim intent dalam bentuk string yang isinya adalah objek data, lalu dconvert dalam bentuk GSON untuk dimasukkan di objek databaru, ada di method setData

        //metode eki mengirim langsung objeknya dari intent lalu langsung diterima didalam objek baru di kelas ini, tidak perlu convert gson gson

//        nik = getIntent().getStringExtra("nik");
//        if(getIntent().hasExtra("dataPribadi")){
//            dataPribadi = getIntent().getStringExtra("dataPribadi");
//            setData();
//        }
//        et_nik.setText(nik);

    }

    private void setData() {



        et_tujuan_penggunaan.setText(KeyValue.getKeyTujuanPenggunaanKmg(dataFinansial.getIDTUJUAN()));
        et_gaji_pokok.setText(dataFinansial.getOMZETPERBULAN());


        et_nilai_permohonan_pembiayaan.setText(dataFinansial.getPLAFONDYANGDIUSULKAN()); //ambil dari pipeline sesuai pengajuan

        String parsedTunjanganTetapLainnya=AppUtil.parseRupiahNoSymbol(dataFinansial.getPENGHASILANTETAPLAIN());
        String parsedAngsuranPinjamanEksisting=AppUtil.parseRupiahNoSymbol(dataFinansial.getANGSURAN());
        et_tunjangan_tetap_lainnya.setText(parsedTunjanganTetapLainnya);
        et_angsuran_pinjaman_eksisting_1.setText(parsedAngsuranPinjamanEksisting);
//        et_margin_pertahun.setText(dataFinansial.); //ini ambil dari data instansi


        //referensi buat bigdecimal
//        valBd_grossProfitMargin = (valBd_pendapatanUsaha.subtract(valBd_hargapokokPenjualan)).divide(valBd_pendapatanUsaha, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP);

        final BigDecimal penghasilanBersih=(new BigDecimal(dataFinansial.getOMZETPERBULAN()).add(new BigDecimal(dataFinansial.getPENGHASILANTETAPLAIN()))).subtract(new BigDecimal(dataFinansial.getANGSURAN()));
        et_penghasilan_bersih.setText(penghasilanBersih.toString()); //ini kalkulasi gaji pokok + penghasilan lain - angsuran eksisting
        et_jangka_waktu.setText(dataFinansial.getJANGKAWAKTU());

//        et_jumlah_plafon_pembiayaan_diusulkan.setText(dataFinansial.getPLAFONDYANGDIUSULKAN());
//        et_harga_beli.setText(dataFinansial.getPERMOHONANKREDIT());
//        et_rpc.setText(); //ini dari data instansi

//        et_jumlah_plafon_pembiayaan_diusulkan.setText(AppUtil.parseRupiah(dataFinansial.getPLAFONDYANGDIUSULKAN()));
//        et_harga_beli.setText(AppUtil.parseRupiah(dataFinansial.getPERMOHONANKREDIT()));

        String parsedJumlahPlafon=AppUtil.parseRupiahNoSymbol(dataFinansial.getPLAFONDYANGDIUSULKAN());
        String parsedHargaBeli=AppUtil.parseRupiahNoSymbol(dataFinansial.getPERMOHONANKREDIT());
        String parsedPlafondInduk=AppUtil.parseRupiahNoSymbol(dataFinansial.getPLAFONDINDUK());

        et_jumlah_plafon_pembiayaan_diusulkan.setText(parsedJumlahPlafon);
        et_harga_beli.setText(parsedHargaBeli);
        et_nilai_permohonan_pembiayaan.setText(parsedPlafondInduk);

        //


        //set margin rate
        if(Integer.parseInt(dataFinansial.getJANGKAWAKTU())>maxJw){
            Toast.makeText(DataFinansialKonsumerKmgActivity.this, "Jangka waktu tidak boleh melebihi "+maxJw+" bulan", Toast.LENGTH_SHORT).show();
            et_jangka_waktu.setText(Integer.toString(maxJw));
            et_jangka_waktu.setSelection(et_jangka_waktu.getText().length());
        }

        if(Integer.parseInt(dataFinansial.getJANGKAWAKTU())<=Integer.parseInt(dataFinansial.getMAXJW1())&&!dataFinansial.getMAXJW1().equalsIgnoreCase("0")){
            et_margin_pertahun.setText(dataFinansial.getMARGINRATE1());
        }
        else if(Integer.parseInt(dataFinansial.getJANGKAWAKTU())<=Integer.parseInt(dataFinansial.getMAXJW2())&&!dataFinansial.getMAXJW2().equalsIgnoreCase("0")){
            et_margin_pertahun.setText(dataFinansial.getMARGINRATE2());
        }
        else if(Integer.parseInt(dataFinansial.getJANGKAWAKTU())<=Integer.parseInt(dataFinansial.getMAXJW3())&&!dataFinansial.getMAXJW3().equalsIgnoreCase("0")){
            et_margin_pertahun.setText(dataFinansial.getMARGINRATE3());
        }
        else if(Integer.parseInt(dataFinansial.getJANGKAWAKTU())<=Integer.parseInt(dataFinansial.getMAXJW4())&&!dataFinansial.getMAXJW4().equalsIgnoreCase("0")){
            et_margin_pertahun.setText(dataFinansial.getMARGINRATE4());
        }


//         rateAnuitas=(new BigDecimal(dataFinansial.getPLAFOND_INDUK()).multiply((new BigDecimal(et_margin_pertahun.getText().toString()).divide(new BigDecimal("12")))).multiply((new BigDecimal(et_margin_pertahun.getText().toString()).divide(new BigDecimal("12"),2, RoundingMode.HALF_UP))).divide(new BigDecimal("1").subtract((new BigDecimal(1).add((new BigDecimal(et_margin_pertahun.getText().toString()).divide(new BigDecimal("12")))).pow(Integer.parseInt(et_jangka_waktu.getText().toString())))), 2, RoundingMode.HALF_UP)).setScale(1, BigDecimal.ROUND_HALF_UP);



        textChangedForDataFinansial();
//62839225.84
        //set rate anuitas
        Double ratePerTahun=Double.parseDouble(et_margin_pertahun.getText().toString())/100/12;

        //ini rumus aslinya, karena double gakbisa menampung hasil perpangkatan yang besar, jadi dipecah ke big decimal dibawah
//        Double pengaliPlafon=ratePerTahun/(1-(1/(Math.pow(1+ratePerTahun,Integer.parseInt(et_jangka_waktu.getText().toString())))));

        BigDecimal pangkatPembagi=(new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
                .getText().toString()));

        BigDecimal pengaliPlafon=new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi,15,RoundingMode.HALF_UP))),15,RoundingMode.HALF_UP).setScale(15,RoundingMode.HALF_UP);;

//        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);

        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2,RoundingMode.HALF_UP);

        et_angsuran.setText(String.valueOf(Angsuran));



        Toast.makeText(this, String.valueOf(Angsuran), Toast.LENGTH_LONG).show();
//        Log.d("anuitas",String.valueOf(rateAnuitas));

        //set rpc
        BigDecimal rpcValue=Angsuran.multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())),2,RoundingMode.HALF_UP);

        et_rpc.setText(String.valueOf(rpcValue));






    }

    private void textChangedForDataFinansial(){
        et_gaji_pokok.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){

                    //perubahan penghasilan bersih
                    BigDecimal penghasilanBersih=(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));

                    et_penghasilan_bersih.setText(penghasilanBersih.toString());


                }
                else{
                    BigDecimal penghasilanBersihZero=new BigDecimal("0").subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString()))));

                    et_penghasilan_bersih.setText(penghasilanBersihZero.toString());

                }

            }



            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_tunjangan_tetap_lainnya.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0&&et_kewajiban_lain.getText().length()>0){
                    BigDecimal penghasilanBersih=(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));

                    et_penghasilan_bersih.setText(penghasilanBersih.toString());
                }
                else{
                    BigDecimal penghasilanBersihZero=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString())).add(new BigDecimal("0")));

                    et_penghasilan_bersih.setText(penghasilanBersihZero.toString());

                }

            }



            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_kewajiban_lain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0&&et_penghasilan_bersih.getText().length()>0){
                    BigDecimal penghasilanBersih=(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));

                    et_penghasilan_bersih.setText(penghasilanBersih.toString());
                }
                else{
                    BigDecimal penghasilanBersihZero=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString())).add(new BigDecimal("0")));

                    et_penghasilan_bersih.setText(penghasilanBersihZero.toString());

                }



            }



            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_kewajiban_lain.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==false&&et_kewajiban_lain.getText().toString().equalsIgnoreCase("")){
                    et_kewajiban_lain.setText("0");
                }
            }
        });

        et_tunjangan_tetap_lainnya.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==false&&et_tunjangan_tetap_lainnya.getText().toString().equalsIgnoreCase("")){
                    et_tunjangan_tetap_lainnya.setText("0");
                }
            }
        });





        et_margin_pertahun.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //set rate anuitas
                Double ratePerTahun=Double.parseDouble(et_margin_pertahun.getText().toString())/100/12;
                BigDecimal pangkatPembagi=(new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
                        .getText().toString()));

                BigDecimal pengaliPlafon=new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi,15,RoundingMode.HALF_UP))),15,RoundingMode.HALF_UP).setScale(15,RoundingMode.HALF_UP);;

//        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);

                BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2,RoundingMode.HALF_UP);

                et_angsuran.setText(String.valueOf(Angsuran));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_penghasilan_bersih.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s!=null&&s.length()>0&&!new BigDecimal(s.toString()).equals(0)){
                    BigDecimal rpcValue=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran.getText().toString())).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())),2,RoundingMode.HALF_UP);

                    et_rpc.setText(String.valueOf(rpcValue));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_angsuran.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                BigDecimal rpcValue=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran.getText().toString())).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())),2,RoundingMode.HALF_UP);

                et_rpc.setText(String.valueOf(rpcValue));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_jangka_waktu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.d("max jw",Integer.toString(maxJw));
                if(s!=null&&s.length()>0&&Integer.parseInt(s.toString())!=0){
                    if(Integer.parseInt(et_jangka_waktu.getText().toString())>maxJw){
                        Toast.makeText(DataFinansialKonsumerKmgActivity.this, "Jangka waktu tidak boleh melebihi "+maxJw+" bulan", Toast.LENGTH_SHORT).show();
                        et_jangka_waktu.setText(Integer.toString(maxJw));
                        et_jangka_waktu.setSelection(et_jangka_waktu.getText().length());
                    }

                    if(Integer.parseInt(et_jangka_waktu.getText().toString())<=Integer.parseInt(dataFinansial.getMAXJW1())&&!dataFinansial.getMAXJW1().equalsIgnoreCase("0")&&!dataFinansial.getMARGINRATE1().equalsIgnoreCase("0")){
                        et_margin_pertahun.setText(dataFinansial.getMARGINRATE1());
                    }
                    else if(Integer.parseInt(et_jangka_waktu.getText().toString())<=Integer.parseInt(dataFinansial.getMAXJW2())&&!dataFinansial.getMAXJW2().equalsIgnoreCase("0")&&!dataFinansial.getMARGINRATE2().equalsIgnoreCase("0")){
                        et_margin_pertahun.setText(dataFinansial.getMARGINRATE2());
                    }
                    else if(Integer.parseInt(et_jangka_waktu.getText().toString())<=Integer.parseInt(dataFinansial.getMAXJW3())&&!dataFinansial.getMAXJW3().equalsIgnoreCase("0")&&!dataFinansial.getMARGINRATE3().equalsIgnoreCase("0")){
                        et_margin_pertahun.setText(dataFinansial.getMARGINRATE3());
                    }
                    else if(Integer.parseInt(et_jangka_waktu.getText().toString())<=Integer.parseInt(dataFinansial.getMAXJW4())&&!dataFinansial.getMAXJW4().equalsIgnoreCase("0")&&!dataFinansial.getMARGINRATE4().equalsIgnoreCase("0")){
                        et_margin_pertahun.setText(dataFinansial.getMARGINRATE4());
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    private void backgroundStatusBar() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorBackgroundTransparent));
        }
    }

    private void checkCollapse() {
//        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangedListener() {
//            @Override
//            public void onStateChanged(AppBarLayout appBarLayout, State state) {
//                if (state.name().equalsIgnoreCase("COLLAPSED")) {
//                    tv_page_title.setVisibility(View.VISIBLE);
//                    btn_takepicture.setVisibility(View.VISIBLE);
//                    tv_page_title.setText("Input Data Nasabah");
//                } else {
//                    tv_page_title.setVisibility(View.GONE);
//                    btn_takepicture.setVisibility(View.GONE);
//                    tv_page_title.setText("");
//                }
//            }
//        });
    }



    //method ketika klik panah dropdown
    private void onclickSelectDialog() {
        //hanya ada satu field yang jenis dropown
//        subSelectDialog(et_jenis_tiering, tf_jenis_tiering);

    }



    //method ketika onclick pada gambar atau textview


    public void loadData() {
        loading.setVisibility(View.VISIBLE);

        //pantekan
//        inquiryRPC req=new inquiryRPC(101419);


        ReqKelengkapanDokumen req=new ReqKelengkapanDokumen();
        req.setId_aplikasi(idAplikasi);
        Call<ParseResponseDataInstansi> call = apiClientAdapter.getApiInterface().inquiryDataFinansialKmg(req);
        call.enqueue(new Callback<ParseResponseDataInstansi>() {
            @Override
            public void onResponse(Call<ParseResponseDataInstansi> call, Response<ParseResponseDataInstansi> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            loading.setVisibility(View.GONE);

                            String dataFinansialString=response.body().getData().toString();

                            String dataInstansiString=response.body().getDataInstansi().toString();
                            Gson gson = new Gson();

                            dataFinansial = gson.fromJson(dataFinansialString, DataFinansial.class);
                            dataInstansi=gson.fromJson(dataInstansiString, DataFinansial.class);

                            dataFinansial.setKATEGORI(dataInstansi.getKATEGORI());
                            dataFinansial.setMINPLAFONBERAGUNAN(dataInstansi.getMINPLAFONBERAGUNAN());
                            dataFinansial.setMAXRPC(dataInstansi.getMAXRPC());
                            dataFinansial.setFIDINSTANSI(dataInstansi.getFIDINSTANSI());
                            dataFinansial.setSISAPLAFONDINSTANSI(dataInstansi.getSISAPLAFONDINSTANSI());

                            dataFinansial.setMARGINRATE1(dataInstansi.getMARGINRATE1());
                            dataFinansial.setMARGINRATE2(dataInstansi.getMARGINRATE2());
                            dataFinansial.setMARGINRATE3(dataInstansi.getMARGINRATE3());
                            dataFinansial.setMARGINRATE4(dataInstansi.getMARGINRATE4());

                            dataFinansial.setMAXJW1(dataInstansi.getMAXJW1());
                            dataFinansial.setMAXJW2(dataInstansi.getMAXJW2());
                            dataFinansial.setMAXJW3(dataInstansi.getMAXJW3());
                            dataFinansial.setMAXJW4(dataInstansi.getMAXJW4());


                            //ngambil max jangka waktu
                            int data_jw1= Integer.parseInt(dataInstansi.getMAXJW1());
                            int data_jw2= Integer.parseInt(dataInstansi.getMAXJW2());
                            int data_jw3= Integer.parseInt(dataInstansi.getMAXJW3());
                            int data_jw4= Integer.parseInt(dataInstansi.getMAXJW4());

                            List<Integer> listJw=new ArrayList<>();
                            listJw.add(data_jw1);
                            listJw.add(data_jw2);
                            listJw.add(data_jw3);
                            listJw.add(data_jw4);

                            maxJw= Collections.max(listJw);








//                                 textChangedForDataFinansial();

                            setData();
//                                Log.d("firmansah",dataFinansialString);
//                                Log.d("firmansah isdebest",dataFinansial.getOMZETPERBULAN());




                        } else {
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(DataFinansialKonsumerKmgActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataFinansialKonsumerKmgActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ParseResponseDataInstansi> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(DataFinansialKonsumerKmgActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }


//gak dipake, karena sudah divalidasi di pemrakarsa
    public void validasiData() {
//        loading.setVisibility(View.VISIBLE);
        final SweetAlertDialog dialog=new SweetAlertDialog(DataFinansialKonsumerKmgActivity.this,SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitle("Memvalidasi");
        dialog.showCancelButton(true);
        dialog.setCancelText("Batal");
        dialog.show();

        //pantekan
//        inquiryRPC req=new inquiryRPC(101419);


        ValidasiDataFinansialKmg req=new ValidasiDataFinansialKmg();
        req.setIdAplikasi(Integer.toString(idAplikasi));
        req.setPlafondUsul(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));
        req.setRPCfinal(et_rpc.getText().toString());
        req.setMAKSIMUM_PLAFOND(NumberTextWatcherCanNolForThousand.trimCommaOfString(AppUtil.parseRupiahNoSymbol(dataFinansial.getMAKSIMUMPLAFOND())));
        req.setCookie_jw_max(Integer.toString(maxJw));
        req.setJANGKA_WAKTU(et_jangka_waktu.getText().toString());
        req.setMAKS_TENOR_MPP(dataFinansial.getMAKSTENORMPP());
        req.setOMZET_PERBULAN(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString()));
        req.setINPUT_PERMOHONAN(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_nilai_permohonan_pembiayaan.getText().toString()));




        Call<ParseResponse> call = apiClientAdapter.getApiInterface().validasiDataFinansial(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            loading.setVisibility(View.GONE);
                            dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            dialog.setTitle("Hasil Validasi");
                            dialog.setContentText(response.body().getMessage()+"\n");
                            dialog.setCancelText("Kembali");
                            dialog.setConfirmText("Simpan");
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    //simpan hasil ke scoring
                                    dialog.dismissWithAnimation();
                                }
                            });
                            dialog.show();
//                            Toast.makeText(DataFinansialKonsumerKmgActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();



                        } else {
                            loading.setVisibility(View.GONE);
                            dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog.setTitle("Hasil Validasi");
                            dialog.setContentText(response.body().getMessage()+"\n");
                            dialog.showCancelButton(false);
                            dialog.setConfirmText("OK");
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog.dismissWithAnimation();
                                }
                            });
                            dialog.show();
//                            AppUtil.notiferror(DataFinansialKonsumerKmgActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataFinansialKonsumerKmgActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(DataFinansialKonsumerKmgActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    private boolean validateForm() {

        //reset nilai jumlah validasi
        jumlahValidasi = 0;

        //else dan if buat validasi seluruh field dengan menggunakan 1 method


        subValidate(et_nilai_permohonan_pembiayaan,tf_nilai_permohonan_pembiayaan);
        subValidate(et_angsuran_pinjaman_eksisting_1,tf_angsuran_pinjaman_eksisting_1);
        subValidate(et_gaji_pokok,tf_gaji_pokok);
        subValidate(et_tunjangan_tetap_lainnya,tf_tunjangan_tetap_lainnya);
        subValidate(et_penghasilan_bersih,tf_penghasilan_bersih);
        subValidate(et_margin_pertahun,tf_margin_pertahun);
        subValidate(et_harga_beli,tf_harga_beli);
        subValidate(et_jangka_waktu,tf_jangka_waktu);
        subValidate(et_jumlah_plafon_pembiayaan_diusulkan,tf_jumlah_plafon_pembiayaan_diusulkan);
        subValidate(et_rpc,tf_rpc);
        subValidate(et_jangka_waktu,tf_jangka_waktu);
        subValidate(et_jumlah_plafon_pembiayaan_diusulkan,tf_jumlah_plafon_pembiayaan_diusulkan);
        subValidate(et_ftv_ratio,tf_ftv_ratio);


        if(jumlahValidasi==13){
            return true;
        }
        else{
            return false;
        }

    }

    private void subValidate(EditText editext, TextFieldBoxes textFieldBoxes) {
        if (editext.getText().toString().trim().equalsIgnoreCase("pilih") ||editext.getText().toString().trim().equalsIgnoreCase("pilih jenis tiering") || editext.getText().toString().trim().isEmpty()) {
            //agar user tau field yang error dari textfield
            textFieldBoxes.setError("Harap Isi " + textFieldBoxes.getLabelText().substring(0, textFieldBoxes.getLabelText().length() - 2), true);

            //agar user tau nama field yang error dari snackbar
            AppUtil.notiferror(DataFinansialKonsumerKmgActivity.this, findViewById(R.id.nested_scv), "Harap pilih " + textFieldBoxes.getLabelText().substring(0, textFieldBoxes.getLabelText().length() - 2));


        } else {
            jumlahValidasi = jumlahValidasi + 1;
        }
    }




    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        if (et_tindaklanjut.getText().toString().trim().isEmpty() || et_tindaklanjut.getText().toString().trim().equalsIgnoreCase("")){
//            rg_typetindaklanjut.clearCheck();
//            val_jenistindaklanjut = "";
//        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}