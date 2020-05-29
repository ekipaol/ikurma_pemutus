package com.application.bris.brisi_pemutus.page_putusan.adapters;


import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import android.util.Log;

import com.application.bris.brisi_pemutus.model.lkn.DataLkn;
import com.application.bris.brisi_pemutus.model.lkn.DataRekomendasiLkn;
import com.application.bris.brisi_pemutus.page_putusan.lkn.FragmentAnalisaKebutuhanModalKerja;
import com.application.bris.brisi_pemutus.page_putusan.lkn.FragmentAnalisaKeuangan;
import com.application.bris.brisi_pemutus.page_putusan.lkn.FragmentLembarKunjungan;
import com.application.bris.brisi_pemutus.page_putusan.lkn.FragmentRekomendasiPembiayaan;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class LknStepAdapter extends AbstractFragmentStepAdapter{
    private String title;
    private DataLkn data;
    private DataRekomendasiLkn dataRekomendasiLkn;

    public LknStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, DataLkn mdata) {
        super(fm, context);
        data = mdata;
    }
    public LknStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, DataLkn mdata,DataRekomendasiLkn mRekomendasiLkn) {
        super(fm, context);
        data = mdata;
        dataRekomendasiLkn=mRekomendasiLkn;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        switch (position){
            case 0:
                title = "Informasi Kunjungan";
                break;
            case 1:
                title = "Analisa Keuangan";
                break;
            case 2:
                title = "Analisa Kebutuhan Modal Kerja (WI)";
                break;
            case 3:
                title = "Rekomendasi Fasilitas Pembiayaan";
                break;
            default:
                title = "Default Tab";
        }
        return new StepViewModel.Builder(context)
                .setTitle(title)
                .create();
    }

    @Override
    public Step createStep(int position) {
        switch (position) {
            case 0:
                FragmentLembarKunjungan fragmentLembarKunjungan = new FragmentLembarKunjungan(data);
                return fragmentLembarKunjungan;
            case 1:
                FragmentAnalisaKeuangan fragmentAnalisisKeuangan = new FragmentAnalisaKeuangan(data);
                return fragmentAnalisisKeuangan;
            case 2:
                FragmentAnalisaKebutuhanModalKerja fragmentAnalisaKebutuhanModalKerja = new FragmentAnalisaKebutuhanModalKerja(data);
                return fragmentAnalisaKebutuhanModalKerja;
            case 3:
                Log.d("rekomendasiStep",dataRekomendasiLkn.getIDIR());
                FragmentRekomendasiPembiayaan fragmentRekomendasiPembiayaan = new FragmentRekomendasiPembiayaan(data,dataRekomendasiLkn);
                return fragmentRekomendasiPembiayaan;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}