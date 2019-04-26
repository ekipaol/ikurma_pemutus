package com.application.bris.brisi.model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.application.bris.brisi.R;
import com.application.bris.brisi.config.menu.Menu;
import com.application.bris.brisi.config.menu.MenuAdapter;
import com.application.bris.brisi.config.menu.MenuClickListener;
import com.application.bris.brisi.config.menu.TestAdapter;
import com.application.bris.brisi.model.menu.ListViewMenu;
import com.application.bris.brisi.model.menu.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity2 extends AppCompatActivity implements MenuClickListener {
    private RecyclerView recyclerView;
    private TestAdapter adapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
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
        setContentView(R.layout.activity_test);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        addData();

        adapter = new TestAdapter(mahasiswaArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity2.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

    }

    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Mahasiswa("Dimas Maulana", "1414370309", "123456789"));
        mahasiswaArrayList.add(new Mahasiswa("Fadly Yonk", "1214234560", "987654321"));
        mahasiswaArrayList.add(new Mahasiswa("Ariyandi Nugraha", "1214230345", "987648765"));
        mahasiswaArrayList.add(new Mahasiswa("Aham Siswana", "1214378098", "098758124"));
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
