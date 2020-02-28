package com.Course.DesignPatterns;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

public class SensorsTracking {
    private ArrayList<Observer> observersList;
    private Activity activity;

    public SensorsTracking(Activity activity) {
        this.activity = activity;
        this.observersList = new ArrayList<>();

        observersList.add(new Light(new TextviewStrategy(activity)));
        observersList.add(new Proximity(new TextviewStrategy(activity)));
        observersList.add(new Pressure(new TextviewStrategy(activity)));
        observersList.add(new Position(new TextviewStrategy(activity)));
        observersList.add(new Compass(new TextviewStrategy(activity)));
    }

    public void pause() {
        SensorsManagement.getInstance().stopSubscribe(observersList);
        SensorsManagement.getInstance().unregisterListeners();
    }

    public void start() {
        Context context = this.activity.getApplicationContext();
        SensorsManagement.getInstance().initSensors(context);
        SensorsManagement.getInstance().registerListeners();
        SensorsManagement.getInstance().startSubscribe(observersList);
        SensorsManagement.getInstance().startBackgroundServices(activity);
    }
}
