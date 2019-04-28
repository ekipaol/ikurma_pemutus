package com.application.bris.brisi.view.corelayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.application.bris.brisi.R;
import com.application.bris.brisi.util.AppUtil;
import com.application.bris.brisi.util.BackStackFragment;
import com.application.bris.brisi.view.corelayout.home.FragmentHome;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoreLayoutActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private Fragment fragment;
    private boolean doubleBackToExitPressedOnce = false;


    @BindView(R.id.bn_main)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corelayout);
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new FragmentHome());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (android.support.v4.app.Fragment frg : getSupportFragmentManager().getFragments()) {
            frg.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        if (!BackStackFragment.handleBackPressed(getSupportFragmentManager())) {{

            if(doubleBackToExitPressedOnce){
                finish();
                return;
            }
            doubleBackToExitPressedOnce = true;
            AppUtil.showToastShort(this, "Tekan sekali lagi untuk keluar aplikasi");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }}
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragment  = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                AppUtil.showToastShort(getApplicationContext(), "home");
                fragment = new FragmentHome();
                break;
            case R.id.navigation_dashboard:
                AppUtil.showToastShort(getApplicationContext(), "dashboard");
                fragment = new FragmentHome();
                break;
            case R.id.navigation_notifications:
                AppUtil.showToastShort(getApplicationContext(), "user");
                fragment = new FragmentHome();
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }
        return false;
    }
}
