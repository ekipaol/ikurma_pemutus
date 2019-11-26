package com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jsibbold.zoomage.ZoomageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


import butterknife.BindView;
import butterknife.ButterKnife;
import me.grantland.widget.AutofitTextView;
import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;

public class ActivityFotoKelengkapanDokumen extends AppCompatActivity {

    @BindView(R.id.bt_simpan_kelengkapan)
    Button bt_simpan_kelengkapan;
    @BindView(R.id.bt_kembali_kelengkapan)
    Button bt_kembali_kelengkapan;
    @BindView(R.id.iv_foto_kelengkapan)
    ZoomageView iv_foto_kelengkapan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_kelengkapan_dokumen);
        ButterKnife.bind(this);
        AppUtil.toolbarRegular(this, "Preview Foto");
        //Load foto, ambil dari intent ya kalo udah ada webservicenya, ini masi di pantek soalnya
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .error(R.drawable.banner_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        int id_foto=(int)getIntent().getIntExtra("id_foto",0);

        if(id_foto==0){
            Toast.makeText(this, "Belum ada foto untuk dokumen ini", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Harap tunggu hingga foto selesai loading", Toast.LENGTH_SHORT).show();
        }

        Glide.with(this)
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+id_foto)
                .apply(options)
                .into(iv_foto_kelengkapan);

        //end load foto

        bt_simpan_kelengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_foto_kelengkapan.buildDrawingCache();

                Bitmap bmp = iv_foto_kelengkapan.getDrawingCache();
                File storageLoc = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES); //context.getExternalFilesDir(null);


                Date currentTime = Calendar.getInstance().getTime();

                File file = new File(storageLoc, "foto_kelengkapan_"+currentTime+".jpg");

                try{
                    FileOutputStream fos = new FileOutputStream(file);
                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.close();

                    scanFile(ActivityFotoKelengkapanDokumen.this, Uri.fromFile(file));
                    Toast.makeText(ActivityFotoKelengkapanDokumen.this, "Berhasil menyimpan foto", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        bt_kembali_kelengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private static void scanFile(Context context, Uri imageUri){
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(imageUri);
        context.sendBroadcast(scanIntent);

    }
}
