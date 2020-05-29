package com.application.bris.brisi_pemutus.page_putusan.adapters;

import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;


import com.application.bris.brisi_pemutus.model.agunan.Agunan;
import com.application.bris.brisi_pemutus.page_putusan.agunan_retry.Agunan3Retry;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.List;

public class AgunanRetryStepAdapter extends AbstractFragmentStepAdapter{
    private String title;
    private List<Agunan> dataLengkap;


    public AgunanRetryStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, List<Agunan> mdataLengkap) {
        super(fm, context);
        dataLengkap = mdataLengkap;
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
                Agunan3Retry agunan3Retry = new Agunan3Retry();
                return agunan3Retry;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 1;
    }
}