package Observed;

import Observer.IObserver;
import Transaction.Client;
import Transaction.Receiver;

import java.io.IOException;

public class Transaction implements IObserved {
    public static int id = 0;
    public Client client;
    public Receiver rec;
    public double amount;
    private IObserver pendingObserver;
    public IObserver endedObserver;

    public Transaction(Client client, Receiver rec, double amount) throws IOException {
        this.client = client;
        this.rec = rec;
        this.amount = amount;
        id += 1;
    }

    public void doTransaction() throws IOException {
        client.acc.takeMoney(amount);
        rec.acc.addMoney(amount);
        NotifyAll();
    }

    @Override
    public void register(IObserver observer) throws IOException {
        this.pendingObserver = observer;
        pendingObserver.Notify();
    }

    @Override
    public void NotifyAll() throws IOException {
        endedObserver.Notify();
    }
}
