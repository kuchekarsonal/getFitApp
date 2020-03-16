package com.example.getfit.Retrofit;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetroFitInterface {
    @POST("register")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("email") String email,
                                    @Field("name") String name,
                                    @Field("password") String password,
                                    @Field("height") Integer height,
                                    @Field("weight") Integer weight,
                                    @Field("activityLevel") String activityLevel);


    @POST("login")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("email") String email,
                                        @Field("password") String password);

}
