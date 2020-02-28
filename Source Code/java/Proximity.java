package com.Course.DesignPatterns;

public class Proximity extends Observer {
    private IPresentStrategy presentStrategy;

    public Proximity(IPresentStrategy presentStrategy) {
        this.presentStrategy = presentStrategy;
    }

    public void subscribe(Sensor proximitySensor) {
        this.observable = proximitySensor;
        proximitySensor.attach(this);
    }

    @Override
    public void update(Object data) {
        String displayData = String.format(Constants.PRESENT_PATTERN, data);
        presentStrategy.present(displayData, Constants.SensorType.PROXIMITY);
    }
}
