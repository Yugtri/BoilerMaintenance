package com.example.boilermaintenance;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.*;
import com.google.firebase.database.*;
import java.util.*;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Inventory extends AppCompatActivity {
    DatabaseReference dref;
    ListView listview;
    ArrayList<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        listview=(ListView)findViewById(R.id.listview);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);
        listview.setAdapter(adapter);
        dref=FirebaseDatabase.getInstance().getReferenceFromUrl("https://boilermaintenance-17ea4.firebaseio.com/inventory");
      //  list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
             
                adapter.add(

                        (String) dataSnapshot.child("unit_cost").getValue());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
               // list.remove(dataSnapshot.getValue(String.class));
                //adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
