package com.application.bris.brisi_pemutus.api.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.application.bris.brisi_pemutus.BuildConfig;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.page_login.view.LoginActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.NullOnEmptyConverterFactory;
import com.application.bris.brisi_pemutus.util.service_encrypt.DESHelper;
import com.application.bris.brisi_pemutus.util.service_encrypt.MagicCryptHelper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class ApiClientAdapter {
    private static Retrofit retrofit;
    private ApiInterface apiInterface;
    private String baseurl = UriApi.Baseurl.URL;
    private static final GsonConverterFactory gson = GsonConverterFactory.create(new Gson());
    private long timeout = 90;
    private TimeUnit timeUnit = TimeUnit.SECONDS;
    private Context context;
    private static int id=1;
    AppPreferences apppref;


    public ApiClientAdapter(Context context){
        this.context = context;
        buildConnection(baseurl,id, timeout, timeUnit);

    }
    public ApiClientAdapter(Context context,int id){
        this.context=context;
        buildConnection(baseurl,id,timeout,timeUnit);

    }


    public ApiClientAdapter(Context context, String url){
        this.context = context;
        buildConnection(url,id, timeout, timeUnit);

    }
    public ApiClientAdapter(Context context, String url,int id){
        this.context = context;
        buildConnection(url,id, timeout, timeUnit);

    }

    public ApiClientAdapter(Context context, long timeoutReq, TimeUnit timeUnitReq){
        this.context = context;
        buildConnection(baseurl,id, timeoutReq, timeUnitReq);

    }

    public ApiClientAdapter(Context context, int id, long timeoutReq, TimeUnit timeUnitReq){
        this.context = context;
        buildConnection(baseurl, id,  timeoutReq, timeUnitReq);
    }


    private void buildConnection(String baseUrl,int id, long timeOut, TimeUnit timeUnit) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor headerAuth =null;
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if(id==1){
            apppref=new AppPreferences(context);
             headerAuth = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
//                    SharedPreferences sp= context.getSharedPreferences("token", Context.MODE_PRIVATE);
//                    String value = sp.getString("token", "token");
                    Request request = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer "+apppref.getToken())
                            .build();
                    return chain.proceed(request);

                }
            };
            clientBuilder.addInterceptor(headerAuth);

            //START INTERCEPTOR AUTO LOG OUT

            //menambah interceptor baru jika token expired, alias perlu login ulang, atau untuk error code lain
            clientBuilder .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    okhttp3.Response response = chain.proceed(request);

                    // validasi global untuk response code tertentu

                    if (response.code() == 401) {



                        //dialog hanya bisa muncul kalo dijalankan di main thread, jadi ditaruh didalam handler
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {

                                //current scenario

                                    Intent intent=new Intent(context,LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.putExtra("expiredToken",true);
                                    context.startActivity(intent);



//                                Toast.makeText(context, "Silahkan lakukan login ulang untuk kembali dapat mengakses aplikasi i-Kurma", Toast.LENGTH_LONG).show();

                                //ideal scenario
//                                SweetAlertDialog dialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
//                                dialog.setCanceledOnTouchOutside(false);
//                                dialog.setTitle("Sesi habis");
//                                dialog.setContentText("Silahkan lakukan login ulang untuk kembali dapat mengakses aplikasi i-Kurma");
//                                dialog.setConfirmText("OK");
//
//                                    dialog.show();
//                                    dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                        @Override
//                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                            dialog.dismissWithAnimation();
//                                            Intent intent=new Intent(context,LoginActivity.class);
//                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                            context.startActivity(intent);
//
//
//                                        }
//                                    });

                            }
                        });


                        return response;
                    }

                    return response;
                }
            });
            //END OF INTERCEPTER LOGOUT


        }

        //Interceptor enkripsi
        clientBuilder .addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                RequestBody requestBody = request.body();

                MagicCryptHelper encryptor =new MagicCryptHelper();

//                DESHelper encryptor =new DESHelper();

                if(request.method().equalsIgnoreCase("POST")){
                    String subtype = requestBody.contentType().subtype();
                    if(subtype.contains("json")){

                        try{
                            String encryptedRequest=encryptor.encrypt(bodyToString(requestBody));
                            AppUtil.logSecure("okhttp_decrypter_request",encryptor.decrypt(encryptedRequest));

//                            encryptedRequest=encryptor.decrypt(encryptedRequest);

                            if(BuildConfig.IS_PRODUCTION==false||BuildConfig.IS_BD){
                                Log.wtf("okhttp_decrypter_request",encryptor.decrypt(encryptedRequest));
                            }

//                            Log.wtf("okhttp_decrypter_request",encryptor.decrypt(encryptedRequest));


                            //jangan lupa kalo bikin request body baru, stringnya diambil dalam bentuk bytes
                            requestBody =   RequestBody.create(MediaType.parse("application/json"), encryptedRequest.getBytes());
                        }
                        catch (Exception e){

                            Log.d("okhttp-error",e.getMessage());
                        }
                    }

                }

                if(requestBody!=null){

                    //comment dari sini jika tanpa enkripsi
//
                    Request.Builder requestBuilder = request.newBuilder();
                    request = requestBuilder
                            .post(requestBody)
                            .build();

                    //end of comment

                    return chain.proceed(request);
                }
                else{
                    return chain.proceed(request);
                }

            }
        });

        //END OF INTERCEPTOR ENKRIPSI




        //otomatis disable logging kalau nembak ke prod
//        if(!UriApi.Baseurl.URL.equalsIgnoreCase("https://intel.brisyariah.co.id:55056/MobileBRISIAPI/webresources/")){
//            clientBuilder.addInterceptor(loggingInterceptor);
//        }

        //otomasi status Logging jika prod atau dev

        if(BuildConfig.SHOW_LOG){
            clientBuilder.addInterceptor(loggingInterceptor);
        }

//        if (!BuildConfig.IS_PRODUCTION) {
//            clientBuilder.addInterceptor(loggingInterceptor);
//        }
//        else{
//            if(BuildConfig.IS_BD){
//                clientBuilder.addInterceptor(loggingInterceptor);
//            }
//        }

        OkHttpClient httpClient;

        if(BuildConfig.IS_PRODUCTION){
            String hostname = "intel.brisyariah.co.id";
            CertificatePinner certificatePinner = new CertificatePinner.Builder()
                    .add(hostname, "sha256/KulbguoZjw2srzUzBvG+5oQnxaoWUqqbMExesRAXq9M=")
                    .add(hostname, "sha256/5kJvNEMw0KjrCAu7eXY5HZdvyCS13BbA0VJG1RSP91w=")
                    .add(hostname, "sha256/r/mIkG3eEpVdm+u/ko/cwxzOMo1bk4TyHIlByibiA5E=")
                    .build();

            httpClient = clientBuilder
                    .connectTimeout(timeOut, timeUnit)
                    .certificatePinner(certificatePinner)
                    .readTimeout(timeOut, timeUnit)
                    .build();
        }
        else{

            httpClient = clientBuilder
                    .connectTimeout(timeOut, timeUnit)
                    .readTimeout(timeOut, timeUnit)
                    .build();
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(gson)
                .client(httpClient)
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

    private String bodyToString(final RequestBody request){
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if(copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        }
        catch (final IOException e) {
            return "did not work";
        }
    }
}
