package com.application.bris.brisi_pemutus.page_putusan;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.history_putusan.ReqHistoryPutusan;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.history_fasilitas.HistoryFasilitas;
import com.application.bris.brisi_pemutus.page_putusan.adapters.AdapterHistory;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PutusanTab1KPR extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View mview;


    RecyclerView rv_listuser;

    RelativeLayout progressbar_loading;

    SwipeRefreshLayout swipeRefreshLayout;

    LottieAnimationView whale;

    TextView tvWhale;



    ShimmerFrameLayout shimmer;

    private SearchView searchView;
    List<HistoryFasilitas> dataUser;
    AdapterHistory adapterUser;
    LinearLayoutManager layoutUser;
    private ApiClientAdapter apiClientAdapter;



    public PutusanTab1KPR() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PutusanTab1KPR.
     */
    // TODO: Rename and change types and number of parameters
    public static PutusanTab1KPR newInstance(String param1, String param2) {
        PutusanTab1KPR fragment = new PutusanTab1KPR();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiClientAdapter = new ApiClientAdapter(getContext());




    }

    public void main(){
        ButterKnife.bind(getActivity());

    }

    public void initializeUser(){
        //  dataUser = getListUser();
        AppPreferences apppref=new AppPreferences(getActivity());
        // progressbar_loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);
        ReqHistoryPutusan req = new ReqHistoryPutusan();
//        req.setCif(Integer.parseInt(getActivity().getIntent().getStringExtra("cif")));
//        req.setId_aplikasi(Integer.parseInt(getActivity().getIntent().getStringExtra("idAplikasi")));

        //pantekan untuk testing
        req.setCif(81857);
        req.setId_aplikasi(101678);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryHistory(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                // progressbar_loading.setVisibility(View.GONE);
                shimmer.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    if(response.body().getStatus().equalsIgnoreCase("00")){
                        String listDataString = response.body().getData().get("historyFasilitas").toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<HistoryFasilitas>>() {}.getType();

                        dataUser = gson.fromJson(listDataString, type);
                        adapterUser = new AdapterHistory(getActivity(), dataUser);
                        rv_listuser.setLayoutManager(new LinearLayoutManager(getActivity()));
                        rv_listuser.setItemAnimator(new DefaultItemAnimator());
                        rv_listuser.addItemDecoration(new MaterialViewPagerHeaderDecorator());
                        rv_listuser.setAdapter(adapterUser);
                        if(dataUser.size()==0){
                            whale.setVisibility(View.VISIBLE);
                            tvWhale.setVisibility(View.VISIBLE);
                        }
                        else{
                            whale.setVisibility(View.GONE);
                            tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // HARUS BIKIN BEGINI UNTUK SEMUA FRAGMENT, AGAR NESTEDSCROLLVIEW BISA DIREGISTER KE MATERIALVIEWPAGER
        View v=inflater.inflate(R.layout.activity_history,container,false);
        mview=v;
        NestedScrollView scrollView=mview.findViewById(R.id.observablescrollview);
        MaterialViewPagerHelper.registerScrollView(getActivity(),scrollView);

        shimmer=mview.findViewById(R.id.ll_shimmer);
        tvWhale=mview.findViewById(R.id.tvWhale);
        whale=mview.findViewById(R.id.animWhale);
//        swipeRefreshLayout=mview.findViewById(R.id.refresh);
        progressbar_loading=mview.findViewById(R.id.progressbar_loading);
        rv_listuser=mview.findViewById(R.id.rv_listuser);



        main();
        initializeUser();

        return mview;
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
