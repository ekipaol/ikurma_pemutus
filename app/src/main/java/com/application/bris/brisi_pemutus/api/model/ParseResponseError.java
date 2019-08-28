package com.application.bris.brisi_pemutus.api.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.ResponseBody;

public class ParseResponseError {
    public static Error confirmEror(ResponseBody responseBody){
        Gson gson = new GsonBuilder().create();
        Error error = new Error();
        try {
            error = gson.fromJson(responseBody.string(), Error.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return error;
    }
}