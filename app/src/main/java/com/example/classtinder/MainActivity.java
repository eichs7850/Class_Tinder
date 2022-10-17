package com.example.classtinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected final static String ACTIVITY_NAME = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.LoginBtn);
        EditText emailField = findViewById(R.id.email);
        EditText passwordField = findViewById(R.id.password);

        SharedPreferences sharedPref = getSharedPreferences("DefaultEmail", MODE_PRIVATE);
        String defaultEmail = sharedPref.getString("DefaultEmail", "email@domain.com");
        emailField.setText(defaultEmail);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("DefaultEmail", emailField.getText().toString());
                editor.apply();
                if(isEmailValid(emailField.getText().toString()) && isPasswordValid(
                        passwordField.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity .this, "You did not enter a login email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDelete()");
    }

    protected boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    protected boolean isPasswordValid(String password) {
        // This is temporary we should have a check for the actual password
        // TODO: check the password is correct for the account
        return password.length() > 0;
    }
}