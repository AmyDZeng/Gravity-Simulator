package com.terriblehacks.gravitysimulator;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by Malzberry on 10/4/2015.
 */
public class Simulator extends Activity implements SensorEventListener{
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private float initialY = 0, lastY, last_x, last_y, last_z;
    private long lastUpdate;
    Boolean lifted, dropped, start;
    //private static final int FALL_THRESHOLD;
    //private static final int LIFT_THRESHOLD;

    TextView instructions;
    TextView testText;
    TextView testTextMax;
    TextView testTextMin;
    float maxY = 0, minY = 0;

    float gravity[] = {0, (float)-9.8, 0};
    float linear_acceleration[] = {0,0,0};
    float alpha = (float) 0.8;

    protected void onCreate(Bundle savedInstanceState){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simulator);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        lastUpdate = System.currentTimeMillis();
        lifted = false;
        dropped = false;
        start = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];




            if (start == false){
                initialY = y;
                start = true;
            }

            last_x = x;
            last_y = y;
            last_z = z;



            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                lastY = y;

                if (lifted == false && dropped == false){

                }
                // TESTS



                gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
                gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
                gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

                linear_acceleration[0] = event.values[0] - gravity[0];
                linear_acceleration[1] = event.values[1] - gravity[1];
                linear_acceleration[2] = event.values[2] - gravity[2];


                //float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 1000000000;
                testText = (TextView) findViewById(R.id.testText);
                //double acc = calculateAcceleration(event.values);
                testText.setText(Float.toString(linear_acceleration[1]));

                if(linear_acceleration[1] > maxY)maxY = linear_acceleration[1];

                testTextMax = (TextView) findViewById(R.id.testTextMax);
                testTextMax.setText(Float.toString(maxY));

                if(linear_acceleration[1] < minY)minY = linear_acceleration[1];

                testTextMin = (TextView) findViewById(R.id.testTextMin);
                testTextMin.setText(Float.toString(minY));

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public static double calculateAcceleration(float[] values) {
        double acceleration = Math.sqrt(Math.pow(values[0], 2)
                + Math.pow(values[1], 2) + Math.pow(values[2], 2));
        return acceleration;
    }

    public void onLift(){
        instructions = (TextView) findViewById(R.id.instructions);
        instructions.setText("Drop it like it's hot");
    }

    public void onDrop(){
        instructions = (TextView) findViewById(R.id.instructions);
        instructions.setText("Gravity Simulation Complete.");
    }

}
