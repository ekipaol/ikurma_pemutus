package com.application.bris.brisi_pemutus.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.util.magiccrypt.MagicCrypt;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Idong
 */

public class AppUtil {
    public static MagicCrypt magicCrypt = new MagicCrypt();
    private Snackbar snackbar;

    public static void showToastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void toolbarRegular(final Context context, String title) {

        Toolbar toolbar = (Toolbar) ((AppCompatActivity) context).findViewById(R.id.tb_regular);
        ImageView btnBack = (ImageView) ((AppCompatActivity) context).findViewById(R.id.btn_back);
        TextView tvPageTitle = (TextView) ((AppCompatActivity) context).findViewById(R.id.tv_page_title);
        tvPageTitle.setText(title);
        ((AppCompatActivity) context).setSupportActionBar(toolbar);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) context).onBackPressed();
            }
        });
    }

    public static void toolbarRegularFragment(final Activity context, String title) {

        Toolbar toolbar = (Toolbar) ((AppCompatActivity) context).findViewById(R.id.tb_regular);
        ImageView btnBack = (ImageView) ((AppCompatActivity) context).findViewById(R.id.btn_back);
        TextView tvPageTitle = (TextView) ((AppCompatActivity) context).findViewById(R.id.tv_page_title);
        tvPageTitle.setText(title);
        ((AppCompatActivity) context).setSupportActionBar(toolbar);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) context).onBackPressed();
            }
        });
    }

    public static long parseLongWithDefault (String data, long defaultValue){
        try {
            return Long.parseLong(data);
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }

    public static String getInitials(String label) {
        String initials="";
        String[] parts = label.split(" ");
        char initial;
        for (int i=0; i<parts.length; i++){
            initial=parts[i].charAt(0);
            initials+=initial;
        }
        return(initials.toUpperCase());
    }

    public static HashMap<String, String> getDeviceInfo(Context context) {
        HashMap<String, String> deviceInfo = new HashMap<>();
        String deviceId = "";
        String deviceName = "";
        String imei = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
            }
            deviceName = Build.MANUFACTURER
                    + " " + Build.MODEL + " Android " + Build.VERSION.RELEASE
                    + " " + Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();
            imei = telephonyManager.getDeviceId().toString();
        } catch (Exception e) {}
        deviceInfo.put(Constants.DEVICE_ID, deviceId);
        deviceInfo.put(Constants.DEVICE_NAME, deviceName);
        deviceInfo.put(Constants.IMEI, imei);
        return deviceInfo;
    }

    public static String encrypt(String data){
        if (data == null){
            return "";
        }
        else{
            return magicCrypt.encrypt(data);
        }
    }



    public static String parseRupiah(String amount){
        Double amountDouble = Double.valueOf(amount);
        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("Rp. ");
        formatRP.setMonetaryDecimalSeparator(',');
        formatRP.setGroupingSeparator('.');
        kursIDN.setDecimalFormatSymbols(formatRP);
        return kursIDN.format(amountDouble);
    }

    public static void notifwarning(Context mcontex, View root, String snackTitle) {
        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_LONG);
        snackbar.show();
        View view = snackbar.getView();

        //lakukan ini jika in ingin menambah panjang snackbar
//        TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setMaxLines(5);  // show multiple line
//        view.setBackgroundColor(ContextCompat.getColor(mcontex, R.color.colorWarning));

        TextView txtv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mcontex, R.drawable.ic_warning_outline_white), null, null, null);
        }
        txtv.setCompoundDrawablePadding(30);
        txtv.setTextColor(ContextCompat.getColor(mcontex, R.color.colorWhite));
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
    }
    public static void notifCheck(Context mcontex, View root, String snackTitle) {
        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_LONG);
        snackbar.show();
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorWhite));

        //lakukan ini jika in ingin menambah panjang snackbar
