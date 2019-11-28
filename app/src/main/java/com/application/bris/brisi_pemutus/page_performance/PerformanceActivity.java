package com.application.bris.brisi_pemutus.page_performance;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.performance_cabang.ReqPerformanceCabang;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.ranking_cabang.RankingCabang;
import com.application.bris.brisi_pemutus.page_disposisi.adapter.AdapterDaftarDisposisi;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerformanceActivity extends AppCompatActivity {

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;


//    @BindView(R.id.animWhale)
//    LottieAnimationView whale;
//    @BindView(R.id.tvWhale)
//    TextView tvWhale;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    @BindView(R.id.progress_bar_pencairan)
    CircularProgressBar progress_bar_pencairan;

    @BindView(R.id.chart_bar_hotprospek)
    HorizontalBarChart charthotprospek;

    @BindView(R.id.tv_percentage)
    TextView tv_percentage;

    @BindView(R.id.tv_pencairan_saat_ini)
    TextView tv_pencairan_saat_ini;

    @BindView(R.id.tv_pipeline_performance)
    TextView tv_pipeline_performance;

    @BindView(R.id.tv_hotprospek_performance)
    TextView tv_hotprospek_performance;

    @BindView(R.id.tv_diputus_performance)
    TextView tv_diputus_performance;

    @BindView(R.id.tv_menunggu_putusan_performance)
    TextView tv_menunggu_putusan_performance;

    @BindView(R.id.tv_noa_pencairan_performance)
    TextView tv_noa_performance;


    long valueToAnimate=0;
    Double doubleValueToAnimate=0d;
    private ApiClientAdapter apiClientAdapter ;


    private SearchView searchView;
    List<RankingCabang> dataCabang;
    AdapterDaftarDisposisi adapterDisposisi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);
        apiClientAdapter= new ApiClientAdapter(this);
        main();
//        getDataRankingSelindo();




    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Performance Cabang");
//        Long animationProgressBarDuration=(long)1500;
//        progress_bar_pencairan.setProgressWithAnimation(73f, animationProgressBarDuration); // =1s

//        animateLongNumbers();
        getDataCabang();

