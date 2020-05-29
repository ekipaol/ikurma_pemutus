package com.application.bris.brisi_pemutus.page_aom.info_agunan;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.delete_agunan.ReqDeleteAgunan;
import com.application.bris.brisi_pemutus.api.model.request.info_agunan.ReqInfoAgunan;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.model.info_agunan.InfoAgunan;
import com.application.bris.brisi_pemutus.page_putusan.agunan.ActivityInsertAgunan;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoAgunanActivity extends AppCompatActivity{



    @BindView(R.id.tv_id_agunan_info)
    TextView tv_id_agunan_info;
    @BindView(R.id.tv_fid_jenis_agunan_info)
    TextView tv_fid_jenis_agunan_info;
    @BindView(R.id.tv_pengikatan_eksisting_info)
    TextView tv_pengikatan_eksisting_info;
    @BindView(R.id.tv_id_cif_appel_info)
    TextView tv_id_cif_appel_info;
    @BindView(R.id.tv_persen_ftv_info)
    TextView tv_persen_ftv_info;
    @BindView(R.id.tv_jenis_pengikatan)
    TextView tv_jenis_pengikatan;
    @BindView(R.id.tv_cover_plafon)
    TextView tv_cover_plafon;
    @BindView(R.id.tv_nilai_pengikatan)
    TextView tv_nilai_pengikatan;

    @BindView(R.id.btn_hapus_info_agunan)
    TextView btn_hapus_info_agunan;
    @BindView(R.id.btn_edit_info_bangunan)
    TextView btn_edit_info_bangunan;
    @BindView(R.id.btn_ikat_info_bangunan)
    TextView btn_ikat_info_bangunan;
    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;


    private int idAplikasi;

    private ApiClientAdapter apiClientAdapter;
    private String dataString;
    private InfoAgunan data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agunan_info_agunan);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
       // idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Info Agunan");

        loadData();

        //LISTENERS FOR BUTTONS
        btn_hapus_info_agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAgunan();
            }
        });

        btn_edit_info_bangunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(InfoAgunanActivity.this, ActivityInsertAgunan.class);

               //id aplikasi masih dipantek untuk testing, di ganti dulu dengan data sebenernya
               intent.putExtra("idAplikasi","101167");
                intent.putExtra("idAgunan",tv_id_agunan_info.getText().toString());

               startActivity(intent);

            }
        });

        btn_ikat_info_bangunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent=new Intent(InfoAgunanActivity.this,SetPengikatanActivity.class);
            intent.putExtra("idAPlikasi",idAplikasi);
            intent.putExtra("idAgunan",tv_id_agunan_info.getText().toString());
            intent.putExtra("idCif",tv_id_cif_appel_info.getText().toString());
            intent.putExtra("fidJenisAgunan",data.getFidjenisAgunan());
            startActivity(intent);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();

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

    private void deleteAgunan(){
        new AlertDialog.Builder(InfoAgunanActivity.this)
                .setTitle("Hapus")
                .setMessage("Anda yakin ingin menghapus agunan?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog loadingDialog = ProgressDialog.show(InfoAgunanActivity.this, "",
                                "Memproses", true);
                        ReqDeleteAgunan req = new ReqDeleteAgunan();
                        req.setIdApl(101678);
                        req.setIdCif(81858);
                        req.setIdAgunan(Integer.parseInt(tv_id_agunan_info.getText().toString()));

                        Call<ParseResponse> call = apiClientAdapter.getApiInterface().deleteAgunan(req);
                        call.enqueue(new Callback<ParseResponse>() {
                            @Override
                            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {


                                try {
                                    if (response.isSuccessful()){
                                        if(response.body().getStatus().equalsIgnoreCase("00")){
                                            loadingDialog.dismiss();
//                            Gson gson = new Gson();
//                            dataString = response.body().getData().toString();
//                            data = gson.fromJson(dataString, InfoAgunan.class);
//                            setData();


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
                                AppUtil.notiferror(InfoAgunanActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
                            }
                        });

                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("Batal", null)
                .show();

    }


    private void loadData() {
        progressbar_loading.setVisibility(View.VISIBLE);
        ReqInfoAgunan req = new ReqInfoAgunan();
        req.setIdApl("101678");
        req.setIdCif(81858);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryInfoAgunan(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {


                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            progressbar_loading.setVisibility(View.GONE);
                            Gson gson = new Gson();
                            dataString = response.body().getData().toString();
                            data = gson.fromJson(dataString, InfoAgunan.class);
                            setData();
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
                AppUtil.notiferror(InfoAgunanActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
            }
        });
    }

    public void setData(){
        tv_id_agunan_info.setText(data.getIdAgunan());

        //fid jenis agunan special treatment
        if(data.getFidjenisAgunan().equalsIgnoreCase("30")){
            tv_fid_jenis_agunan_info.setText("Tanah Kosong / Sawah");
        }
        else if(data.getFidjenisAgunan().equalsIgnoreCase("31")){
            tv_fid_jenis_agunan_info.setText("Deposito");
        }
        else if(data.getFidjenisAgunan().equalsIgnoreCase("32")){
            tv_fid_jenis_agunan_info.setText("Kendaraan Bermotor");
        }
        else if(data.getFidjenisAgunan().equalsIgnoreCase("33")){
            tv_fid_jenis_agunan_info.setText("Kios");
        }
        else if(data.getFidjenisAgunan().equalsIgnoreCase("7")){
            tv_fid_jenis_agunan_info.setText("Tanah dan Bangunan");
        }
        else if(data.getFidjenisAgunan().equalsIgnoreCase("8")){
            tv_fid_jenis_agunan_info.setText("Mesin-mesin");
        }
        //END OF FID SPECIAL TREATMENT



        tv_pengikatan_eksisting_info.setText(data.getPengikatanEksisting());
        tv_id_cif_appel_info.setText(data.getIdCif());
        tv_persen_ftv_info.setText(data.getPersenFTV());
        tv_jenis_pengikatan.setText(data.getJenisPengikatan());
        tv_cover_plafon.setText(AppUtil.parseRupiah(data.getCoverPlafond()));
        tv_nilai_pengikatan.setText(AppUtil.parseRupiah(data.getNilaiPengikatan()));
    }
}