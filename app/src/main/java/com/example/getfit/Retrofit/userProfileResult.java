package com.example.getfit.Retrofit;

public class userProfileResult {
   private String email;
   private  Float height;
   private  Float current_weight;
   private  String activity_level;
   private String gender;
   private Integer age;
   private Float goal_weight;

    public userProfileResult(String email, Float height, Float weight, String activity_level, String gender, Integer age, Float goal_weight) {
        this.email = email;
        this.height = height;
        this.current_weight = weight;
        this.activity_level = activity_level;
        this.gender = gender;
        this.age = age;
        this.goal_weight = goal_weight;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public void setWeight(Float weight) {
        this.current_weight = weight;
    }

    public void setActivity_level(String activity_level) {
        this.activity_level = activity_level;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGoal_weight(Float goal_weight) {
        this.goal_weight = goal_weight;
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