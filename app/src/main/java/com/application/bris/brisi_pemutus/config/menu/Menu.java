package com.application.bris.brisi_pemutus.config.menu;

import android.content.Context;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.menu.ListViewMenu;

import java.util.List;

/**
 * Created by PID on 28/04/2019.
 */

public class Menu {

    public static String MENU_ID = "idMenu";
    public static String MENU_ROOT = "rootMenu";

    /*************************** Main Menu AO***************************/
    public static void mainMenuAO(Context context, List<ListViewMenu> menu) {
        AppPreferences appPreferences = new AppPreferences(context);
        menu.add(new ListViewMenu(R.drawable.ic_daftar_putusan, context.getString(R.string.menu_pengajuan)));
        //hide disposisi for a while
//        menu.add(new ListViewMenu(R.drawable.ic_disposisi_3, "Disposisi"));
        menu.add(new ListViewMenu(R.drawable.ic_riwayat, "Riwayat"));

        //seluruh pemutus kecuali UH bisa mengakses menu manajemen user
        if(!appPreferences.getFidRole().equalsIgnoreCase("71")){
            menu.add(new ListViewMenu(R.drawable.ic_manajemen_user, context.getString(R.string.menu_disetujui)));
        }



//        if (!appPreferences.getFidRole().equalsIgnoreCase("71")) {
            menu.add(new ListViewMenu(R.drawable.ic_performance, "Performance"));
//  }


        //seluruh pemutus kecuali UH bisa mengakses menu ambil alih
        if(!appPreferences.getFidRole().equalsIgnoreCase("71")){
//            menu.add(new ListViewMenu(R.drawable.ic_ambil_alih_2, "Ambil Alih"));
        }


        //ada tombol logout jika bukan ambil alih
        if(appPreferences.getStatusAmbilAlih().equalsIgnoreCase("tidak")){
            menu.add(new ListViewMenu(R.drawable.ic_logout_front, "Logout"));
        }
        //ada tombol keluar ambil alih jika user sedang ambil alih
        else{
            menu.add(new ListViewMenu(R.drawable.ic_keluar_ambil_alih2,"Keluar Ambil Alih"));
        }




            //manajemen user hanya bsia diakses pinca dan pincapem
//        if(appPreferences.getFidRole().equalsIgnoreCase("76")||appPreferences.getFidRole().equalsIgnoreCase("79")){
//            menu.add(new ListViewMenu(R.drawable.ic_manajemen_user, context.getString(R.string.menu_disetujui)));
//        }


//        menu.add(new ListViewMenu(R.drawable.ico_pengajuan_eform, "Disposisi"));


//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_pipeline, context.getString(R.string.menu_pipeline)));
//        menu.add(new ListViewMenu(R.drawable.ico_hotprospek, context.getString(R.string.menu_hotprospek)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
        }
    }

