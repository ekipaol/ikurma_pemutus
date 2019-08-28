package com.application.bris.brisi_pemutus.adapter.hotprospek;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.hotprospek.HotProspek;
import com.application.bris.brisi_pemutus.util.AppUtil;

import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class HotprospekHomeAdapater extends RecyclerView.Adapter<HotprospekHomeAdapater.PipelineViewHolder> {

    private Context context;
    private List<HotProspek> data;

    public HotprospekHomeAdapater(Context context, List<HotProspek> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_hotprospek_front, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PipelineViewHolder holder, int position) {
//        holder.iv_foto.setImageResource(data.get(position).getFoto());
//        holder.tv_nama.setText(data.get(position).getNama());
//        holder.tv_produk.setText(data.get(position).getProduk());
//        holder.tv_plafond.setText(AppUtil.parseRupiah(data.get(position).getPlafond()));
//        holder.tv_tenor.setText(data.get(position).getTenor()+" Bulan");
//        holder.tv_tujuanpenggunaan.setText(data.get(position).getTujuanpenggunaan());
//        holder.tv_status.setText(data.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_foto;
        private TextView tv_nama, tv_produk, tv_plafond, tv_tenor, tv_tujuanpenggunaan, tv_status;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_produk = (TextView) itemView.findViewById(R.id.tv_produk);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tenor = (TextView) itemView.findViewById(R.id.tv_tenor);
            tv_tujuanpenggunaan = (TextView) itemView.findViewById(R.id.tv_tujuanpenggunaan);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }
}
