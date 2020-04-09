package com.example.getfit;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.POST;

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
    EditText email, height, weight, activityLevel;
    RadioGroup gender;
    EditText age, goalWeight;
    Button ok;
    RadioButton radioButton;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        email = (EditText)findViewById(R.id.userProfileEmail);
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

               //String Email = email.getText().toString();
               executeUserProfile(email.getText().toString(),

                       Float.valueOf(height.getText().toString()),
                       Float.valueOf(weight.getText().toString()),
                       activityLevel.getText().toString(),
                        radioButton.getText().toString(),
                       Integer.parseInt(age.getText().toString()),
                       Float.valueOf(goalWeight.getText().toString()));

            }

            private void executeUserProfile(String email, Float height, Float weight, String activityLevel, String gender, int age, Float goalWeight) {
                userProfileResult post = new userProfileResult(email,
                        height,weight,activityLevel,gender,age,goalWeight);


                Call<userProfileResult> call =retrofitInterface.createUserProfile(post);
                call.enqueue(new Callback<userProfileResult>() {
                    @Override
                    public void onResponse(Call<userProfileResult> call, Response<userProfileResult> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(userProfile.this, response.code(),Toast.LENGTH_SHORT).show();
                        }else{
                            userProfileResult userProfileResult = response.body();
                            Toast.makeText(userProfile.this,
                                    "Succesfully Created Profile", Toast.LENGTH_LONG).show();
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
