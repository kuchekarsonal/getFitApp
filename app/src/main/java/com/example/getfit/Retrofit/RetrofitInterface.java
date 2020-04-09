package com.example.getfit.Retrofit;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup (@Body HashMap<String, String> map);

    /**
     * @param post
     * @return
     */
    @POST("/questionnaire/{email}")
    Call<userProfileResult> createUserProfile(@Body userProfileResult post);


}
//    String email;
//    private float height;
//    private float weight;
//    private String activityLevel;
//    private String gender;
//    private int age;
//    private String goalWeight;