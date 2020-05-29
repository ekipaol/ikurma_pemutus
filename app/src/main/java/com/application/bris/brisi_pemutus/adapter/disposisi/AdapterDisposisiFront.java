package com.application.bris.brisi_pemutus.adapter.disposisi;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.disposisi.Disposisi;
import com.application.bris.brisi_pemutus.page_disposisi.view.DetailDisposisiActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;

import java.util.List;


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

