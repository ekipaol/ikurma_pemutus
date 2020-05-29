package com.application.bris.brisi_pemutus.page_konsumer_purna.page_konsumer_purna.data_finansial;

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
import com.application.bris.brisi_pemutus.api.model.ParseResponseDataInstansi;
import com.application.bris.brisi_pemutus.api.model.ParseResponseError;
import com.application.bris.brisi_pemutus.api.model.Error;
import com.application.bris.brisi_pemutus.api.model.request.id_aplikasi.ReqIdAplikasi;
import com.application.bris.brisi_pemutus.api.model.request.kelengkapan_dokumen.ReqKelengkapanDokumen;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_finansial.DataFinansial;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.data_finansial.DataFinansialKonsumerKmgActivity;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.front_menu.PutusanFrontMenuKmg;
import com.application.bris.brisi_pemutus.page_putusan.agunan.DialogKeyValue;
import com.application.bris.brisi_pemutus.page_putusan.agunan_retry.AgunanTerikatActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;


public class DataFinansialPurnaActivity extends AppCompatActivity  {

    @BindView(R.id.tb_regular)
    Toolbar toolbar;
    @BindView(R.id.tv_page_title)
    TextView tv_page_title;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.btn_hitung_ijk)
    Button btn_hitung_ijk;
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

    @BindView(R.id.tf_asuransi_penjaminan)
    TextFieldBoxes tf_asuransi_penjaminan;
    @BindView(R.id.et_asuransi_penjaminan)
    EditText et_asuransi_penjaminan;

    @BindView(R.id.tf_fee_pihak_ketiga)
    TextFieldBoxes tf_fee_pihak_ketiga;
    @BindView(R.id.et_fee_pihak_ketiga)
    EditText et_fee_pihak_ketiga;

    @BindView(R.id.tf_nominal_fee)
    TextFieldBoxes tf_nominal_fee;
    @BindView(R.id.et_nominal_fee)
    EditText et_nominal_fee;

    @BindView(R.id.tf_premi_asuransi)
    TextFieldBoxes tf_premi_asuransi;
    @BindView(R.id.et_premi_asuransi)
    EditText et_premi_asuransi;

    @BindView(R.id.tf_rate_ijk)
    TextFieldBoxes tf_rate_ijk;
    @BindView(R.id.et_rate_ijk)
    EditText et_rate_ijk;

    @BindView(R.id.tf_rate_ijk2)
    TextFieldBoxes tf_rate_ijk2;
    @BindView(R.id.et_rate_ijk2)
    EditText et_rate_ijk2;


    @BindView(R.id.ll_datafinansial)
    LinearLayout ll_datafinansial;

    @BindView(R.id.bt_lanjut_data_finansial)
    Button bt_lanjut_data_finansial;

    int maxJw = 0;
    Double maxMargin = 0d;

    BigDecimal rateAnuitas = new BigDecimal(0);


    //variabel buat menghitung berapa field yang udah lolos validasi
    private int jumlahValidasi = 0;
    int idAplikasi = 0;
    int umur=0;
    boolean paramIjkBerubah=false;
    String cif, approved;

    //value untuk dropdown
    String val_asuransi="";


    //VALUE
    private static String val_jenis_tiering = "";

    Long nilaiAngsuranAppel=0l;
    Long nilaiAngsuranAppelKonsumtif=0l;



    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    DataFinansial dataFinansial;
    DataFinansial dataInstansi;
    DataFinansial dataQanun;
    DataFinansial dataAngsuran;
    AllDataFront superData;

    String tujuanPenggunaanDepan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumer_data_finansial_purna);

//        //push up when keyboard shown
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ButterKnife.bind(this);

        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        //set data finansial as already read
        appPreferences.setReadDataFinansial("yes");
        superData= (AllDataFront)getIntent().getSerializableExtra("superData");
//        setGps();
        backgroundStatusBar();
//        checkCollapse();
        tv_page_title.setText("Data Finansial");
        ImageView backToolbar = findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataFinansialPurnaActivity.this, PutusanFrontMenuKmg.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

            }
        });

        //real intent
        idAplikasi = Integer.parseInt(superData.getIdAplikasi());
        cif =superData.getCif();

        et_tujuan_penggunaan.setText(superData.getTujuanPembiayaan());

        //pantekan intent
//        idAplikasi = 101928;
//        cif = "5991";
//        approved = "no";
//        tujuanPenggunaanDepan="Beli rumah sekaligus isinya";

