package com.application.bris.brisi_pemutus.page_eklaim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.databinding.MenuEklaimBinding;


public class MenuEklaimActivity extends AppCompatActivity implements  View.OnClickListener {
    MenuEklaimBinding binding;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding View
        binding = MenuEklaimBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar.tbRegular);
        setContentView(binding.getRoot());
        appPreferences = new AppPreferences(this);

        //Sdk untuk background toolbar
        customToolbar();
        backgroundStatusBar();
        setAlllOnClick();
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
    }

    public void customToolbar() {
        binding.toolbar.tvPageTitle.setText("Menu  e-Klaim");
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setAlllOnClick(){
        binding.btMonitoringProgress.setOnClickListener(this);
        binding.btReminderKlaim.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_reminder_klaim:
                Intent intent=new Intent(MenuEklaimActivity.this,ReminderKlaimActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_monitoring_progress:
//                Intent intent=new Intent(MenuEklaimActivity.this,ReminderKlaimActivity.class);
//                startActivity(intent);
                Toast.makeText(this, "On Development", Toast.LENGTH_SHORT).show();
                break;

        }
    }


}