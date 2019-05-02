package com.application.bris.brisi.page_aom.adapter.pipeline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.bris.brisi.R;
import com.application.bris.brisi.model.pipeline.pipeline;
import com.application.bris.brisi.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class PipelineAdapater extends RecyclerView.Adapter<PipelineAdapater.PipelineViewHolder> implements Filterable {

    private Context context;
    private List<pipeline> data;
    private List<pipeline> datafiltered;

    public PipelineAdapater(Context context, List<pipeline> data) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_pipeline, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PipelineViewHolder holder, final int position) {
        final pipeline datas = datafiltered.get(position);


        holder.iv_foto.setImageResource(datas.getFoto());
        holder.tv_nama.setText(datas.getNama());
        holder.tv_produk.setText(datas.getProduk());
        holder.tv_plafond.setText(AppUtil.parseRupiah(datas.getPlafond()));
        holder.tv_tenor.setText(datas.getTenor()+" Bulan");
        holder.tv_waktu.setText(datas.getWaktu());
    }

    @Override
    public int getItemCount() {
        return datafiltered.size();
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
                    List<pipeline> filteredList = new ArrayList<>();
                    for (pipeline row : data){
                        if(row.getNama().toLowerCase().contains(charString.toLowerCase()) || row.getProduk().toLowerCase().contains(charString.toLowerCase())
                            || row.getPlafond().toLowerCase().contains(charString.toLowerCase()) || row.getTenor().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<pipeline>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_foto;
        private TextView tv_nama, tv_produk, tv_plafond, tv_tenor, tv_waktu;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_produk = (TextView) itemView.findViewById(R.id.tv_produk);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tenor = (TextView) itemView.findViewById(R.id.tv_tenor);
            tv_waktu = (TextView) itemView.findViewById(R.id.tv_waktu);
        }
    }
}
