package com.Course.DesignPatterns;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class LightSensor extends com.Course.DesignPatterns.Sensor implements SensorEventListener {
    private SensorManager sensorManager;

    public LightSensor() {
    }

    public LightSensor(Context context) {
        sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        this.observers = new ArrayList<>();
    }

    public void registerListener() {
        this.sensorManager.registerListener(
                this,
                this.sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    @Override
    public void unregisterListener() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float lightValue = sensorEvent.values[0];
        notifyAllObservers(lightValue);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

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
