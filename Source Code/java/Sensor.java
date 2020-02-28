package com.Course.DesignPatterns;

import android.app.Service;

import java.util.List;

public abstract class Sensor extends Service implements IObservable {
    protected List<Observer> observers;

    public abstract void registerListener();

    public abstract void unregisterListener();

}
