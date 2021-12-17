package com.application.bris.brisi_pemutus.page_riwayat.adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
import com.application.bris.brisi_pemutus.model.putusan_akad.PutusanAkad;
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

public class AdapterAkad extends RecyclerView.Adapter<AdapterAkad.PipelineViewHolder> implements Filterable {

    private Context context;
    private List<PutusanAkad> data;
    private List<PutusanAkad> datafiltered;

    public AdapterAkad(Context context, List<PutusanAkad> data) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;
    }

    @NonNull
    @Override
    public AdapterAkad.PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_pemutus_akad, parent, false);
        return new AdapterAkad.PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterAkad.PipelineViewHolder holder, final int position) {
        final PutusanAkad datas = datafiltered.get(position);
//        Log.d("adapterAkad",datafiltered.get(position).getNO_AKAD());

        //  holder.iv_foto.setImageResource(datas.getFoto());
        holder.tv_nama.setText(datas.getNama_nasabah());
        holder.tv_id_aplikasi.setText("ID Aplikasi : "+datas.getId_aplikasi());
        holder.tv_plafond.setText(AppUtil.parseRupiah(datas.getPlafond_induk()));
        holder.tv_no_akad.setText(datas.getNO_AKAD());
        holder.tv_status_akad.setText(datas.getStatus_aplikasi());
        holder.tv_produk.setText(datas.getTipe_produk());

        //tenor specialTreatment
        if(datas.getJangka_waktu().equalsIgnoreCase("0")){
            holder.tv_tenor.setText("Belum ada data tenor");
        }
        else{
            int tenorInt=Integer.parseInt(datas.getJangka_waktu());
            if(tenorInt>=13 && tenorInt%12>0){
                holder.tv_tenor.setText("Tenor : "+tenorInt/12+ " Tahun "+tenorInt%12+" Bulan");
            }
            else if(tenorInt>=13 && tenorInt%12==0){
                holder.tv_tenor.setText("Tenor "+tenorInt/12+ " Tahun");
            }
            else{
                holder.tv_tenor.setText("Tenor "+datas.getJangka_waktu()+ " Bulan");
            }
        }




        //glide options for photo
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
        AppUtil.setImageGlide(context,data.get(position).getFid_photo(),holder.iv_foto);

        try{
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.getDefault());
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
                if(datas.getTipe_produk()!=null&&datas.getKODE_PRODUK()!=null){
                    if(datas.getTipe_produk().equalsIgnoreCase("KMG")||datas.getKODE_PRODUK().equalsIgnoreCase("428")||datas.getKODE_PRODUK().equalsIgnoreCase("429")||datas.getKODE_PRODUK().equalsIgnoreCase("430")){
                        //ngirim intent dengan keterangan ini, agar tidak ada tombol putusan di halaman detail
                        Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenuKmg.class);
                        intent.putExtra("data_putusan_akad",datas);
                        intent.putExtra("jenisPembiayaan","kmg");
                        holder.iv_foto.getContext().startActivity(intent);
                    }
                    //tetep bikin validasi kpr disini siapa tau nanti ditambahin kode produk waktu dahsboard kpr
                    else  if(datas.getTipe_produk().equalsIgnoreCase("kpr")){
                        Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenuKpr.class);
                        intent.putExtra("data_putusan_akad",datas);
                        intent.putExtra("jenisPembiayaan","kpr");
                        holder.iv_foto.getContext().startActivity(intent);
                    }
                    else{
                        //ngirim intent dengan keterangan ini, agar tidak ada tombol putusan di halaman detail
                        Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenu.class);
                        intent.putExtra("data_putusan_akad",datas);
                        intent.putExtra("jenisPembiayaan","mikro");
                        holder.iv_foto.getContext().startActivity(intent);
                    }

                    //kalo kpr, maka kode produknya dapet null, jadi bikin if sendiri
                }else{
                    if(datas.getTipe_produk().equalsIgnoreCase("kpr")){
                        Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenuKpr.class);
                        intent.putExtra("data_putusan_akad",datas);
                        intent.putExtra("jenisPembiayaan","kpr");
                        holder.iv_foto.getContext().startActivity(intent);
                    }

                }





            }
        });
        holder.bt_proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(datas.getTipe_produk()!=null&&datas.getKODE_PRODUK()!=null){
                    if(datas.getTipe_produk().equalsIgnoreCase("KMG")||datas.getKODE_PRODUK().equalsIgnoreCase("428")||datas.getKODE_PRODUK().equalsIgnoreCase("429")||datas.getKODE_PRODUK().equalsIgnoreCase("430")){
                        //ngirim intent dengan keterangan ini, agar tidak ada tombol putusan di halaman detail
                        Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenuKmg.class);
                        intent.putExtra("data_putusan_akad",datas);
                        intent.putExtra("jenisPembiayaan","kmg");
                        holder.iv_foto.getContext().startActivity(intent);
                    }
                    //tetep bikin validasi kpr disini siapa tau nanti ditambahin kode produk waktu dahsboared kpr
                    else  if(datas.getTipe_produk().equalsIgnoreCase("kpr")){
                        Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenuKpr.class);
                        intent.putExtra("data_putusan_akad",datas);
                        intent.putExtra("jenisPembiayaan","kpr");
                        holder.iv_foto.getContext().startActivity(intent);
                    }
                    else{
                        //ngirim intent dengan keterangan ini, agar tidak ada tombol putusan di halaman detail
                        Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenu.class);
                        intent.putExtra("data_putusan_akad",datas);
                        intent.putExtra("jenisPembiayaan","mikro");
                        holder.iv_foto.getContext().startActivity(intent);
                    }

                    //kalo kpr, maka kode produknya dapet null, jadi bikin if sendiri
                }else{
                    if(datas.getTipe_produk().equalsIgnoreCase("kpr")){
                        Intent intent = new Intent(holder.iv_foto.getContext(), PutusanFrontMenuKpr.class);
                        intent.putExtra("data_putusan_akad",datas);
                        intent.putExtra("jenisPembiayaan","kpr");
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
                    List<PutusanAkad> filteredList = new ArrayList<>();
                    for (PutusanAkad row : data){
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
                datafiltered = (ArrayList<PutusanAkad>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_foto;
        private Button bt_proses;
        private TextView tv_nama, tv_plafond, tv_tenor, tv_waktu,tv_id_aplikasi,tv_no_akad,tv_status_akad,tv_produk;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            bt_proses=itemView.findViewById(R.id.btn_proses);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tenor = (TextView) itemView.findViewById(R.id.tv_tenor);
            tv_waktu = (TextView) itemView.findViewById(R.id.tv_waktu);
            tv_id_aplikasi=itemView.findViewById(R.id.tv_id_aplikasi_akad);
            tv_no_akad=itemView.findViewById(R.id.tv_no_akad);
            tv_status_akad=itemView.findViewById(R.id.tv_status_akad);
            tv_produk=itemView.findViewById(R.id.tv_produk);
        }
    }
}
