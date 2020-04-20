package com.example.getfit.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.getfit.Diet;
import com.example.getfit.DietPlanSelected;
import com.example.getfit.R;
import com.example.getfit.Fragments.dummy.DummyContent;
import com.example.getfit.Fragments.dummy.DummyContent.DummyItem;

import java.util.List;


public class Fragment_dietPlan extends Fragment {

    Button Continue;
    LinearLayout llregular, llstrength, llweightgain, llweightloss;
    private DietPlanSelected fragment_dietPlanSelected;

    // TODO: Customize parameter argument names
   // private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    //private int mColumnCount = 1;
    //private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public Fragment_dietPlan() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
   /* public static Fragment_dietPlan newInstance(int columnCount) {
        Fragment_dietPlan fragment = new Fragment_dietPlan();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }*/

   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        Continue = view.findViewById(R.id.btn_continue);
        llregular = view.findViewById(R.id.LLRegularplan);
        llstrength = view.findViewById(R.id.LLStrengthplan);
        llweightgain = view.findViewById(R.id.LLWeightgainplan);
        llweightloss = view.findViewById(R.id.LLweightLossPlan);

        fragment_dietPlanSelected =new DietPlanSelected();

        //getSupportFragmentManager().beginTransaction().replace(R.id.mainframeLayout,fragmentHome).commit();

        llregular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llregular.setBackgroundColor(Color.parseColor("#FFB6C1"));

                String plan = "RegularPlan";
                ((Diet)getActivity()).DietPlanSelected(plan);
            }
        });

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.dietPlan, fragment_dietPlanSelected);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
                String plan = "RegularPlan";
                ((Diet)getActivity()).DietPlanSelected(plan);

            }
        });
       /* // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }*/
        return view;
    }

/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }*/

   /* @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
  /*  public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }*/


}
