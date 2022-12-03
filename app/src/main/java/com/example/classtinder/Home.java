package com.example.classtinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //Button calendarButton = findViewById(R.id.calendarbtn);
        Button courseSelectionButton = findViewById(R.id.choosebtn);
        Button reselectProgramButton = findViewById(R.id.repickbtn);
        Button logOutButton = findViewById(R.id.Logoutbtn);

        /* calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Calendar.class);
                startActivity(intent);
            }
        });*/

        courseSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Swipe.class);
                startActivity(intent);
            }
        });

        reselectProgramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, ProgramSelection.class);
                startActivity(intent);
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
