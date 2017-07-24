import Observed.Account;
import Observer.AccountObserver;
import Transaction.TransactionQueue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<String, Account> accMap = new TreeMap<>();
        List<String> transList = new LinkedList<>();
        List<String> unhandledList;
        Account account;

        FileReader accReader = new FileReader("accs.txt"); //Account reading
        BufferedReader buffAccReader = new BufferedReader(accReader);
        String accLine = buffAccReader.readLine();
        String[] acc;

        do {
            acc = accLine.split(" ");
            int id = Integer.parseInt(acc[0]);
            double amount = Double.parseDouble(acc[2]);
            account = new Account(id, acc[1], amount); //creating account
            new AccountObserver(account); //adding observer
            accMap.put(acc[1], account); //adding account to map
            accLine = buffAccReader.readLine();
        } while (accLine != null);
        buffAccReader.close();

        FileReader transReader = new FileReader("transfers.txt"); //transactions reading
        BufferedReader buffTransReader = new BufferedReader(transReader);
        String transLine = buffTransReader.readLine();

        do {
            transList.add(transLine); //adding transaction to list
            transLine = buffTransReader.readLine();
        } while (transLine != null);
        buffTransReader.close();


        TransactionQueue.getInstance().setMap(accMap);
        TransactionQueue.getInstance().setList(transList);
        TransactionQueue.getInstance().runQueue();

        unhandledList = TransactionQueue.getInstance().getList();
    }
}
