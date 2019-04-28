package com.application.bris.brisi.config.pipelinehome;

import android.content.Context;

import com.application.bris.brisi.R;
import com.application.bris.brisi.model.menu.ListViewMenu;
import com.application.bris.brisi.model.pipeline.pipeline;

import java.util.List;

/**
 * Created by PID on 28/04/2019.
 */

public class Pipelinehome {

    /*************************** Sample Data Pipeline Home ***************************/
    public static void listPipeline(Context context, List<pipeline> pipelines) {
        pipelines.add(new pipeline(R.drawable.brochure, "Ridho Pratama", "Mikro 25", "25000000", "24", "28 April 2019 12:01"));
        pipelines.add(new pipeline(R.drawable.sticker, "Wildan Afifi", "Mikro 75", "75000000", "36", "28 April 2019 12:01"));
        pipelines.add(new pipeline(R.drawable.poster, "Ridho Pratama", "Mikro 200", "200000000", "48", "28 April 2019 12:01"));
        pipelines.add(new pipeline(R.drawable.namecard, "Wildan Afifi", "KUR 25", "25000000", "12", "28 April 2019 12:01"));
    }
}
