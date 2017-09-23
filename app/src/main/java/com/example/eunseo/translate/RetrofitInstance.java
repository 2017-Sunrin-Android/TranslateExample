package com.example.eunseo.translate;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by eunseo on 2017-09-23.
 */

public class RetrofitInstance {
    private static Retrofit retrofit=null;
    private static String API_URL="https://openapi.naver.com/";
    static public Retrofit getInstance(Context context){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL) // 통신 url
                    .addConverterFactory(GsonConverterFactory.create()) // json통신 여부
                    .build();
        }
        return retrofit;
    }
}
