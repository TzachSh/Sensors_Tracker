package com.Course.DesignPatterns;

public class Compass extends Observer {
    private IPresentStrategy presentStrategy;

    public Compass(IPresentStrategy presentStrategy) {
        this.presentStrategy = presentStrategy;
    }

    public void subscribe(Sensor compassSensor) {
        this.observable = compassSensor;
        compassSensor.attach(this);
    }

    @Override
    public void update(Object data) {
        String displayData = String.format(Constants.PRESENT_PATTERN, data);
        presentStrategy.present(displayData, Constants.SensorType.COMPASS);
    }
}