//        TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setMaxLines(5);  // show multiple line
//        view.setBackgroundColor(ContextCompat.getColor(mcontex, R.color.colorWarning));

        TextView txtv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mcontex, R.drawable.ic_correct), null, null, null);
        }
        txtv.setCompoundDrawablePadding(30);
        txtv.setTextColor(ContextCompat.getColor(mcontex, R.color.green_teal));
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public static String hashMd5(String data){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(data.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;

        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    public static String parseTanggalGeneral(String tgl, String formatDate, String formatDateTo){
        if (tgl == null){
            return null;
        }
        else{
            SimpleDateFormat format = new SimpleDateFormat(formatDate);
            SimpleDateFormat formatTo = new SimpleDateFormat(formatDateTo);
            String tglTo = "";
            try {
                Date date = format.parse(tgl);
                tglTo = formatTo.format(date);
            }
            catch (ParseException e){
                e.printStackTrace();
            }
            return tglTo;
        }
    }

    public static int parseIntWithDefault(String data, int defaultValue){
        try {
            return Integer.parseInt(data);
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }

    public static void notiferror(Context mcontex, View root, String snackTitle) {
        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_LONG);
        snackbar.show();
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(mcontex, R.color.colorWhite));
        TextView txtv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mcontex, R.drawable.ic_error_outline_secondary_24dp), null, null, null);
        }
        txtv.setCompoundDrawablePadding(30);
        txtv.setTextColor(ContextCompat.getColor(mcontex, R.color.colorCancelRed));
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public static String parseRupiahInt(Integer amount){
        Double amountDouble = new Double(amount);
        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("Rp. ");
        formatRP.setMonetaryDecimalSeparator(',');
        formatRP.setGroupingSeparator('.');
        kursIDN.setDecimalFormatSymbols(formatRP);
        return kursIDN.format(amountDouble);
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

    public static Bitmap rotateImageIfRequired(Context context, Bitmap img, Uri selectedImage){
        ExifInterface ei = null;
        InputStream inputStream;
        try {
            inputStream = context.getContentResolver().openInputStream(selectedImage);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ei = new ExifInterface(inputStream);
            }
            else{
                ei = new ExifInterface(selectedImage.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }

    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }



//    public static String encrypt(String data){
//        return magicCrypt.encrypt(data);
//    }
//
//    public static String decrypt(String data){
//        return magicCrypt.decrypt(data);
//    }


//    public static void notiferror(Context mcontex, View root, String snackTitle) {
//        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_INDEFINITE);
//        snackbar.setDuration(3000);
//        snackbar.show();
//        View view = snackbar.getView();
//        view.setBackgroundColor(ContextCompat.getColor(mcontex, R.color.colorError));
//        TextView txtv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mcontex, R.drawable.ic_info_outline_white_24dp), null, null, null);
//        }
//        txtv.setCompoundDrawablePadding(30);
//        txtv.setTextColor(ContextCompat.getColor(mcontex, R.color.colorWhite));
//        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
//    }
//
//    public static void notifwarning(Context mcontex, View root, String snackTitle) {
//        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_SHORT);
//        snackbar.show();
//        View view = snackbar.getView();
//        view.setBackgroundColor(ContextCompat.getColor(mcontex, R.color.colorWarning));
//        TextView txtv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mcontex, R.drawable.ic_warning_white_24dp), null, null, null);
//        }
//        txtv.setCompoundDrawablePadding(30);
//        txtv.setTextColor(ContextCompat.getColor(mcontex, R.color.colorWhite));
//        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
//    }
//
//    public static void notifsuccess(Context mcontex, View root, String snackTitle) {
//        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_INDEFINITE);
//        snackbar.setDuration(3000);
//        snackbar.show();
//        View view = snackbar.getView();
//        view.setBackgroundColor(ContextCompat.getColor(mcontex, R.color.colorSuccess));
//        TextView txtv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mcontex, R.drawable.ic_check_white_24dp), null, null, null);
//        }
//        txtv.setCompoundDrawablePadding(30);
//        txtv.setTextColor(ContextCompat.getColor(mcontex, R.color.colorWhite));
//        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
//    }
}