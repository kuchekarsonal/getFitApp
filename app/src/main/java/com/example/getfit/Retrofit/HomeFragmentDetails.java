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

    float eatenCalories;
    float eatenCarbs;
    float eatenFats;
    float eatenProtein;

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

    public float getEatenCalories() {
        return eatenCalories;
    }

    public float getEatenCarbs() {
        return eatenCarbs;
    }

    public float getEatenFats() {
        return eatenFats;
    }

    public float getEatenProtein() {
        return eatenProtein;
    }
}
