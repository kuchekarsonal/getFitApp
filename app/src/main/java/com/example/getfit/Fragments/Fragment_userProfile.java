package com.example.getfit.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private TextView weightTextView;
    private  TextView heightTextView;
    private  TextView activityTextView;
    private  TextView ageTextView;
    private  TextView goalWeightTextView;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    public Fragment_userProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_fragment_user_profile, container, false);
        nameTextView = view.findViewById(R.id.name_id);
        weightTextView = view.findViewById(R.id.weight_id);
        heightTextView = view.findViewById(R.id.height_id);
        activityTextView = view.findViewById(R.id.activity_id);
        ageTextView = view.findViewById(R.id.age_id);
        goalWeightTextView = view.findViewById(R.id.goal_id);

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
                    weightTextView.setText( String.valueOf(p.getCurrentWeight()));
                    goalWeightTextView.setText( String.valueOf(p.getGoalWeight()));
                    heightTextView.setText(String.valueOf(p.getHeight()));
                    ageTextView.setText(String.valueOf(p.getAge()));
                    activityTextView.setText( String.valueOf(p.getActivityLevel()));

                }

            }

            @Override
            public void onFailure(Call<MeResult> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
