package com.example.aquasolandroid10application;

// SixthActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SixthActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        imageView = findViewById(R.id.ugoneugtwo);
        String selection = getIntent().getStringExtra("selection");
        if ("jatinsugone".equals(selection)) {
            imageView.setImageResource(R.drawable.jatinsugone);
        } else if ("jatinsugtwo".equals(selection)) {
            imageView.setImageResource(R.drawable.jatinsugtwo);
        }
    }

    // Handle button 1 click
    public void onButton1Click(View view) {
        // Navigate to SeventhActivity
        navigateToSeventhActivity();
    }

    // Handle button 2 click
    public void onButton2Click(View view) {
        // Navigate to SeventhActivity
        navigateToSeventhActivity();
    }

    // Handle button 3 click
    public void onButton3Click(View view) {
        // Navigate to SeventhActivity
        navigateToSeventhActivity();
    }

    // Method to navigate to SeventhActivity
    private void navigateToSeventhActivity() {
        Intent intent = new Intent(SixthActivity.this, SeventhActivity.class);
        startActivity(intent);
    }
}
