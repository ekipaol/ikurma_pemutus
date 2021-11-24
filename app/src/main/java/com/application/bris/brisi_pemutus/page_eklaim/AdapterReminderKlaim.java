package com.application.bris.brisi_pemutus.page_eklaim;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.databinding.ItemReminderKlaimBinding;
import com.application.bris.brisi_pemutus.model.DataReminderKlaim;

import java.util.ArrayList;
import java.util.List;

public class AdapterReminderKlaim extends RecyclerView.Adapter<AdapterReminderKlaim.MenuViewHolder> implements Filterable {

    private List<DataReminderKlaim> data;
    private Context context;
    private ItemReminderKlaimBinding binding;
    private List<DataReminderKlaim> datafiltered;
    private AppPreferences appPreferences;

    public AdapterReminderKlaim(Context context, List<DataReminderKlaim> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = mdata;

        ((ReminderKlaimActivity)context).updateTotalData(Integer.toString(datafiltered.size()));
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemReminderKlaimBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        final DataReminderKlaim datas = datafiltered.get(position);

        holder.tvCabang.setText(datas.getUker());
        holder.tvNamaNasabah.setText(datas.getNamaNasabah());
        holder.tvArea.setText(datas.getArea());
        holder.tvProduk.setText(datas.getNamaProduk());
        holder.tvPenjaminan.setText(datas.getPenjaminan());
        holder.tvKol.setText(datas.getKol());
        holder.tvLd.setText(datas.getNoLd());

        onClicks(position, holder);


    }

    private void onClicks(final int currentPosition, @NonNull MenuViewHolder holder) {
//
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetilReminderKlaimActivity.class);
                intent.putExtra("data", data.get(currentPosition));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (datafiltered == null) {
            return 0;
        } else {
            return datafiltered.size();
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    datafiltered = data;
                } else {
                    List<DataReminderKlaim> filteredList = new ArrayList<>();
                    for (DataReminderKlaim row : data) {
                        if (row.getNamaNasabah().toLowerCase().contains(charString.toLowerCase())
                                || row.getUker().toLowerCase().contains(charString.toLowerCase())
                                || row.getArea().toLowerCase().contains(charString.toLowerCase())|| row.getNoLd().toLowerCase().contains(charString.toLowerCase())|| row.getPenjaminan().toLowerCase().contains(charString.toLowerCase())|| "kol ".concat(row.getKol()).toLowerCase().contains(charString.toLowerCase())) {
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
                datafiltered = (ArrayList<DataReminderKlaim>) filterResults.values;
                ((ReminderKlaimActivity)context).updateTotalData(Integer.toString(datafiltered.size()));
                notifyDataSetChanged();
            }
        };
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvCabang, tvNamaNasabah, tvKol, tvArea, tvProduk,tvLd,tvPenjaminan;
        Button btnDetail;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvCabang = binding.tvCabang;
            tvNamaNasabah = binding.tvNamaNasabah;
            tvKol = binding.tvKol;
            tvArea = binding.tvArea;
            tvProduk = binding.tvNamaProduk;
            tvLd = binding.tvNomorLd;
            tvPenjaminan = binding.tvPenjaminan;
            btnDetail = binding.btDetilReminderKlaim;
        }
    }
}