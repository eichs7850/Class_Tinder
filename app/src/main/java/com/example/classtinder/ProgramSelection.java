package com.example.classtinder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;


public class ProgramSelection extends Activity implements View.OnClickListener {
    int selectedProgram = 0;
    public int[] selectedPrograms = {0,0};

    boolean doubleDegree = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //I THOUGHT THIS WOULD MAKE MY LAYOUT CLEANER!
        setContentView(R.layout.programselection);
        Button one = (Button) findViewById(R.id.CompSciButton);
        one.setOnClickListener(this);
        Button two = (Button) findViewById(R.id.UXButton);
        two.setOnClickListener(this);
        Button three = (Button) findViewById(R.id.MedicalButton);
        three.setOnClickListener(this);
        Button four = (Button) findViewById(R.id.AFMButton);
        four.setOnClickListener(this);
        Button five = (Button) findViewById(R.id.BiologyButton);
        five.setOnClickListener(this);
        Button swipe = (Button) findViewById(R.id.SwipePage);
        swipe.setOnClickListener(this);
        Button six = (Button) findViewById(R.id.buisnessBtn);
        six.setOnClickListener(this);
        Button seven = (Button) findViewById(R.id.PsychButton);
        seven.setOnClickListener(this);
        Button eight = (Button) findViewById(R.id.dataScienceBtn);
        eight.setOnClickListener(this);
        Button nine = (Button) findViewById(R.id.musicBtn);
        nine.setOnClickListener(this);
        Button ten = (Button) findViewById(R.id.eduBtn);
        ten.setOnClickListener(this);
        Button eleven = (Button) findViewById(R.id.baguetteBtn);
        eleven.setOnClickListener(this);
        Button twelve = (Button) findViewById(R.id.filmBtn);
        twelve.setOnClickListener(this);
        Button thirteen = (Button) findViewById(R.id.strudelBtn);
        thirteen.setOnClickListener(this);
        Button fourteen = (Button) findViewById(R.id.crewritinBtn);
        fourteen.setOnClickListener(this);
        Button fifteen = (Button) findViewById(R.id.financeBtn);
        fifteen.setOnClickListener(this);
        Button sixteen = (Button) findViewById(R.id.historyBtn);
        sixteen.setOnClickListener(this);
        Button seventeen = (Button) findViewById(R.id.gloStudies);
        seventeen.setOnClickListener(this);
        Button eighteen = (Button) findViewById(R.id.kinBtn);
        eighteen.setOnClickListener(this);
        Button back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(this);
        Button help = (Button) findViewById(R.id.helpBtn);
        help.setOnClickListener(this);
        CheckBox dblDeg = (CheckBox) super.findViewById(R.id.isDoubleDegree);
        dblDeg.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        TextView progSelection = findViewById(R.id.progSelection);
        //Why hello there, my name is YandreDev
        switch (v.getId()) {
            case R.id.CompSciButton:
                shift(selectedPrograms, 1);
                selectedProgram = 1;
                progSelection.setText("Computer Science Selected");
                break;

            case R.id.UXButton:
                shift(selectedPrograms, 2);
                progSelection.setText("UX design Selected");
                break;

            case R.id.MedicalButton:
                shift(selectedPrograms, 3);
                progSelection.setText("Medical Sci Selected");
                break;

            case R.id.AFMButton:
                shift(selectedPrograms, 4);
                progSelection.setText("AFM Selected");
                break;

            case R.id.BiologyButton:
                shift(selectedPrograms, 5);
                progSelection.setText("Biology Selected");
                break;

            case R.id.PsychButton:
                shift(selectedPrograms, 6);
                progSelection.setText("Psych Selected");
                break;

            case R.id.buisnessBtn:
                shift(selectedPrograms, 7);
                progSelection.setText("Buisness Selected");
                break;

            case R.id.dataScienceBtn:
                shift(selectedPrograms, 8);
                progSelection.setText("Data Sci Selected");
                break;

            case R.id.musicBtn:
                shift(selectedPrograms, 9);
                progSelection.setText("Biology Selected");
                break;

            case R.id.eduBtn:
                shift(selectedPrograms, 10);
                progSelection.setText("Education Selected");
                break;

            case R.id.baguetteBtn:
                shift(selectedPrograms, 11);
                progSelection.setText("French Selected");
                break;

            case R.id.filmBtn:
                shift(selectedPrograms, 12);
                progSelection.setText("Film Selected");
                break;

            case R.id.strudelBtn:
                shift(selectedPrograms, 13);
                progSelection.setText("German Selected");
                break;

            case R.id.crewritinBtn:
                shift(selectedPrograms, 14);
                progSelection.setText("Creative Writing Selected");
                break;

            case R.id.financeBtn:
                shift(selectedPrograms, 15);
                progSelection.setText("Finance Selected");
                break;

            case R.id.historyBtn:
                shift(selectedPrograms, 16);
                progSelection.setText("Biology Selected");
                break;

            case R.id.gloStudies:
                shift(selectedPrograms, 17);
                progSelection.setText("Global Studies Selected");
                break;

            case R.id.kinBtn:
                shift(selectedPrograms, 18);
                progSelection.setText("Kinematics Selected");
                break;

            case R.id.backButton:
                Intent i = new Intent(ProgramSelection.this, Home.class);
                startActivity(i);
                break;

            case R.id.isDoubleDegree:
                doubleDegree = !doubleDegree;
                if(doubleDegree){
                    Toast.makeText(ProgramSelection.this, "You're a double degree!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProgramSelection.this, "You're not a double degree!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.SwipePage:
                SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("selected_program", selectedPrograms[1]);
                //if double degree
                if(doubleDegree) {
                    editor.putInt("selected_program2", selectedPrograms[0]);
                } else {
                    editor.putInt("selected_program2", -1);
                }
                editor.commit();
                Integer result = (Integer) selectedPrograms[0];
                Integer resulttoo = (Integer) selectedPrograms[1];
                Log.d("course one ", result.toString());
                Log.d("course 2 ", resulttoo.toString());
                Intent intent = new Intent(ProgramSelection.this, Swipe.class);
                startActivity(intent);

                break;

            case R.id.helpBtn:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Help");
                builder.setMessage("Created by Jacob Cabral\nVersion 1.13 \nUse the buttons to select your courses." +
                        " Click the checkbox if you're enrolled in a double degree to pick two!");
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create();
                builder.show();
//            default:
//                break;
        }
    }
    public void shift(int[] arr, int i){
            //Helper method to ensure both values in the array are loaded with course selection variables
        //shift the 'top' of the array to the bottom
        arr[0] = arr[1];
        //load the top in with the new int
        arr[1] = i;
    }

}
