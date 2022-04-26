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
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.disposisi.Disposisi;
import com.application.bris.brisi_pemutus.model.disposisi.DisposisiNew;
import com.application.bris.brisi_pemutus.page_disposisi.view.DetailDisposisiActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterDaftarDisposisi extends RecyclerView.Adapter<AdapterDaftarDisposisi.UserViewHolder> implements Filterable {
    private Context context;
    private List<DisposisiNew> data;
    private List<DisposisiNew> datafiltered;
    AppPreferences appPreferences;

    public AdapterDaftarDisposisi(Context context, List<DisposisiNew> data) {
        this.context = context;
        this.data = data;
        this.datafiltered=data;

    }

    @NonNull
    @Override
    public AdapterDaftarDisposisi.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_list_disposisi_v2, parent, false);
        return new AdapterDaftarDisposisi.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterDaftarDisposisi.UserViewHolder holder, final int position) {
        final DisposisiNew datas = datafiltered.get(position);
        final Intent intent=new Intent(holder.tv_nama.getContext(), DetailDisposisiActivity.class);
        intent.putExtra("id",datas.getId());



        Locale local=new Locale("in","id");
       // NumberFormat formatter = NumberFormat.getCurrencyInstance(local);
        holder.tv_nama.setText(datas.getNamaDebitur());
        holder.tv_produk.setText(datas.getJenisProdukDesc());
        holder.tv_plafon.setText("Plafon : "+ AppUtil.parseRupiah(datas.getPlafond()));


        //tenor specialTreatment
        if(datas.getTenor().equalsIgnoreCase("0")){
            holder.tv_tenor.setText("Belum ada data tenor");
        }
        else{
            int tenorInt=Integer.parseInt(datas.getTenor());
            if(tenorInt>=13 && tenorInt%12>0){
                holder.tv_tenor.setText("Tenor : "+tenorInt/12+ " Tahun "+tenorInt%12+" Bulan");
            }
            else if(tenorInt>=13 && tenorInt%12==0){
                holder.tv_tenor.setText("Tenor : "+tenorInt/12+ " Tahun");
            }
            else{
                holder.tv_tenor.setText("Tenor : "+datas.getTenor()+ " Bulan");
            }
        }

        if(datas.getDisposisiKepadaUserName()==null||datas.getDisposisiKepadaUserName().isEmpty()){
            holder.tv_ao.setText("Belum Disposisi");
//            holder.tv_tanggal_assigned.setText("Tanggal Pengajuan "+datas.getDisposisiTanggal().substring(0,16));
        }
        else{
            holder.tv_ao.setText(datas.getDisposisiKepadaUserName());

            //masi error
//            holder.tv_tanggal_assigned.setText("Tanggal Pengajuan "+AppUtil.parseTanggalGeneral(datas.getTANGGAL_ASSIGNED(),"yyyy-mm-dd hh:ii:ss","dd-mm-yyyy hh:ii"));

//            holder.tv_tanggal_assigned.setText("Tanggal Disposisi "+datas.getTANGGAL_ASSIGNED().substring(0,16));
        }

        holder.cv_disposisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.tv_tenor.getContext().startActivity(intent);
//                Toast.makeText(context, "nit not masuk detail disposisi", Toast.LENGTH_SHORT).show();
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
                    List<DisposisiNew> filteredList = new ArrayList<>();
                    for (DisposisiNew row : data){
                        if(row.getNamaDebitur().toLowerCase().contains(charString.toLowerCase()) || row.getJenisProdukDesc().toLowerCase().contains(charString.toLowerCase())
                                 || row.getTenor().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<DisposisiNew>) filterResults.values;
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
            tv_produk =  itemView.findViewById(R.id.tv_produk_header);
            tv_plafon = itemView.findViewById(R.id.tv_plafond);
            tv_tenor =  itemView.findViewById(R.id.tv_tenor);
            tv_ao=itemView.findViewById(R.id.tv_nama_pemrakarsa);
            cv_disposisi=itemView.findViewById(R.id.cv_disposisi);
//            tv_tanggal_assigned=itemView.findViewById(R.id.tv_waktu_assigned);



        }
    }
}
