package com.example.classtinder;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;

public class ProgramSelection extends Activity implements View.OnClickListener {
    int selectedProgram = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    }
    @Override
    public void onClick(View v) {
        TextView progSelection = findViewById(R.id.progSelection);
        switch (v.getId()) {
            case R.id.CompSciButton:
                selectedProgram = 1;
                progSelection.setText("Computer Science Selected");
                break;

            case R.id.UXButton:
                selectedProgram = 2;
                progSelection.setText("UX design Selected");
                break;

            case R.id.MedicalButton:
                selectedProgram = 3;
                progSelection.setText("Medical Sci Selected");
                break;

            case R.id.AFMButton:
                selectedProgram = 4;
                progSelection.setText("AFM Selected");
                break;

            case R.id.BiologyButton:
                selectedProgram = 5;
                progSelection.setText("Biology Selected");
                break;

            case R.id.SwipePage:
                SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("selected_program", selectedProgram);
                editor.commit();
                Intent intent = new Intent(ProgramSelection.this, Swipe.class);
                startActivity(intent);
                break;


            default:
                break;
        }
    }


}
