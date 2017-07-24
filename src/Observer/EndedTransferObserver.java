package Observer;

import Observed.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EndedTransferObserver implements IObserver {
    private Transaction transfer;

    private String newFile() {
        return "finished_transfers.txt";
    }

    public EndedTransferObserver(Transaction transfer) throws IOException {
        this.transfer = transfer;
        transfer.endedObserver = this;
    }

    @Override
    public void Notify() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        FileWriter transFin = new FileWriter(newFile(), true);
        BufferedWriter bw = new BufferedWriter(transFin);
        bw.write(Transaction.id + " " + transfer.client.acc.getNumber() + " " + transfer.rec.acc.getNumber()
                + " " + transfer.amount + " " + dateFormat.format(date) + " FINISHED");
        bw.newLine();
        bw.close();
        transFin.close();
    }
}