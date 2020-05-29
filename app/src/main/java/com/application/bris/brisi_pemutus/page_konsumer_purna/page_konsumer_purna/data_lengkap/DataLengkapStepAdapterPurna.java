package com.application.bris.brisi_pemutus.page_konsumer_purna.page_konsumer_purna.data_lengkap;

import android.content.Context;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkap;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkapKonsumerKmg;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class DataLengkapStepAdapterPurna extends AbstractFragmentStepAdapter {
    private String title,approved, gimmick;
    private DataLengkapKonsumerKmg dataLengkap;

    //    public DataLengkapStepAdapterPurna(@NonNull FragmentManager fm, @NonNull Context context, DataLengkap mdataLengkap) {
//        super(fm, context);
//        dataLengkap = mdataLengkap;
//    }
    public DataLengkapStepAdapterPurna(@NonNull FragmentManager fm, @NonNull Context context, DataLengkapKonsumerKmg mdataLengkap, String mgimmick) {
        super(fm, context);
        dataLengkap = mdataLengkap;
        gimmick = mgimmick;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
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
                FragmentDataPribadiPurna fragmentDataPribadi = new FragmentDataPribadiPurna(dataLengkap);
                return fragmentDataPribadi;
            case 1:
                FragmentDataAlamatPurna fragmentDataAlamat = new FragmentDataAlamatPurna(dataLengkap);
                return fragmentDataAlamat;
            case 2:
                FragmentDataPekerjaanPurna fragmentDataUsaha = new FragmentDataPekerjaanPurna(dataLengkap, gimmick);
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