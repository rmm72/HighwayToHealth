package com.example.highwaytohealth;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HeartMonitor extends WearableActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    private TextView mTextViewHeart;
    SensorManager mSensorManager;
    Sensor mHeartRateSensor;
    SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mTextViewHeart = findViewById(R.id.heart);
        if (mTextViewHeart == null) {
            mTextViewHeart.setText("No Heartbeat detected.");
        }
        mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(TAG, "LISTENER REGISTERED.");
        mTextViewHeart.setText("Sensing bpm: ");


        mSensorManager.registerListener(sensorEventListener, mHeartRateSensor, mSensorManager.SENSOR_DELAY_FASTEST);

    }


    public void onResume(){
        super.onResume();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged - accuracy: " + accuracy);
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_HEART_RATE) {
            String msg = "BPM = " + (int)event.values[0];
            mTextViewHeart.setText(msg);
            Log.d(TAG, msg);
        }
        else
            Log.d(TAG, "Unknown sensor type");
    }
//    public void onloadhighBPMButtonClicked(View view) {
//        Intent my_intent = new Intent(getBaseContext(), HighHeartRateWarning.class);
//        startActivity(my_intent);
//
//    }
    public void onloadlowBPMButtonClicked(View view) {
        Intent my_intent = new Intent(getBaseContext(), EmergencyAlert.class);
        startActivity(my_intent);
    }
}

