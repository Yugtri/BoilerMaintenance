package com.example.boilermaintenance;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.Integer.parseInt;

public class Ultrasonic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultra);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();//boilermaintenance-17ea4.firebaseio.com/sensor/");
        Query query = reference.child("sensor").child("dist");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value;
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                        // do something with the individual "issues"
                        TextView textview = (TextView) findViewById(R.id.textView2);
                        TextView textview2 = (TextView) findViewById(R.id.textView7);
//                       //                        String[] value = {""}; //declaration of the array
                        String val1 = "";
                        String val2 = "";

                        try {
                            for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                                Object object = messageSnapshot.getValue(Object.class);
                                String json = new Gson().toJson(object);
                                JSONObject jsonObject = new JSONObject(json);
                                val1 += jsonObject.get("val").toString() + "\n";
                                val2 += jsonObject.get("time").toString() + "\n";
                                textview.setText(val1);
                                value = parseInt(jsonObject.get("val").toString());
                                Log.v("UltraSensor", String.valueOf(value));
                                textview2.setText(val2);
                                if (value>95)
                                {
                                    sendNotification(dataSnapshot);
                            }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    void sendNotification(DataSnapshot issue)
    {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "default")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle("Check Distance!") // title for notification
                .setContentText("Boiler " + issue.getKey() + "water level high" )// message for notification
                // set alarm sound for notification
                .setAutoCancel(true); // clear notification after click
            mNotificationManager.notify(0, mBuilder.build());
//        Toast.makeText(getApplicationContext(), issue.toString() + "hey", Toast.LENGTH_LONG).show();


    }

}

