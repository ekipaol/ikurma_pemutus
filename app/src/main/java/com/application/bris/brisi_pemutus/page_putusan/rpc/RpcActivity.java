package com.application.bris.brisi_pemutus.page_putusan.rpc;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseError;
import com.application.bris.brisi_pemutus.api.model.request.kelengkapan_dokumen.ReqKelengkapanDokumen;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.model.rpc.Rpc;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RpcActivity extends AppCompatActivity{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;

    @BindView(R.id.tv_pendapatanusaha)
    TextView tv_pendapatanusaha;
    @BindView(R.id.tv_hargapokokpenjualan)
    TextView tv_hargapokokpenjualan;
    @BindView(R.id.tv_sewakontrak)
    TextView tv_sewakontrak;
    @BindView(R.id.tv_gajipegawai)
    TextView tv_gajipegawai;
    @BindView(R.id.tv_telponlistrikair)
    TextView tv_telponlistrikair;
    @BindView(R.id.tv_pajakretribusi)
    TextView tv_pajakretribusi;
    @BindView(R.id.tv_transportasi)
    TextView tv_transportasi;
    @BindView(R.id.tv_biayarumahtangga)
    TextView tv_biayarumahtangga;
    @BindView(R.id.tv_pengeluaranlainnya)
    TextView tv_pengeluaranlainnya;
    @BindView(R.id.tv_pengeluaranusaha)
    TextView tv_pengeluaranusaha;
    @BindView(R.id.tv_pendapatanbersih)
    TextView tv_pendapatanbersih;
    @BindView(R.id.tv_penghasilanlainnya)
    TextView tv_penghasilanlainnya;
    @BindView(R.id.tv_labarugi)
    TextView tv_labarugi;
    @BindView(R.id.tv_rpc)
    TextView tv_rpc;
    @BindView(R.id.tv_angsuranbrisexisting)
    TextView tv_angsuranbrisexisting;
    @BindView(R.id.tv_angsuransaatini)
    TextView tv_angsuransaatini;
    @BindView(R.id.tv_rpcratio)
    TextView tv_rpcratio;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    private int idAplikasi;

    private ApiClientAdapter apiClientAdapter;
    private String dataString;
    private Rpc data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek_rpc);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        sm_placeholder.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Perhitungan RPC");
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataRPC();

    }

    @Override
    protected void onPause() {
        super.onPause();
        sm_placeholder.stopShimmer();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }


    private void loadDataRPC() {
//        sm_placeholder.setVisibility(View.VISIBLE);
//        sm_placeholder.startShimmer();
        loading.setVisibility(View.VISIBLE);
        ReqKelengkapanDokumen req = new ReqKelengkapanDokumen();
        req.setId_aplikasi(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryRpc(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                sm_placeholder.stopShimmer();
                loading.setVisibility(View.GONE);
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            dataString = response.body().getData().get("rpc").toString();
                            data = gson.fromJson(dataString, Rpc.class);

                            tv_pendapatanusaha.setText(AppUtil.parseRupiahInt(data.getPendapatanUsaha()));

                            tv_hargapokokpenjualan.setText(AppUtil.parseRupiahInt(data.getPengeluaranHargaPokokPenjualan()));
                            tv_sewakontrak.setText(AppUtil.parseRupiahInt(data.getPengeluaranHargaSewa()));
                            tv_gajipegawai.setText(AppUtil.parseRupiahInt(data.getPengeluaranGajiPegawai()));
                            tv_telponlistrikair.setText(AppUtil.parseRupiahInt(data.getPengeluaranTelpListrik()));
                            tv_pajakretribusi.setText(AppUtil.parseRupiahInt(data.getPengeluaranPajak()));
                            tv_transportasi.setText(AppUtil.parseRupiahInt(data.getPengeluaranTransportasi()));
                            tv_biayarumahtangga.setText(AppUtil.parseRupiahInt(data.getPengeluaranRumahTangga()));
                            tv_pengeluaranlainnya.setText(AppUtil.parseRupiahInt(data.getPengeluaranLainnya()));
                            tv_pengeluaranusaha.setText(AppUtil.parseRupiahInt(data.getPengeluaranUsaha()));
                            tv_pendapatanbersih.setText(AppUtil.parseRupiahInt(data.getPendapatanBersih()));
                            tv_penghasilanlainnya.setText(AppUtil.parseRupiahInt(data.getPenghasilanLain()));
                            tv_labarugi.setText(AppUtil.parseRupiahInt(data.getLabaRugi()));
                            tv_rpc.setText(AppUtil.parseRupiahInt(data.getRpc()));
                            tv_angsuranbrisexisting.setText(AppUtil.parseRupiahInt(data.getAngsuranBrisExisting()));
                            tv_angsuransaatini.setText(AppUtil.parseRupiahInt(data.getAngsuranAplikasi()));
                            tv_rpcratio.setText(data.getRpcRatio()+"%");
                        }
                    }
                    else {
                        finish();
                        Toast.makeText(RpcActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                       //pesan error
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(RpcActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
            }
        });
    }
}