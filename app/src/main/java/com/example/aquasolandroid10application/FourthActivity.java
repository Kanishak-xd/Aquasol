package com.example.aquasolandroid10application;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FourthActivity extends AppCompatActivity {

    private EditText editTextEmailLogin, editTextPasswordLogin;
    private Button buttonConfirmLogin, buttonSignUpInstead;  // Add the buttonSignUpInstead reference
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize the views
        editTextEmailLogin = findViewById(R.id.editTextEmailLogin);
        editTextPasswordLogin = findViewById(R.id.editTextPasswordLogin);
        buttonConfirmLogin = findViewById(R.id.buttonConfirmLogin);
        buttonSignUpInstead = findViewById(R.id.buttonSignUpInstead);  // Initialize the SignUpInstead button

        // Set click listener for the Confirm button
        buttonConfirmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        // Set click listener for the Sign Up Instead button
        buttonSignUpInstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToThirdActivity();  // Call the method to navigate to ThirdActivity
            }
        });
    }

    // Method to handle login functionality
    private void loginUser() {
        String email = editTextEmailLogin.getText().toString().trim();
        String password = editTextPasswordLogin.getText().toString().trim();

        // Validate email and password fields
        if (TextUtils.isEmpty(email)) {
            editTextEmailLogin.setError("Please enter your email");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPasswordLogin.setError("Please enter your password");
            return;
        }

        // Check if the email exists and log in
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, go to FifthActivity
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(FourthActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        goToFifthActivity();
                    } else {
                        // If sign-in fails, check if it's due to non-existent email or incorrect password
                        String errorMessage = task.getException() != null ? task.getException().getMessage() : "Login failed";
                        if (errorMessage.contains("There is no user record")) {
                            Toast.makeText(FourthActivity.this, "No such account exists with this E-mail address", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(FourthActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    // Method to navigate to FifthActivity
    private void goToFifthActivity() {
        Intent intent = new Intent(FourthActivity.this, FifthActivity.class);
        startActivity(intent);
        finish(); // Optional: Close FourthActivity so the user can't go back after logging in
    }

    // Method to navigate to ThirdActivity when the user clicks the Sign Up Instead button
    private void goToThirdActivity() {
        Intent intent = new Intent(FourthActivity.this, ThirdActivity.class);
        startActivity(intent);
    }
}
