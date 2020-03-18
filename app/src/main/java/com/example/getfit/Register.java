package com.example.getfit;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.getfit.Retrofit.RetrofitClient;
import com.example.getfit.Retrofit.RetrofitInterface;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    EditText userName, email, password,confirmPassword;
            //EditText height, weight, activityLevel;
    Button btn_register;
    Retrofit retrofit;
    RetrofitInterface retrofitInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText)findViewById(R.id.editUsername);
        email =(EditText)findViewById(R.id.editEmailId);
        password =(EditText)findViewById(R.id.editPassword);
        confirmPassword=(EditText)findViewById(R.id.confirmPassword);
//        height = (EditText)findViewById(R.id.editHeight);
//        weight = (EditText)findViewById(R.id.editWeight);
//        activityLevel =(EditText)findViewById(R.id.editActivityLevel);

            btn_register =(Button)findViewById(R.id.registerSubmit);


        //Init Service
        retrofit = RetrofitClient.getInstance();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(userName.getText().toString(), email.getText().toString(),password.getText().toString(),
                        confirmPassword.getText().toString());
            }
        });
    }

    private void registerUser(String userName, String email, String password, String confirmPassword) {

        if(TextUtils.isEmpty(userName))
        {
            Toast.makeText(this, "Your Name cannot be null or empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Email Id cannot be null or empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Password cannot be null or empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(confirmPassword))
        {
            Toast.makeText(this, "Confirm Password cannot be null or empty", Toast.LENGTH_SHORT).show();
            return;
        }
       if(!(TextUtils.equals(password,confirmPassword))){
           Toast.makeText(this, "Password and Confirm Password must be same", Toast.LENGTH_SHORT).show();
       }

        HashMap<String, String> map = new HashMap<>();
        map.put("name",userName);
        map.put("email", email);
        map.put("password", password);
        map.put("confirmPassword", confirmPassword);
        Call<Void> call = retrofitInterface.executeSignup(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    Toast.makeText(Register.this,
                            "Signed up successfully", Toast.LENGTH_LONG).show();
                } else if (response.code() == 400) {
                    Toast.makeText(Register.this,
                            "Already registered", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Register.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();

            }
        });

    }
}
