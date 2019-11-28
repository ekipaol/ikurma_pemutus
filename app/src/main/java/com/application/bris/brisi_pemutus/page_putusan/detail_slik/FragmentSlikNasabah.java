package com.application.bris.brisi_pemutus.page_putusan.detail_slik;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.detail_slik.DetailSlik;
import com.application.bris.brisi_pemutus.model.history_catatan.HistoryCatatan;
import com.application.bris.brisi_pemutus.model.history_fasilitas.HistoryFasilitas;
import com.application.bris.brisi_pemutus.page_putusan.adapters.HistoryCatatanAdapter;
import com.application.bris.brisi_pemutus.page_putusan.adapters.HistoryFasilitasAdapater;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by idong on 20/06/2019.
 */

public class FragmentSlikNasabah extends Fragment{

    @BindView(R.id.rv_detail_slik_nasabah)
    RecyclerView rv_detail_slik_nasabah;
    @BindView(R.id.animWhale)
    LottieAnimationView animWhale;
    @BindView(R.id.tvWhale)
    TextView tvWhale;

    private String dataSlikNasabah;
    private List<DetailSlik> data;

    public FragmentSlikNasabah(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_slik_nasabah, container, false);
        ButterKnife.bind(this, view);
        dataSlikNasabah = getArguments().getString("dataSlikNasabah");

//        Log.d("datasliknasabag",dataSlikNasabah);

        if(dataSlikNasabah.length()<=5){
            animWhale.setVisibility(View.VISIBLE);
            tvWhale.setVisibility(View.VISIBLE);
            rv_detail_slik_nasabah.setVisibility(View.GONE);
        }
//        Log.d("FASILITAS", dataSlikNasabah);

        //DATA HISTORY FASILITAS
        Gson gson = new Gson();
        Type type = new TypeToken<List<DetailSlik>>() {}.getType();
        data = gson.fromJson(dataSlikNasabah, type);
        DetailSlikAdapter adp = new DetailSlikAdapter(getContext(), data);
        rv_detail_slik_nasabah.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_detail_slik_nasabah.setItemAnimator(new DefaultItemAnimator());
        rv_detail_slik_nasabah.setAdapter(adp);

        return view;
    }
}


