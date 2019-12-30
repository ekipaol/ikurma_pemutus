package com.application.bris.brisi_pemutus.page_konsumer_kmg.data_lengkap;


import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkapKonsumerKmg;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class DataLengkapKonsumerKmgStepAdapter extends AbstractFragmentStepAdapter {
    private String title;
    private DataLengkapKonsumerKmg dataLengkap;

    public DataLengkapKonsumerKmgStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, DataLengkapKonsumerKmg mdataLengkap) {
        super(fm, context);
        dataLengkap = mdataLengkap;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        StepViewModel.Builder builder = new StepViewModel.Builder(context);
        switch (position){
            case 0:
                title = "Data Pribadi";
                break;
            case 1:
                title = "Data Alamat";
                break;
            case 2:
                title = "Data Pekerjaan";
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
                FragmentDataPribadiKonsumerKmg fragmentDataPribadi = new FragmentDataPribadiKonsumerKmg(dataLengkap);
                return fragmentDataPribadi;
            case 1:
                FragmentDataAlamatKonsumerKmg fragmentDataAlamat = new FragmentDataAlamatKonsumerKmg(dataLengkap);
                return fragmentDataAlamat;
            case 2:
                FragmentDataPekerjaanKonsumerKmg fragmentDataUsaha = new FragmentDataPekerjaanKonsumerKmg(dataLengkap);
                return fragmentDataUsaha;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
