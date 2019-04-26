package com.application.bris.brisi.config.menu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.bris.brisi.R;
import com.application.bris.brisi.model.menu.Mahasiswa;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by PID on 4/25/2019.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder>{
    private ArrayList<Mahasiswa> dataList;

    public TestAdapter(ArrayList<Mahasiswa> siswa){
        this.dataList = siswa;
    }


    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.TestViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNpm.setText(dataList.get(position).getNpm());
        holder.txtNoHp.setText(dataList.get(position).getNohp());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtNpm, txtNoHp;

        public TestViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama_mahasiswa);
            txtNpm = (TextView) itemView.findViewById(R.id.txt_npm_mahasiswa);
            txtNoHp = (TextView) itemView.findViewById(R.id.txt_nohp_mahasiswa);
        }
    }
}
