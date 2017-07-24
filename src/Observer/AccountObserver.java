package Observer;

import Observed.Account;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountObserver implements IObserver {
    private Account acc;
    private FileWriter accHist = null;
    private static int ids = 0;
    private int id;

    public AccountObserver(Account acc) throws IOException {
        this.acc = acc;
        ids += 1;
        this.id = ids;
        accHist = new FileWriter(newFile());
        acc.register(this);
    }

    private String newFile() {
        return "account" + acc.getId() + "_history.txt";
    }

    @Override
    public void Notify() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        accHist = new FileWriter(newFile(), true);
        BufferedWriter bw = new BufferedWriter(accHist);
        bw.write(acc.getId() + " " + dateFormat.format(date) + " " + acc.getBalance());
        bw.newLine();
        bw.close();
        accHist.close();
    }
}