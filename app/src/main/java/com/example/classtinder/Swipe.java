package com.example.classtinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Swipe extends Activity {
    private SwipeDeck cardStack;
    private ArrayList<CourseModal> courseModalArrayList;
    Map<String, Pair<Double[], String[]>> emptyCourses = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe);
        courseModalArrayList = new ArrayList<>();
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        // on below line we are adding data to our array list.
        courseModalArrayList.add(new CourseModal("C++", "30 days", new String[] {"20 Tracks"}, new String[] {"C++ Self Paced Course"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("Java", "30 days", new String[] {"20 Tracks"}, new String[] {"Java Self Paced Course"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("Python", "30 days", new String[] {"20 Tracks"}, new String[] {"Python Self Paced Course"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("DSA", "30 days", new String[] {"20 Tracks"}, new String[] {"DSA Self Paced Course"}, R.drawable.laurierlogo));

        // on below line we are creating a variable for our adapter class and passing array list to it.
        final DeckAdapter adapter = new DeckAdapter(courseModalArrayList, this);

        // on below line we are setting adapter to our card stack.
        cardStack.setAdapter(adapter);

        // on below line we are setting event callback to our card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                // on card swipe left we are displaying a toast message.
                Toast.makeText(Swipe.this, "Card Swiped Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardSwipedRight(int position) {
                // on card swiped to right we are displaying a toast message.
                Toast.makeText(Swipe.this, "Card Swiped Right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardClicked(int position) {
                Toast.makeText(Swipe.this, "Card clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardsDepleted() {
                // this method is called when no card is present
                Toast.makeText(Swipe.this, "No more courses present", Toast.LENGTH_SHORT).show();
            }

            public void cardActionDown() {
                // this method is called when card is swiped down.
                Log.i("TAG", "CARDS MOVED DOWN");
            }

            public void cardActionUp() {
                // this method is called when card is moved up.
                Log.i("TAG", "CARDS MOVED UP");
            }
        });
        Map<String, Pair<Double[], String[]>> listOfCourses = addCourses(emptyCourses);
        Bridge.instance().listOfCourses = listOfCourses;
//        Intent intent = new Intent(Swipe.this, Calendar.class);
//        startActivity(intent);
    }

    private static Map<String, Pair<Double[], String[]>> addCourses(Map<String, Pair<Double[], String[]>> listOfCourses) {
        listOfCourses.put("MA238", new Pair<>(new Double[]{16.0, 17.5}, new String[]{"Monday", "Wednesday"}) );
        listOfCourses.put("CP322", new Pair<>(new Double[]{12.0, 13.5}, new String[]{"Tuesday", "Thursday"}));
        return listOfCourses;
    }
}
