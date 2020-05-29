package com.application.bris.brisi_pemutus.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import androidx.core.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.application.bris.brisi_pemutus.page_putusan.prescreening.PrescreeningActivity;

//import net.lingala.zip4j.ZipFile;

import net.lingala.zip4j.core.ZipFile;

import java.io.File;
import java.io.FileOutputStream;

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
//                    AppUtil.showToastShort(context, "Download Berhasil, Silahkan buka folder Download");
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
                new ZipFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+downloadFileName).extractAll(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/HasilSlik/"+downloadFileName).toString());


                Log.d("nikorang",downloadFileName.substring(14,30));

                //membuka file pdf berdasarkan NIK, karena isi nama file pdf adalah NIK
                //real data
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/HasilSlik")+"/"+downloadFileName+"/"+downloadFileName.substring(14,30)+".pdf");

                //biar terdaftar sebagai objek yang di download
//                DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//                dm.addCompletedDownload("Hasil Slik", "Hasil Slik Nasabah", false, "application/pdf", file.getAbsolutePath(), file.length(), true);

                //pantekan
//                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/HasilSlik")+"/"+"1811052412930001"+".pdf");

                if (file.exists()) {

                    Log.d("masuksini","dia masuk exis bro");

                    Intent intent ;
                    Uri pdfPath = Uri.fromFile(file);

                    //android nougat keatas harus pake fileprovider kalo mau buka buka file
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(uri);
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                        context.startActivity(intent);
                    } else {
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(pdfPath, "application/pdf");
                        intent = Intent.createChooser(intent, "Open File");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
                    }

//                    Uri pdfPath = Uri.fromFile(file);
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setDataAndType(pdfPath, "application/pdf");

                    try {
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        AppUtil.showToastLong(context, "Anda belum punya aplikasi untuk melihat PDF, harap download terlebih dahulu");
                    }


                }

                else {
                    File fileNotFound = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/HasilSlik") + "/" + downloadFileName + "/NO FOUND (KTP).pdf");

                    Log.d("namafile",Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/HasilSlik") + "/" + downloadFileName+ "/NO FOUND (KTP).pdf");

                    if (fileNotFound.exists()) {

                        Log.d("diaeksis", "file exis bro");

                        Intent intent;
                        Uri pdfPath = Uri.fromFile(fileNotFound);

                        //android nougat keatas harus pake fileprovider kalo mau buka buka file
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Log.d("diaeksis", "file exis nougat");
                            Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", fileNotFound);
                            intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(uri);
                            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                        context.startActivity(intent);
                        } else {
                            Log.d("diaeksis", "file exis tidak nougat");
                            intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(pdfPath, "application/pdf");
                            intent = Intent.createChooser(intent, "Open File");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
                        }

//                    Uri pdfPath = Uri.fromFile(file);
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setDataAndType(pdfPath, "application/pdf");

                        try {
                            context.startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            AppUtil.showToastLong(context, "Anda belum punya aplikasi untuk melihat PDF, harap download terlebih dahulu");
                        }


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
