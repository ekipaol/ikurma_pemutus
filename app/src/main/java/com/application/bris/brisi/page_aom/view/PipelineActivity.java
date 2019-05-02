package com.application.bris.brisi.page_aom.view;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RelativeLayout;

import com.application.bris.brisi.R;
import com.application.bris.brisi.config.pipelinehome.Pipelinehome;
import com.application.bris.brisi.model.pipeline.pipeline;
import com.application.bris.brisi.page_aom.adapter.pipeline.PipelineAdapater;
import com.application.bris.brisi.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PipelineActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.rv_listpipeline)
    RecyclerView rv_listpipeline;
    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private SearchView searchView;
    List<pipeline> dataPipeline;
    PipelineAdapater adapaterPipeline;
    LinearLayoutManager layoutPipeline;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_pipeline);
        main();
        backgroundStatusBar();
        initializePipelineHome();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nama Nasabah, Produk, dll ..");

        searchPipeline();

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Daftar Pipeline");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
    }

    public void initializePipelineHome(){
        dataPipeline = getListPipelineHome();
        adapaterPipeline = new PipelineAdapater(this, dataPipeline);
        rv_listpipeline.setLayoutManager(new LinearLayoutManager(PipelineActivity.this));
        rv_listpipeline.setItemAnimator(new DefaultItemAnimator());
        rv_listpipeline.setAdapter(adapaterPipeline);
    }

    private List<pipeline> getListPipelineHome(){
        List<pipeline> pipelines = new ArrayList<>();
        Pipelinehome.listPipeline(this, pipelines);
        return pipelines;
    }

    private void searchPipeline(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapaterPipeline.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapaterPipeline.getFilter().filter(query);
                    return false;
                }
                catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        PipelineActivity.this.recreate();
    }
}
