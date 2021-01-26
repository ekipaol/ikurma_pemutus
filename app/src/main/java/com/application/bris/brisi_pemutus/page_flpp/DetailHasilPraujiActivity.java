package com.application.bris.brisi_pemutus.page_flpp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseSaldo;
import com.application.bris.brisi_pemutus.api.model.request.putusan_pemutus.ReqSetujuPutusan;
import com.application.bris.brisi_pemutus.api.model.request.req_saldo.ReqSaldoNasabah;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.baseapp.RouteApp;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.flpp.HasilPraujiFlpp;
import com.application.bris.brisi_pemutus.model.monitoring.NasabahMonitoring;
import com.application.bris.brisi_pemutus.model.saldo_nasabah.SaldoNasabah;
import com.application.bris.brisi_pemutus.page_monitoring.ActivityRiwayatMutasi;
import com.application.bris.brisi_pemutus.page_putusan.history_catatan.CatatanActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.view.corelayout.CoreLayoutActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class DetailHasilPraujiActivity extends AppCompatActivity {

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;

    @BindView(R.id.tv_nama_nasabah)
    TextView tv_nama_nasabah;

    @BindView(R.id.tv_ktp_nasabah)
    TextView tv_ktp_nasabah;

    @BindView(R.id.tv_npwp_nasabah)
    TextView tv_npwp_nasabah;

    @BindView(R.id.tv_id_aplikasi)
    TextView tv_id_aplikasi;

    @BindView(R.id.tv_plafond)
    TextView tv_plafond;

    @BindView(R.id.tv_jangka_waktu)
    TextView tv_jangka_waktu;

    @BindView(R.id.tv_gaji_pokok)
    TextView tv_gaji_pokok;

    @BindView(R.id.tv_angsuran)
    TextView tv_angsuran;

    @BindView(R.id.tv_harga_rumah)
    TextView tv_harga_rumah;

    @BindView(R.id.tv_nilai_flpp)
    TextView tv_nilai_flpp;


    @BindView(R.id.tv_margin)
    TextView tv_margin;

    @BindView(R.id.tv_nama_pihak_ketiga)
    TextView tv_nama_pihak_ketiga;

    @BindView(R.id.tv_nama_perumahan)
    TextView tv_nama_perumahan;

    @BindView(R.id.tv_alamat)
    TextView tv_alamat;

    @BindView(R.id.tv_kota_kab_agunan)
    TextView tv_kota_kab_agunan;

    @BindView(R.id.tv_kodepos_agunan)
    TextView tv_kodepos_agunan;

    @BindView(R.id.tv_luas_tanah)
    TextView tv_luas_tanah;

    @BindView(R.id.tv_luas_bangunan)
    TextView tv_luas_bangunan;

    @BindView(R.id.tv_no_sp3k)
    TextView tv_no_sp3k;

    @BindView(R.id.tv_tanggal_sp3k)
    TextView tv_tanggal_sp3k;

    @BindView(R.id.tv_no_dks)
    TextView tv_no_dks;

    @BindView(R.id.tv_status)
    TextView tv_status;

    @BindView(R.id.tv_keterangan)
    TextView tv_keterangan;

    @BindView(R.id.bt_kembalikan_ke_ao)
    Button bt_kembalikan_ke_ao;


    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;



    ApiClientAdapter apiClientAdapter;
    AppPreferences appPreferences;


    HasilPraujiFlpp dataPrauji;


    Call<ParseResponseSaldo> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hasil_prauji_flpp);
        main();

    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Detail Hasil Pra Uji");
        apiClientAdapter = new ApiClientAdapter(this);
        dataPrauji = (HasilPraujiFlpp) getIntent().getSerializableExtra("dataPrauji");
        appPreferences=new AppPreferences(this);


            setData();

            otherViewChanges();



        bt_kembalikan_ke_ao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //nampilkan dialog
                SweetAlertDialog dialog=new SweetAlertDialog(DetailHasilPraujiActivity.this,SweetAlertDialog.WARNING_TYPE);
                dialog.setTitle("Konfirmasi");
                dialog.setContentText("Apakah anda yakin akan mengembalikan aplikasi ke AO?");
                dialog.setConfirmText("Ya");
                dialog.setCancelText("Tidak");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        Toast.makeText(DetailHasilPraujiActivity.this, "beep boop kamu mengembalikan ke AO", Toast.LENGTH_SHORT).show();
                        kembalikanKeAo(dialog);
                    }
                });
                dialog.show();
            }
        });


    }

    private void setData(){
        tv_nama_nasabah.setText(dataPrauji.getNamaNasabah());

        tv_ktp_nasabah.setText(dataPrauji.getKTP());

        tv_npwp_nasabah.setText(dataPrauji.getNPWP());



        tv_gaji_pokok.setText(AppUtil.parseRupiah(dataPrauji.getGajiPokok()));

        tv_plafond.setText(AppUtil.parseRupiah(dataPrauji.getNilaiKPR()));

        tv_alamat.setText(dataPrauji.getAlamatAgunan());


        tv_nama_pihak_ketiga.setText(dataPrauji.getNamaBadanHukum());
        tv_nama_perumahan.setText(dataPrauji.getNamaPerumahan());

        tv_harga_rumah.setText(AppUtil.parseRupiah(dataPrauji.getHargaRumah()));

        tv_angsuran.setText(AppUtil.parseRupiah(dataPrauji.getAngsuran()));

        tv_jangka_waktu.setText(dataPrauji.getTenor()+" Bulan");

        tv_margin.setText(String.valueOf(Double.parseDouble(dataPrauji.getSukuBungaKPR())*100)+"%");

        tv_id_aplikasi.setText(dataPrauji.getIdAplikasi());

        tv_nilai_flpp.setText(AppUtil.parseRupiah(dataPrauji.getNilaiFLPP()));


        tv_kodepos_agunan.setText(dataPrauji.getAgnKodepos());

        tv_kota_kab_agunan.setText(dataPrauji.getKotaKabAgunan());


        tv_luas_tanah.setText(dataPrauji.getLuasTanah()+" M2");
        tv_luas_bangunan.setText(dataPrauji.getLuasBangunan() + " M2");

        tv_status.setText(dataPrauji.getDESC1());
        tv_keterangan.setText(dataPrauji.getKeterangan());

        tv_no_sp3k.setText(dataPrauji.getNoSP3K());
        tv_no_dks.setText(dataPrauji.getNoDKS());
        tv_tanggal_sp3k.setText(AppUtil.parseTanggalGeneral(dataPrauji.getTanggalSP3K(),"ddMMyyyy","dd-MM-yyyy"));



    }

    private void kembalikanKeAo(SweetAlertDialog dialog1){
        ReqSetujuPutusan req = new ReqSetujuPutusan();
        req.setRole_id(appPreferences.getFidRole());
        req.setSt_aplikasid(dataPrauji.getSTATUS());
        req.setFid_aplikasi(dataPrauji.getIdAplikasi());
        req.setId_pemutus(appPreferences.getUid());
        req.setCatatan_pemutus("Dikembalikan ke AO berdasarkan hasil pra uji");
        req.setKode_dsn(appPreferences.getDsnCode());

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().pemutusKembalikanKmg(req);


        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("00")) {


                        dialog1.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        dialog1.setTitleText("Putusan Berhasil");

                        dialog1.setContentText("Pembiayaan berhasil dikembalikan ke AO");

                        dialog1.setConfirmText("OK");
                        dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialog1.dismissWithAnimation();
                              finish();
                            }
                        });
                        dialog1.showCancelButton(false);


                    } else {
                        dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        dialog1.setTitle("Terjadi Kesalahan");
                        dialog1.setContentText(response.body().getMessage());
                        dialog1.setConfirmText("Coba lagi");
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                dialog1.setTitle("Gagal");
                dialog1.setContentText("Gagal terhubung ke server");
                dialog1.setConfirmText("Ok");

            }
        });
    }

    private void otherViewChanges(){
        if(dataPrauji.getSTATUS().equalsIgnoreCase("-16")){
            bt_kembalikan_ke_ao.setVisibility(View.VISIBLE);
        }

        else{
            bt_kembalikan_ke_ao.setVisibility(GONE);
        }
    }







}