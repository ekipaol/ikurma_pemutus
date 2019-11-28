package com.application.bris.brisi_pemutus.adapter.menu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.listener.menu.MenuClickListener;
import com.application.bris.brisi_pemutus.listeners.PutusanHomeListener;
import com.application.bris.brisi_pemutus.model.dashboard.DashboardCred;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.model.menu.ListViewMenu;

import java.util.List;

import me.grantland.widget.AutofitTextView;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private List<ListViewMenu> listMenu;
    private Context context;
    private MenuClickListener mMenuClickListener;
    private ApiClientAdapter apiClientAdapter ;
    DashboardCred dataNotifikasi;
    List<Putusan> listPutusan;
    Putusan testing;
    PutusanHomeListener listenerPutusan;
    int notifPutusan=0;

    public MenuAdapter(Context context, List<ListViewMenu> menu, MenuClickListener menuClickListener) {
        this.context = context;
        this.listMenu = menu;
        this.mMenuClickListener = menuClickListener;
    }
    public MenuAdapter(Context context, List<ListViewMenu> menu, MenuClickListener menuClickListener,int jumlahPutusan) {
        this.context = context;
        this.listMenu = menu;
        this.mMenuClickListener = menuClickListener;
        this.notifPutusan=jumlahPutusan;
    }

    public MenuAdapter(Context context, List<ListViewMenu> menu, MenuClickListener menuClickListener,DashboardCred notifikasi) {
        this.context = context;
        this.listMenu = menu;
        this.mMenuClickListener = menuClickListener;
        this.dataNotifikasi =notifikasi;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuAdapter.MenuViewHolder holder, final int position) {
        holder.iv_iconmenu.setImageResource(listMenu.get(position).getIcon());
        holder.tv_titlemenu.setText(listMenu.get(position).getTitle());
        holder.rl_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenuClickListener.onMenuClick(listMenu.get(position).getTitle());
            }
        });

        //load notifikasi di icon tertentu

        if(holder.tv_titlemenu.getText().toString().equalsIgnoreCase("Putusan")) {

            if (dataNotifikasi.getNotifDashboard() != 0||dataNotifikasi.getJlhPutusanDeviasi()!=0) {
                holder.tv_badgemenu.setText(Integer.toString(dataNotifikasi.getNotifDashboard()+dataNotifikasi.getJlhPutusanDeviasi()));
                holder.tv_badgemenu.setVisibility(View.VISIBLE);
            }

        }
        else if(holder.tv_titlemenu.getText().toString().equalsIgnoreCase("Disposisi")){
            if (dataNotifikasi.getNotifDashboardDisposisi()!=0) {
                holder.tv_badgemenu.setText(Integer.toString(dataNotifikasi.getNotifDashboardDisposisi()));
                holder.tv_badgemenu.setVisibility(View.VISIBLE);

            }
        }

    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public List<Putusan> getListPutusan(){
        return listPutusan;
    }

    public Putusan getPutusanSingle(){
        return testing;
    }



    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rl_menu;
        private ImageView iv_iconmenu;
        private TextView  tv_badgemenu;
        private AutofitTextView tv_titlemenu;

        public MenuViewHolder(View itemView) {
            super(itemView);
            rl_menu = (RelativeLayout) itemView.findViewById(R.id.rl_menu);
            iv_iconmenu = (ImageView) itemView.findViewById(R.id.iv_iconmenu);
            tv_titlemenu = (AutofitTextView) itemView.findViewById(R.id.tv_titlemenu);
            tv_badgemenu = (TextView) itemView.findViewById(R.id.tv_badgemenu);
        }

    }
}