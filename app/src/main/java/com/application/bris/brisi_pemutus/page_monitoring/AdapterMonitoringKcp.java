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
import com.application.bris.brisi_pemutus.model.monitoring.KcpMonitoring;

import java.util.ArrayList;
import java.util.List;

public class AdapterMonitoringKcp extends RecyclerView.Adapter<AdapterMonitoringKcp.HotprospekViewHolder> implements Filterable {

    private Context context;
    private List<KcpMonitoring> data;
    private List<KcpMonitoring> datafiltered;

    public AdapterMonitoringKcp(Context context, List<KcpMonitoring> data) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;




    }

    @NonNull
    @Override
    public AdapterMonitoringKcp.HotprospekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_monitoring_kcp, parent, false);
        return new AdapterMonitoringKcp.HotprospekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterMonitoringKcp.HotprospekViewHolder holder, final int position) {
        final KcpMonitoring datas = datafiltered.get(position);

        holder.tv_nama_kcp.setText(datas.getNAMA_CABANG());
        holder.tv_pencairan_kcp.setText((datas.getTOTAL_PENCAIRAN())+"%");
        holder.tv_dpk_kcp.setText((datas.getTOTAL_DPK())+"%");
        holder.tv_outstanding_kcp.setText((datas.getTOTAL_OS())+"%");
        holder.tv_kol_kcp.setText(datas.getTOTAL_KOL2()+"%");
        holder.tv_npf_kcp.setText(datas.getTOTAL_NPF()+"%");

        holder.bt_detail_kcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MonitoringAoActivity.class);
                intent.putExtra("namaCabang",datas.getNAMA_CABANG());
                intent.putExtra("kodeSkk",datas.getKODE_CABANG());
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
            Log.d("ukuran_si_kcp",Integer.toString(datafiltered.size()));
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
                    List<KcpMonitoring> filteredList = new ArrayList<>();
                    for (KcpMonitoring row : data){
                        if(row.getNAMA_CABANG().toLowerCase().contains(charString.toLowerCase()) || row.getKODE_CABANG().toLowerCase().contains(charString.toLowerCase())
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
                datafiltered = (ArrayList<KcpMonitoring>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class HotprospekViewHolder extends RecyclerView.ViewHolder {
        private Button bt_detail_kcp;
        private TextView tv_nama_kcp, tv_pencairan_kcp, tv_outstanding_kcp, tv_dpk_kcp, tv_kol_kcp,tv_npf_kcp;

        public HotprospekViewHolder(View itemView) {
            super(itemView);
            tv_nama_kcp = (TextView) itemView.findViewById(R.id.tv_nama_kcp);
            tv_pencairan_kcp = (TextView) itemView.findViewById(R.id.tv_pencairan_kcp);
            tv_outstanding_kcp = (TextView) itemView.findViewById(R.id.tv_outstanding_kcp);
            tv_dpk_kcp = (TextView) itemView.findViewById(R.id.tv_dpk_kcp);
            tv_kol_kcp = (TextView) itemView.findViewById(R.id.tv_kol_kcp);
            tv_npf_kcp= (TextView) itemView.findViewById(R.id.tv_npf_kcp);
            bt_detail_kcp = (Button) itemView.findViewById(R.id.bt_detil_monitoring_pencairan_kcp);

        }
    }
}