package com.application.bris.brisi_pemutus.page_putusan.history;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.history_catatan.HistoryCatatan;
import com.application.bris.brisi_pemutus.page_putusan.adapters.HistoryCatatanAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by idong on 20/06/2019.
 */

public class FragmentHistoryCatatan extends Fragment{

    @BindView(R.id.rv_historycatatan)
    RecyclerView rv_historyfasilitas;

    private String dataCatatan;
    private List<HistoryCatatan> data;

    public FragmentHistoryCatatan(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ao_fragment_historycatatan, container, false);
        ButterKnife.bind(this, view);
        dataCatatan = getArguments().getString("dataCatatan");
        Log.d("FASILITAS", dataCatatan);

        //DATA HISTORY FASILITAS
        Gson gson = new Gson();
        Type type = new TypeToken<List<HistoryCatatan>>() {}.getType();
        data = gson.fromJson(dataCatatan, type);
        HistoryCatatanAdapter adp = new HistoryCatatanAdapter(getContext(), data);
        rv_historyfasilitas.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_historyfasilitas.setItemAnimator(new DefaultItemAnimator());
        rv_historyfasilitas.setAdapter(adp);

        return view;
    }
}

