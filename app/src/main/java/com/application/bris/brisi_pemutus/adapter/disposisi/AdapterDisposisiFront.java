package com.application.bris.brisi_pemutus.adapter.disposisi;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.application.bris.brisi_pemutus.model.disposisi.Disposisi;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.model.pipeline.pipeline;
import com.application.bris.brisi_pemutus.page_disposisi.view.DaftarDisposisiActivity;
import com.application.bris.brisi_pemutus.page_disposisi.view.DetailDisposisiActivity;
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


public class AdapterDisposisiFront extends RecyclerView.Adapter<AdapterDisposisiFront.PipelineViewHolder> {

    private Context context;
    private List<Disposisi> data;


    public AdapterDisposisiFront(Context context,List<Disposisi> putusan) {
        this.context = context;
        this.data=putusan;
        ;
    }

    @NonNull
    @Override
    public AdapterDisposisiFront.PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_disposisi_front, parent, false);
        return new AdapterDisposisiFront.PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterDisposisiFront.PipelineViewHolder holder, final int position) {

        final Intent intent=new Intent(holder.tv_nama.getContext(), DetailDisposisiActivity.class);
        intent.putExtra("disposisi",data.get(position));
        if(data.get(position).getNAMA_ASSIGNED()!=null){
            intent.putExtra("menuAsal","riwayatDisposisi");
        }

        holder.tv_nama.setText(data.get(position).getNAMA_LENGKAP());
        holder.tv_produk.setText(data.get(position).getNAMA_PRODUK());

        holder.tv_plafond.setText(AppUtil.parseRupiah(data.get(position).getPLAFOND()));
        //tenor specialTreatment
        if(data.get(position).getJANGKA_WAKTU().equalsIgnoreCase("0")){
            holder.tv_tenor.setText("Belum ada data tenor");
        }
        else{
            int tenorInt=Integer.parseInt(data.get(position).getJANGKA_WAKTU());
            if(tenorInt>=13 && tenorInt%12>0){
                holder.tv_tenor.setText("Tenor : "+tenorInt/12+ " Tahun "+tenorInt%12+" Bulan");
            }
            else if(tenorInt>=13 && tenorInt%12==0){
                holder.tv_tenor.setText("Tenor "+tenorInt/12+ " Tahun");
            }
            else{
                holder.tv_tenor.setText("Tenor "+data.get(position).getJANGKA_WAKTU()+ " Bulan");
            }
        }
//        try{
//            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a", Locale.getDefault());
//            String originDate = data.get(position).getTGL_CREATED();
//            SimpleDateFormat formatIn = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
//            SimpleDateFormat formatOut = new SimpleDateFormat("dd-MMM-yyy hh:mm");
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(formatIn.parse(originDate));
//
//            String newDate = formatOut.format(calendar.getTime());
//        }
//        catch(Exception e){
//
//        }

        holder.tv_waktu.setText(data.get(position).getTGL_CREATED().substring(0,16));

        holder.rv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();



    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {

       private CardView rv_content;
        private TextView tv_nama, tv_produk, tv_plafond, tv_tenor, tv_waktu;

        public PipelineViewHolder(View itemView) {
            super(itemView);


            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama_disposisi_front);
            tv_produk = (TextView) itemView.findViewById(R.id.tv_produk_disposisi_front);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond_disposisi_front);
            tv_tenor = (TextView) itemView.findViewById(R.id.tv_tenor_disposisi_front);
            tv_waktu = (TextView) itemView.findViewById(R.id.tv_tanggal_pengajuan_front);
            rv_content=itemView.findViewById(R.id.cv_content_disposisi_front);




        }
    }
}

