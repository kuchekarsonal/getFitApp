package com.example.getfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    private Intent intent;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.nav_main);

        bottomNavigationView.setSelectedItemId(R.id.nav_menu_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_menu_home:
                        return true;
                    case R.id.nav_menu_analysis:
                        intent = new Intent(HomeActivity.this,AnalysisActivity.class);
                        HomeActivity.this.startActivity(intent);
                        HomeActivity.this.finish();
                        return true;
                    case R.id.nav_menu_me:
                        intent = new Intent(HomeActivity.this,MeActivity.class);
                        HomeActivity.this.startActivity(intent);
                        HomeActivity.this.finish();
                        return true;
                    case R.id.nav_menu_plans:
                        intent = new Intent(HomeActivity.this,PlansActivity.class);
                        HomeActivity.this.startActivity(intent);
                        HomeActivity.this.finish();
                        return true;
                }

                return false;
            }
        });
    }
}
