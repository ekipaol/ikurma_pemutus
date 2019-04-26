package com.application.bris.brisi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.application.bris.brisi.config.menu.Menu;
import com.application.bris.brisi.config.menu.MenuAdapter;
import com.application.bris.brisi.config.menu.MenuClickListener;
import com.application.bris.brisi.model.MainActivity2;
import com.application.bris.brisi.model.menu.ListViewMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MenuClickListener {
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private RecyclerView rv_menu;
//    @BindView(R.id.rv_menu)
//    RecyclerView rv_menu;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    Intent it = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(it);
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setSupportActionBar(toolbar);
        rv_menu = (RecyclerView) findViewById(R.id.rv_menu);
        rv_menu.setHasFixedSize(true);
        List<ListViewMenu> listMenu = getListMenu();
        MenuAdapter menuAdapter = new MenuAdapter(MainActivity.this, listMenu, MainActivity.this);
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 3);
//        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 3);
//        rv_menu.setLayoutManager(layoutManager);
//        rv_menu.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rv_menu.setLayoutManager(layoutManager);
        rv_menu.setAdapter(menuAdapter);

//        collapsing_toolbar.setCollapsedTitleTextColor(getApplicationContext().getResources().getColor(R.color.colorWhite));
//        collapsing_toolbar.setExpandedTitleColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));

    }

    private List<ListViewMenu> getListMenu() {
        List<ListViewMenu> menu = new ArrayList<>();
        Menu.mainMenuAO(this, menu);
        return menu;
    }

    @Override
    public void onMenuClick(String menu) {
        Toast.makeText(this, menu, Toast.LENGTH_SHORT);
    }
}
