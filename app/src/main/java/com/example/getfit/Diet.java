package com.example.getfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getfit.Fragments.Fragment_Analysis;
import com.example.getfit.Fragments.Fragment_Home;
import com.example.getfit.Fragments.Fragment_dietPlan;
import com.example.getfit.Fragments.Fragment_userProfile;
import com.example.getfit.Fragments.NewRegistrationDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class Diet extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout mMainFrame;
    public String userEmail;

    //fragments
    private Fragment_Home fragmentHome;
    private Fragment_dietPlan fragmentDietPlan;
    private Fragment_Analysis fragmentAnalysis;
    private Fragment_userProfile fragmentUserProfile;
    private TextView caloriesTextView;
    private Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        if(((MyApplication)this.getApplication()).getUserEmail() != null)
        {
            userEmail = ((MyApplication)this.getApplication()).getUserEmail();
        }

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

                    case  R.id.nav_menu_plans:
                        setFragment(fragmentDietPlan);
                        return  true;

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
        if(getIntent().hasExtra("New Registration") && getIntent().hasExtra("Calories")) {
            if (getIntent().getExtras().getBoolean("New Registration")) {
                //Opening Dialog Box
//                NewRegistrationDialog dialog = new NewRegistrationDialog();
//                Bundle bundle = new Bundle();
//                bundle.putFloat("Calories",getIntent().getExtras().getFloat("Calories"));
//                dialog.setArguments(bundle);
//                dialog.show(getSupportFragmentManager(), "New Registration Dialog");


                final Dialog newRegDialog = new Dialog(this);
                newRegDialog.setContentView(R.layout.new_registration_dialog);
                float calories = getIntent().getExtras().getFloat("Calories");
                close = newRegDialog.findViewById(R.id.new_reg_dialog_btn);
                caloriesTextView = newRegDialog.findViewById(R.id.new_reg_calories_value);

                caloriesTextView.setText("Your daily required Calories is "+String.valueOf(calories)+ " kcals.");

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newRegDialog.dismiss();
                    }
                });

                newRegDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                newRegDialog.show();


            }
        }
    }
}
