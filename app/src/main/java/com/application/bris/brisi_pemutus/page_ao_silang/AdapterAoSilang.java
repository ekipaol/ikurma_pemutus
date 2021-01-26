package com.application.bris.brisi_pemutus.page_ao_silang;


import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.model.ao_silang.AoSilang;
import com.application.bris.brisi_pemutus.model.detailHotprospek.DetailHotprospek;
import com.application.bris.brisi_pemutus.model.detailHotprospek.DetailHotprospekKpr;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.model.super_data_front.AllDataFront;
import com.application.bris.brisi_pemutus.page_konsumer_kmg.front_menu.PutusanFrontMenuKmg;
import com.application.bris.brisi_pemutus.page_konsumer_kpr.PutusanFrontMenuKpr;
import com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen.ActivityFotoKelengkapanDokumen;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;
import java.util.List;


public class AdapterAoSilang extends RecyclerView.Adapter<AdapterAoSilang.UserViewHolder> implements Filterable {
    private Context context;
    private List<DetailHotprospekKpr> data;
    private List<DetailHotprospekKpr> datafiltered;
    private int random=0;
    private int random2=0;

    public AdapterAoSilang(Context context, List<DetailHotprospekKpr> data) {
        this.context = context;
        this.data = data;
        this.datafiltered=data;

    }



    @NonNull
    @Override
    public AdapterAoSilang.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_pembiayaan, parent, false);
        return new AdapterAoSilang.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterAoSilang.UserViewHolder holder, final int position) {
        final DetailHotprospekKpr datas = datafiltered.get(position);

        holder.tv_nama.setText(datas.getNama_debitur_1());
        holder.tv_produk.setText(datas.getNama_produk());
        holder.tv_plafond.setText(AppUtil.parseRupiah(String.valueOf(datas.getPlafond_induk())));
        holder.tv_tenor.setText(Integer.toString(datas.getJw()));
        holder.tv_waktu.setText("Tanggal Entry : " + AppUtil.parseTanggalGeneral(datas.getTanggal_entry(), "ddMMyyyy", "dd-MM-yyyy"));
        holder.tv_id_aplikasi.setText("ID Aplikasi : "+Integer.toString(datas.getId_aplikasi()));

        if(datas.getJw()==0){
            holder.tv_tenor.setText("Belum ada data tenor");
        }
        else{
            int tenorInt=datas.getJw();
            if(tenorInt>=13 && tenorInt%12>0){
                holder.tv_tenor.setText("Tenor : "+tenorInt/12+ " Tahun "+tenorInt%12+" Bulan");
            }
            else if(tenorInt>=13 && tenorInt%12==0){
                holder.tv_tenor.setText("Tenor "+tenorInt/12+ " Tahun");
            }
            else{
                holder.tv_tenor.setText("Tenor "+datas.getJw()+ " Bulan");
            }
        }

        AppUtil.loadPhotoWithCache(context,holder.iv_foto,Integer.toString(datas.getFid_photo()));

        holder.cv_ao_silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Putusan dataPutusan=new Putusan();
                dataPutusan.setId_aplikasi(Integer.toString(datas.getId_aplikasi()));
                dataPutusan.setCif_appel(Integer.toString(datas.getFid_cif_las()));
                dataPutusan.setFid_status(Integer.toString(datas.getId_st_aplikasi()));
                dataPutusan.setStatus_aplikasi(datas.getStatus_aplikasi());
                dataPutusan.setTujuan_penggunaan(datas.getNama_tujuan());
                dataPutusan.setTenor(Integer.toString(datas.getJw()));
                dataPutusan.setPlafond_induk(Long.toString(datas.getPlafond_induk()));
                dataPutusan.setFid_status(Integer.toString(datas.getId_st_aplikasi()));
                dataPutusan.setNama_nasabah(datas.getNama_debitur_1());
                dataPutusan.setNama_produk(datas.getNama_produk());
                dataPutusan.setTipe_produk(datas.getNama_produk());
//                dataPutusan.setKODE_GIMMICK(datas.getGIMMICK_ID());
                dataPutusan.setNAMA_GIMMICK(datas.getGIMMICK_ID());
                dataPutusan.setKODE_PRODUK(datas.getKode_produk());
                dataPutusan.setFid_photo(Integer.toString(datas.getFid_photo()));

                Intent intent=new Intent(holder.tv_nama.getContext(), PutusanFrontMenuKpr.class);
                intent.putExtra("data_putusan",dataPutusan);
                intent.putExtra("jenisPembiayaan","kpr");
                holder.tv_nama.getContext().startActivity(intent);
//                Toast.makeText(context, "Pura-pura ke halaman pilih AO", Toast.LENGTH_SHORT).show();;

            }
        });

        holder.bt_proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Putusan dataPutusan=new Putusan();
                dataPutusan.setId_aplikasi(Integer.toString(datas.getId_aplikasi()));
                dataPutusan.setCif_appel(Integer.toString(datas.getFid_cif_las()));
                dataPutusan.setFid_status(Integer.toString(datas.getId_st_aplikasi()));
                dataPutusan.setStatus_aplikasi(datas.getStatus_aplikasi());
                dataPutusan.setTujuan_penggunaan(datas.getNama_tujuan());
                dataPutusan.setTenor(Integer.toString(datas.getJw()));
                dataPutusan.setPlafond_induk(Long.toString(datas.getPlafond_induk()));
                dataPutusan.setNama_nasabah(datas.getNama_debitur_1());
                dataPutusan.setNama_produk(datas.getNama_produk());
