package com.example.getfit.Retrofit;

import com.google.gson.annotations.SerializedName;

public class HomeFragmentDetails {

    @SerializedName("cal")
    float calories;

    @SerializedName("c")
    float carbohydrates;

    @SerializedName("f")
    float fat;

    @SerializedName("p")
    float protein;

    public HomeFragmentDetails(float calories,float carbohydrates,float fat,float protein){
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.protein = protein;
    }

    public float getFat() {
        return fat;
    }

    public float getProtein() {
        return protein;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public float getCalories() {
        return calories;
    }

}
