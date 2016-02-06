package com.example.hamzahashmi.crunchtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        TextView totalExer;
        TextView repsOrMins;
        TextView totalCals;
        EditText amtExercised;
        EditText num;
        GridView grid;
        SeekBar seek;
        public static final String TAG = "MyActivity";
        Exercise currentExer;

    public  void similar(View v){
        //when similar button is clicked, present the similar activity.
        Intent intent = new Intent(this, SimilarActivity.class); //import, change String.class
        intent.putExtra(TAG, totalCals.getText());
        startActivity(intent);

    }

    public void target(View v){
        Intent intent = new Intent(this, GoalActivity.class); //import, change String.class
        startActivity(intent);


    }
    public void updateExerciseAmt(){
        if(currentExer != null && amtExercised.getText().toString().length() >0){
            totalExer.setText(amtExercised.getText());
            RepsExercise reps = (RepsExercise) currentExer;
            Integer inti = Integer.parseInt(amtExercised.getText().toString());
            totalCals.setText(Float.toString(reps.repsToCalories(inti)));

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        totalExer = (TextView) findViewById(R.id.amt);
        totalCals = (TextView) findViewById(R.id.total);
        repsOrMins = (TextView) findViewById(R.id.unit);
        amtExercised = (EditText) findViewById(R.id.exercisedAmt);
        final Button jog = (Button) findViewById(R.id.jog);
        final Button jj = (Button) findViewById(R.id.jj);
        final Button situp = (Button) findViewById(R.id.su);
        final Button button = (Button) findViewById(R.id.pu);


        amtExercised.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    Log.d(TAG, TAG);
                    if (currentExer != null && amtExercised.getText().toString().length() > 0) {
                        totalExer.setText(amtExercised.getText());
                        RepsExercise reps = (RepsExercise) currentExer;
                        Integer inti = Integer.parseInt(amtExercised.getText().toString());
                        totalCals.setText(Float.toString(reps.repsToCalories(inti)));

                    }
                    else if (amtExercised.getText().toString().length() == 0){
                        int ttl = 0;
                        totalCals.setText(Integer.toString(ttl));
                        totalExer.setText(Integer.toString(ttl));
                    }
                }
                return false;
            }
        });




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float proportion = (float) 100 / (float) 350;
                currentExer = new RepsExercise("Push Ups", proportion);
                situp.setEnabled(true);
                jog.setEnabled(true);
                jj.setEnabled(true);
                button.setEnabled(false);
                repsOrMins.setText("reps");
                amtExercised.setHint("Enter reps");
                updateExerciseAmt();

            }
        });

        situp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float proportion = (float)100/(float)200;
                currentExer = new RepsExercise("Sit Ups", proportion);
                situp.setEnabled(false);
                jog.setEnabled(true);
                jj.setEnabled(true);
                button.setEnabled(true);
                repsOrMins.setText("reps");
                amtExercised.setHint("Enter reps");
                updateExerciseAmt();



            }
        });

        jj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float proportion = (float)100/(float)10;
                currentExer = new RepsExercise("Jumping Jacks", proportion);
                situp.setEnabled(true);
                jog.setEnabled(true);
                jj.setEnabled(false);
                button.setEnabled(true);
                repsOrMins.setText("mins");
                amtExercised.setHint("Enter minutes");
                updateExerciseAmt();

            }
        });
        jog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float proportion = (float)100/(float)12;
                currentExer = new RepsExercise("Jogging", proportion);
               // seek.setVisibility(View.INVISIBLE);
                situp.setEnabled(true);
                jog.setEnabled(false);
                jj.setEnabled(true);
                button.setEnabled(true);
                repsOrMins.setText("mins");
                amtExercised.setHint("Enter minutes");
                updateExerciseAmt();


            }
        });





        //grid = (GridView)findViewById(R.id.gridView);
//        grid.setAdapter(new GridAdapter(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


class Exercise{


    //able to do all math calculation for reps to calories within this class

    String exerciseName;
    float toCalorie;

    Exercise(String exercise, float toCalorie){
        this.exerciseName = exercise;
        this.toCalorie = toCalorie;
    }

    public float targetCalories(float cal){
        return  cal / toCalorie;
    }





}

class MinutesExerise extends  Exercise{


    MinutesExerise(String exercise, float minutesTo){
        super(exercise, minutesTo);

    }

    public float minutesToCalories(int minutes){
        return this.toCalorie * minutes;

    }

}

class RepsExercise extends  Exercise{


    RepsExercise(String exercise, float repToCalorie){
        super(exercise, repToCalorie);

    }

    public float repsToCalories(int reps){
        return this.toCalorie * reps;

    }



}




//class GridAdapter extends BaseAdapter {
//
//
//    private static final String TAG = "GRID";
//
//    ArrayList<Exercise> exercises;
//    Context context;
//    GridAdapter(Context context){
//        this.context = context;
//        exercises = new ArrayList<Exercise>();
//        String[] exercise = context.getResources().getStringArray(R.array.exercises);
//        for (String name:exercise) {
//            Exercise exer = new Exercise(name);
//            Log.d(TAG,name);
//            exercises.add(exer);
//        }
//    }
//    @Override
//    public int getCount() {
//        return exercises.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return exercises.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View row = inflater.inflate(R.layout.single_item, parent,false);
//        TextView text =  (TextView)row.findViewById(R.id.textView);
//        text.setText(exercises.get(position).exerciseName);
//        row.setBackgroundColor(Color.WHITE);
//
//        return row;
//    }
//}

