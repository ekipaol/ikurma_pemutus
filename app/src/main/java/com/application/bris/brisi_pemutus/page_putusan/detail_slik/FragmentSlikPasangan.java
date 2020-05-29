package com.application.bris.brisi_pemutus.page_putusan.detail_slik;



import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.detail_slik.DetailSlik;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by idong on 20/06/2019.
 */

public class FragmentSlikPasangan extends Fragment{

    @BindView(R.id.rv_detail_slik_pasangan)
    RecyclerView rv_detail_slik_pasangan;
    @BindView(R.id.animWhale)
    LottieAnimationView animWhale;
    @BindView(R.id.tvWhale)
    TextView tvWhale;

    private String dataSlikPasangan;
    private List<DetailSlik> data;

    public FragmentSlikPasangan(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_slik_pasangan, container, false);
        ButterKnife.bind(this, view);
        dataSlikPasangan = getArguments().getString("dataSlikPasangan");
        if(dataSlikPasangan.length()<=5){
            animWhale.setVisibility(View.VISIBLE);
            tvWhale.setVisibility(View.VISIBLE);
            rv_detail_slik_pasangan.setVisibility(View.GONE);
        }
//        Log.d("FASILITAS", dataSlikPasangan);

        //DATA HISTORY FASILITAS
        Gson gson = new Gson();
        Type type = new TypeToken<List<DetailSlik>>() {}.getType();
        data = gson.fromJson(dataSlikPasangan, type);
        DetailSlikAdapter adp = new DetailSlikAdapter(getContext(), data);
        rv_detail_slik_pasangan.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_detail_slik_pasangan.setItemAnimator(new DefaultItemAnimator());
        rv_detail_slik_pasangan.setAdapter(adp);

        return view;
    }
}


