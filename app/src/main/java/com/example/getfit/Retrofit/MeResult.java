package com.example.getfit.Retrofit;

public class MeResult {
    private  String name;
    private int age;
    private String activity_level;
    private float current_weight;
    private float goal_weight;
    private float height;
    public MeResult(String name, int age, String activity_level, float current_weight, float goal_weight, float height){
        this.name = name;
        this.age = age;
        this.activity_level = activity_level;
        this.current_weight = current_weight;
        this.goal_weight = goal_weight;
        this.height = height;

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getCurrentWeight() {
        return current_weight;
    }

    public float getGoalWeight() {
        return goal_weight;
    }

    public float getHeight() {
        return height;
    }

    public String getActivityLevel() {
        return activity_level;

    }
}
