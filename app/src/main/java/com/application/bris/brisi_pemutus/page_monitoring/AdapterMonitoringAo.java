package com.application.bris.brisi_pemutus.page_monitoring;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.monitoring.AoMonitoring;
import com.application.bris.brisi_pemutus.model.monitoring.NasabahMonitoring;
import com.application.bris.brisi_pemutus.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class AdapterMonitoringAo extends RecyclerView.Adapter<AdapterMonitoringAo.HotprospekViewHolder> implements Filterable {

    private Context context;
    private List<AoMonitoring> data;
    private List<AoMonitoring> datafiltered=new ArrayList<>();
    private List<AoMonitoring> datafilteredAgain=new ArrayList<>();
    AppPreferences appPreferences;

    public AdapterMonitoringAo(Context context, List<AoMonitoring> data) {
        this.context = context;
        this.data = data;

        appPreferences=new AppPreferences(context);

        Log.d("duperthisid",appPreferences.getFidRole());

        //UH dan MMM hanya bisa ngeliat monitoring AOM
        if(appPreferences.getFidRole().equalsIgnoreCase("71")||appPreferences.getFidRole().equalsIgnoreCase("72")){
            for (int i = 0; i <data.size() ; i++) {
                if(data.get(i).getFID_ROLE().equalsIgnoreCase("8")){
                    datafiltered.add(data.get(i));

                }
            }
        }

        //MM hanya bisa ngeliat data AO general
        else if(appPreferences.getFidRole().equalsIgnoreCase("77")) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getFID_ROLE().equalsIgnoreCase("100")) {
                    datafiltered.add(data.get(i));

                }
            }
        }

        else{
            this.datafiltered=data;
        }

        datafilteredAgain=datafiltered;




    }

    @NonNull
    @Override
    public AdapterMonitoringAo.HotprospekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_monitoring_ao, parent, false);
        return new AdapterMonitoringAo.HotprospekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterMonitoringAo.HotprospekViewHolder holder, final int position) {
        final AoMonitoring datas = datafiltered.get(position);

        holder.tv_nama_ao.setText(datas.getNAMA_PEGAWAI().toUpperCase());
        holder.tv_pencairan_ao.setText((datas.getTOTAL_PENCAIRAN())+"%");
        holder.tv_dpk_ao.setText((datas.getTOTAL_DPK())+"%");
        holder.tv_outstanding_ao.setText((datas.getTOTAL_OS())+"%");
        holder.tv_kol_ao.setText(datas.getTOTAL_KOL2()+"%");
        holder.tv_npf_ao.setText(datas.getTOTAL_NPF()+"%");

        holder.tv_total_pipeline.setText(datas.getTOTAL_PIPELINE());
        holder.tv_total_hotprospek.setText(datas.getTOTAL_HOTPROSPEK());
        holder.tv_total_approved.setText(datas.getTOTAL_APPROVED());
        holder.tv_total_cair.setText(datas.getTOTAL_CAIR());

        if(datas.getFID_ROLE().equalsIgnoreCase("8")){
            holder.tv_jabatan.setText("MRM/MS/TAD");
        }
        else{
            holder.tv_jabatan.setText("Marketing");
        }

        holder.bt_detail_ao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MonitoringNasabahActivity.class);
//                intent.putExtra("uid",Integer.parseInt(datas.getFID_UID()));
                intent.putExtra("noPegawai",(datas.getNO_PEGAWAI()));
                intent.putExtra("namaPegawai",(datas.getNAMA_PEGAWAI()));

                context.startActivity(intent);
//              Toast.makeText(context, "Anda memencet user : "+datas.getNamaNasabah(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(datafiltered==null){
            return 0;
        }
        else{
            return datafiltered.size();
        }

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    datafiltered = datafilteredAgain;
                } else {
                    List<AoMonitoring> filteredList = new ArrayList<>();
                    for (AoMonitoring row : datafilteredAgain){
                        if(row.getNAMA_PEGAWAI().toLowerCase().contains(charString.toLowerCase())
                        ){
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
                datafiltered = (ArrayList<AoMonitoring>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class HotprospekViewHolder extends RecyclerView.ViewHolder {
        private Button bt_detail_ao;
        private TextView tv_nama_ao, tv_pencairan_ao, tv_outstanding_ao, tv_dpk_ao, tv_kol_ao,tv_npf_ao,tv_jabatan;
        private TextView tv_total_pipeline, tv_total_hotprospek, tv_total_approved, tv_total_cair;

        public HotprospekViewHolder(View itemView) {
            super(itemView);
            tv_nama_ao = (TextView) itemView.findViewById(R.id.tv_nama_ao);
            tv_pencairan_ao = (TextView) itemView.findViewById(R.id.tv_pencairan_ao);
            tv_outstanding_ao = (TextView) itemView.findViewById(R.id.tv_outstanding_ao);
            tv_dpk_ao = (TextView) itemView.findViewById(R.id.tv_dpk_ao);
            tv_kol_ao = (TextView) itemView.findViewById(R.id.tv_kol_ao);
            tv_npf_ao= (TextView) itemView.findViewById(R.id.tv_npf_ao);
            tv_jabatan= (TextView) itemView.findViewById(R.id.tv_jabatan);
            tv_total_pipeline= (TextView) itemView.findViewById(R.id.tv_total_pipeline);
            tv_total_hotprospek= (TextView) itemView.findViewById(R.id.tv_total_hotprospek);
            tv_total_approved= (TextView) itemView.findViewById(R.id.tv_total_approved);
            tv_total_cair= (TextView) itemView.findViewById(R.id.tv_total_cair);
            bt_detail_ao = (Button) itemView.findViewById(R.id.bt_detil_monitoring_pencairan_ao);

        }
    }
}
