package com.Course.DesignPatterns;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class ProximitySensor extends Sensor implements SensorEventListener {
    private SensorManager sensorManager;

    public ProximitySensor() {
    }

    public ProximitySensor(Context context) {
        this.observers = new ArrayList<>();
        this.sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
    }

    @Override
    public void registerListener() {
        this.sensorManager.registerListener(
                this,
                this.sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_PROXIMITY),
                SensorManager.SENSOR_DELAY_NORMAL
        );
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
        float proximityValue = sensorEvent.values[0];
        notifyAllObservers(proximityValue);
    }

    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int i) {

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
