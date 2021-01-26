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
import com.application.bris.brisi_pemutus.model.monitoring.KcMonitoring;
import com.application.bris.brisi_pemutus.model.monitoring.KcpMonitoring;

import java.util.ArrayList;
import java.util.List;

public class AdapterMonitoringKc extends RecyclerView.Adapter<AdapterMonitoringKc.HotprospekViewHolder> implements Filterable {

    private Context context;
    private List<KcMonitoring> data;
    private List<KcMonitoring> datafiltered;

    public AdapterMonitoringKc(Context context, List<KcMonitoring> data) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;




    }

    @NonNull
    @Override
    public AdapterMonitoringKc.HotprospekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_monitoring_kc, parent, false);
        return new AdapterMonitoringKc.HotprospekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterMonitoringKc.HotprospekViewHolder holder, final int position) {
        final KcMonitoring datas = datafiltered.get(position);

        holder.tv_nama_kc.setText(datas.getNAMA_CABANG());
        holder.tv_pencairan_kc.setText((datas.getTOTAL_PENCAIRAN())+"%");
        holder.tv_dpk_kc.setText((datas.getTOTAL_DPK())+"%");
        holder.tv_outstanding_kc.setText((datas.getTOTAL_OS())+"%");
        holder.tv_kol_kc.setText(datas.getTOTAL_KOL2()+"%");
        holder.tv_npf_kc.setText(datas.getTOTAL_NPF()+"%");

        holder.bt_detail_kc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MonitoringKcpActivity.class);
                intent.putExtra("kodeSkk",datas.getKODE_SKK());
                intent.putExtra("namaCabang",datas.getNAMA_CABANG());
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
            Log.d("ukuran_si_kc",Integer.toString(datafiltered.size()));
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
                    datafiltered = data;
                } else {
                    List<KcMonitoring> filteredList = new ArrayList<>();
                    for (KcMonitoring row : data){
                        if(row.getNAMA_CABANG().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<KcMonitoring>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class HotprospekViewHolder extends RecyclerView.ViewHolder {
        private Button bt_detail_kc;
        private TextView tv_nama_kc, tv_pencairan_kc, tv_outstanding_kc, tv_dpk_kc, tv_kol_kc, tv_npf_kc;

        public HotprospekViewHolder(View itemView) {
            super(itemView);
            tv_nama_kc = (TextView) itemView.findViewById(R.id.tv_nama_kc);
            tv_pencairan_kc = (TextView) itemView.findViewById(R.id.tv_pencairan_kc);
            tv_outstanding_kc = (TextView) itemView.findViewById(R.id.tv_outstanding_kc);
            tv_dpk_kc = (TextView) itemView.findViewById(R.id.tv_dpk_kc);
            tv_kol_kc = (TextView) itemView.findViewById(R.id.tv_kol_kc);
            tv_npf_kc = (TextView) itemView.findViewById(R.id.tv_npf_kc);
            bt_detail_kc = (Button) itemView.findViewById(R.id.bt_detil_monitoring_pencairan_kc);

        }
    }
}