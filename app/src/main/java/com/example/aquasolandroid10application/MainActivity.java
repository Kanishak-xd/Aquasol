package com.example.aquasolandroid10application;// MainActivity.java

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add a delay before opening the next activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    // Start the next activity
                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                    // Finish the current activity to prevent the user from coming back
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000); // Delay in milliseconds (1000ms = 1s)
    }
}