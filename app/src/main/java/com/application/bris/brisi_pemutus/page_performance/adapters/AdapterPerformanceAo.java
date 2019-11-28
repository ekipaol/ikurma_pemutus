package com.application.bris.brisi_pemutus.page_performance.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.dashboard.RequestDashboard;
import com.application.bris.brisi_pemutus.api.model.request.dashboard_pemrakarsa.RequestDashboardPemrakarsa;
import com.application.bris.brisi_pemutus.api.model.request.data_cabang.RequestDataCabang;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.model.data_ao.Ao;
import com.application.bris.brisi_pemutus.model.kantor_cabang.KantorCabang;
import com.application.bris.brisi_pemutus.model.performance_ao.PerformanceAo;
import com.application.bris.brisi_pemutus.page_daftar_user.view.DetailUserActivity;
import com.application.bris.brisi_pemutus.page_daftar_user.view.UserActivity;
import com.application.bris.brisi_pemutus.page_performance.PerformanceAoActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterPerformanceAo extends RecyclerView.Adapter<AdapterPerformanceAo.UserViewHolder> implements Filterable {
    private Context context;
    private List<PerformanceAo> data;
    private List<PerformanceAo> datafiltered=new ArrayList<>();
    private List<Ao> dataPerson;
    Call<ParseResponse> call;
    private ApiClientAdapter apiClientAdapter;
    private String kodeSkk;
    private int uid;
    private String jabatanContainer="";
    private BigDecimal totalPencairan;
    private int totalHotprospek,totalPipeline,totalDisetujui,totalDitolak,totalNoa;


    public AdapterPerformanceAo(Context context, List<PerformanceAo> data, List<Ao> dataPerson) {
        this.context = context;
        this.data = data;
        this.datafiltered=data;
        this.dataPerson=dataPerson;
        this.totalPencairan=new BigDecimal(0);
        this.totalHotprospek=0;
        this.totalPipeline=0;
        this.totalDisetujui=0;
        this.totalDitolak=0;
        this.totalNoa=0;

        for (int i = 0; i <data.size() ; i++) {
            totalPencairan=totalPencairan.add(new BigDecimal(data.get(i).getAMOUNT()));
            totalHotprospek=totalHotprospek+Integer.parseInt(data.get(i).getHOT_PROSPEK());
            totalPipeline=totalPipeline+Integer.parseInt(data.get(i).getPIPELINE());
            totalDisetujui=totalDisetujui+Integer.parseInt(data.get(i).getDIPUTUS());
            totalDitolak=totalDitolak+Integer.parseInt(data.get(i).getJLH_DITOLAK());
            totalNoa=totalNoa+Integer.parseInt(data.get(i).getNOA());
        }

        ((PerformanceAoActivity)context).updateTotal(AppUtil.parseRupiah(String.valueOf(totalPencairan)),String.valueOf(totalPipeline),String.valueOf(totalHotprospek),String.valueOf(totalDisetujui),String.valueOf(totalDitolak),String.valueOf(totalNoa));






    }



    @NonNull
    @Override
    public AdapterPerformanceAo.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_performance_ao, parent, false);
        return new AdapterPerformanceAo.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterPerformanceAo.UserViewHolder holder, final int position) {
        final PerformanceAo datas = datafiltered.get(position);
        final Ao dataPersonnel = dataPerson.get(position);

        for (int i = 0; i <datafiltered.size() ; i++) {
            if(datafiltered.get(i).getNAMA_PEGAWAI().equalsIgnoreCase(dataPersonnel.getNama_pegawai())){
                jabatanContainer=dataPerson.get(i).getJabatan();
                break;
            }
            else{
                jabatanContainer="AOM";
            }
        }


        holder.tv_nama.setText(datas.getNAMA_PEGAWAI());
        holder.tv_kantor.setText(datas.getSBDESC());
        holder.tv_jabatan.setText(jabatanContainer);
        holder.tv_pipeline.setText(datas.getPIPELINE());
        holder.tv_hotprospek.setText(datas.getHOT_PROSPEK());
        holder.tv_approved.setText(datas.getDIPUTUS());
        holder.tv_rejected.setText(datas.getJLH_DITOLAK());
        holder.tv_noa.setText(datas.getNOA());
        holder.tv_amount.setText(AppUtil.parseRupiah(datas.getAMOUNT()));

         totalPencairan=totalPencairan.add(new BigDecimal(datas.getAMOUNT()));





//        getDataPerformance(kodeSkk,Integer.parseInt(datas.getUid()),holder.tv_hotprospek,holder.tv_pipeline,holder.tv_approved,holder.tv_rejected,holder.progress_pipeline,holder.progress_hotprospek,holder.progress_approved,holder.progress_rejected);


    }



    @Override
    public int getItemCount() {
        //return data.size();
//        tambah jika sudah ada fitur search
        return datafiltered.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    datafiltered = data;
                } else {
                    List<PerformanceAo> filteredList = new ArrayList<>();
                    for (PerformanceAo row : data){
                        if(row.getNAMA_PEGAWAI().toLowerCase().contains(charString.toLowerCase())  || row.getSBDESC().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(row);
                        }
                    }
                    datafiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = datafiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                datafiltered = (ArrayList<PerformanceAo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private void getDataPerformance(String kodeSkk, int uid, final TextView hotprospek, final TextView pipeline,final TextView approved,final TextView rejected, final ProgressBar progressPipeline, final ProgressBar progressHotprospek,final ProgressBar progressApproved,final ProgressBar progressRejected) {
        RequestDashboardPemrakarsa req = new RequestDashboardPemrakarsa();
        req.setKodeSkk(kodeSkk);
        req.setUid(uid);
        call = apiClientAdapter.getApiInterface().dashboardPemrakarsa(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call2, Response<ParseResponse> response) {
                if (response.isSuccessful()) {
                    progressHotprospek.setVisibility(View.GONE);
                    progressPipeline.setVisibility(View.GONE);
                    progressApproved.setVisibility(View.GONE);
                    progressRejected.setVisibility(View.GONE);
                    hotprospek.setVisibility(View.VISIBLE);
                    pipeline.setVisibility(View.VISIBLE);
                    approved.setVisibility(View.VISIBLE);
                    rejected.setVisibility(View.VISIBLE);

                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        Gson gson = new Gson();
                        Type type1 = new TypeToken<List<KantorCabang>>() {
                        }.getType();

                        hotprospek.setText(response.body().getData().get("jlhHotProspek").toString());
                        pipeline.setText(response.body().getData().get("jlhPipeline").toString());
                        approved.setText(response.body().getData().get("jlhApproved").toString());
                        rejected.setText(response.body().getData().get("jlhRejected").toString());
                    } else {
                        Toast.makeText(context, "Error occured", Toast.LENGTH_SHORT).show();
//                        context.finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call2, Throwable t) {
                Log.d("LOG D", t.getMessage());
                Toast.makeText(context, "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
//                finish();
            }
        });
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_nama, tv_kantor, tv_hotprospek, tv_pipeline,tv_jabatan,tv_approved,tv_rejected,tv_noa,tv_amount;
        private ProgressBar progress_pipeline,progress_hotprospek,progress_approved,progress_rejected,progress_noa,progress_amount;
//        private Button btDetail;
//        CardView cv_content;

        public UserViewHolder(View itemView) {
            super(itemView);

            tv_nama =  itemView.findViewById(R.id.tv_nama_ao_performance);
            tv_kantor =  itemView.findViewById(R.id.tv_kantor_performance_ao);

            tv_jabatan =  itemView.findViewById(R.id.tv_jabatan_performance_ao);
            tv_hotprospek=itemView.findViewById(R.id.tv_hotprospek_performance_ao);
            tv_pipeline=itemView.findViewById(R.id.tv_pipeline_performance_ao);
            tv_approved=itemView.findViewById(R.id.tv_approved_performance_ao);
            tv_rejected=itemView.findViewById(R.id.tv_rejected_performance_ao);
            tv_noa=itemView.findViewById(R.id.tv_noa_performance_ao);
            tv_amount=itemView.findViewById(R.id.tv_amount_performance_ao);
            progress_hotprospek=itemView.findViewById(R.id.progress_hotprospek_performance);
            progress_pipeline=itemView.findViewById(R.id.progress_pipeline_performance);
            progress_approved=itemView.findViewById(R.id.progress_approved_performance);
            progress_rejected=itemView.findViewById(R.id.progress_rejected_performance);
            progress_noa=itemView.findViewById(R.id.progress_noa_performance);
            progress_amount=itemView.findViewById(R.id.progress_amount_performance);




        }
    }
}