//        Toast.makeText(this, "Ada Pantekan intent", Toast.LENGTH_SHORT).show();
//



            AppUtil.disableEditTexts(ll_datafinansial);
            btn_send.setVisibility(View.GONE);

        bt_lanjut_data_finansial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DataFinansialPurnaActivity.this, AgunanTerikatActivity.class);
                intent.putExtra("cif", superData.getCif());
                intent.putExtra("idAplikasi", superData.getIdAplikasi());
                intent.putExtra("tujuanPembiayaan", superData.getTujuanPembiayaan());
                intent.putExtra("jw", Integer.parseInt(superData.getJw()));
                intent.putExtra("plafond", superData.getPlafond());
                intent.putExtra("superData",superData);
                startActivity(intent);


            }
        });

        loadData();


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
        et_nominal_fee.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nominal_fee));
        et_premi_asuransi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_premi_asuransi));
        et_fee_pihak_ketiga.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_fee_pihak_ketiga));


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
        et_margin_pertahun_konsumtif.setFocusable(false);
        et_asuransi_penjaminan.setFocusable(false);
        et_nominal_fee.setFocusable(false);
        et_premi_asuransi.setFocusable(false);
        et_rate_ijk.setFocusable(false);
        et_rate_ijk2.setFocusable(false);
        et_fee_pihak_ketiga.setFocusable(false);
//        et_jumlah_plafon_pembiayaan_konsumtif.setFocusable(false);




    }

    private void setData() {


        //pakai textchanged khusus jika pembiayaan takeover, karena total plafonnya beda
        if (dataFinansial.getIDTUJUAN().equalsIgnoreCase("91")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")) {
            Log.d("marsupilamitujuan",dataFinansial.getIDTUJUAN());
            Log.d("marsupilami","masuk takeover");
            textChangedForDataFinansialTakeOver();
        }
        else{
            Log.d("marsupilamitujuan",dataFinansial.getIDTUJUAN());
            Log.d("marsupilami","masuk non-takeover");
            textChangedForDataFinansial();
        }




        //isi kolom hari qardh kalau takeover
        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("91")){
            tf_jangka_waktu_qardh.setVisibility(View.VISIBLE);
            et_jangka_waktu_qardh.setText("14");
            et_harga_beli.setText("0");
        }

        //kalau takeover topup, harga beli jangan diilangin
        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")){
            tf_jangka_waktu_qardh.setVisibility(View.VISIBLE);
            et_jangka_waktu_qardh.setText("14");
        }

//ini ambil dari keyvalue pantekan, dan dia tidak dinamis, jadi di c0mment aje
//        et_tujuan_penggunaan.setText(KeyValue.getKeyTujuanPenggunaanKmg(dataFinansial.getIDTUJUAN()));

        //ini ngambil dari halaman hotprospek detail, jadi udah sesuai dari respon DB
        et_tujuan_penggunaan.setText(superData.getTujuanPembiayaan());
        et_gaji_pokok.setText(dataFinansial.getOMZETPERBULAN());

        et_rate_ijk.setText(dataFinansial.getRATE1());
        et_rate_ijk2.setText(dataFinansial.getRATE2());
        et_premi_asuransi.setText(dataFinansial.getIjk());
        et_nominal_fee.setText(dataFinansial.getBIAYA_FEE());
        et_asuransi_penjaminan.setText(dataFinansial.getASURANSI_PENJAMINAN());

        //set fee kalau otomatis
        et_fee_pihak_ketiga.setText(dataFinansial.getFEE());
//        Log.d("nilai fee",dataFinansial.getFEE());







//        et_nilai_permohonan_pembiayaan.setText(AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPLAFONDINDUK())); //ambil dari pipeline sesuai pengajuan
//
//        String parsedTunjanganTetapLainnya=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPENGHASILANTETAPLAIN());
//        String parsedAngsuranPinjamanEksisting=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getANGSURAN());


        et_tunjangan_tetap_lainnya.setText(String.valueOf(dataFinansial.getPENGHASILANTETAPLAIN()));
        if (et_tunjangan_tetap_lainnya.getText().toString().equalsIgnoreCase("000")) {
            et_tunjangan_tetap_lainnya.setText("0");
        }

        //for some reason calling datafinansial.getangsuran returns 0 eventhough it has already been set in load data with a value.
        //this is a workaround for now
//        Log.d("masbaydatafinansial",String.valueOf(dataFinansial.getANGSURAN()));
        et_angsuran_pinjaman_eksisting_1.setText(String.valueOf(dataFinansial.getANGSURAN()));
        if (et_angsuran_pinjaman_eksisting_1.getText().toString().equalsIgnoreCase("000")) {
            et_angsuran_pinjaman_eksisting_1.setText("0");
        }



//        et_margin_pertahun.setText(dataFinansial.); //ini ambil dari data instansi


        //referensi buat bigdecimal
//        valBd_grossProfitMargin = (valBd_pendapatanUsaha.subtract(valBd_hargapokokPenjualan)).divide(valBd_pendapatanUsaha, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP);

        final BigDecimal penghasilanBersih = (new BigDecimal(dataFinansial.getOMZETPERBULAN()).add(new BigDecimal(dataFinansial.getPENGHASILANTETAPLAIN()))).subtract(new BigDecimal(dataFinansial.getANGSURAN()));
        et_penghasilan_bersih.setText(penghasilanBersih.toString()); //ini kalkulasi gaji pokok + penghasilan lain - angsuran eksisting


        et_jangka_waktu.setText(dataFinansial.getJANGKAWAKTU());
        et_jangka_waktu_takeover.setText(dataFinansial.getJANGKA_WAKTU_TO());

