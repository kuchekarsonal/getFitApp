package com.example.getfit.ModelClasses;

public class Model {
    private int image;
    private String Title;
    private String foodItem1;

    private float foodItem1_calorieCount;
    private float foodItem1_serves;


    private String foodItem2;
    private float foodItem2_calorieCount;
    private float foodItem2_serves;



    public Model(int image, String title, String foodItem1, float foodItem1_calorieCount, float foodItem1_serves, String foodItem2, float foodItem2_calorieCount, float foodItem2_serves) {
        this.image = image;
        Title = title;
        this.foodItem1 = foodItem1;
        this.foodItem1_calorieCount = foodItem1_calorieCount;
        this.foodItem1_serves = foodItem1_serves;
        this.foodItem2 = foodItem2;
        this.foodItem2_calorieCount = foodItem2_calorieCount;
        this.foodItem2_serves = foodItem2_serves;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getFoodItem1() {
        return foodItem1;
    }

    public void setFoodItem1(String foodItem1) {
        this.foodItem1 = foodItem1;
    }

    public float getFoodItem1_calorieCount() {
        return foodItem1_calorieCount;
    }

    public void setFoodItem1_calorieCount(float foodItem1_calorieCount) {
        this.foodItem1_calorieCount = foodItem1_calorieCount;
    }

    public float getFoodItem1_serves() {
        return foodItem1_serves;
    }

    public void setFoodItem1_serves(float foodItem1_serves) {
        this.foodItem1_serves = foodItem1_serves;
    }

    public String getFoodItem2() {
        return foodItem2;
    }

    public void setFoodItem2(String foodItem2) {
        this.foodItem2 = foodItem2;
    }

    public float getFoodItem2_calorieCount() {
        return foodItem2_calorieCount;
    }

    public void setFoodItem2_calorieCount(float foodItem2_calorieCount) {
        this.foodItem2_calorieCount = foodItem2_calorieCount;
    }

    public float getFoodItem2_serves() {
        return foodItem2_serves;
    }

    public void setFoodItem2_serves(float foodItem2_serves) {
        this.foodItem2_serves = foodItem2_serves;
    }


}
