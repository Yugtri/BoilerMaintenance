package com.example.boilermaintenance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.*;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private Button sensor;

    //view objects
    private EditText name, location;
    private TextView textViewUserEmail;
    private Button buttonLogout, save, inventory;
    private DatabaseReference databaseReference;
    private DatabaseReference nameV;
    private DatabaseReference locationV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        save = (Button) findViewById(R.id.save);
        name = (EditText) findViewById(R.id.name);
        inventory = (Button) findViewById(R.id.inventory);
        location = (EditText) findViewById(R.id.location);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        locationV = databaseReference.child("NAME");
        nameV = databaseReference.child("LOCATION");
        //displaying logged in user name
        textViewUserEmail.setText("Welcome " + user.getEmail());
        sensor = (Button) findViewById(R.id.sensor);
        //adding listener to button
        buttonLogout.setOnClickListener(this);
        save.setOnClickListener(this);
        inventory.setOnClickListener(this);
        sensor.setOnClickListener(this);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserInformation post = dataSnapshot.getValue(UserInformation.class);
                System.out.println(post.location);
                Toast.makeText(getApplicationContext(), post.name, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }


        });
    }

    @Override
    public void onClick(View view) {
        //if logout is pressed
        if (view == buttonLogout) {
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (view == sensor) {
            startActivity(new Intent(this, Sensor.class));
        }
        if (view == save) {
            saveInformation();
        }
        if (view == inventory) {
            startActivity(new Intent(this, Inventory.class));
        }
    }

    private void saveInformation() {
        String n1 = name.getText().toString().trim();
        String l1 = location.getText().toString().trim();
        UserInformation userInformation = new UserInformation(n1, l1);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Log.v("Dataa_Sayeeeeeeee", userInformation.toString());
        locationV.setValue(n1);
        nameV.setValue(l1);
        //databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(getApplicationContext(), "Saved Values", Toast.LENGTH_LONG).show();
    }
}
