package com.Course.DesignPatterns;

public abstract class Observer {
    protected IObservable observable;

    public abstract void update(Object data);

    public abstract void subscribe(Sensor sensor);

    public void unSubscribe() {
        this.observable.detach(this);
    }
}
