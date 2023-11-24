package com.example.aquasolandroid10application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class FifthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        ImageButton ugoneSelectionButton = findViewById(R.id.ugoneselection);
        ImageButton ugtwoSelectionButton = findViewById(R.id.ugtwoselection);

        // Set onClickListener for ugoneSelectionButton
        ugoneSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click for ugoneSelectionButton
                Intent intent = new Intent(FifthActivity.this, SixthActivity.class);
                intent.putExtra("selection", "jatinsugone");
                startActivity(intent);
            }
        });

        // Set onClickListener for ugtwoSelectionButton
        ugtwoSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click for ugtwoSelectionButton
                Intent intent = new Intent(FifthActivity.this, SixthActivity.class);
                intent.putExtra("selection", "jatinsugtwo");
                startActivity(intent);
            }
        });
    }
}
