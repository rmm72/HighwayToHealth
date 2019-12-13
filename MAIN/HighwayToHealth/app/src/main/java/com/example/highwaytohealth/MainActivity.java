package com.example.highwaytohealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.media.MediaPlayer;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
    private MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.h2h);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.mTextView);
        final Button cal = findViewById(R.id.calButton);
        final Button bmi = findViewById(R.id.bmiButton);
        final Button heart = findViewById(R.id.heartButton);

        mediaPlayer.start();

        final View.OnClickListener buttListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = v.getId();
                switch (id) {
                    case R.id.calButton:
                        onCalcClick();
                        break;
                    case R.id.bmiButton:
                        onBMIClick();
                        break;
                    case R.id.heartButton:
                        onHeartClick();
                        break;
                }
            }
        };

        cal.setOnClickListener(buttListener);
        bmi.setOnClickListener(buttListener);
        heart.setOnClickListener(buttListener);

        // Enables Always-on
        setAmbientEnabled();
    }

    public void onCalcClick() {
        Intent intentC = new Intent(getBaseContext(), CalorieCalculator.class);
        startActivity(intentC);
    }
    public void onBMIClick() {
        Intent intentB = new Intent(getBaseContext(), BMI.class);
        startActivity(intentB);
    }
    public void onHeartClick() {
        Intent intentH = new Intent(getBaseContext(), HeartMonitor.class);
        startActivity(intentH);
    }

}
