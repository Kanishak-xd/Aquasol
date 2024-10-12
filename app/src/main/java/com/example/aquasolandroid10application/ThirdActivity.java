package com.example.aquasolandroid10application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThirdActivity extends AppCompatActivity {

    // Declare the buttons and EditText fields
    private Button buttonConfirmSignUp;
    private Button buttonLoginInstead;
    private EditText editTextUsernameSignUp, editTextEmailSignUp, editTextPasswordSignUp;

    // Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Firebase initialization
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance("https://aquasol-db-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("users");

        // Initialize the views
        buttonConfirmSignUp = findViewById(R.id.buttonConfirmSignUp);
        buttonLoginInstead = findViewById(R.id.buttonLoginInstead);
        editTextUsernameSignUp = findViewById(R.id.editTextUsernameSignUp);
        editTextEmailSignUp = findViewById(R.id.editTextEmailSignUp);
        editTextPasswordSignUp = findViewById(R.id.editTextPasswordSignUp);

        // Set onClickListener for Sign Up button
        buttonConfirmSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        // Set onClickListener for Login button
        buttonLoginInstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Login logic handling
                startActivity(new Intent(ThirdActivity.this, FourthActivity.class));
            }
        });
    }

    private void registerUser() {
        String username = editTextUsernameSignUp.getText().toString().trim();
        String email = editTextEmailSignUp.getText().toString().trim();
        String password = editTextPasswordSignUp.getText().toString().trim();

        // Validate input
        if (email.isEmpty()) {
            editTextEmailSignUp.setError("Email is required");
            editTextEmailSignUp.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPasswordSignUp.setError("Password must be at least 6 characters");
            editTextPasswordSignUp.requestFocus();
            return;
        }

        // Create a user in Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Store user data in Firebase Realtime Database
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            User userData = new User(username, email);
                            mDatabase.child(user.getUid()).setValue(userData)
                                    .addOnCompleteListener(dbTask -> {
                                        if (dbTask.isSuccessful()) {
                                            // Log before navigating
                                            Log.d("ThirdActivity", "User data saved successfully.");
                                            Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear the activity stack
                                            startActivity(intent);
                                            finish(); // Optional: Finish the current activity
                                        } else {
                                            String dbErrorMessage = dbTask.getException() != null ? dbTask.getException().getMessage() : "Unknown error";
                                            Log.e("ThirdActivity", "Failed to save data: " + dbErrorMessage);
                                            Toast.makeText(ThirdActivity.this, "Failed to save data: " + dbErrorMessage, Toast.LENGTH_LONG).show();
                                        }
                                    });
                        }
                    } else {
                        String authErrorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown error";
                        Log.e("ThirdActivity", "Failed to register: " + authErrorMessage);
                        if (authErrorMessage.contains("The email address is already in use")) {
                            Toast.makeText(ThirdActivity.this, "Email is already in use. Please try a different email.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ThirdActivity.this, "Failed to register: " + authErrorMessage, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
