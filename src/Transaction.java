import java.time.format.DateTimeFormatter;

public class Transaction {
    private static int transactionCounter;

    private String transactionId;
    private String accountNumber;
    private String type;
    private double amount;
    private double balanceAfter;

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

    public Transaction(String accountNumber, String type, double amount, double balanceAfter) {

        transactionCounter++;
        this.transactionId = String.format("TXN%03d", transactionCounter);
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;

    }


    public void displayTransactionDetails() {

    }
}
