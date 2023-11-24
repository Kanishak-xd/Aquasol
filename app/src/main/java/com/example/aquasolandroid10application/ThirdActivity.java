package com.example.aquasolandroid10application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Set click listener for the Sign Up button
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFourthActivity();
            }
        });
    }

    // Method to navigate to FourthActivity
    public void goToFourthActivity() {
        Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
        startActivity(intent);
    }
}