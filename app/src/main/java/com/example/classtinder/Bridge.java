package com.example.classtinder;

import android.util.Pair;

import java.util.Map;

public class Bridge
{
    private Bridge() {
    }

    static Bridge obj = null;
    public static Bridge instance()
    {
        if (obj == null)
            obj = new Bridge();
        return obj;
    }

    public Map<String, Pair<String[], String[]>> listOfCourses;

}