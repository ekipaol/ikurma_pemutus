
package com.application.bris.brisi_pemutus.listeners;


import com.application.bris.brisi_pemutus.model.keyvalue.keyvalue;

/**
 * Created by PID on 05/05/19.
 */

public interface KeyValueListener {
    void onKeyValueSelect(String title, keyvalue data);
}