//        et_jumlah_plafon_pembiayaan_diusulkan.setText(dataFinansial.getPLAFONDYANGDIUSULKAN());
//        et_harga_beli.setText(dataFinansial.getPERMOHONANKREDIT());
//        et_rpc.setText(); //ini dari data instansi

//        et_jumlah_plafon_pembiayaan_diusulkan.setText(AppUtil.parseRupiah(dataFinansial.getPLAFONDYANGDIUSULKAN()));
//        et_harga_beli.setText(AppUtil.parseRupiah(dataFinansial.getPERMOHONANKREDIT()));

//        String parsedJumlahPlafon=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPLAFONDYANGDIUSULKAN());
//        String parsedHargaBeli=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPERMOHONANKREDIT());
//        String parsedPlafondInduk=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPLAFONDINDUK());
        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("91")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
            et_jumlah_plafon_pembiayaan_diusulkan.setText(String.valueOf(dataFinansial.getPLAFONDTAKEOVER()));
            if (et_jumlah_plafon_pembiayaan_diusulkan.getText().toString().equalsIgnoreCase("000")) {
                et_jumlah_plafon_pembiayaan_diusulkan.setText("0");
            }
        }
        else{
            et_jumlah_plafon_pembiayaan_diusulkan.setText(String.valueOf(dataFinansial.getPLAFONDYANGDIUSULKAN()));
            if (et_jumlah_plafon_pembiayaan_diusulkan.getText().toString().equalsIgnoreCase("000")) {
                et_jumlah_plafon_pembiayaan_diusulkan.setText("0");
            }
        }


        //kalau takeover, get data konsumtif
        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("91")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
            et_jumlah_plafon_pembiayaan_konsumtif.setText(String.valueOf(dataFinansial.getPLAFOND_KONSUMTIF()));
            if (et_jumlah_plafon_pembiayaan_konsumtif.getText().toString().equalsIgnoreCase("000")) {
                et_jumlah_plafon_pembiayaan_konsumtif.setText("0");
            }
        }

        et_harga_beli.setText(String.valueOf(dataFinansial.getHARGAPROPERTY()));
        if (et_harga_beli.getText().toString().equalsIgnoreCase("000")) {
            et_harga_beli.setText("0");
        }

        et_nilai_permohonan_pembiayaan.setText(String.valueOf(dataFinansial.getPLAFONDINDUK()));
        et_kewajiban_lain.setText(String.valueOf(dataFinansial.getBIAYALAINLAIN()));




        //set margin rate
        if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) > maxJw) {
            Toast.makeText(DataFinansialPurnaActivity.this, "Jangka waktu tidak boleh melebihi " + maxJw + " bulan", Toast.LENGTH_SHORT).show();

//            if(Integer.toString(maxMargin).length()<=2){
//                et_margin_pertahun.setText(Integer.toString(maxMargin).substring(0,2)+"."+Integer.toString(maxMargin).substring(2));
//            }
            et_margin_pertahun.setText(Double.toString(maxMargin));
            et_jangka_waktu_takeover.setText(Integer.toString(maxJw));
            et_jangka_waktu_takeover.setSelection(et_jangka_waktu_takeover.getText().length());

            et_margin_pertahun_konsumtif.setText(Double.toString(maxMargin));
            et_jangka_waktu.setText(Integer.toString(maxJw));
            et_jangka_waktu.setSelection(et_jangka_waktu.getText().length());
        }

        if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW1()) && !dataFinansial.getMAXJW1().equalsIgnoreCase("0")) {
            et_margin_pertahun.setText(dataFinansial.getMARGINRATE1());
        } else if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW2()) && !dataFinansial.getMAXJW2().equalsIgnoreCase("0")) {
            et_margin_pertahun.setText(dataFinansial.getMARGINRATE2());
        } else if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW3()) && !dataFinansial.getMAXJW3().equalsIgnoreCase("0")) {
            et_margin_pertahun.setText(dataFinansial.getMARGINRATE3());
        } else if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW4()) && !dataFinansial.getMAXJW4().equalsIgnoreCase("0")) {
            et_margin_pertahun.setText(dataFinansial.getMARGINRATE4());
        }

        //khusus takeover topup
        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
            if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW1()) && !dataFinansial.getMAXJW1().equalsIgnoreCase("0")) {
                et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE1());
            } else if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW2()) && !dataFinansial.getMAXJW2().equalsIgnoreCase("0")) {
                et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE2());
            } else if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW3()) && !dataFinansial.getMAXJW3().equalsIgnoreCase("0")) {
                et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE3());
            } else if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW4()) && !dataFinansial.getMAXJW4().equalsIgnoreCase("0")) {
                et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE4());
            }
        }




