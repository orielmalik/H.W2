package com.example.hw2orielmalik322985441;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class StepDetector {
    private com.example.hw2orielmalik322985441.stepCallback stepCallback;
    private SensorManager sensorManager;
    private Sensor sensor;

    private int stepCountX = 0,fx=0,fy=0;
    private int stepCountY = 0;
    private long timestemp = 0;


    private SensorEventListener sensorEventListener;
   private int stepCounter=0;

    public StepDetector(Context context, stepCallback _stepCallback) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.stepCallback = _stepCallback;
        initEventListener();

    }

    private void initEventListener() {
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                fx=(int)x;
                Log.d( "onSensorChanged:",""+ x);
                float y = event.values[1];
                calculateStep(x, y);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }


            private void calculateStep(float x, float y) {
                if (x > 6.0) {
                    if (System.currentTimeMillis() - timestemp > 500) {
                        timestemp = System.currentTimeMillis();
                        stepCounter++;
                        stepCountX++;
                    if (stepCallback != null) {
                            stepCallback.stepX();
                        }
                    }
                } else   {

                    if (System.currentTimeMillis() - timestemp > 500) {
                        timestemp = System.currentTimeMillis();
                        stepCounter--;
                        stepCountX--;


                        if (stepCallback != null) {
                            stepCallback.stepX();
                        }
                    }

                    if (y > 6.0) {
                        if (System.currentTimeMillis() - timestemp > 500) {
                            timestemp = System.currentTimeMillis();
                            stepCounter += 2;
                            stepCountY++;
                            if (stepCallback != null) {
                                stepCallback.stepY();
                            }
                        }
                    }


                }
            }
    public void start() {
        sensorManager.registerListener(
                sensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    public void stop() {
        sensorManager.unregisterListener(
                sensorEventListener,
                sensor
        );
    }

public void setStepCounter(int t)

{
    this.stepCounter=t;
}
public int getStepCounter(){return  stepCounter;}

    public void setStepCountX(int stepCountX) {
        this.stepCountX = stepCountX;
    }

    public int getStepCountX() {
        return stepCountX;
    }

    public int getFx() {
        return fx;
    }

    public void setFx(int fx) {
        this.fx = fx;
    }
}
