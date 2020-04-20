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
import com.example.getfit.Retrofit.PlanResult;
import com.example.getfit.Retrofit.RetrofitClient;
import com.example.getfit.Retrofit.RetrofitInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Diet extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout mMainFrame;
    public String userEmail;

    //fragments
    private Fragment_Home fragmentHome;
    private Fragment_dietPlan fragmentDietPlan;
    private Fragment_Analysis fragmentAnalysis;
    private Fragment_userProfile fragmentUserProfile;
    private  DietPlanSelected fragmentDietPlanSelected;
    private TextView caloriesTextView;
    private Button close;

    //Retrofit
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;


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
        fragmentDietPlanSelected = new DietPlanSelected();

        //Init Service
        retrofit = RetrofitClient.getInstance();
        retrofitInterface = retrofit.create(RetrofitInterface.class);



        getSupportFragmentManager().beginTransaction().replace(R.id.mainframeLayout,fragmentHome).commit();

        getPlan();

        checkNewRegistration();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_menu_home:
                        setFragment(fragmentHome);
                        return  true;

                    case  R.id.nav_menu_plans:
//                        Log.d("Diet.java",((MyApplication)Diet.this.getApplication()).getDietPlanSelected());
                        Toast.makeText(Diet.this,"Plan Received"+ ((MyApplication)Diet.this.getApplication()).getDietPlanSelected(), Toast.LENGTH_SHORT).show();
                        String msg = ((MyApplication)Diet.this.getApplication()).getDietPlanSelected();

                        Log.d(msg, " Executed");
                        if(msg.equals("NoPlan"))
                        {
                            setFragment(fragmentDietPlan);
                        }
                        else{
                            setFragment(fragmentDietPlanSelected);
                        }

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

            public void setFragment(Fragment fragment) {
                FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainframeLayout, fragment).commit();
            }


        });
    }


    public void DietPlanSelected(String plan) {

        //Send selectedDietPlan to Server - retrofitCall

        ((MyApplication)this.getApplication()).setDietPlanSelected(plan);

        PlanResult planResult = new PlanResult(plan);
        Call<PlanResult> setPlanCall = retrofitInterface.setPlan(userEmail,planResult);
        setPlanCall.enqueue(new Callback<PlanResult>() {
            @Override
            public void onResponse(Call<PlanResult> call, Response<PlanResult> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Diet.this,"Some failure in setting Plan - Diet.java", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Diet.this,"Plan Added", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PlanResult> call, Throwable t) {
                Toast.makeText(Diet.this,"In set Plan - Diet.java" +t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframeLayout, fragmentDietPlanSelected).commit();
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
    private void getPlan(){
        final Call<PlanResult> getPlanCall = retrofitInterface.getPlan(userEmail);
        getPlanCall.enqueue(new Callback<PlanResult>() {
            @Override
            public void onResponse(Call<PlanResult> call, Response<PlanResult> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Diet.this,"Some failure in getting Plan - Diet.java", Toast.LENGTH_SHORT).show();
                }else{
                    PlanResult plan = response.body();
                    ((MyApplication)Diet.this.getApplication()).setDietPlanSelected(plan.getPlan());
//                    Toast.makeText(Diet.this,"Plan Recieved"+plan.getPlan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PlanResult> call, Throwable t) {
                Toast.makeText(Diet.this,"In get Plan - Diet.java" +t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