//        initializeGrafik();






    }

    //AMBIL TOP 3 SELINDO
    public void getDataCabang() {
        //  dataUser = getListUser();
        //progressbar_loading.setVisibility(View.VISIBLE);
//        shimmer.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        AppPreferences appPreferences = new AppPreferences(this);

        ReqPerformanceCabang req=new ReqPerformanceCabang();
//        req.setKodeKanwil(getIntent().getStringExtra("kodeKanwil"));
        req.setKodeKanwil(appPreferences.getKodeKanwil());

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().getPerformanceCabang(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                loading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<RankingCabang>>() {
                        }.getType();

                  dataCabang = gson.fromJson(response.body().getData(), type);

                  tv_pencairan_saat_ini.setText(AppUtil.parseRupiah(dataCabang.get(0).getAMOUNT()));
                  tv_pipeline_performance.setText(dataCabang.get(0).getPIPELINE());
                  tv_hotprospek_performance.setText(dataCabang.get(0).getHOT_PROSPEK());
                  tv_diputus_performance.setText(dataCabang.get(0).getDIPUTUS());
                  tv_menunggu_putusan_performance.setText(dataCabang.get(0).getMENUNGGU_PUTUSAN());
                  tv_noa_performance.setText(dataCabang.get(0).getNOA());



                  //masih belum bisa animasi dengan angka real
//                  Double pencairanCabang=Double.valueOf(dataCabang.get(0).getAMOUNT());
//                  Log.d("pencairancabang",Float.toString(pencairanCabang));
//
//                  //pencairan dibagi target (25 miliar perbulan) kali 100
//                  Double persentasePencapaian=pencairanCabang/25000000000L*100;
//
//                  animateDoubleNumbers(Double.valueOf(dataCabang.get(0).getAMOUNT()),tv_pencairan_saat_ini,false);
//
//                        startCountAnimation(Integer.parseInt(dataCabang.get(0).getPIPELINE()),1500,tv_pipeline_performance);
//                        startCountAnimation(Integer.parseInt(dataCabang.get(0).getHOT_PROSPEK()),1500,tv_hotprospek_performance);
//
//                        startCountAnimation(Integer.parseInt(dataCabang.get(0).getDIPUTUS()),1500,tv_diputus_performance);
//                        startCountAnimation(Integer.parseInt(dataCabang.get(0).getMENUNGGU_PUTUSAN()),1500,tv_menunggu_putusan_performance);

//                        animateProgressBar(persentasePencapaian,tv_percentage);







                    }
                    else{
                        Toasty.error(PerformanceActivity
                        .this,"Terjadi kesalahan dalam pemgambilan data cabang").show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                Toasty.error(PerformanceActivity
                        .this,"Gagal terhubung ke server, silahkan coba lagi").show();
            }
        });
    }

    private void startCountAnimation(int jumlah,int milisecondDurasi,final TextView textView) {
        ValueAnimator animator = ValueAnimator.ofInt(0, jumlah);
        animator.setDuration(milisecondDurasi);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
    }

    private void startCountAnimationFloat(float jumlah,int milisecondDurasi,final TextView textView) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, jumlah);
        animator.setDuration(milisecondDurasi);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
    }

    private void animateLongNumbers(Long amount, final TextView textView, final Boolean convertRupiah){
        //long value animation

        final long startValue = 0;
        // Also make a final reference to the target long for the animator
        final long finishValue = amount; // animate to 2 weeks from now
        // Calculate distance needed to travel
        final long distance = finishValue - startValue;
        // Create a ValueAnimator of floats from 0 through to 1.
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(1200);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                valueToAnimate = startValue + (long) (distance * (float) animation.getAnimatedValue());
                if(convertRupiah==true){
                    textView.setText(AppUtil.parseRupiahLong(valueToAnimate));
                }
                else{
                    textView.setText(Long.toString(valueToAnimate));
                }

            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // Make sure you end on the correct value
                valueToAnimate = finishValue;
                if(convertRupiah==true){
                    textView.setText(AppUtil.parseRupiahLong(valueToAnimate));
                }
                else{
                    textView.setText(Long.toString(valueToAnimate));
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    private void animateDoubleNumbers(final Double amount, final TextView textView, final Boolean convertRupiah){
        //long value animation

        final Double startValue = 0d;
        // Also make a final reference to the target long for the animator
        final Double finishValue = amount; // animate to 2 weeks from now
        // Calculate distance needed to travel
        final Double distance = finishValue - startValue;
        // Create a ValueAnimator of floats from 0 through to 1.
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(1200);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                doubleValueToAnimate = startValue + (Double) (distance * (float) animation.getAnimatedValue());
                if(convertRupiah==true){
                    textView.setText(AppUtil.parseRupiahDouble(doubleValueToAnimate));
                }
                else{
                    textView.setText(Double.toString(amount));
                }

            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // Make sure you end on the correct value
                doubleValueToAnimate = finishValue;
                if(convertRupiah==true){
                    textView.setText(AppUtil.parseRupiahLong(valueToAnimate));
                }
                else{
                    textView.setText(Long.toString(valueToAnimate));
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    private void animateProgressBar(final Long amount, final TextView textView){
        //long value animation

        final long startValue = 0;
        // Also make a final reference to the target long for the animator
        final long finishValue = amount; // animate to 2 weeks from now
        // Calculate distance needed to travel
        final long distance = finishValue - startValue;
        // Create a ValueAnimator of floats from 0 through to 1.
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(1200);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                valueToAnimate = startValue + (long) (distance * (float) animation.getAnimatedValue());

                    textView.setText(Long.toString(valueToAnimate));
                progress_bar_pencairan.setProgressWithAnimation(amount,1500L);


            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // Make sure you end on the correct value
                valueToAnimate = finishValue;

                    textView.setText(Long.toString(valueToAnimate));


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    private void initializeGrafik(){



        //buat objek entry untuk memasukkan data grafik -- data hotprospek
        List<BarEntry> entriesHotprospek = new ArrayList<>();

        //sumbu X menentukan posisi, sumbu Y isi data, keterangan masing masing data ditambahkan di value formatter dibawah -- data hotprospek
        entriesHotprospek.add(new BarEntry(0, 50));

        entriesHotprospek.add(new BarEntry(1, 30));

        entriesHotprospek.add(new BarEntry(2, 20));




        BarDataSet dataSetHotprospek = new BarDataSet(entriesHotprospek, "Jumlah Hotprospek");
        dataSetHotprospek.setColor(getResources().getColor(R.color.main_blue_color));
        BarData barDataHotprospek=new BarData(dataSetHotprospek);


        //ada bug tulisan description label dibawah kanan, kode dibawah mengosongkan tulisan itu
        Description description=new Description();
        description.setText("");

        //basic setting grafik
        charthotprospek.getAxisLeft().setGranularity(1);
        charthotprospek.getAxisRight().setGranularity(1);
        charthotprospek.getLegend().setYEntrySpace(2);
        charthotprospek.setDescription(description);


        //nama KCP yang dijadikan judul di sumbu X ditaruh disini
        final String[] namaKCP = new String[] { "KCP Sunter", "KCP Kelapa Gading", "KCP Koja"};

        //proses ngeganti sumbu X jadi nama AOM
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return namaKCP[(int) value];
            }
        };


        XAxis xAxis1Hotprospek=charthotprospek.getXAxis();
        xAxis1Hotprospek.setGranularity(1); // minimum axis-step (interval) is 1
        xAxis1Hotprospek.setValueFormatter(formatter);

        charthotprospek.setData(barDataHotprospek);
        charthotprospek.invalidate();

    }

}
