package com.application.bris.brisi_pemutus.page_putusan.prescreening;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;

import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.kelengkapan_dokumen.ReqKelengkapanDokumen;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.model.prescreening.Prescreening;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.front_menu.PutusanFrontMenuKmg;
import com.application.bris.brisi_pemutus.page_putusan.PutusanFrontMenu;
import com.application.bris.brisi_pemutus.page_putusan.data_lengkap.ActivityDataLengkap;
import com.application.bris.brisi_pemutus.page_putusan.detail_slik.DetailSlikActivity;
import com.application.bris.brisi_pemutus.page_putusan.sektor_ekonomi.SektorEkonomiActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.DownloadTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescreeningActivity extends AppCompatActivity{

ApiClientAdapter apiClientAdapter;
Prescreening dataPrescreening;

public RelativeLayout loading;

    @BindView (R.id.tv_dhn)
    TextView tv_dhn;
    @BindView (R.id.tv_slik)
    TextView tv_slik;
    @BindView (R.id.tv_sikp)
    TextView tv_sikp;
    @BindView (R.id.tv_dukcapil)
    TextView tv_dukcapil;
    @BindView (R.id.tv_hasil_prescreening)
    TextView tv_hasil_prescreening;
    @BindView (R.id.ll_hasil_prescreening)
    LinearLayout ll_hasil_prescreening;
    @BindView (R.id.ll_content_sikp)
    LinearLayout ll_content_sikp;
    @BindView (R.id.bt_lanjut_data_lengkap)
    Button bt_lanjut_data_lengkap;
    @BindView (R.id.btn_download)
    Button bt_download_slik;
    @BindView (R.id.btn_download_pasangan)
    Button bt_download_slik_pasangan;
    @BindView (R.id.btn_detailslik)
    Button btn_detailslik;
    @BindView (R.id.tv_no_tiket_slik_nasabah)
    TextView tv_no_tiket_slik_nasabah;
    @BindView (R.id.tv_no_tiket_slik_pasangan)
    TextView tv_no_tiket_slik_pasangan;

    Putusan dataPutusan;

    SweetAlertDialog dialog;
     AllDataFront superData;
    Call<ParseResponse> call;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_prescreening);
        loading=findViewById(R.id.progressbar_loading);
        apiClientAdapter = new ApiClientAdapter(this);
        ButterKnife.bind(this);
         superData=(AllDataFront)getIntent().getSerializableExtra("superData");

        //set prescreening as already read
        AppPreferences appPreferences=new AppPreferences(this);
        appPreferences.setReadPreScreening("yes");

        dataPutusan=(Putusan)getIntent().getSerializableExtra("dataPutusan");
       // tv_dhn.setVisibility(View.VISIBLE);
               AppUtil.toolbarRegular(this, "Prescreening");
        ImageView backToolbar=findViewById(R.id.btn_back);


        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(superData.getJenisPembiayaan()!=null&&superData.getJenisPembiayaan().equalsIgnoreCase("kmg")){
                    Intent intent = new Intent(PrescreeningActivity.this, PutusanFrontMenuKmg.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(PrescreeningActivity.this, PutusanFrontMenu.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }

            }
        });

        if(superData.getJenisPembiayaan()!=null&&superData.getJenisPembiayaan().equalsIgnoreCase("kmg")){
            bt_lanjut_data_lengkap.setText("Lanjut ke Sektor Ekonomi");
        }
        else{
            bt_lanjut_data_lengkap.setText("Lanjut ke Data Lengkap");

        }

        loadData();


        bt_lanjut_data_lengkap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(superData.getJenisPembiayaan()!=null&&superData.getJenisPembiayaan().equalsIgnoreCase("kmg")){
                    Intent intent=new Intent(PrescreeningActivity.this, SektorEkonomiActivity.class);
                    intent.putExtra("cif", superData.getCif());
                    intent.putExtra("idAplikasi", superData.getIdAplikasi());
                    intent.putExtra("superData",superData);
                    intent.putExtra("jenisPembiayaan",superData.getJenisPembiayaan());
                    intent.putExtra("dataPutusan",dataPutusan);
                    startActivity(intent);
                }
                else {
                    Intent intent=new Intent(PrescreeningActivity.this, ActivityDataLengkap.class);
                    intent.putExtra("cif", superData.getCif());
                    intent.putExtra("idAplikasi", superData.getIdAplikasi());
                    intent.putExtra("superData",superData);
                    intent.putExtra("dataPutusan",dataPutusan);
                    startActivity(intent);
                }



            }
        });

        bt_download_slik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                downloadSlik(1);
            }
        });

        bt_download_slik_pasangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadSlik(2);
            }
        });

        btn_detailslik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PrescreeningActivity.this, DetailSlikActivity.class);
                intent.putExtra("idAplikasi",Integer.parseInt(superData.getIdAplikasi()));
                intent.putExtra("kodeProduk",superData.getKodeProduk());
                startActivity(intent);
            }
        });


        backgroundStatusBar();


    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void loadData(){
        loading.setVisibility(View.VISIBLE);
        //real data
        ReqKelengkapanDokumen req = new ReqKelengkapanDokumen();
        req.setId_aplikasi(Integer.parseInt(superData.getIdAplikasi()));
        //pantekan buat testing
//        ReqHistoryPutusan req=new ReqHistoryPutusan();
//                req.setCif(81857);
//        req.setId_aplikasi(101678);



        if(superData.getKodeProduk().equalsIgnoreCase("428")||superData.getKodeProduk().equalsIgnoreCase("429")||superData.getKodeProduk().equalsIgnoreCase("430")||superData.getKodeProduk().equalsIgnoreCase("316")||superData.getKodeProduk().equalsIgnoreCase("317")||superData.getKodeProduk().equalsIgnoreCase("321")){
            call = apiClientAdapter.getApiInterface().inquiryPrescreeningKmgMikro(req);
        }
        else{
            call = apiClientAdapter.getApiInterface().inquiryPrescreening(req);
        }

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){

                            String dataKelengkapanString = response.body().getData().get("prescreening").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<Prescreening>() {
                            }.getType();
                            dataPrescreening = gson.fromJson(dataKelengkapanString, Prescreening.class);
//                            Log.d("preyscreening",dataPrescreening.getResult());
//                            Log.d("preyscreeningstring",dataKelengkapanString);
                            if(dataPrescreening.getNoPermin()!=null&&!dataPrescreening.getNoPermin().isEmpty()){
                                tv_no_tiket_slik_nasabah.setText(dataPrescreening.getNoPermin());
                            }
                            if(dataPrescreening.getNoPermin2()!=null&&!dataPrescreening.getNoPermin2().isEmpty()){
                                tv_no_tiket_slik_pasangan.setText(dataPrescreening.getNoPermin2());
                            }




                            //tutorial overlay
//                            AppUtil.tutorialOverlay(PrescreeningActivity.this,btn_detailslik,"Lihat hasil remark pemrakarsa terhadap SLIK nasabah melalui tombol ini","tutorial_prescreening_detail_slik");

                            
                            setData(dataPrescreening);



                            //end of tutorial overlay


                        }
                        else{
                            AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            finish();
                        }
                    }
                    else {
                        //error message minta ke bang idong
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                finish();
            }
        });


    }



    private void setData(Prescreening dataPrescreening){
//        Log.d("jakiro",dataPrescreening.getDhn());
        if(dataPrescreening.getDhn().equalsIgnoreCase("true")){
            tv_dhn.setText("LOLOS");
            tv_dhn.setVisibility(View.VISIBLE);
        }
        else{
            tv_dhn.setText("TIDAK LOLOS");
        }





        if(dataPrescreening.getDukcapil().equalsIgnoreCase("true")){
            tv_dukcapil.setText("LOLOS");
            tv_dukcapil.setVisibility(View.VISIBLE);

        }
        else {
            tv_dukcapil.setText("TIDAK LOLOS");
        }


        if(dataPrescreening.getSlik().equalsIgnoreCase("true")){
            tv_slik.setText("LOLOS");
            tv_slik.setVisibility(View.VISIBLE);


        }
        else{
            tv_slik.setText("TIDAK LOLOS");
            tv_slik.setVisibility(View.VISIBLE);
        }

//        Log.d("hasilprescreening",dataPrescreening.getResult());
        if(dataPrescreening.getResult().equalsIgnoreCase("hijau")){
            ll_hasil_prescreening.setVisibility(View.VISIBLE);
            tv_hasil_prescreening.setText("HIJAU");
            tv_hasil_prescreening.setBackgroundColor(getResources().getColor(R.color.main_green_stroke_color));
        }
        else if(dataPrescreening.getResult().equalsIgnoreCase("kuning")){
            ll_hasil_prescreening.setVisibility(View.VISIBLE);
            tv_hasil_prescreening.setText("KUNING");
            tv_hasil_prescreening.setBackgroundColor(getResources().getColor(R.color.niceYellow));
        }
        else if(dataPrescreening.getResult().equalsIgnoreCase("merah")){
            ll_hasil_prescreening.setVisibility(View.VISIBLE);
            tv_hasil_prescreening.setText("MERAH");
            tv_hasil_prescreening.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
        }

        //hati hati blok kode dibawah, kalau nerima null, dia tidak akan menjalankan kode dibawahnya, jadi kode sikp ditaruh di paling bawah

        if(dataPrescreening.getSikp()!=null&&dataPrescreening.getSikp().equalsIgnoreCase("true")){
            tv_sikp.setText("LOLOS");
            tv_sikp.setVisibility(View.VISIBLE);
            ll_content_sikp.setVisibility(View.VISIBLE);
//            Log.d("jakiro2",dataPrescreening.getResult());
        }
        else if(dataPrescreening.getSikp()!=null&&dataPrescreening.getSikp().equalsIgnoreCase("false")){

            tv_sikp.setText("TIDAK LOLOS");
            tv_sikp.setVisibility(View.VISIBLE);
            ll_content_sikp.setVisibility(View.VISIBLE);
        }
        else{
            ll_content_sikp.setVisibility(View.GONE);
        }


    }

    private void downloadSlik(int id){
//        loading.setVisibility(View.VISIBLE);
//       tv_loading.setVisibility(View.VISIBLE);
//       tv_loading.setText("Downloading...");
        dialog=new SweetAlertDialog(PrescreeningActivity.this,SweetAlertDialog.PROGRESS_TYPE);
        dialog.setContentText("Harap Tunggu\n");
        dialog.setCancelText("Batal");
        dialog.show();
        ReqKelengkapanDokumen req = new ReqKelengkapanDokumen();

        //real data
        req.setId_aplikasi(Integer.parseInt(superData.getIdAplikasi()));

        //pantekan
//        req.setId_aplikasi(Integer.parseInt("101705"));

        Call<ParseResponse> call = null;
        if (id == 1){
            if (superData.getKodeProduk().equalsIgnoreCase("428")) {
                call = apiClientAdapter.getApiInterface().downloadSlikKmgMikro(req);
            }
            else{
                call = apiClientAdapter.getApiInterface().downloadSlik(req);
            }

        }
        else{
            if (superData.getKodeProduk().equalsIgnoreCase("428")) {
                call = apiClientAdapter.getApiInterface().downloadSlikPasanganKmgMikro(req);
            }
            else{
                call = apiClientAdapter.getApiInterface().downloadSlikPasangan(req);
            }

        }
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
//                tv_loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00"))
                        {

                            dialog.dismissWithAnimation();
                            String fileName = response.body().getData().get("fileName").getAsString();
                            String dataString = response.body().getData().get("file").getAsString();

                            //cek kalo string byte kurang dari 50 karakter alias corrupt, alias zipnya kosong
                            if(dataString.length()<=50){
                                AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), "PDF tidak ditemukan");
                            }
                            else{
                                byte[] data = Base64.decode(dataString, Base64.DEFAULT);
                                new DownloadTask(PrescreeningActivity.this, data, fileName);
                                final SweetAlertDialog dialog=new SweetAlertDialog(PrescreeningActivity.this,SweetAlertDialog.NORMAL_TYPE);
                                dialog.setTitleText("Buka folder download?");
                                dialog.setContentText("SLIK berhasil di download, apakah anda ingin membuka folder download sekarang?");
                                dialog.setConfirmText("Ya");
                                dialog.setCancelText("Tidak");
                                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        openFolder();
                                        dialog.dismissWithAnimation();
                                    }
                                });
                                dialog.show();
                            }

                        }
                        else {
                            dialog.dismissWithAnimation();
                            AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        dialog.dismissWithAnimation();
//                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
//                loading.setVisibility(View.GONE);
//                tv_loading.setVisibility(View.GONE);
                dialog.dismissWithAnimation();
                AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void openFolder()
    {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        Uri uri = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/HasilSlik").toString());
//        intent.setDataAndType(uri, "*/*");
//        startActivity(Intent.createChooser(intent, "Open folder"));


         startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));

    }






}