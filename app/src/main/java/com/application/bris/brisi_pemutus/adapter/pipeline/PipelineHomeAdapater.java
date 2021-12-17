package com.application.bris.brisi_pemutus.adapter.pipeline;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by PID on 4/26/2019.
 */

public class PipelineHomeAdapater extends RecyclerView.Adapter<PipelineHomeAdapater.PipelineViewHolder> {

    private Context context;
    private List<Putusan> data;


    public PipelineHomeAdapater(Context context,List<Putusan> putusan) {
        this.context = context;
        this.data=putusan;
       ;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_pipeline_front, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PipelineViewHolder holder, final int position) {


//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .placeholder(R.drawable.banner_placeholder)
//                .error(R.drawable.banner_placeholder)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .priority(Priority.HIGH);
//
//        Glide.with(context)
//                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+data.get(position).getFid_photo())
//                .apply(options)
//                .into(holder.iv_foto);
        AppUtil.setImageGlide(context,data.get(position).getFid_photo(),holder.iv_foto);

        //set nama atau tipe produk
        if(data.get(position).getNama_produk()==null){
            holder.tv_produk.setText(data.get(position).getTipe_produk());
        }
        else{
            holder.tv_produk.setText(data.get(position).getNama_produk());
        }

        if(data.get(position).getKODE_PRODUK()==null){
            data.get(position).setKODE_PRODUK("0");
        }

        holder.tv_nama.setText(data.get(position).getNama_nasabah());
        holder.tv_id_aplikasi.setText("ID Aplikasi : "+data.get(position).getId_aplikasi());
        holder.tv_plafond.setText(AppUtil.parseRupiah(data.get(position).getPlafond_induk()));
        //tenor specialTreatment
        if(data.get(position).getTenor().equalsIgnoreCase("0")){
            holder.tv_tenor.setText("Belum ada data tenor");
        }
        else{
            int tenorInt=Integer.parseInt(data.get(position).getTenor());
            if(tenorInt>=13 && tenorInt%12>0){
                holder.tv_tenor.setText("Tenor : "+tenorInt/12+ " Tahun "+tenorInt%12+" Bulan");
            }
            else if(tenorInt>=13 && tenorInt%12==0){
                holder.tv_tenor.setText("Tenor "+tenorInt/12+ " Tahun");
            }
            else{
                holder.tv_tenor.setText("Tenor "+data.get(position).getTenor()+ " Bulan");
            }
        }
        try{
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.getDefault());
            String originDate = data.get(position).getTanggal_entry();
            SimpleDateFormat formatIn = new SimpleDateFormat("ddMMyyy");
            SimpleDateFormat formatOut = new SimpleDateFormat("dd MMM yyy");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatIn.parse(originDate));

            String newDate = formatOut.format(calendar.getTime());
            holder.tv_waktu.setText(newDate);

        }
        catch(Exception e){

        }

        holder.iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //check apakah dia purna prapurna (kmg/kmj mikro/konsumer) dan kmg embp (mikro/konsumer)

                //LOAN TYPE MIKRO
                //1	428		Multiguna Mikro EmBP
                //2	428		Multiguna Mikro non EmBP
                //3	316		Multijasa Mikro EmBP
                //4	317		KMJ Purna Faedah iB Mikro
                //5	321		KMJ Pra Purna EmBP iB Mikro
                //6	429		KMG Purna Faedah iB Mikro
                //7	430		KMG Pra Purna EmBP iB Mikro
                //8	321		KMJ Pra Purna Faedah iB Mikro
                //9	430		KMG Pra Purna Faedah iB Mikro



                if(data.get(position).getTipe_produk().equalsIgnoreCase("kmg")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("428")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("429")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("430")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("316")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("317")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("321")){
                    Intent intent=new Intent(context, PutusanFrontMenuKmg.class);
                    intent.putExtra("data_putusan",data.get(position));
                    intent.putExtra("jenisPembiayaan","kmg");
                    context.startActivity(intent);

                }
                else  if(data.get(position).getTipe_produk().equalsIgnoreCase("kpr")){
                    Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenuKpr.class);
                    intent.putExtra("data_putusan",data.get(position));
                    intent.putExtra("jenisPembiayaan","kpr");
                    holder.iv_foto.getContext().startActivity(intent);
                }
                else{
                    Intent intent = new Intent(holder.iv_foto.getContext(),PutusanFrontMenu.class);
                    intent.putExtra("data_putusan",data.get(position));
                    intent.putExtra("jenisPembiayaan","mikro");
                    holder.iv_foto.getContext().startActivity(intent);

                }
            }
        });

        //for testing only
//        holder.iv_foto.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Intent intent=new Intent(context, PutusanFrontMenuKmg.class);
//                intent.putExtra("data_putusan",data.get(position));
//                intent.putExtra("jenisPembiayaan","kmg");
//                context.startActivity(intent);
//                return true;
//            }
//        });


        holder.bt_proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(data.get(position).getTipe_produk().equalsIgnoreCase("kmg")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("428")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("429")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("430")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("316")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("317")||data.get(position).getKODE_PRODUK().equalsIgnoreCase("321")){
                    Intent intent=new Intent(context, PutusanFrontMenuKmg.class);
                    intent.putExtra("data_putusan",data.get(position));
                    intent.putExtra("jenisPembiayaan","kmg");
                    context.startActivity(intent);

                }
                else  if(data.get(position).getTipe_produk().equalsIgnoreCase("kpr")){
                    Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenuKpr.class);
                    intent.putExtra("data_putusan",data.get(position));
                    intent.putExtra("jenisPembiayaan","kpr");
                    holder.iv_foto.getContext().startActivity(intent);
                }
                else{
                    Intent intent = new Intent(holder.iv_foto.getContext(),PutusanFrontMenu.class);
                    intent.putExtra("data_putusan",data.get(position));
                    intent.putExtra("jenisPembiayaan","mikro");
                    holder.iv_foto.getContext().startActivity(intent);

                }

            }
        });
    }

    @Override
    public int getItemCount() {
       return data.size();



    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_foto;
        private Button bt_proses;
        private TextView tv_nama, tv_produk, tv_plafond, tv_tenor, tv_waktu,tv_id_aplikasi;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            bt_proses=itemView.findViewById(R.id.btn_proses);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_produk = (TextView) itemView.findViewById(R.id.tv_produk);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tenor = (TextView) itemView.findViewById(R.id.tv_tenor);
            tv_waktu = (TextView) itemView.findViewById(R.id.tv_waktu);
            tv_id_aplikasi=itemView.findViewById(R.id.tv_id_aplikasi_item_pembiayaan_front);

        }
    }
}
