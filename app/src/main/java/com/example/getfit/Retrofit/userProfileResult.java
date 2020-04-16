package com.example.getfit.Retrofit;

import com.google.gson.annotations.SerializedName;

public class userProfileResult {
   private String email;
   private  float height;
   private  float current_weight;
   private  String activity_level;
   private String gender;
   private int age;
   private float goal_weight;

   @SerializedName("bmi")
   private float BMI;
   @SerializedName("bmr")
   private float BMR;
   @SerializedName("calorieCount")
   private float requiredCalCount;

    public userProfileResult(String email, float height, float weight, String activity_level, String gender, int age, float goal_weight, float BMI,float BMR, float requiredCalCount) {
        this.email = email;
        this.height = height;
        this.current_weight = weight;
        this.activity_level = activity_level;
        this.gender = gender;
        this.age = age;
        this.goal_weight = goal_weight;
        this.BMI = BMI;
        this.BMR = BMR;
        this.requiredCalCount = requiredCalCount;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.current_weight = weight;
    }

    public void setActivity_level(String activity_level) {
        this.activity_level = activity_level;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGoal_weight(Float goal_weight) {
        this.goal_weight = goal_weight;
    }

    public void setBMI(float BMI) {
        this.BMI = BMI;
    }

    public void setCurrent_weight(float current_weight) {
        this.current_weight = current_weight;
    }

    public void setBMR(float BMR) {
        this.BMR = BMR;
    }

    public void setGoal_weight(float goal_weight) {
        this.goal_weight = goal_weight;
    }

    public void setRequiredCalCount(float requiredCalCount) {
        this.requiredCalCount = requiredCalCount;
    }


    public float getBMI() {
        return BMI;
    }

    public float getCurrent_weight() {
        return current_weight;
    }

    public float getBMR() {
        return BMR;
    }

    public float getGoal_weight() {
        return goal_weight;
    }

    public float getHeight() {
        return height;
    }

    public float getRequiredCalCount() {
        return requiredCalCount;
    }

    public int getAge() {
        return age;
    }

    public String getActivity_level() {
        return activity_level;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

}

//
//  email : req.body.email,
//          height : req.body.height,
//          current_weight : req.body.current_weight,
//          activity_level : req.body.activity_level,
//          gender : req.body.gender,
//          age : req.body.age,
//          goal_weight : req.body.goal_weight