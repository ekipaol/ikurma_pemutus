package com.application.bris.brisi.config.menu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.brisi.R;
import com.application.bris.brisi.model.menu.ListViewMenu;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private List<ListViewMenu> listMenu;
    private Context context;
    private MenuClickListener mMenuClickListener;

    public MenuAdapter(Context context, List<ListViewMenu> menu, MenuClickListener menuClickListener) {
        this.context = context;
        this.listMenu = menu;
        this.mMenuClickListener = menuClickListener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MenuViewHolder holder, int position) {
        holder.iv.setBackgroundResource(listMenu.get(position).getIcon());
        holder.tv.setText(listMenu.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv;
        private LinearLayout ll;

        public MenuViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_iconmenu);
            tv = (TextView) itemView.findViewById(R.id.tv_titlemenu);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
        }

    }
}