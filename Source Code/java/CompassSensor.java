package com.Course.DesignPatterns;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import static com.Course.DesignPatterns.Constants.RADS_TO_DEGS;

public class CompassSensor extends Sensor implements SensorEventListener {
    private SensorManager sensorManager;
    private float[] gravity;
    private float[] geomagnetic;

    public CompassSensor() {
    }

    public CompassSensor(Context context) {
        sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        observers = new ArrayList<>();
    }

    @Override
    public void registerListener() {
        registerAccelerometer();
        registerMagneticField();
    }

    @Override
    public void unregisterListener() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers(Object data) {
        for (Observer observer : observers) {
            observer.update(data);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        switch (sensorEvent.sensor.getType()) {
            case android.hardware.Sensor.TYPE_ACCELEROMETER:
                gravity = sensorEvent.values;
                break;
            case android.hardware.Sensor.TYPE_MAGNETIC_FIELD:
                geomagnetic = sensorEvent.values;
                break;
            default:
                return;
        }

        notifyAllObservers(calculateAzimut());
    }

    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int i) {

    }

    private void registerAccelerometer() {
        this.sensorManager.registerListener(
                this,
                this.sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    private void registerMagneticField() {
        this.sensorManager.registerListener(
                this,
                this.sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    private float calculateAzimut() {
        float[] R = new float[Constants.MATRIX_DIM];
        float[] I = new float[Constants.MATRIX_DIM];
        float[] orientation = new float[Constants.VECTOR_DIM];
        float azimut;
        float rotation;

        if (gravity == null || geomagnetic == null) {
            return Constants.ERROR_VALUE;
        }

        SensorManager.getRotationMatrix(R, I, gravity, geomagnetic);
        SensorManager.getOrientation(R, orientation);

        azimut = orientation[0];
        rotation = -azimut * (float) RADS_TO_DEGS;

        return rotation;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
    }

}
