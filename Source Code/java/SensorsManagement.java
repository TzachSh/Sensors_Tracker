package com.Course.DesignPatterns;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;

public class SensorsManagement {
    private static SensorsManagement instance = null;
    private HashMap<String, Sensor> sensorsMap;

    // Private constructor
    private SensorsManagement() {
    }

    public static synchronized SensorsManagement getInstance() {
        if (instance == null) {
            instance = new SensorsManagement();
        }
        return instance;
    }

    public void initSensors(Context context) {
        sensorsMap = new HashMap<>();
        sensorsMap.put(Light.class.getName(), new LightSensor(context));
        sensorsMap.put(Position.class.getName(), new LocationSensor(context, Constants.MIN_TIME_UPDATE, Constants.MIN_DISTANCE_UPDATE));
        sensorsMap.put(Pressure.class.getName(), new PressureSensor(context));
        sensorsMap.put(Proximity.class.getName(), new ProximitySensor(context));
        sensorsMap.put(Compass.class.getName(), new CompassSensor(context));
    }

    public void registerListeners() {

        for (Sensor sensor : sensorsMap.values()) {
            sensor.registerListener();
        }

    }

    public void unregisterListeners() {

        for (Sensor sensor : sensorsMap.values()) {
            sensor.unregisterListener();
        }

    }

    public void startSubscribe(ArrayList<Observer> observers) {

        for (Observer observer : observers) {
            observer.subscribe(sensorsMap.get(observer.getClass().getName()));
        }

    }

    public void stopSubscribe(ArrayList<Observer> observers) {

        for (Observer observer : observers) {
            observer.unSubscribe();
        }
    }

    public void startBackgroundServices(Activity activity) {
        Context context = activity.getApplicationContext();
        activity.getApplication().startService(new Intent(context, LightSensor.class));
        activity.getApplication().startService(new Intent(context, Proximity.class));
        activity.getApplication().startService(new Intent(context, Pressure.class));
        activity.getApplication().startService(new Intent(context, Position.class));
        activity.getApplication().startService(new Intent(context, LocationSensor.class));
        activity.getApplication().startService(new Intent(context, Compass.class));
    }
}

