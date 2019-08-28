package com.application.bris.brisi_pemutus.page_putusan.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.model.agunan_kendaraan.AgunanKendaraan;
import com.application.bris.brisi_pemutus.model.agunan_kios.AgunanKios;
import com.application.bris.brisi_pemutus.model.agunan_terikat.AgunanTerikat;
import com.application.bris.brisi_pemutus.page_aom.info_agunan.InfoAgunanActivity;
import com.application.bris.brisi_pemutus.page_putusan.agunan.ActivityInsertAgunan;
import com.application.bris.brisi_pemutus.page_putusan.agunan_deposito.AgunanDepositoActivity;
import com.application.bris.brisi_pemutus.page_putusan.agunan_kendaraan.AgunanKendaraanActivity;
import com.application.bris.brisi_pemutus.page_putusan.agunan_kios.AgunanKiosActivity;
import com.application.bris.brisi_pemutus.page_putusan.agunan_retry.ActivityAgunanRetry;
import com.application.bris.brisi_pemutus.page_putusan.agunan_tanah_kosong.ActivityAgunanTanahKosong;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

public class AgunanTerikatAdapater extends RecyclerView.Adapter<AgunanTerikatAdapater.PipelineViewHolder> implements Filterable {

    private Context context;
    private List<AgunanTerikat> data;
    private List<AgunanTerikat> datafiltered;


    public AgunanTerikatAdapater(Context context, List<AgunanTerikat> data) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;

    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_agunan_terikat, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PipelineViewHolder holder, final int position) {
        final AgunanTerikat datas = datafiltered.get(position);

        Log.d("idfoto ekipaol",datas.getId_foto());

        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.ico_img_for_upload);
        options.placeholder(R.mipmap.ico_img_for_upload);
//                                .centerCrop()
//                                .placeholder(R.mipmap.ico_img_for_upload)
//                                .diskCacheStrategy(DiskCacheStrategy.ALL);


        Glide.with(holder.tv_id_agunan.getContext())
                .asBitmap()
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+datas.getId_foto())
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        holder.iv_foto.setImageBitmap(resource);

                    }
                });

        //  holder.iv_foto.setImageResource(datas.getFoto());
        holder.tv_nama.setText(datas.getNAMA_DEBITUR_1());
        holder.tv_jenis.setText(datas.getDESC_AGUNAN());
        holder.tv_id_agunan.setText(datas.getFID_AGUNAN());
        holder.tv_id_aplikasi.setText(datas.getID_APLIKASI());
        holder.tv_plafond_induk.setText(AppUtil.parseRupiah(datas.getPLAFOND_INDUK()));
        holder.cv_agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(  holder.tv_jenis.getText().toString().equalsIgnoreCase("Tanah dan bangunan")){
                    Intent intent = new Intent(holder.cv_agunan.getContext(), ActivityAgunanRetry.class);
                    intent.putExtra("idAgunan",holder.tv_id_agunan.getText().toString());
                    intent.putExtra("idAplikasi",datas.getID_APLIKASI());
                    intent.putExtra("cif",datas.getFID_CIF_LAS());
                    holder.cv_agunan.getContext().startActivity(intent);
                }
                else  if(  holder.tv_jenis.getText().toString().equalsIgnoreCase("Tanah kosong")){
                    Intent intent2 = new Intent(holder.cv_agunan.getContext(), ActivityAgunanTanahKosong.class);
                    intent2.putExtra("idAgunan",holder.tv_id_agunan.getText().toString());
                    intent2.putExtra("idAplikasi",datas.getID_APLIKASI());
                    intent2.putExtra("cif",datas.getFID_CIF_LAS());
                    holder.cv_agunan.getContext().startActivity(intent2);
                }
                else  if(  holder.tv_jenis.getText().toString().equalsIgnoreCase("deposito")){
                    Intent intent2 = new Intent(holder.cv_agunan.getContext(), AgunanDepositoActivity.class);
                    intent2.putExtra("idAgunan",holder.tv_id_agunan.getText().toString());
                    intent2.putExtra("idAplikasi",datas.getID_APLIKASI());
                    intent2.putExtra("cif",datas.getFID_CIF_LAS());
                    holder.cv_agunan.getContext().startActivity(intent2);
                }
                else  if(  holder.tv_jenis.getText().toString().equalsIgnoreCase("Kios")){
                    Intent intent2 = new Intent(holder.cv_agunan.getContext(), AgunanKiosActivity.class);
                    intent2.putExtra("idAgunan",holder.tv_id_agunan.getText().toString());
                    intent2.putExtra("idAplikasi",datas.getID_APLIKASI());
                    intent2.putExtra("cif",datas.getFID_CIF_LAS());
                    holder.cv_agunan.getContext().startActivity(intent2);
                }
                else  if(  holder.tv_jenis.getText().toString().equalsIgnoreCase("kendaraan")){
                    Intent intent2 = new Intent(holder.cv_agunan.getContext(), AgunanKendaraanActivity.class);
                    intent2.putExtra("idAgunan",holder.tv_id_agunan.getText().toString());
                    intent2.putExtra("idAplikasi",datas.getID_APLIKASI());
                    intent2.putExtra("cif",datas.getFID_CIF_LAS());
                    holder.cv_agunan.getContext().startActivity(intent2);
                }





            }
        });
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
                    List<AgunanTerikat> filteredList = new ArrayList<>();
                    for (AgunanTerikat row : data){
                        if(row.getNAMA_DEBITUR_1().toLowerCase().contains(charString.toLowerCase()) || row.getDESC_AGUNAN().toLowerCase().contains(charString.toLowerCase())
                                || row.getFID_AGUNAN().toLowerCase().contains(charString.toLowerCase()) || row.getID_APLIKASI().toLowerCase().contains(charString.toLowerCase())
                                || row.getPLAFOND_INDUK().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<AgunanTerikat>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_foto;
        private CardView cv_agunan;
        private TextView tv_nama, tv_jenis, tv_id_agunan, tv_id_aplikasi, tv_plafond_induk;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            cv_agunan = (CardView) itemView.findViewById(R.id.cv_agunan);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_jenis = (TextView) itemView.findViewById(R.id.tv_jenis);
            tv_id_agunan = (TextView) itemView.findViewById(R.id.tv_id_agunan);
            tv_id_aplikasi = (TextView) itemView.findViewById(R.id.tv_id_aplikasi);
            tv_plafond_induk = (TextView) itemView.findViewById(R.id.tv_plafond_induk);
        }
    }
}