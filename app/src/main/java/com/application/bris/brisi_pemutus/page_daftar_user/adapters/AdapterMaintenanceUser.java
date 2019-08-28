package com.application.bris.brisi_pemutus.page_daftar_user.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.data_ao.Ao;
import com.application.bris.brisi_pemutus.model.user.User;
import com.application.bris.brisi_pemutus.page_daftar_user.view.ProsesMaintenanceUser;

import java.util.ArrayList;
import java.util.List;

public class AdapterMaintenanceUser extends RecyclerView.Adapter<AdapterMaintenanceUser.UserViewHolder> implements Filterable {
    private Context context;
    private List<Ao> data;
    private List<Ao> datafiltered;

    public AdapterMaintenanceUser(Context context, List<Ao> data) {
        this.context = context;
        this.data = data;
       this.datafiltered=data;



    }

    @NonNull
    @Override
    public AdapterMaintenanceUser.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_maintenance_user, parent, false);


        return new AdapterMaintenanceUser.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterMaintenanceUser.UserViewHolder holder, final int position) {
        final Ao datas = datafiltered.get(position);
        //final User datas = data.get(position);



        holder.tv_nama.setText(datas.getNama_pegawai());
        holder.tv_jabatan.setText(datas.getJabatan());
        holder.tv_kantor.setText(datas.getSbdesc());
        holder.tv_status.setText(datas.getStatus());
        holder.tv_pin.setText(datas.getFid_jhoof());

        holder.tv_pin.setText("Officer Code : "+datas.getFid_jhoof());
        if (holder.tv_status.getText().toString().equalsIgnoreCase("tidak aktif")){
            holder.tv_status.setBackgroundResource(R.drawable.round3);
        }
        else if(holder.tv_status.getText().toString().equalsIgnoreCase("aktif")){
            holder.tv_status.setBackgroundResource(R.drawable.round2);
        }

        holder.ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.tv_nama.getContext(), ProsesMaintenanceUser.class);
                intent.putExtra("nama",holder.tv_nama.getText().toString());
                intent.putExtra("jabatan",holder.tv_jabatan.getText().toString());
                intent.putExtra("kantor",holder.tv_kantor.getText().toString());
                intent.putExtra("officer_code",holder.tv_pin.getText().toString());
                intent.putExtra("uid",datas.getUid());

                holder.tv_kantor.getContext().startActivity(intent);
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
                    List<Ao> filteredList = new ArrayList<>();
                    for (Ao row : data){
                        if(row.getStatus().equalsIgnoreCase("aktif")){


                        if(row.getNama_pegawai().toLowerCase().contains(charString.toLowerCase()) || row.getJabatan().toLowerCase().contains(charString.toLowerCase())
                                || row.getSbdesc().toLowerCase().contains(charString.toLowerCase()) || row.getStatus().toLowerCase().contains(charString.toLowerCase() )){
                            filteredList.add(row);
                        }}
                    }
                    datafiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = datafiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                datafiltered = (ArrayList<Ao>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_nama, tv_kantor, tv_status, tv_jabatan,tv_uid,tv_pin;
        Button ubah;

        public UserViewHolder(View itemView) {
            super(itemView);

            tv_nama =  itemView.findViewById(R.id.tv_namauser);
            tv_kantor =  itemView.findViewById(R.id.tv_kantoruser);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_jabatan =  itemView.findViewById(R.id.tv_jabatanuser);
            ubah=itemView.findViewById(R.id.btUbahKode);
            tv_pin=itemView.findViewById(R.id.tv_kode_officer);

        }
    }
}
