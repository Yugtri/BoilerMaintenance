package com.example.boilermaintenance;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Sensor extends AppCompatActivity  implements View.OnClickListener  {

    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

    }

    public void onClick(View view) {
        //if logout is pressed
        if (view == button) {
            startActivity(new Intent(this, Temperature.class));
        }
        if (view == button2) {
            startActivity(new Intent(this, Gas.class));
        }
        if (view == button3) {
            startActivity(new Intent(this, DrawGraph.class));
        }
        if (view == button4) {
            startActivity(new Intent(this, DrawGraph.class));
        }
    }
}
