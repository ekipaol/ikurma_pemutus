package com.application.bris.brisi_pemutus.page_daftar_user.adapters;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.delete_aom.ReqDeleteAom;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.model.data_ao.Ao;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterReactivePassword extends RecyclerView.Adapter<AdapterReactivePassword.UserViewHolder> implements Filterable {
    private Context context;
    private List<Ao> data;
    private List<Ao> datafiltered;
    ApiClientAdapter apiClientAdapter;

    public AdapterReactivePassword(Context context, List<Ao> data) {
        this.context = context;
        this.data = data;
        this.datafiltered=data;

    }

    @NonNull
    @Override
    public AdapterReactivePassword.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_reactive_password, parent, false);
        apiClientAdapter=new ApiClientAdapter(view.getContext());
        return new AdapterReactivePassword.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterReactivePassword.UserViewHolder holder, final int position) {
        final Ao datas = datafiltered.get(position);
        //final User datas = data.get(position);



        holder.tv_nama.setText(datas.getNama_pegawai());
        if(datas.getJabatan().equalsIgnoreCase("bo")){
            holder.tv_jabatan.setText(datas.getJabatan()+"S");
        }
        else{
            holder.tv_jabatan.setText(datas.getJabatan());
        }
        holder.tv_kantor.setText(datas.getSbdesc());
        holder.tv_status.setText(datas.getStatus());
        holder.tv_lock.setText(datas.getLock());

        if (holder.tv_status.getText().toString().equalsIgnoreCase("tidak aktif")){
            holder.tv_status.setBackgroundResource(R.drawable.round3);
        }
        else if(holder.tv_status.getText().toString().equalsIgnoreCase("aktif")){
            holder.tv_status.setBackgroundResource(R.drawable.round2);
        }

        holder.ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv_jabatan.getContext());
//
//                builder.setTitle("Konfirmasi");
//
//                builder.setMessage("Anda akan melakukan reactive password untuk user "+datas.getJabatan()+" "+datas.getNama()+" ?");
//                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        //ubah status di database jika oke
//                        Toast.makeText(holder.tv_jabatan.getContext(), "Status password "+datas.getNama()+" diubah", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User cancelled the dialog
//                    }
//                });
//                builder.create();
//                builder.show();

//                new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
//                        .setTitleText("Konfirmasi")
//                        .setContentText("Anda akan melakukan reactive password untuk user "+datas.getJabatan()+" "+datas.getNama_pegawai()+" ?")
//                        .setConfirmText("Konfirmasi")
//                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//                                sDialog
//                                        .setTitleText("Berhasil")
//                                        .setContentText("Password berhasil direset")
//                                        .setConfirmText("OK")
//                                        .setConfirmClickListener(null)
//                                        .showCancelButton(false)
//                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                            }
//                        }).setCancelText("Kembali")
//                        .show();
//                notifyDataSetChanged();

                deaktivasiStatusAom(Integer.parseInt(datas.getUid()),datas.getNama_pegawai(),datas.getJabatan());
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
                                || row.getSbdesc().toLowerCase().contains(charString.toLowerCase())|| row.getLock().toLowerCase().contains(charString.toLowerCase()) || row.getStatus().toLowerCase().contains(charString.toLowerCase())){
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

        private TextView tv_nama, tv_kantor, tv_status, tv_jabatan,tv_lock;
        Button ubah;

        public UserViewHolder(View itemView) {
            super(itemView);
            tv_lock=itemView.findViewById(R.id.tv_status_kunci);
            tv_nama =  itemView.findViewById(R.id.tv_namauser);
            tv_kantor =  itemView.findViewById(R.id.tv_kantoruser);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_jabatan =  itemView.findViewById(R.id.tv_jabatanuser);
            ubah=itemView.findViewById(R.id.btUbahKode);


        }
    }

    public void deaktivasiStatusAom(final int uid, String namaPegawai,String jabatan) {
        final SweetAlertDialog dialogKonfirm = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        dialogKonfirm.setTitleText("Konfirmasi");
        dialogKonfirm.setContentText("Anda akan melakukan reactive password untuk user "+jabatan+" "+namaPegawai)
                .setConfirmText("Ya")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                        dialogKonfirm.setContentText("");
                        ReqDeleteAom req = new ReqDeleteAom();
                        req.setUid(uid);
                        Call<ParseResponse> call = apiClientAdapter.getApiInterface().reactivePasswordAom(req);
                        call.enqueue(new Callback<ParseResponse>() {
                            @Override
                            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                                if(response.isSuccessful()){
                                    if(response.body().getStatus().equalsIgnoreCase("00")){

                                        dialogKonfirm.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        dialogKonfirm.setTitleText("Berhasil");
                                        dialogKonfirm.setContentText("Password berhasil di reset");
                                        dialogKonfirm.setConfirmText("OK");
                                        dialogKonfirm.showCancelButton(false);
                                        dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                dialogKonfirm.dismissWithAnimation();
                                                ((Activity)context).recreate();
                                            }
                                        });

                                    }
                                    else{
                                        dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                        dialogKonfirm.setTitleText("Gagal");
                                        dialogKonfirm.setContentText(response.body().getMessage());
                                        dialogKonfirm.setConfirmText("Coba lagi");

                                    }
                                }

                            }


                            @Override
                            public void onFailure(Call<ParseResponse> call, Throwable t) {
                                Log.d("LOG D", t.getMessage());
                                dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialogKonfirm.setTitleText("Gagal");
                                dialogKonfirm.setContentText(t.getMessage());
                                dialogKonfirm.setCancelText("OK");
                            }
                        });

                    }
                }).setCancelText("Batal")
                .show();
    }
}
