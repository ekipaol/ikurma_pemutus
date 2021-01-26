package com.application.bris.brisi_pemutus.page_konsumer_kpr.data_finansial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.Error;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseError;
import com.application.bris.brisi_pemutus.api.model.request.kelengkapan_dokumen.ReqKelengkapanDokumen;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_finansial.DataFinansialKpr;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.data_finansial.DataFinansialKonsumerKmgActivity;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.front_menu.PutusanFrontMenuKmg;
import com.application.bris.brisi_pemutus.page_konsumer_kpr.PutusanFrontMenuKpr;
import com.application.bris.brisi_pemutus.page_putusan.agunan_retry.AgunanTerikatActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;


public class DataFinansialKprActivity extends AppCompatActivity  {

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

    @BindView(R.id.tf_rumah_keberapa)
    TextFieldBoxes tf_rumah_keberapa;
    @BindView(R.id.et_rumah_keberapa)
    EditText et_rumah_keberapa;


    @BindView(R.id.tf_jangka_waktu)
    TextFieldBoxes tf_jangka_waktu;
    @BindView(R.id.et_jangka_waktu)
    EditText et_jangka_waktu;

    @BindView(R.id.tf_jangka_waktu_qardh)
    TextFieldBoxes tf_jangka_waktu_qardh;
    @BindView(R.id.et_jangka_waktu_qardh)
    EditText et_jangka_waktu_qardh;

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



    @BindView(R.id.tf_uang_muka)
    TextFieldBoxes tf_uang_muka;
    @BindView(R.id.et_uang_muka)
    EditText et_uang_muka;

    @BindView(R.id.tf_jangka_waktu_takeover)
    TextFieldBoxes tf_jangka_waktu_takeover;
    @BindView(R.id.et_jangka_waktu_takeover)
    EditText et_jangka_waktu_takeover;

    @BindView(R.id.tf_jumlah_plafon_pembiayaan_konsumtif)
    TextFieldBoxes tf_jumlah_plafon_pembiayaan_konsumtif;
    @BindView(R.id.et_jumlah_plafon_pembiayaan_konsumtif)
    EditText et_jumlah_plafon_pembiayaan_konsumtif;

    @BindView(R.id.tf_margin_pertahun_konsumtif)
    TextFieldBoxes tf_margin_pertahun_konsumtif;
    @BindView(R.id.et_margin_pertahun_konsumtif)
    EditText et_margin_pertahun_konsumtif;

    @BindView(R.id.tf_angsuran_konsumtif)
    TextFieldBoxes tf_angsuran_konsumtif;
    @BindView(R.id.et_angsuran_konsumtif)
    EditText et_angsuran_konsumtif;

    @BindView(R.id.tf_rpc_konsumtif)
    TextFieldBoxes tf_rpc_konsumtif;
    @BindView(R.id.et_rpc_konsummtif)
    EditText et_rpc_konsummtif;

    @BindView(R.id.ll_datafinansial)
    LinearLayout ll_datafinansial;

    @BindView(R.id.tv_label_topup)
    TextView tv_label_topup;

    @BindView(R.id.tv_label_takeover)
    TextView tv_label_takeover;

    @BindView(R.id.bt_lanjut_data_finansial)
    Button bt_lanjut_data_finansial;


    BigDecimal rateAnuitas = new BigDecimal(0);


    //variabel buat menghitung berapa field yang udah lolos validasi
    private int jumlahValidasi = 0;
    int idAplikasi = 0;
    String cif, approved,idTujuanPembiayaan;
    AllDataFront superData;


    //VALUE
    private static String val_jenis_tiering = "";

    Long nilaiAngsuranAppel=0l;
    Long nilaiAngsuranAppelKonsumtif=0l;


    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    DataFinansialKpr dataFinansial;
    String tujuanPenggunaanDepan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumer_data_finansial_kpr);

