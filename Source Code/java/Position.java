package com.Course.DesignPatterns;

import android.location.Location;

public class Position extends Observer {
    private IPresentStrategy presentStrategy;

    public Position(IPresentStrategy presentStrategy) {
        this.presentStrategy = presentStrategy;
    }

    public void subscribe(Sensor locationSensor) {
        this.observable = locationSensor;
        locationSensor.attach(this);
    }

    @Override
    public void update(Object data) {
        Location location = (Location) data;
        String displayData = String.format(Constants.LOCATION_PATTERN, location.getLatitude(), location.getLongitude());
        presentStrategy.present(displayData, Constants.SensorType.POSITION);
    }
}
