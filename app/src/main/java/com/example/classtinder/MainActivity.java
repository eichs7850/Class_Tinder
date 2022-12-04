package com.example.classtinder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    protected final static String ACTIVITY_NAME = "MainActivity";
    private GoogleSignInClient googleSignInClient;
    FirebaseAuth mAuth;
    public int RC_SIGN_IN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.LoginBtn);
        com.google.android.gms.common.SignInButton googleButton = findViewById(R.id.google);
        EditText emailField = findViewById(R.id.email);
        EditText passwordField = findViewById(R.id.password);

        SharedPreferences sharedPref = getSharedPreferences("DefaultEmail", MODE_PRIVATE);
        String defaultEmail = sharedPref.getString("DefaultEmail", "email@domain.com");
        emailField.setText(defaultEmail);

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.google:
                        signIn();
                        break;
                }
            }
        });


/*
        mAuth = FirebaseAuth.getInstance();*/
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

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//
//            try {
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                firebaseAuthWithGoogle(Objects.requireNonNull(account));
//            } catch (ApiException e) {
//                Log.w("hhm", "Google signin failed", e);
//            }
//        }
//    }

    public void signIn(){

        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Integer result = (Integer) resultCode;
        Log.d("HEY IDIOT THE RESULTCODE IS ", result.toString());
        if(resultCode == 0) {
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "Sign-In successful!", Toast.LENGTH_SHORT);
        } else {
            Toast.makeText(MainActivity.this, "Sign-In unsuccessful", Toast.LENGTH_SHORT);
        }


        // bad result
        //do something
    }
/*    public void onActivityResult(){
        SignInCredential googleCredential = oneTapClient.getSignInCredentialFromIntent(data);
        String idToken = googleCredential.getGoogleIdToken();
        if (idToken !=  null) {
            // Got an ID token from Google. Use it to authenticate
            // with Firebase.
            AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
            mAuth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithCredential:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                                updateUI(null);
                            }
                        }
                    });
        }
    }*/

/*    public void GoogleSingIn(View v) {

        BeginSignInRequest.Builder signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId("796053582641-d5s9p89ap36v3ng1o7ss3i339k3bud7u.apps.googleusercontent.com")
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build());





    }*/
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }
    public void updateUI(GoogleSignInAccount account){

        if(account != null){
            Toast.makeText(this,"You Signed In successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,Home.class));

        }else {
            Toast.makeText(this,"You Didnt signed in",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

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

