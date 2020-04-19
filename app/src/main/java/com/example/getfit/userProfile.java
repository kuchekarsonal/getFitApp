package com.example.getfit;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.POST;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.getfit.Retrofit.RetrofitClient;
import com.example.getfit.Retrofit.RetrofitInterface;
import com.example.getfit.Retrofit.userProfileResult;

import java.util.HashMap;

public class userProfile extends AppCompatActivity {
    EditText height, weight, activityLevel;
    String email;
    RadioGroup gender;
    EditText age, goalWeight;
    Button ok;
    RadioButton radioButton;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    float height_val,weight_val,goalWeight_val;
    int age_val;
    String gender_val,activityLevel_val;
    float BMI,BMR,requiredCalCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        if(getIntent().hasExtra("email")){
            email = getIntent().getStringExtra("email");
        }
        height = (EditText)findViewById(R.id.userProfileHeight);
        weight = (EditText)findViewById(R.id.userProfileWeight);
        activityLevel =(EditText) findViewById(R.id.userProfileActivityLevel);
        gender = (RadioGroup)findViewById(R.id.radioGender);
        age= (EditText)findViewById(R.id.userProfileAge);
        goalWeight =(EditText)findViewById(R.id.userProfileGoalWeight);
        ok = (Button)findViewById(R.id.userProfileSubmit);

        //Init Service
        retrofit = RetrofitClient.getInstance();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedRadioId = gender.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(selectedRadioId);

                //retrieving values
                height_val = Float.valueOf(height.getText().toString());
                weight_val = Float.valueOf(weight.getText().toString());
                activityLevel_val = activityLevel.getText().toString();
                gender_val = radioButton.getText().toString();
                age_val = Integer.parseInt(age.getText().toString());
                goalWeight_val = Float.valueOf(goalWeight.getText().toString());


                //Calculating BMR
                if(gender_val == "male"){
                    BMR = (height_val*6.25f) +(weight_val*9.99f) - (age_val*4.92f) +5f;
                }
                else{
                    BMR = (height_val*6.25f) +(weight_val*9.99f) - (age_val*4.92f) +5f;
                }

                //Calculating requiredCalCount
                switch (activityLevel_val){

                    case "very light":  requiredCalCount = BMR*1.3f;
                        break;
                    case "light":  requiredCalCount = BMR*1.55f;
                        break;
                    case "moderate":  requiredCalCount = BMR*1.65f;
                        break;
                    case "heavy":  requiredCalCount = BMR*1.8f;
                        break;
                    case "very heavy":  requiredCalCount = BMR*2f;
                        break;
                }

                //Calculating BMI
                BMI = weight_val/((height_val*0.01f)*(height_val*0.01f));

                ((MyApplication)getApplication()).setDietPlanSelected("NoPlan");

               executeUserProfile(email,height_val,weight_val,activityLevel_val,gender_val,age_val,goalWeight_val,BMI,BMR,requiredCalCount);

            }

            private void executeUserProfile(String email, float height, float weight, String activityLevel, String gender, int age, float goalWeight, float BMI, float BMR, final float requiredCalCount) {
                userProfileResult post = new userProfileResult(email,
                        height,weight,activityLevel,gender,age,goalWeight,BMI,BMR,requiredCalCount);


                Call<userProfileResult> call =retrofitInterface.createUserProfile(post);
                call.enqueue(new Callback<userProfileResult>() {
                    @Override
                    public void onResponse(Call<userProfileResult> call, Response<userProfileResult> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(userProfile.this, response.code(),Toast.LENGTH_SHORT).show();
                        }else{
                            userProfileResult userProfileResult = response.body();
                            Toast.makeText(userProfile.this,
                                    "Successfully Created Profile", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(userProfile.this,Diet.class);
                            intent.putExtra("New Registration",true);
                            intent.putExtra("Calories",requiredCalCount);
                            userProfile.this.startActivity(intent);
                            userProfile.this.finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<userProfileResult> call, Throwable t) {
                        Toast.makeText(userProfile.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();

                    }
                });


               // email : req.body.email,
//          height : req.body.height,
//          current_weight : req.body.current_weight,
//          activity_level : req.body.activity_level,
//          gender : req.body.gender,
//          age : req.body.age,
//          goal_weight : req.body.goal_weight
            }


        });

    }
}
