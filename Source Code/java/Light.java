package com.Course.DesignPatterns;

public class Light extends Observer {
    private IPresentStrategy presentStrategy;

    public Light(IPresentStrategy presentStrategy) {
        this.presentStrategy = presentStrategy;
    }

    @Override
    public void update(Object data) {
        String displayData = String.format(Constants.PRESENT_PATTERN, data);
        presentStrategy.present(displayData, Constants.SensorType.LIGHT);
    }

    public void subscribe(Sensor lightSensor) {
        this.observable = lightSensor;
        lightSensor.attach(this);
    }
}
