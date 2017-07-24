package Observer;


import Observed.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransferObserver implements IObserver {

    private String newFile() {
        return "transfer_history.txt";
    }

    public TransferObserver(Transaction transfer) throws IOException {
        transfer.register(this);
    }

    @Override
    public void Notify() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        FileWriter transHist = new FileWriter(newFile(), true);
        BufferedWriter bw = new BufferedWriter(transHist);
        bw.write(Transaction.id + " " + dateFormat.format(date) + " " + "PENDING");
        bw.newLine();
        bw.close();
        transHist.close();
    }
}