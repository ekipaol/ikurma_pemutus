package com.application.bris.brisi.config.menu;

import android.content.Context;

import com.application.bris.brisi.R;
import com.application.bris.brisi.model.menu.ListViewMenu;

import java.util.List;

/**
 * Created by PID on 28/04/2019.
 */

public class Menu {

    public static String MENU_ID = "idMenu";
    public static String MENU_ROOT = "rootMenu";

    /*************************** Main Menu AO***************************/
    public static void mainMenuAO(Context context, List<ListViewMenu> menu) {
        menu.add(new ListViewMenu(R.drawable.ico_pipeline, context.getString(R.string.menu_pipeline)));
        menu.add(new ListViewMenu(R.drawable.ico_hotprospek, context.getString(R.string.menu_hotprospek)));
        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_pipeline, context.getString(R.string.menu_pipeline)));
//        menu.add(new ListViewMenu(R.drawable.ico_hotprospek, context.getString(R.string.menu_hotprospek)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved)));
    }
}
