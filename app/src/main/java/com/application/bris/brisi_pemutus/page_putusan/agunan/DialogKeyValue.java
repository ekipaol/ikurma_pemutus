package com.application.bris.brisi_pemutus.page_putusan.agunan;

/**
 * Created by idong on 06/05/2019.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.config.globaldata.GlobalData;
import com.application.bris.brisi_pemutus.listeners.KeyValueListener;
import com.application.bris.brisi_pemutus.model.keyvalue.keyvalue;

import java.util.ArrayList;
import java.util.List;

public class DialogKeyValue extends DialogFragment{
    private ImageView btn_close;
    private TextView tv_title;
    private DialogKeyValue.ProdukAdapater produkAdapater;
    private RecyclerView rv_produk;
    private List<keyvalue> dataKeyvalue;
    private static KeyValueListener keyValueListener;
    public static final String TAG = "example_dialog";

    private static String title;

    public static DialogKeyValue display(FragmentManager fragmentManager, String titleId, KeyValueListener keyValueListenerId) {
        title = titleId;
        keyValueListener = keyValueListenerId;
        DialogKeyValue dialogAddress = new DialogKeyValue();
        dialogAddress.show(fragmentManager, TAG);
        return dialogAddress;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            //dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide_Produk);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_produk, container, false);
        btn_close = (ImageView) view.findViewById(R.id.btn_close);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        rv_produk = (RecyclerView) view.findViewById(R.id.rv_produk);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customToolbar();
        initializeProduct();
    }

    public void customToolbar(){
        backgroundStatusBar();
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_title.setText("Pilih "+title);
    }

    private void backgroundStatusBar(){
        Window window = getDialog().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initializeProduct(){
        if(title.equalsIgnoreCase("jenis usaha") || title.equalsIgnoreCase("bidang usaha")){
            dataKeyvalue = getDataJenisUsaha();
        }
        else if(title.equalsIgnoreCase("pekerjaan")){
            dataKeyvalue = getDataBidangPekerjaan();
        }
        else if (title.equalsIgnoreCase("tujuan penggunaan")){
            dataKeyvalue = getDataTujuanPenggunaan();
        }
        else if (title.equalsIgnoreCase("jenis kelamin")){
            dataKeyvalue = getDataJenisKelamin();
        }

        else if (title.equalsIgnoreCase("agama")){
            dataKeyvalue = getDataAgama();
        }

        else if (title.equalsIgnoreCase("status nikah")){
            dataKeyvalue = getDataStatusNikah();
        }

        else if (title.equalsIgnoreCase("tipe pendapatan")){
            dataKeyvalue = getDataTipePendapatan();
        }

        else if (title.equalsIgnoreCase("pendidikan terakhir")){
            dataKeyvalue = getDataPendidikanTerakhir();
        }

        else if (title.equalsIgnoreCase("status tempat domisili")){
            dataKeyvalue = getDataStatusTempatDomisili();
        }

        //tambahan eki
        else if (title.equalsIgnoreCase("bentuk bidang tanah")){
            dataKeyvalue = getBidangTanah();
        }
        else if (title.equalsIgnoreCase("permukaan tanah")){
            dataKeyvalue = getPermukaanTanah();
        }
        else if (title.equalsIgnoreCase("jenis surat tanah")){
            dataKeyvalue = getJenisSurat();
        }
        else if (title.equalsIgnoreCase("hub dengan pemegang hak")){
            dataKeyvalue = getHubNasabah();
        }
        else if (title.equalsIgnoreCase("Jenis Bangunan")){
            dataKeyvalue = getJenisbangunan();
        }
        else if (title.equalsIgnoreCase("Lokasi Bangunan")){
            dataKeyvalue = getLokasiBangun();
        }
        else if (title.equalsIgnoreCase("jenis agunan xbrl")){
            dataKeyvalue = getJenisAgunanXBRL();
        }
        else if (title.equalsIgnoreCase("hub penghuni dengan pemegang hak")){
            dataKeyvalue = getHubPenghuniDenganPemegangHak();
        }

        //tambahan eki buat tanah kosong
        else if (title.equalsIgnoreCase("Hub Pemegang Hak dg Nasabah")){
            dataKeyvalue = getHubPenghuniDenganPemegangHak();
        }
        else if (title.equalsIgnoreCase("status penggarap")){
            dataKeyvalue = getStatusPenggarap();
        }
        else if (title.equalsIgnoreCase("jenis dokumen")){
            dataKeyvalue = getJenisDokumen();
        }
        else if (title.equalsIgnoreCase("check bpn")){
            dataKeyvalue = getCheckBpn();
        }
        else if (title.equalsIgnoreCase("Hasil")){
            dataKeyvalue = getHasilBpn();
        }
        else if (title.equalsIgnoreCase("Hub Penggarap dg Pemegang Hak")){
            dataKeyvalue = getHubPenggarap();
        }





        produkAdapater = new DialogKeyValue.ProdukAdapater(getContext(), dataKeyvalue, title, keyValueListener);
        rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_produk.setItemAnimator(new DefaultItemAnimator());
        rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rv_produk.setAdapter(produkAdapater);
    }

    private List<keyvalue> getDataJenisUsaha(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.bidangUsaha(getContext(), data);
        return data;
    }

    private List<keyvalue> getDataBidangPekerjaan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.bidangKerjaan(getContext(), data);
        return data;
    }

    private List<keyvalue> getDataTujuanPenggunaan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.tujuanPenggunaan(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataJenisKelamin(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisKelamin(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataAgama(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.agama(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataStatusNikah(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.statusMenikah(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataTipePendapatan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.tipePendapatan(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataPendidikanTerakhir(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.pendidikanTerakhir(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataStatusTempatDomisili(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.statusTempatDomisili(getContext(), data);
        return  data;
    }


    //tambahan eki
    private List<keyvalue> getBidangTanah(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.bentukBidangTanah(getContext(), data);
        return  data;
    }

    private List<keyvalue> getPermukaanTanah(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.permukaanTanah(getContext(), data);
        return  data;
    }
    private List<keyvalue> getJenisSurat(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisSuratTanah(getContext(), data);
        return  data;
    }
    private List<keyvalue> getHubNasabah(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.hubDenganPemegangHak(getContext(), data);
        return  data;
    }
    private List<keyvalue> getJenisbangunan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisBangunan(getContext(), data);
        return  data;
    }
    private List<keyvalue> getLokasiBangun(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.lokasiBangunan(getContext(), data);
        return  data;
    }
    private List<keyvalue> getJenisAgunanXBRL(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisAgunanXBRL(getContext(), data);
        return  data;
    }
    private List<keyvalue> getHubPenghuniDenganPemegangHak(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.hubPenghuniDenganPemegangHak(getContext(), data);
        return  data;
    }

    //tambahan eki untuk tanah kosong

    private List<keyvalue> getStatusPenggarap(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.statusPenggarap(getContext(), data);
        return  data;
    }

    private List<keyvalue> getHubPenggarap(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.hubPenggarapDgPemegang(getContext(), data);
        return  data;
    }

    private List<keyvalue> getJenisDokumen(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisDokumen(getContext(), data);
        return  data;
    }

    private List<keyvalue> getCheckBpn(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.checkBpn(getContext(), data);
        return  data;
    }

    private List<keyvalue> getHasilBpn(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.hasilBpn(getContext(), data);
        return  data;
    }


    //CLASS ADAPTER PRODUCT
    public class ProdukAdapater extends RecyclerView.Adapter<ProdukAdapater.ProductViewHolder> {

        private Context context;
        private List<keyvalue> data;
        private String title;
        private KeyValueListener keyValueListener;

        public ProdukAdapater(Context context, List<keyvalue> data, String title, KeyValueListener keyValueListener) {
            this.context = context;
            this.data = data;
            this.title = title;
            this.keyValueListener = keyValueListener;
        }

        @NonNull
        @Override
        public ProdukAdapater.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_product, parent, false);
            return new ProdukAdapater.ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
            holder.tv_title.setText(title);
            holder.tv_product.setText(data.get(position).getName());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    keyValueListener.onKeyValueSelect(title, data.get(position));
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ProductViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_title, tv_product;
            public RelativeLayout rl_product;
            public ProductViewHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_product = (TextView) itemView.findViewById(R.id.tv_product);
                rl_product = (RelativeLayout) itemView.findViewById(R.id.rl_product);
            }
        }
    }
}
