package com.application.bris.brisi_pemutus.page_putusan.adapters;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.agunan.Agunan;
import com.application.bris.brisi_pemutus.model.data_lengkap.DataLengkap;
import com.application.bris.brisi_pemutus.model.list_foto_agunan.ListFotoAgunan;
import com.application.bris.brisi_pemutus.page_putusan.agunan.FragmentAgunan1Identifikasi;
import com.application.bris.brisi_pemutus.page_putusan.agunan.FragmentAgunan2Surat;
import com.application.bris.brisi_pemutus.page_putusan.agunan.FragmentAgunan4Spek;
import com.application.bris.brisi_pemutus.page_putusan.agunan.FragmentAgunan5Lingkungan;
import com.application.bris.brisi_pemutus.page_putusan.agunan.FragmentAgunan6Hasil;
import com.application.bris.brisi_pemutus.page_putusan.agunan.FragmentAgunan7Lain;
import com.application.bris.brisi_pemutus.page_putusan.agunan_retry.Agunan3Retry;
import com.application.bris.brisi_pemutus.page_putusan.data_lengkap.FragmentDataAlamat;
import com.application.bris.brisi_pemutus.page_putusan.data_lengkap.FragmentDataPribadi;
import com.application.bris.brisi_pemutus.page_putusan.data_lengkap.FragmentDataUsaha;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.List;

public class SampleFragmentStepAdapter extends AbstractFragmentStepAdapter{
    private String title;
    private DataLengkap dataLengkap;
    private Agunan dataAgunan;
    private List<ListFotoAgunan> listFotoAgunan;
    private int status;

    String stringAgunan;


    public SampleFragmentStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, DataLengkap mdataLengkap, int status) {
        super(fm, context);
        dataLengkap = mdataLengkap;
        this.status=status;
    }

    public SampleFragmentStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, Agunan mDataAgunan, int status) {
        super(fm, context);
        dataAgunan = mDataAgunan;
        this.status=status;
    }

    public SampleFragmentStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, Agunan mDataAgunan, int status, List<ListFotoAgunan> listFoto) {
        super(fm, context);
        dataAgunan = mDataAgunan;
        listFotoAgunan=listFoto;
        this.status=status;
    }




    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        StepViewModel.Builder builder = new StepViewModel.Builder(context);

        if(status==1){
            switch (position){
            case 0:
                title = "Data Pribadi";
                break;
            case 1:
                title = "Data Alamat";
                break;
            case 2:
                title = "Data Usaha";
                break;

                default:
                    title = "Default Tab";
        }




        }
        else if(status==2){
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
        }

//        else if(status==3){
//            switch (position){
//                case 0:
//                    title = "Data Pribadi";
//                    break;
//                case 1:
//                    title = "Data Alamat";
//                    break;
//                case 2:
//                    title = "Data Pekerjaan";
//
//                    builder.setEndButtonLabel("Ke Prescreening");
//                    break;
//
//                default:
//                    title = "Default Tab";
//            }
//        }

        return new StepViewModel.Builder(context)
                .setTitle(title)
                .create();
    }

    @Override
    public Step createStep(int position) {
        if(status==1){
            switch (position) {

                case 0:
                    FragmentDataPribadi fragmentDataPribadi = new FragmentDataPribadi(dataLengkap);
                    return fragmentDataPribadi;
                case 1:
                    FragmentDataAlamat fragmentDataAlamat = new FragmentDataAlamat(dataLengkap);
                    return fragmentDataAlamat;
                case 2:
                    FragmentDataUsaha fragmentDataUsaha = new FragmentDataUsaha(dataLengkap);
                    return fragmentDataUsaha;

                default:
                    throw new IllegalArgumentException("Unsupported position: " + position);
            }
        }
        else if(status==2){
            switch (position) {
                case 0:
                    FragmentAgunan1Identifikasi fragmentAgunan1Identifikasi = new FragmentAgunan1Identifikasi(dataAgunan);
                    return fragmentAgunan1Identifikasi;
                case 1:
                    FragmentAgunan2Surat fragmentAgunan2Surat = new FragmentAgunan2Surat(dataAgunan);
                    return fragmentAgunan2Surat;

                case 2:
                    Agunan3Retry agunan3Retry = new Agunan3Retry(dataAgunan);
                    return agunan3Retry;
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
       else   throw new IllegalArgumentException("Unsupported position: " + position);
    }

    @Override
    public int getCount() {
        if (status==1){
            return 3;
        }
        else if(status==2){
            return 7;
        }
        else return 3;

    }
}