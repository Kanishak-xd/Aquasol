package com.example.aquasolandroid10application;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SeventhActivity extends AppCompatActivity {

    private TextView progPercentTextView;
    private TextView levelTdsFlowRateTextView;
    private ImageView levelTdsFlowRateIconImageView;
    private TextView tankUgText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh);
        String selection = getIntent().getStringExtra("selection");
        tankUgText = findViewById(R.id.tank_ug_text);


        // Initialize views
        progPercentTextView = findViewById(R.id.ProgPercent);
        levelTdsFlowRateTextView = findViewById(R.id.leveltdsflowrate);
        levelTdsFlowRateIconImageView = findViewById(R.id.leveltdsflowrateicon);
        ImageButton waterLevelButton = findViewById(R.id.waterlevelbutton);
        ImageButton flowRateButton = findViewById(R.id.flowratebutton);
        ImageButton tdsButton = findViewById(R.id.tdsbutton);


        // Update the tank_ug_text based on the selection
        if ("jatinsugone".equals(selection)) {
            // Handle the text updates for jatinsugone
            tankUgText.setText("WATER TANK ONE - UG1");
        } else if ("jatinsugtwo".equals(selection)) {
            // Handle the text updates for jatinsugtwo
            tankUgText.setText("WATER TANK ONE - UG2");
        }

        // Set onClickListeners for the buttons
        waterLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Change the text to "30%" when waterlevelbutton is clicked
                progPercentTextView.setText("30%");
                // Change the text to "WATER LEVEL" when waterlevelbutton is clicked
                levelTdsFlowRateTextView.setText("WATER LEVEL");
                // Change the image to waterlevelicon when waterlevelbutton is clicked
                levelTdsFlowRateIconImageView.setImageResource(R.drawable.waterlevelicon);
            }
        });

        flowRateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Change the text to "100L/min" when flowratebutton is clicked
                progPercentTextView.setText("100L/min");
                // Change the text to "FLOW RATE" when flowratebutton is clicked
                levelTdsFlowRateTextView.setText("FLOW RATE");
                // Change the image to flowratebuttonicon when flowratebutton is clicked
                levelTdsFlowRateIconImageView.setImageResource(R.drawable.flowratebuttonicon);
            }
        });

        tdsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Change the text to "100ppm" when tdsbutton is clicked
                progPercentTextView.setText("100ppm");
                // Change the text to "TDS VALUE" when tdsbutton is clicked
                levelTdsFlowRateTextView.setText("TDS VALUE");
                // Change the image to tdslevelbuttonicon when tdsbutton is clicked
                levelTdsFlowRateIconImageView.setImageResource(R.drawable.tdslevelbuttonicon);
            }
        });

        // You can perform additional actions on other views if needed
    }
}

