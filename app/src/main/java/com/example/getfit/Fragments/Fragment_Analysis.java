package com.example.getfit.Fragments;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.getfit.MyApplication;
import com.example.getfit.R;

import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.getSystemServiceName;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Analysis extends Fragment {

    private NotificationManagerCompat notificationManager;
    Button increment, decrement;
    TextView  countText;
      int count=0;
    //Context context;

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

       // context = getActivity();

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;

                if(count >=5 )
               {
                    notifyUser();

                    Log.d(String.valueOf(count), "onClick: ");
               }
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
    public void notifyUser() {

        String message = "Great Work!! You  drank  " + count +  "amount of Water Today";
        notificationManager = NotificationManagerCompat.from(getActivity());
        Notification notification = new NotificationCompat.Builder(getActivity(), MyApplication.Channel_1_ID)
                .setSmallIcon(R.drawable.message)
                .setContentTitle("Stay Healthy and Hydrated")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(
//                getActivity()
//        )
//                .setSmallIcon(R.drawable.message)
//                .setContentTitle("New Notification")
//                .setContentText(message)
//                .setAutoCancel(true);

                /*Intent notificationIntent = new Intent(getActivity() , getActivity());
                PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(contentIntent);*/

//        NotificationManager notification = ( NotificationManager ) getActivity().getSystemService( getActivity().NOTIFICATION_SERVICE );
//        notification.notify(1,builder.build());

        notificationManager.notify(1,notification);

    }
}
