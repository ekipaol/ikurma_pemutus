package com.application.bris.brisi_pemutus.page_eklaim;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.Error;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseError;
import com.application.bris.brisi_pemutus.api.model.request.ReqUidRole;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.databinding.ActivityReminderKlaimBinding;
import com.application.bris.brisi_pemutus.model.DataReminderKlaim;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReminderKlaimActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    ActivityReminderKlaimBinding binding;
    private AdapterReminderKlaim adapterReminderKlaim;

    public static int idAplikasi = 0;
    private List<DataReminderKlaim> dataReminderKlaim = new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private SearchView searchView;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding View
        binding = ActivityReminderKlaimBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbarReguler.tbRegular);
        //Button Click
        setclickable();

        //Navbar
        customToolbar();

        //Sdk untuk background toolbar
        backgroundStatusBar();
        //initialize List
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        loadData();

        initialize();

    }

    private void loadData()  {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        AppPreferences appPreferences = new AppPreferences(this);

        ReqUidRole req = new ReqUidRole();
        //pantekan uid dan role
//        Toast.makeText(this, "Ada pantekan uid dan role", Toast.LENGTH_SHORT).show();
//        req.setUid("39377");
//        req.setRole(131);

        req.setUid(appPreferences.getUid());
        req.setRole(Integer.parseInt(appPreferences.getFidRole()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().listReminderClaim(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            String listDataString = response.body().getData().get("listData").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<DataReminderKlaim>>() {
                            }.getType();
                            dataReminderKlaim = gson.fromJson(listDataString, type);
                            if (dataReminderKlaim.size() > 0) {
                                binding.llEmptydata.setVisibility(View.GONE);
                                adapterReminderKlaim = new AdapterReminderKlaim(ReminderKlaimActivity.this, dataReminderKlaim);
                                binding.rvReminderKlaim.setLayoutManager(new LinearLayoutManager(ReminderKlaimActivity.this));
                                binding.rvReminderKlaim.setItemAnimator(new DefaultItemAnimator());
                                binding.rvReminderKlaim.setAdapter(adapterReminderKlaim);
                            } else {
                                binding.llEmptydata.setVisibility(View.VISIBLE);
                            }
                        } else {
                            AppUtil.notiferror(ReminderKlaimActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ReminderKlaimActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ReminderKlaimActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    public void initialize() {
        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);
        binding.refresh.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nama Nasabah, Cabang, Kol, dll ..");
        searchFilter();
        return true;

    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void customToolbar() {
        binding.toolbarReguler.tvPageTitle.setText("List Reminder Klaim");
        binding.toolbarReguler.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setclickable() {

    }

    public void updateTotalData(String jumlahData){
        binding.tvTotalData.setText("Total Data : "+jumlahData);
        binding.tvTotalData.setVisibility(View.VISIBLE);
    }

    private void searchFilter() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    adapterReminderKlaim.getFilter().filter(query);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapterReminderKlaim.getFilter().filter(query);
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

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

    @Override
    public void onRefresh() {

    }
}