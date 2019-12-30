package com.application.bris.brisi_pemutus.page_aktivasi;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.data_lengkap.DataLengkapKonsumerKmgActivity;
import com.application.bris.brisi_pemutus.page_performance.PerformanceActivity;
import com.application.bris.brisi_pemutus.page_putusan.detail_slik.DetailSlikActivity;
import com.application.bris.brisi_pemutus.page_ranking.RankingActivity;
import com.application.bris.brisi_pemutus.splash_screen.SplashScreen;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PID on 6/15/2019.
 */

public class FragmentWelcome extends Fragment implements Step{

    @BindView(R.id.iv_welcome)
    ImageView iv_welcome;
    @BindView(R.id.tv_welcome_title)
    TextView tv_welcome_title;
    @BindView(R.id.tv_welcome_desc)
    TextView tv_welcome_desc;

    public FragmentWelcome() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        ButterKnife.bind(this, view);

        Glide.with(getContext())
                .load(R.drawable.welcome_page_bank)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(iv_welcome);

        iv_welcome.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent=new Intent(getActivity(), DataLengkapKonsumerKmgActivity.class);
                startActivity(intent);
                return false;
            }
        });

        tv_welcome_title.setText("Simple dan Mudah");
        tv_welcome_desc.setText("Preview putusan dari pemrakarsa, dan lakukan putusan dari mana saja.");
        return view;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }
}
