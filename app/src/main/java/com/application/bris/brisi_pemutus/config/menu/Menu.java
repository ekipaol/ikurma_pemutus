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
    public static void mainMenuPemutus(Context context, List<ListViewMenu> menu) {
        AppPreferences appPreferences = new AppPreferences(context);
        menu.add(new ListViewMenu(R.drawable.ic_daftar_putusan, context.getString(R.string.menu_pengajuan)));

        //hanya pinca dan pincapem yang bisa akses menu disposisi
        if(appPreferences.getFidRole().equalsIgnoreCase("76")||appPreferences.getFidRole().equalsIgnoreCase("79")){
            menu.add(new ListViewMenu(R.drawable.ic_disposisi_3, "Disposisi"));
        }

        menu.add(new ListViewMenu(R.drawable.ic_riwayat, "Riwayat"));

        //hanya pinca dan pincapem yang bisa akses menu manajemen user
        if(appPreferences.getFidRole().equalsIgnoreCase("76")||appPreferences.getFidRole().equalsIgnoreCase("79")){
            menu.add(new ListViewMenu(R.drawable.ic_manajemen_user, context.getString(R.string.menu_disetujui)));
        }



        //halaman performance hanya bisa diakses pinca pincapem dan mmm dan UH
        if (appPreferences.getFidRole().equalsIgnoreCase("76")||appPreferences.getFidRole().equalsIgnoreCase("79")||appPreferences.getFidRole().equalsIgnoreCase("72")||appPreferences.getFidRole().equalsIgnoreCase("71")) {
            menu.add(new ListViewMenu(R.drawable.ic_performance, "Performance"));
  }


        //seluruh pemutus kecuali UH dan MM bisa mengakses menu ambil alih dan eklaim
        if(!appPreferences.getFidRole().equalsIgnoreCase("71")&&!appPreferences.getFidRole().equalsIgnoreCase("77")){
            menu.add(new ListViewMenu(R.drawable.ic_ambil_alih_2, "Ambil Alih Putusan"));
            //MENU EKLAIM
//            menu.add(new ListViewMenu(R.drawable.ic_eklaim, "e-Klaim"));
        }

        //halaman ao silang hanya bisa diakses pinca pincapem MM
  if(appPreferences.getFidRole().equalsIgnoreCase("79")||appPreferences.getFidRole().equalsIgnoreCase("76")||appPreferences.getFidRole().equalsIgnoreCase("77")){
            menu.add(new ListViewMenu(R.drawable.ic_ao_silang, "AO Silang"));
        }

        //MENU MONITORING
        menu.add(new ListViewMenu(R.drawable.ic_business,"Monitoring"));





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


//        menu.add(new ListViewMenu(R.drawable.ic_disposisi_3, "Disposisi"));


//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_pipeline, context.getString(R.string.menu_pipeline)));
//        menu.add(new ListViewMenu(R.drawable.ico_hotprospek, context.getString(R.string.menu_hotprospek)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
        }

    public static void mainMenuPusat(Context context, List<ListViewMenu> menu) {
        AppPreferences appPreferences = new AppPreferences(context);


        //MENU MONITORING
        menu.add(new ListViewMenu(R.drawable.ic_business,"Monitoring"));

        menu.add(new ListViewMenu(R.drawable.ic_ambil_alih,"Salam Digital"));



        //ada tombol logout jika bukan ambil alih
        if(appPreferences.getStatusAmbilAlih().equalsIgnoreCase("tidak")){
            menu.add(new ListViewMenu(R.drawable.ic_logout_front, "Logout"));
        }
        //ada tombol keluar ambil alih jika user sedang ambil alih
        else{
            menu.add(new ListViewMenu(R.drawable.ic_keluar_ambil_alih2,"Keluar Ambil Alih"));
        }
    }

    public static void mainMenuPemutusAmanah(Context context, List<ListViewMenu> menu) {
        AppPreferences appPreferences = new AppPreferences(context);
        menu.add(new ListViewMenu(R.drawable.ic_daftar_putusan, context.getString(R.string.menu_pengajuan)));

        //hanya pinca dan pincapem yang bisa akses menu disposisi
        if(appPreferences.getFidRole().equalsIgnoreCase("76")||appPreferences.getFidRole().equalsIgnoreCase("79")){
            menu.add(new ListViewMenu(R.drawable.ic_disposisi_3, "Disposisi"));
        }

        menu.add(new ListViewMenu(R.drawable.ic_riwayat, "Riwayat"));



        //seluruh pemutus kecuali UH dan MM bisa mengakses menu ambil alih
        if(!appPreferences.getFidRole().equalsIgnoreCase("71")&&!appPreferences.getFidRole().equalsIgnoreCase("77")){
            menu.add(new ListViewMenu(R.drawable.ic_ambil_alih_2, "Ambil Alih Putusan"));
        }

        //halaman ao silang hanya bisa diakses pinca pincapem MM
        if(appPreferences.getFidRole().equalsIgnoreCase("79")||appPreferences.getFidRole().equalsIgnoreCase("76")||appPreferences.getFidRole().equalsIgnoreCase("77")){
            menu.add(new ListViewMenu(R.drawable.ic_ao_silang, "AO Silang"));
        }

        //MENU MONITORING
        menu.add(new ListViewMenu(R.drawable.ic_business,"Monitoring"));


        //MENU EKLAIM
//        menu.add(new ListViewMenu(R.drawable.ic_eklaim, "e-Klaim"));



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


//        menu.add(new ListViewMenu(R.drawable.ic_disposisi_3, "Disposisi"));


//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_pipeline, context.getString(R.string.menu_pipeline)));
//        menu.add(new ListViewMenu(R.drawable.ico_hotprospek, context.getString(R.string.menu_hotprospek)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
    }

    public static void menuPemutusAm(Context context, List<ListViewMenu> menu){
        menu.add(new ListViewMenu(R.drawable.ic_daftar_putusan, context.getString(R.string.menu_pengajuan)));
        menu.add(new ListViewMenu(R.drawable.ic_riwayat, "Riwayat"));
        menu.add(new ListViewMenu(R.drawable.ic_business,"Monitoring"));
//        menu.add(new ListViewMenu(R.drawable.ic_eklaim, "e-Klaim"));
        menu.add(new ListViewMenu(R.drawable.ic_logout_front, "Logout"));


    }

    public static void menuPemutusAmpm(Context context, List<ListViewMenu> menu){
        menu.add(new ListViewMenu(R.drawable.ic_daftar_putusan, context.getString(R.string.menu_pengajuan)));
        menu.add(new ListViewMenu(R.drawable.ic_riwayat, "Riwayat"));
        menu.add(new ListViewMenu(R.drawable.ic_business,"Monitoring"));
//        menu.add(new ListViewMenu(R.drawable.ic_eklaim, "e-Klaim"));
        menu.add(new ListViewMenu(R.drawable.ic_logout_front, "Logout"));


    }
    }

