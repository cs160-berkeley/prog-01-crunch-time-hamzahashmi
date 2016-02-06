package com.example.hamzahashmi.crunchtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GoalActivity extends AppCompatActivity {

    EditText goalCals;
    TextView pu;
    TextView su;
    TextView jj;
    TextView run;
    Exercise exercises[];
    int targetCalories;

    public void computeExercise(){
        pu.setText(Integer.toString( (int) Math.ceil(exercises[0].targetCalories(targetCalories))) + " "+ exercises[0].exerciseName);
        su.setText(Integer.toString((int) Math.ceil(exercises[1].targetCalories(targetCalories))) + " " + exercises[1].exerciseName);
        jj.setText(Integer.toString((int) Math.ceil(exercises[2].targetCalories(targetCalories))) + " mins " + exercises[2].exerciseName);
        run.setText(Integer.toString((int) Math.ceil(exercises[3].targetCalories(targetCalories))) + " mins " + exercises[3].exerciseName);

    }

    public void home(View v){
        Intent intent = new Intent(this, MainActivity.class); //import, change String.class
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        goalCals = (EditText) findViewById(R.id.goalcals);
        pu = (TextView) findViewById(R.id.pu);
        su = (TextView) findViewById(R.id.su);
        jj = (TextView) findViewById(R.id.jj);
        run = (TextView) findViewById(R.id.run);
        exercises = new Exercise[4];
        exercises[0] = new RepsExercise("Push Ups", (float) 100 / (float) 350);
        exercises[1] = new RepsExercise("Sit Ups", (float)100/(float)200);
        exercises[2] = new RepsExercise("Jumping Jacks", (float)100/(float)10);
        exercises[3] = new RepsExercise("Jogging", (float)100/(float)12);
        targetCalories = 0;
        goalCals.setOnKeyListener(
                new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                            if (goalCals.getText().toString().length() != 0){
                                String targetCals = goalCals.getText().toString();
                                targetCalories = Integer.parseInt(targetCals);
                            }
                            else{
                                targetCalories = 0;
                            }
                        }
                        computeExercise();
                        return false;
                    }
                }
        );

    }

}
