package com.application.bris.brisi_pemutus.page_aom.adapter.pipeline;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.front_menu.PutusanFrontMenuKmg;
import com.application.bris.brisi_pemutus.page_konsumer_kpr.PutusanFrontMenuKpr;
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

import es.dmoral.toasty.Toasty;

/**
 * Created by PID on 4/26/2019.
 */

public class PutusanAdapter extends RecyclerView.Adapter<PutusanAdapter.PipelineViewHolder> implements Filterable {

    private Context context;
    private List<Putusan> data;
    private List<Putusan> datafiltered;

    public PutusanAdapter(Context context, List<Putusan> data) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_pembiayaan, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PipelineViewHolder holder, final int position) {
        final Putusan datas = datafiltered.get(position);


      //  holder.iv_foto.setImageResource(datas.getFoto());
        holder.tv_nama.setText(datas.getNama_nasabah());
        holder.tv_produk.setText(datas.getNama_produk());
        holder.tv_id_aplikasi.setText("ID Aplikasi : "+data.get(position).getId_aplikasi());
        holder.tv_plafond.setText(AppUtil.parseRupiah(datas.getPlafond_induk()));

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
                holder.tv_tenor.setText("Tenor "+tenorInt/12+ " Tahun");
            }
            else{
                holder.tv_tenor.setText("Tenor "+datas.getTenor()+ " Bulan");
            }
        }


        //glide options for photo
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .error(R.drawable.banner_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);


        //hidupkan kembali glide jika sudah ada fid photo di listputusan
        Glide.with(context)
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+datas.getFid_photo())
                .apply(options)
                .into(holder.iv_foto);

        try{
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a",Locale.getDefault());
            String originDate = datas.getTanggal_entry();
            SimpleDateFormat formatIn = new SimpleDateFormat("ddMMyyy");
            SimpleDateFormat formatOut = new SimpleDateFormat("dd MMM yyy");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatIn.parse(originDate));

            String newDate = formatOut.format(calendar.getTime());
            holder.tv_waktu.setText(newDate);

        }
         catch(Exception e){

         }






//on click listeners
        holder.iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(datas.getTenor().equalsIgnoreCase("0")){
                    Toasty.info(context,"Aplikasi ini diinput melalui APPEL, silahkan diputus melalui APPEL juga").show();
                }
                else{

                    if(data.get(position).getTipe_produk().equalsIgnoreCase("kmg")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("428")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("429")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("430")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("316")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("317")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("321")){
                        Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenuKmg.class);
                        intent.putExtra("data_putusan",datas);
                        intent.putExtra("jenisPembiayaan","kmg");
                        holder.iv_foto.getContext().startActivity(intent);
                    }
                    else  if(datas.getTipe_produk().equalsIgnoreCase("kpr")){
                        Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenuKpr.class);
                        intent.putExtra("data_putusan",datas);
                        intent.putExtra("jenisPembiayaan","kpr");
                        holder.iv_foto.getContext().startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(holder.iv_foto.getContext(),PutusanFrontMenu.class);
                        intent.putExtra("data_putusan",datas);
                        intent.putExtra("jenisPembiayaan","mikro");
//                Log.d("datasdeviasi",datas.getFid_photo());
                        holder.iv_foto.getContext().startActivity(intent);
                    }
                }




            }
        });
        holder.bt_proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Log.d("tipeproduk",datas.getTipe_produk());

                if(datas.getTenor().equalsIgnoreCase("0")){
                    Toasty.info(context,"Aplikasi ini diinput melalui APPEL, silahkan diputus melalui APPEL juga").show();
                }
                else{
                    if(data.get(position).getTipe_produk().equalsIgnoreCase("kmg")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("428")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("429")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("430")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("316")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("317")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("321")){
                        Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenuKmg.class);
                        intent.putExtra("data_putusan",datas);
                        intent.putExtra("jenisPembiayaan","kmg");
                        holder.iv_foto.getContext().startActivity(intent);
                    }
                    else  if(datas.getTipe_produk().equalsIgnoreCase("kpr")){
                        Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenuKpr.class);
                        intent.putExtra("data_putusan",datas);
                        intent.putExtra("jenisPembiayaan","kpr");
                        holder.iv_foto.getContext().startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(holder.iv_foto.getContext(),PutusanFrontMenu.class);
                        intent.putExtra("data_putusan",datas);
//                Log.d("datasdeviasi",datas.getFid_photo());
                        holder.iv_foto.getContext().startActivity(intent);
                    }
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
                    List<Putusan> filteredList = new ArrayList<>();
                    for (Putusan row : data){
                        if(row.getNama_nasabah().toLowerCase().contains(charString.toLowerCase()) || row.getTipe_produk().toLowerCase().contains(charString.toLowerCase())
                                || row.getPlafond_induk().toLowerCase().contains(charString.toLowerCase()) || row.getTanggal_entry().toLowerCase().contains(charString.toLowerCase()) || row.getId_aplikasi().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<Putusan>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_foto;
        private Button bt_proses;
        private TextView tv_nama, tv_produk, tv_plafond, tv_tenor, tv_waktu,tv_id_aplikasi;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            bt_proses=itemView.findViewById(R.id.btn_proses);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_produk = (TextView) itemView.findViewById(R.id.tv_produk);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tenor = (TextView) itemView.findViewById(R.id.tv_tenor);
            tv_waktu = (TextView) itemView.findViewById(R.id.tv_waktu);
            tv_id_aplikasi=itemView.findViewById(R.id.tv_id_aplikasi_item_pembiayaan);
        }
    }
}
