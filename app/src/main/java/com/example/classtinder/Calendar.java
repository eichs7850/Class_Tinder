package com.example.classtinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Calendar extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calander);

        Button calendarBackButton = findViewById(R.id.calendarBackButton);
        View calendarTimeLine = findViewById(R.id.calendarTimeLine);

        //Get the list of courses HashMap

        Map<String, Pair<Double[], String[]>> hashMap = Bridge.instance().listOfCourses;
        Log.d("lol", hashMap.toString());
        Double[] classTimes = hashMap.get("MA238").first;
        String[] classDays = hashMap.get("MA238").second;
        Log.d("MA238 Times", Arrays.toString(classTimes));
        Log.d("MA238 Days", Arrays.toString(classDays));
        //good luck chandler my code is shit

        // Set the placement of the calendar time view
        Date currentTime = new Date();
        int hour = currentTime.getHours();
        hour = (hour-7)*60;
        int min = currentTime.getMinutes();
        int calendarLineSetValue = hour + min;

        LinearLayout.LayoutParams newParameters = (LinearLayout.LayoutParams) calendarTimeLine.getLayoutParams();
        newParameters.setMargins(newParameters.leftMargin, calendarLineSetValue, newParameters.rightMargin, newParameters.bottomMargin);
        calendarTimeLine.setLayoutParams(newParameters);

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "The time isnt working properly, work in progress...", Snackbar.LENGTH_LONG);
        snackbar.show();

        calendarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Calendar.this, Home.class);
                startActivity(intent);
            }
        });
    }
}
