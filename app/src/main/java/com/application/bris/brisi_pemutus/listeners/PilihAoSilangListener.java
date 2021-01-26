package com.application.bris.brisi_pemutus.listeners;


import com.application.bris.brisi_pemutus.model.ao_silang_list_kanwil.ListKanwil;
import com.application.bris.brisi_pemutus.model.ao_silang_list_user.ListUser;

/**
 * Created by PID on 05/05/19.
 */

public interface PilihAoSilangListener {

    void onSelectCabang(String title, ListKanwil data);

    void onSelectRsc(String title, ListKanwil data);

    void onSelectUser(String title, ListUser data);
}
