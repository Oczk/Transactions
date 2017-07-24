package Observed;

import Observer.IObserver;

import java.io.IOException;

public interface IObserved {
    void register(IObserver observer) throws IOException;

    void NotifyAll() throws IOException;

}