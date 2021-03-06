package com.example.getfit.Retrofit;

import com.example.getfit.AddMealItem;
import com.example.getfit.ModelClasses.DietPlanModel;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {

    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup (@Body HashMap<String, String> map);

    /**
     * @param post
     * @return
     */
    @POST("/questionnaire")
    Call<userProfileResult> createUserProfile(@Body userProfileResult post);

    @GET("/addBreakfast/{email}")
    Call<List<AddMealItem>> getBreakfast(@Path("email") String email);

    @GET("/addLunch/{email}")
    Call<List<AddMealItem>> getLunch(@Path("email") String email);

    @GET("/addDinner/{email}")
    Call<List<AddMealItem>> getDinner(@Path("email") String email);

    @GET("/search")
    Call<List<SearchFoodItem>> getFoodItems();

    @GET("/search/{collection}/{category}/{foodItem}")
    Call<SearchFoodDetails> getFoodDetails(@Path("collection") String collection, @Path("foodItem") String foodItem,@Path("category") String category);

    @GET("/getInfo/calorieMacros/{email}")
    Call<HomeFragmentDetails> getHomeFragDetails(@Path("email") String email);

    @POST("/addMeal/{email}/{mealType}")
    Call<AddMealItem> addMeal(@ Body AddMealItem mealItem,@Path("email") String email, @Path("mealType") String mealType);

    @GET("/getMeal/{email}/{mealType}")
    Call<List<AddMealItem>> getMeal(@Path("email") String email,@Path("mealType") String mealType);

    @GET("profile/{email}")
    Call<MeResult> getPosts(@Path("email") String email);

    @GET("plan/getPlan/{email}")
    Call<PlanResult> getPlan(@Path("email") String email);

    @POST("plan/setPlan/{email}")
    Call<PlanResult> setPlan(@Path("email") String email, @Body PlanResult result);

    @GET("/nodeFlask/{email}")
    Call<DietPlanModel> getRecommendation(@Path("email") String email);

    @PUT("/profile/{email}")
    Call<Void> getUpdatedProfile(@Path("email") String email, @Body MeResult Result);


}
//    String email;
//    private float height;
//    private float weight;
//    private String activityLevel;
//    private String gender;
//    private int age;
//    private String goalWeight;