//        //push up when keyboard shown
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ButterKnife.bind(this);

        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        //set data finansial as already read
        appPreferences.setReadDataFinansial("yes");
        superData= (AllDataFront)getIntent().getSerializableExtra("superData");

        backgroundStatusBar();

        tv_page_title.setText("Data Finansial");

        idAplikasi = Integer.parseInt(getIntent().getStringExtra("idAplikasi"));
        cif = getIntent().getStringExtra("cif");
        approved = getIntent().getStringExtra("approved");

        idTujuanPembiayaan=Integer.toString(getIntent().getIntExtra("idTujuanPembiayaan",0));
        tujuanPenggunaanDepan=getIntent().getStringExtra("tujuanPembiayaan");

        ImageView backToolbar = findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataFinansialKprActivity.this, PutusanFrontMenuKpr.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

            }
        });

        bt_lanjut_data_finansial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DataFinansialKprActivity.this, AgunanTerikatActivity.class);
                intent.putExtra("cif", superData.getCif());
                intent.putExtra("idAplikasi", superData.getIdAplikasi());
                intent.putExtra("tujuanPembiayaan", superData.getTujuanPembiayaan());
                intent.putExtra("jw", Integer.parseInt(superData.getJw()));
                intent.putExtra("plafond", superData.getPlafond());
                intent.putExtra("superData",superData);
                startActivity(intent);


            }
        });



        //disable edittexts
        AppUtil.disableEditTexts(ll_datafinansial);

        loadData();

        otherViewChanges();


        //rupiah formatting
        et_angsuran_pinjaman_eksisting_1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_angsuran_pinjaman_eksisting_1));
        et_nilai_permohonan_pembiayaan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilai_permohonan_pembiayaan));
        et_gaji_pokok.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_gaji_pokok));
        et_tunjangan_tetap_lainnya.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_tunjangan_tetap_lainnya));
        et_penghasilan_bersih.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_penghasilan_bersih));
        et_harga_beli.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_harga_beli));
        et_jumlah_plafon_pembiayaan_diusulkan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_jumlah_plafon_pembiayaan_diusulkan));
        et_angsuran.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_angsuran));
        et_kewajiban_lain.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_kewajiban_lain));
        et_uang_muka.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_uang_muka));
        et_jumlah_plafon_pembiayaan_konsumtif.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_jumlah_plafon_pembiayaan_konsumtif));
        et_angsuran_konsumtif.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_angsuran_konsumtif));


        //disable edittexts
        et_angsuran_pinjaman_eksisting_1.setFocusable(false);
        et_penghasilan_bersih.setFocusable(false);
//        et_margin_pertahun.setFocusable(false);
        et_rpc.setFocusable(false);
        et_angsuran.setFocusable(false);
        et_tujuan_penggunaan.setFocusable(false);
        et_nilai_permohonan_pembiayaan.setFocusable(false);
        et_uang_muka.setFocusable(false);
        et_jangka_waktu_qardh.setFocusable(false);

        et_angsuran_konsumtif.setFocusable(false);
        et_rpc_konsummtif.setFocusable(false);
