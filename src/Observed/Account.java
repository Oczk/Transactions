package Observed;

import Observer.IObserver;

import java.io.IOException;

public class Account implements IObserved {
    private String number;
    private double balance;
    private int id;
    private IObserver observer;

    public Account(int id, String number, double balance) {
        this.id = id;
        this.number = number;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    void addMoney(double amount) throws IOException {
        balance = balance + amount;
        NotifyAll();
    }

    void takeMoney(double amount) throws IOException {
        balance = balance - amount;
        NotifyAll();
    }


    @Override
    public void register(IObserver observer) {
        this.observer = observer;
    }

    @Override
    public void NotifyAll() throws IOException {
        observer.Notify();
    }
}
