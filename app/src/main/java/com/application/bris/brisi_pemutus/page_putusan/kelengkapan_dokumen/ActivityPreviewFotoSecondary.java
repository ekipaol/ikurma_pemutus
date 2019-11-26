package com.application.bris.brisi_pemutus.page_putusan.kelengkapan_dokumen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
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
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.jsibbold.zoomage.ZoomageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;


import butterknife.BindView;
import butterknife.ButterKnife;
import me.grantland.widget.AutofitTextView;
import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;

public class ActivityPreviewFotoSecondary extends AppCompatActivity {

    @BindView(R.id.bt_simpan_kelengkapan)
    Button bt_simpan_kelengkapan;
    @BindView(R.id.bt_kembali_kelengkapan)
    Button bt_kembali_kelengkapan;
    @BindView(R.id.iv_foto_kelengkapan)
    ImageViewZoom iv_foto_kelengkapan;

    Bitmap rotatedBitmap;

    //kelas ini untuk preview foto dengan lubrary imagezoom, yang bisa preview gambar fullsize dan tidk terpotong


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_foto_secondary);
        ButterKnife.bind(this);
        AppUtil.toolbarRegular(this, "Preview Foto");
        //Load foto, ambil dari intent ya kalo udah ada webservicenya, ini masi di pantek soalnya
        final RequestOptions options = new RequestOptions()
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

//        Glide.with(this)
//                .asBitmap()
//                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+id_foto)
//                .apply(options)
//                .into(new CustomTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        int width = resource.getWidth();
//                        int height = resource.getHeight();;
//                        Log.d("rotasion",Integer.toString(width)+"///"+Integer.toString(height));
//                        if (width > height){
//                            Log.d("rotasion","image is rotated");
//                            rotatedBitmap = rotate(resource,-90);
//                            iv_foto_kelengkapan.setImageBitmap(rotatedBitmap);
//
//
////                           Glide.with(ActivityPreviewFotoSecondary.this)
////                                   .load(rotatedBitmap)
////                                   .apply(options)
////                                   .into(iv_foto_kelengkapan);
//
//                        }
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//                    }
//                });





//        Glide.with(this)
//                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+id_foto)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        //rotate image when resource ready
//
//                        Bitmap hasilResize=getResizedBitmap(((BitmapDrawable)iv_foto_kelengkapan.getDrawable()).getBitmap(),1024);
//
//                        int width =  hasilResize.getWidth();
//                        int height =   hasilResize.getHeight();
//                        Log.d("rotasion",Integer.toString(width)+"///"+Integer.toString(height));
//                        if (width > height){
//                            Log.d("rotasion","image is rotated");
//                            rotatedBitmap = rotate( ((BitmapDrawable)iv_foto_kelengkapan.getDrawable()).getBitmap(),-90);
//                            BitmapDrawable ob = new BitmapDrawable(getResources(), rotatedBitmap);
//                            iv_foto_kelengkapan.setImageBitmap(rotatedBitmap);
////                            iv_foto_kelengkapan.setBackground(ob);
//
////                           Glide.with(ActivityPreviewFotoSecondary.this)
////                                   .load(rotatedBitmap)
////                                   .apply(options)
////                                   .into(iv_foto_kelengkapan);
//
//                        }
//                        return false;
//                    }
//                })
//                .apply(options)
//                .into(iv_foto_kelengkapan);


        Glide.with(this)
                .asBitmap()
                .load(UriApi.Baseurl.URL + UriApi.foto.urlFoto+id_foto)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Bitmap resizedimage=getResizedBitmap(resource,1024);
                        iv_foto_kelengkapan.setImageBitmap(resizedimage);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });


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

                    scanFile(ActivityPreviewFotoSecondary.this, Uri.fromFile(file));
                    Toast.makeText(ActivityPreviewFotoSecondary.this, "Berhasil menyimpan foto", Toast.LENGTH_SHORT).show();

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

    private Bitmap rotate(Bitmap bm, int rotation) {
        if (rotation != 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(rotation);
            Bitmap bmOut = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
            return bmOut;
        }
        return bm;
    }

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }




}
