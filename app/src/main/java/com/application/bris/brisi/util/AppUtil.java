package com.application.bris.brisi.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
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

import com.application.bris.brisi.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;

/**
 * Created by Idong
 */

public class AppUtil {
//    public static MagicCrypt magicCrypt = new MagicCrypt();
    private Snackbar snackbar;

    public static void showToastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

//    public static void toolbarRegular(final Context context, String title) {
//
//        Toolbar toolbar = (Toolbar) ((AppCompatActivity) context).findViewById(R.id.tb_regular);
//        ImageView btnBack = (ImageView) ((AppCompatActivity) context).findViewById(R.id.btn_back);
//        TextView tvPageTitle = (TextView) ((AppCompatActivity) context).findViewById(R.id.tv_page_title);
//        tvPageTitle.setText(title);
//        ((AppCompatActivity) context).setSupportActionBar(toolbar);
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((AppCompatActivity) context).onBackPressed();
//            }
//        });
//    }

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
            imei = telephonyManager.getDeviceId().toString();
        } catch (Exception e) {}

        deviceInfo.put("device_id", deviceId);
        deviceInfo.put("imei", imei);

        return deviceInfo;
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