//                dataPutusan.setKODE_GIMMICK(datas.getGIMMICK_ID());
                dataPutusan.setNAMA_GIMMICK(datas.getGIMMICK_ID());
                dataPutusan.setKODE_PRODUK(datas.getKode_produk());
                dataPutusan.setFid_photo(Integer.toString(datas.getFid_photo()));

                Intent intent=new Intent(holder.tv_nama.getContext(), PutusanFrontMenuKpr.class);
                intent.putExtra("data_putusan",dataPutusan);
                intent.putExtra("jenisPembiayaan","kpr");
                holder.tv_nama.getContext().startActivity(intent);
//                Toast.makeText(context, "Pura-pura ke halaman pilih AO", Toast.LENGTH_SHORT).show();;

            }
        });






//        holder.cv_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent=new Intent(holder.tv_nama_partner.getContext(), AoSilangEditActivity.class);
////                intent.putExtra("uid",datas.getUid());
////                holder.tv_nama_partner.getContext().startActivity(intent);
//                holder.easyFlipView.flipTheView();
//
//                Toast.makeText(context, "Clicking "+holder.tv_nama_ao.getText(), Toast.LENGTH_SHORT).show();;
//            }
//        });

//        holder.cv_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent=new Intent(holder.tv_nama_partner.getContext(), AoSilangEditActivity.class);
////                intent.putExtra("uid",datas.getUid());
////                holder.tv_nama_partner.getContext().startActivity(intent);
//                holder.easyFlipView.flipTheView();
//
////                Toast.makeText(context, "Clicking "+holder.tv_nama_ao.getText(), Toast.LENGTH_SHORT).show();;
//            }
//        });




//        holder.iv_foto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context, ActivityFotoKelengkapanDokumen.class);
//                intent.putExtra("id_foto",Integer.parseInt(datas.getNikAoSilang()));
//               context.startActivity(intent);
//            }
//        });





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
                    List<DetailHotprospekKpr> filteredList = new ArrayList<>();
                    for (DetailHotprospekKpr row : data){
                        if(row.getNama_debitur_1().toLowerCase().contains(charString.toLowerCase()) || String.valueOf(row.getId_aplikasi()).toLowerCase().contains(charString.toLowerCase())
                                || row.getNama_produk().toLowerCase().contains(charString.toLowerCase()) || String.valueOf(row.getJw()).toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<DetailHotprospekKpr>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView tv_nama_ao, tv_kantor, tv_nama_partner, tv_kantor_partner;
//        private ImageView iv_foto_ao, iv_foto_partner;
//        private EasyFlipView easyFlipView;

        private ImageView iv_foto;
        private Button bt_proses;
        private TextView tv_nama, tv_produk, tv_plafond, tv_tenor, tv_waktu,tv_id_aplikasi;

        CardView cv_item,cv_back,cv_ao_silang;

        public UserViewHolder(View itemView) {
            super(itemView);


//            iv_foto_ao=itemView.findViewById(R.id.iv_foto_ao_silang);
//            iv_foto_partner=itemView.findViewById(R.id.iv_foto_partner_ao_silang);
//            tv_nama_ao =  itemView.findViewById(R.id.tv_nama_ao_silang);
//            tv_kantor =  itemView.findViewById(R.id.tv_kantor_ao_silang);
//            tv_nama_partner = itemView.findViewById(R.id.tv_nama_partner_ao_silang);
//            tv_kantor_partner =  itemView.findViewById(R.id.tv_nama_kantor_partner_ao_silang);
//            cv_item=itemView.findViewById(R.id.cv_item_ao_silang);
//            cv_back=itemView.findViewById(R.id.cv_ao_silang_back);
//            easyFlipView = itemView.findViewById(R.id.v_easyflip);

            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            bt_proses=itemView.findViewById(R.id.btn_proses);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_produk = (TextView) itemView.findViewById(R.id.tv_produk);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tenor = (TextView) itemView.findViewById(R.id.tv_tenor);
            tv_waktu = (TextView) itemView.findViewById(R.id.tv_waktu);
            tv_id_aplikasi=itemView.findViewById(R.id.tv_id_aplikasi_item_pembiayaan);
            cv_ao_silang=itemView.findViewById(R.id.cv_item_pembiayaan);


        }
    }
}
