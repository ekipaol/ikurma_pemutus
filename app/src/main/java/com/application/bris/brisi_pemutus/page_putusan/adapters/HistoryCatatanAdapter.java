package com.application.bris.brisi_pemutus.page_putusan.adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.history_catatan.HistoryCatatan;

import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by PID on 4/26/2019.
 */

public class HistoryCatatanAdapter extends RecyclerView.Adapter<HistoryCatatanAdapter.PipelineViewHolder>{

    private Context context;
    private List<HistoryCatatan> data;
    AppPreferences appPreferences;

    public HistoryCatatanAdapter(Context context, List<HistoryCatatan> data) {
        this.context = context;
        this.data = data;
        appPreferences=new AppPreferences(context);
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_historycatatan, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PipelineViewHolder holder, final int position) {


        final HistoryCatatan datas = data.get(position);
        holder.tv_jabatan.setText(datas.getJabatan());
        holder.tv_nama.setText(datas.getNama_pemutus());
        holder.tv_catatan.setText((datas.getCatatan_pemutus()));
        holder.tv_jenis_putusan.setText(datas.getJenis_putusan());
        holder.tv_putusan.setText((datas.getPutusan_pemutus()));

        holder.btCopyCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("history_catatan", holder.tv_catatan.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toasty.success(context, "Catatan telah disalin", Toast.LENGTH_SHORT).show();

            }
        });

        //tutorial overlay

//        AppUtil.tutorialOverlay((Activity)holder.btCopyCatatan.getContext(),holder.cv_catatan,"Sekarang pemutus bisa klik tombol salin untuk menyalin catatan putusan","tutorial_salin_catatan_tutor");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_jabatan, tv_nama, tv_catatan, tv_jenis_putusan, tv_putusan;
        Button btCopyCatatan;
        CardView cv_catatan;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            tv_jabatan = (TextView) itemView.findViewById(R.id.tv_jabatan_pemutus);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama_pemutus);
            tv_catatan = (TextView) itemView.findViewById(R.id.tv_catatan_pemutus);
            tv_jenis_putusan = (TextView)itemView.findViewById(R.id.tv_jenis_putusan);
            tv_putusan = (TextView) itemView.findViewById(R.id.tv_putusan_pemutus);
            btCopyCatatan=itemView.findViewById(R.id.bt_salin_history_catatan);
            cv_catatan=(CardView) itemView.findViewById(R.id.cv_catatan_history);

        }
    }
}

