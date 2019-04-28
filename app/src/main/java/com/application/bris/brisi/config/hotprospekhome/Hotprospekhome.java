package com.application.bris.brisi.config.hotprospekhome;

import android.content.Context;

import com.application.bris.brisi.R;
import com.application.bris.brisi.model.hotprospek.hotprospek;
import com.application.bris.brisi.model.pipeline.pipeline;

import java.util.List;

/**
 * Created by PID on 28/04/2019.
 */

public class Hotprospekhome {

    /*************************** Sample Data Pipeline Home ***************************/
    public static void listHotprospek(Context context, List<hotprospek> hotprospeks) {
        hotprospeks.add(new hotprospek(R.drawable.brochure, "Ridho Pratama", "Mikro 25", "25000000", "24", "Konsumtif", "Sedang dalam tinjauan"));
        hotprospeks.add(new hotprospek(R.drawable.brochure, "Wildan Afifi", "Mikro 75", "65000000", "48", "Produktif", "Input Data Lengkap"));
        hotprospeks.add(new hotprospek(R.drawable.brochure, "Ridho Pratama", "KUR 25", "25000000", "12", "Produktif", "Input LKN"));
    }
}
