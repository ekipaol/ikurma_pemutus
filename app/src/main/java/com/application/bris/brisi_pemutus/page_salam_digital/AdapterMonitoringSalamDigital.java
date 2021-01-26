package com.application.bris.brisi_pemutus.page_salam_digital;


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
import com.application.bris.brisi_pemutus.model.monitoring.MonitoringSalamDigital;
import com.application.bris.brisi_pemutus.model.monitoring.MonitoringSalamDigital;
import com.application.bris.brisi_pemutus.page_monitoring.MonitoringKcpActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class AdapterMonitoringSalamDigital extends RecyclerView.Adapter<AdapterMonitoringSalamDigital.HotprospekViewHolder> implements Filterable {

    private Context context;
    private List<MonitoringSalamDigital> data;
    private List<MonitoringSalamDigital> datafiltered;

    public AdapterMonitoringSalamDigital(Context context, List<MonitoringSalamDigital> data) {
        this.context = context;
        this.data = data;
        for (int i = 0; i <data.size() ; i++) {
            if(data.get(i).getNamaProduk()==null){
                data.get(i).setNamaProduk("");
            }

            if(!data.get(i).getDisposisi().equalsIgnoreCase("belum disposisi")){
                data.get(i).setDisposisi("Didisposisi ke : "+data.get(i).getDisposisi());
            }

        }
        this.datafiltered = data;

    }

    @NonNull
    @Override
    public AdapterMonitoringSalamDigital.HotprospekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_monitoring_salam_digital, parent, false);
        return new AdapterMonitoringSalamDigital.HotprospekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterMonitoringSalamDigital.HotprospekViewHolder holder, final int position) {
        final MonitoringSalamDigital datas = datafiltered.get(position);

        holder.tv_nama_nasabah.setText(datas.getNamaNasabah());
        holder.tv_plafond.setText(AppUtil.parseRupiah(datas.getPlafond()));
        holder.tv_unit_kerja.setText((datas.getUnitKerja()));



        holder.tv_status_prescreening.setText(datas.getPrescreening());

        if(datas.getDisposisi().equalsIgnoreCase("Belum disposisi")){
            holder.tv_status_disposisi.setText((datas.getDisposisi()));
            holder.tv_status_disposisi.setTextColor(context.getResources().getColor(R.color.red));
        }
        else{
            holder.tv_status_disposisi.setText(datas.getDisposisi());
            holder.tv_status_disposisi.setTextColor(context.getResources().getColor(R.color.green));
        }


        if(datas.getPrescreening().equalsIgnoreCase("gagal prescreening")){
            holder.tv_status_prescreening.setTextColor(context.getResources().getColor(R.color.red));
        }
        else{
            holder.tv_status_prescreening.setTextColor(context.getResources().getColor(R.color.green));
        }


        holder.tv_tanggal_pengajuan.setText(AppUtil.parseTanggalGeneral(datas.getTanggalPengajuan(),"yyyy-MM-dd HH:mm:ss","dd MMM yyyy"));
        holder.tv_nama_produk.setText(datas.getNamaProduk());


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
                    List<MonitoringSalamDigital> filteredList = new ArrayList<>();
                    for (MonitoringSalamDigital row : data){
                        if(row.getNamaNasabah().toLowerCase().contains(charString.toLowerCase())||row.getNamaProduk().toLowerCase().contains(charString.toLowerCase())||row.getDisposisi().toLowerCase().contains(charString.toLowerCase())||row.getPrescreening().toLowerCase().contains(charString.toLowerCase())||row.getUnitKerja().toLowerCase().contains(charString.toLowerCase())||row.getTanggalPengajuan().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<MonitoringSalamDigital>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class HotprospekViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_nama_nasabah, tv_plafond, tv_nama_produk, tv_status_prescreening, tv_status_disposisi, tv_unit_kerja,tv_tanggal_pengajuan;

        public HotprospekViewHolder(View itemView) {
            super(itemView);
            tv_nama_nasabah = (TextView) itemView.findViewById(R.id.tv_nama_nasabah);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_nama_produk = (TextView) itemView.findViewById(R.id.tv_nama_produk);
            tv_status_prescreening = (TextView) itemView.findViewById(R.id.tv_status_prescreening);
            tv_status_disposisi = (TextView) itemView.findViewById(R.id.tv_status_disposisi);
            tv_unit_kerja = (TextView) itemView.findViewById(R.id.tv_unit_kerja);
            tv_tanggal_pengajuan = (TextView) itemView.findViewById(R.id.tv_tanggal_pengajuan);

        }
    }
}