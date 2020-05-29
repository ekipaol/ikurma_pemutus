package com.application.bris.brisi_pemutus.page_ao_silang;


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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.ao_silang.AoSilang;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityFotoKelengkapanDokumen;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;
import java.util.List;


public class AdapterAoSilang extends RecyclerView.Adapter<AdapterAoSilang.UserViewHolder> implements Filterable {
    private Context context;
    private List<AoSilang> data;
    private List<AoSilang> datafiltered;
    private int random=0;
    private int random2=0;

    public AdapterAoSilang(Context context, List<AoSilang> data) {
        this.context = context;
        this.data = data;
        this.datafiltered=data;

    }



    @NonNull
    @Override
    public AdapterAoSilang.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_ao_silang, parent, false);
        return new AdapterAoSilang.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterAoSilang.UserViewHolder holder, final int position) {
        final AoSilang datas = datafiltered.get(position);

        holder.tv_nama_ao.setText(datas.getNamaAoSilang());
        holder.tv_kantor.setText(datas.getNamaKantorAoSilang());
        holder.tv_nama_partner.setText(datas.getNamaPartnerAoSilang());
        holder.tv_kantor_partner.setText(datas.getNamaKantorPartnerAoSilang());


        holder.cv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(holder.tv_nama_partner.getContext(), AoSilangEditActivity.class);
//                intent.putExtra("uid",datas.getUid());
//                holder.tv_nama_partner.getContext().startActivity(intent);
                holder.easyFlipView.flipTheView();

                Toast.makeText(context, "Clicking "+holder.tv_nama_ao.getText(), Toast.LENGTH_SHORT).show();;
            }
        });

        holder.cv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(holder.tv_nama_partner.getContext(), AoSilangEditActivity.class);
//                intent.putExtra("uid",datas.getUid());
//                holder.tv_nama_partner.getContext().startActivity(intent);
                holder.easyFlipView.flipTheView();

//                Toast.makeText(context, "Clicking "+holder.tv_nama_ao.getText(), Toast.LENGTH_SHORT).show();;
            }
        });


        AppUtil.loadPhotoWithCache(context,holder.iv_foto_ao,datas.getNikAoSilang());
        AppUtil.loadPhotoWithCache(context,holder.iv_foto_partner,datas.getNikPartnerAoSilang());

        holder.iv_foto_ao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",Integer.parseInt(datas.getNikAoSilang()));
               context.startActivity(intent);
            }
        });

        holder.iv_foto_partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ActivityFotoKelengkapanDokumen.class);
                intent.putExtra("id_foto",Integer.parseInt(datas.getNikPartnerAoSilang()));
                context.startActivity(intent);
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
                    List<AoSilang> filteredList = new ArrayList<>();
                    for (AoSilang row : data){
                        if(row.getNamaAoSilang().toLowerCase().contains(charString.toLowerCase()) || row.getNamaKantorAoSilang().toLowerCase().contains(charString.toLowerCase())
                                || row.getNamaPartnerAoSilang().toLowerCase().contains(charString.toLowerCase()) || row.getNamaKantorPartnerAoSilang().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<AoSilang>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_nama_ao, tv_kantor, tv_nama_partner, tv_kantor_partner;
        private ImageView iv_foto_ao, iv_foto_partner;
        private EasyFlipView easyFlipView;
        CardView cv_item,cv_back;

        public UserViewHolder(View itemView) {
            super(itemView);


            iv_foto_ao=itemView.findViewById(R.id.iv_foto_ao_silang);
            iv_foto_partner=itemView.findViewById(R.id.iv_foto_partner_ao_silang);
            tv_nama_ao =  itemView.findViewById(R.id.tv_nama_ao_silang);
            tv_kantor =  itemView.findViewById(R.id.tv_kantor_ao_silang);
            tv_nama_partner = itemView.findViewById(R.id.tv_nama_partner_ao_silang);
            tv_kantor_partner =  itemView.findViewById(R.id.tv_nama_kantor_partner_ao_silang);
            cv_item=itemView.findViewById(R.id.cv_item_ao_silang);
            cv_back=itemView.findViewById(R.id.cv_ao_silang_back);
            easyFlipView = itemView.findViewById(R.id.v_easyflip);


        }
    }
}
