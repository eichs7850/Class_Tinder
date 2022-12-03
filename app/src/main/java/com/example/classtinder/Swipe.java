package com.example.classtinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Swipe extends Activity {
    private ArrayList<CourseModal> courseModalArrayList;
    Map<String, Pair<String[], String[]>> listOfCourses = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe);

        SharedPreferences sharedPreferences = getSharedPreferences("your_prefs", Context.MODE_PRIVATE);

        int program = sharedPreferences.getInt("selected_program", -1);
        courseModalArrayList = new ArrayList<>();
        SwipeDeck cardStack = findViewById(R.id.swipe_deck);

        if(program == -1 || program == 0) {
            Toast.makeText(Swipe.this, "No Program Selected, Sending to Program Selection", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Swipe.this, ProgramSelection.class);
            startActivity(intent);
        }

        if (program == 1) {
            courseModalArrayList = loadCSCourses(courseModalArrayList);
        }

        if (program == 2) {
            courseModalArrayList = loadUXCourses(courseModalArrayList);
        }

        if (program == 3) {
            courseModalArrayList = loadMedCourses(courseModalArrayList);
        }

        if (program == 4) {
            courseModalArrayList = loadAFMCourses(courseModalArrayList);
        }

        if (program == 5) {
            courseModalArrayList = loadBioCourses(courseModalArrayList);
        }

        final DeckAdapter adapter = new DeckAdapter(courseModalArrayList, this);

        cardStack.setAdapter(adapter);

        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                Toast.makeText(Swipe.this, "Course Denied", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardSwipedRight(int position) {
                Toast.makeText(Swipe.this, "Course Added", Toast.LENGTH_SHORT).show();
                listOfCourses.put(courseModalArrayList.get(position).getCourseName(), new Pair<>(courseModalArrayList.get(position).getCourseDays(), courseModalArrayList.get(position).getCourseTimes()));

            }

            @Override
            public void cardClicked(int position) {
                Toast.makeText(Swipe.this, "Course Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardsDepleted() {
                // this method is called when no card is present
                Toast.makeText(Swipe.this, "No more courses present", Toast.LENGTH_SHORT).show();
                Bridge.instance().listOfCourses = listOfCourses;
                Intent intent = new Intent(Swipe.this, Calendar.class);
                startActivity(intent);
            }

        });

    }

    private static ArrayList<CourseModal> loadCSCourses(ArrayList<CourseModal> courseModalArrayList) {
        courseModalArrayList.add(new CourseModal("MA238", "Hardest math course", new String[] {"Monday", "Wednesday"}, new String[] {"02:30pm", "03:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("CP470", "Best android course", new String[] {"Monday", "Wednesday"}, new String[] {"04:30", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("CP104", "I miss David Brown", new String[] {"Wednesday"}, new String[] {"02:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("CP312", "Software Engineering", new String[] {"Tuesday", "Thursday"}, new String[] {"10:30am", "11:50am"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("CP212", "Windows App Programming", new String[] {"Friday"}, new String[] {"09:00am", "12:00pm"}, R.drawable.laurierlogo));
        return courseModalArrayList;
    }

    private static ArrayList<CourseModal> loadUXCourses(ArrayList<CourseModal> courseModalArrayList) {
        courseModalArrayList.add(new CourseModal("UX340", "Understanding the human computer interaction", new String[] {"Monday", "Wednesday", "Friday"}, new String[] {"08:00am", "8:50am"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("UX118", "Thinking like a designer in the real world", new String[] {"Monday", "Wednesday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("UX458", "Researching the artificial mind", new String[] {"Tuesday", "Thursday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("UX403", "Designing products for businesses", new String[] {"Tuesday", "Thursday"}, new String[] {"07:30pm", "08:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("UX285", "Human meets Artificial Intelligence", new String[] {"Friday"}, new String[] {"4:30pm", "7:30pm"}, R.drawable.laurierlogo));
        return courseModalArrayList;
    }

    private static ArrayList<CourseModal> loadMedCourses(ArrayList<CourseModal> courseModalArrayList) {
        courseModalArrayList.add(new CourseModal("HE305", "Positive Psychology", new String[] {"Monday", "Wednesday", "Friday"}, new String[] {"08:00am", "8:50am"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("HE100", "Health Issues I", new String[] {"Monday", "Wednesday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("HE101", "Health Issues II", new String[] {"Tuesday", "Thursday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("HE300", "Epidemiology", new String[] {"Tuesday", "Thursday"}, new String[] {"07:30pm", "08:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("HN210", "Human Anatomy", new String[] {"Friday"}, new String[] {"4:30pm", "7:30pm"}, R.drawable.laurierlogo));
        return courseModalArrayList;
    }

    private static ArrayList<CourseModal> loadAFMCourses(ArrayList<CourseModal> courseModalArrayList) {
        courseModalArrayList.add(new CourseModal("BU127", "Introduction to Financial Accounting", new String[] {"Monday", "Wednesday", "Friday"}, new String[] {"08:00am", "8:50am"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BU233", "Personal Finance", new String[] {"Monday", "Wednesday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BU283", "Financial Management I", new String[] {"Tuesday", "Thursday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BU387", "Intermediate Accounting I", new String[] {"Tuesday", "Thursday"}, new String[] {"07:30pm", "08:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BU410", "Advanced Equity Analysis", new String[] {"Friday"}, new String[] {"4:30pm", "7:30pm"}, R.drawable.laurierlogo));
        return courseModalArrayList;
    }

    private static ArrayList<CourseModal> loadBioCourses(ArrayList<CourseModal> courseModalArrayList) {
        courseModalArrayList.add(new CourseModal("BI236", "Cell Biology", new String[] {"Monday", "Wednesday", "Friday"}, new String[] {"08:00am", "8:50am"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BI326", "Bioinformatics", new String[] {"Monday", "Wednesday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BI475", "Microbial Ecology", new String[] {"Tuesday", "Thursday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BI111", "Biological Diversity and Evolution", new String[] {"Tuesday", "Thursday"}, new String[] {"07:30pm", "08:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BI266", "Life on Earth: Plants", new String[] {"Friday"}, new String[] {"4:30pm", "7:30pm"}, R.drawable.laurierlogo));
        return courseModalArrayList;
    }
}

