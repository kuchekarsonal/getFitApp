package com.example.getfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.getfit.Retrofit.RetroFitInterface;
import com.example.getfit.Retrofit.RetrofitClient;

public class login extends AppCompatActivity {
    EditText editMailId, editPassword;
    Button btnLogin;
    private   Retrofit retrofitClient

    private static CompositeDisposable compositeDisposable = new CompositeDisposable();
    RetroFitInterface iMyService;


    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Init Service
        retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(RetroFitInterface.class);

        //init views
        editMailId = (EditText)findViewById(R.id.editEmailId);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btn_login);

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
            Toast.makeText(this, "Email cannot be null or empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Password cannot be null or empty", Toast.LENGTH_SHORT).show();
            return;
        }

       compositeDisposable.add(iMyService.loginUser(email, password)) //error here
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<String>() {
                   @Override
                   public void accept(String s) {
                       Toast.makeText(login.this, " "+ s, Toast.LENGTH_SHORT).show();
                   }
               });

    }

}
