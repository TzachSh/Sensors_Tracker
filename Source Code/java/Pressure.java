package com.Course.DesignPatterns;

public class Pressure extends Observer {
    private IPresentStrategy presentStrategy;

    public Pressure(IPresentStrategy presentStrategy) {
        this.presentStrategy = presentStrategy;
    }

    public void subscribe(Sensor pressureSensor) {
        this.observable = pressureSensor;
        pressureSensor.attach(this);
    }

    @Override
    public void update(Object data) {
        String displayData = String.format(Constants.PRESENT_PATTERN, data);
        presentStrategy.present(displayData, Constants.SensorType.PRESSURE);
    }
}
