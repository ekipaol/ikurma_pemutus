package com.application.bris.brisi_pemutus.listeners;

import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;

import java.util.List;

public interface PutusanHomeListener {
    void onMenuLoad(List<Putusan> putusan);
}
