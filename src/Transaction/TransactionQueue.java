package Transaction;

import Observed.Account;
import Observed.Transaction;
import Observer.EndedTransferObserver;
import Observer.TransferObserver;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TransactionQueue {
    private static TransactionQueue instance;

    private TransactionQueue() {
    }

    public static TransactionQueue getInstance() {
        if (instance == null)
            instance = new TransactionQueue();
        return instance;
    }

    private List<String> transactionsList;

    public void setList(List<String> transactionsList) {
        this.transactionsList = transactionsList;
    }

    private List<String> unhandledList = new LinkedList<>();

    public List<String> getList() {
        return unhandledList;
    }

    private Map<String, Account> accMap;

    public void setMap(Map<String, Account> accMap) {
        this.accMap = accMap;
    }

    public void runQueue() throws IOException {
        for (int i = 0; i < transactionsList.size(); i++) {
            String aTransactionsList = transactionsList.get(i);
            String[] transactionLine = aTransactionsList.split(" ");
            Account acc = accMap.get(transactionLine[0]);
            Client clientAcc = new Client(acc);
            acc = accMap.get(transactionLine[1]);
            Receiver receiverAcc = new Receiver(acc);
            double amount = Double.parseDouble(transactionLine[2]);
            Transaction transaction = new Transaction(clientAcc, receiverAcc, amount);
            new TransferObserver(transaction);
            new EndedTransferObserver(transaction);
            if (clientAcc.acc.getBalance() < amount) {
                if (transactionLine.length > 3) {
                    unhandledList.add(aTransactionsList);
                } else {
                    transactionsList.add(aTransactionsList + " N");
                }
            } else {
                transaction.doTransaction();
            }
        }
    }
}