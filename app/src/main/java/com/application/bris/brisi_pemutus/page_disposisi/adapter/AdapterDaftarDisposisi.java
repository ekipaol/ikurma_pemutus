package com.application.bris.brisi_pemutus.page_disposisi.adapter;


import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.disposisi.Disposisi;
import com.application.bris.brisi_pemutus.page_disposisi.view.DetailDisposisiActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterDaftarDisposisi extends RecyclerView.Adapter<AdapterDaftarDisposisi.UserViewHolder> implements Filterable {
    private Context context;
    private List<Disposisi> data;
    private List<Disposisi> datafiltered;

    public AdapterDaftarDisposisi(Context context, List<Disposisi> data) {
        this.context = context;
        this.data = data;
        this.datafiltered=data;

    }

    @NonNull
    @Override
    public AdapterDaftarDisposisi.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_daftar_disposisi, parent, false);
        return new AdapterDaftarDisposisi.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterDaftarDisposisi.UserViewHolder holder, final int position) {
        final Disposisi datas = datafiltered.get(position);
        final Intent intent=new Intent(holder.tv_nama.getContext(), DetailDisposisiActivity.class);
        intent.putExtra("disposisi",datas);
        if(datas.getNAMA_ASSIGNED()!=null){
            intent.putExtra("menuAsal","riwayatDisposisi");
        }
        //final User datas = data.get(position);


        Locale local=new Locale("in","id");
       // NumberFormat formatter = NumberFormat.getCurrencyInstance(local);
        holder.tv_nama.setText(datas.getNAMA_LENGKAP());
        holder.tv_produk.setText(datas.getNAMA_PRODUK());
        holder.tv_plafon.setText("Plafon : "+ AppUtil.parseRupiah(datas.getPLAFOND()));


        //tenor specialTreatment
        if(datas.getJANGKA_WAKTU().equalsIgnoreCase("0")){
            holder.tv_tenor.setText("Belum ada data tenor");
        }
        else{
            int tenorInt=Integer.parseInt(datas.getJANGKA_WAKTU());
            if(tenorInt>=13 && tenorInt%12>0){
                holder.tv_tenor.setText("Tenor : "+tenorInt/12+ " Tahun "+tenorInt%12+" Bulan");
            }
            else if(tenorInt>=13 && tenorInt%12==0){
                holder.tv_tenor.setText("Tenor "+tenorInt/12+ " Tahun");
            }
            else{
                holder.tv_tenor.setText("Tenor "+datas.getJANGKA_WAKTU()+ " Bulan");
            }
        }

        if(datas.getNAMA_ASSIGNED()==null){
            holder.tv_ao.setText("Belum ada AO");
            holder.tv_tanggal_assigned.setText("Tanggal Pengajuan "+datas.getTGL_CREATED().substring(0,16));
        }
        else{
            holder.tv_ao.setText(datas.getNAMA_ASSIGNED());

            //masi error
//            holder.tv_tanggal_assigned.setText("Tanggal Pengajuan "+AppUtil.parseTanggalGeneral(datas.getTANGGAL_ASSIGNED(),"yyyy-mm-dd hh:ii:ss","dd-mm-yyyy hh:ii"));

            holder.tv_tanggal_assigned.setText("Tanggal Disposisi "+datas.getTANGGAL_ASSIGNED().substring(0,16));
        }

        holder.cv_disposisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.tv_tenor.getContext().startActivity(intent);
            }
        });





    }



    @Override
    public int getItemCount() {
        //return data.size();
//        tambah jika sudah ada fitur search
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
                    List<Disposisi> filteredList = new ArrayList<>();
                    for (Disposisi row : data){
                        if(row.getNAMA_LENGKAP().toLowerCase().contains(charString.toLowerCase()) || row.getNAMA_PRODUK().toLowerCase().contains(charString.toLowerCase())
                                 || row.getJANGKA_WAKTU().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<Disposisi>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_nama, tv_produk, tv_plafon, tv_tenor,tv_ao,tv_tanggal_assigned;
        CardView cv_disposisi;


        public UserViewHolder(View itemView) {
            super(itemView);

            tv_nama =  itemView.findViewById(R.id.tv_nama);
            tv_produk =  itemView.findViewById(R.id.tv_produk);
            tv_plafon = itemView.findViewById(R.id.tv_plafon);
            tv_tenor =  itemView.findViewById(R.id.tv_tenor);
            tv_ao=itemView.findViewById(R.id.tv_pemrakarsa);
            cv_disposisi=itemView.findViewById(R.id.cv_disposisi);
            tv_tanggal_assigned=itemView.findViewById(R.id.tv_waktu_assigned);



        }
    }
}
