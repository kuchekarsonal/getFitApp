package com.example.getfit.Retrofit;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static Retrofit instance;
    private static String BASE_URL = "http://192.168.43.46:3000"; //ip address of our machine

    public static Retrofit getInstance(){
        if(instance ==  null)
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // enter ip address, In emulator, localhost will changed to ip address
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

       return  instance;
    }
}
