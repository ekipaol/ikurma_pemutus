package com.application.bris.brisi_pemutus.page_daftar_user.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.application.bris.brisi_pemutus.page_daftar_user.view.DetailUserActivity;

import java.util.ArrayList;
import java.util.List;

public class AdapterDaftarUser extends RecyclerView.Adapter<AdapterDaftarUser.UserViewHolder> implements Filterable {
    private Context context;
    private List<Ao> data;
    private List<Ao> datafiltered;

    public AdapterDaftarUser(Context context, List<Ao> data) {
        this.context = context;
        this.data = data;
        this.datafiltered=data;

    }



    @NonNull
    @Override
    public AdapterDaftarUser.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_daftar_user, parent, false);
        return new AdapterDaftarUser.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterDaftarUser.UserViewHolder holder, final int position) {
        final Ao datas = datafiltered.get(position);

//        Log.d("substringekipaol",datas.getSbdesc().substring(0,7));
//        if(datas.getJabatan().equalsIgnoreCase("uh")&&datas.getSbdesc().substring(0,7).equalsIgnoreCase("ums kcp")){
//            holder.cv_content.setVisibility(View.GONE);
//        }

        holder.tv_nama.setText(datas.getNama_pegawai());
        if(datas.getJabatan().equalsIgnoreCase("bo")){
            holder.tv_jabatan.setText(datas.getJabatan()+"S");
        }
        else{
            holder.tv_jabatan.setText(datas.getJabatan());
        }
        holder.tv_kantor.setText(datas.getSbdesc());
        holder.tv_status.setText(datas.getStatus());
        holder.tv_uid.setText("UID: "+datas.getUid());
        if (holder.tv_status.getText().toString().equalsIgnoreCase("tidak aktif")){
            holder.tv_status.setBackgroundResource(R.drawable.round3);
        }
        else if(holder.tv_status.getText().toString().equalsIgnoreCase("aktif")){
            holder.tv_status.setBackgroundResource(R.drawable.round2);
        }

        holder.btDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.btDetail.getContext(), DetailUserActivity.class);
                intent.putExtra("uid",datas.getUid());
                intent.putExtra("officer_code",datas.getFid_jhoof());
                intent.putExtra("nama",datas.getNama_pegawai());
                intent.putExtra("kantor",datas.getSbdesc());
                intent.putExtra("jabatan",datas.getJabatan());
                intent.putExtra("limit",datas.getLimit());
                intent.putExtra("status",datas.getStatus());
                intent.putExtra("lock",datas.getLock());
                intent.putExtra("username",datas.getUsername());
                intent.putExtra("nik",datas.getNik());
                intent.putExtra("sk",datas.getSk());
                holder.tv_jabatan.getContext().startActivity(intent);
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
                        if(row.getNama_pegawai().toLowerCase().contains(charString.toLowerCase()) || row.getJabatan().toLowerCase().contains(charString.toLowerCase())
                                || row.getSbdesc().toLowerCase().contains(charString.toLowerCase()) || row.getStatus().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<Ao>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_nama, tv_kantor, tv_status, tv_jabatan,tv_uid;
        private Button btDetail;
        CardView cv_content;

        public UserViewHolder(View itemView) {
            super(itemView);

            tv_nama =  itemView.findViewById(R.id.tv_namauser);
            tv_kantor =  itemView.findViewById(R.id.tv_kantoruser);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_jabatan =  itemView.findViewById(R.id.tv_jabatanuser);
            tv_uid=itemView.findViewById(R.id.tv_uid);
            btDetail=itemView.findViewById(R.id.btDetail);
            cv_content=itemView.findViewById(R.id.cv_content_user);

        }
    }
}
