package com.Course.DesignPatterns;

public interface IObservable {
    void attach(Observer observer);

    void detach(Observer observer);

    void notifyAllObservers(Object data);
}