//        et_margin_pertahun_konsumtif.setFocusable(false);
//        et_jumlah_plafon_pembiayaan_konsumtif.setFocusable(false);


    }

    private void setData() {



        textChangedForDataFinansial();





//ini ambil dari keyvalue pantekan, dan dia tidak dinamis, jadi di c0mment aje
//        et_tujuan_penggunaan.setText(KeyValue.getKeyTujuanPenggunaanKmg(dataFinansial.getIDTUJUAN()));

        //ini ngambil dari halaman hotprospek detail, jadi udah sesuai dari respon DB
        et_tujuan_penggunaan.setText(tujuanPenggunaanDepan);
        et_gaji_pokok.setText(dataFinansial.getoMZETPERBULAN());
        et_rumah_keberapa.setText(dataFinansial.getrUMAHKEBERAPA());


//        et_nilai_permohonan_pembiayaan.setText(AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPLAFONDINDUK())); //ambil dari pipeline sesuai pengajuan
//
//        String parsedTunjanganTetapLainnya=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getpENGHASILANTETAPLAIN());
//        String parsedAngsuranPinjamanEksisting=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getaNGSURAN());


        et_tunjangan_tetap_lainnya.setText(String.valueOf(dataFinansial.getpENGHASILANTETAPLAIN()));
        if (et_tunjangan_tetap_lainnya.getText().toString().equalsIgnoreCase("000")) {
            et_tunjangan_tetap_lainnya.setText("0");
        }

        //for some reason calling datafinansial.getaNGSURAN returns 0 eventhough it has already been set in load data with a value.
        //this is a workaround for now
//        Log.d("masbaydatafinansial",String.valueOf(dataFinansial.getaNGSURAN()));
        et_angsuran_pinjaman_eksisting_1.setText(String.valueOf(dataFinansial.getaNGSURAN()));
        if (et_angsuran_pinjaman_eksisting_1.getText().toString().equalsIgnoreCase("000")) {
            et_angsuran_pinjaman_eksisting_1.setText("0");
        }



//        et_margin_pertahun.setText(dataFinansial.); //ini ambil dari data instansi


        //referensi buat bigdecimal
//        valBd_grossProfitMargin = (valBd_pendapatanUsaha.subtract(valBd_hargapokokPenjualan)).divide(valBd_pendapatanUsaha, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP);


        //set margin dari service
        //margin biasa
        if(dataFinansial.getSUKU_MARGIN()!=null&& !dataFinansial.getSUKU_MARGIN().equalsIgnoreCase("0")){
            et_margin_pertahun.setText(dataFinansial.getSUKU_MARGIN());

        }


        final BigDecimal penghasilanBersih = (new BigDecimal(dataFinansial.getoMZETPERBULAN()).add(new BigDecimal(dataFinansial.getpENGHASILANTETAPLAIN()))).subtract(new BigDecimal(dataFinansial.getaNGSURAN()));
        et_penghasilan_bersih.setText(penghasilanBersih.toString()); //ini kalkulasi gaji pokok + penghasilan lain - angsuran eksisting


        et_jangka_waktu.setText(dataFinansial.getjANGKAWAKTU());


//        et_jumlah_plafon_pembiayaan_diusulkan.setText(dataFinansial.getpLAFONDYANGDIUSULKAN());
//        et_harga_beli.setText(dataFinansial.getPERMOHONANKREDIT());
//        et_rpc.setText(); //ini dari data instansi

//        et_jumlah_plafon_pembiayaan_diusulkan.setText(AppUtil.parseRupiah(dataFinansial.getpLAFONDYANGDIUSULKAN()));
//        et_harga_beli.setText(AppUtil.parseRupiah(dataFinansial.getPERMOHONANKREDIT()));

//        String parsedJumlahPlafon=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getpLAFONDYANGDIUSULKAN());
//        String parsedHargaBeli=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPERMOHONANKREDIT());
//        String parsedPlafondInduk=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPLAFONDINDUK());

        et_jumlah_plafon_pembiayaan_diusulkan.setText(String.valueOf(dataFinansial.getpLAFONDYANGDIUSULKAN()));
        if (et_jumlah_plafon_pembiayaan_diusulkan.getText().toString().equalsIgnoreCase("000")) {
            et_jumlah_plafon_pembiayaan_diusulkan.setText("0");
        }


        et_harga_beli.setText(String.valueOf(dataFinansial.gethARGAPROPERTY()));
        if (et_harga_beli.getText().toString().equalsIgnoreCase("000")) {
            et_harga_beli.setText("0");
        }



        et_nilai_permohonan_pembiayaan.setText(String.valueOf(dataFinansial.getpERMOHONANKREDIT()));
        et_kewajiban_lain.setText(String.valueOf(dataFinansial.getbIAYALAINLAIN()));
        et_tunjangan_tetap_lainnya.setText(String.valueOf(dataFinansial.getpENGHASILANTETAPLAIN()));


        nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())),Integer.parseInt(et_jangka_waktu.getText().toString()));

        et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));

        //ini ngitung RPC dengan nilai dari appel
        BigDecimal rpcValue=new BigDecimal(nilaiAngsuranAppel).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())),2, RoundingMode.HALF_UP);


        et_rpc.setText(String.valueOf(rpcValue));



    }

    private void textChangedForDataFinansial() {

        Log.d("baris 383", "mengakses kelas ini");

        et_gaji_pokok.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    if (s.length() > 0) {

                        //perubahan penghasilan bersih
                        BigDecimal penghasilanBersih = (new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));

                        et_penghasilan_bersih.setText(penghasilanBersih.toString());


                    } else {
                        BigDecimal penghasilanBersihZero = new BigDecimal("0").subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString()))));

                        et_penghasilan_bersih.setText(penghasilanBersihZero.toString());

                    }
                } catch (Exception e) {
                    Log.d("exceptional 412", e.getMessage());
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

                try {
                    if (s.length() > 0 && et_kewajiban_lain.getText().length() > 0) {
                        BigDecimal penghasilanBersih = (new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));

                        et_penghasilan_bersih.setText(penghasilanBersih.toString());
                    } else {
                        BigDecimal penghasilanBersihZero = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString())).add(new BigDecimal("0")));

                        et_penghasilan_bersih.setText(penghasilanBersihZero.toString());

                    }

                } catch (Exception e) {
                    Log.d("exceptional 454", e.getMessage());
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

                try {


                    if (s.length() > 0 && et_penghasilan_bersih.getText().length() > 0) {
                        BigDecimal penghasilanBersih = (new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));

                        et_penghasilan_bersih.setText(penghasilanBersih.toString());
                    } else {
                        BigDecimal penghasilanBersihZero = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString())).add(new BigDecimal("0")));

                        et_penghasilan_bersih.setText(penghasilanBersihZero.toString());

                    }

                } catch (Exception e) {
                    Log.d("exceptional 496", e.getMessage());
                }


            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_kewajiban_lain.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false && et_kewajiban_lain.getText().toString().equalsIgnoreCase("")) {
                    et_kewajiban_lain.setText("0");
                }
            }
        });

        et_tunjangan_tetap_lainnya.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false && et_tunjangan_tetap_lainnya.getText().toString().equalsIgnoreCase("")) {
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

                try {


                    //set rate anuitas
//                    Double ratePerTahun = Double.parseDouble(et_margin_pertahun.getText().toString()) / 100 / 12;
//                    BigDecimal pangkatPembagi = (new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
//                            .getText().toString()));
//
//                    BigDecimal pengaliPlafon = new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi, 15, RoundingMode.HALF_UP))), 15, RoundingMode.HALF_UP).setScale(15, RoundingMode.HALF_UP);
//                    ;
//
////        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);
//
//                    BigDecimal Angsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2, RoundingMode.HALF_UP);
//
//                    et_angsuran.setText(String.valueOf(Angsuran));

                    //ini ngitung angsuran dari rumus appel, jadi tipe datanya long bro, buset dah

                    if(Float.parseFloat(et_margin_pertahun.getText().toString())!=0f&&!dataFinansial.getjANGKAWAKTU().equalsIgnoreCase("0")){
                        nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())),Integer.parseInt(et_jangka_waktu
                                .getText().toString()));

                        et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
                    }

                } catch (Exception e) {
                    Log.d("exceptional 560", e.getMessage());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_jumlah_plafon_pembiayaan_diusulkan
                .addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        try {


                            //INI NGITUNG ANGSURAN DENGAN BIG DECIMAL

                            //perubahan angsuran
                            //set rate anuitas
//                            Double ratePerTahun = Double.parseDouble(et_margin_pertahun.getText().toString()) / 100 / 12;
//                            BigDecimal pangkatPembagi = (new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
//                                    .getText().toString()));
//
//                            BigDecimal pengaliPlafon = new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi, 15, RoundingMode.HALF_UP))), 15, RoundingMode.HALF_UP).setScale(15, RoundingMode.HALF_UP);
//                            ;
//
////        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);
//
//                            BigDecimal Angsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2, RoundingMode.HALF_UP);
//
//                            et_angsuran.setText(String.valueOf(Angsuran));


                            //ini ngitung angsuran dari rumus appel, jadi tipe datanya long bro, buset dah

                            nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())),Integer.parseInt(et_jangka_waktu
                                    .getText().toString()));

                            et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));


                            //jika harga beli dan plafon tidak kosong
                            if (!et_harga_beli.getText().toString().isEmpty() && !et_jumlah_plafon_pembiayaan_diusulkan.getText().toString().isEmpty()) {
                                //perubahan uang muka
                                BigDecimal uangMukaBD = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_harga_beli.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())));
                                et_uang_muka.setText(uangMukaBD.toString());
                            }


                        } catch (Exception e) {
                            Log.d("exceptional 599", e.getMessage());
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

        et_harga_beli
                .addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        try {

                            //jika harga beli dan plafon tidak kosong
                            if (!et_harga_beli.getText().toString().isEmpty() && !et_jumlah_plafon_pembiayaan_diusulkan.getText().toString().isEmpty()) {


                                //perubahan uang muka
                                BigDecimal uangMukaBD = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_harga_beli.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())));
                                et_uang_muka.setText(uangMukaBD.toString());
                            }


                        } catch (Exception e) {
                            Log.d("exceptional 599", e.getMessage());
                        }

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

                try {


                    if (s != null && s.length() > 0 && !new BigDecimal(s.toString()).equals(0)) {

                        BigDecimal rpcValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran.getText().toString())).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())), 2, RoundingMode.HALF_UP);

                        et_rpc.setText(String.valueOf(rpcValue));
                    }
                } catch (Exception e) {
                    Log.d("exceptional 630", e.getMessage());
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

                BigDecimal rpcValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran.getText().toString())).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())), 2, RoundingMode.HALF_UP);

                et_rpc.setText(String.valueOf(rpcValue));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //memastikan bahwa plafon tidak bisa diedit sebelum jangka waktu diisi, karna dalam rumus angsuran, ada pembagian dengan jangka waktu, jadi kalo diisi 0 nanti dia error division by zero
        et_jumlah_plafon_pembiayaan_diusulkan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && et_jangka_waktu.getText().toString().isEmpty()) {
                    tf_jangka_waktu.setError("Harap isi jangka waktu", true);
                    AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(R.id.content), "Harap isi jangka waktu sebelum mengubah plafon diusulkan");
                    Log.d("ekiduper"," masuk kedalam non takeover");
                }

                if (hasFocus && et_margin_pertahun.getText().toString().isEmpty()) {
                    tf_margin_pertahun.setError("Harap isi margin terlebih dahulu", true);
                    AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(R.id.content), "Harap isi margin sebelum mengubah plafon diusulkan");
                    Log.d("ekiduper"," masuk kedalam non takeover");
                }


            }
        });

        et_jangka_waktu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s != null && s.length() > 0 && Integer.parseInt(s.toString()) != 0) {

                    //kalau dari qanun, tidak ambil margin dari margin rate, tapi sakarepe dewe AOnya, di validasi di service



                    Long totalTakeover=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));

                    if(!et_margin_pertahun.getText().toString().isEmpty()){
                        nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),totalTakeover,Integer.parseInt(et_jangka_waktu.getText().toString()));

                        et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
                    }




                } else {
                    if (s.length() > 0) {
                        Toast.makeText(DataFinansialKprActivity.this, "Jangka waktu tidak boleh 0 ", Toast.LENGTH_SHORT).show();
                        et_jangka_waktu.setText("1");

                        et_jangka_waktu.setSelection(et_jangka_waktu.getText().length());
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











    public void loadData() {
        loading.setVisibility(View.VISIBLE);


        ReqKelengkapanDokumen req = new ReqKelengkapanDokumen();
        req.setId_aplikasi(idAplikasi);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryDataFinansialKpr(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
//                            Toast.makeText(DataFinansialKprActivity.this, "Id aplikasi masih hardcode", Toast.LENGTH_SHORT).show();
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            loading.setVisibility(View.GONE);
                            String dataAngsuranString = "";
                            String dataFinansialString = response.body().getData().get("prescoring").toString();
//                            Log.d("pirmen",response.body().getData_qanun().toString());
                            Gson gson = new Gson();

                            dataFinansial = gson.fromJson(dataFinansialString, DataFinansialKpr.class);



                            //kalau omzet perbulan 0, berarti belum pernah ngisi data finansial sebelumnya, maka edittext tidak diisi dulu, hanya beberapa field yang bisa diisi
                            if (dataFinansial.getoMZETPERBULAN()==null||dataFinansial.getoMZETPERBULAN().equalsIgnoreCase("0")) {
                                //pakai textchanged khusus jika pembiayaan takeover, karena total plafonnya beda

                                textChangedForDataFinansial();

                                //ini ngambil dari halaman hotprospek detail, jadi udah sesuai dari respon DB
                                et_tujuan_penggunaan.setText(tujuanPenggunaanDepan);
                                et_nilai_permohonan_pembiayaan.setText(dataFinansial.getpLAFONDYANGDIUSULKAN().toString());
                                et_harga_beli.setText(dataFinansial.gethARGAPROPERTY().toString());
                                et_angsuran_pinjaman_eksisting_1.setText(dataFinansial.getaNGSURAN().toString());
                                et_jangka_waktu_qardh.setText("14");
//                                    et_jumlah_plafon_pembiayaan_diusulkan.setText(dataFinansial.getPLAFONDINDUK().toString());

                            } else {
                                setData();
                            }


//                                Log.d("firmansah",dataFinansialString);
//                                Log.d("firmansah isdebest",dataFinansial.getoMZETPERBULAN());


                        } else {
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }


    public long hitungAngsuranAppel(float _rate, long _plafond, int jk_waktu){
        return (long)((long)(((_rate / 12) / 100) * _plafond) / (1 - (1 / Math.pow((1.00 + ((_rate / 100) / 12.00)), jk_waktu))));
    }

    private void otherViewChanges(){
        if(superData.getFidStatus().equalsIgnoreCase("163")){
            bt_lanjut_data_finansial.setVisibility(View.INVISIBLE);
        }
    }


}
