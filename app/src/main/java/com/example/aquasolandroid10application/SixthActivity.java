package com.example.aquasolandroid10application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SixthActivity extends AppCompatActivity {

    private ImageView imageView;
    private String selection; // Variable to store UG selection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        imageView = findViewById(R.id.ugoneugtwo);
        selection = getIntent().getStringExtra("selection");

        if ("1".equals(selection)) {
            imageView.setImageResource(R.drawable.jatinsugone);
        } else if ("2".equals(selection)) {
            imageView.setImageResource(R.drawable.jatinsugtwo);
        }
    }

    public void onButton1Click(View view) {
        navigateToSeventhActivity("T1");
    }

    public void onButton2Click(View view) {
        navigateToSeventhActivity("T2");
    }

    public void onButton3Click(View view) {
        navigateToSeventhActivity("T3");
    }

    private void navigateToSeventhActivity(String tank) {
        Intent intent = new Intent(SixthActivity.this, SeventhActivity.class);
        intent.putExtra("selection", selection + tank); // Pass the full selection value
        startActivity(intent);
    }
}
