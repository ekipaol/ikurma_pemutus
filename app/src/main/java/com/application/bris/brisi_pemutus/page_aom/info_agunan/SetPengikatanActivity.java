package com.application.bris.brisi_pemutus.page_aom.info_agunan;

import android.app.ProgressDialog;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.EmptyRequest;
import com.application.bris.brisi_pemutus.api.model.request.agunan_set_pengikatan.ReqSetPengikatan;
import com.application.bris.brisi_pemutus.api.model.request.ikat_agunan.ReqIkatAgunan;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.model.agunan_jenis_klasifikasi.AgunanJenisKlasifikasi;
import com.application.bris.brisi_pemutus.model.agunan_set_pengikatan.AgunanPengikatan;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetPengikatanActivity extends AppCompatActivity{



    @BindView(R.id.tv_id_plafond_pengajuan_set_pengikatan)
    TextView tv_id_plafond_pengajuan_set_pengikatan;
    @BindView(R.id.tv_jenis_agunan_set_pengikatan)
    TextView tv_jenis_agunan_set_pengikatan;
    @BindView(R.id.tv_id_agunan_set_pengikatan)
    TextView tv_id_agunan_set_pengikatan;
    @BindView(R.id.tv_nilai_market)
    TextView tv_nilai_market;
    @BindView(R.id.tv_nominal_pengikatan_aplikasi_lain)
    TextView tv_nominal_pengikatan_aplikasi_lain;
    @BindView(R.id.tv_nominal_akan_diikat)
    TextView tv_nominal_akan_diikat;
    @BindView(R.id.tv_plafon_cover_pengikatan)
    TextView tv_cover_plafon;
    @BindView(R.id.tv_jenis_pengikatan_agunan)
    TextView tv_jenis_pengikatan_agunan;


    @BindView(R.id.tv_no_bukti_pengikatan)
    TextView tv_no_bukti_pengikatan;

    @BindView(R.id.sp_jenis_pengikatan)
    Spinner sp_jenis_pengikatan;

    @BindView(R.id.sp_klasifikasi_agunan)
    Spinner sp_klasifikasi_agunan;

    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;

    @BindView(R.id.btn_ikat_set_pengikatan)
    Button btn_ikat_set_pengikatan;

    @BindView(R.id.btn_kembali_set_pengikatan)
    Button btn_kembali_set_pengikatan;

    //pantekan jenis agunan
    String fidjenisAgunan="7";

    List<AgunanJenisKlasifikasi> dataJenisKlasifikasi;


    private ApiClientAdapter apiClientAdapter;
    private String dataString;
    private AgunanPengikatan data;

    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agunan_set_pengikatan);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        // idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Pengikatan Agunan");

        loadData();

        //LISTENERS FOR BUTTONS
        btn_kembali_set_pengikatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
      //  loadData();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }




    private void loadData() {
        progressbar_loading.setVisibility(View.VISIBLE);
        ReqSetPengikatan req = new ReqSetPengikatan();


        req.setIdApl(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        req.setIdCif(Integer.parseInt(getIntent().getStringExtra("cif")));
        req.setIdAgunan(Integer.parseInt(getIntent().getStringExtra("idAgunan")));
        req.setFidjenisAgunan(getIntent().getIntExtra("tipeAgunan",0));

//        {"fidjenisAgunan":7,"idAgunan":48971,"idApl":601771,"idCif":82296}


        Call<ParseResponse> call = apiClientAdapter.getApiInterface().setPengikatan(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {


                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){


                            progressbar_loading.setVisibility(View.GONE);
                            Gson gson = new Gson();
                            dataString = response.body().getData().toString();
                            data = gson.fromJson(dataString, AgunanPengikatan.class);

                            //REQUEST klasifikasi agunan

                            Call<ParseResponseArr> call2 = apiClientAdapter.getApiInterface().jenisKlasifikasi(EmptyRequest.INSTANCE);
                            call2.enqueue(new Callback<ParseResponseArr>() {
                                @Override
                                public void onResponse(Call<ParseResponseArr> call2, Response<ParseResponseArr> response2) {

                                    try {
                                        if (response2.isSuccessful()){
                                            if(response2.body().getStatus().equalsIgnoreCase("00")){

                                                String listDataString = response2.body().getData().toString();
                                                Gson gson = new Gson();
                                                Type type = new TypeToken<List<AgunanJenisKlasifikasi>>() {}.getType();

                                                dataJenisKlasifikasi = gson.fromJson(listDataString, type);
                                                List<String> dataJenisKlasifikasiString=new ArrayList<>();
                                                List<Integer> dataJenisKlasifikasiInt=new ArrayList<>();

//                                                for (int i = 0; i < dataJenisKlasifikasi.size(); i++) {
//                                                    dataJenisKlasifikasi.get(i).getJenisPengikatan();
//                                                    dataJenisKlasifikasiString.add(dataJenisKlasifikasi.get(i).getJenisPengikatan());
//                                                    dataJenisKlasifikasiInt.add(Integer.parseInt(dataJenisKlasifikasi.get(i).getValuePengikatan()));
//
//                                                }


                                                setData();

                                                adapter = new ArrayAdapter<>(SetPengikatanActivity.this, android.R.layout.simple_list_item_1, dataJenisKlasifikasiString);
                                                sp_jenis_pengikatan.setAdapter(adapter);
//                                                Toast.makeText(SetPengikatanActivity.this, "Berhasil Mengikat Agunan", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else {
                                            //pesan error
                                        }
                                    }
                                    catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ParseResponseArr> call2, Throwable t) {
                                    AppUtil.notiferror(SetPengikatanActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
                                }
                            });


                        }
                    }
                    else {
                        //pesan error
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(SetPengikatanActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
            }
        });
    }

    public void setData(){

        fidjenisAgunan=Integer.toString(getIntent().getIntExtra("tipeAgunan",0));
        tv_id_plafond_pengajuan_set_pengikatan.setText(AppUtil.parseRupiah(data.getPlafond()));

        if(fidjenisAgunan.equalsIgnoreCase("30")){
            tv_jenis_agunan_set_pengikatan.setText("Tanah Kosong / Sawah");
        }
        else if(fidjenisAgunan.equalsIgnoreCase("31")){
            tv_jenis_agunan_set_pengikatan.setText("Deposito");
        }
        else if(fidjenisAgunan.equalsIgnoreCase("32")){
            tv_jenis_agunan_set_pengikatan.setText("Kendaraan Bermotor");
        }
        else if(fidjenisAgunan.equalsIgnoreCase("33")){
            tv_jenis_agunan_set_pengikatan.setText("Kios");
        }
        else if(fidjenisAgunan.equalsIgnoreCase("7")){
            tv_jenis_agunan_set_pengikatan.setText("Tanah dan Bangunan");
        }
        else if(fidjenisAgunan.equalsIgnoreCase("8")){
            tv_jenis_agunan_set_pengikatan.setText("Mesin-mesin");
        }
        tv_nilai_market.setText(AppUtil.parseRupiah(data.getNilaiMarket()));
       tv_nominal_pengikatan_aplikasi_lain.setText(AppUtil.parseRupiah(data.getPengikatanLain()));
//        tv_nilai_market.setText(AppUtil.parseRupiah(data.getPengikatanAplikasi()));
        tv_cover_plafon.setText(AppUtil.parseRupiah(data.getPlafondCover()));
        tv_id_agunan_set_pengikatan.setText(getIntent().getStringExtra("idAgunan"));
        tv_nominal_akan_diikat.setText(AppUtil.parseRupiah(data.getPengikatanAplikasi()));

        for (int i = 0; i <dataJenisKlasifikasi.size() ; i++) {
            Log.d("datajenisklasifikasi",dataJenisKlasifikasi.get(i).getValuePengikatan());

            //NOTE INI DRI SERVICENYA KEBALIK, YANG VALUE MALAH ISINYA KEY, YANG JENISPENGIKATAN ISINYA VALUE
            if(dataJenisKlasifikasi.get(i).getValuePengikatan().equalsIgnoreCase(data.getJenisPengikatan())){
                tv_jenis_pengikatan_agunan.setText(dataJenisKlasifikasi.get(i).getJenisPengikatan());
                break;
            }
        }




    }

    private void buttonIkat(){
        new AlertDialog.Builder(SetPengikatanActivity.this)
                .setTitle("Ikat")
                .setMessage("Apakah jenengan yakin ingin mengikat agunan?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog loadingDialog = ProgressDialog.show(SetPengikatanActivity.this, "",
                                "Memproses", true);
                        ReqIkatAgunan req = new ReqIkatAgunan();

                        //data masih dipantek semua yaaaa
                        req.setIdApl("101678");
                        req.setIdCif("81858");
                        req.setFidjenisAgunan(7);
                        req.setPlafondCover("30000000");
                        req.setNamaDebitur("CICAH AISYAH");
                        req.setTipeProduk("20");
                        req.setKlasifikasiAgunan("pokok");
                        req.setJenisPengikatan("10");
                        req.setPengikatanAplikasi("33000000");
                        req.setDesc_rekomendasi("0000");
                        req.setRekomendasiAgunan("4");
                      //  req.setDesc_rekomendasi("Marketable");


                        Call<ParseResponse> call = apiClientAdapter.getApiInterface().ikatAgunan(req);
                        call.enqueue(new Callback<ParseResponse>() {
                            @Override
                            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response1) {


                                try {
                                    if (response1.isSuccessful()){


                                        if(response1.body().getStatus().equalsIgnoreCase("00")){

                                            // END OF SPINNER KLASIFIKASI AGUNAN REQUEST
                                            loadingDialog.dismiss();
                                            Toast.makeText(SetPengikatanActivity.this, "Berhasil Mengikat Agunan", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {
                                        //pesan error
                                    }
                                }
                                catch (Exception e){
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ParseResponse> call, Throwable t) {
                                AppUtil.notiferror(SetPengikatanActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
                            }
                        });

                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("Batal", null)
                .show();
    }
}