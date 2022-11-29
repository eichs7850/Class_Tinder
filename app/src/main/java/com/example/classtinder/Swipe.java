package com.example.classtinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Pair;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Swipe extends Activity {
    Map<String, Pair<Double[], String[]>> emptyCourses = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe);
        Map<String, Pair<Double[], String[]>> listOfCourses = addCourses(emptyCourses);
        Bridge.instance().listOfCourses = listOfCourses;
        Intent intent = new Intent(Swipe.this, Calendar.class);
        startActivity(intent);
    }

    private static Map<String, Pair<Double[], String[]>> addCourses(Map<String, Pair<Double[], String[]>> listOfCourses) {
        listOfCourses.put("MA238", new Pair<>(new Double[]{16.0, 17.5}, new String[]{"Monday", "Wednesday"}) );
        listOfCourses.put("CP322", new Pair<>(new Double[]{12.0, 13.5}, new String[]{"Tuesday", "Thursday"}));
        return listOfCourses;
    }
}
