package com.example.aquasolandroid10application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {

    private Button buttonConfirmLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        buttonConfirmLogin = findViewById(R.id.buttonConfirmLogin);

        // Set click listener for the Confirm button
        buttonConfirmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFifthActivity();
            }
        });
    }

    // Method to navigate to FifthActivity
    private void goToFifthActivity() {
        Intent intent = new Intent(FourthActivity.this, FifthActivity.class);
        startActivity(intent);
    }
}