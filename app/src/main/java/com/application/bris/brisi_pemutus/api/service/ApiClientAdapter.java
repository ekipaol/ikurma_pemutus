package com.application.bris.brisi_pemutus.api.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.util.NullOnEmptyConverterFactory;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
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
        }






        clientBuilder.addInterceptor(loggingInterceptor);

        OkHttpClient httpClient = clientBuilder
                .connectTimeout(timeOut, timeUnit)
                .readTimeout(timeOut, timeUnit)
                .build();

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
}