//         rateAnuitas=(new BigDecimal(dataFinansial.getPLAFOND_INDUK()).multiply((new BigDecimal(et_margin_pertahun.getText().toString()).divide(new BigDecimal("12")))).multiply((new BigDecimal(et_margin_pertahun.getText().toString()).divide(new BigDecimal("12"),2, RoundingMode.HALF_UP))).divide(new BigDecimal("1").subtract((new BigDecimal(1).add((new BigDecimal(et_margin_pertahun.getText().toString()).divide(new BigDecimal("12")))).pow(Integer.parseInt(et_jangka_waktu.getText().toString())))), 2, RoundingMode.HALF_UP)).setScale(1, BigDecimal.ROUND_HALF_UP);


//62839225.84
        //set rate anuitas
//        Double ratePerTahun = Double.parseDouble(et_margin_pertahun.getText().toString()) / 100 / 12;
//
//        //ini rumus aslinya, karena double gakbisa menampung hasil perpangkatan yang besar, jadi dipecah ke big decimal dibawah
////        Double pengaliPlafon=ratePerTahun/(1-(1/(Math.pow(1+ratePerTahun,Integer.parseInt(et_jangka_waktu.getText().toString())))));
//
//        BigDecimal pangkatPembagi = (new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
//                .getText().toString()));
//
//        BigDecimal pengaliPlafon = new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi, 15, RoundingMode.HALF_UP))), 15, RoundingMode.HALF_UP).setScale(15, RoundingMode.HALF_UP);
//        ;
//
////        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);
//
//        BigDecimal Angsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2, RoundingMode.HALF_UP);
//
//        et_angsuran.setText(String.valueOf(Angsuran));
//
//
////        Toast.makeText(this, String.valueOf(Angsuran), Toast.LENGTH_LONG).show();
////        Log.d("anuitas",String.valueOf(rateAnuitas));
//
//        //set rpc
//        BigDecimal rpcValue = Angsuran.multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())), 2, RoundingMode.HALF_UP);
//
//        et_rpc.setText(String.valueOf(rpcValue));

        //ini ngitung angsuran dari rumus appel, jadi tipe datanya long bro, buset dah



        //khusus takeover topup, memang ngeset angsuran takeover dan topup
        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
            nilaiAngsuranAppelKonsumtif=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun_konsumtif.getText().toString()),Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString())),Integer.parseInt(et_jangka_waktu.getText().toString()));



            //ini ngitung RPC dengan nilai dari appel
            BigDecimal rpcValueKonsumtif=new BigDecimal(nilaiAngsuranAppelKonsumtif).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())),2, RoundingMode.HALF_UP);

            et_rpc_konsummtif.setText(String.valueOf(rpcValueKonsumtif));

            et_angsuran_konsumtif.setText(String.valueOf(nilaiAngsuranAppelKonsumtif));



            nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())),Integer.parseInt(et_jangka_waktu_takeover.getText().toString()));

            //ini ngitung RPC dengan nilai dari appel
            BigDecimal rpcValue=new BigDecimal(nilaiAngsuranAppel).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())),2,RoundingMode.HALF_UP);


            et_rpc.setText(String.valueOf(rpcValue));

            et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));


        }
        else{
            nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())),Integer.parseInt(et_jangka_waktu
                    .getText().toString()));

            et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));

            //ini ngitung RPC dengan nilai dari appel
            BigDecimal rpcValue=new BigDecimal(nilaiAngsuranAppel).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())),2,RoundingMode.HALF_UP);


            et_rpc.setText(String.valueOf(rpcValue));
        }





//        Toast.makeText(this, String.valueOf(Angsuran), Toast.LENGTH_LONG).show();
//        Log.d("anuitas",String.valueOf(rateAnuitas));

        //set rpc
