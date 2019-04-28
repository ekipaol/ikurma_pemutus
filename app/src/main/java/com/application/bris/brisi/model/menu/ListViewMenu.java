package com.application.bris.brisi.model.menu;

/**
 * Created by PID on 28/04/19.
 */

public class ListViewMenu {
    private int icon;
    private String title;

    public ListViewMenu(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
