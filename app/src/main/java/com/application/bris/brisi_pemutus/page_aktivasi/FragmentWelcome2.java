package com.application.bris.brisi_pemutus.page_aktivasi;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;

import com.application.bris.brisi_pemutus.page_login.view.LoginActivity;
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

public class FragmentWelcome2 extends Fragment implements Step{

    @BindView(R.id.iv_welcome)
    ImageView iv_welcome;
    @BindView(R.id.tv_welcome_title)
    TextView tv_welcome_title;
    @BindView(R.id.tv_welcome_desc)
    TextView tv_welcome_desc;

    int counterBypassActivation;

    public FragmentWelcome2() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        ButterKnife.bind(this, view);

        Glide.with(getContext())
                .load(R.drawable.welcome_page2)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(iv_welcome);

        tv_welcome_title.setText("Cepat dan Digital");
        tv_welcome_desc.setText("Manajemen user dan disposisi putusan dalam sentuhan jari.");

        //bypass aktivasi langsung ke login dengan klik gambar 4 kali, lalu klik tahan gambar
        iv_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterBypassActivation++;
            }
        });

        iv_welcome.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(counterBypassActivation>=4){
                    Intent intent =new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getActivity(), "@ekipaol", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
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
