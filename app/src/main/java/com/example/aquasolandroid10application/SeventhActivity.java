package com.example.aquasolandroid10application;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SeventhActivity extends AppCompatActivity {

    private TextView waterLevelTextView, flowRateTextView, tdsValueTextView, textView3;
    private ProgressBar progressBarOne, progressBarTwo, progressBarThree;

    private static final String CHANNEL_ID = "water_level_channel"; // Notification channel ID
    private static final int REQUEST_NOTIFICATION_PERMISSION = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh);

        // Back button
        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(SeventhActivity.this, FifthActivity.class);
            startActivity(intent);
        });

        // TextViews and ProgressBars
        waterLevelTextView = findViewById(R.id.textView7);
        flowRateTextView = findViewById(R.id.textView9);
        tdsValueTextView = findViewById(R.id.textView8);
        textView3 = findViewById(R.id.textView3);

        progressBarOne = findViewById(R.id.progressBarOne);
        progressBarTwo = findViewById(R.id.progressBarTwo);
        progressBarThree = findViewById(R.id.progressBarThree);

        // Check and request notification permission if Android version is 13 or above
        checkNotificationPermission();

        // Get the selection from the previous activity
        String selection = getIntent().getStringExtra("selection");
        String tank = "";

        if (selection != null) {
            if (selection.startsWith("1")) {
                tank = "tank" + selection.substring(2);
                setTankDescription(selection, "UG 1");
            } else if (selection.startsWith("2")) {
                tank = "tank" + (Integer.parseInt(selection.substring(2)) + 3);
                setTankDescription(selection, "UG 2");
            }
        }

        // Fetch data from Firebase
        DatabaseReference tankReference = FirebaseDatabase.getInstance().getReference().child("readings").child(tank);

        // Fetch and display water level
        tankReference.child("waterLevel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int waterLevel = dataSnapshot.getValue(Integer.class);
                    waterLevelTextView.setText(waterLevel + "%");
                    progressBarOne.setProgress(waterLevel);

                    // Trigger notification if water level exceeds 75%
                    if (waterLevel > 75) {
                        sendNotification("Water level has exceeded 75%");
                    }
                } else {
                    Log.d("FirebaseData", "Water Level data not found.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Failed to read Water Level: " + error.getMessage());
            }
        });

        // Fetch and display flow rate
        tankReference.child("flowRate").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    double flowRate = dataSnapshot.getValue(Double.class);
                    flowRateTextView.setText(flowRate + " L/min");
                    progressBarThree.setProgress((int) flowRate);
                } else {
                    Log.d("FirebaseData", "Flow Rate data not found.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Failed to read Flow Rate: " + error.getMessage());
            }
        });

        // Fetch and display TDS value
        tankReference.child("tdsValue").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int tdsValue = dataSnapshot.getValue(Integer.class);
                    tdsValueTextView.setText(tdsValue + " ppm");
                    progressBarTwo.setProgress(tdsValue);
                } else {
                    Log.d("FirebaseData", "TDS Value data not found.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Failed to read TDS Value: " + error.getMessage());
            }
        });
    }

    private void checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_NOTIFICATION_PERMISSION);
            } else {
                createNotificationChannel(); // Permission already granted
            }
        } else {
            createNotificationChannel(); // For Android versions below 13, permission is not required
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_NOTIFICATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                createNotificationChannel();
            } else {
                // Handle permission denial accordingly
                Log.d("Permission", "Notification permission denied.");
            }
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Water Level Alerts";
            String description = "Notifications for Water Level Exceeded 75%";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void sendNotification(String message) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notification_foreground) // Ensure this icon exists in res/drawable
                    .setContentTitle("Water Level Alert")
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1, builder.build());
        } else {
            Log.d("Notification", "Cannot send notification; permission not granted.");
        }
    }

    private void setTankDescription(String selection, String ug) {
        String description;
        switch (selection.substring(2)) {
            case "T1":
                description = "Water Tank 1 - " + ug;
                break;
            case "T2":
                description = "Water Tank 2 - " + ug;
                break;
            case "T3":
                description = "Water Tank 3 - " + ug;
                break;
            default:
                description = "Unknown Tank - " + ug;
                break;
        }
        textView3.setText(description);
    }
}
