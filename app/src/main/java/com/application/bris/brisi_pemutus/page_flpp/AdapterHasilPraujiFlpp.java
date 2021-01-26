package com.application.bris.brisi_pemutus.page_flpp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.flpp.HasilPraujiFlpp;
import com.application.bris.brisi_pemutus.page_monitoring.DetailMonitoringNasabahActivity;
import com.application.bris.brisi_pemutus.page_monitoring.MonitoringNasabahActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class AdapterHasilPraujiFlpp extends RecyclerView.Adapter<AdapterHasilPraujiFlpp.HotprospekViewHolder> implements Filterable {

    private Context context;
    private List<HasilPraujiFlpp> data;
    private List<HasilPraujiFlpp> datafiltered;

    public AdapterHasilPraujiFlpp(Context context, List<HasilPraujiFlpp> data) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;

    }

    @NonNull
    @Override
    public AdapterHasilPraujiFlpp.HotprospekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_prauji_flpp, parent, false);
        return new AdapterHasilPraujiFlpp.HotprospekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterHasilPraujiFlpp.HotprospekViewHolder holder, final int position) {
        final HasilPraujiFlpp datas = datafiltered.get(position);

        holder.tv_nama_nasabah.setText(datas.getNamaNasabah());
        holder.tv_plafond.setText(AppUtil.parseRupiah(datas.getNilaiKPR()));
        holder.tv_id_aplikasi.setText(datas.getIdAplikasi());
        holder.tv_jangka_waktu.setText(datas.getTenor()+ " Bulan");
        holder.tv_nama_pihak_ketiga.setText(datas.getNamaBadanHukum());
        holder.tv_keterangan.setText(datas.getKeterangan());
        holder.tv_status.setText(datas.getDESC1());



        holder.bt_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailHasilPraujiActivity.class);
                intent.putExtra("dataPrauji",datas);
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
                    List<HasilPraujiFlpp> filteredList = new ArrayList<>();
                    for (HasilPraujiFlpp row : data){
                        if(row.getNamaNasabah().toLowerCase().contains(charString.toLowerCase()) || row.getNamaBadanHukum().toLowerCase().contains(charString.toLowerCase()) || row.getDESC1().toLowerCase().contains(charString.toLowerCase())|| row.getNamaPerumahan().toLowerCase().contains(charString.toLowerCase())
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
                datafiltered = (ArrayList<HasilPraujiFlpp>) filterResults.values;


                notifyDataSetChanged();
            }
        };
    }

    public class HotprospekViewHolder extends RecyclerView.ViewHolder {
        private Button bt_detail;
        private TextView tv_nama_nasabah, tv_id_aplikasi, tv_plafond, tv_jangka_waktu, tv_nama_pihak_ketiga,tv_keterangan,tv_status;
        public HotprospekViewHolder(View itemView) {
            super(itemView);
            tv_nama_nasabah = (TextView) itemView.findViewById(R.id.tv_nama_nasabah);
            tv_id_aplikasi = (TextView) itemView.findViewById(R.id.tv_id_aplikasi);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_jangka_waktu = (TextView) itemView.findViewById(R.id.tv_jangka_waktu);
            tv_nama_pihak_ketiga = (TextView) itemView.findViewById(R.id.tv_nama_pihak_ketiga);
            tv_keterangan = (TextView) itemView.findViewById(R.id.tv_keterangan);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            bt_detail = (Button) itemView.findViewById(R.id.bt_detail_prauji_flpp);

        }
    }
}
