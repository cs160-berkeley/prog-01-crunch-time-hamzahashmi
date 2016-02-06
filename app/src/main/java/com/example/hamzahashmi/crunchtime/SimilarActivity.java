package com.example.hamzahashmi.crunchtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SimilarActivity extends AppCompatActivity {

    Exercise exercises[];
    TextView tv0;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    public static final String TAG = "SimActivity";

    public void goHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        exercises = new Exercise[4];
        exercises[0] = new RepsExercise("Push Ups", (float) 100 / (float) 350);
        exercises[1] = new RepsExercise("Sit Ups", (float)100/(float)200);
        exercises[2] = new RepsExercise("Jumping Jacks", (float)100/(float)10);
        exercises[3] = new RepsExercise("Jogging", (float)100/(float)12);
        tv0 = (TextView)findViewById(R.id.tv0);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);


        Intent intent = getIntent();
        String target = intent.getStringExtra(MainActivity.TAG);
        float targetCals = Float.parseFloat(target);
        float cals = (exercises[0].targetCalories(targetCals));
        Log.d(TAG, Float.toString(cals));
        tv0.setText(Float.toString(cals));

        cals = (exercises[1].targetCalories(targetCals));
        Log.d(TAG, Float.toString(cals));
        tv1.setText(Float.toString(cals));

        cals = (exercises[2].targetCalories(targetCals));
        Log.d(TAG, Float.toString(cals));
        tv2.setText(Float.toString(cals) + " mins");

        cals = (exercises[3].targetCalories(targetCals));
        Log.d(TAG, Float.toString(cals));
        tv3.setText(Float.toString(cals) + " mins");






    }

}
