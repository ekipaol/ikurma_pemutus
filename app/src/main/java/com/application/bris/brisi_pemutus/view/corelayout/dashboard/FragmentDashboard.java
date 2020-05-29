package com.application.bris.brisi_pemutus.view.corelayout.dashboard;


import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import me.grantland.widget.AutofitTextView;


public class FragmentDashboard extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView jabatan,kantor,username,nik;
    AutofitTextView nama;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View mview;



    public FragmentDashboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PutusanTab1KPR.
     */
    // TODO: Rename and change types and number of parameters
    public static com.application.bris.brisi_pemutus.view.corelayout.profile.FragmentProfile newInstance(String param1, String param2) {
        com.application.bris.brisi_pemutus.view.corelayout.profile.FragmentProfile fragment = new com.application.bris.brisi_pemutus.view.corelayout.profile.FragmentProfile();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_dashboard_front,container,false);
        mview=v;

        HorizontalBarChart chartpipeline=mview.findViewById(R.id.chart_bar_pipeline);

        HorizontalBarChart charthotprospek=mview.findViewById(R.id.chart_bar_hotprospek);

        PieChart chartpencairan=mview.findViewById(R.id.chart_pie_pencairan);

        //buat objek entry untuk memasukkan data grafik -- data pipeline
        List<BarEntry> entries = new ArrayList<>();

        //sumbu X menentukan posisi, sumbu Y isi data, keterangan masing masing data ditambahkan di value formatter dibawah -- data pipeline
        entries.add(new BarEntry(0, 5));

        entries.add(new BarEntry(1, 13));

        entries.add(new BarEntry(2, 4));

        entries.add(new BarEntry(3, 3));

        entries.add(new BarEntry(4, 7));

        entries.add(new BarEntry(5, 12));

        entries.add(new BarEntry(6, 10));



        //buat objek entry untuk memasukkan data grafik -- data hotprospek
        List<BarEntry> entriesHotprospek = new ArrayList<>();

        //sumbu X menentukan posisi, sumbu Y isi data, keterangan masing masing data ditambahkan di value formatter dibawah -- data hotprospek
        entriesHotprospek.add(new BarEntry(0, 3));

        entriesHotprospek.add(new BarEntry(1, 2));

        entriesHotprospek.add(new BarEntry(2, 4));

        entriesHotprospek.add(new BarEntry(3, 3));

        entriesHotprospek.add(new BarEntry(4, 3));

        entriesHotprospek.add(new BarEntry(5, 1));

        entriesHotprospek.add(new BarEntry(6, 2));



        BarDataSet dataSet = new BarDataSet(entries, "Jumlah Pipeline");
        dataSet.setColor(getResources().getColor(R.color.colorPrimary));
        BarData barData=new BarData(dataSet);

        BarDataSet dataSetHotprospek = new BarDataSet(entriesHotprospek, "Jumlah Hotprospek");
        dataSetHotprospek.setColor(getResources().getColor(R.color.main_blue_color));
        BarData barDataHotprospek=new BarData(dataSetHotprospek);


        //ada bug tulisan description label dibawah kanan, kode dibawah mengosongkan tulisan itu
        Description description=new Description();
        description.setText("");
        charthotprospek.getAxisLeft().setGranularity(1);
        charthotprospek.getAxisRight().setGranularity(1);
        charthotprospek.getLegend().setYEntrySpace(2);
        chartpipeline.setDescription(description);
        charthotprospek.setDescription(description);

        chartpipeline.setDrawGridBackground(false);






        //nama AOM yang dijadikan judul di sumbu X ditaruh disini
        final String[] namaAOM = new String[] { "eki", "fazry", "alek","jono","budi","lontongers","jandi" };

        //proses ngeganti sumbu X jadi nama AOM
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return namaAOM[(int) value];
            }
        };

        XAxis xAxis = chartpipeline.getXAxis();
        xAxis.setGranularity(1); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        XAxis xAxis1Hotprospek=charthotprospek.getXAxis();
        xAxis1Hotprospek.setGranularity(1); // minimum axis-step (interval) is 1
        xAxis1Hotprospek.setValueFormatter(formatter);



        charthotprospek.setData(barDataHotprospek);
        chartpipeline.setData(barData);
        chartpipeline.invalidate();
        charthotprospek.invalidate();

        //buat objek entry untuk memasukkan data grafik -- data pencairan
        List<PieEntry> entriesPencairan = new ArrayList<>();

        //sumbu X menentukan posisi, sumbu Y isi data, keterangan masing masing data ditambahkan di value formatter dibawah -- data pencairan
        entriesPencairan.add(new PieEntry(17, "Putusan Setuju"));

        entriesPencairan.add(new PieEntry(3, "Putusan Ditolak"));

        entriesPencairan.add(new PieEntry(2, "Putusan Dikembalikan"));

        PieDataSet set = new PieDataSet(entriesPencairan, "");
        PieData data = new PieData(set);
        set.setColors(ColorTemplate.JOYFUL_COLORS);
        chartpencairan.setData(data);
        chartpencairan.setEntryLabelColor(Color.BLACK);
        chartpencairan.setEntryLabelTextSize(7);
        chartpencairan.setDescription(description);

        chartpencairan.invalidate();







        return mview;
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
