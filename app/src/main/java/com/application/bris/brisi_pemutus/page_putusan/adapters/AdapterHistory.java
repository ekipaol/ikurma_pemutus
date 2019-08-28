package com.application.bris.brisi_pemutus.page_putusan.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.data_ao.Ao;
import com.application.bris.brisi_pemutus.model.history_fasilitas.HistoryFasilitas;
import com.application.bris.brisi_pemutus.page_daftar_user.view.DetailUserActivity;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.ArrayList;
import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.UserViewHolder>  {
    private Context context;
    private List<HistoryFasilitas> data;
    private List<HistoryFasilitas> datafiltered;

    public AdapterHistory(Context context, List<HistoryFasilitas> data) {
        this.context = context;
        this.data = data;
        this.datafiltered=data;

    }



    @NonNull
    @Override
    public AdapterHistory.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_history, parent, false);
        return new AdapterHistory.UserViewHolder(view,viewType);
    }
    @Override
    public int getItemCount() {
        //return data.size();
//        tambah jika sudah ada fitur search
        return datafiltered.size();
    }

    @Override
    public void onBindViewHolder(final AdapterHistory.UserViewHolder holder, final int position) {
        final HistoryFasilitas datas = datafiltered.get(position);


        holder.status.setText(datas.getStatus_aplikasi());
        holder.tanggal.setText(datas.getTanggal_entry());
        holder.pemrakarsa.setText(datas.getNama_pemrakarsa());
        holder.waktu.setText(datas.getTanggal_entry());





    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }




    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView status, pemrakarsa, waktu, tanggal;
        public TimelineView mTimelineView;

        public UserViewHolder(View itemView,int viewType) {
            super(itemView);
            mTimelineView = (TimelineView) itemView.findViewById(R.id.timeline_history);
            mTimelineView.initLine(viewType);

            status =  itemView.findViewById(R.id.tv_status_history);
            pemrakarsa =  itemView.findViewById(R.id.tv_pemrakarsa_history);
            waktu = itemView.findViewById(R.id.tv_waktu_history);
            tanggal =  itemView.findViewById(R.id.tv_tanggal_history);


        }
    }
}