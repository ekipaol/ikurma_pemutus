package com.application.bris.brisi_pemutus.page_putusan.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.history_catatan.HistoryCatatan;
import com.application.bris.brisi_pemutus.model.history_fasilitas.HistoryFasilitas;
import com.application.bris.brisi_pemutus.util.AppUtil;

import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class HistoryCatatanAdapter extends RecyclerView.Adapter<HistoryCatatanAdapter.PipelineViewHolder>{

    private Context context;
    private List<HistoryCatatan> data;

    public HistoryCatatanAdapter(Context context, List<HistoryCatatan> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_historycatatan, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PipelineViewHolder holder, final int position) {

        final HistoryCatatan datas = data.get(position);
        holder.tv_jabatan.setText(datas.getJabatan());
        holder.tv_nama.setText(datas.getNama_pemutus());
        holder.tv_catatan.setText((datas.getCatatan_pemutus()));
        holder.tv_jenis_putusan.setText(datas.getJenis_putusan());
        holder.tv_putusan.setText((datas.getPutusan_pemutus()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_jabatan, tv_nama, tv_catatan, tv_jenis_putusan, tv_putusan;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            tv_jabatan = (TextView) itemView.findViewById(R.id.tv_jabatan_pemutus);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama_pemutus);
            tv_catatan = (TextView) itemView.findViewById(R.id.tv_catatan_pemutus);
            tv_jenis_putusan = (TextView)itemView.findViewById(R.id.tv_jenis_putusan);
            tv_putusan = (TextView) itemView.findViewById(R.id.tv_putusan_pemutus);

        }
    }
}

