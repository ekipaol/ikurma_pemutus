package com.application.bris.brisi_pemutus.adapter.eform;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.eform.Eform;
import com.application.bris.brisi_pemutus.util.AppUtil;

import java.util.List;

public class EformHomeAdapter extends RecyclerView.Adapter<EformHomeAdapter.EformViewHolder>{
    private Context context;
    private List<Eform> data;

    public EformHomeAdapter(Context context, List<Eform> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public EformHomeAdapter.EformViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_eform, parent, false);
        return new EformHomeAdapter.EformViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EformHomeAdapter.EformViewHolder holder, int position) {

        holder.iv_foto.setImageResource(data.get(position).getFoto());
        holder.tv_nama.setText(data.get(position).getNama());
        holder.tv_produk.setText(data.get(position).getProduk());
        holder.tv_plafond.setText(AppUtil.parseRupiah(data.get(position).getPlafond()));
        holder.tv_tenor.setText(data.get(position).getTenor()+" Bulan");
        holder.tv_waktu.setText(data.get(position).getWaktu());
        holder.tvKeteranganEform.setText(data.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class EformViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_foto;
        private TextView tv_nama, tv_produk, tv_plafond, tv_tenor, tv_waktu,tvKeteranganEform;

        public EformViewHolder(View itemView) {
            super(itemView);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            tv_nama = (TextView) itemView.findViewById(R.id.tvKeteranganEform);
            tv_produk = (TextView) itemView.findViewById(R.id.tv_produk);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tenor = (TextView) itemView.findViewById(R.id.tv_tenor);
            tv_waktu = (TextView) itemView.findViewById(R.id.tv_waktu);
            tvKeteranganEform = itemView.findViewById(R.id.tvKeteranganEform);
        }
    }
}
