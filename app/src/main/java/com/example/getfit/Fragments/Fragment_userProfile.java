package com.example.getfit.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getfit.MyApplication;
import com.example.getfit.R;
import com.example.getfit.Retrofit.MeResult;
import com.example.getfit.Retrofit.RetrofitClient;
import com.example.getfit.Retrofit.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_userProfile extends Fragment {
    private TextView nameTextView;
    private EditText weightEditText;
    private  EditText heightEditText;
    private  EditText activityEditText;
    private  EditText ageEditText;
    private  EditText goalWeightEditText;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private Button buttonUpdate;
    private String email;
    public Fragment_userProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_fragment_user_profile, container, false);
        nameTextView = view.findViewById(R.id.name_id);
        weightEditText = view.findViewById(R.id.weight_id);
        heightEditText = view.findViewById(R.id.height_id);
        activityEditText = view.findViewById(R.id.activity_id);
        ageEditText = view.findViewById(R.id.age_id);
        goalWeightEditText = view.findViewById(R.id.goal_id);
        buttonUpdate = view.findViewById(R.id.button_id);
        email = ((MyApplication)getActivity().getApplication()).getUserEmail();


        //Init Service
        retrofit = RetrofitClient.getInstance();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<MeResult> call = retrofitInterface.getPosts(((MyApplication)getActivity().getApplication()).getUserEmail());
        call.enqueue(new Callback<MeResult>() {
            @Override
            public void onResponse(Call<MeResult> call, Response<MeResult> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "error occured",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    MeResult p = response.body();
                    Log.d("Fragment User Profile",p.getName());
                    nameTextView.setText(p.getName());
                    weightEditText.setText( String.valueOf(p.getCurrentWeight()));
                    goalWeightEditText.setText( String.valueOf(p.getGoalWeight()));
                    heightEditText.setText(String.valueOf(p.getHeight()));
                    ageEditText.setText(String.valueOf(p.getAge()));
                    activityEditText.setText( String.valueOf(p.getActivityLevel()));

                }

            }

            @Override
            public void onFailure(Call<MeResult> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }

        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameTextView.getText().toString();
                float weight = Float.valueOf(weightEditText.getText().toString());
                float goalWeight = Float.valueOf(goalWeightEditText.getText().toString());
                float height = Float.valueOf(heightEditText.getText().toString());
                int age = Integer.valueOf(ageEditText.getText().toString());
                String activity = activityEditText.getText().toString();
                MeResult result = new MeResult(name, age, activity, weight,goalWeight, height);

                Call<Void> updateCall = retrofitInterface.getUpdatedProfile(email, result);
                updateCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getActivity(), "error occured",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getActivity(), "Details Updated Successfully",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
