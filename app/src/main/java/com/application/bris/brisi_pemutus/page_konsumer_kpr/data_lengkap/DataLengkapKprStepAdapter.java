package com.application.bris.brisi_pemutus.page_konsumer_kpr.data_lengkap;


import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkapKonsumerKmg;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkapKpr;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.data_lengkap.FragmentDataAlamatKonsumerKmg;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.data_lengkap.FragmentDataPekerjaanKonsumerKmg;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.data_lengkap.FragmentDataPribadiKonsumerKmg;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class DataLengkapKprStepAdapter extends AbstractFragmentStepAdapter {
    private String title;
    private DataLengkapKpr dataLengkap;

    public DataLengkapKprStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, DataLengkapKpr mdataLengkap) {
        super(fm, context);
        dataLengkap = mdataLengkap;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {

//        builder.setEndButtonLabel("");
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
                FragmentDataPribadiKpr fragmentDataPribadi = new FragmentDataPribadiKpr(dataLengkap);
                return fragmentDataPribadi;
            case 1:
                FragmentDataAlamatKpr fragmentDataAlamat = new FragmentDataAlamatKpr(dataLengkap);
                return fragmentDataAlamat;
            case 2:
                FragmentDataPekerjaanKpr fragmentDataUsaha = new FragmentDataPekerjaanKpr(dataLengkap);
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