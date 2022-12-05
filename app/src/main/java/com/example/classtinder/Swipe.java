package com.example.classtinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

        Integer program = sharedPreferences.getInt("selected_program", -1);
        Integer secondProgram = sharedPreferences.getInt("selected_program2", -1);
        courseModalArrayList = new ArrayList<>();
        SwipeDeck cardStack = findViewById(R.id.swipe_deck);

//        editor.putInt("selected_program", selectedPrograms[1]);
//        //if double degree
//        if(doubleDegree) {
//            editor.putInt("selected_program2", selectedPrograms[0]);
//        } else {
//            editor.putInt("selected_program2", -1);
//        }

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

        if(program == 6) {
            courseModalArrayList = loadPsychCourses(courseModalArrayList);
        }

        if(program == 7) {
            courseModalArrayList = loadBizCourses(courseModalArrayList);
        }

        if(program == 8) {
            courseModalArrayList = loadDataCourses(courseModalArrayList);
        }

        if(program == 9) {
            courseModalArrayList = loadMusicCourses(courseModalArrayList);
        }

        if(program == 10) {
            courseModalArrayList = loadEduCourses(courseModalArrayList);
        }

        if(secondProgram == 1) {
            courseModalArrayList = loadCSCourses(courseModalArrayList);
        }

        if (secondProgram == 2) {
            courseModalArrayList = loadUXCourses(courseModalArrayList);
        }

        if (secondProgram == 3) {
            courseModalArrayList = loadMedCourses(courseModalArrayList);
        }

        if (secondProgram == 4) {
            courseModalArrayList = loadAFMCourses(courseModalArrayList);
        }

        if (secondProgram == 5) {
            courseModalArrayList = loadBioCourses(courseModalArrayList);
        }

        if (secondProgram == 6) {
            courseModalArrayList = loadPsychCourses(courseModalArrayList);
        }

        if (secondProgram == 7) {
            courseModalArrayList = loadBizCourses(courseModalArrayList);
        }

        if (secondProgram == 8) {
            courseModalArrayList = loadDataCourses(courseModalArrayList);
        }

        if (secondProgram == 9) {
            courseModalArrayList = loadMusicCourses(courseModalArrayList);
        }

        if (secondProgram == 10) {
            courseModalArrayList = loadEduCourses(courseModalArrayList);
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
        courseModalArrayList.add(new CourseModal("UX340", "Understanding the human computer interaction", new String[] {"Monday", "Wednesday", "Friday"}, new String[] {"08:00am", "08:50am"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("UX118", "Thinking like a designer in the real world", new String[] {"Monday", "Wednesday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("UX458", "Researching the artificial mind", new String[] {"Tuesday", "Thursday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("UX403", "Designing products for businesses", new String[] {"Tuesday", "Thursday"}, new String[] {"07:30pm", "08:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("UX285", "Human meets Artificial Intelligence", new String[] {"Friday"}, new String[] {"04:30pm", "07:30pm"}, R.drawable.laurierlogo));
        return courseModalArrayList;
    }

    private static ArrayList<CourseModal> loadMedCourses(ArrayList<CourseModal> courseModalArrayList) {
        courseModalArrayList.add(new CourseModal("HE305", "Positive Psychology", new String[] {"Monday", "Wednesday", "Friday"}, new String[] {"08:00am", "08:50am"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("HE100", "Health Issues I", new String[] {"Monday", "Wednesday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("HE101", "Health Issues II", new String[] {"Tuesday", "Thursday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("HE300", "Epidemiology", new String[] {"Tuesday", "Thursday"}, new String[] {"07:30pm", "08:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("HN210", "Human Anatomy", new String[] {"Friday"}, new String[] {"04:30pm", "07:30pm"}, R.drawable.laurierlogo));
        return courseModalArrayList;
    }

    private static ArrayList<CourseModal> loadAFMCourses(ArrayList<CourseModal> courseModalArrayList) {
        courseModalArrayList.add(new CourseModal("BU127", "Introduction to Financial Accounting", new String[] {"Monday", "Wednesday", "Friday"}, new String[] {"08:00am", "08:50am"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BU233", "Personal Finance", new String[] {"Monday", "Wednesday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BU283", "Financial Management I", new String[] {"Tuesday", "Thursday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BU387", "Intermediate Accounting I", new String[] {"Tuesday", "Thursday"}, new String[] {"07:30pm", "08:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BU410", "Advanced Equity Analysis", new String[] {"Friday"}, new String[] {"04:30pm", "07:30pm"}, R.drawable.laurierlogo));
        return courseModalArrayList;
    }

    private static ArrayList<CourseModal> loadBioCourses(ArrayList<CourseModal> courseModalArrayList) {
        courseModalArrayList.add(new CourseModal("BI236", "Cell Biology", new String[] {"Monday", "Wednesday", "Friday"}, new String[] {"08:00am", "08:50am"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BI326", "Bioinformatics", new String[] {"Monday", "Wednesday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BI475", "Microbial Ecology", new String[] {"Tuesday", "Thursday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BI111", "Biological Diversity and Evolution", new String[] {"Tuesday", "Thursday"}, new String[] {"07:30pm", "08:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BI266", "Life on Earth: Plants", new String[] {"Friday"}, new String[] {"04:30pm", "07:30pm"}, R.drawable.laurierlogo));
        return courseModalArrayList;
    }

    private static ArrayList<CourseModal> loadPsychCourses(ArrayList<CourseModal> courseModalArrayList) {
        courseModalArrayList.add(new CourseModal("PS101", "Introduction to Psychology I", new String[] {"Monday", "Wednesday", "Friday"}, new String[] {"08:00am", "08:50am"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("PS102", "Introduction to Psychology II", new String[] {"Monday", "Wednesday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("PS260", "Introduction to Cognitive Psychology", new String[] {"Tuesday", "Thursday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("PS280", "Abnormal Psychology", new String[] {"Tuesday", "Thursday"}, new String[] {"07:30pm", "08:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("PS295", "Introduction to Research Methods", new String[] {"Friday"}, new String[] {"04:30pm", "07:30pm"}, R.drawable.laurierlogo));
        return courseModalArrayList;
    }

    private static ArrayList<CourseModal> loadBizCourses(ArrayList<CourseModal> courseModalArrayList) {
        courseModalArrayList.add(new CourseModal("BU111", "Understanding the Business Environment", new String[] {"Monday", "Wednesday", "Friday"}, new String[] {"08:00am", "08:50am"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BU205", "Introduction to Applied Statistics", new String[] {"Monday", "Wednesday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BU482", "Sales Management", new String[] {"Tuesday", "Thursday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("BU487", "Advanced Accounting", new String[] {"Tuesday", "Thursday"}, new String[] {"07:30pm", "08:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("ENTR200", "The Entrepreneurial Method", new String[] {"Friday"}, new String[] {"04:30pm", "07:30pm"}, R.drawable.laurierlogo));
        return courseModalArrayList;
    }

    private static ArrayList<CourseModal> loadDataCourses(ArrayList<CourseModal> courseModalArrayList) {
        courseModalArrayList.add(new CourseModal("DATA100", "Introduction to Data Analytics", new String[] {"Monday", "Wednesday", "Friday"}, new String[] {"08:00am", "08:50am"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("DATA200", "Data Analytics", new String[] {"Monday", "Wednesday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("DATA490", "Special Topics in Data Science", new String[] {"Tuesday", "Thursday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("DATA495", "Directed Research in Data Science", new String[] {"Tuesday", "Thursday"}, new String[] {"07:30pm", "08:50pm"}, R.drawable.laurierlogo));
        return courseModalArrayList;
    }

    private static ArrayList<CourseModal> loadMusicCourses(ArrayList<CourseModal> courseModalArrayList) {
        courseModalArrayList.add(new CourseModal("MU161", "Theory I", new String[] {"Monday", "Wednesday", "Friday"}, new String[] {"08:00am", "08:50am"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("MU162", "Theory II", new String[] {"Monday", "Wednesday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("MU240", "CM Foundations", new String[] {"Tuesday", "Thursday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("MU352", "Principles of Music Therapy", new String[] {"Tuesday", "Thursday"}, new String[] {"07:30pm", "08:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("MU383", "Chamber Music", new String[] {"Tuesday", "Thursday"}, new String[] {"07:30pm", "08:50pm"}, R.drawable.laurierlogo));
        return courseModalArrayList;
    }

    private static ArrayList<CourseModal> loadEduCourses(ArrayList<CourseModal> courseModalArrayList) {
        courseModalArrayList.add(new CourseModal("EU411", "Foundations of Mathematics for Teaching", new String[] {"Monday", "Wednesday", "Friday"}, new String[] {"08:00am", "08:50am"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("EU422I", "Teaching for the Arts J/I", new String[] {"Monday", "Wednesday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("EU422P", "Teaching for the Arts P/J", new String[] {"Tuesday", "Thursday"}, new String[] {"04:30pm", "05:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("EU434", "Global Education", new String[] {"Tuesday", "Thursday"}, new String[] {"07:30pm", "08:50pm"}, R.drawable.laurierlogo));
        courseModalArrayList.add(new CourseModal("EU438", "Gifted Education", new String[] {"Tuesday", "Thursday"}, new String[] {"07:30pm", "08:50pm"}, R.drawable.laurierlogo));
        return courseModalArrayList;
    }
}
