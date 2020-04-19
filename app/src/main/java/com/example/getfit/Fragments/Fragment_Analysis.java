package com.example.getfit.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.getfit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Analysis extends Fragment {
    Button increment, decrement;
    TextView  countText;
    static  int count =0;


    public Fragment_Analysis() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__analysis, container, false);
        increment = (Button)view.findViewById(R.id.increment);
        decrement = (Button)view.findViewById(R.id.decrement);
        countText = (TextView)view.findViewById(R.id.tvCount);

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                countText.setText(String.valueOf(count));
            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count --;
                countText.setText(String.valueOf(count));
            }
        });
        return view;

    }

}
