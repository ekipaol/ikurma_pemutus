package com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.model.kelengkapan_dokumen_agunan.KelengkapanDokumenAgunan;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.page_putusan.PutusanFrontMenu;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterKelengkapanDokumenAgunan extends RecyclerView.Adapter<AdapterKelengkapanDokumenAgunan.PipelineViewHolder>  {

    private Context context;
    private List<KelengkapanDokumenAgunan> data;


    public AdapterKelengkapanDokumenAgunan(Context context, List<KelengkapanDokumenAgunan> data) {
        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public AdapterKelengkapanDokumenAgunan.PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_kelengkapan_dokumen_agunan, parent, false);
        return new AdapterKelengkapanDokumenAgunan.PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterKelengkapanDokumenAgunan.PipelineViewHolder holder, final int position) {
        final KelengkapanDokumenAgunan datas = data.get(position);

        holder.tv_agunan_kelengkapan.setText(datas.getKATEGORI());

        holder.bt_agunan_kelengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.bt_agunan_kelengkapan.getContext(),ActivityPreviewFotoSecondary.class);
                intent.putExtra("id_foto",Integer.parseInt(datas.getID()));
                holder.bt_agunan_kelengkapan.getContext().startActivity(intent);
            }
        });


//        //  holder.iv_foto.setImageResource(datas.getFoto());
//        holder.tv_nama.setText(datas.getNama_nasabah());
//        holder.tv_produk.setText(datas.getTipe_produk());
//        holder.tv_id_aplikasi.setText("ID Aplikasi : "+data.get(position).getId_aplikasi());
//        holder.tv_plafond.setText(AppUtil.parseRupiah(datas.getPlafond_induk()));
//
//        //tenor specialTreatment
//        if(datas.getTenor().equalsIgnoreCase("0")){
//            holder.tv_tenor.setText("Belum ada data tenor");
//        }
//        else{
//            int tenorInt=Integer.parseInt(datas.getTenor());
//            if(tenorInt>=13 && tenorInt%12>0){
//                holder.tv_tenor.setText("Tenor : "+tenorInt/12+ " Tahun "+tenorInt%12+" Bulan");
//            }
//            else if(tenorInt>=13 && tenorInt%12==0){
//                holder.tv_tenor.setText("Tenor "+tenorInt/12+ " Tahun");
//            }
//            else{
//                holder.tv_tenor.setText("Tenor "+datas.getTenor()+ " Bulan");
//            }
//        }


//        //glide options for photo
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .placeholder(R.drawable.banner_placeholder)
//                .error(R.drawable.banner_placeholder)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .priority(Priority.HIGH);
//
//
//        //hidupkan kembali glide jika sudah ada fid photo di listputusan
//        Glide.with(context)
//                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+datas.getFid_photo())
//                .apply(options)
//                .into(holder.iv_foto);

//        try{
//            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.getDefault());
//            String originDate = datas.getTanggal_entry();
//            SimpleDateFormat formatIn = new SimpleDateFormat("ddMMyyy");
//            SimpleDateFormat formatOut = new SimpleDateFormat("dd MMM yyy");
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(formatIn.parse(originDate));
//
//            String newDate = formatOut.format(calendar.getTime());
//            holder.tv_waktu.setText(newDate);
//
//        }
//        catch(Exception e){
//
//        }






//on click listeners
//        holder.iv_foto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//                Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenu.class);
//                intent.putExtra("data_putusan",datas);
//                holder.iv_foto.getContext().startActivity(intent);
//            }
//        });
//        holder.bt_proses.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//                Intent intent = new Intent(holder.iv_foto.getContext(),PutusanFrontMenu.class);
//                intent.putExtra("data_putusan",datas);
//                holder.iv_foto.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_agunan_kelengkapan;
        private Button bt_agunan_kelengkapan;
        private TextView tv_agunan_kelengkapan;

        public PipelineViewHolder(View itemView) {
            super(itemView);
           tv_agunan_kelengkapan=itemView.findViewById(R.id.tv_agunan_kelengkapan);
           bt_agunan_kelengkapan=itemView.findViewById(R.id.bt_agunan_kelengkapan);
           cv_agunan_kelengkapan=itemView.findViewById(R.id.cv_agunan_kelengkapan);
        }
    }
}