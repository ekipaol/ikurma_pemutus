package com.application.bris.brisi_pemutus.page_putusan.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.history_fasilitas.HistoryFasilitas;
import com.application.bris.brisi_pemutus.util.AppUtil;

import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class HistoryFasilitasAdapater extends RecyclerView.Adapter<HistoryFasilitasAdapater.PipelineViewHolder>{

    private Context context;
    private List<HistoryFasilitas> data;

    public HistoryFasilitasAdapater(Context context, List<HistoryFasilitas> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_historyfasilitas, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PipelineViewHolder holder, final int position) {

        final HistoryFasilitas datas = data.get(position);
        holder.tv_namapemrakarsa.setText(datas.getNama_pemrakarsa());
        holder.tv_ukerpemrakarsa.setText(datas.getUker_pemrakarsa());
        holder.tv_idaplikasi.setText(String.valueOf(datas.getId_aplikasi()));
        holder.tv_produk.setText(datas.getTipe_produk());
        holder.tv_plafond.setText(String.format(AppUtil.parseRupiah(datas.getPlafond_induk())));
        holder.tv_tanggalentry.setText(AppUtil.parseTanggalGeneral(datas.getTanggal_entry(), "ddMMyyyy", "dd-MM-yyyy"));
        holder.tv_status.setText(datas.getStatus_aplikasi());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_namapemrakarsa, tv_ukerpemrakarsa, tv_idaplikasi, tv_produk, tv_plafond, tv_tanggalentry, tv_status;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            tv_namapemrakarsa = (TextView) itemView.findViewById(R.id.tv_namapemrakarsa);
            tv_ukerpemrakarsa = (TextView) itemView.findViewById(R.id.tv_ukerpemrakarsa);
            tv_idaplikasi = (TextView) itemView.findViewById(R.id.tv_idaplikasi);
            tv_produk = (TextView)itemView.findViewById(R.id.tv_produk);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tanggalentry = (TextView) itemView.findViewById(R.id.tv_tanggalentry);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }
}
