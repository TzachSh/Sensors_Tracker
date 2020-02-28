package com.Course.DesignPatterns;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;

public class LocationSensor extends Sensor implements LocationListener {
    private LocationManager locationManager;
    private long minTime;
    private float minDistance;
    private Context context;

    public LocationSensor() {
    }

    public LocationSensor(Context context, long minTimeUpdate, float minDistanceUpdate) {
        this.observers = new ArrayList<>();
        this.locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        this.minDistance = minDistanceUpdate;
        this.minTime = minTimeUpdate;
        this.context = context;
    }

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
    public void onLocationChanged(Location location) {
        notifyAllObservers(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void registerListener() {

        //Register location listener only if the user accepted location services
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, this);
    }

    @Override
    public void unregisterListener() {
        locationManager.removeUpdates(this);
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
