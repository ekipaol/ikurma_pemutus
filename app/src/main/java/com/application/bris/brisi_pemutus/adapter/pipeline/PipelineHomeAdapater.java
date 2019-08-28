package com.application.bris.brisi_pemutus.adapter.pipeline;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.dashboard.RequestDashboard;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.agunan_deposito.AgunanDeposito;
import com.application.bris.brisi_pemutus.model.dashboard.DashboardCred;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.model.pipeline.pipeline;
import com.application.bris.brisi_pemutus.page_disposisi.view.DaftarDisposisiActivity;
import com.application.bris.brisi_pemutus.page_putusan.PutusanFrontMenu;
import com.application.bris.brisi_pemutus.page_putusan.agunan_deposito.AgunanDepositoActivity;
import com.application.bris.brisi_pemutus.page_putusan.agunan_kendaraan.AgunanKendaraanActivity;
import com.application.bris.brisi_pemutus.page_putusan.agunan_kios.AgunanKiosActivity;
import com.application.bris.brisi_pemutus.page_putusan.agunan_tanah_kosong.ActivityAgunanTanahKosong;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .error(R.drawable.banner_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(context)
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+data.get(position).getFid_photo())
                .apply(options)
                .into(holder.iv_foto);

        holder.tv_nama.setText(data.get(position).getNama_nasabah());
        holder.tv_produk.setText(data.get(position).getNama_produk());
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



                Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenu.class);
                intent.putExtra("data_putusan",data.get(position));
                holder.iv_foto.getContext().startActivity(intent);
            }
        });

        //for testing only
        holder.iv_foto.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent=new Intent(context, DaftarDisposisiActivity.class);
                context.startActivity(intent);
                return true;
            }
        });
        holder.bt_proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(holder.iv_foto.getContext(),PutusanFrontMenu.class);
                intent.putExtra("data_putusan",data.get(position));
                holder.iv_foto.getContext().startActivity(intent);
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
