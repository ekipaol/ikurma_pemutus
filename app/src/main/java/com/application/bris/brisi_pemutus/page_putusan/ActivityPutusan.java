package com.application.bris.brisi_pemutus.page_putusan;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.page_putusan.adapters.ViewPagerAdapter;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;

public class ActivityPutusan extends AppCompatActivity   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.putusan_viewpager);

        MaterialViewPager mViewPager =  findViewById(R.id.viewpager_putusan);
        Toolbar mToolbar=mViewPager.getToolbar();

//        if (mToolbar != null) {
//            setSupportActionBar(mToolbar);
//
//            ActionBar actionBar = getSupportActionBar();
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowHomeEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);
//            actionBar.setDisplayUseLogoEnabled(false);
//            actionBar.setHomeButtonEnabled(true);
//
//
//        }

        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        ViewPager viewPager = mViewPager.getViewPager();


        ViewPagerAdapter myPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        myPagerAdapter.addFrag(new PutusanTab1KPR(), "History Status");
        myPagerAdapter.addFrag(new PutusanTab2Mikro(), "History Fasilitas");
        myPagerAdapter.addFrag(new PutusanTab3KPR(), "Testing Agunan");

        viewPager.setAdapter(myPagerAdapter);

//After set an adapter to the ViewPager
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

    }



}
