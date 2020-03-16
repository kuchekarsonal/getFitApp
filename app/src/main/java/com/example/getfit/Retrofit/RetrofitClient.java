package com.example.getfit.Retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static Retrofit instance;

    public static Retrofit getInstance(){
        if(instance ==  null)
            instance = new Retrofit.Builder()
                    .baseUrl(" http://ipaddress ") // enter ip address, In emulatot, localhost will changed to ip address
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

        return  instance;
    }
}
