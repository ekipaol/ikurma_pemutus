package com.application.bris.brisi_pemutus.page_konsumer_kpr.sektor_ekonomi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.sektor_ekonomi.ReqSektorEkonomi;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_finansial.DataFinansialKpr;
import com.application.bris.brisi_pemutus.model.data_pembiayaan.DataPbySebelumPutusan;
import com.application.bris.brisi_pemutus.model.sektor_ekonomi_kmg.SektorEkonomiKmg;
import com.application.bris.brisi_pemutus.model.sektor_ekonomi_kpr.CodeDesc;
import com.application.bris.brisi_pemutus.model.sektor_ekonomi_kpr.SektorEkonomiKpr;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.data_finansial.DataFinansialKonsumerKmgActivity;
import com.application.bris.brisi_pemutus.page_konsumer_kpr.PutusanFrontMenuKpr;
import com.application.bris.brisi_pemutus.page_konsumer_kpr.data_finansial.DataFinansialFlppActivity;
import com.application.bris.brisi_pemutus.page_konsumer_kpr.data_finansial.DataFinansialKprActivity;
import com.application.bris.brisi_pemutus.page_konsumer_purna.page_konsumer_purna.data_finansial.DataFinansialPurnaActivity;
import com.application.bris.brisi_pemutus.page_putusan.lkn.LknActivity;

import com.application.bris.brisi_pemutus.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class SektorEkonomiKprActivity extends AppCompatActivity {

    @BindView(R.id.ll_content)
    LinearLayout ll_content;

    @BindView(R.id.tf_tujuanpenggunaan)
    TextFieldBoxes tf_tujuanpenggunaan;
    @BindView(R.id.et_tujuanpenggunaan)
    EditText et_tujuanpenggunaan;

    @BindView(R.id.tf_bidangusaha)
    TextFieldBoxes tf_bidangusaha;
    @BindView(R.id.et_bidangusaha)
    EditText et_bidangusaha;

    @BindView(R.id.tf_sifatpembiayaan)
    TextFieldBoxes tf_sifatpembiayaan;
    @BindView(R.id.et_sifatpembiayaan)
    EditText et_sifatpembiayaan;

    @BindView(R.id.tf_jenispenggunaan)
    TextFieldBoxes tf_jenispenggunaan;
    @BindView(R.id.et_jenispenggunaan)
    EditText et_jenispenggunaan;

    @BindView(R.id.tf_jenispenggunaanlbu)
    TextFieldBoxes tf_jenispenggunaanlbu;
    @BindView(R.id.et_jenispenggunaanlbu)
    EditText et_jenispenggunaanlbu;

    @BindView(R.id.tf_jenispembiayaanlbu)
    TextFieldBoxes tf_jenispembiayaanlbu;
    @BindView(R.id.et_jenispembiayaanlbu)
    EditText et_jenispembiayaanlbu;

    @BindView(R.id.tf_sifatpembiayaanlbu)
    TextFieldBoxes tf_sifatpembiayaanlbu;
    @BindView(R.id.et_sifatpembiayaanlbu)
    EditText et_sifatpembiayaanlbu;

    @BindView(R.id.tf_kategoripembiayaanlbu)
    TextFieldBoxes tf_kategoripembiayaanlbu;
    @BindView(R.id.et_kategoripembiayaanlbu)
    EditText et_kategoripembiayaanlbu;

    @BindView(R.id.tf_sektorekonomi)
    TextFieldBoxes tf_sektorekonomi;
    @BindView(R.id.et_sektorekonomi)
    EditText et_sektorekonomi;

    @BindView(R.id.tf_klasifikasi_kpr)
    TextFieldBoxes tf_klasifikasi_kpr;
    @BindView(R.id.et_klasifikasi_kpr)
    EditText et_klasifikasi_kpr;

    @BindView(R.id.tf_tujuan_buka_rekening)
    TextFieldBoxes tf_tujuan_buka_rekening;
    @BindView(R.id.et_tujuan_membuka_rekening)
    EditText et_tujuan_membuka_rekening;

    @BindView(R.id.tf_sumber_aplikasi)
    TextFieldBoxes tf_sumber_aplikasi;
    @BindView(R.id.et_sumber_aplikasi)
    EditText et_sumber_aplikasi;

    @BindView(R.id.tf_referensi)
    TextFieldBoxes tf_referensi;
    @BindView(R.id.et_referensi)
    EditText et_referensi;

    @BindView(R.id.tf_hubungannasabahdgnbank)
    TextFieldBoxes tf_hubungannasabahdgnbank;
    @BindView(R.id.et_hubungannasabahdgnbank)
    EditText et_hubungannasabahdgnbank;


    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    @BindView(R.id.bt_lanjut_sektor_ekonomi)
    Button bt_lanjut_sektor_ekonomi;

    private int idAplikasi;
    private int cifLas;
    private int idTujuan;
    private String namaTujuan;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    AllDataFront superData;
    String jenisPembiayaan;
    Call<ParseResponse> call;
    List<CodeDesc> dtReferensi;
    List<CodeDesc> dtTujuanMembukaRekening;

 
    private SektorEkonomiKpr dataSektorEkonomiKpr;

    private String dataSektorEkonomiKmgString;
    private String idProgram="";



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek_kpr_sektorekonomi);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);

        //set sektor ekonomi as already read
        appPreferences = new AppPreferences(this);
        appPreferences.setReadSektorEkonomi("yes");


        jenisPembiayaan=getIntent().getStringExtra("jenisPembiayaan");
        superData= (AllDataFront)getIntent().getSerializableExtra("superData");
        idAplikasi = Integer.parseInt(superData.getIdAplikasi());
        cifLas = Integer.parseInt(superData.getCif());
        idTujuan = getIntent().getIntExtra("idTujuan", 0);
        namaTujuan =superData.getTujuanPembiayaan();
        if(getIntent().hasExtra("idProgram")){
            idProgram=getIntent().getStringExtra("idProgram");
        }

        AppUtil.disableEditTexts(ll_content);

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Sektor Ekonomi");
        ImageView backToolbar = findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
                    Intent intent = new Intent(SektorEkonomiKprActivity.this, PutusanFrontMenuKpr.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                

            }
        });

      
            bt_lanjut_sektor_ekonomi.setText("Lanjut Ke Data Finansial");
        


        bt_lanjut_sektor_ekonomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             
                    Intent intent;
