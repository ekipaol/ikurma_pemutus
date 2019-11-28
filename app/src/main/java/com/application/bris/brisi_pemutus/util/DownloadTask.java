package com.application.bris.brisi_pemutus.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.application.bris.brisi_pemutus.page_putusan.prescreening.PrescreeningActivity;

//import net.lingala.zip4j.ZipFile;

import net.lingala.zip4j.core.ZipFile;

import java.io.File;
import java.io.FileOutputStream;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DownloadTask {
    private static final String TAG = "Download Task";
    private Context context;
    private String downloadFileName = "";
    private byte[] data = null;
    public DownloadTask(Context context, byte[] data, String downloadFileName) {
        this.context = context;
        this.data = data;
        this.downloadFileName = downloadFileName;
        //Start Downloading Task
        new DownloadingTask().execute();
    }
    public void hideLoading(){
        ((PrescreeningActivity)context).loading.setVisibility(View.GONE);
    }
    private class DownloadingTask extends AsyncTask<Void, Void, Void> {
        File apkStorage = null;
        File outputFile = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(Void result) {
            try {
                if (outputFile != null) {
                    hideLoading();
                    AppUtil.showToastShort(context, "Download Berhasil, Silahkan buka folder Download");
                    Log.d("SUKSES DOWNLOAD", "1");
                } else {
                    hideLoading();
                    AppUtil.showToastShort(context, "Download Gagal");
                    Log.d("GAGAL DOWNLOAD", "2");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                if (new CheckForSDCard().isSDCardPresent()) {
                    apkStorage = new File(
                            Environment.getExternalStorageDirectory().toString(), "Download");
                } else
                    Toast.makeText(context, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();
                //If File is not present create directory
                if (!apkStorage.exists()) {
                    apkStorage.mkdir();
                    Log.e(TAG, "Directory Created.");
                }

                outputFile = new File(apkStorage, downloadFileName);//Create Output file in Main File
                //Create New File if not present

                if (!outputFile.exists()) {
                    outputFile.createNewFile();

                    Log.e(TAG, "File Created");
                }
                FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location
                fos.write(data);
                fos.flush();
                fos.close();
                Log.d(TAG, "Download OK ");

                //extract file hasil download zip di folder download
                new ZipFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+downloadFileName).extractAll(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/HasilSlik").toString());

                Log.d("nikorang",downloadFileName.substring(14,30));

                //membuka file pdf berdasarkan NIK, karena isi nama file pdf adalah NIK
                //real data
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/HasilSlik")+"/"+downloadFileName.substring(14,30)+".pdf");

                //pantekan
//                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/HasilSlik")+"/"+"1811052412930001"+".pdf");

                if (file.exists()) {

                    Uri pdfPath = Uri.fromFile(file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(pdfPath, "application/pdf");

                    try {
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        AppUtil.showToastLong(context, "Anda belum punya aplikasi untuk melihat PDF, harap download terlebih dahulu");
                    }


                }







            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

//    public void openFolder()
//    {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
//                + "/myFolder/");
//        intent.setDataAndType(uri, "text/csv");
//        context.startActivity(Intent.createChooser(intent, "Open folder"));
//    }
}
