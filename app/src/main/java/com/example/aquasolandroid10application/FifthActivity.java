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

        ugoneSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pass UG1 selection
                Intent intent = new Intent(FifthActivity.this, SixthActivity.class);
                intent.putExtra("selection", "1"); // UG1
                startActivity(intent);
            }
        });

        ugtwoSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pass UG2 selection
                Intent intent = new Intent(FifthActivity.this, SixthActivity.class);
                intent.putExtra("selection", "2"); // UG2
                startActivity(intent);
            }
        });
    }
}
