package com.example.getfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.getfit.Fragments.Fragment_Analysis;
import com.example.getfit.Fragments.Fragment_Home;
import com.example.getfit.Fragments.Fragment_dietPlan;
import com.example.getfit.Fragments.Fragment_userProfile;
import com.example.getfit.Fragments.NewRegistrationDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Diet extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout mMainFrame;

    //fragments
    private Fragment_Home fragmentHome;
    private Fragment_dietPlan fragmentDietPlan;
    private Fragment_Analysis fragmentAnalysis;
    private Fragment_userProfile fragmentUserProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        bottomNavigationView = findViewById(R.id.nav_main);
        mMainFrame = findViewById(R.id.mainframeLayout);

        //intialise fragments;
        fragmentHome = new Fragment_Home();
        fragmentAnalysis = new Fragment_Analysis();
        fragmentDietPlan =new Fragment_dietPlan();
        fragmentUserProfile = new Fragment_userProfile();

        getSupportFragmentManager().beginTransaction().replace(R.id.mainframeLayout,fragmentHome).commit();

        checkNewRegistration();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_menu_home:
                        setFragment(fragmentHome);
                        return  true;

//                    case  R.id.nav_menu_plans:
//                        setFragment(fragmentDietPlan);
//                        return  true;

                    case R.id.nav_menu_analysis:
                        setFragment(fragmentAnalysis);
                        return true;

                    case R.id.nav_menu_me:
                        setFragment(fragmentUserProfile);

                        return true;

                     default: return false;

                }
            }

            private void setFragment(Fragment fragment) {
                FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainframeLayout, fragment).commit();
            }
        });
    }

    private void checkNewRegistration(){
        if(getIntent().hasExtra("New Registration")) {
            if (getIntent().getExtras().getBoolean("New Registration")) {
                //Opening Dialog Box
                NewRegistrationDialog dialog = new NewRegistrationDialog();
                dialog.show(getSupportFragmentManager(), "New Registration Dialog");
            }
        }
    }
}
