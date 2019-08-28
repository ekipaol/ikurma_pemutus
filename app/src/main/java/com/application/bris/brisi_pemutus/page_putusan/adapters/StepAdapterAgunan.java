package com.application.bris.brisi_pemutus.page_putusan.adapters;


import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;


import com.application.bris.brisi_pemutus.model.agunan.Agunan;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkap;
import com.application.bris.brisi_pemutus.page_putusan.agunan.FragmentAgunan2Surat;
import com.application.bris.brisi_pemutus.page_putusan.agunan.FragmentAgunan1Identifikasi;
import com.application.bris.brisi_pemutus.page_putusan.agunan.FragmentAgunan3Uraian;
import com.application.bris.brisi_pemutus.page_putusan.agunan.FragmentAgunan4Spek;
import com.application.bris.brisi_pemutus.page_putusan.agunan.FragmentAgunan5Lingkungan;
import com.application.bris.brisi_pemutus.page_putusan.agunan.FragmentAgunan6Hasil;
import com.application.bris.brisi_pemutus.page_putusan.agunan.FragmentAgunan7Lain;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.List;

public class StepAdapterAgunan extends AbstractFragmentStepAdapter{
    private String title;
    private Agunan dataAgunan;



    public StepAdapterAgunan(@NonNull FragmentManager fm, @NonNull Context context, Agunan mdataLengkap) {
        super(fm, context);
        dataAgunan = mdataLengkap;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        switch (position){
            case 0:
                title = "Identifikasi Tanah";
                break;
            case 1:
                title = "Identifikasi Surat Tanah";
                break;
            case 2:
                title = "Uraian Bangunan";
                break;
            case 3:
                title = "Spesifikasi Bangunan";
                break;
            case 4:
                title = "Data Lingkungan";
                break;
            case 5:
                title = "Hasil Penilaian";
                break;
            case 6:
                title = "Lain-lain";
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
                FragmentAgunan1Identifikasi fragmentAgunan1IdentifikasiDefa = new FragmentAgunan1Identifikasi(dataAgunan);

                return fragmentAgunan1IdentifikasiDefa;
            case 1:
                FragmentAgunan2Surat fragmentAgunan2Surat = new FragmentAgunan2Surat(dataAgunan);
                return fragmentAgunan2Surat;
            case 2:
                FragmentAgunan3Uraian fragmentAgunan3Uraian = new FragmentAgunan3Uraian(dataAgunan);
                return fragmentAgunan3Uraian;
            case 3:
                FragmentAgunan4Spek fragmentAgunan4Spek = new FragmentAgunan4Spek(dataAgunan);
                return fragmentAgunan4Spek;
            case 4:
                FragmentAgunan5Lingkungan fragmentAgunan5Lingkungan = new FragmentAgunan5Lingkungan(dataAgunan);
                return fragmentAgunan5Lingkungan;
            case 5:
                FragmentAgunan6Hasil fragmentAgunan6Hasil = new FragmentAgunan6Hasil(dataAgunan);
                return fragmentAgunan6Hasil;
            case 6:
                FragmentAgunan7Lain fragmentAgunan7Lain = new FragmentAgunan7Lain(dataAgunan);
                return fragmentAgunan7Lain;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }



    @Override
    public int getCount() {
        return 7;
    }
}
