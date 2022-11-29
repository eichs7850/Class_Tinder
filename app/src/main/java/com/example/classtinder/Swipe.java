package com.example.classtinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Swipe extends Activity {
    Map<String, Double[]> emptyCourses = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe);
        Map<String, Double[]> listOfCourses = addCourses(emptyCourses);

        Intent intent = new Intent(Swipe.this, Calendar.class);
        intent.putExtra("listOfCourses", (Serializable) listOfCourses);

        startActivity(intent);
    }

    private static Map<String, Double[]> addCourses(Map<String, Double[]> listOfCourses) {
        // 100.0 = Monday
        // 101.0 = Tuesday
        // 102.0 = Wednesday
        // 103.0 = Thursday
        // 104.0 = Friday
        listOfCourses.put("MA238", new Double[] {16.0, 17.5, 100.0});
        listOfCourses.put("CP322", new Double[] {12.0, 13.5, 101.0});
        return listOfCourses;
    }
}
