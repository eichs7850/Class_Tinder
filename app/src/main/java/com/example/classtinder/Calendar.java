package com.example.classtinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class Calendar extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calander);

        Button calendarBackButton = findViewById(R.id.calendarBackButton);
        View calendarTimeLine = findViewById(R.id.calendarTimeLine);

        // Used in dynamically creating buttons, to retrieve grid ID for placement and convert the current entry day to get day id value from dayGrids
        final int[] dayGrids = {R.id.Monday, R.id.Tuesday, R.id.Wednesday, R.id.Thursday, R.id.Friday};
        Hashtable<String, Integer> dayGridGetter = new Hashtable<String, Integer>();
        dayGridGetter.put("Monday", 0);
        dayGridGetter.put("Tuesday", 1);
        dayGridGetter.put("Wednesday", 2);
        dayGridGetter.put("Thursday", 3);
        dayGridGetter.put("Friday", 4);


        //Get the list of courses HashMap
        Map<String, Pair<String[], String[]>> hashMap = Bridge.instance().listOfCourses;
        for (Map.Entry<String, Pair<String[], String[]>> entry : hashMap.entrySet()) {

            //Convert to 24 Hour (if the time starts with 12, conversion isn't necessary)
            if (entry.getValue().first[0].endsWith("m")) {

                //Convert starting time to 24 hour
                if (entry.getValue().first[0].startsWith("12") && entry.getValue().first[0].endsWith("PM") || entry.getValue().first[0].endsWith("pm")){
                    Integer temp = 12;
                    temp = temp + Integer.parseInt(entry.getValue().first[0].substring(0, 2));
                    Log.i("Time conversion check: ", temp.toString() + entry.getValue().first[0].substring(2,5));
                    entry.getValue().first[0] = temp.toString() + entry.getValue().first[0].substring(2,5);
                }

                //Convert ending time to 24 hour
                if (entry.getValue().first[1].startsWith("12") && entry.getValue().first[1].endsWith("PM") || entry.getValue().first[1].endsWith("pm")){
                    Integer temp = 12;
                    temp = temp + Integer.parseInt(entry.getValue().first[1].substring(0, 2));
                    Log.i("Time conversion check: ", temp.toString() + entry.getValue().first[1].substring(2,5));
                    entry.getValue().first[1] = temp.toString() + entry.getValue().first[1].substring(2,5);
                }

                //Calculate the duration of the class & convert to minutes
                String startTime = entry.getValue().first[0].substring(0, 2) + entry.getValue().first[0].substring(3, 5);
                String endTime = entry.getValue().first[1].substring(0, 2) + entry.getValue().first[1].substring(3, 5);
                Integer duration = (Integer.parseInt(endTime) - Integer.parseInt(startTime)); //convert to pixel size

                Integer durationHours = Integer.parseInt(duration.toString().substring(0, 1));
                Integer durationMinutes = duration % 100;
                durationMinutes += durationHours * 60;

                Log.d("Class time start ", entry.getValue().first[0]);
                Log.d("Class time end ", entry.getValue().first[1]);
                Log.d("Class time duration ", duration.toString());
                Log.d("Class time durationHours ", durationHours.toString());
                Log.d("Class time durationMinutes ", durationMinutes.toString());


                //Publish the course to the schedule on all days listed
                for(int i = 0; i < entry.getValue().second.length; i++) {
                    Button tester = new Button(this);
                    tester.setText(entry.getKey());

                    int day = dayGridGetter.get(entry.getValue().second[i]);

                    //get the resource dynamically:
                    RelativeLayout ll = (RelativeLayout)findViewById(dayGrids[day]);
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, (int) (durationMinutes * 3.2));

                    Integer margin = (Integer.parseInt(entry.getValue().first[0].substring(0,2)) - 8) * 168 + (int) ((Integer.parseInt(entry.getValue().first[0].substring(3,5))) * 2.8) + 143; //149 is the default pixel size of the first grid from 7-8am

                    lp.setMargins(0,margin,0,0);
                    ll.addView(tester, lp);

                }
            }


            Log.d("iterating key", entry.getKey()); //Class title
            Log.d("iterating other value", entry.getValue().second[0]);
        }
        //good luck chandler my code is shit -> love you too buddy <3

        // Set the placement of the calendar time view
        Date currentTime = new Date();
        Integer hour = currentTime.getHours();
        Log.d("Current Hour ", hour.toString());
        hour = (hour-7)*60;
        Integer min = currentTime.getMinutes();
        Log.d("Current Minute ", min.toString());
        Integer calendarLineSetValue = (int) ((hour + min) * 2.8);
        Log.d("Current Time in Minute to Pixels ", calendarLineSetValue.toString());

        LinearLayout.LayoutParams newParameters = (LinearLayout.LayoutParams) calendarTimeLine.getLayoutParams();
        newParameters.setMargins(newParameters.leftMargin, calendarLineSetValue, newParameters.rightMargin, newParameters.bottomMargin);
        calendarTimeLine.setLayoutParams(newParameters);

        calendarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Calendar.this, Home.class);
                startActivity(intent);
            }
        });
    }


}
