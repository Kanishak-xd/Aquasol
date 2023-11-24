package com.example.aquasolandroid10application;
// SecondActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class    SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize views
        ImageView imageView1 = findViewById(R.id.uni);
        TextView bhashanTextView = findViewById(R.id.bhashan);
        Button button = findViewById(R.id.continuebutton);

        // Set an onClickListener for the button
        // Set an onClickListener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remove the try-catch block temporarily
                Intent intent = new Intent(SecondActivity.this, FourthActivity.class);
                startActivity(intent);
            }
        });


        // You can perform additional actions on imageView1 and bhashanTextView if needed
    }
}