//                    Log.d("isikodegimik",superData.getKodeGimmick());

                if(superData.getKodeGimmick().equalsIgnoreCase("222")){
                    intent = new Intent(SektorEkonomiKprActivity.this, DataFinansialFlppActivity.class);
                }
                else{
                    intent = new Intent(SektorEkonomiKprActivity.this, DataFinansialKprActivity.class);
                }
                 

                        intent.putExtra("cif", superData.getCif());
                        intent.putExtra("idAplikasi", superData.getIdAplikasi());
                        intent.putExtra("tujuanPembiayaan", superData.getTujuanPembiayaan());
                        intent.putExtra("jw", Integer.parseInt(superData.getJw()));
                        intent.putExtra("plafond", superData.getPlafond());
                        intent.putExtra("superData",superData);
//                        Log.d("isikodegimik1",superData.getKodeGimmick());
                        startActivity(intent);

                    
//                    intent.putExtra("cif", superData.getCif());
//                    intent.putExtra("idAplikasi", superData.getIdAplikasi());
//                    intent.putExtra("tujuanPembiayaan", superData.getTujuanPembiayaan());
//                    intent.putExtra("jw", Integer.parseInt(superData.getJw()));
//                    intent.putExtra("plafond", superData.getPlafond());
//                    intent.putExtra("superData",superData);
//                    startActivity(intent);
                
            

            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
