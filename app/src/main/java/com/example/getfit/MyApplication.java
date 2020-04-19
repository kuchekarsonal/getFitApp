package com.example.getfit;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApplication extends Application {

    public static final String Channel_1_ID = "Water Intake";

    private String userEmail;
    private String dietPlanSelected;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setDietPlanSelected(String dietPlanSelected) {
        this.dietPlanSelected = dietPlanSelected;
    }

    public String getDietPlanSelected() {
        return dietPlanSelected;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    Channel_1_ID,
                    "Water Intake",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Water Intake Notification");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }

    }
}
