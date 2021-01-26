package com.application.bris.brisi_pemutus.dialog;

/**
 * Created by idong on 06/05/2019.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.listeners.PilihAoSilangListener;
import com.application.bris.brisi_pemutus.model.ao_silang_list_kanwil.ListKanwil;
import com.application.bris.brisi_pemutus.model.ao_silang_list_user.ListUser;

import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class DialogPilihAo extends DialogFragment {
    private ImageView btn_close;
    private TextView tv_title;
    private SkkAdapater skkAdapater;
    private RscAdapater rscAdapater;
    private AoAdapater aoAdapater;
    private RecyclerView rv_produk;
    private static List<?> dataKanwil, dataSkk, dataRsc, dataAo;
    private static PilihAoSilangListener listener;
    public static final String TAG = "example_dialog";
    public List<ListKanwil> dataKeyValueKanwil;
    public List<ListKanwil> dataKeyValueSkk;
    public List<ListKanwil> dataKeyValueRsc;
    public List<ListUser> dataKeyValueAo;
    public static String dialog;
    private static String title;
    private SearchView searchView;
    private Realm realm;

    public static DialogPilihAo display(FragmentManager fragmentManager, String titleId, List<?> mdata, PilihAoSilangListener mlistener, String mdialog) {
        dialog = mdialog;
        title = titleId;
        listener = mlistener;

        if (dialog.equalsIgnoreCase("skk")) {
            dataSkk = (List<ListKanwil>) mdata;
        } else if (dialog.equalsIgnoreCase("rsc")) {
            dataRsc = (List<ListKanwil>) mdata;
        }

        DialogPilihAo dialogAddress = new DialogPilihAo();
        dialogAddress.show(fragmentManager, TAG);
        return dialogAddress;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        this.realm = Realm.getInstance(config);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide_Produk);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_desccode, container, false);
        btn_close = (ImageView) view.findViewById(R.id.btn_close);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        rv_produk = (RecyclerView) view.findViewById(R.id.rv_produk);
        searchView = (SearchView) view.findViewById(R.id.searchView);
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
        tv_title.setTextSize(13);
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
        if (dialog.equalsIgnoreCase("skk")) {
            dataKeyValueSkk = (List<ListKanwil>) dataSkk;

            skkAdapater = new SkkAdapater(getContext(), dataKeyValueSkk, title, listener);
            rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_produk.setItemAnimator(new DefaultItemAnimator());
            rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            rv_produk.setAdapter(skkAdapater);

            if (dataKeyValueSkk.size() > 0)
            {
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        try {
                            skkAdapater.getFilter().filter(query);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String query) {
                        try {
                            skkAdapater.getFilter().filter(query);
                            return false;
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            return false;
                        }
                    }
                });
            }
        } else if (dialog.equalsIgnoreCase("rsc")) {
            dataKeyValueRsc = (List<ListKanwil>) dataRsc;

            rscAdapater = new RscAdapater(getContext(), dataKeyValueRsc, title, listener);
            rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_produk.setItemAnimator(new DefaultItemAnimator());
            rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            rv_produk.setAdapter(rscAdapater);

            if (dataKeyValueRsc.size() > 0)
            {
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        try {
                            rscAdapater.getFilter().filter(query);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String query) {
                        try {
                            rscAdapater.getFilter().filter(query);
                            return false;
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            return false;
                        }
                    }
                });
            }
        } else if (dialog.equalsIgnoreCase("ao")) {
            dataKeyValueAo = getDataAo();

            aoAdapater = new AoAdapater(getContext(), dataKeyValueAo, title, listener);
            rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_produk.setItemAnimator(new DefaultItemAnimator());
            rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            rv_produk.setAdapter(aoAdapater);

            if (dataKeyValueAo.size() > 0)
            {
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        try {
                            aoAdapater.getFilter().filter(query);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String query) {
                        try {
                            aoAdapater.getFilter().filter(query);
                            return false;
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            return false;
                        }
                    }
                });
            }
        }
    }

    private List<ListUser> getDataAo(){
        RealmResults<ListUser> datas = realm.where(ListUser.class)
                .equalTo("status", "A", Case.INSENSITIVE)
                .distinct("NAMA_PEGAWAI")
                .findAll();
        return datas;
    }

    public class SkkAdapater extends RecyclerView.Adapter<SkkAdapater.ProductViewHolder> implements Filterable {

        private Context context;
        private List<ListKanwil> data;
        private List<ListKanwil> datafiltered;
        private String title;
        private PilihAoSilangListener listener;

        public SkkAdapater(Context context, List<ListKanwil> data, String title, PilihAoSilangListener listener) {
            this.context = context;
            this.data = data;
            this.datafiltered = data;
            this.title = title;
            this.listener = listener;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
            final ListKanwil datas  = datafiltered.get(position);

            holder.tv_title.setVisibility(View.GONE);

            holder.tv_product.setText(datas.getSBDESC());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectCabang(title, datas);
                    dismiss();
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
                        List<ListKanwil> filteredList = new ArrayList<>();
                        for (ListKanwil row : data){
                            if(row.getSBDESC().toLowerCase().contains(charString.toLowerCase())){
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
                    datafiltered = (ArrayList<ListKanwil>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
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

    public class RscAdapater extends RecyclerView.Adapter<RscAdapater.ProductViewHolder> implements Filterable {

        private Context context;
        private List<ListKanwil> data;
        private List<ListKanwil> datafiltered;
        private String title;
        private PilihAoSilangListener listener;

        public RscAdapater(Context context, List<ListKanwil> data, String title, PilihAoSilangListener listener) {
            this.context = context;
            this.data = data;
            this.datafiltered = data;
            this.title = title;
            this.listener = listener;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
            final ListKanwil datas  = datafiltered.get(position);

            holder.tv_title.setVisibility(View.GONE);

            holder.tv_product.setText(datas.getSBDESC());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectRsc(title, datas);
                    dismiss();
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
                        List<ListKanwil> filteredList = new ArrayList<>();
                        for (ListKanwil row : data){
                            if(row.getSBDESC().toLowerCase().contains(charString.toLowerCase())){
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
                    datafiltered = (ArrayList<ListKanwil>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
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

    public class AoAdapater extends RecyclerView.Adapter<AoAdapater.ProductViewHolder> implements Filterable {

        private Context context;
        private List<ListUser> data;
        private List<ListUser> datafiltered;
        private String title;
        private PilihAoSilangListener listener;

        public AoAdapater(Context context, List<ListUser> data, String title, PilihAoSilangListener listener) {
            this.context = context;
            this.data = data;
            this.datafiltered = data;
            this.title = title;
            this.listener = listener;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
            final ListUser datas  = datafiltered.get(position);

            holder.tv_title.setVisibility(View.GONE);

            holder.tv_product.setText(datas.getNAMA_PEGAWAI());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectUser(title, datas);
                    dismiss();
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
                        List<ListUser> filteredList = new ArrayList<>();
                        for (ListUser row : data){
                            if(row.getNAMA_PEGAWAI().toLowerCase().contains(charString.toLowerCase())){
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
                    datafiltered = (ArrayList<ListUser>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
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
