package com.example.getfit;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.getfit.Retrofit.LoginResult;
import com.example.getfit.Retrofit.RetrofitInterface;
import com.example.getfit.Retrofit.RetrofitClient;

import java.util.HashMap;

import static android.widget.Toast.LENGTH_SHORT;


public class login extends AppCompatActivity {
    EditText editMailId, editPassword;
    Button btnLogin;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    //private static CompositeDisposable compositeDisposable = new CompositeDisposable();
   // RetroFitInterface iMyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //init views
        editMailId = (EditText)findViewById(R.id.editEmailId);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.login);

        //Init Service
        retrofit = RetrofitClient.getInstance();
        retrofitInterface = retrofit.create(RetrofitInterface.class);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(editMailId.getText().toString(),
                        editPassword.getText().toString());

            }
        });


    }

    private void loginUser(final String email, String password) {
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Email cannot be null or empty", LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Password cannot be null or empty", LENGTH_SHORT).show();
            return;
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);

        Call<LoginResult> call = retrofitInterface.executeLogin(map);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.code()  == 200)
                {
                    LoginResult result = response.body();
                    ((MyApplication)login.this.getApplication()).setUserEmail(email);
                    Toast.makeText(login.this,
                            "Welcome to getFit App", Toast.LENGTH_LONG).show();
                    login.this.startActivity(new Intent(login.this, Diet.class));
                    login.this.finish();


                }else if(response.code() == 404)
                {
                   Toast.makeText(login.this, "Wrong Credentials", LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
               Toast.makeText(login.this, t.getMessage(),
                        LENGTH_SHORT).show();
            }
        });

    }

}
