package com.application.bris.brisi.view.corelayout.home;

import android.animation.ArgbEvaluator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi.R;
import com.application.bris.brisi.adapter.hotprospek.HotprospekHomeAdapater;
import com.application.bris.brisi.config.hotprospekhome.Hotprospekhome;
import com.application.bris.brisi.config.menu.Menu;
import com.application.bris.brisi.adapter.menu.MenuAdapter;
import com.application.bris.brisi.config.pipelinehome.Pipelinehome;
import com.application.bris.brisi.listener.menu.MenuClickListener;
import com.application.bris.brisi.model.hotprospek.hotprospek;
import com.application.bris.brisi.model.menu.ListViewMenu;
import com.application.bris.brisi.adapter.pipeline.PipelineHomeAdapater;
import com.application.bris.brisi.model.pipeline.pipeline;
import com.application.bris.brisi.util.AppBarStateChangedListener;
import com.application.bris.brisi.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PID on 3/29/2019.
 */

public class FragmentHome extends Fragment implements MenuClickListener {
    private View view;

    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbartitle)
    TextView tv_toolbartitle;
    @BindView(R.id.rv_menu)
    RecyclerView rv_menu;
    @BindView(R.id.rv_pipeline)
    RecyclerView rv_pipeline;
    @BindView(R.id.rv_hotprospek)
    RecyclerView rv_hotprospek;

    List<ListViewMenu> dataMenu;
    List<pipeline> dataPipeline;
    List<hotprospek> dataHotprospek;
    MenuAdapter adapterMenu;
    PipelineHomeAdapater adapaterPipeline;
    HotprospekHomeAdapater adapterHotprospek;
    GridLayoutManager layoutMenu;
    int coloumMenu = 3;
    LinearLayoutManager layoutPipelineHome;
    LinearLayoutManager layoutHotprospekHome;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        checkCollapse();
        initializeMenu();
        initializePipelineHome();
        initializeHotprospekHome();
        return view;
    }

    public void initializeMenu(){
        rv_menu.setHasFixedSize(true);
        dataMenu = getListMenu();
        adapterMenu = new MenuAdapter(getContext(), dataMenu, this);
        layoutMenu = new GridLayoutManager(getContext(), coloumMenu);
        rv_menu.setLayoutManager(layoutMenu);
        rv_menu.setAdapter(adapterMenu);
        ViewCompat.setNestedScrollingEnabled(rv_menu, false);
    }

    private List<ListViewMenu> getListMenu() {
        List<ListViewMenu> menu = new ArrayList<>();
        Menu.mainMenuAO(getContext(), menu);
        return menu;
    }

    public void initializePipelineHome(){
        dataPipeline = getListPipelineHome();
        adapaterPipeline = new PipelineHomeAdapater(getContext(), dataPipeline);
        layoutPipelineHome = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_pipeline.setLayoutManager(layoutPipelineHome);
        rv_pipeline.setAdapter(adapaterPipeline);
        ViewCompat.setNestedScrollingEnabled(rv_pipeline, false);
    }

    private List<pipeline> getListPipelineHome(){
        List<pipeline> pipelines = new ArrayList<>();
        Pipelinehome.listPipeline(getContext(), pipelines);
        return pipelines;
    }

    public void initializeHotprospekHome(){
        dataHotprospek = getListHotprospekHome();
        adapterHotprospek = new HotprospekHomeAdapater(getContext(), dataHotprospek);
        layoutHotprospekHome = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_hotprospek.setLayoutManager(layoutHotprospekHome);
        rv_hotprospek.setAdapter(adapterHotprospek);
        ViewCompat.setNestedScrollingEnabled(rv_hotprospek, false);
    }

    private List<hotprospek> getListHotprospekHome(){
        List<hotprospek> hotprospeks = new ArrayList<>();
        Hotprospekhome.listHotprospek(getContext(), hotprospeks);
        return hotprospeks;
    }



    @Override
    public void onMenuClick(String menu) {
        AppUtil.showToastShort(getContext(), menu);
    }

    private void checkCollapse(){
        appbar.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d(getClass().getCanonicalName(), state.name());
                if (state.name().equalsIgnoreCase("COLLAPSED")){
                    tv_toolbartitle.setText("BRISI");
                    backgroundStatusBar(state.name());
                }
                else {
                    backgroundStatusBar(state.name());
                    tv_toolbartitle.setText("");
                }
            }
        });
    }

    private void backgroundStatusBar(String state){
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            if (state.equalsIgnoreCase("COLLAPSED")){
                window.setStatusBarColor(getActivity().getResources().getColor(R.color.colorWhite));
            }
            else {
                window.setStatusBarColor(getActivity().getResources().getColor(R.color.colorBackgroundTransparent));
            }
        }
    }
}
