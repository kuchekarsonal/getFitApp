package com.example.getfit;

import android.app.Application;

public class MyApplication extends Application {

    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