//        BigDecimal rpcValue=Angsuran.multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())),2,RoundingMode.HALF_UP);








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

                    nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())),Integer.parseInt(et_jangka_waktu
                            .getText().toString()));

                    et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
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

                        //kalau plafond berubah, berarti param berubah
                        paramIjkBerubah=true;

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
                    AppUtil.notiferror(DataFinansialPurnaActivity.this, findViewById(R.id.content), "Harap isi jangka waktu sebelum mengubah plafon diusulkan");
                }
            }
        });

        et_jangka_waktu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //kalau tenor berubah, berarti param berubah
                paramIjkBerubah=true;

                Log.d("max jw", Integer.toString(maxJw));
                if (s != null && s.length() > 0 && Integer.parseInt(s.toString()) != 0) {
                    if (Integer.parseInt(et_jangka_waktu.getText().toString()) > maxJw) {
                        Toast.makeText(DataFinansialPurnaActivity.this, "Jangka waktu tidak boleh melebihi " + maxJw + " bulan", Toast.LENGTH_SHORT).show();
                        et_jangka_waktu.setText(Integer.toString(maxJw));
                        et_jangka_waktu.setSelection(et_jangka_waktu.getText().length());
                    }


                    if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW1()) && !dataFinansial.getMAXJW1().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE1().equalsIgnoreCase("0")) {
                        et_margin_pertahun.setText(dataFinansial.getMARGINRATE1());
                    } else if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW2()) && !dataFinansial.getMAXJW2().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE2().equalsIgnoreCase("0")) {
                        et_margin_pertahun.setText(dataFinansial.getMARGINRATE2());
                    } else if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW3()) && !dataFinansial.getMAXJW3().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE3().equalsIgnoreCase("0")) {
                        et_margin_pertahun.setText(dataFinansial.getMARGINRATE3());
                    } else if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW4()) && !dataFinansial.getMAXJW4().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE4().equalsIgnoreCase("0")) {
                        et_margin_pertahun.setText(dataFinansial.getMARGINRATE4());
                    }
                } else {
                    if (s.length() > 0) {
                        Toast.makeText(DataFinansialPurnaActivity.this, "Jangka waktu tidak boleh 0 ", Toast.LENGTH_SHORT).show();
                        et_jangka_waktu.setText("1");

                        et_jangka_waktu.setSelection(et_jangka_waktu.getText().length());
                    }

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        et_fee_pihak_ketiga.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //kalau fee berubah, berarti param berubah
                paramIjkBerubah=true;

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void textChangedForDataFinansialTakeOver() {

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

                    Long totalTakeoverKonsumtif=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));

                    nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),totalTakeoverKonsumtif,Integer.parseInt(et_jangka_waktu.getText().toString()));

                    et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
                } catch (Exception e) {
                    Log.d("exceptional 560", e.getMessage());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_margin_pertahun_konsumtif.addTextChangedListener(new TextWatcher() {
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

                    Long totalTakeoverKonsumtif=(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString())));

                    nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun_konsumtif.getText().toString()),totalTakeoverKonsumtif,Integer.parseInt(et_jangka_waktu.getText().toString()));

                    et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
                } catch (Exception e) {
                    Log.d("exceptional 560", e.getMessage());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_jumlah_plafon_pembiayaan_konsumtif
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

                            Long totalTakeoverKonsumtif=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString()));

                            nilaiAngsuranAppelKonsumtif=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun_konsumtif.getText().toString()),totalTakeoverKonsumtif,Integer.parseInt(et_jangka_waktu.getText().toString()));

                            et_angsuran_konsumtif.setText(String.valueOf(nilaiAngsuranAppelKonsumtif));


                            //jika harga beli dan plafon tidak kosong
                            if (!et_harga_beli.getText().toString().isEmpty() && !et_jumlah_plafon_pembiayaan_konsumtif.getText().toString().isEmpty()) {
                                //perubahan uang muka
                                BigDecimal uangMukaBD = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_harga_beli.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString())));
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

                            Long totalTakeoverKonsumtif=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));

                            nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),totalTakeoverKonsumtif,Integer.parseInt(et_jangka_waktu_takeover.getText().toString()));

                            et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));



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
                            if (!et_harga_beli.getText().toString().isEmpty() && !et_jumlah_plafon_pembiayaan_konsumtif.getText().toString().isEmpty()) {


                                //perubahan uang muka
                                BigDecimal uangMukaBD = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_harga_beli.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString())));
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

                        BigDecimal rpcKonsumtifValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_konsumtif.getText().toString())).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())), 2, RoundingMode.HALF_UP);

                        et_rpc_konsummtif.setText(String.valueOf(rpcKonsumtifValue));
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

        et_angsuran_konsumtif.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                BigDecimal rpcValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_konsumtif.getText().toString())).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())), 2, RoundingMode.HALF_UP);

                et_rpc_konsummtif.setText(String.valueOf(rpcValue));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //memastikan bahwa plafon tidak bisa diedit sebelum jangka waktu diisi, karna dalam rumus angsuran, ada pembagian dengan jangka waktu, jadi kalo diisi 0 nanti dia error division by zero
        et_jumlah_plafon_pembiayaan_diusulkan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && et_jangka_waktu_takeover.getText().toString().isEmpty()) {
                    tf_jangka_waktu_takeover.setError("Harap isi jangka waktu", true);
                    AppUtil.notiferror(DataFinansialPurnaActivity.this, findViewById(R.id.content), "Harap isi jangka waktu sebelum mengubah plafon diusulkan");
                }

                if (hasFocus && (et_gaji_pokok.getText().toString().isEmpty()||et_gaji_pokok.getText().toString().equalsIgnoreCase("0"))) {
                    tf_gaji_pokok.setError("Harap isi gaji pokok", true);
                    AppUtil.notiferror(DataFinansialPurnaActivity.this, findViewById(R.id.content), "Harap isi gaji pokok sebelum mengubah plafon diusulkan");
                }
            }
        });


        et_jumlah_plafon_pembiayaan_konsumtif.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && et_jangka_waktu.getText().toString().isEmpty()) {
                    tf_jangka_waktu.setError("Harap isi jangka waktu", true);
                    AppUtil.notiferror(DataFinansialPurnaActivity.this, findViewById(R.id.content), "Harap isi jangka waktu sebelum mengubah plafon konsumtif");
                }

                if (hasFocus && (et_gaji_pokok.getText().toString().isEmpty()||et_gaji_pokok.getText().toString().equalsIgnoreCase("0"))) {
                    tf_gaji_pokok.setError("Harap isi gaji pokok", true);
                    AppUtil.notiferror(DataFinansialPurnaActivity.this, findViewById(R.id.content), "Harap isi gaji pokok sebelum mengubah plafon konsumtif");
                }
            }
        });

        et_jangka_waktu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.d("max jw", Integer.toString(maxJw));
                if (s != null && s.length() > 0 && Integer.parseInt(s.toString()) != 0) {
                    if (Integer.parseInt(et_jangka_waktu.getText().toString()) > maxJw) {
                        Toast.makeText(DataFinansialPurnaActivity.this, "Jangka waktu tidak boleh melebihi " + maxJw + " bulan", Toast.LENGTH_SHORT).show();
                        et_jangka_waktu.setText(Integer.toString(maxJw));
                        et_jangka_waktu.setSelection(et_jangka_waktu.getText().length());
                    }


                    if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW1()) && !dataFinansial.getMAXJW1().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE1().equalsIgnoreCase("0")) {
                        et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE1());
                    } else if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW2()) && !dataFinansial.getMAXJW2().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE2().equalsIgnoreCase("0")) {
                        et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE2());
                    } else if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW3()) && !dataFinansial.getMAXJW3().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE3().equalsIgnoreCase("0")) {
                        et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE3());
                    } else if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW4()) && !dataFinansial.getMAXJW4().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE4().equalsIgnoreCase("0")) {
                        et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE4());
                    }
                } else {
                    if (s.length() > 0) {
                        Toast.makeText(DataFinansialPurnaActivity.this, "Jangka waktu tidak boleh 0 ", Toast.LENGTH_SHORT).show();
                        et_jangka_waktu.setText("1");

                        et_jangka_waktu.setSelection(et_jangka_waktu.getText().length());
                    }

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_jangka_waktu_takeover.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.d("max jw", Integer.toString(maxJw));
                if (s != null && s.length() > 0 && Integer.parseInt(s.toString()) != 0) {
                    if (Integer.parseInt(et_jangka_waktu_takeover.getText().toString()) > maxJw) {
                        Toast.makeText(DataFinansialPurnaActivity.this, "Jangka waktu tidak boleh melebihi " + maxJw + " bulan", Toast.LENGTH_SHORT).show();
                        et_jangka_waktu_takeover.setText(Integer.toString(maxJw));
                        et_jangka_waktu_takeover.setSelection(et_jangka_waktu_takeover.getText().length());
                    }


                    if (Integer.parseInt(et_jangka_waktu_takeover.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW1()) && !dataFinansial.getMAXJW1().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE1().equalsIgnoreCase("0")) {
                        et_margin_pertahun.setText(dataFinansial.getMARGINRATE1());
                    } else if (Integer.parseInt(et_jangka_waktu_takeover.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW2()) && !dataFinansial.getMAXJW2().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE2().equalsIgnoreCase("0")) {
                        et_margin_pertahun.setText(dataFinansial.getMARGINRATE2());
                    } else if (Integer.parseInt(et_jangka_waktu_takeover.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW3()) && !dataFinansial.getMAXJW3().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE3().equalsIgnoreCase("0")) {
                        et_margin_pertahun.setText(dataFinansial.getMARGINRATE3());
                    } else if (Integer.parseInt(et_jangka_waktu_takeover.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW4()) && !dataFinansial.getMAXJW4().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE4().equalsIgnoreCase("0")) {
                        et_margin_pertahun.setText(dataFinansial.getMARGINRATE4());
                    }
                } else {
                    if (s.length() > 0) {
                        Toast.makeText(DataFinansialPurnaActivity.this, "Jangka waktu tidak boleh 0 ", Toast.LENGTH_SHORT).show();
                        et_jangka_waktu_takeover.setText("1");

                        et_jangka_waktu_takeover.setSelection(et_jangka_waktu_takeover.getText().length());
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








    public void loadData() {
        loading.setVisibility(View.VISIBLE);


        //real data
        ReqKelengkapanDokumen req = new ReqKelengkapanDokumen();
        req.setId_aplikasi(idAplikasi);

        //pantekan
//        inquiryRPC req=new inquiryRPC(101928);
//        Toast.makeText(DataFinansialPurnaActivity.this, "Id aplikasi masih hardcode", Toast.LENGTH_SHORT).show();
        Call<ParseResponseDataInstansi> call = apiClientAdapter.getApiInterface().inquiryDataFinansialKmg(req);
        call.enqueue(new Callback<ParseResponseDataInstansi>() {
            @Override
            public void onResponse(Call<ParseResponseDataInstansi> call, Response<ParseResponseDataInstansi> response) {
                try {
                    if (response.isSuccessful()) {

                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            loading.setVisibility(View.GONE);
                            String dataAngsuranString = "";
                            String dataFinansialString = response.body().getData().toString();
                            String dataInstansiString = response.body().getDataInstansi().toString();
//                            Log.d("pirmen",response.body().getData_qanun().toString());
                            if (response.body().getData().get("data_angsuran") != null) {
                                dataAngsuranString = response.body().getData().get("data_angsuran").toString();
                            } else {
                                dataAngsuranString = response.body().getDataAngsuran().toString();
                            }

                            Gson gson = new Gson();

                            dataFinansial = gson.fromJson(dataFinansialString, DataFinansial.class);
                            dataInstansi = gson.fromJson(dataInstansiString, DataFinansial.class);
                            dataAngsuran = gson.fromJson(dataAngsuranString, DataFinansial.class);


                            dataFinansial.setANGSURAN(dataAngsuran.getANGSURAN());
//                                Log.d("masbayangsuran",dataAngsuran.getANGSURAN().toString());
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

                            dataFinansial.setFIX_RATE(dataInstansi.getFIX_RATE());
                            dataFinansial.setFIX_RATE(dataInstansi.getJANGKA_WAKTU_MAX());

                            dataFinansial.setFEE(dataInstansi.getFEE());

                            //mengubah margin sesuai dengan fixrate dan max jw, jika dia non embp

                            if(dataFinansial.getFIX_RATE()!=null&&!dataFinansial.getFIX_RATE().isEmpty()){

                                dataFinansial.setMARGINRATE1(dataInstansi.getFIX_RATE());
                                dataFinansial.setMARGINRATE2(dataInstansi.getFIX_RATE());
                                dataFinansial.setMARGINRATE3(dataInstansi.getFIX_RATE());
                                dataFinansial.setMARGINRATE4(dataInstansi.getFIX_RATE());

                                dataFinansial.setMAXJW1(dataInstansi.getJANGKA_WAKTU_MAX());
                                dataFinansial.setMAXJW2(dataInstansi.getJANGKA_WAKTU_MAX());
                                dataFinansial.setMAXJW3(dataInstansi.getJANGKA_WAKTU_MAX());
                                dataFinansial.setMAXJW4(dataInstansi.getJANGKA_WAKTU_MAX());
                            }


                            //CEK KALAU DIA CABANG QANUN, MAKA MARGIN BISA DIEDIT
                            if(dataFinansial.getQanun()!=null&&dataFinansial.getQanun().equalsIgnoreCase("true")){
                                et_margin_pertahun.setFocusable(true);
                                tf_margin_pertahun.setPanelBackgroundColor(getResources().getColor(R.color.colorBgEdittext));
                                et_margin_pertahun_konsumtif.setFocusable(true);
                                tf_margin_pertahun_konsumtif.setPanelBackgroundColor(getResources().getColor(R.color.colorBgEdittext));
                            }
                            else{
//                                et_margin_pertahun.setFocusable(false);
                                tf_margin_pertahun.setPanelBackgroundColor(getResources().getColor(R.color.colorBgEdittext));
                                et_margin_pertahun_konsumtif.setFocusable(false);
                            }

                            //parameter yang berubah kalau pembiayaan takeover murni
                            if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("91")){
                                tf_jumlah_plafon_pembiayaan_diusulkan.setLabelText("Plafond Takeover");
//                                tf_jangka_waktu.setLabelText("Jangka Waktu Konsumtif *");
                                tf_jangka_waktu_qardh.setVisibility(View.VISIBLE);
                                tf_harga_beli.setVisibility(View.GONE);
                                tf_uang_muka.setVisibility(View.GONE);


                            }


                            //parameter yang berubah kalau pembiayaan takeover + konsumtif
                            else if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
                                tf_jumlah_plafon_pembiayaan_diusulkan.setLabelText("Plafond Takeover *");
                                tf_jangka_waktu.setLabelText("Jangka Waktu Konsumtif *");
                                tf_margin_pertahun.setLabelText("Margin Takeover *");
                                tf_angsuran_.setLabelText("Angsuran Takeover *");
                                tf_rpc.setLabelText("RPC Takeover *");

                                tf_jumlah_plafon_pembiayaan_konsumtif.setVisibility(View.VISIBLE);
                                tf_jangka_waktu_qardh.setVisibility(View.VISIBLE);
                                tf_jangka_waktu_takeover.setVisibility(View.VISIBLE);
                                tf_angsuran_konsumtif.setVisibility(View.VISIBLE);
                                tf_rpc_konsumtif.setVisibility(View.VISIBLE);
                                tf_margin_pertahun_konsumtif.setVisibility(View.VISIBLE);

                            }



                            //ngambil max jangka waktu
                            int data_jw1 = Integer.parseInt(dataFinansial.getMAXJW1());
                            int data_jw2 = Integer.parseInt(dataFinansial.getMAXJW2());
                            int data_jw3 = Integer.parseInt(dataFinansial.getMAXJW3());
                            int data_jw4 = Integer.parseInt(dataFinansial.getMAXJW4());

                            Double data_margin1 = Double.parseDouble(dataFinansial.getMARGINRATE1());
                            Double data_margin2 = Double.parseDouble(dataFinansial.getMARGINRATE2());
                            Double data_margin3 = Double.parseDouble(dataFinansial.getMARGINRATE3());
                            Double data_margin4 = Double.parseDouble(dataFinansial.getMARGINRATE4());

                            List<Integer> listJw = new ArrayList<>();
                            listJw.add(data_jw1);
                            listJw.add(data_jw2);
                            listJw.add(data_jw3);
                            listJw.add(data_jw4);

                            List<Double> listMargin = new ArrayList<>();
                            listMargin.add(data_margin1);
                            listMargin.add(data_margin2);
                            listMargin.add(data_margin3);
                            listMargin.add(data_margin4);

                            List<String> listMarginString = new ArrayList<>();
                            listMarginString.add(dataFinansial.getMARGINRATE1());
                            listMarginString.add(dataFinansial.getMARGINRATE2());
                            listMarginString.add(dataFinansial.getMARGINRATE3());
                            listMarginString.add(dataFinansial.getMARGINRATE4());

                            maxJw = Collections.max(listJw);
                            maxMargin = Collections.max(listMargin);

                            if(dataFinansial.getFIX_RATE()!=null&&!dataFinansial.getFIX_RATE().isEmpty()){
                                maxMargin=Double.parseDouble(dataFinansial.getFIX_RATE());
                            }
//                                maxMargin=0;
//
//                                for (int i = 0; i <listMargin.size() ; i++) {
//                                    if(maxMargin<=Integer.parseInt(listMarginString.get(i).replace(".","")))
//                                    {
//                                        for (int j = 0; j <listMargin.size() ; j++) {
//                                            if
//                                            (Integer.parseInt(listMarginString.get(i).replace(".",""))>=Integer.parseInt(listMarginString.get(j).replace(".",""))){
//                                                maxMargin=i;
//
//                                            }
//                                            else{
//                                                maxMargin=j;
//                                            }
//                                        }
//                                    }
//                                    else{
//                                        continue;
//                                    }
//
//                                }


//                                 textChangedForDataFinansial();

                            //kalau omzet perbulan 0, berarti belum pernah ngisi data finansial sebelumnya, maka edittext tidak diisi dulu, hanya beberapa field yang bisa diisi
                            if (dataFinansial.getOMZETPERBULAN().equalsIgnoreCase("0")) {
                                //pakai textchanged khusus jika pembiayaan takeover, karena total plafonnya beda
                                if (dataFinansial.getIDTUJUAN().equalsIgnoreCase("91")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")) {
//                                    Log.d("marsupilamitujuan",dataFinansial.getIDTUJUAN());
//                                    Log.d("marsupilami","masuk takeover");
                                    textChangedForDataFinansialTakeOver();
                                }
                                else{
//                                    Log.d("marsupilamitujuan",dataFinansial.getIDTUJUAN());
//                                    Log.d("marsupilami","masuk non-takeover");
                                    textChangedForDataFinansial();
                                }
                                //berarti kosong kabeh ,jadi set data yang dari awal udah keisi aje

                                //ini ambil dari keyvalue pantekan, dan dia tidak dinamis, jadi di comment aje
//        et_tujuan_penggunaan.setText(KeyValue.getKeyTujuanPenggunaanKmg(dataFinansial.getIDTUJUAN()));

                                //ini ngambil dari halaman hotprospek detail, jadi udah sesuai dari respon DB
                                et_tujuan_penggunaan.setText(tujuanPenggunaanDepan);
                                et_nilai_permohonan_pembiayaan.setText(dataFinansial.getPLAFONDINDUK().toString());
                                et_harga_beli.setText(dataFinansial.getHARGAPROPERTY().toString());
                                et_angsuran_pinjaman_eksisting_1.setText(dataFinansial.getANGSURAN().toString());
                                et_jangka_waktu_qardh.setText("14");

                                //khusus purna
                                et_fee_pihak_ketiga.setText(dataFinansial.getFEE());

                            } else {
                                setData();
                            }


//                                Log.d("firmansah",dataFinansialString);
//                                Log.d("firmansah isdebest",dataFinansial.getOMZETPERBULAN());


                        } else {
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(DataFinansialPurnaActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataFinansialPurnaActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ParseResponseDataInstansi> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(DataFinansialPurnaActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }









    public long hitungAngsuranAppel(float _rate, long _plafond, int jk_waktu){
        return (long)((long)(((_rate / 12) / 100) * _plafond) / (1 - (1 / Math.pow((1.00 + ((_rate / 100) / 12.00)), jk_waktu))));
    }


}
