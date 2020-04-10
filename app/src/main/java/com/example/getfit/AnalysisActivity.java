package com.example.getfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AnalysisActivity extends AppCompatActivity {

    private Intent intent;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        bottomNavigationView = findViewById(R.id.nav_main);

        bottomNavigationView.setSelectedItemId(R.id.nav_menu_analysis);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_menu_home:
                        intent = new Intent(AnalysisActivity.this,PlansActivity.class);
                        AnalysisActivity.this.startActivity(intent);
                        AnalysisActivity.this.finish();
                        return true;
                    case R.id.nav_menu_analysis:
                        return true;
                    case R.id.nav_menu_me:
                        intent = new Intent(AnalysisActivity.this,MeActivity.class);
                        AnalysisActivity.this.startActivity(intent);
                        AnalysisActivity.this.finish();
                        return true;
                    case R.id.nav_menu_plans:
                        intent = new Intent(AnalysisActivity.this,PlansActivity.class);
                        AnalysisActivity.this.startActivity(intent);
                        AnalysisActivity.this.finish();
                        return true;
                }

                return false;
            }
        });
    }

}
