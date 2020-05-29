package com.application.bris.brisi_pemutus.page_putusan.detail_slik;


import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.detail_slik.DetailSlik;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityFotoKelengkapanDokumen;
import com.application.bris.brisi_pemutus.util.AppUtil;

import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class DetailSlikAdapter extends RecyclerView.Adapter<DetailSlikAdapter.PipelineViewHolder>{

    private Context context;
    private List<DetailSlik> data;

    public DetailSlikAdapter(Context context, List<DetailSlik> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_detail_slik, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PipelineViewHolder holder, final int position) {

        final DetailSlik datas = data.get(position);
        holder.tv_nama_bank.setText(datas.getNAMA_BANK());
        holder.tv_plafond.setText(AppUtil.parseRupiahDouble(Double.parseDouble(datas.getPLAFOND())));
        holder.tv_angsuran.setText((AppUtil.parseRupiahDouble(Double.parseDouble(datas.getANGSURAN()))));
        holder.tv_tanggal_mulai.setText(AppUtil.parseTanggalGeneral(datas.getTANGGAL_MULAI(),"yyyyMMdd","dd-MM-yyyy"));
        holder.tv_tanggal_jatuh_tempo.setText(AppUtil.parseTanggalGeneral(datas.getTANGGAL_JATUH_TEMPO(),"yyyyMMdd","dd-MM-yyyy"));
        holder.tv_kol.setText((datas.getKOL()));
        holder.tv_status.setText(datas.getKONDISI_KET());
        holder.tv_keterangan_remark.setText(datas.getREMARK_KET());

        if(datas.getREMARK().equalsIgnoreCase("1")){
            holder.tv_remark.setText("Lunas");
        }
        else if(datas.getREMARK().equalsIgnoreCase("2")){
            holder.tv_remark.setText("Top Up");
        }
        else if(datas.getREMARK().equalsIgnoreCase("3")){
            holder.tv_remark.setText("Take Over");
        }
        else if(datas.getREMARK().equalsIgnoreCase("0")){
            holder.tv_remark.setText("Sesuai Hasil Slik");
        }

        //kalau tidak ada foto, maka tidak ada tombol lihat foto
        if(datas.getFID_PHOTO().equalsIgnoreCase("0")){
            holder.bt_foto_remark.setVisibility(View.GONE);
        }

        holder.bt_foto_remark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",Integer.parseInt(datas.getFID_PHOTO()));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_nama_bank, tv_plafond, tv_angsuran, tv_tanggal_mulai, tv_tanggal_jatuh_tempo,tv_kol,tv_remark,tv_keterangan_remark, tv_status;
        private Button bt_foto_remark;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            tv_nama_bank = (TextView) itemView.findViewById(R.id.tv_namabank);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond_detail_slik);
            tv_angsuran = (TextView) itemView.findViewById(R.id.tv_angsuran_detail_slik);
            tv_tanggal_mulai = (TextView)itemView.findViewById(R.id.tv_tanggal_mulai_detail_slik);
            tv_tanggal_jatuh_tempo = (TextView) itemView.findViewById(R.id.tv_tanggal_jatuh_tempo_detail_slik);
            tv_kol = (TextView) itemView.findViewById(R.id.tv_kol_slik);
            tv_keterangan_remark = (TextView) itemView.findViewById(R.id.tv_keterangan_remark);
            tv_remark=(TextView) itemView.findViewById(R.id.tv_remark);
            tv_status=(TextView) itemView.findViewById(R.id.tv_status_detail_slik);
            bt_foto_remark=itemView.findViewById(R.id.bt_foto_bukti_remark);
        }
    }
}