//        ll_shimmer.setVisibility(View.VISIBLE);
        sm_placeholder.startShimmer();
     
            loadData();
        

    }



    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

   

    private void loadData() {
        ReqSektorEkonomi req = new ReqSektorEkonomi();
        //data real
        req.setIdAplikasi(idAplikasi);
        req.setIdRole(Integer.parseInt(appPreferences.getFidRole()));

        if(superData.getKodeGimmick().equalsIgnoreCase("222")){
            call = apiClientAdapter.getApiInterface().inquirySektorEkonomiFlpp(req);
        }
        else{
            call = apiClientAdapter.getApiInterface().inquirySektorEkonomiKpr(req);
        }


        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){

                            Gson gson = new Gson();
                            Type typeCodeDesc = new TypeToken<List<CodeDesc>>() {}.getType();

                            dataSektorEkonomiKmgString = response.body().getData().get("dataPbySebelumPutusan").toString();
                            dataSektorEkonomiKpr = gson.fromJson(dataSektorEkonomiKmgString, SektorEkonomiKpr.class);

                            try{
                                String dtReferensiString = response.body().getData().get("dtReferensi").toString();
                                dtReferensi = gson.fromJson(dtReferensiString, typeCodeDesc);
                            }
                            catch (Exception e){
                                Log.d("ada error di :","dtreferensi");
                            }

                            try{
                                String dtTujuanMembukaRekeningString = response.body().getData().get("dtTujuanBukaRekening").toString();
                                dtTujuanMembukaRekening = gson.fromJson(dtTujuanMembukaRekeningString, typeCodeDesc);
                            }
                            catch (Exception e){
                                Log.d("ada error di :","dttujuanbukarekening");
                            }


                            setData();


                        }
                        else{
                            finish();
                            AppUtil.notiferror(SektorEkonomiKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        finish();
                        //error message
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                finish();
                AppUtil.notiferror(SektorEkonomiKprActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
            }
        });

    }

 


    public void setData(){

        et_tujuanpenggunaan.setText(namaTujuan);

        if(dataSektorEkonomiKpr.getBidangUsahaText()!=null){
            et_bidangusaha.setText(dataSektorEkonomiKpr.getBidangUsahaText());
        }

        if(dataSektorEkonomiKpr.getSifatKreditText()!=null){
            et_sifatpembiayaan.setText(dataSektorEkonomiKpr.getSifatKreditText());
        }


        if(dataSektorEkonomiKpr.getJenisPenggunaanText()!=null){
            et_jenispenggunaan.setText(dataSektorEkonomiKpr.getJenisPenggunaanText());

        }

        if(dataSektorEkonomiKpr.getJenisPenggunaanLBUText()!=null){
            et_jenispenggunaanlbu.setText(dataSektorEkonomiKpr.getJenisPenggunaanLBUText());
        }

        if(dataSektorEkonomiKpr.getHubDebiturDgnBankText()!=null){
            et_hubungannasabahdgnbank.setText(dataSektorEkonomiKpr.getHubDebiturDgnBankText());
        }

        if(dataSektorEkonomiKpr.getKlasifikasiKPR()!=null){
            et_klasifikasi_kpr.setText(dataSektorEkonomiKpr.getKlasifikasiKPR());

        }

        if(dataSektorEkonomiKpr.getSumberAplikasi()!=null){
            et_sumber_aplikasi.setText(dataSektorEkonomiKpr.getSumberAplikasi());
        }



        //dapet kembalian referensi tanpa bentuk teksnya, jadi harus dicari pasangan keynya dari objek dtReferensi
        if(dataSektorEkonomiKpr.getReferensi()!=null){
//            et_tujuan_membuka_rekening.setText(dataPbySebelumPutusan.getTujuanMembukaRekening());
            for (int i = 0; i <dtReferensi.size() ; i++) {
                if(dataSektorEkonomiKpr.getReferensi().equalsIgnoreCase(dtReferensi.get(i).getDesc1())){
                    et_referensi.setText(dtReferensi.get(i).getDesc2());
                    break;

                }

            }

        }

        if(dataSektorEkonomiKpr.getTujuanMembukaRekening()!=null){
//            et_tujuan_membuka_rekening.setText(dataPbySebelumPutusan.getTujuanMembukaRekening());
            for (int i = 0; i <dtTujuanMembukaRekening.size() ; i++) {
//                Log.d("clockworkmachine",dataSektorEkonomiKpr.getTujuanMembukaRekening()+" "+dtTujuanMembukaRekening.get(i).getDesc1());
                if(dataSektorEkonomiKpr.getTujuanMembukaRekening().equalsIgnoreCase(dtTujuanMembukaRekening.get(i).getDesc1())){
//                    Log.d("clockworkmachineHIT!",dataSektorEkonomiKpr.getTujuanMembukaRekening()+" "+dtTujuanMembukaRekening.get(i).getDesc2());
                    et_tujuan_membuka_rekening.setText(dtTujuanMembukaRekening.get(i).getDesc2());
                    break;

                }

            }

        }



        if(dataSektorEkonomiKpr.getKategoriKreditLBUText()!=null){
            et_kategoripembiayaanlbu.setText(dataSektorEkonomiKpr.getKategoriKreditLBUText());
        }


        if(dataSektorEkonomiKpr.getSektorEkonomiText()!=null){
            et_sektorekonomi.setText(dataSektorEkonomiKpr.getSektorEkonomiText());
        }

        if(dataSektorEkonomiKpr.getJenisKreditLBUText()!=null){
            et_jenispembiayaanlbu.setText(dataSektorEkonomiKpr.getJenisKreditLBUText());
        }

        if(dataSektorEkonomiKpr.getSifatKreditLBUText()!=null){
            et_sifatpembiayaanlbu.setText(dataSektorEkonomiKpr.getSifatKreditLBUText());
        }




    }

   

}
