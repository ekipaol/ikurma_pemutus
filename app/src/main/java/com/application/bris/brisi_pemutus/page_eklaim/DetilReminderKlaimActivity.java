package com.application.bris.brisi_pemutus.page_eklaim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.databinding.ActivityDetilReminderKlaimBinding;
import com.application.bris.brisi_pemutus.model.DataReminderKlaim;


public class DetilReminderKlaimActivity extends AppCompatActivity implements  View.OnClickListener {
    ActivityDetilReminderKlaimBinding binding;
    private AppPreferences appPreferences;
    private ApiClientAdapter apiClientAdapter;
    private DataReminderKlaim dataReminderKlaim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding View
        binding = ActivityDetilReminderKlaimBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar.tbRegular);
        setContentView(binding.getRoot());
        appPreferences = new AppPreferences(this);
        dataReminderKlaim=(DataReminderKlaim)getIntent().getSerializableExtra("data");

        //Sdk untuk background toolbar
        customToolbar();
        backgroundStatusBar();
        setAlllOnClick();
        setData();
    }

    private void setData(){
        binding.tvNamaNasabah.setText(dataReminderKlaim.getNamaNasabah());
        binding.tvProdukNasabah.setText(dataReminderKlaim.getNamaProduk());
        binding.tvOs.setText(dataReminderKlaim.getOs());
        binding.tvKol.setText(dataReminderKlaim.getKol());
        binding.tvPenjaminan.setText(dataReminderKlaim.getPenjaminan());
        binding.tvNomorLd.setText(dataReminderKlaim.getNoLd());
        binding.tvArea.setText(dataReminderKlaim.getArea());
        binding.tvNamaCabang.setText(dataReminderKlaim.getUker());

    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
    }

    private void setAlllOnClick(){
     //still nothing
    }

    public void customToolbar() {
        binding.toolbar.tvPageTitle.setText("Detil Reminder Klaim");
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_reminder_klaim:
                Intent intent=new Intent(DetilReminderKlaimActivity.this,ReminderKlaimActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_monitoring_progress:
//                Intent intent=new Intent(DetilReminderKlaimActivity.this,ReminderKlaimActivity.class);
//                startActivity(intent);
                Toast.makeText(this, "On Development", Toast.LENGTH_SHORT).show();
                break;

        }
    }


}